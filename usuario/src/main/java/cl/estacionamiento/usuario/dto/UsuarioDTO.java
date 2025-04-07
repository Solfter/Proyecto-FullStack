package cl.estacionamiento.usuario.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {
    @Id
    @Column(name = "RUT")
    private int rut;
    @Column(name = "DV")
    private char dv;
    @Column(name = "PNOMBRE")
    private String pNombre;
    @Column(name = "SNOMBRE")
    private String sNombre;
    @Column(name = "APPATERNO")
    private String apPaterno;
    @Column(name = "APMATERNO")
    private String apMaterno;
    @Column(name = "CORREO")
    private String correo;
    @Column(name = "NRO_CELULAR")
    private int nroCelular;
}
