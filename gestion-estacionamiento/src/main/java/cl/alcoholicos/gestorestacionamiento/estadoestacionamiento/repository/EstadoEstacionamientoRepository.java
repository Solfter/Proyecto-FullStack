package cl.alcoholicos.gestorestacionamiento.estadoestacionamiento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.alcoholicos.gestorestacionamiento.estadoestacionamiento.dto.EstadoEstacionamientoDTO;

@Repository
public interface EstadoEstacionamientoRepository extends JpaRepository<EstadoEstacionamientoDTO, Integer> {

}
