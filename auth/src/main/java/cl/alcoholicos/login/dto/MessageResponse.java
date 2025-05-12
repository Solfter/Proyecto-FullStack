package main.java.cl.alcoholicos.login.dto;

@Data
@Builder
public class MessageResponse {
    private String message;
    
    public MessageResponse(String message) {
        this.message = message;
    }
}