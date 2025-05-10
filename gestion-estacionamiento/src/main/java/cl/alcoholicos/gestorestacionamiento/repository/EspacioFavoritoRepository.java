package cl.alcoholicos.gestorestacionamiento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.alcoholicos.gestorestacionamiento.entity.EspacioFavoritoEntity;

@Repository
public interface EspacioFavoritoRepository extends JpaRepository<EspacioFavoritoEntity, Integer> {

}
