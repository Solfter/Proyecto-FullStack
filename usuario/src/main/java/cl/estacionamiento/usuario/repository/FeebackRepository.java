package cl.estacionamiento.usuario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.estacionamiento.usuario.dto.FeedbackDTO;

@Repository
public interface FeebackRepository  extends JpaRepository<FeedbackDTO, Integer> {

}
