package cl.alcoholicos.gestorestacionamiento.mapper;

import cl.alcoholicos.gestorestacionamiento.dto.SensorBasicDTO;
import cl.alcoholicos.gestorestacionamiento.dto.SensorCreateDTO;
import cl.alcoholicos.gestorestacionamiento.dto.SensorResponseDTO;
import cl.alcoholicos.gestorestacionamiento.entity.SensorEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-05T11:15:22-0400",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.42.50.v20250628-1110, environment: Java 21.0.7 (Eclipse Adoptium)"
)
@Component
public class SensorMapperImpl implements SensorMapper {

    @Override
    public SensorResponseDTO toResponseDTO(SensorEntity sensorEntity) {
        if ( sensorEntity == null ) {
            return null;
        }

        SensorResponseDTO sensorResponseDTO = new SensorResponseDTO();

        sensorResponseDTO.setIdSensor( sensorEntity.getIdSensor() );
        if ( sensorEntity.getNroSensor() != null ) {
            sensorResponseDTO.setNroSensor( sensorEntity.getNroSensor() );
        }

        return sensorResponseDTO;
    }

    @Override
    public SensorEntity toEntity(SensorCreateDTO sensorCreateDTO) {
        if ( sensorCreateDTO == null ) {
            return null;
        }

        SensorEntity sensorEntity = new SensorEntity();

        sensorEntity.setNroSensor( sensorCreateDTO.getNroSensor() );

        return sensorEntity;
    }

    @Override
    public SensorBasicDTO toBasicDTO(SensorEntity reservaEntity) {
        if ( reservaEntity == null ) {
            return null;
        }

        SensorBasicDTO sensorBasicDTO = new SensorBasicDTO();

        sensorBasicDTO.setNroSensor( reservaEntity.getNroSensor() );

        return sensorBasicDTO;
    }

    @Override
    public void updateFromUpdateDTO(SensorResponseDTO updateDTO, SensorEntity entity) {
        if ( updateDTO == null ) {
            return;
        }

        entity.setIdSensor( updateDTO.getIdSensor() );
        entity.setNroSensor( updateDTO.getNroSensor() );
    }
}
