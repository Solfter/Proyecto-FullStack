package cl.alcoholicos.gestorestacionamiento.usuario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.alcoholicos.gestorestacionamiento.usuario.dto.UsuarioDTO;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioDTO, Integer> {
    
}
