package cl.alcoholicos.gestorestacionamiento.tipoestadoreserva.controller;

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

import cl.alcoholicos.gestorestacionamiento.tipoestadoreserva.dto.TipoEstadoReservaDTO;
import cl.alcoholicos.gestorestacionamiento.tipoestadoreserva.service.impl.TipoEstadoReservaService;

@RestController
@RequestMapping("/tipoestadoreserva")
public class TipoEstadoReservaController {
    
    @Autowired
    private TipoEstadoReservaService tipoEstadoReservaService;

    @GetMapping
    public ResponseEntity<List<TipoEstadoReservaDTO>> getAll() {
        List<TipoEstadoReservaDTO> tipoEstadoReservas = tipoEstadoReservaService.getAll();
        if (tipoEstadoReservas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(tipoEstadoReservas);
    }

    @PostMapping
    public ResponseEntity<TipoEstadoReservaDTO> insert(@RequestBody TipoEstadoReservaDTO tipoEstadoReserva) {
        TipoEstadoReservaDTO nuevoTipoEstadoReserva = tipoEstadoReservaService.insert(tipoEstadoReserva);
        return ResponseEntity.ok(nuevoTipoEstadoReserva);
    }

    @PutMapping("/{idTipoEstadoReserva}")
    public ResponseEntity<TipoEstadoReservaDTO> update(@PathVariable Integer idTipoEstadoReserva, @RequestBody TipoEstadoReservaDTO tipoEstadoReserva) {
        TipoEstadoReservaDTO tipoEstadoReservaExistente = tipoEstadoReservaService.getById(idTipoEstadoReserva);
        if (tipoEstadoReservaExistente == null) {
            return ResponseEntity.notFound().build();
        }
        TipoEstadoReservaDTO tipoEstadoReservaActualizado = tipoEstadoReservaService.update(idTipoEstadoReserva, tipoEstadoReserva);
        return ResponseEntity.ok(tipoEstadoReservaActualizado);
    }

    @DeleteMapping("/{idTipoEstadoReserva}")
    public ResponseEntity<TipoEstadoReservaDTO> delete(@PathVariable Integer idTipoEstadoReserva) {
        TipoEstadoReservaDTO tipoEstadoReserva = tipoEstadoReservaService.getById(idTipoEstadoReserva);
        if (tipoEstadoReserva == null) {
            return ResponseEntity.notFound().build();
        }
        TipoEstadoReservaDTO tipoEstadoReservaBorrado = tipoEstadoReservaService.delete(idTipoEstadoReserva);
        if (tipoEstadoReservaBorrado == null) {
            return ResponseEntity.ok(tipoEstadoReserva);
        }
        return ResponseEntity.badRequest().build();
    }
}
