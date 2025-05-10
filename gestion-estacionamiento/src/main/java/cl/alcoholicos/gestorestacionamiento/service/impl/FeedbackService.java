package cl.alcoholicos.gestorestacionamiento.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.alcoholicos.gestorestacionamiento.entity.FeedbackEntity;
import cl.alcoholicos.gestorestacionamiento.repository.FeedbackRepository;
import cl.alcoholicos.gestorestacionamiento.service.IFeedback;

@Service
public class FeedbackService implements IFeedback {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Override
    public FeedbackEntity insert(FeedbackEntity feedback) {
        return feedbackRepository.save(feedback);
    }

    @Override
    public FeedbackEntity update(Integer idFeedback, FeedbackEntity feedback) {
        feedback.setIdFeedback(idFeedback);
        return feedbackRepository.save(feedback);
    }

    @Override
    public FeedbackEntity delete(Integer idFeedback) {
        feedbackRepository.deleteById(idFeedback);
        return null;
    }

    @Override
    public FeedbackEntity getById(Integer idFeedback) {
        return feedbackRepository.findById(idFeedback).get();
    }

    @Override
    public List<FeedbackEntity> getAll() {
        return (List<FeedbackEntity>) feedbackRepository.findAll();
    }

}
