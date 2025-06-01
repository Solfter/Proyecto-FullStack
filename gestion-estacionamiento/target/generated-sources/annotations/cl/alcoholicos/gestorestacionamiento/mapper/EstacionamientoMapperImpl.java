package cl.alcoholicos.gestorestacionamiento.mapper;

import cl.alcoholicos.gestorestacionamiento.dto.EstacionamientoCreateDTO;
import cl.alcoholicos.gestorestacionamiento.dto.EstacionamientoResponseDTO;
import cl.alcoholicos.gestorestacionamiento.dto.EstacionamientoUpdateDTO;
import cl.alcoholicos.gestorestacionamiento.dto.EstadoEstacionamientoResponseDTO;
import cl.alcoholicos.gestorestacionamiento.dto.SensorResponseDTO;
import cl.alcoholicos.gestorestacionamiento.entity.EstacionamientoEntity;
import cl.alcoholicos.gestorestacionamiento.entity.EstadoEstacionamientoEntity;
import cl.alcoholicos.gestorestacionamiento.entity.SensorEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-01T16:11:56-0400",
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
        estacionamientoResponseDTO.sensor( sensorEntityToSensorResponseDTO( estacionamientoEntity.getSensor() ) );

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

    protected EstadoEstacionamientoResponseDTO estadoEstacionamientoEntityToEstadoEstacionamientoResponseDTO(EstadoEstacionamientoEntity estadoEstacionamientoEntity) {
        if ( estadoEstacionamientoEntity == null ) {
            return null;
        }

        EstadoEstacionamientoResponseDTO estadoEstacionamientoResponseDTO = new EstadoEstacionamientoResponseDTO();

        estadoEstacionamientoResponseDTO.setDescEstadoEstacionamiento( estadoEstacionamientoEntity.getDescEstadoEstacionamiento() );
        estadoEstacionamientoResponseDTO.setIdEstadoEstacionamiento( estadoEstacionamientoEntity.getIdEstadoEstacionamiento() );

        return estadoEstacionamientoResponseDTO;
    }

    protected SensorResponseDTO sensorEntityToSensorResponseDTO(SensorEntity sensorEntity) {
        if ( sensorEntity == null ) {
            return null;
        }

        SensorResponseDTO sensorResponseDTO = new SensorResponseDTO();

        sensorResponseDTO.setIdSensor( sensorEntity.getIdSensor() );

        return sensorResponseDTO;
    }
}
