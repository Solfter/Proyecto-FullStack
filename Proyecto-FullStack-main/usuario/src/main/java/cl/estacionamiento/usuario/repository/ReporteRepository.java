package cl.estacionamiento.usuario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.estacionamiento.usuario.dto.ReporteDTO;

@Repository
public interface ReporteRepository  extends JpaRepository<ReporteDTO, Integer> {

}
