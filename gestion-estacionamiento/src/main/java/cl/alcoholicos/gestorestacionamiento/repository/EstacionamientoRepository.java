package cl.alcoholicos.gestorestacionamiento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cl.alcoholicos.gestorestacionamiento.entity.EstacionamientoEntity;

@Repository
public interface EstacionamientoRepository extends JpaRepository<EstacionamientoEntity, Integer> {
    
    @Modifying
    @Query(value = "UPDATE estacionamiento SET id_est_estacionamiento = 1 WHERE id_estacionamiento = :idEstacionamiento", 
           nativeQuery = true)
    void liberarEstacionamiento(@Param("idEstacionamiento") Integer idEstacionamiento);
}
