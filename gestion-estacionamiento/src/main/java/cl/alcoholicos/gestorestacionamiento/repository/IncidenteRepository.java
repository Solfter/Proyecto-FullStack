package cl.alcoholicos.gestorestacionamiento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.alcoholicos.gestorestacionamiento.entity.IncidenteEntity;

@Repository
public interface IncidenteRepository  extends JpaRepository<IncidenteEntity, Integer> {

}
