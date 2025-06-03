package cl.alcoholicos.gestorestacionamiento.mapper;

import cl.alcoholicos.gestorestacionamiento.dto.EstacionamientoBasicDTO;
import cl.alcoholicos.gestorestacionamiento.dto.EstacionamientoCreateDTO;
import cl.alcoholicos.gestorestacionamiento.dto.EstacionamientoResponseDTO;
import cl.alcoholicos.gestorestacionamiento.dto.EstacionamientoUpdateDTO;
import cl.alcoholicos.gestorestacionamiento.dto.EstadoEstacionamientoResponseDTO;
import cl.alcoholicos.gestorestacionamiento.entity.EstacionamientoEntity;
import cl.alcoholicos.gestorestacionamiento.entity.EstadoEstacionamientoEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-03T18:53:03-0400",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.42.0.v20250514-1000, environment: Java 21.0.7 (Eclipse Adoptium)"
)
@Component
public class EstacionamientoMapperImpl implements EstacionamientoMapper {

    @Override
    public EstacionamientoResponseDTO toResponseDTO(EstacionamientoEntity estacionamientoEntity) {
        if ( estacionamientoEntity == null ) {
            return null;
        }

        EstacionamientoResponseDTO.EstacionamientoResponseDTOBuilder estacionamientoResponseDTO = EstacionamientoResponseDTO.builder();

        estacionamientoResponseDTO.estadoEstacionamiento( estadoEstacionamientoEntityToEstadoEstacionamientoResponseDTO( estacionamientoEntity.getEstadoEstacionamiento() ) );
        estacionamientoResponseDTO.idEstacionamiento( estacionamientoEntity.getIdEstacionamiento() );
        estacionamientoResponseDTO.nroEstacionamiento( estacionamientoEntity.getNroEstacionamiento() );

        return estacionamientoResponseDTO.build();
    }

    @Override
    public EstacionamientoEntity toEntity(EstacionamientoCreateDTO estacionamiento) {
        if ( estacionamiento == null ) {
            return null;
        }

        EstacionamientoEntity estacionamientoEntity = new EstacionamientoEntity();

        estacionamientoEntity.setNroEstacionamiento( estacionamiento.getNroEstacionamiento() );

        return estacionamientoEntity;
    }

    @Override
    public void updateFromUpdateDTO(EstacionamientoUpdateDTO updateDTO, EstacionamientoEntity entity) {
        if ( updateDTO == null ) {
            return;
        }

        entity.setNroEstacionamiento( updateDTO.getNroEstacionamiento() );
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

    protected EstadoEstacionamientoResponseDTO estadoEstacionamientoEntityToEstadoEstacionamientoResponseDTO(EstadoEstacionamientoEntity estadoEstacionamientoEntity) {
        if ( estadoEstacionamientoEntity == null ) {
            return null;
        }

        EstadoEstacionamientoResponseDTO estadoEstacionamientoResponseDTO = new EstadoEstacionamientoResponseDTO();

        estadoEstacionamientoResponseDTO.setDescEstadoEstacionamiento( estadoEstacionamientoEntity.getDescEstadoEstacionamiento() );
        estadoEstacionamientoResponseDTO.setEstacionamientos( estacionamientoEntityListToEstacionamientoBasicDTOList( estadoEstacionamientoEntity.getEstacionamientos() ) );
        estadoEstacionamientoResponseDTO.setIdEstadoEstacionamiento( estadoEstacionamientoEntity.getIdEstadoEstacionamiento() );

        return estadoEstacionamientoResponseDTO;
    }
}
