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
@Table(name = "USUARIO")
public class UsuarioEntity {
    @Id
    @Column(name = "RUT")
    private int rut;
    
    @Column(name = "DV")
    private String dv;
    
    @Column(name = "P_NOMBRE") 
    private String primerNombre;    
    
    @Column(name = "S_NOMBRE") 
    private String segundoNombre;    
    
    @Column(name = "AP_PATERNO")
    private String apPaterno;  
    
    @Column(name = "AP_MATERNO")
    private String apMaterno;  
    
    @Column(name = "CORREO")
    private String correo;
    
    @Column(name = "NRO_CELULAR")
    private int nroCelular;

    @Column(name = "PASSWORD")
    private String password;
}
