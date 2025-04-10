package cl.alcoholicos.gestorestacionamiento.tipoestadoreserva.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.alcoholicos.gestorestacionamiento.tipoestadoreserva.dto.TipoEstadoReservaDTO;

@Repository
public interface TipoEstadoReservaRepository  extends JpaRepository<TipoEstadoReservaDTO, Integer> {

}
