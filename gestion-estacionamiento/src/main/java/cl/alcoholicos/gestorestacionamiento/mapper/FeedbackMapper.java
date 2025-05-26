package cl.alcoholicos.gestorestacionamiento.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import cl.alcoholicos.gestorestacionamiento.dto.FeedbackResponseDTO;
import cl.alcoholicos.gestorestacionamiento.entity.FeedbackEntity;

@Mapper(componentModel = "spring")
public interface FeedbackMapper {
    FeedbackResponseDTO toResponseDTO(FeedbackEntity feedback);
    List<FeedbackResponseDTO> toResponseDTOList(List<FeedbackEntity> feedbacks);

}

