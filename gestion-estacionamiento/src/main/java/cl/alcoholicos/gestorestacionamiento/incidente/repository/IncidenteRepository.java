package cl.alcoholicos.gestorestacionamiento.incidente.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.alcoholicos.gestorestacionamiento.incidente.entity.IncidenteEntity;

@Repository
public interface IncidenteRepository  extends JpaRepository<IncidenteEntity, Integer> {

}
