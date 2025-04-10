package cl.alcoholicos.gestorestacionamiento.sensor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.alcoholicos.gestorestacionamiento.sensor.dto.SensorDTO;

@Repository
public interface SensorRepository  extends JpaRepository<SensorDTO, Integer> {

}
