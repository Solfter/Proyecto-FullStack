package cl.alcoholicos.gestorestacionamiento.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MarcaResponseDTO {

    private int idMarca;

    private String nombreMarca;
}
