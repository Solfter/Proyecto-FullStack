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

            logger.info("Procesando {} reservas vencidas", reservasVencidas.size());

            // Estado correcto para reservas vencidas
            TipoEstadoReservaEntity estadoExpirada = tipoEstadoReservaRepository.findByDescTipoEstadoReserva("Expirada")
                .orElseThrow(() -> new RuntimeException("No se pudo encontrar el estado de reserva Expirada"));

            int procesadas = 0;
            int yaProcesadas = 0;
            
            for (ReservaEntity reserva : reservasVencidas) {
                try {
                    // Verificar si ya está en estado Expirada
                    if (esReservaEnEstado(reserva, "Expirada")) {
                        yaProcesadas++;
                        continue;
                    }
                    
                    // 1. Cambiar estado de la RESERVA a Expirada
                    EstadoReservaEntity nuevoEstado = new EstadoReservaEntity();
                    nuevoEstado.setReserva(reserva);
                    nuevoEstado.setTipoEstadoReserva(estadoExpirada);
                    nuevoEstado.setFechaEstadoReserva(ahora);
                    estadoReservaRepository.save(nuevoEstado);

                    // 2. LIBERAR EL ESTACIONAMIENTO (cambiar a Disponible)
                    EstacionamientoEntity estacionamiento = reserva.getEstacionamiento();
                    int filasActualizadas = estacionamientoRepository.liberarEstacionamiento(estacionamiento.getNroEstacionamiento());
                    
                    procesadas++;
                    if (filasActualizadas > 0) {
                        logger.debug("Reserva {} marcada como Expirada y estacionamiento {} liberado", 
                                    reserva.getIdReserva(), estacionamiento.getNroEstacionamiento());
                    } else {
                        logger.debug("Reserva {} marcada como Expirada, estacionamiento {} ya estaba disponible", 
                                    reserva.getIdReserva(), estacionamiento.getNroEstacionamiento());
                    }
                    
                } catch (Exception e) {
                    logger.error("Error procesando reserva vencida ID {}: {}", reserva.getIdReserva(), e.getMessage());
                }
            }
            
            logger.info("Reservas vencidas procesadas: {} nuevas, {} ya procesadas", procesadas, yaProcesadas);
            
        } catch (Exception e) {
            logger.error("Error en actualizarReservasVencidas: {}", e.getMessage(), e);
        }
    }

    @Scheduled(fixedRate = 300000) // 5 minutos en milisegundos
    public void actualizarReservasCompletadas() {
        try {
            LocalDateTime ahora = LocalDateTime.now();
            
            // Buscar reservas ACTIVAS que han completado
            List<ReservaEntity> reservasCompletadas = reservaRepository.findReservasCompletadas();

            if (reservasCompletadas.isEmpty()) {
                logger.debug("No se encontraron reservas completadas para actualizar");
                return;
            }

            logger.info("Procesando {} reservas completadas", reservasCompletadas.size());

            // Estado correcto para reservas completadas
            TipoEstadoReservaEntity estadoCompletada = tipoEstadoReservaRepository.findByDescTipoEstadoReserva("Completada")
                .orElseThrow(() -> new RuntimeException("No se pudo encontrar el estado de reserva Completada"));

            int procesadas = 0;
            int yaProcesadas = 0;
            
            for (ReservaEntity reserva : reservasCompletadas) {
                try {
                    // Verificar si ya está en estado Completada
                    if (esReservaEnEstado(reserva, "Completada")) {
                        yaProcesadas++;
                        continue;
                    }
                    
                    // 1. Cambiar estado de la RESERVA a Completada
                    EstadoReservaEntity nuevoEstado = new EstadoReservaEntity();
                    nuevoEstado.setReserva(reserva);
                    nuevoEstado.setTipoEstadoReserva(estadoCompletada);
                    nuevoEstado.setFechaEstadoReserva(ahora);
                    estadoReservaRepository.save(nuevoEstado);

                    // 2. LIBERAR EL ESTACIONAMIENTO (cambiar a Disponible)
                    EstacionamientoEntity estacionamiento = reserva.getEstacionamiento();
                    int filasActualizadas = estacionamientoRepository.liberarEstacionamiento(estacionamiento.getNroEstacionamiento());
                    
                    procesadas++;
                    if (filasActualizadas > 0) {
                        logger.debug("Reserva {} marcada como Completada y estacionamiento {} liberado", 
                                    reserva.getIdReserva(), estacionamiento.getNroEstacionamiento());
                    } else {
                        logger.debug("Reserva {} marcada como Completada, estacionamiento {} ya estaba disponible", 
                                    reserva.getIdReserva(), estacionamiento.getNroEstacionamiento());
                    }
                    
                } catch (Exception e) {
                    logger.error("Error procesando reserva completada ID {}: {}", reserva.getIdReserva(), e.getMessage());
                }
            }
            
            logger.info("Reservas completadas procesadas: {} nuevas, {} ya procesadas", procesadas, yaProcesadas);
            
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

            logger.info("Procesando {} estacionamientos de reservas canceladas", reservasCanceladas.size());

            int liberados = 0;
            int yaDisponibles = 0;
            
            for (ReservaEntity reserva : reservasCanceladas) {
                try {
                    // Solo LIBERAR EL ESTACIONAMIENTO
                    // No cambiar estado de la reserva porque ya está cancelada
                    EstacionamientoEntity estacionamiento = reserva.getEstacionamiento();
                    int filasActualizadas = estacionamientoRepository.liberarEstacionamiento(estacionamiento.getNroEstacionamiento());
                    
                    if (filasActualizadas > 0) {
                        liberados++;
                        logger.debug("Estacionamiento {} liberado de reserva cancelada {}", 
                                    estacionamiento.getNroEstacionamiento(), reserva.getIdReserva());
                    } else {
                        yaDisponibles++;
                        logger.debug("Estacionamiento {} ya estaba disponible (reserva cancelada {})", 
                                    estacionamiento.getNroEstacionamiento(), reserva.getIdReserva());
                    }
                } catch (Exception e) {
                    logger.error("Error liberando estacionamiento de reserva cancelada ID {}: {}", reserva.getIdReserva(), e.getMessage());
                }
            }
            
            logger.info("Estacionamientos de reservas canceladas: {} liberados, {} ya disponibles", liberados, yaDisponibles);
            
        } catch (Exception e) {
            logger.error("Error en liberarEstacionamientosReservasCanceladas: {}", e.getMessage(), e);
        }
    }

    private boolean esReservaEnEstado(ReservaEntity reserva, String estado) {
        if (reserva.getEstadosReservas() == null || reserva.getEstadosReservas().isEmpty()) {
            return false;
        }
        
        // Obtener el estado más reciente
        EstadoReservaEntity estadoMasReciente = reserva.getEstadosReservas().getLast();
        return estadoMasReciente.getTipoEstadoReserva().getDescTipoEstadoReserva().equals(estado);
    }
}