package cl.alcoholicos.gestorestacionamiento.marca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.alcoholicos.gestorestacionamiento.marca.dto.MarcaDTO;

@Repository
public interface MarcaRepository  extends JpaRepository<MarcaDTO, Integer> {

}
