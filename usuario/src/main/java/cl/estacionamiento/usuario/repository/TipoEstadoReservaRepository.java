package cl.estacionamiento.usuario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.estacionamiento.usuario.dto.TipoEstadoReservaDTO;

@Repository
public interface TipoEstadoReservaRepository  extends JpaRepository<TipoEstadoReservaDTO, Integer> {

}
