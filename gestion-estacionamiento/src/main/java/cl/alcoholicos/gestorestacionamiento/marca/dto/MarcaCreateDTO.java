package cl.alcoholicos.gestorestacionamiento.marca.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MarcaCreateDTO {
    private String nombreMarca;
}
