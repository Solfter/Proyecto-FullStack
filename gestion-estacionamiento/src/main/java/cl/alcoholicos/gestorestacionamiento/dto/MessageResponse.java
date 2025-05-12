package cl.alcoholicos.gestorestacionamiento.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
// @AllArgsConstructor removed to avoid duplicate constructor
public class MessageResponse {
    private String message;
    
    public MessageResponse(String message) {
        this.message = message;
    }
}