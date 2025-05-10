package cl.alcoholicos.gestorestacionamiento.zona.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ZONA")
public class ZonaEntity {
    @Id
    @Column(name = "ID_ZONA")
    private String idZona;

    @Column(name = "NOMBRE_ZONA")
    private String nombreZona;

    @Column(name = "DESCRIPCION")
    private String descripcion;

    @Column(name = "CAPACIDAD")
    private int capacidad;
}
