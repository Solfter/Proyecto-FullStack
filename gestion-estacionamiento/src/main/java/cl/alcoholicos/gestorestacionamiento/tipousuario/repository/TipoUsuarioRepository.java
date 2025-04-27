package cl.alcoholicos.gestorestacionamiento.tipousuario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.alcoholicos.gestorestacionamiento.tipousuario.entity.TipoUsuarioEntity;

@Repository
public interface TipoUsuarioRepository  extends JpaRepository<TipoUsuarioEntity, Integer> {

}
