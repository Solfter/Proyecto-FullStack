package cl.alcoholicos.gestorestacionamiento.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import cl.alcoholicos.gestorestacionamiento.dto.EstadoReservaResponseDTO;
import cl.alcoholicos.gestorestacionamiento.entity.EstadoReservaEntity;
import cl.alcoholicos.gestorestacionamiento.entity.ReservaEntity;
import cl.alcoholicos.gestorestacionamiento.entity.TipoEstadoReservaEntity;
import cl.alcoholicos.gestorestacionamiento.mapper.EstadoReservaMapper;
import cl.alcoholicos.gestorestacionamiento.repository.EstadoReservaRepository;
import cl.alcoholicos.gestorestacionamiento.repository.TipoEstadoReservaRepository;
import cl.alcoholicos.gestorestacionamiento.service.IEstadoReserva;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class EstadoReservaService implements IEstadoReserva{

    private final EstadoReservaRepository estadoReservaRepository;
    private final EstadoReservaMapper estadoReservaMapper;
    private final TipoEstadoReservaRepository tipoEstadoReservaRepository;

    @Override
    public List<EstadoReservaResponseDTO> getByIdReserva(Integer idReserva) {
        return estadoReservaRepository.findAllByReservaIdReserva(idReserva).stream()
                .map(estadoReservaMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<EstadoReservaResponseDTO> getAll() {
        return estadoReservaRepository.findAll().stream()
                .map(estadoReservaMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void crearEstadoInicial(ReservaEntity reserva) {
        EstadoReservaEntity estadoInicial = new EstadoReservaEntity();
        
        TipoEstadoReservaEntity tipoEstadoReserva = tipoEstadoReservaRepository.findById(2)
            .orElseThrow(() -> new RuntimeException("Tipo de estado Inicial no encontrado"));
        
        estadoInicial.setReserva(reserva);
        estadoInicial.setTipoEstadoReserva(tipoEstadoReserva);
        estadoInicial.setFechaEstadoReserva(LocalDateTime.now());

        estadoReservaRepository.save(estadoInicial);

    }

    @Override
    public void actualizarAEstadoActiva(ReservaEntity reserva) {

        List<EstadoReservaEntity> estadosReservas = estadoReservaRepository.findAllByReservaIdReserva(reserva.getIdReserva());

        if (estadosReservas.isEmpty()) {
            throw new IllegalStateException("La reserva no tiene estados previos registrados");
        }

        TipoEstadoReservaEntity tipoEstadoReservaActivo = tipoEstadoReservaRepository.findById(1)
            .orElseThrow(() -> new RuntimeException("Tipo de estado Activa no encontrado"));
        
        TipoEstadoReservaEntity tipoEstadoReservaConfirmada = tipoEstadoReservaRepository.findById(2)
            .orElseThrow(() -> new RuntimeException("Tipo de estado Confirmada no encontrado"));

        EstadoReservaEntity ultimoEstado = estadosReservas.get(estadosReservas.size() - 1);

        if (ultimoEstado.getTipoEstadoReserva() != tipoEstadoReservaConfirmada) {
            throw new IllegalStateException("La reserva debe estar en estado \"Confirmada\" para continuar");
        }

        EstadoReservaEntity estadoActivo = new EstadoReservaEntity();

        estadoActivo.setReserva(reserva);
        estadoActivo.setTipoEstadoReserva(tipoEstadoReservaActivo);
        estadoActivo.setFechaEstadoReserva(LocalDateTime.now());

        estadoReservaRepository.save(estadoActivo);
    }

    
    

}
