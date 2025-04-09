package cl.estacionamiento.usuario.service;

import java.util.List;

import cl.estacionamiento.usuario.dto.ReporteDTO;

public interface IReporte {
           ReporteDTO insert(ReporteDTO reporte);

    ReporteDTO update(Integer idReporte, ReporteDTO reporte);

    ReporteDTO delete(Integer idReporte);

    ReporteDTO getById(Integer idReporte);

    List<ReporteDTO> getAll();

}
