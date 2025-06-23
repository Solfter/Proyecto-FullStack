package cl.alcoholicos.gestorestacionamiento.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import cl.alcoholicos.gestorestacionamiento.dto.ReservaCreateDTO;
import cl.alcoholicos.gestorestacionamiento.dto.ReservaResponseDTO;
import cl.alcoholicos.gestorestacionamiento.dto.ReservaUpdateDTO;
import cl.alcoholicos.gestorestacionamiento.entity.EstacionamientoEntity;
import cl.alcoholicos.gestorestacionamiento.entity.EstadoEstacionamientoEntity;
import cl.alcoholicos.gestorestacionamiento.entity.EstadoReservaEntity;
import cl.alcoholicos.gestorestacionamiento.entity.ReservaEntity;
import cl.alcoholicos.gestorestacionamiento.entity.TipoEstadoReservaEntity;
import cl.alcoholicos.gestorestacionamiento.entity.UsuarioEntity;
import cl.alcoholicos.gestorestacionamiento.mapper.ReservaMapper;
import cl.alcoholicos.gestorestacionamiento.repository.EstacionamientoRepository;
import cl.alcoholicos.gestorestacionamiento.repository.EstadoEstacionamientoRepository;
import cl.alcoholicos.gestorestacionamiento.repository.EstadoReservaRepository;
import cl.alcoholicos.gestorestacionamiento.repository.ReservaRepository;
import cl.alcoholicos.gestorestacionamiento.repository.TipoEstadoReservaRepository;
import cl.alcoholicos.gestorestacionamiento.repository.UsuarioRepository;
import cl.alcoholicos.gestorestacionamiento.service.IReserva;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ReservaService implements IReserva {

    private final ReservaRepository reservaRepository;
    private final ReservaMapper reservaMapper;
    private final EstacionamientoRepository estacionamientoRepository;
    private final UsuarioRepository usuarioRepository;
    private final EstadoEstacionamientoRepository estadoEstacionamientoRepository;
    private final EstadoReservaService estadoReservaService;
    private final TipoEstadoReservaRepository tipoEstadoReservaRepository;

    @Override
    @Transactional
    public ReservaResponseDTO insert(ReservaCreateDTO createDTO, Integer rutUsuario) {

        validarReserva(createDTO);

        ReservaEntity reserva = reservaMapper.toEntity(createDTO);

        EstacionamientoEntity estacionamiento = estacionamientoRepository.findById(createDTO.getIdEstacionamiento())
                                                .orElseThrow(() -> new EntityNotFoundException("Estacionamiento no encontrado"));

        EstadoEstacionamientoEntity reservado = estadoEstacionamientoRepository.findById(3)
            .orElseThrow(() -> new EntityNotFoundException("Estado de estacionamiento no encontrado"));

        UsuarioEntity usuario = usuarioRepository.findById(rutUsuario)
                                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));


        if (estacionamiento.getEstadoEstacionamiento().getIdEstadoEstacionamiento() != 1) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "El estacionamiento se encuentra en estado de: " + estacionamiento.getEstadoEstacionamiento().getDescEstadoEstacionamiento());
        }

        estacionamiento.setEstadoEstacionamiento(reservado);
        reserva.setEstacionamiento(estacionamiento);
        reserva.setUsuario(usuario);
        reserva.setFechaCreacionReserva(LocalDateTime.now());
        
        ReservaEntity reservaGuardada = reservaRepository.save(reserva);
        ReservaResponseDTO responseDTO = reservaMapper.toResponseDTO(reservaGuardada);
        

        estadoReservaService.crearEstadoInicial(reservaGuardada);
        
        return responseDTO;
    }

    @Override
    public ReservaResponseDTO update(Integer idReserva, ReservaUpdateDTO reservaUpdate) {
        // Buscar la reserva existente
        ReservaEntity reservaExistente = reservaRepository.findById(idReserva)
                .orElseThrow(() -> new EntityNotFoundException("Reserva no encontrada con ID: " + idReserva));
        
        
        // Validar que la reserva esté en estado "confirmada"
        if (!esEstadoConfirmada(reservaExistente)) {
            throw new IllegalStateException("Solo se pueden modificar reservas en estado 'confirmada'");
        }
        
        // Obtener fecha y hora actual del sistema
        LocalDate fechaActual = LocalDate.now();
        LocalTime horaActual = LocalTime.now();
        
        // Validaciones de fecha
        if (reservaUpdate.getFechaReserva() != null) {
            LocalDate nuevaFecha = reservaUpdate.getFechaReserva();
            
            // La fecha no puede ser menor a la fecha del sistema
            if (nuevaFecha.isBefore(fechaActual)) {
                throw new IllegalArgumentException("La fecha de reserva no puede ser anterior a la fecha actual");
            }
            
            reservaExistente.setFechaReserva(nuevaFecha);
        }
        
        // Validaciones de hora
        LocalTime nuevaHoraInicio = reservaUpdate.getHoraInicio();
        LocalTime nuevaHoraFin = reservaUpdate.getHoraFin();
        
        // Si no se proporciona hora de inicio, usar la existente
        if (nuevaHoraInicio == null) {
            nuevaHoraInicio = reservaExistente.getHoraInicio();
        }
        
        // Si no se proporciona hora de fin, usar la existente
        if (nuevaHoraFin == null) {
            nuevaHoraFin = reservaExistente.getHoraFin();
        }
        
        // Validar que hora de inicio no sea mayor a hora de fin
        if (nuevaHoraInicio.isAfter(nuevaHoraFin) || nuevaHoraInicio.equals(nuevaHoraFin)) {
            throw new IllegalArgumentException("La hora de inicio debe ser anterior a la hora de fin");
        }
        
        // Si la fecha de reserva es hoy, validar que la hora de inicio no sea menor a la hora actual
        LocalDate fechaReserva = reservaExistente.getFechaReserva();
        if (fechaReserva.equals(fechaActual) && nuevaHoraInicio.isBefore(horaActual)) {
            throw new IllegalArgumentException("Para reservas de hoy, la hora de inicio no puede ser anterior a la hora actual");
        }
        
        // Validar disponibilidad del estacionamiento en el nuevo horario (si cambió)
        if (hayCambioEnHorario(reservaExistente, nuevaHoraInicio, nuevaHoraFin) || 
            hayCambioEnFecha(reservaExistente, reservaUpdate.getFechaReserva())) {
            
            if (!estacionamientoDisponible(reservaExistente.getEstacionamiento().getIdEstacionamiento(), 
                                        fechaReserva, nuevaHoraInicio, nuevaHoraFin, idReserva)) {
                throw new IllegalStateException("El estacionamiento no está disponible en el horario solicitado");
            }
        }
        
        // Actualizar los campos
        if (reservaUpdate.getHoraInicio() != null) {
            reservaExistente.setHoraInicio(nuevaHoraInicio);
        }
        
        if (reservaUpdate.getHoraFin() != null) {
            reservaExistente.setHoraFin(nuevaHoraFin);
        }
        
        // Guardar la reserva actualizada
        ReservaEntity reservaActualizada = reservaRepository.save(reservaExistente);

        ReservaResponseDTO reservaResponseDTO = reservaMapper.toResponseDTO(reservaActualizada);
        
        // Convertir a DTO y retornar
        return reservaResponseDTO;
    }


    @Override
    public boolean delete(Integer idReserva) {
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public ReservaResponseDTO getByReservaId(Integer idReserva) {
        return reservaRepository.findById(idReserva)
            .map(reservaMapper::toResponseDTO)
            .orElse(null);
    }

    @Override
    public List<ReservaResponseDTO> getAll() {
        return reservaRepository.findAll().stream()
            .map(reservaMapper::toResponseDTO)
            .collect(Collectors.toList());
    }

    private void validarReserva(ReservaCreateDTO createDTO) {
        if (createDTO.getFechaReserva().isBefore(LocalDate.now())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "La fecha de reserva no puede ser anterior a la fecha actual");
        }

        if (createDTO.getFechaReserva().isEqual(LocalDate.now()) &&
            createDTO.getHoraInicio().isBefore(LocalTime.now())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "La hora de inicio no puede ser anterior a la hora actual");
        }

        if (createDTO.getHoraFin().isBefore(createDTO.getHoraInicio())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "La hora de fin no puede ser anterior a la hora de inicio");
        }
    }

    @Override
    public List<ReservaResponseDTO> getByUserId(Integer rutUsuario) {
        return reservaRepository.findByUsuarioRut(rutUsuario).stream()
            .map(reservaMapper::toResponseDTO)
            .collect(Collectors.toList());
    }

    private boolean esEstadoConfirmada(ReservaEntity reserva) {
        // Verificar si existe al menos un estado "confirmada" activo
        TipoEstadoReservaEntity tipoEstadoReservaConfirmada = tipoEstadoReservaRepository.findById(2)
            .orElseThrow(() -> new EntityNotFoundException("No se encuentra la id 2 de tipoEstadoReserva"));

        List<EstadoReservaEntity> estadosReserva = reserva.getEstadosReservas();
        
        if (estadosReserva == null || estadosReserva.isEmpty()) {
            return false;
        }

        if (estadosReserva.getLast().getTipoEstadoReserva().equals(tipoEstadoReservaConfirmada)) {
            return true;
        }
        return false;
    }

    private boolean hayCambioEnHorario(ReservaEntity reservaExistente, LocalTime nuevaHoraInicio, LocalTime nuevaHoraFin) {
        return !reservaExistente.getHoraInicio().equals(nuevaHoraInicio) || 
               !reservaExistente.getHoraFin().equals(nuevaHoraFin);
    }

    private boolean hayCambioEnFecha(ReservaEntity reservaExistente, LocalDate nuevaFecha) {
        return nuevaFecha != null && !reservaExistente.getFechaReserva().equals(nuevaFecha);
    }

    private boolean estacionamientoDisponible(Integer idEstacionamiento, LocalDate fecha, 
                                        LocalTime horaInicio, LocalTime horaFin, Integer idReservaExcluir) {
        // Buscar reservas que se solapen en el mismo estacionamiento y horario
        List<ReservaEntity> reservasConflicto = reservaRepository.findReservasConflicto(
                idEstacionamiento, fecha, horaInicio, horaFin, idReservaExcluir);
        
    return reservasConflicto.isEmpty();
    }
}
