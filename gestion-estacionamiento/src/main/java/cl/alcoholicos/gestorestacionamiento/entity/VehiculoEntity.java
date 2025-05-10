package cl.alcoholicos.gestorestacionamiento.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "VEHICULO")
public class VehiculoEntity {
    @Id
    @Column(name = "PATENTE")
    private String patente;
    @Column(name = "COLOR")
    private String color;
    @Column(name = "RUT_USUARIO")
    private int rutUsuario;
    @Column(name = "ID_MODELO")
    private int idModelo;
    @Column(name = "ANO")
    private int anio;
}
