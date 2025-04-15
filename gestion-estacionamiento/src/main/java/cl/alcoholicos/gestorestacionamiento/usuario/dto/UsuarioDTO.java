package cl.alcoholicos.gestorestacionamiento.usuario.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

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
@Table(name = "USUARIO")
public class UsuarioDTO {
    @Id
    @Column(name = "RUT")
    private int rut;
    
    @Column(name = "DV")
    private Character dv;
    
    @Column(name = "P_NOMBRE") 
    @JsonProperty("pNombre")
    private String pNombre;    
    
    @Column(name = "S_NOMBRE") 
    @JsonProperty("sNombre")
    private String sNombre;    
    
    @Column(name = "AP_PATERNO")
    private String apPaterno;  
    
    @Column(name = "AP_MATERNO")
    private String apMaterno;  
    
    @Column(name = "CORREO")
    private String correo;
    
    @Column(name = "NRO_CELULAR")
    private int nroCelular;
}
