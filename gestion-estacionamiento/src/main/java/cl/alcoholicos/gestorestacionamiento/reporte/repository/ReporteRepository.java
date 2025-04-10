package cl.alcoholicos.gestorestacionamiento.reporte.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.alcoholicos.gestorestacionamiento.reporte.dto.ReporteDTO;

@Repository
public interface ReporteRepository  extends JpaRepository<ReporteDTO, Integer> {

}
