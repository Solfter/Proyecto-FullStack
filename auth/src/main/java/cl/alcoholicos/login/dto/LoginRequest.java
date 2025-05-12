package main.java.cl.alcoholicos.login.dto;

@Data
@Builder
public class LoginRequest {
    @NotBlank
    private String email;

    @NotBlank
    private String password;
}