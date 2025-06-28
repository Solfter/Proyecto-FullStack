package cl.alcoholicos.gestorestacionamiento.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cl.alcoholicos.gestorestacionamiento.entity.EstacionamientoEntity;
import cl.alcoholicos.gestorestacionamiento.entity.EstadoReservaEntity;
import cl.alcoholicos.gestorestacionamiento.entity.ReservaEntity;
import cl.alcoholicos.gestorestacionamiento.entity.TipoEstadoReservaEntity;
import cl.alcoholicos.gestorestacionamiento.repository.EstacionamientoRepository;
import cl.alcoholicos.gestorestacionamiento.repository.EstadoReservaRepository;
import cl.alcoholicos.gestorestacionamiento.repository.ReservaRepository;
import cl.alcoholicos.gestorestacionamiento.repository.TipoEstadoReservaRepository;

@Service
@Transactional
public class ReservaSchedulerService {

    @Autowired
    private ReservaRepository reservaRepository;
    @Autowired
    private EstadoReservaRepository estadoReservaRepository;
    @Autowired
    private TipoEstadoReservaRepository tipoEstadoReservaRepository;
    @Autowired
    private EstacionamientoRepository estacionamientoRepository;

    // Se ejecuta cada 5 minutos
    @Scheduled(fixedRate = 300000) // 5 minutos en milisegundos
    public void actualizarReservasVencidas() {
        LocalDateTime ahora = LocalDateTime.now();
        
        // Buscar reservas que han vencido
        List<ReservaEntity> reservasVencidas = reservaRepository.findReservasVencidas();

        // Estado correcto para reservas vencidas
        TipoEstadoReservaEntity estadoExpirada = tipoEstadoReservaRepository.findByDescTipoEstadoReserva("Expirada")
            .orElseThrow(() -> new RuntimeException("No se pudo encontrar el estado de reserva Expirada"));

        for (ReservaEntity reserva : reservasVencidas) {
            // 1. Cambiar estado de la RESERVA a Expirada
            EstadoReservaEntity nuevoEstado = new EstadoReservaEntity();
            nuevoEstado.setReserva(reserva);
            nuevoEstado.setTipoEstadoReserva(estadoExpirada);
            nuevoEstado.setFechaEstadoReserva(ahora);
            estadoReservaRepository.save(nuevoEstado);

            // 2. LIBERAR EL ESTACIONAMIENTO (cambiar a Disponible)
            EstacionamientoEntity estacionamiento = reserva.getEstacionamiento();
            estacionamientoRepository.liberarEstacionamiento(estacionamiento.getNroEstacionamiento());
        }
        
        if (!reservasVencidas.isEmpty()) {
            System.out.println("Se actualizaron " + reservasVencidas.size() + " reservas vencidas a Expirada");
        }
    }

    @Scheduled(fixedRate = 300000) // 5 minutos en milisegundos
    public void actualizarReservasCompletadas() {
        LocalDateTime ahora = LocalDateTime.now();
        
        // Buscar reservas que han completado
        List<ReservaEntity> reservasCompletadas = reservaRepository.findReservasCompletadas();

        // Estado correcto para reservas completadas
        TipoEstadoReservaEntity estadoCompletada = tipoEstadoReservaRepository.findByDescTipoEstadoReserva("Completada")
            .orElseThrow(() -> new RuntimeException("No se pudo encontrar el estado de reserva Completada"));

        for (ReservaEntity reserva : reservasCompletadas) {
            // 1. Cambiar estado de la RESERVA a Completada
            EstadoReservaEntity nuevoEstado = new EstadoReservaEntity();
            nuevoEstado.setReserva(reserva);
            nuevoEstado.setTipoEstadoReserva(estadoCompletada);
            nuevoEstado.setFechaEstadoReserva(ahora);
            estadoReservaRepository.save(nuevoEstado);

            // 2. LIBERAR EL ESTACIONAMIENTO (cambiar a Disponible)
            EstacionamientoEntity estacionamiento = reserva.getEstacionamiento();
            estacionamientoRepository.liberarEstacionamiento(estacionamiento.getNroEstacionamiento());
        }
        
        if (!reservasCompletadas.isEmpty()) {
            System.out.println("Se actualizaron " + reservasCompletadas.size() + " reservas completadas a Completada");
        }
    }

    @Scheduled(fixedRate = 300000) // 5 minutos en milisegundos
    public void liberarEstacionamientosReservasCanceladas() {
        // Buscar reservas que están canceladas
        List<ReservaEntity> reservasCanceladas = reservaRepository.findReservasCanceladas();

        for (ReservaEntity reserva : reservasCanceladas) {
            // Solo LIBERAR EL ESTACIONAMIENTO
            // No cambiar estado de la reserva porque ya está cancelada
            EstacionamientoEntity estacionamiento = reserva.getEstacionamiento();
            estacionamientoRepository.liberarEstacionamiento(estacionamiento.getNroEstacionamiento());
        }
        
        if (!reservasCanceladas.isEmpty()) {
            System.out.println("Se liberaron " + reservasCanceladas.size() + " estacionamientos de reservas canceladas");
        }
    }
}