package cl.estacionamiento.usuario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.estacionamiento.usuario.dto.ReservaDTO;


@Repository
public interface ReservaRepository  extends JpaRepository<ReservaDTO, Integer> {

}
