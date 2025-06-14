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

        TipoEstadoReservaEntity estadoReservaExpirada = tipoEstadoReservaRepository.findById(5)
            .orElseThrow(() -> new RuntimeException("No se pudo encontrar el estado de reserva Expirado"));

        for (ReservaEntity reserva : reservasVencidas) {
            // Cambiar estado de la RESERVA
            EstadoReservaEntity nuevoEstado = new EstadoReservaEntity();
            nuevoEstado.setReserva(reserva);
            nuevoEstado.setTipoEstadoReserva(estadoReservaExpirada);
            nuevoEstado.setFechaEstadoReserva(ahora);
            estadoReservaRepository.save(nuevoEstado);

            // 2. LIBERAR EL ESTACIONAMIENTO (LO MÁS IMPORTANTE)
            EstacionamientoEntity estacionamiento = reserva.getEstacionamiento();
            estacionamientoRepository.liberarEstacionamiento(estacionamiento.getIdEstacionamiento());
        }
        
        if (!reservasVencidas.isEmpty()) {
            System.out.println("Se actualizaron " + reservasVencidas.size() + " reservas vencidas a Disponible");
        }
    }
}
