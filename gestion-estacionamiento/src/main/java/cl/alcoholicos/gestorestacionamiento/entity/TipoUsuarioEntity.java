package cl.alcoholicos.gestorestacionamiento.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name = "TIPO_USUARIO")
public class TipoUsuarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TIPO_USUARIO")
    private int idTipoUsuario;

    @Column(name = "DESC_TIPO_USUARIO")
    private String descTipoUsuario;

    @OneToMany(mappedBy = "tipoUsuario")
    private List<UsuarioEntity> usuarios;
    
}
