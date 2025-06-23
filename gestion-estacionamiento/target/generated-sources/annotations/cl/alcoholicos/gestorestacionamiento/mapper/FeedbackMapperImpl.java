package cl.alcoholicos.gestorestacionamiento.mapper;

import cl.alcoholicos.gestorestacionamiento.dto.FeedbackResponseDTO;
import cl.alcoholicos.gestorestacionamiento.entity.FeedbackEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-23T18:56:53-0400",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.42.0.v20250514-1000, environment: Java 21.0.7 (Eclipse Adoptium)"
)
@Component
public class FeedbackMapperImpl implements FeedbackMapper {

    @Override
    public FeedbackResponseDTO toResponseDTO(FeedbackEntity feedback) {
        if ( feedback == null ) {
            return null;
        }

        FeedbackResponseDTO.FeedbackResponseDTOBuilder feedbackResponseDTO = FeedbackResponseDTO.builder();

        feedbackResponseDTO.descFeedback( feedback.getDescFeedback() );
        feedbackResponseDTO.fechaFeedback( feedback.getFechaFeedback() );
        feedbackResponseDTO.idFeedback( feedback.getIdFeedback() );

        return feedbackResponseDTO.build();
    }

    @Override
    public List<FeedbackResponseDTO> toResponseDTOList(List<FeedbackEntity> feedbacks) {
        if ( feedbacks == null ) {
            return null;
        }

        List<FeedbackResponseDTO> list = new ArrayList<FeedbackResponseDTO>( feedbacks.size() );
        for ( FeedbackEntity feedbackEntity : feedbacks ) {
            list.add( toResponseDTO( feedbackEntity ) );
        }

        return list;
    }
}
