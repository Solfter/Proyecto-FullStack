package cl.alcoholicos.gestorestacionamiento.vehiculo.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehiculoUpdateDTO {
    @NotBlank
    private String color;
    
    @Min(1900)
    @Max(2025)
    private Integer anio; // Integer permite null
    
    // No incluimos patente, rutUsuario ni idModelo porque no deberían cambiarse
}