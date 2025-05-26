package cl.alcoholicos.gestorestacionamiento.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "RESERVA")
public class ReservaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_RESERVA")
    private int idReserva;

    @Column(name = "FECHA_CREACION_RESERVA")
    private LocalDate fechaCreacionReserva;

    @Column(name = "FECHA_RESERVA")
    private LocalDate fechaReserva;

    @Column(name = "HORA_INICIO")
    private LocalTime horaInicio;

    @Column(name = "HORA_FIN")
    private LocalTime horaFin;

    @ManyToOne
    @JoinColumn(name = "RUT_USUARIO")
    private UsuarioEntity usuario;

    @ManyToOne
    @JoinColumn(name = "ID_ESTACIONAMIENTO")
    private EstacionamientoEntity estacionamiento;

    @OneToMany(mappedBy = "reserva")
    private List<EstadoReservaEntity> estadosReservas;
    
}
