package cl.alcoholicos.gestorestacionamiento.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "ESPACIO_FAVORITO")
public class EspacioFavoritoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ESPACIO_FAVORITO")
    private int idEspacioFavorito;

    @ManyToOne
    @JoinColumn(name = "RUT_USUARIO")
    private UsuarioEntity usuario;
    
    @ManyToOne
    @JoinColumn(name = "ID_ESTACIONAMIENTO")
    private EstacionamientoEntity estacionamiento;


}


