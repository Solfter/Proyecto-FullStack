package cl.alcoholicos.gestorestacionamiento.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "ESTADO_INCIDENTE")
public class EstadoIncidenteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ESTADO_INCIDENTE")
    private int idEstadoIncidente;

    @ManyToOne
    @JoinColumn(name = "ID_TIPO_ESTADO_INCIDENTE")
    private TipoEstadoIncidenteEntity tipoEstadoIncidente;

    @ManyToOne
    @JoinColumn(name = "ID_INCIDENTE")
    private IncidenteEntity incidente;
}
