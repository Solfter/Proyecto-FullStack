package cl.estacionamiento.usuario.service;

import java.util.List;

import cl.estacionamiento.usuario.dto.FeedbackDTO;

public interface IFeedback {
        FeedbackDTO insert(FeedbackDTO feedback);

    FeedbackDTO update(Integer idFeedback, FeedbackDTO feedback);

    FeedbackDTO delete(Integer idFeedback);

    FeedbackDTO getById(Integer idFeedback);

    List<FeedbackDTO> getAll();
    
    

}
