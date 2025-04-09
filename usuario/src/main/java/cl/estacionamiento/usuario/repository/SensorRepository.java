package cl.estacionamiento.usuario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.estacionamiento.usuario.dto.SensorDTO;

@Repository
public interface SensorRepository  extends JpaRepository<SensorDTO, Integer> {

}
