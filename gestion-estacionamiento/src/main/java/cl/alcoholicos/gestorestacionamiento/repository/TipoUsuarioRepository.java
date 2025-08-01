package cl.alcoholicos.gestorestacionamiento.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.alcoholicos.gestorestacionamiento.entity.TipoUsuarioEntity;

@Repository
public interface TipoUsuarioRepository  extends JpaRepository<TipoUsuarioEntity, Integer> {

    Optional<TipoUsuarioEntity> findByDescTipoUsuario (String descTipoUsuario);
}
