package cl.alcoholicos.gestorestacionamiento.mapper;

import cl.alcoholicos.gestorestacionamiento.dto.SensorCreateDTO;
import cl.alcoholicos.gestorestacionamiento.dto.SensorResponseDTO;
import cl.alcoholicos.gestorestacionamiento.entity.SensorEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-14T08:53:17-0400",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.42.0.v20250514-1000, environment: Java 21.0.7 (Eclipse Adoptium)"
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

        return sensorResponseDTO;
    }

    @Override
    public SensorEntity toEntity(SensorCreateDTO sensorCreateDTO) {
        if ( sensorCreateDTO == null ) {
            return null;
        }

        SensorEntity sensorEntity = new SensorEntity();

        sensorEntity.setIdSensor( sensorCreateDTO.getIdSensor() );

        return sensorEntity;
    }

    @Override
    public void updateFromUpdateDTO(SensorResponseDTO updateDTO, SensorEntity entity) {
        if ( updateDTO == null ) {
            return;
        }

        entity.setIdSensor( updateDTO.getIdSensor() );
    }
}
