package cl.alcoholicos.gestorestacionamiento.reporte.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cl.alcoholicos.gestorestacionamiento.reporte.dto.ReporteDTO;
import cl.alcoholicos.gestorestacionamiento.reporte.repository.ReporteRepository;
import cl.alcoholicos.gestorestacionamiento.reporte.service.IReporte;

public class ReporteService implements IReporte {

    @Autowired
    private ReporteRepository reporteRepository;

    @Override
    public ReporteDTO insert(ReporteDTO reporte) {
        return reporteRepository.save(reporte);
    }

    @Override
    public ReporteDTO update(Integer idReporte, ReporteDTO reporte) {
        reporte.setIdReporte(idReporte);
        return reporteRepository.save(reporte);
    }

    @Override
    public ReporteDTO delete(Integer idReporte) {
        reporteRepository.deleteById(idReporte);
        return null;
    }

    @Override
    public ReporteDTO getById(Integer idReporte) {
        return reporteRepository.findById(idReporte).get();
    }

    @Override
    public List<ReporteDTO> getAll() {
        return (List<ReporteDTO>) reporteRepository.findAll();
    }

}
