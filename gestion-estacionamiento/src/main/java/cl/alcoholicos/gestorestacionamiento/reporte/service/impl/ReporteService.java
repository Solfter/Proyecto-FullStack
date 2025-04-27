package cl.alcoholicos.gestorestacionamiento.reporte.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.alcoholicos.gestorestacionamiento.reporte.entity.ReporteEntity;
import cl.alcoholicos.gestorestacionamiento.reporte.repository.ReporteRepository;
import cl.alcoholicos.gestorestacionamiento.reporte.service.IReporte;

@Service
public class ReporteService implements IReporte {

    @Autowired
    private ReporteRepository reporteRepository;

    @Override
    public ReporteEntity insert(ReporteEntity reporte) {
        return reporteRepository.save(reporte);
    }

    @Override
    public ReporteEntity update(Integer idReporte, ReporteEntity reporte) {
        reporte.setIdReporte(idReporte);
        return reporteRepository.save(reporte);
    }

    @Override
    public ReporteEntity delete(Integer idReporte) {
        reporteRepository.deleteById(idReporte);
        return null;
    }

    @Override
    public ReporteEntity getById(Integer idReporte) {
        return reporteRepository.findById(idReporte).get();
    }

    @Override
    public List<ReporteEntity> getAll() {
        return (List<ReporteEntity>) reporteRepository.findAll();
    }

}
