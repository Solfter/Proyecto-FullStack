package cl.alcoholicos.gestorestacionamiento.tipoestadoreserva.dto;

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
@Table(name = "TIPO_ESTADO_RESERVA")
public class TipoEstadoReservaDTO {
    @Id
    @Column(name = "ID_TPO_EST_RESERVA")
    private int idTipoEstadoReserva;
    @Column(name = "DESC_TPO_EST_RESERVA")
    private int descTipoEstadoReserva;
}
