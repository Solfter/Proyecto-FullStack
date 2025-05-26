package cl.alcoholicos.gestorestacionamiento.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
    
    @ManyToOne
    @JoinColumn(name = "RUT_USUARIO")
    private UsuarioEntity usuario;

    @ManyToOne
    @JoinColumn(name = "ID_MODELO")
    private ModeloEntity modelo;

    @Column(name = "ANO")
    private int anio;

}
