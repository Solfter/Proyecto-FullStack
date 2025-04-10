package cl.alcoholicos.gestorestacionamiento.marca.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
@Table(name = "MARCA")
public class MarcaDTO {
    @Id
    @Column(name = "ID_MARCA")
    private int idMarca;
    @Column(name = "NOMBRE_MARCA")
    private String nombreMarca;
}
