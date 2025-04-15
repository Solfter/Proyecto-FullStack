package cl.alcoholicos.gestorestacionamiento.reserva.controller;

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

import cl.alcoholicos.gestorestacionamiento.reserva.dto.ReservaDTO;
import cl.alcoholicos.gestorestacionamiento.reserva.service.impl.ReservaService;

@RestController
@RequestMapping("/reserva")
public class ReservaController {
    
    @Autowired
    private ReservaService reservaService;

    @GetMapping
    public ResponseEntity<List<ReservaDTO>> getAll() {
        List<ReservaDTO> reservas = reservaService.getAll();
        if (reservas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(reservas);
    }

    @PostMapping
    public ResponseEntity<ReservaDTO> insert(@RequestBody ReservaDTO reserva) {
        ReservaDTO nuevaReserva = reservaService.insert(reserva);
        return ResponseEntity.ok(nuevaReserva);
    }

    @PutMapping("/{idReserva}")
    public ResponseEntity<ReservaDTO> update(@PathVariable Integer idReserva, @RequestBody ReservaDTO reserva) {
        ReservaDTO reservaExistente = reservaService.getById(idReserva);
        if (reservaExistente == null) {
            return ResponseEntity.notFound().build();
        }
        ReservaDTO reservaActualizada = reservaService.update(idReserva, reserva);
        return ResponseEntity.ok(reservaActualizada);
    }

    @DeleteMapping("/{idReserva}")
    public ResponseEntity<ReservaDTO> delete(@PathVariable Integer idReserva) {

        ReservaDTO reserva = reservaService.getById(idReserva);
        if (reserva == null) {
            return ResponseEntity.notFound().build();
        }

        ReservaDTO reservaBorrada = reservaService.delete(idReserva);
        if (reservaBorrada == null) {
            return ResponseEntity.ok(reserva);
        }

        return ResponseEntity.badRequest().build();
    }
}
