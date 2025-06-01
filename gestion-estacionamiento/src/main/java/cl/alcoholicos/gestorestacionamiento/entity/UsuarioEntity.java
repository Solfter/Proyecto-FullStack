package cl.alcoholicos.gestorestacionamiento.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
    @Column(name = "RUT_USUARIO")
    private int rut;
    
    @Column(name = "DV", length = 1)
    private String dv;
    
    @Column(name = "PNOMBRE_USUARIO", length = 50) 
    private String primerNombre;    
    
    @Column(name = "SNOMBRE_USUARIO", length = 50) 
    private String segundoNombre;    
    
    @Column(name = "APELLIDO_PATERNO_USUARIO", length = 50)
    private String apPaterno;  
    
    @Column(name = "APELLIDO_MATERNO_USUARIO", length = 50)
    private String apMaterno;  
    
    @Column(name = "CORREO", length = 50)
    private String correo;
    
    @Column(name = "NRO_CELULAR")
    private int nroCelular;

    @Column(name = "PASSWORD")
    private String password;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<VehiculoEntity> vehiculos;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<IncidenteEntity> incidentes;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<FeedbackEntity> feedbacks;
    
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<ReservaEntity> reservas;
    
    @ManyToOne
    @JoinColumn(name = "ID_TIPO_USUARIO")
    private TipoUsuarioEntity tipoUsuario;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<EspacioFavoritoEntity> espaciosFavoritos;

}
