package cl.alcoholicos.gestorestacionamiento.zona.entity;

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
@Table(name = "ZONA")
public class ZonaEntity {
    @Id
    @Column(name = "ID_ZONA")
    private Character idZona;
}
