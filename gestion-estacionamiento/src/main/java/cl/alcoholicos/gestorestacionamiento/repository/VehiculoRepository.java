package cl.alcoholicos.gestorestacionamiento.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.alcoholicos.gestorestacionamiento.entity.VehiculoEntity;

@Repository
public interface VehiculoRepository  extends JpaRepository<VehiculoEntity, String> {

    List<VehiculoEntity> findByUsuarioRut (Integer rut);
    
}
