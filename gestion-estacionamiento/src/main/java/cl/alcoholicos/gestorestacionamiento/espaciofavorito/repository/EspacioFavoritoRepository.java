package cl.alcoholicos.gestorestacionamiento.espaciofavorito.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.alcoholicos.gestorestacionamiento.espaciofavorito.dto.EspacioFavoritoDTO;
import cl.alcoholicos.gestorestacionamiento.espaciofavorito.dto.EspacioFavoritoId;

@Repository
public interface EspacioFavoritoRepository extends JpaRepository<EspacioFavoritoDTO, EspacioFavoritoId> {

}
