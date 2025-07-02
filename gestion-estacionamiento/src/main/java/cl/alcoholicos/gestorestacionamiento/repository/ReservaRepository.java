package cl.alcoholicos.gestorestacionamiento.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cl.alcoholicos.gestorestacionamiento.entity.ReservaEntity;

@Repository
public interface ReservaRepository extends JpaRepository<ReservaEntity, Integer> {

    // Busca reservas que están CONFIRMADAS pero ya vencieron (no las que
    // ya están Expiradas)
    @Query(value = """
            SELECT r.* FROM reserva r
            INNER JOIN estado_reserva er ON r.id_reserva = er.id_reserva
            INNER JOIN tipo_estado_reserva ter ON er.id_tipo_estado_reserva = ter.id_tipo_estado_reserva
            WHERE r.fecha_reserva <= TRUNC(SYSDATE)
            AND TO_TIMESTAMP(
                TO_CHAR(r.fecha_reserva, 'YYYY-MM-DD') || ' ' ||
                TO_CHAR(r.hora_fin, 'HH24:MI:SS'),
                'YYYY-MM-DD HH24:MI:SS'
            ) <= SYSTIMESTAMP
            AND LOWER(ter.descripcion_tipo_estado_reserva) = LOWER('Confirmada')
            AND er.fecha_estado_reserva = (
                SELECT MAX(er2.fecha_estado_reserva)
                FROM estado_reserva er2
                WHERE er2.id_reserva = r.id_reserva
            )
            """, nativeQuery = true)
    List<ReservaEntity> findReservasVencidas();

    // Busca reservas que están ACTIVAS pero ya terminaron (no las que ya
    // están Completadas)
    @Query(value = """
            SELECT r.* FROM reserva r
            INNER JOIN estado_reserva er ON r.id_reserva = er.id_reserva
            INNER JOIN tipo_estado_reserva ter ON er.id_tipo_estado_reserva = ter.id_tipo_estado_reserva
            WHERE r.fecha_reserva <= TRUNC(SYSDATE)
            AND TO_TIMESTAMP(
                TO_CHAR(r.fecha_reserva, 'YYYY-MM-DD') || ' ' ||
                TO_CHAR(r.hora_fin, 'HH24:MI:SS'),
                'YYYY-MM-DD HH24:MI:SS'
            ) <= SYSTIMESTAMP
            AND LOWER(ter.descripcion_tipo_estado_reserva) = LOWER('Activa')
            AND er.fecha_estado_reserva = (
                SELECT MAX(er2.fecha_estado_reserva)
                FROM estado_reserva er2
                WHERE er2.id_reserva = r.id_reserva
            )
            """, nativeQuery = true)
    List<ReservaEntity> findReservasCompletadas();

    // Busca todas las reservas de un usuario por su RUT
    List<ReservaEntity> findByUsuarioRut(Integer rutUsuario);

    // Busca reservas que tienen conflicto de horarios con una nueva reserva
    @Query("""
            SELECT r FROM ReservaEntity r
            WHERE r.estacionamiento.id = :idEstacionamiento
            AND r.fechaReserva = :fecha
            AND r.idReserva != :idReservaExcluir
            AND (
                (r.horaInicio <= :horaInicio AND r.horaFin > :horaInicio) OR
                (r.horaInicio < :horaFin AND r.horaFin >= :horaFin) OR
                (r.horaInicio >= :horaInicio AND r.horaFin <= :horaFin)
            )
            AND EXISTS (
                SELECT er FROM EstadoReservaEntity er
                WHERE er.reserva = r
                AND er.fechaEstadoReserva = (
                    SELECT MAX(er2.fechaEstadoReserva)
                    FROM EstadoReservaEntity er2
                    WHERE er2.reserva = r
                )
                AND er.tipoEstadoReserva.descTipoEstadoReserva IN ('Confirmada', 'Activa')
            )
            """)
    List<ReservaEntity> findReservasConflicto(
            @Param("idEstacionamiento") Integer idEstacionamiento,
            @Param("fecha") LocalDate fecha,
            @Param("horaInicio") LocalTime horaInicio,
            @Param("horaFin") LocalTime horaFin,
            @Param("idReservaExcluir") Integer idReservaExcluir);

    // Busca reservas que están Canceladas
    @Query(value = """
            SELECT r.* FROM reserva r
            INNER JOIN estado_reserva er ON r.id_reserva = er.id_reserva
            INNER JOIN tipo_estado_reserva ter ON er.id_tipo_estado_reserva = ter.id_tipo_estado_reserva
            WHERE LOWER(ter.descripcion_tipo_estado_reserva) = LOWER('Cancelada')
            AND er.fecha_estado_reserva = (
                SELECT MAX(er2.fecha_estado_reserva)
                FROM estado_reserva er2
                WHERE er2.id_reserva = r.id_reserva
            )
            """, nativeQuery = true)
    List<ReservaEntity> findReservasCanceladas();

    // Busca la reserva activa actual en un estacionamiento específico
    @Query("""
            SELECT r
            FROM ReservaEntity r
            JOIN r.estadosReservas er
            JOIN er.tipoEstadoReserva ter
            WHERE r.estacionamiento.nroEstacionamiento = :nroEstacionamiento
              AND ter.descTipoEstadoReserva = 'Activa'
              AND er.fechaEstadoReserva = (
                    SELECT MAX(er2.fechaEstadoReserva)
                    FROM EstadoReservaEntity er2
                    WHERE er2.reserva.idReserva = r.idReserva
                )
              AND CURRENT_DATE = r.fechaReserva
              AND CURRENT_TIME BETWEEN r.horaInicio AND r.horaFin
            """)
    Optional<ReservaEntity> findReservaActivaPorEstacionamiento(@Param("nroEstacionamiento") int nroEstacionamiento);

    // Busca la hora de fin de la reserva activa en un estacionamiento específico
    // Un estacionamiento solo puede tener una reserva activa a la vez
    @Query(value = """
            SELECT TO_CHAR(R.HORA_FIN, 'HH24:MI') AS horaFin
            FROM ESTADO_RESERVA ER
            JOIN RESERVA R ON ER.ID_RESERVA = R.ID_RESERVA
            JOIN ESTACIONAMIENTO E ON R.ID_ESTACIONAMIENTO = E.ID_ESTACIONAMIENTO
            JOIN TIPO_ESTADO_RESERVA TER ON ER.ID_TIPO_ESTADO_RESERVA = TER.ID_TIPO_ESTADO_RESERVA
            WHERE ER.ID_ESTADO_RESERVA IN (
                SELECT MAX(ER2.ID_ESTADO_RESERVA)
                FROM ESTADO_RESERVA ER2
                GROUP BY ER2.ID_RESERVA
            )
            AND TER.DESCRIPCION_TIPO_ESTADO_RESERVA = 'Activa'
            AND E.NRO_ESTACIONAMIENTO = :nroEstacionamiento
            ORDER BY ER.ID_RESERVA
            """, nativeQuery = true)
    Optional<String> findHorasFinDeReservasActivasPorNroEstacionamiento(
            @Param("nroEstacionamiento") int nroEstacionamiento);

}