package cl.alcoholicos.gestorestacionamiento.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import cl.alcoholicos.gestorestacionamiento.dto.EstadoReservaResponseDTO;
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
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Service
public class EstadoReservaService implements IEstadoReserva {

    // Constantes para estados de reserva
    private static final String ESTADO_CONFIRMADA = "Confirmada";
    private static final String ESTADO_ACTIVA = "Activa";
    private static final String ESTADO_CANCELADA = "Cancelada";
    
    // Constantes para estados de estacionamiento
    private static final String ESTADO_OCUPADO = "Ocupado";
    private static final String ESTADO_DISPONIBLE = "Disponible";

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
        TipoEstadoReservaEntity tipoEstadoReserva = obtenerTipoEstadoReserva(ESTADO_CONFIRMADA);
        
        EstadoReservaEntity estadoInicial = new EstadoReservaEntity();
        estadoInicial.setReserva(reserva);
        estadoInicial.setTipoEstadoReserva(tipoEstadoReserva);
        estadoInicial.setFechaEstadoReserva(LocalDateTime.now());

        estadoReservaRepository.save(estadoInicial);
        log.info("Estado inicial creado para reserva ID: {}", reserva.getIdReserva());
    }

    @Transactional
    public boolean actualizarAEstadoActiva(Integer idReserva) {
        ReservaEntity reserva = obtenerReserva(idReserva);
        validarEstadosPrevios(reserva.getIdReserva());
        
        EstadoReservaEntity ultimoEstado = obtenerUltimoEstado(reserva.getIdReserva());
        TipoEstadoReservaEntity tipoEstadoConfirmada = obtenerTipoEstadoReserva(ESTADO_CONFIRMADA);
        
        if (!ultimoEstado.getTipoEstadoReserva().equals(tipoEstadoConfirmada)) {
            throw new IllegalStateException("La reserva debe estar en estado \"" + ESTADO_CONFIRMADA + "\" para continuar");
        }

        // Crear nuevo estado activo
        crearNuevoEstado(reserva, ESTADO_ACTIVA);
        
        // Actualizar estado del estacionamiento
        actualizarEstadoEstacionamiento(reserva, ESTADO_OCUPADO);
        
        log.info("Reserva ID: {} actualizada a estado Activa", idReserva);
        return true;
    }

    @Transactional
    public boolean actualizarAEstadoCancelada(ReservaEntity reserva) {
        validarEstadosPrevios(reserva.getIdReserva());
        
        // Crear nuevo estado cancelado
        crearNuevoEstado(reserva, ESTADO_CANCELADA);
        
        // Actualizar estado del estacionamiento
        actualizarEstadoEstacionamiento(reserva, ESTADO_DISPONIBLE);
        
        log.info("Reserva ID: {} actualizada a estado Cancelada", reserva.getIdReserva());
        return true;
    }

    // Métodos privados para reducir duplicación de código
    
    private ReservaEntity obtenerReserva(Integer idReserva) {
        return reservaRepository.findById(idReserva)
                .orElseThrow(() -> new EntityNotFoundException("Reserva no encontrada con ID: " + idReserva));
    }

    private void validarEstadosPrevios(Integer idReserva) {
        List<EstadoReservaEntity> estadosReservas = estadoReservaRepository
                .findAllByReservaIdReserva(idReserva);
        
        if (estadosReservas.isEmpty()) {
            throw new IllegalStateException("La reserva no tiene estados previos registrados");
        }
    }

    private EstadoReservaEntity obtenerUltimoEstado(Integer idReserva) {
        List<EstadoReservaEntity> estadosReservas = estadoReservaRepository
                .findAllByReservaIdReserva(idReserva);
        return estadosReservas.get(estadosReservas.size() - 1);
    }

    private TipoEstadoReservaEntity obtenerTipoEstadoReserva(String descripcion) {
        return tipoEstadoReservaRepository
                .findByDescTipoEstadoReserva(descripcion)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Tipo de estado \"" + descripcion + "\" no encontrado"));
    }

    private void crearNuevoEstado(ReservaEntity reserva, String descripcionEstado) {
        TipoEstadoReservaEntity tipoEstado = obtenerTipoEstadoReserva(descripcionEstado);
        
        EstadoReservaEntity nuevoEstado = new EstadoReservaEntity();
        nuevoEstado.setReserva(reserva);
        nuevoEstado.setTipoEstadoReserva(tipoEstado);
        nuevoEstado.setFechaEstadoReserva(LocalDateTime.now());
        
        estadoReservaRepository.save(nuevoEstado);
    }

    private void actualizarEstadoEstacionamiento(ReservaEntity reserva, String descripcionEstado) {
        int nroEstacionamiento = reserva.getEstacionamiento().getNroEstacionamiento();
        
        EstacionamientoEntity estacionamiento = estacionamientoRepository
                .findByNroEstacionamiento(nroEstacionamiento)
                .orElseThrow(() -> new EntityNotFoundException(
                        "No se encontró el estacionamiento con número: " + nroEstacionamiento));

        EstadoEstacionamientoEntity estadoEstacionamiento = estadoEstacionamientoRepository
                .findByDescEstadoEstacionamiento(descripcionEstado)
                .orElseThrow(() -> new EntityNotFoundException(
                        "No se encontró el estado de estacionamiento \"" + descripcionEstado + "\""));

        estacionamiento.setEstadoEstacionamiento(estadoEstacionamiento);
        estacionamientoRepository.save(estacionamiento);
        
        log.debug("Estado del estacionamiento {} actualizado a: {}", 
                nroEstacionamiento, descripcionEstado);
    }
}
