package cl.alcoholicos.gestorestacionamiento.modelo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.alcoholicos.gestorestacionamiento.modelo.dto.ModeloDTO;

@Repository
public interface ModeloRepository  extends JpaRepository<ModeloDTO, Integer> {

}
