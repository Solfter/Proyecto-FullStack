package cl.alcoholicos.gestorestacionamiento.mapper;

import cl.alcoholicos.gestorestacionamiento.dto.EstadoEstacionamientoResponseDTO;
import cl.alcoholicos.gestorestacionamiento.entity.EstadoEstacionamientoEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-01T16:11:56-0400",
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
        estadoEstacionamientoResponseDTO.setIdEstadoEstacionamiento( estadoEstacionamientoEntity.getIdEstadoEstacionamiento() );

        return estadoEstacionamientoResponseDTO;
    }

    @Override
    public EstadoEstacionamientoEntity toEntity(EstadoEstacionamientoResponseDTO estacionamiento) {
        if ( estacionamiento == null ) {
            return null;
        }

        EstadoEstacionamientoEntity estadoEstacionamientoEntity = new EstadoEstacionamientoEntity();

        estadoEstacionamientoEntity.setDescEstadoEstacionamiento( estacionamiento.getDescEstadoEstacionamiento() );
        estadoEstacionamientoEntity.setIdEstadoEstacionamiento( estacionamiento.getIdEstadoEstacionamiento() );

        return estadoEstacionamientoEntity;
    }

    @Override
    public void updateFromUpdateDTO(EstadoEstacionamientoResponseDTO updateDTO, EstadoEstacionamientoEntity entity) {
        if ( updateDTO == null ) {
            return;
        }

        if ( updateDTO.getDescEstadoEstacionamiento() != null ) {
            entity.setDescEstadoEstacionamiento( updateDTO.getDescEstadoEstacionamiento() );
        }
        entity.setIdEstadoEstacionamiento( updateDTO.getIdEstadoEstacionamiento() );
    }
}
