package cl.alcoholicos.gestorestacionamiento.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import cl.alcoholicos.gestorestacionamiento.dto.ReservaCreateDTO;
import cl.alcoholicos.gestorestacionamiento.dto.ReservaResponseDTO;
import cl.alcoholicos.gestorestacionamiento.dto.ReservaUpdateDTO;
import cl.alcoholicos.gestorestacionamiento.entity.EstacionamientoEntity;
import cl.alcoholicos.gestorestacionamiento.entity.ReservaEntity;
import cl.alcoholicos.gestorestacionamiento.entity.UsuarioEntity;
import cl.alcoholicos.gestorestacionamiento.mapper.ReservaMapper;
import cl.alcoholicos.gestorestacionamiento.repository.EstacionamientoRepository;
import cl.alcoholicos.gestorestacionamiento.repository.ReservaRepository;
import cl.alcoholicos.gestorestacionamiento.repository.UsuarioRepository;
import cl.alcoholicos.gestorestacionamiento.service.IReserva;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ReservaService implements IReserva {

    private final ReservaRepository reservaRepository;
    private final ReservaMapper reservaMapper;
    private final EstacionamientoRepository estacionamientoRepository;
    private final UsuarioRepository usuarioRepository;

    @Override
    public ReservaResponseDTO insert(ReservaCreateDTO createDTO, Integer rutUsuario) {
        ReservaEntity reserva = reservaMapper.toEntity(createDTO);

        EstacionamientoEntity estacionamiento = estacionamientoRepository.findById(createDTO.getIdEstacionamiento())
                                                .orElseThrow(() -> new EntityNotFoundException("Estacionamiento no encontrado"));

        UsuarioEntity usuario = usuarioRepository.findById(rutUsuario)
                                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));

        if (estacionamiento.getEstadoEstacionamiento().getIdEstadoEstacionamiento() != 1) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "El estacionamiento no está dispnible");
        }

        reserva.setEstacionamiento(estacionamiento);
        reserva.setUsuario(usuario);
        
        ReservaEntity reservaGuardada = reservaRepository.save(reserva);

        ReservaResponseDTO responseDTO = reservaMapper.toResponseDTO(reservaGuardada);
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
    public ReservaResponseDTO getById(Integer idReserva) {
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

}
