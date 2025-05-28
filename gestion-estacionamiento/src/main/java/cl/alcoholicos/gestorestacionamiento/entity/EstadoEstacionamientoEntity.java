package cl.alcoholicos.gestorestacionamiento.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "ESTADO_ESTACIONAMIENTO")
public class EstadoEstacionamientoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_EST_ESTACIONAMIENTO")
    private int idEstadoEstacionamiento;

    @Column(name = "DESC_ESTADO_ESTACIONAMIENTO")
    private String descEstadoEstacionamiento;

    @OneToMany(mappedBy = "estadoEstacionamiento", cascade = CascadeType.ALL)
    private List<EstacionamientoEntity> estacionamientos;
}
