package cl.alcoholicos.gestorestacionamiento.vehiculo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.alcoholicos.gestorestacionamiento.vehiculo.dto.VehiculoDTO;

@Repository
public interface VehiculoRepository  extends JpaRepository<VehiculoDTO, Integer> {

}
