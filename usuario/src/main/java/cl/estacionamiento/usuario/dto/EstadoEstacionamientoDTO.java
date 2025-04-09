package cl.estacionamiento.usuario.dto;

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
@Table(name = "ESTADO_ESTACIONAMIENTO")
public class EstadoEstacionamientoDTO {
    @Id
    @Column(name = "ID_EST_ESTACIONAMIENTO")
    private int idEstadoEstacionamiento;
    @Column(name = "DESC_ESTADO_ESTACIONAMIENTO")
    private String descEstadoEstacionamiento;
}
