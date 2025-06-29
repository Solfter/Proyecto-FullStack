package cl.alcoholicos.gestorestacionamiento.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_EST_ESTACIONAMIENTO")
    private EstadoEstacionamientoEntity estadoEstacionamiento;

    @OneToOne
    @JoinColumn(name = "ID_SENSOR", unique = true)
    private SensorEntity sensor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_TIPO_ESTACIONAMIENTO")
    private TipoEstacionamientoEntity tipoEstacionamiento;

    @OneToMany(mappedBy = "estacionamiento", cascade = CascadeType.ALL)
    private List<EspacioFavoritoEntity> espaciosFavoritos;
}
