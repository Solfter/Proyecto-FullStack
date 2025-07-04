package cl.alcoholicos.gestorestacionamiento.service;

import java.util.List;

import cl.alcoholicos.gestorestacionamiento.dto.FeedbackCreateDTO;
import cl.alcoholicos.gestorestacionamiento.dto.FeedbackResponseDTO;
import cl.alcoholicos.gestorestacionamiento.entity.FeedbackEntity;

public interface IFeedback {
    
    FeedbackResponseDTO insert(FeedbackCreateDTO feedback, Integer rutUsuario);

    FeedbackEntity update(Integer idFeedback, FeedbackEntity feedback);

    boolean delete(Integer idFeedback);

    FeedbackEntity getById(Integer idFeedback);

    List<FeedbackEntity> getAll();
    
    

}
