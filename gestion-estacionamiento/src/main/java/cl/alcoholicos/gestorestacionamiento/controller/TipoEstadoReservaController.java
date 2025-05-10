package cl.alcoholicos.gestorestacionamiento.controller;

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

import cl.alcoholicos.gestorestacionamiento.entity.TipoEstadoReservaEntity;
import cl.alcoholicos.gestorestacionamiento.service.impl.TipoEstadoReservaService;

@RestController
@RequestMapping("/tipoestadoreserva")
public class TipoEstadoReservaController {
    
    @Autowired
    private TipoEstadoReservaService tipoEstadoReservaService;

    @GetMapping
    public ResponseEntity<List<TipoEstadoReservaEntity>> getAll() {
        List<TipoEstadoReservaEntity> tipoEstadoReservas = tipoEstadoReservaService.getAll();
        if (tipoEstadoReservas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(tipoEstadoReservas);
    }

    @PostMapping
    public ResponseEntity<TipoEstadoReservaEntity> insert(@RequestBody TipoEstadoReservaEntity tipoEstadoReserva) {
        TipoEstadoReservaEntity nuevoTipoEstadoReserva = tipoEstadoReservaService.insert(tipoEstadoReserva);
        return ResponseEntity.ok(nuevoTipoEstadoReserva);
    }

    @PutMapping("/{idTipoEstadoReserva}")
    public ResponseEntity<TipoEstadoReservaEntity> update(@PathVariable Integer idTipoEstadoReserva, @RequestBody TipoEstadoReservaEntity tipoEstadoReserva) {
        TipoEstadoReservaEntity tipoEstadoReservaExistente = tipoEstadoReservaService.getById(idTipoEstadoReserva);
        if (tipoEstadoReservaExistente == null) {
            return ResponseEntity.notFound().build();
        }
        TipoEstadoReservaEntity tipoEstadoReservaActualizado = tipoEstadoReservaService.update(idTipoEstadoReserva, tipoEstadoReserva);
        return ResponseEntity.ok(tipoEstadoReservaActualizado);
    }

    @DeleteMapping("/{idTipoEstadoReserva}")
    public ResponseEntity<TipoEstadoReservaEntity> delete(@PathVariable Integer idTipoEstadoReserva) {
        TipoEstadoReservaEntity tipoEstadoReserva = tipoEstadoReservaService.getById(idTipoEstadoReserva);
        if (tipoEstadoReserva == null) {
            return ResponseEntity.notFound().build();
        }
        TipoEstadoReservaEntity tipoEstadoReservaBorrado = tipoEstadoReservaService.delete(idTipoEstadoReserva);
        if (tipoEstadoReservaBorrado == null) {
            return ResponseEntity.ok(tipoEstadoReserva);
        }
        return ResponseEntity.badRequest().build();
    }
}
