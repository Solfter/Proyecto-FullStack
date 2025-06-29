package cl.alcoholicos.gestorestacionamiento.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(ReservaSchedulerService.class);

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
        try {
            LocalDateTime ahora = LocalDateTime.now();
            
            // Buscar reservas CONFIRMADAS que han vencido
            List<ReservaEntity> reservasVencidas = reservaRepository.findReservasVencidas();

            if (reservasVencidas.isEmpty()) {
                logger.debug("No se encontraron reservas vencidas para actualizar");
                return;
            }

            // Estado correcto para reservas vencidas
            TipoEstadoReservaEntity estadoExpirada = tipoEstadoReservaRepository.findByDescTipoEstadoReserva("Expirada")
                .orElseThrow(() -> new RuntimeException("No se pudo encontrar el estado de reserva Expirada"));

            int procesadas = 0;
            for (ReservaEntity reserva : reservasVencidas) {
                try {
                    // 1. Cambiar estado de la RESERVA a Expirada
                    EstadoReservaEntity nuevoEstado = new EstadoReservaEntity();
                    nuevoEstado.setReserva(reserva);
                    nuevoEstado.setTipoEstadoReserva(estadoExpirada);
                    nuevoEstado.setFechaEstadoReserva(ahora);
                    estadoReservaRepository.save(nuevoEstado);

                    // 2. LIBERAR EL ESTACIONAMIENTO (cambiar a Disponible)
                    EstacionamientoEntity estacionamiento = reserva.getEstacionamiento();
                    estacionamientoRepository.liberarEstacionamiento(estacionamiento.getNroEstacionamiento());
                    
                    procesadas++;
                } catch (Exception e) {
                    logger.error("Error procesando reserva vencida ID {}: {}", reserva.getIdReserva(), e.getMessage());
                }
            }
            
            logger.info("Se actualizaron {} reservas vencidas a Expirada", procesadas);
            
        } catch (Exception e) {
            logger.error("Error en actualizarReservasVencidas: {}", e.getMessage(), e);
        }
    }

    @Scheduled(fixedRate = 300000) // 5 minutos en milisegundos
    public void actualizarReservasCompletadas() {
        try {
            LocalDateTime ahora = LocalDateTime.now();
            
            // Buscar reservas EN_USO que han completado
            List<ReservaEntity> reservasCompletadas = reservaRepository.findReservasCompletadas();

            if (reservasCompletadas.isEmpty()) {
                logger.debug("No se encontraron reservas completadas para actualizar");
                return;
            }

            // Estado correcto para reservas completadas
            TipoEstadoReservaEntity estadoCompletada = tipoEstadoReservaRepository.findByDescTipoEstadoReserva("Completada")
                .orElseThrow(() -> new RuntimeException("No se pudo encontrar el estado de reserva Completada"));

            int procesadas = 0;
            for (ReservaEntity reserva : reservasCompletadas) {
                try {
                    // 1. Cambiar estado de la RESERVA a Completada
                    EstadoReservaEntity nuevoEstado = new EstadoReservaEntity();
                    nuevoEstado.setReserva(reserva);
                    nuevoEstado.setTipoEstadoReserva(estadoCompletada);
                    nuevoEstado.setFechaEstadoReserva(ahora);
                    estadoReservaRepository.save(nuevoEstado);

                    // 2. LIBERAR EL ESTACIONAMIENTO (cambiar a Disponible)
                    EstacionamientoEntity estacionamiento = reserva.getEstacionamiento();
                    estacionamientoRepository.liberarEstacionamiento(estacionamiento.getNroEstacionamiento());
                    
                    procesadas++;
                } catch (Exception e) {
                    logger.error("Error procesando reserva completada ID {}: {}", reserva.getIdReserva(), e.getMessage());
                }
            }
            
            logger.info("Se actualizaron {} reservas completadas a Completada", procesadas);
            
        } catch (Exception e) {
            logger.error("Error en actualizarReservasCompletadas: {}", e.getMessage(), e);
        }
    }

    // Ejecutar solo una vez al inicio para limpiar reservas canceladas pendientes
    @Scheduled(initialDelay = 10000, fixedRate = 300000) // 10 segundos de delay inicial, luego cada 5 minutos
    public void liberarEstacionamientosReservasCanceladas() {
        try {
            // Buscar reservas que están canceladas
            List<ReservaEntity> reservasCanceladas = reservaRepository.findReservasCanceladas();

            if (reservasCanceladas.isEmpty()) {
                logger.debug("No se encontraron estacionamientos de reservas canceladas para liberar");
                return;
            }

            int liberados = 0;
            for (ReservaEntity reserva : reservasCanceladas) {
                try {
                    // Solo LIBERAR EL ESTACIONAMIENTO
                    // No cambiar estado de la reserva porque ya está cancelada
                    EstacionamientoEntity estacionamiento = reserva.getEstacionamiento();
                    estacionamientoRepository.liberarEstacionamiento(estacionamiento.getNroEstacionamiento());
                    liberados++;
                } catch (Exception e) {
                    logger.error("Error liberando estacionamiento de reserva cancelada ID {}: {}", reserva.getIdReserva(), e.getMessage());
                }
            }
            
            logger.info("Se liberaron {} estacionamientos de reservas canceladas", liberados);
            
        } catch (Exception e) {
            logger.error("Error en liberarEstacionamientosReservasCanceladas: {}", e.getMessage(), e);
        }
    }
}