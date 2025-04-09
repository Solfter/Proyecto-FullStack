package cl.estacionamiento.usuario.dto;

import org.hibernate.annotations.ForeignKey;

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
@Table(name = "ESTADO_RESERVA")
public class EstadoReservaDTO {
    @Id
    @Column(name = "ID_")
    private int idTipoEstReserva;
    @Id
    @Column(name = "ID_RESERVA")
    private int idReserva;
    @Column(name = "ID_ESTADO_Reserva")
    private String descEstadoReserva;
}