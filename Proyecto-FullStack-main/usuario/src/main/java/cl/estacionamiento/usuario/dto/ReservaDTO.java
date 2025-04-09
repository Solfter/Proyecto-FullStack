package cl.estacionamiento.usuario.dto;

import java.sql.Date;
import java.time.LocalTime;

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
@Table(name = "RESERVA")
public class ReservaDTO {
    @Id
    @Column(name = "ID_RESERVA")
    private int idReserva;
    @Column(name = "FECHA_CREACION_RESERVA")
    private Date fechaCreacionReserva;
    @Column(name = "FECHA_RESERVA")
    private Date fechaReserva;
    @Column(name = "HORA_INICIO")
    private LocalTime horaInicio;
    @Column(name = "HORA_FIN")
    private LocalTime horaFin;
    @Column(name = "RUT_USUARIO")
    private int rutUsuario;
    @Column(name = "ID_ESTACIONAMIENTO")
    private int idEstacionamiento;
}
