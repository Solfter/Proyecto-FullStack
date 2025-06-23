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
import cl.alcoholicos.gestorestacionamiento.entity.ReservaEntity;
import cl.alcoholicos.gestorestacionamiento.entity.UsuarioEntity;
import cl.alcoholicos.gestorestacionamiento.mapper.ReservaMapper;
import cl.alcoholicos.gestorestacionamiento.repository.EstacionamientoRepository;
import cl.alcoholicos.gestorestacionamiento.repository.EstadoEstacionamientoRepository;
import cl.alcoholicos.gestorestacionamiento.repository.ReservaRepository;
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
    public ReservaResponseDTO update(Integer idReserva, ReservaUpdateDTO reserva) {
        throw new UnsupportedOperationException("Unimplemented method 'update'");
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


}
