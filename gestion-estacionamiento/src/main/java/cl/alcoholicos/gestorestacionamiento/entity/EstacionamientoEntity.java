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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ESTACIONAMIENTO")
public class EstacionamientoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ESTACIONAMIENTO")
    private int idEstacionamiento;

    @Column(name = "NRO_ESTACIONAMIENTO")
    private int nroEstacionamiento;

    @ManyToOne
    @JoinColumn(name = "id_est_estacionamiento")
    private EstadoEstacionamientoEntity estadoEstacionamiento;

    @OneToOne
    @JoinColumn(name = "ID_SENSOR")
    private SensorEntity sensor;

    @OneToMany(mappedBy = "estacionamiento", cascade = CascadeType.ALL)
    private List<EspacioFavoritoEntity> espaciosFavoritos;
}
