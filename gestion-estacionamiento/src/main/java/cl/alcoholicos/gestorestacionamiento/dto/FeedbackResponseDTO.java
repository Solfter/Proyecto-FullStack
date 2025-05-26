package cl.alcoholicos.gestorestacionamiento.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FeedbackResponseDTO {
    private int idFeedback;

    private Date fechaFeedback;
    
    private String descFeedback;

}
