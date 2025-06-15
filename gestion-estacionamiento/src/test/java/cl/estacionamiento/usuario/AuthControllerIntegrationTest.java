package cl.estacionamiento.usuario;

import com.fasterxml.jackson.databind.ObjectMapper;
import cl.alcoholicos.gestorestacionamiento.dto.LoginRequest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.containsString;

@SpringBootTest(classes = cl.alcoholicos.gestorestacionamiento.GestorApplication.class)
@AutoConfigureMockMvc
class AuthControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testLoginFailure_UserNotFound() throws Exception {
        LoginRequest request = new LoginRequest();
        request.setCorreo("usuario_inexistente@correo.com");
        request.setPassword("password_cualquiera");

        mockMvc.perform(post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.message").value(containsString("Error de autenticación: Usuario no encontrado")));
    }

    @Test
    void testLoginSuccess_ValidCredentials() throws Exception {
        LoginRequest request = new LoginRequest();
        request.setCorreo("se.gonzalez2@duocuc.cl");
        request.setPassword("test_fullstack");

        mockMvc.perform(post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").isNotEmpty())
                .andExpect(jsonPath("$.correo").value("se.gonzalez2@duocuc.cl"))
                .andExpect(jsonPath("$.nombre").value("Sebastián"));
    }

    @Test
    void testLoginFailure_WrongPassword() throws Exception {
        LoginRequest request = new LoginRequest();
        request.setCorreo("se.gonzalez2@duocuc.cl");
        request.setPassword("wrong_password");

        mockMvc.perform(post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.message").value(containsString("Error de autenticación: Contraseña incorrecta")));
    }
}