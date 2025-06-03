package cl.alcoholicos.gestorestacionamiento.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import cl.alcoholicos.gestorestacionamiento.dto.EstadoReservaResponseDTO;
import cl.alcoholicos.gestorestacionamiento.mapper.EstadoReservaMapper;
import cl.alcoholicos.gestorestacionamiento.repository.EstadoReservaRepository;
import cl.alcoholicos.gestorestacionamiento.service.IEstadoReserva;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class EstadoReservaService implements IEstadoReserva{

    private final EstadoReservaRepository estadoReservaRepository;
    private final EstadoReservaMapper estadoReservaMapper;

    @Override
    public EstadoReservaResponseDTO getById(Integer idEstadoReserva) {
        return estadoReservaRepository.findById(idEstadoReserva)
                .map(estadoReservaMapper::toResponseDTO)
                .orElseThrow(() -> new EntityNotFoundException("Estado de reserva no encontrado"));
    }

    @Override
    public List<EstadoReservaResponseDTO> getAll() {
        return estadoReservaRepository.findAll().stream()
                .map(estadoReservaMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

}
