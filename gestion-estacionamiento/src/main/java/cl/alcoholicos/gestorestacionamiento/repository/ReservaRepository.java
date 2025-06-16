package cl.alcoholicos.gestorestacionamiento.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cl.alcoholicos.gestorestacionamiento.entity.ReservaEntity;


@Repository
public interface ReservaRepository extends JpaRepository<ReservaEntity, Integer> {

    @Query(value = """
        SELECT r.* FROM reserva r 
        INNER JOIN estado_reserva er ON r.id_reserva = er.id_reserva
        WHERE r.fecha_reserva <= TRUNC(SYSDATE)
        AND TO_TIMESTAMP(
            TO_CHAR(r.fecha_reserva, 'YYYY-MM-DD') || ' ' || 
            TO_CHAR(r.hora_fin, 'HH24:MI:SS'), 
            'YYYY-MM-DD HH24:MI:SS'
        ) <= SYSTIMESTAMP
        AND er.id_tipo_estado_reserva = 2
        AND er.fecha_estado_reserva = (
            SELECT MAX(er2.fecha_estado_reserva) 
            FROM estado_reserva er2 
            WHERE er2.id_reserva = r.id_reserva
        )
        """, 
        nativeQuery = true)
    List<ReservaEntity> findReservasVencidas();

    @Query(value = """
        SELECT r.* FROM reserva r 
        INNER JOIN estado_reserva er ON r.id_reserva = er.id_reserva
        WHERE r.fecha_reserva <= TRUNC(SYSDATE)
        AND TO_TIMESTAMP(
            TO_CHAR(r.fecha_reserva, 'YYYY-MM-DD') || ' ' || 
            TO_CHAR(r.hora_fin, 'HH24:MI:SS'), 
            'YYYY-MM-DD HH24:MI:SS'
        ) <= SYSTIMESTAMP
        AND er.id_tipo_estado_reserva = 1
        AND er.fecha_estado_reserva = (
            SELECT MAX(er2.fecha_estado_reserva) 
            FROM estado_reserva er2 
            WHERE er2.id_reserva = r.id_reserva
        )
        """, 
        nativeQuery = true)
    List<ReservaEntity> findReservasCompletadas();
}
