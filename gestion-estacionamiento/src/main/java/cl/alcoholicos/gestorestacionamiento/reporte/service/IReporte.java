package cl.alcoholicos.gestorestacionamiento.reporte.service;

import java.util.List;

import cl.alcoholicos.gestorestacionamiento.reporte.dto.ReporteDTO;

public interface IReporte {
    
    ReporteDTO insert(ReporteDTO reporte);

    ReporteDTO update(Integer idReporte, ReporteDTO reporte);

    ReporteDTO delete(Integer idReporte);

    ReporteDTO getById(Integer idReporte);

    List<ReporteDTO> getAll();

}
