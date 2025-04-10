package cl.alcoholicos.gestorestacionamiento.estadoestacionamiento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.alcoholicos.gestorestacionamiento.estacionamiento.dto.EstacionamientoDTO;

@Repository
public interface EstadoEstacionamientoRepository extends JpaRepository<EstacionamientoDTO, Integer> {

}
