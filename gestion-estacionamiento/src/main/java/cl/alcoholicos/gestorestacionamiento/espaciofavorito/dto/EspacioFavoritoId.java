package cl.alcoholicos.gestorestacionamiento.espaciofavorito.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class EspacioFavoritoId implements Serializable {
    private int idEstacionamiento;
    private int rutUsuario;
}

