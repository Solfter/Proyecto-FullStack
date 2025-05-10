package cl.alcoholicos.gestorestacionamiento.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehiculoResponseDTO {
    private String patente;
    private String color;
    private int anio;
    private int rutUsuario; // Considera usar solo el DTO básico del usuario si existe
    private int idModelo;   // Considera cambiar por ModeloDTO si necesitas más datos
}
