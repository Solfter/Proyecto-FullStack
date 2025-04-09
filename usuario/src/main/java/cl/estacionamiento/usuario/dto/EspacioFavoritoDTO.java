package cl.estacionamiento.usuario.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ESPACIO_FAVORITO")
public class EspacioFavoritoDTO {
    @Column(name = "RUT_USUARIO")
    private int rut;
    @Column(name = "ID_ESTACIONAMIENTO")
    private int idEstacionamiento;
}
