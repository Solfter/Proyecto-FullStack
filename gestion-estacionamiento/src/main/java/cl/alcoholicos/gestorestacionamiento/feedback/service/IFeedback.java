package cl.alcoholicos.gestorestacionamiento.feedback.service;

import java.util.List;

import cl.alcoholicos.gestorestacionamiento.feedback.entity.FeedbackEntity;

public interface IFeedback {
    
    FeedbackEntity insert(FeedbackEntity feedback);

    FeedbackEntity update(Integer idFeedback, FeedbackEntity feedback);

    FeedbackEntity delete(Integer idFeedback);

    FeedbackEntity getById(Integer idFeedback);

    List<FeedbackEntity> getAll();
    
    

}
