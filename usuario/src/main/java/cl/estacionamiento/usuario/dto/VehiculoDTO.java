package cl.estacionamiento.usuario.dto;

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
@Table(name = "VEHICULO")
public class VehiculoDTO {
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
