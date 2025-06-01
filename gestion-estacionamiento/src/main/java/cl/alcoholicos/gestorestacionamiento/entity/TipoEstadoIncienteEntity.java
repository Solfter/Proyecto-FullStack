package cl.alcoholicos.gestorestacionamiento.entity;

import java.util.List;

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
@Table(name = "TIPO_ESTADO_INCIDENTE")
public class TipoEstadoIncienteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_estado_incidente")
    private int idTipoEstadoIncidente;
    
    @Column(name = "descripcion_estado_incidente")
    private String descTipoEstadoIncidente;

    @OneToMany(mappedBy = "tipoEstadoIncidente")
    private List<EstadoIncidenteEntity> estadosIncidentes; 
}
