package cl.alcoholicos.gestorestacionamiento.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "TIPO_ESTACIONAMIENTO")
public class TipoEstacionamientoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TIPO_ESTACIONAMIENTO")
    private Integer idTipoEstacionamiento;

    @Column(name = "DESC_TIPO_ESTACIONAMIENTO")
    private String descTipoEstacionamiento;

    @OneToMany(mappedBy = "tipoEstacionamiento", fetch = FetchType.LAZY)
    private List<EstacionamientoEntity> estacionamientos;
}
