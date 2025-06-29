package cl.alcoholicos.gestorestacionamiento.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import cl.alcoholicos.gestorestacionamiento.dto.EstadoReservaResponseDTO;
import cl.alcoholicos.gestorestacionamiento.dto.ReservaResponseDTO;
import cl.alcoholicos.gestorestacionamiento.entity.EstacionamientoEntity;
import cl.alcoholicos.gestorestacionamiento.entity.EstadoEstacionamientoEntity;
import cl.alcoholicos.gestorestacionamiento.entity.EstadoReservaEntity;
import cl.alcoholicos.gestorestacionamiento.entity.ReservaEntity;
import cl.alcoholicos.gestorestacionamiento.entity.TipoEstadoReservaEntity;
import cl.alcoholicos.gestorestacionamiento.mapper.EstadoReservaMapper;
import cl.alcoholicos.gestorestacionamiento.repository.EstacionamientoRepository;
import cl.alcoholicos.gestorestacionamiento.repository.EstadoEstacionamientoRepository;
import cl.alcoholicos.gestorestacionamiento.repository.EstadoReservaRepository;
import cl.alcoholicos.gestorestacionamiento.repository.ReservaRepository;
import cl.alcoholicos.gestorestacionamiento.repository.TipoEstadoReservaRepository;
import cl.alcoholicos.gestorestacionamiento.service.IEstadoReserva;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class EstadoReservaService implements IEstadoReserva {

    private final EstadoReservaRepository estadoReservaRepository;
    private final EstadoReservaMapper estadoReservaMapper;
    private final TipoEstadoReservaRepository tipoEstadoReservaRepository;
    private final ReservaRepository reservaRepository;
    private final EstacionamientoRepository estacionamientoRepository;
    private final EstadoEstacionamientoRepository estadoEstacionamientoRepository;

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

        TipoEstadoReservaEntity tipoEstadoReserva = tipoEstadoReservaRepository
                .findByDescTipoEstadoReserva("Confirmada")
                .orElseThrow(() -> new RuntimeException("Tipo de estado Inicial no encontrado"));

        estadoInicial.setReserva(reserva);
        estadoInicial.setTipoEstadoReserva(tipoEstadoReserva);
        estadoInicial.setFechaEstadoReserva(LocalDateTime.now());

        estadoReservaRepository.save(estadoInicial);

    }

    @Transactional
    public boolean actualizarAEstadoActiva(Integer idReserva) {

        ReservaEntity reserva = reservaRepository.findById(idReserva)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));

        List<EstadoReservaEntity> estadosReservas = estadoReservaRepository
                .findAllByReservaIdReserva(reserva.getIdReserva());

        if (estadosReservas.isEmpty()) {
            throw new IllegalStateException("La reserva no tiene estados previos registrados");
        }

        TipoEstadoReservaEntity tipoEstadoReservaActivo = tipoEstadoReservaRepository
                .findByDescTipoEstadoReserva("Activa")
                .orElseThrow(() -> new RuntimeException("Tipo de estado Activa no encontrado"));

        TipoEstadoReservaEntity tipoEstadoReservaConfirmada = tipoEstadoReservaRepository
                .findByDescTipoEstadoReserva("Confirmada")
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

        EstacionamientoEntity estacionamiento = estacionamientoRepository
                .findByNroEstacionamiento(reserva.getEstacionamiento().getNroEstacionamiento())
                .orElseThrow(() -> new RuntimeException("No se logro encontrar el estacionamiento con nro "
                        + reserva.getEstacionamiento().getNroEstacionamiento()));

        EstadoEstacionamientoEntity estadoEstacionamientoOcupado = estadoEstacionamientoRepository
                .findByDescEstadoEstacionamiento("Ocupado")
                .orElseThrow(() -> new EntityNotFoundException(
                        "No se logro encontrar el estado de estacionamiento \"Ocupado\""));

        estacionamiento.setEstadoEstacionamiento(estadoEstacionamientoOcupado);
        estacionamientoRepository.save(estacionamiento);

        System.out.println("Mi log personalizado: " + estacionamiento.getEstadoEstacionamiento());

        return true;

    }

    @Transactional
    public boolean actualizarAEstadoCancelada(ReservaEntity reserva) {

        List<EstadoReservaEntity> estadosReservas = estadoReservaRepository
                .findAllByReservaIdReserva(reserva.getIdReserva());

        if (estadosReservas.isEmpty()) {
            throw new IllegalStateException("La reserva no tiene estados previos registrados");
        }

        TipoEstadoReservaEntity tipoEstadoReservaCancelada = tipoEstadoReservaRepository
                .findByDescTipoEstadoReserva("Cancelada")
                .orElseThrow(() -> new RuntimeException("Tipo de estado Cancelada no encontrado"));

        TipoEstadoReservaEntity tipoEstadoReservaActiva = tipoEstadoReservaRepository
                .findByDescTipoEstadoReserva("Activa")
                .orElseThrow(() -> new RuntimeException("Tipo de estado Activa no encontrado"));

        TipoEstadoReservaEntity tipoEstadoReservaConfirmada = tipoEstadoReservaRepository
                .findByDescTipoEstadoReserva("Confirmada")
                .orElseThrow(() -> new RuntimeException("Tipo de estado Confirmada no encontrado"));

        EstadoReservaEntity ultimoEstado = estadosReservas.get(estadosReservas.size() - 1);

        /*
         * if (ultimoEstado.getTipoEstadoReserva() != tipoEstadoReservaConfirmada ||
         * ultimoEstado.getTipoEstadoReserva() != tipoEstadoReservaActiva) {
         * throw new
         * IllegalStateException("La reserva debe estar en estado \"Confirmada\" o \"Activa\" para continuar"
         * );
         * }
         */

        EstadoReservaEntity estadoActivo = new EstadoReservaEntity();

        estadoActivo.setReserva(reserva);
        estadoActivo.setTipoEstadoReserva(tipoEstadoReservaCancelada);
        estadoActivo.setFechaEstadoReserva(LocalDateTime.now());

        estadoReservaRepository.save(estadoActivo);

        EstacionamientoEntity estacionamiento = estacionamientoRepository
                .findByNroEstacionamiento(reserva.getEstacionamiento().getNroEstacionamiento())
                .orElseThrow(() -> new RuntimeException("No se logro encontrar el estacionamiento con nro "
                        + reserva.getEstacionamiento().getNroEstacionamiento()));

        EstadoEstacionamientoEntity estadoEstacionamientoDisponible = estadoEstacionamientoRepository
                .findByDescEstadoEstacionamiento("Disponible")
                .orElseThrow(() -> new EntityNotFoundException(
                        "No se logro encontrar el estado de estacionamiento \"Disponible\""));

        estacionamiento.setEstadoEstacionamiento(estadoEstacionamientoDisponible);
        estacionamientoRepository.save(estacionamiento);

        return true;
    }

}
