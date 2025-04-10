package cl.alcoholicos.gestorestacionamiento.estadoincidente.dto;

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
@Table(name = "ESTADO_INCIDENTE")
public class EstadoIncidenteDTO {
    @Id
    @Column(name = "ID_ESTADO_INCIDENTE")
    private int idEstadoIncidente;
    @Column(name = "DESC_ESTADO_INCIDENTE")
    private String descEstadoIncidente;
}
