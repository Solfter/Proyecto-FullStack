package cl.estacionamiento.usuario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.estacionamiento.usuario.dto.TipoUsuarioDTO;

@Repository
public interface TipoUsuarioRepository  extends JpaRepository<TipoUsuarioDTO, Integer> {

}
