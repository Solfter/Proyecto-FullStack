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

import cl.alcoholicos.gestorestacionamiento.entity.FeedbackEntity;
import cl.alcoholicos.gestorestacionamiento.service.impl.FeedbackService;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {
    @Autowired
    private FeedbackService feedbackService;

    @GetMapping
    public ResponseEntity<List<FeedbackEntity>> getAll() {
        List<FeedbackEntity> feedbacks = feedbackService.getAll();
        if (feedbacks.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(feedbacks);  
    }

    @PostMapping
    public ResponseEntity<FeedbackEntity> insert(@RequestBody FeedbackEntity feedback) {
        FeedbackEntity nuevoFeedback = feedbackService.insert(feedback);
        return ResponseEntity.ok(nuevoFeedback);
    }

    @PutMapping("/{idFeedback}")
    public ResponseEntity<FeedbackEntity> update(@PathVariable Integer idFeedback, @RequestBody FeedbackEntity feedback) {
        FeedbackEntity feedbackExistente = feedbackService.getById(idFeedback);
        if (feedbackExistente == null) {
            return ResponseEntity.notFound().build();
        }
        FeedbackEntity feedbackActuailizado = feedbackService.update(idFeedback, feedback);
        return ResponseEntity.ok(feedbackActuailizado);

    }

    @DeleteMapping("/{idFeedback}")
    public ResponseEntity<FeedbackEntity> delete (@PathVariable Integer idFeedback) {
        FeedbackEntity feedback = feedbackService.getById(idFeedback);
        if (feedback == null) {
            return ResponseEntity.notFound().build();
        }

        FeedbackEntity feedbackBorrado = feedbackService.delete(idFeedback);
        if (feedbackBorrado == null) {
            return ResponseEntity.ok(feedback);
        }

        return ResponseEntity.badRequest().build();
    }
}
