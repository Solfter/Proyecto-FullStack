package cl.alcoholicos.gestorestacionamiento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.alcoholicos.gestorestacionamiento.entity.ModeloEntity;

@Repository
public interface ModeloRepository  extends JpaRepository<ModeloEntity, Integer> {

}
