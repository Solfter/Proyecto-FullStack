package cl.alcoholicos.gestorestacionamiento.reserva.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.alcoholicos.gestorestacionamiento.reserva.dto.ReservaDTO;


@Repository
public interface ReservaRepository  extends JpaRepository<ReservaDTO, Integer> {

}
