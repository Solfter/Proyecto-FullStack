package cl.estacionamiento.usuario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.estacionamiento.usuario.dto.IncidenteDTO;

@Repository
public interface IncidenteRepository  extends JpaRepository<IncidenteDTO, Integer> {

}
