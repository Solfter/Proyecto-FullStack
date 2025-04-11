package cl.alcoholicos.gestorestacionamiento.feedback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.alcoholicos.gestorestacionamiento.feedback.dto.FeedbackDTO;

@Repository
public interface FeedbackRepository  extends JpaRepository<FeedbackDTO, Integer> {

}
