package cl.alcoholicos.gestorestacionamiento.estacionamiento.dto;

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
@Table(name = "ESTACIONAMIENTO")
public class EstacionamientoDTO {
    @Id
    @Column(name = "ID_ESTACIONAMIENTO")
    private int idEstacionamiento;
    @Column(name = "NRO_ESTACIONAMIENTO")
    private int nroEstacionamiento;
    @Column(name = "ID_EST_ESTACIONAMIENTO")
    private int idEstadoEstacionamiento;
    @Column(name = "ID_SENSOR")
    private int idSensor;
    @Column(name = "ID_ZONA")
    private char idZona;
}
