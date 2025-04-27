package cl.alcoholicos.gestorestacionamiento.reporte.service;

import java.util.List;

import cl.alcoholicos.gestorestacionamiento.reporte.entity.ReporteEntity;

public interface IReporte {
    
    ReporteEntity insert(ReporteEntity reporte);

    ReporteEntity update(Integer idReporte, ReporteEntity reporte);

    ReporteEntity delete(Integer idReporte);

    ReporteEntity getById(Integer idReporte);

    List<ReporteEntity> getAll();

}
