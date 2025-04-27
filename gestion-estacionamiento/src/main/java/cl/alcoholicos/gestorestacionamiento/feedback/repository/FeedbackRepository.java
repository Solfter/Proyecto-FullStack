package cl.alcoholicos.gestorestacionamiento.feedback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.alcoholicos.gestorestacionamiento.feedback.entity.FeedbackEntity;

@Repository
public interface FeedbackRepository  extends JpaRepository<FeedbackEntity, Integer> {

}
