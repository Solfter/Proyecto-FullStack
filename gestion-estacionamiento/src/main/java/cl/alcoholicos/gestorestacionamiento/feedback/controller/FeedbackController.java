package cl.alcoholicos.gestorestacionamiento.feedback.controller;

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

import cl.alcoholicos.gestorestacionamiento.feedback.dto.FeedbackDTO;
import cl.alcoholicos.gestorestacionamiento.feedback.service.impl.FeedbackService;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {
    @Autowired
    private FeedbackService feedbackService;

    @GetMapping
    public ResponseEntity<List<FeedbackDTO>> getAll() {
        List<FeedbackDTO> feedbacks = feedbackService.getAll();
        if (feedbacks.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(feedbacks);  
    }

    @PostMapping
    public ResponseEntity<FeedbackDTO> insert(@RequestBody FeedbackDTO feedback) {
        FeedbackDTO nuevoFeedback = feedbackService.insert(feedback);
        return ResponseEntity.ok(nuevoFeedback);
    }

    @PutMapping("/{idFeedback}")
    public ResponseEntity<FeedbackDTO> update(@PathVariable Integer idFeedback, @RequestBody FeedbackDTO feedback) {
        FeedbackDTO feedbackExistente = feedbackService.getById(idFeedback);
        if (feedbackExistente == null) {
            return ResponseEntity.notFound().build();
        }
        FeedbackDTO feedbackActuailizado = feedbackService.update(idFeedback, feedback);
        return ResponseEntity.ok(feedbackActuailizado);

    }

    @DeleteMapping("/{idFeedback}")
    public ResponseEntity<FeedbackDTO> delete (@PathVariable Integer idFeedback) {
        FeedbackDTO feedback = feedbackService.getById(idFeedback);
        if (feedback == null) {
            return ResponseEntity.notFound().build();
        }

        FeedbackDTO feedbackBorrado = feedbackService.delete(idFeedback);
        if (feedbackBorrado == null) {
            return ResponseEntity.ok(feedback);
        }

        return ResponseEntity.badRequest().build();
    }
}
