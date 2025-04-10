package cl.alcoholicos.gestorestacionamiento.estadoincidente.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.alcoholicos.gestorestacionamiento.estadoincidente.dto.EstadoIncidenteDTO;

@Repository
public interface EstadoIncidenteRepository  extends JpaRepository<EstadoIncidenteDTO, Integer> {

}
