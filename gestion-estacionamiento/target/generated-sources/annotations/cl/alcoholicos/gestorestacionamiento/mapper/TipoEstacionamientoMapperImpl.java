package cl.alcoholicos.gestorestacionamiento.mapper;

import cl.alcoholicos.gestorestacionamiento.dto.EstacionamientoResponseDTO;
import cl.alcoholicos.gestorestacionamiento.dto.EstadoEstacionamientoBasicDTO;
import cl.alcoholicos.gestorestacionamiento.dto.SensorBasicDTO;
import cl.alcoholicos.gestorestacionamiento.dto.TipoEstacionamientoBasicDTO;
import cl.alcoholicos.gestorestacionamiento.dto.TipoEstacionamientoCreateDTO;
import cl.alcoholicos.gestorestacionamiento.dto.TipoEstacionamientoResponseDTO;
import cl.alcoholicos.gestorestacionamiento.entity.EstacionamientoEntity;
import cl.alcoholicos.gestorestacionamiento.entity.EstadoEstacionamientoEntity;
import cl.alcoholicos.gestorestacionamiento.entity.SensorEntity;
import cl.alcoholicos.gestorestacionamiento.entity.TipoEstacionamientoEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-29T10:02:42-0400",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.42.50.v20250624-0847, environment: Java 21.0.7 (Eclipse Adoptium)"
)
@Component
public class TipoEstacionamientoMapperImpl implements TipoEstacionamientoMapper {

    @Override
    public TipoEstacionamientoResponseDTO toResponseDTO(TipoEstacionamientoEntity tipoEstacionamiento) {
        if ( tipoEstacionamiento == null ) {
            return null;
        }

        TipoEstacionamientoResponseDTO tipoEstacionamientoResponseDTO = new TipoEstacionamientoResponseDTO();

        tipoEstacionamientoResponseDTO.setDescTipoEstacionamiento( tipoEstacionamiento.getDescTipoEstacionamiento() );
        tipoEstacionamientoResponseDTO.setEstacionamientos( estacionamientoEntityListToEstacionamientoResponseDTOList( tipoEstacionamiento.getEstacionamientos() ) );
        tipoEstacionamientoResponseDTO.setIdTipoEstacionamiento( tipoEstacionamiento.getIdTipoEstacionamiento() );

        return tipoEstacionamientoResponseDTO;
    }

    @Override
    public TipoEstacionamientoEntity toEntity(TipoEstacionamientoCreateDTO createDTO) {
        if ( createDTO == null ) {
            return null;
        }

        TipoEstacionamientoEntity tipoEstacionamientoEntity = new TipoEstacionamientoEntity();

        tipoEstacionamientoEntity.setDescTipoEstacionamiento( createDTO.getDescTipoEstacionamiento() );

        return tipoEstacionamientoEntity;
    }

    @Override
    public TipoEstacionamientoBasicDTO toBasicDTO(TipoEstacionamientoEntity entity) {
        if ( entity == null ) {
            return null;
        }

        TipoEstacionamientoBasicDTO tipoEstacionamientoBasicDTO = new TipoEstacionamientoBasicDTO();

        tipoEstacionamientoBasicDTO.setDescTipoEstacionamiento( entity.getDescTipoEstacionamiento() );

        return tipoEstacionamientoBasicDTO;
    }

    protected EstadoEstacionamientoBasicDTO estadoEstacionamientoEntityToEstadoEstacionamientoBasicDTO(EstadoEstacionamientoEntity estadoEstacionamientoEntity) {
        if ( estadoEstacionamientoEntity == null ) {
            return null;
        }

        EstadoEstacionamientoBasicDTO estadoEstacionamientoBasicDTO = new EstadoEstacionamientoBasicDTO();

        estadoEstacionamientoBasicDTO.setDescEstadoEstacionamiento( estadoEstacionamientoEntity.getDescEstadoEstacionamiento() );

        return estadoEstacionamientoBasicDTO;
    }

    protected SensorBasicDTO sensorEntityToSensorBasicDTO(SensorEntity sensorEntity) {
        if ( sensorEntity == null ) {
            return null;
        }

        SensorBasicDTO sensorBasicDTO = new SensorBasicDTO();

        sensorBasicDTO.setNroSensor( sensorEntity.getNroSensor() );

        return sensorBasicDTO;
    }

    protected EstacionamientoResponseDTO estacionamientoEntityToEstacionamientoResponseDTO(EstacionamientoEntity estacionamientoEntity) {
        if ( estacionamientoEntity == null ) {
            return null;
        }

        EstacionamientoResponseDTO.EstacionamientoResponseDTOBuilder estacionamientoResponseDTO = EstacionamientoResponseDTO.builder();

        estacionamientoResponseDTO.estadoEstacionamiento( estadoEstacionamientoEntityToEstadoEstacionamientoBasicDTO( estacionamientoEntity.getEstadoEstacionamiento() ) );
        estacionamientoResponseDTO.idEstacionamiento( estacionamientoEntity.getIdEstacionamiento() );
        estacionamientoResponseDTO.nroEstacionamiento( estacionamientoEntity.getNroEstacionamiento() );
        estacionamientoResponseDTO.sensor( sensorEntityToSensorBasicDTO( estacionamientoEntity.getSensor() ) );
        estacionamientoResponseDTO.tipoEstacionamiento( toBasicDTO( estacionamientoEntity.getTipoEstacionamiento() ) );

        return estacionamientoResponseDTO.build();
    }

    protected List<EstacionamientoResponseDTO> estacionamientoEntityListToEstacionamientoResponseDTOList(List<EstacionamientoEntity> list) {
        if ( list == null ) {
            return null;
        }

        List<EstacionamientoResponseDTO> list1 = new ArrayList<EstacionamientoResponseDTO>( list.size() );
        for ( EstacionamientoEntity estacionamientoEntity : list ) {
            list1.add( estacionamientoEntityToEstacionamientoResponseDTO( estacionamientoEntity ) );
        }

        return list1;
    }
}
