package cl.alcoholicos.gestorestacionamiento.mapper;

import cl.alcoholicos.gestorestacionamiento.dto.EstacionamientoCreateDTO;
import cl.alcoholicos.gestorestacionamiento.dto.EstacionamientoResponseDTO;
import cl.alcoholicos.gestorestacionamiento.dto.EstacionamientoUpdateDTO;
import cl.alcoholicos.gestorestacionamiento.entity.EstacionamientoEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-24T08:58:27-0400",
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

        estacionamientoResponseDTO.idEstacionamiento( estacionamientoEntity.getIdEstacionamiento() );
        estacionamientoResponseDTO.idEstadoEstacionamiento( estacionamientoEntity.getIdEstadoEstacionamiento() );
        estacionamientoResponseDTO.idSensor( estacionamientoEntity.getIdSensor() );
        estacionamientoResponseDTO.idZona( estacionamientoEntity.getIdZona() );
        estacionamientoResponseDTO.nroEstacionamiento( estacionamientoEntity.getNroEstacionamiento() );

        return estacionamientoResponseDTO.build();
    }

    @Override
    public EstacionamientoEntity toEntity(EstacionamientoCreateDTO estacionamiento) {
        if ( estacionamiento == null ) {
            return null;
        }

        EstacionamientoEntity estacionamientoEntity = new EstacionamientoEntity();

        estacionamientoEntity.setIdEstadoEstacionamiento( estacionamiento.getIdEstadoEstacionamiento() );
        estacionamientoEntity.setIdSensor( estacionamiento.getIdSensor() );
        estacionamientoEntity.setIdZona( estacionamiento.getIdZona() );
        estacionamientoEntity.setNroEstacionamiento( estacionamiento.getNroEstacionamiento() );

        return estacionamientoEntity;
    }

    @Override
    public void updateFromUpdateDTO(EstacionamientoUpdateDTO updateDTO, EstacionamientoEntity entity) {
        if ( updateDTO == null ) {
            return;
        }

        entity.setIdEstadoEstacionamiento( updateDTO.getIdEstadoEstacionamiento() );
        entity.setIdSensor( updateDTO.getIdSensor() );
        if ( updateDTO.getIdZona() != null ) {
            entity.setIdZona( updateDTO.getIdZona() );
        }
        entity.setNroEstacionamiento( updateDTO.getNroEstacionamiento() );
    }
}
