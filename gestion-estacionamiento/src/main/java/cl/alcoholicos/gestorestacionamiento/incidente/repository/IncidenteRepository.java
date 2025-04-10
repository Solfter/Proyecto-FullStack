package cl.alcoholicos.gestorestacionamiento.incidente.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.alcoholicos.gestorestacionamiento.incidente.dto.IncidenteDTO;

@Repository
public interface IncidenteRepository  extends JpaRepository<IncidenteDTO, Integer> {

}
