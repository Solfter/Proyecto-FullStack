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
            """, nativeQuery = true)
    void liberarEstacionamiento(@Param("nroEstacionamiento") Integer nroEstacionamiento);

    Optional<EstacionamientoEntity> findByNroEstacionamiento(Integer nroEstacionamiento);

    @Query("SELECT e FROM EstacionamientoEntity e WHERE LOWER(e.estadoEstacionamiento.descEstadoEstacionamiento) = LOWER(:estado)")
    List<EstacionamientoEntity> findByEstadoEstacionamientoDescEstadoEstacionamiento(@Param("estado") String estado);

    

}
