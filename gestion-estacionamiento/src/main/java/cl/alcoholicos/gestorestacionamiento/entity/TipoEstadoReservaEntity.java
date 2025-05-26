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
@Table(name = "TIPO_ESTADO_RESERVA")
public class TipoEstadoReservaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TPO_EST_RESERVA")
    private int idTipoEstadoReserva;
    
    @Column(name = "DESC_TPO_EST_RESERVA")
    private String descTipoEstadoReserva;

    @OneToMany(mappedBy = "tipoEstadoReserva")
    private List<EstadoReservaEntity> estadosReserva; 
}
