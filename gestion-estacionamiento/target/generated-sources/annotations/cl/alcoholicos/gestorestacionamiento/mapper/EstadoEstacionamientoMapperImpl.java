package cl.alcoholicos.gestorestacionamiento.mapper;

import cl.alcoholicos.gestorestacionamiento.dto.EstacionamientoBasicDTO;
import cl.alcoholicos.gestorestacionamiento.dto.EstadoEstacionamientoResponseDTO;
import cl.alcoholicos.gestorestacionamiento.entity.EstacionamientoEntity;
import cl.alcoholicos.gestorestacionamiento.entity.EstadoEstacionamientoEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-22T19:22:10-0400",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.42.0.v20250514-1000, environment: Java 21.0.7 (Eclipse Adoptium)"
)
@Component
public class EstadoEstacionamientoMapperImpl implements EstadoEstacionamientoMapper {

    @Override
    public EstadoEstacionamientoResponseDTO toResponseDTO(EstadoEstacionamientoEntity estadoEstacionamientoEntity) {
        if ( estadoEstacionamientoEntity == null ) {
            return null;
        }

        EstadoEstacionamientoResponseDTO estadoEstacionamientoResponseDTO = new EstadoEstacionamientoResponseDTO();

        estadoEstacionamientoResponseDTO.setDescEstadoEstacionamiento( estadoEstacionamientoEntity.getDescEstadoEstacionamiento() );
        estadoEstacionamientoResponseDTO.setEstacionamientos( estacionamientoEntityListToEstacionamientoBasicDTOList( estadoEstacionamientoEntity.getEstacionamientos() ) );
        estadoEstacionamientoResponseDTO.setIdEstadoEstacionamiento( estadoEstacionamientoEntity.getIdEstadoEstacionamiento() );

        return estadoEstacionamientoResponseDTO;
    }

    @Override
    public void updateFromUpdateDTO(EstadoEstacionamientoResponseDTO updateDTO, EstadoEstacionamientoEntity entity) {
        if ( updateDTO == null ) {
            return;
        }

        if ( updateDTO.getDescEstadoEstacionamiento() != null ) {
            entity.setDescEstadoEstacionamiento( updateDTO.getDescEstadoEstacionamiento() );
        }
        if ( entity.getEstacionamientos() != null ) {
            List<EstacionamientoEntity> list = estacionamientoBasicDTOListToEstacionamientoEntityList( updateDTO.getEstacionamientos() );
            if ( list != null ) {
                entity.getEstacionamientos().clear();
                entity.getEstacionamientos().addAll( list );
            }
        }
        else {
            List<EstacionamientoEntity> list = estacionamientoBasicDTOListToEstacionamientoEntityList( updateDTO.getEstacionamientos() );
            if ( list != null ) {
                entity.setEstacionamientos( list );
            }
        }
        entity.setIdEstadoEstacionamiento( updateDTO.getIdEstadoEstacionamiento() );
    }

    protected EstacionamientoBasicDTO estacionamientoEntityToEstacionamientoBasicDTO(EstacionamientoEntity estacionamientoEntity) {
        if ( estacionamientoEntity == null ) {
            return null;
        }

        EstacionamientoBasicDTO estacionamientoBasicDTO = new EstacionamientoBasicDTO();

        estacionamientoBasicDTO.setNroEstacionamiento( estacionamientoEntity.getNroEstacionamiento() );

        return estacionamientoBasicDTO;
    }

    protected List<EstacionamientoBasicDTO> estacionamientoEntityListToEstacionamientoBasicDTOList(List<EstacionamientoEntity> list) {
        if ( list == null ) {
            return null;
        }

        List<EstacionamientoBasicDTO> list1 = new ArrayList<EstacionamientoBasicDTO>( list.size() );
        for ( EstacionamientoEntity estacionamientoEntity : list ) {
            list1.add( estacionamientoEntityToEstacionamientoBasicDTO( estacionamientoEntity ) );
        }

        return list1;
    }

    protected EstacionamientoEntity estacionamientoBasicDTOToEstacionamientoEntity(EstacionamientoBasicDTO estacionamientoBasicDTO) {
        if ( estacionamientoBasicDTO == null ) {
            return null;
        }

        EstacionamientoEntity estacionamientoEntity = new EstacionamientoEntity();

        estacionamientoEntity.setNroEstacionamiento( estacionamientoBasicDTO.getNroEstacionamiento() );

        return estacionamientoEntity;
    }

    protected List<EstacionamientoEntity> estacionamientoBasicDTOListToEstacionamientoEntityList(List<EstacionamientoBasicDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<EstacionamientoEntity> list1 = new ArrayList<EstacionamientoEntity>( list.size() );
        for ( EstacionamientoBasicDTO estacionamientoBasicDTO : list ) {
            list1.add( estacionamientoBasicDTOToEstacionamientoEntity( estacionamientoBasicDTO ) );
        }

        return list1;
    }
}
