package main.java.cl.alcoholicos.login.dto;

@Data
@Builder
public class LoginResponse {
    private String token;
    private Long id;
    private String email;
    private String nombre;
    private List<String> roles;
}