package cl.alcoholicos.gestorestacionamiento.vehiculo.mapper;

import cl.alcoholicos.gestorestacionamiento.vehiculo.dto.VehiculoCreateDTO;
import cl.alcoholicos.gestorestacionamiento.vehiculo.dto.VehiculoResponseDTO;
import cl.alcoholicos.gestorestacionamiento.vehiculo.dto.VehiculoUpdateDTO;
import cl.alcoholicos.gestorestacionamiento.vehiculo.entity.VehiculoEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-28T22:46:12-0400",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.42.0.z20250331-1358, environment: Java 21.0.6 (Eclipse Adoptium)"
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
        vehiculoResponseDTO.idModelo( vehiculo.getIdModelo() );
        vehiculoResponseDTO.patente( vehiculo.getPatente() );
        vehiculoResponseDTO.rutUsuario( vehiculo.getRutUsuario() );

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
        vehiculoEntity.setIdModelo( vehiculoCreateDTO.getIdModelo() );
        vehiculoEntity.setPatente( vehiculoCreateDTO.getPatente() );
        vehiculoEntity.setRutUsuario( vehiculoCreateDTO.getRutUsuario() );

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
}
