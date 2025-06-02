package cl.alcoholicos.gestorestacionamiento.mapper;

import cl.alcoholicos.gestorestacionamiento.dto.MarcaResponseDTO;
import cl.alcoholicos.gestorestacionamiento.dto.ModeloResponseDTO;
import cl.alcoholicos.gestorestacionamiento.dto.VehiculoCreateDTO;
import cl.alcoholicos.gestorestacionamiento.dto.VehiculoResponseDTO;
import cl.alcoholicos.gestorestacionamiento.dto.VehiculoUpdateDTO;
import cl.alcoholicos.gestorestacionamiento.entity.MarcaEntity;
import cl.alcoholicos.gestorestacionamiento.entity.ModeloEntity;
import cl.alcoholicos.gestorestacionamiento.entity.VehiculoEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-02T18:31:14-0400",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.42.0.v20250514-1000, environment: Java 21.0.7 (Eclipse Adoptium)"
)
@Component
public class VehiculoMapperImpl implements VehiculoMapper {

    @Override
    public VehiculoResponseDTO toResponseDTO(VehiculoEntity vehiculo) {
        if ( vehiculo == null ) {
            return null;
        }

        VehiculoResponseDTO.VehiculoResponseDTOBuilder vehiculoResponseDTO = VehiculoResponseDTO.builder();

        vehiculoResponseDTO.anio( vehiculo.getAnio() );
        vehiculoResponseDTO.color( vehiculo.getColor() );
        vehiculoResponseDTO.modelo( modeloEntityToModeloResponseDTO( vehiculo.getModelo() ) );
        vehiculoResponseDTO.patente( vehiculo.getPatente() );

        return vehiculoResponseDTO.build();
    }

    @Override
    public VehiculoEntity toEntity(VehiculoCreateDTO vehiculoCreateDTO) {
        if ( vehiculoCreateDTO == null ) {
            return null;
        }

        VehiculoEntity vehiculoEntity = new VehiculoEntity();

        vehiculoEntity.setAnio( vehiculoCreateDTO.getAnio() );
        vehiculoEntity.setColor( vehiculoCreateDTO.getColor() );
        vehiculoEntity.setPatente( vehiculoCreateDTO.getPatente() );

        return vehiculoEntity;
    }

    @Override
    public void updateFromUpdateDTO(VehiculoUpdateDTO updateDTO, VehiculoEntity entity) {
        if ( updateDTO == null ) {
            return;
        }

        if ( updateDTO.getColor() != null ) {
            entity.setColor( updateDTO.getColor() );
        }
    }

    protected MarcaResponseDTO marcaEntityToMarcaResponseDTO(MarcaEntity marcaEntity) {
        if ( marcaEntity == null ) {
            return null;
        }

        MarcaResponseDTO marcaResponseDTO = new MarcaResponseDTO();

        marcaResponseDTO.setIdMarca( marcaEntity.getIdMarca() );
        marcaResponseDTO.setNombreMarca( marcaEntity.getNombreMarca() );

        return marcaResponseDTO;
    }

    protected ModeloResponseDTO modeloEntityToModeloResponseDTO(ModeloEntity modeloEntity) {
        if ( modeloEntity == null ) {
            return null;
        }

        ModeloResponseDTO modeloResponseDTO = new ModeloResponseDTO();

        modeloResponseDTO.setIdModelo( modeloEntity.getIdModelo() );
        modeloResponseDTO.setMarca( marcaEntityToMarcaResponseDTO( modeloEntity.getMarca() ) );
        modeloResponseDTO.setNombreModelo( modeloEntity.getNombreModelo() );

        return modeloResponseDTO;
    }
}
