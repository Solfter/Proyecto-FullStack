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

        EstacionamientoEntity estacionamiento = estacionamientoRepository.findByNroEstacionamiento(createDTO.getNroEstacionamiento())
                .orElseThrow(() -> new EntityNotFoundException("Estacionamiento no encontrado"));

        EstadoEstacionamientoEntity reservado = estadoEstacionamientoRepository.findByDescEstadoEstacionamiento("Reservado")
                .orElseThrow(() -> new EntityNotFoundException("Estado de estacionamiento no encontrado"));

        UsuarioEntity usuario = usuarioRepository.findById(rutUsuario)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));

        if (!estacionamiento.getEstadoEstacionamiento().getDescEstadoEstacionamiento().equals("Disponible")) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "El estacionamiento se encuentra en estado de: "
                    + estacionamiento.getEstadoEstacionamiento().getDescEstadoEstacionamiento());
        }

        estacionamiento.setEstadoEstacionamiento(reservado);
        reserva.setEstacionamiento(estacionamiento);
        reserva.setUsuario(usuario);
        reserva.setFechaCreacionReserva(LocalDate.now());

        ReservaEntity reservaGuardada = reservaRepository.save(reserva);
        ReservaResponseDTO responseDTO = reservaMapper.toResponseDTO(reservaGuardada);

        estadoReservaService.crearEstadoInicial(reservaGuardada);

        return responseDTO;
    }

    @Override
    public ReservaResponseDTO update(Integer idReserva, ReservaUpdateDTO reservaUpdate, Integer rutUsuario) {
        // Buscar la reserva existente
        ReservaEntity reservaExistente = reservaRepository.findById(idReserva)
                .orElseThrow(() -> new EntityNotFoundException("Reserva no encontrada con ID: " + idReserva));

        // VALIDAR AUTORIZACIÓN - El usuario solo puede modificar sus propias reservas
        if (reservaExistente.getUsuario().getRut() != rutUsuario) {
            throw new SecurityException("No tienes permisos para modificar esta reserva");
        }

        // Validar que la reserva esté en estado "confirmada"
        if (!esEstadoConfirmada(reservaExistente)) {
            throw new IllegalStateException("Solo se pueden modificar reservas en estado 'confirmada'");
        }

        // Validar que la reserva no haya pasado (opcional, depende de tu lógica de
        // negocio)
        if (esReservaPasada(reservaExistente)) {
            throw new IllegalStateException("No se pueden modificar reservas que ya han pasado");
        }

        // Obtener fecha y hora actual del sistema
        LocalDate fechaActual = LocalDate.now();
        LocalTime horaActual = LocalTime.now();

        // Validar y actualizar fecha
        LocalDate nuevaFecha = validarYObtenerFecha(reservaUpdate, reservaExistente, fechaActual);

        // Validar y actualizar horas
        LocalTime[] nuevasHoras = validarYObtenerHoras(reservaUpdate, reservaExistente, nuevaFecha, fechaActual,
                horaActual);
        LocalTime nuevaHoraInicio = nuevasHoras[0];
        LocalTime nuevaHoraFin = nuevasHoras[1];

        // Validar disponibilidad del estacionamiento
        validarDisponibilidadEstacionamiento(reservaExistente, nuevaFecha, nuevaHoraInicio, nuevaHoraFin, idReserva);

        // Actualizar la entidad
        actualizarReservaEntity(reservaExistente, nuevaFecha, nuevaHoraInicio, nuevaHoraFin, reservaUpdate);

        // Guardar y retornar
        ReservaEntity reservaActualizada = reservaRepository.save(reservaExistente);
        return reservaMapper.toResponseDTO(reservaActualizada);
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
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "La fecha de reserva no puede ser anterior a la fecha actual");
        }

        if (createDTO.getFechaReserva().isEqual(LocalDate.now()) &&
                createDTO.getHoraInicio().isBefore(LocalTime.now())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "La hora de inicio no puede ser anterior a la hora actual");
        }

        if (createDTO.getHoraFin().isBefore(createDTO.getHoraInicio())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "La hora de fin no puede ser anterior a la hora de inicio");
        }
    }

    @Override
    public List<ReservaResponseDTO> getByUserId(Integer rutUsuario) {
        return reservaRepository.findByUsuarioRut(rutUsuario).stream()
                .map(reservaMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    private LocalDate validarYObtenerFecha(ReservaUpdateDTO update, ReservaEntity existente, LocalDate fechaActual) {
        if (update.getFechaReserva() != null) {
            LocalDate nuevaFecha = update.getFechaReserva();
            if (nuevaFecha.isBefore(fechaActual)) {
                throw new IllegalArgumentException("La fecha de reserva no puede ser anterior a la fecha actual");
            }
            return nuevaFecha;
        }
        return existente.getFechaReserva();
    }

    private LocalTime[] validarYObtenerHoras(ReservaUpdateDTO update, ReservaEntity existente,
            LocalDate fecha, LocalDate fechaActual, LocalTime horaActual) {
        LocalTime horaInicio = update.getHoraInicio() != null ? update.getHoraInicio() : existente.getHoraInicio();
        LocalTime horaFin = update.getHoraFin() != null ? update.getHoraFin() : existente.getHoraFin();

        if (horaInicio.isAfter(horaFin) || horaInicio.equals(horaFin)) {
            throw new IllegalArgumentException("La hora de inicio debe ser anterior a la hora de fin");
        }

        if (fecha.equals(fechaActual) && horaInicio.isBefore(horaActual)) {
            throw new IllegalArgumentException(
                    "Para reservas de hoy, la hora de inicio no puede ser anterior a la hora actual");
        }

        return new LocalTime[] { horaInicio, horaFin };
    }

    private void validarDisponibilidadEstacionamiento(ReservaEntity reserva, LocalDate fecha,
            LocalTime horaInicio, LocalTime horaFin, Integer idReserva) {
        if (hayCambioEnHorario(reserva, horaInicio, horaFin) || hayCambioEnFecha(reserva, fecha)) {
            if (!estacionamientoDisponible(reserva.getEstacionamiento().getIdEstacionamiento(),
                    fecha, horaInicio, horaFin, idReserva)) {
                throw new IllegalStateException("El estacionamiento no está disponible en el horario solicitado");
            }
        }
    }

    private void actualizarReservaEntity(ReservaEntity reserva, LocalDate fecha,
            LocalTime horaInicio, LocalTime horaFin, ReservaUpdateDTO update) {
        if (update.getFechaReserva() != null) {
            reserva.setFechaReserva(fecha);
        }
        if (update.getHoraInicio() != null) {
            reserva.setHoraInicio(horaInicio);
        }
        if (update.getHoraFin() != null) {
            reserva.setHoraFin(horaFin);
        }
    }

    private boolean esReservaPasada(ReservaEntity reserva) {
        LocalDate fechaActual = LocalDate.now();
        LocalTime horaActual = LocalTime.now();

        return reserva.getFechaReserva().isBefore(fechaActual) ||
                (reserva.getFechaReserva().equals(fechaActual) && reserva.getHoraFin().isBefore(horaActual));
    }

    private boolean esEstadoConfirmada(ReservaEntity reserva) {
        // Verificar si existe al menos un estado "confirmada" activo
        TipoEstadoReservaEntity tipoEstadoReservaConfirmada = tipoEstadoReservaRepository.findByDescTipoEstadoReserva("Confirmada")
                .orElseThrow(() -> new EntityNotFoundException("No se encuentra la descripcion confirmada de tipoEstadoReserva"));

        List<EstadoReservaEntity> estadosReserva = reserva.getEstadosReservas();

        if (estadosReserva == null || estadosReserva.isEmpty()) {
            return false;
        }

        if (estadosReserva.getLast().getTipoEstadoReserva().equals(tipoEstadoReservaConfirmada)) {
            return true;
        }
        return false;
    }

    private boolean hayCambioEnHorario(ReservaEntity reservaExistente, LocalTime nuevaHoraInicio,
            LocalTime nuevaHoraFin) {
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
