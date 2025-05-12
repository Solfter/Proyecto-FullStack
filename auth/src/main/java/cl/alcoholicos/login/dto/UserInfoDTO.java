package main.java.cl.alcoholicos.login.dto;

@Data
@Builder
public class UserInfoDTO {
    private Long id;
    private String email;
    private String nombre;
    private List<String> roles;
}