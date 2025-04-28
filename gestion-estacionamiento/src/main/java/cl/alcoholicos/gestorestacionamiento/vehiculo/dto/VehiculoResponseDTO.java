package cl.alcoholicos.gestorestacionamiento.vehiculo.dto;

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
    
    // Ejemplo de método para formatear datos
    public String getPatenteFormateada() {
        return patente.substring(0, 2) + "·" + patente.substring(2, 4) + "·" + patente.substring(4);
    }
}
