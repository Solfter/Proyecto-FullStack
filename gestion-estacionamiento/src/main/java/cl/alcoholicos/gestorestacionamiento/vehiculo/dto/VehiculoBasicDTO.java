package cl.alcoholicos.gestorestacionamiento.vehiculo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehiculoBasicDTO {
    private String patente;
    private String color;
    private int anio;
}
