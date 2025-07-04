package cl.alcoholicos.gestorestacionamiento.mapper;

import cl.alcoholicos.gestorestacionamiento.dto.FeedbackCreateDTO;
import cl.alcoholicos.gestorestacionamiento.dto.FeedbackResponseDTO;
import cl.alcoholicos.gestorestacionamiento.dto.UsuarioBasicDTO;
import cl.alcoholicos.gestorestacionamiento.entity.FeedbackEntity;
import cl.alcoholicos.gestorestacionamiento.entity.UsuarioEntity;
import java.sql.Date;
import java.time.ZoneOffset;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-04T18:29:20-0400",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.42.50.v20250628-1110, environment: Java 21.0.7 (Eclipse Adoptium)"
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
        if ( feedback.getFechaFeedback() != null ) {
            feedbackResponseDTO.fechaFeedback( new Date( feedback.getFechaFeedback().atStartOfDay( ZoneOffset.UTC ).toInstant().toEpochMilli() ) );
        }
        feedbackResponseDTO.idFeedback( feedback.getIdFeedback() );
        feedbackResponseDTO.usuario( usuarioEntityToUsuarioBasicDTO( feedback.getUsuario() ) );

        return feedbackResponseDTO.build();
    }

    @Override
    public FeedbackEntity toEntity(FeedbackCreateDTO feedback) {
        if ( feedback == null ) {
            return null;
        }

        FeedbackEntity feedbackEntity = new FeedbackEntity();

        feedbackEntity.setDescFeedback( feedback.getDescFeedback() );
        if ( feedback.getFechaFeedback() != null ) {
            feedbackEntity.setFechaFeedback( feedback.getFechaFeedback().toLocalDate() );
        }

        return feedbackEntity;
    }

    protected UsuarioBasicDTO usuarioEntityToUsuarioBasicDTO(UsuarioEntity usuarioEntity) {
        if ( usuarioEntity == null ) {
            return null;
        }

        UsuarioBasicDTO usuarioBasicDTO = new UsuarioBasicDTO();

        usuarioBasicDTO.setApMaterno( usuarioEntity.getApMaterno() );
        usuarioBasicDTO.setApPaterno( usuarioEntity.getApPaterno() );
        usuarioBasicDTO.setCorreo( usuarioEntity.getCorreo() );
        usuarioBasicDTO.setDv( usuarioEntity.getDv() );
        usuarioBasicDTO.setNroCelular( usuarioEntity.getNroCelular() );
        usuarioBasicDTO.setPrimerNombre( usuarioEntity.getPrimerNombre() );
        usuarioBasicDTO.setRut( usuarioEntity.getRut() );
        usuarioBasicDTO.setSegundoNombre( usuarioEntity.getSegundoNombre() );

        return usuarioBasicDTO;
    }
}
