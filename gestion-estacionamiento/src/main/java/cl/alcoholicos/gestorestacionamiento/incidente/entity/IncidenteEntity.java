package cl.alcoholicos.gestorestacionamiento.incidente.entity;

import java.sql.Date;

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
@Table(name = "INCIDENTE")
public class IncidenteEntity {
    @Id
    @Column(name = "ID_INCIDENTE")
    private int idIncidente;
    @Column(name = "FECHA_INCIDENTE")
    private Date fechaIncidente;
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Column(name = "ID_ESTADO_INCIDENTE")
    private int idEstadoIncidente;
    @Column(name = "RUT_USUARIO")
    private int rutUsuario;
}
