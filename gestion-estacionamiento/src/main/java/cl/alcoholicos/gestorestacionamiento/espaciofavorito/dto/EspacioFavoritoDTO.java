package cl.alcoholicos.gestorestacionamiento.espaciofavorito.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
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
@IdClass(EspacioFavoritoId.class)
@Table(name = "ESPACIO_FAVORITO")
public class EspacioFavoritoDTO {
    @Id
    @Column(name = "ID_ESPACIO_FAVORITO")
    private int idEspacioFavorito;
    @Id
    @Column(name = "ID_ESTACIONAMIENTO")
    private int idEstacionamiento;
}
