package cl.alcoholicos.gestorestacionamiento.feedback.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.alcoholicos.gestorestacionamiento.feedback.repository.FeedbackRepository;
import cl.alcoholicos.gestorestacionamiento.feedback.dto.FeedbackDTO;
import cl.alcoholicos.gestorestacionamiento.feedback.service.IFeedback;

@Service
public class FeedbackService implements IFeedback {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Override
    public FeedbackDTO insert(FeedbackDTO feedback) {
        return feedbackRepository.save(feedback);
    }

    @Override
    public FeedbackDTO update(Integer idFeedback, FeedbackDTO feedback) {
        feedback.setIdFeedback(idFeedback);
        return feedbackRepository.save(feedback);
    }

    @Override
    public FeedbackDTO delete(Integer idFeedback) {
        feedbackRepository.deleteById(idFeedback);
        return null;
    }

    @Override
    public FeedbackDTO getById(Integer idFeedback) {
        return feedbackRepository.findById(idFeedback).get();
    }

    @Override
    public List<FeedbackDTO> getAll() {
        return (List<FeedbackDTO>) feedbackRepository.findAll();
    }

}
