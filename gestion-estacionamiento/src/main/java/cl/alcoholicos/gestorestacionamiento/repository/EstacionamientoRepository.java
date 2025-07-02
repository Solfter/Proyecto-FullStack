package cl.alcoholicos.gestorestacionamiento.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cl.alcoholicos.gestorestacionamiento.entity.EstacionamientoEntity;
import jakarta.transaction.Transactional;

@Repository
public interface EstacionamientoRepository extends JpaRepository<EstacionamientoEntity, Integer> {

    @Modifying
    @Transactional
    @Query(value = """
            UPDATE estacionamiento
            SET id_est_estacionamiento = (
                SELECT id_est_estacionamiento
                FROM estado_estacionamiento
                WHERE desc_estado_estacionamiento = 'Disponible'
            )
            WHERE nro_estacionamiento = :nroEstacionamiento
            AND id_est_estacionamiento != (
                SELECT id_est_estacionamiento
                FROM estado_estacionamiento
                WHERE desc_estado_estacionamiento = 'Disponible'
            )
            """, nativeQuery = true)
    int liberarEstacionamiento(@Param("nroEstacionamiento") Integer nroEstacionamiento);

    Optional<EstacionamientoEntity> findByNroEstacionamiento(Integer nroEstacionamiento);

    @Query("SELECT e FROM EstacionamientoEntity e WHERE LOWER(e.estadoEstacionamiento.descEstadoEstacionamiento) = LOWER(:estado)")
    List<EstacionamientoEntity> findByEstadoEstacionamientoDescEstadoEstacionamiento(@Param("estado") String estado);

    // Método para verificar si un estacionamiento está disponible
    @Query(value = """
            SELECT COUNT(*) > 0
            FROM estacionamiento e
            JOIN estado_estacionamiento ee ON e.id_est_estacionamiento = ee.id_est_estacionamiento
            WHERE e.nro_estacionamiento = :nroEstacionamiento
            AND LOWER(ee.desc_estado_estacionamiento) = LOWER('Disponible')
            """, nativeQuery = true)
    boolean isEstacionamientoDisponible(@Param("nroEstacionamiento") Integer nroEstacionamiento);

}
