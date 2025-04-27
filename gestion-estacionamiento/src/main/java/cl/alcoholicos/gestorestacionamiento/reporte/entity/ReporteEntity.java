package cl.alcoholicos.gestorestacionamiento.reporte.entity;

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
@Table(name = "REPORTE")
public class ReporteEntity {
    @Id
    @Column(name = "ID_REPORTE")
    private int idReporte;
    @Column(name = "FEC_REPORTE")
    private Date fechaReporte;
    @Column(name = "RUT_USUARIO")
    private int rutUsuario;
    @Column(name = "DESC_REPORTE")
    private String descReporte;
}
