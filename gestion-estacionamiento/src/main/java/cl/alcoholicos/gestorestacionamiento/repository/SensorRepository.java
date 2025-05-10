package cl.alcoholicos.gestorestacionamiento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.alcoholicos.gestorestacionamiento.entity.SensorEntity;

@Repository
public interface SensorRepository  extends JpaRepository<SensorEntity, Integer> {

}
