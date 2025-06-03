package cl.alcoholicos.gestorestacionamiento.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EspacioFavoritoResponseDTO {

    private int idEspacioFavorito;

    private UsuarioBasicDTO usuario;

    private EstacionamientoBasicDTO estacionamiento;

}
