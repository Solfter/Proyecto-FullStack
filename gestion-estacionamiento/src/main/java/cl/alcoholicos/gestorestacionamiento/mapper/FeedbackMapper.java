package cl.alcoholicos.gestorestacionamiento.mapper;

import org.mapstruct.Mapper;

import cl.alcoholicos.gestorestacionamiento.dto.FeedbackCreateDTO;
import cl.alcoholicos.gestorestacionamiento.dto.FeedbackResponseDTO;
import cl.alcoholicos.gestorestacionamiento.entity.FeedbackEntity;

@Mapper(componentModel = "spring")
public interface FeedbackMapper {
    FeedbackResponseDTO toResponseDTO(FeedbackEntity feedback);
    FeedbackEntity toEntity (FeedbackCreateDTO feedback);

}

