package cl.alcoholicos.gestorestacionamiento.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehiculoCreateDTO {
    @NotBlank
    @Size(min = 6, max = 6)
    @Pattern(regexp = "[A-Z]{2}[0-9]{4}|[A-Z]{4}[0-9]{2}") // Validación patente chilena
    private String patente;
    
    @NotBlank
    private String color;
    
    @NotNull
    private int rutUsuario;
    
    @NotNull
    private int idModelo;
    
    @Min(1900)
    @Max(2025)
    private int anio;

}
