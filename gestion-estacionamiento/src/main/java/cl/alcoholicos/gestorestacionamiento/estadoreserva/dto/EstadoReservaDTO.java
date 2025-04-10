package cl.alcoholicos.gestorestacionamiento.estadoreserva.dto;

import cl.alcoholicos.gestorestacionamiento.estadoreserva.controller.EstadoReservaId;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
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
@IdClass(EstadoReservaId.class)
@Table(name = "ESTADO_RESERVA")
public class EstadoReservaDTO {
    @Id
    @Column(name = "ID_TPO_EST_RESERVA")
    private int idTipoEstReserva;
    @Id
    @Column(name = "ID_RESERVA")
    private int idReserva;
    @Column(name = "ID_ESTADO_RESERVA")
    private String descEstadoReserva;
}