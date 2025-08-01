package cl.alcoholicos.gestorestacionamiento.entity;

import java.time.LocalDateTime;

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
@Table(name = "ESTADO_RESERVA")
public class EstadoReservaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ESTADO_RESERVA")
    private int idEstadoReserva;

    @ManyToOne
    @JoinColumn(name = "ID_TIPO_ESTADO_RESERVA")
    private TipoEstadoReservaEntity tipoEstadoReserva;

    @ManyToOne
    @JoinColumn(name = "ID_RESERVA")
    private ReservaEntity reserva;

    @Column(name = "FECHA_ESTADO_RESERVA")
    private LocalDateTime fechaEstadoReserva;

}