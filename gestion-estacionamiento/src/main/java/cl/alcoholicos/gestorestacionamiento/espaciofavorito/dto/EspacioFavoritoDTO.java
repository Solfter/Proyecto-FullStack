package cl.alcoholicos.gestorestacionamiento.espaciofavorito.dto;

import cl.alcoholicos.gestorestacionamiento.usuario.dto.UsuarioDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
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
@IdClass(EspacioFavoritoId.class)
@Table(name = "ESPACIO_FAVORITO")
public class EspacioFavoritoDTO {

    @Id
    @ManyToOne
    @JoinColumn(name = "rut_usuario", nullable = false)
    private UsuarioDTO usuario;

    @Id
    @Column(name = "ID_ESTACIONAMIENTO")
    private int idEstacionamiento;

}


