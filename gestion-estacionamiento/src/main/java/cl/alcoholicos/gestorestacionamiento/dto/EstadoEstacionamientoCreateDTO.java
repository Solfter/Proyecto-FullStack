package cl.alcoholicos.gestorestacionamiento.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstadoEstacionamientoCreateDTO {

    @NotBlank(message = "La descripción del estado es obligatoria")

    private String descEstadoEstacionamiento;
    

}
