package cl.alcoholicos.gestorestacionamiento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.alcoholicos.gestorestacionamiento.entity.TipoUsuarioEntity;

@Repository
public interface TipoUsuarioRepository  extends JpaRepository<TipoUsuarioEntity, Integer> {

}
