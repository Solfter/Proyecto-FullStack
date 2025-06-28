package cl.alcoholicos.gestorestacionamiento.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModeloUpdateDTO {

    private String nombreModelo;

    private MarcaResponseDTO marca;     
}
