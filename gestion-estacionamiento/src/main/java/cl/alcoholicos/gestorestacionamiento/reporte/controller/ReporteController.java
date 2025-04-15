package cl.alcoholicos.gestorestacionamiento.reporte.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.alcoholicos.gestorestacionamiento.reporte.dto.ReporteDTO;
import cl.alcoholicos.gestorestacionamiento.reporte.service.impl.ReporteService;

@RestController
@RequestMapping("/reporte")
public class ReporteController {
    
    @Autowired
    private ReporteService reporteService;

    @GetMapping
    public ResponseEntity<List<ReporteDTO>> getAll() {
        List<ReporteDTO> reportes = reporteService.getAll();
        if (reportes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(reportes);  
    }

    @PostMapping
    public ResponseEntity<ReporteDTO> insert(@RequestBody ReporteDTO reporte) {
        ReporteDTO nuevoReporte = reporteService.insert(reporte);
        return ResponseEntity.ok(nuevoReporte);
    }

    @PutMapping("/{idReporte}")
    public ResponseEntity<ReporteDTO> update(@PathVariable Integer idReporte, @RequestBody ReporteDTO reporte) {
        ReporteDTO reporteExistente = reporteService.getById(idReporte);
        if (reporteExistente == null) {
            return ResponseEntity.notFound().build();
        }
        ReporteDTO reporteActualizado = reporteService.update(idReporte, reporte);
        return ResponseEntity.ok(reporteActualizado);

    }

    @DeleteMapping("/{idReporte}")
    public ResponseEntity<ReporteDTO> delete (@PathVariable Integer idReporte) {
        ReporteDTO reporte = reporteService.getById(idReporte);
        if (reporte == null) {
            return ResponseEntity.notFound().build();
        }

        ReporteDTO reporteBorrado = reporteService.delete(idReporte);
        if (reporteBorrado == null) {
            return ResponseEntity.ok(reporte);
        }

        return ResponseEntity.badRequest().build();
    }
}
