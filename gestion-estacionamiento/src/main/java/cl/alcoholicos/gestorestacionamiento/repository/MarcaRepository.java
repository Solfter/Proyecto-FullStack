package cl.alcoholicos.gestorestacionamiento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.alcoholicos.gestorestacionamiento.entity.MarcaEntity;

@Repository
public interface MarcaRepository  extends JpaRepository<MarcaEntity, Integer> {

}
