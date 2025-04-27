package cl.alcoholicos.gestorestacionamiento.marca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.alcoholicos.gestorestacionamiento.marca.entity.MarcaEntity;

@Repository
public interface MarcaRepository  extends JpaRepository<MarcaEntity, Integer> {

}
