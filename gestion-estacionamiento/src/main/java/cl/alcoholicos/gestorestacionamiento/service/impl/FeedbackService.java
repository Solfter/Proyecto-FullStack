package cl.alcoholicos.gestorestacionamiento.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.alcoholicos.gestorestacionamiento.dto.FeedbackCreateDTO;
import cl.alcoholicos.gestorestacionamiento.dto.FeedbackResponseDTO;
import cl.alcoholicos.gestorestacionamiento.entity.FeedbackEntity;
import cl.alcoholicos.gestorestacionamiento.entity.UsuarioEntity;
import cl.alcoholicos.gestorestacionamiento.mapper.FeedbackMapper;
import cl.alcoholicos.gestorestacionamiento.repository.FeedbackRepository;
import cl.alcoholicos.gestorestacionamiento.repository.UsuarioRepository;
import cl.alcoholicos.gestorestacionamiento.service.IFeedback;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FeedbackService implements IFeedback {

    private final FeedbackRepository feedbackRepository;
    private final FeedbackMapper feedbackMapper;
    private final UsuarioRepository usuarioRepository;

    @Override
    public FeedbackResponseDTO insert(FeedbackCreateDTO createDTO, Integer rutUsuario) {
        FeedbackEntity feedback = feedbackMapper.toEntity(createDTO);

        UsuarioEntity usuario = usuarioRepository.findById(rutUsuario)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));

        feedback.setUsuario(usuario);
        feedback.setFechaFeedback(LocalDate.now());

        FeedbackEntity feedbackGuardado = feedbackRepository.save(feedback);
        FeedbackResponseDTO responseDTO = feedbackMapper.toResponseDTO(feedbackGuardado);
        return responseDTO;
    }

    @Override
    public FeedbackEntity update(Integer idFeedback, FeedbackEntity feedback) {
        feedback.setIdFeedback(idFeedback);
        return feedbackRepository.save(feedback);
    }

    @Override
    public boolean delete(Integer idFeedback) {
        if (feedbackRepository.existsById(idFeedback)) {
            feedbackRepository.deleteById(idFeedback);
            return true;
        }
        return false;
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
