package cl.alcoholicos.gestorestacionamiento.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackCreateDTO {

    private Date fechaFeedback;
    
    private String descFeedback;

}
