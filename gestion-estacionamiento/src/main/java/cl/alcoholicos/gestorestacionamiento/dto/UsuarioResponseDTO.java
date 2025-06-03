package cl.alcoholicos.gestorestacionamiento.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioResponseDTO {
    private int rut;
    private String dv;
    private String primerNombre;
    private String segundoNombre;
    private String apPaterno;
    private String apMaterno;
    private String correo;
    private int nroCelular;
    private String password;
    private List<VehiculoResponseDTO> vehiculos; 
    private List<IncidenteResponseDTO> incidentes;
    private List<FeedbackResponseDTO> feedbaks;
    private List<ReservaResponseDTO> reservas;
    private TipoUsuarioResponseDTO tipoUsuario;
    private List<EspacioFavoritoResponseDTO> espaciosFavoritos;
    private List<String> roles = new ArrayList<>();
}