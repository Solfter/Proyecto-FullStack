package cl.alcoholicos.gestorestacionamiento.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
@Table(name = "MODELO")
public class ModeloEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_MODELO")
    private int idModelo;

    @Column(name = "NOMBRE_MODELO")
    private String nombreModelo;

    @ManyToOne
    @JoinColumn(name = "ID_MARCA")
    private MarcaEntity marca;

    @OneToMany(mappedBy = "modelo" ,cascade = CascadeType.ALL)
    private List<VehiculoEntity> vehiculos;
}
