package cl.alcoholicos.gestorestacionamiento.controller;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class EstadoReservaId implements Serializable {
    private int idTipoEstReserva;
    private int idReserva;
}
