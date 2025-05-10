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

import cl.alcoholicos.gestorestacionamiento.entity.ReservaEntity;
import cl.alcoholicos.gestorestacionamiento.service.impl.ReservaService;

@RestController
@RequestMapping("/reserva")
public class ReservaController {
    
    @Autowired
    private ReservaService reservaService;

    @GetMapping
    public ResponseEntity<List<ReservaEntity>> getAll() {
        List<ReservaEntity> reservas = reservaService.getAll();
        if (reservas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(reservas);
    }

    @PostMapping
    public ResponseEntity<ReservaEntity> insert(@RequestBody ReservaEntity reserva) {
        ReservaEntity nuevaReserva = reservaService.insert(reserva);
        return ResponseEntity.ok(nuevaReserva);
    }

    @PutMapping("/{idReserva}")
    public ResponseEntity<ReservaEntity> update(@PathVariable Integer idReserva, @RequestBody ReservaEntity reserva) {
        ReservaEntity reservaExistente = reservaService.getById(idReserva);
        if (reservaExistente == null) {
            return ResponseEntity.notFound().build();
        }
        ReservaEntity reservaActualizada = reservaService.update(idReserva, reserva);
        return ResponseEntity.ok(reservaActualizada);
    }

    @DeleteMapping("/{idReserva}")
    public ResponseEntity<ReservaEntity> delete(@PathVariable Integer idReserva) {

        ReservaEntity reserva = reservaService.getById(idReserva);
        if (reserva == null) {
            return ResponseEntity.notFound().build();
        }

        ReservaEntity reservaBorrada = reservaService.delete(idReserva);
        if (reservaBorrada == null) {
            return ResponseEntity.ok(reserva);
        }

        return ResponseEntity.badRequest().build();
    }
}
