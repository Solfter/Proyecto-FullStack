package cl.alcoholicos.gestorestacionamiento.mapper;

import cl.alcoholicos.gestorestacionamiento.dto.EstacionamientoCreateDTO;
import cl.alcoholicos.gestorestacionamiento.dto.EstacionamientoResponseDTO;
import cl.alcoholicos.gestorestacionamiento.dto.EstacionamientoUpdateDTO;
import cl.alcoholicos.gestorestacionamiento.dto.EstadoEstacionamientoBasicDTO;
import cl.alcoholicos.gestorestacionamiento.entity.EstacionamientoEntity;
import cl.alcoholicos.gestorestacionamiento.entity.EstadoEstacionamientoEntity;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-01T21:56:16-0400",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.42.50.v20250628-1110, environment: Java 21.0.7 (Eclipse Adoptium)"
)
@Component
public class EstacionamientoMapperImpl implements EstacionamientoMapper {

    @Autowired
    private TipoEstacionamientoMapper tipoEstacionamientoMapper;

    @Override
    public EstacionamientoResponseDTO toResponseDTO(EstacionamientoEntity estacionamientoEntity) {
        if ( estacionamientoEntity == null ) {
            return null;
        }

        EstacionamientoResponseDTO.EstacionamientoResponseDTOBuilder estacionamientoResponseDTO = EstacionamientoResponseDTO.builder();

        estacionamientoResponseDTO.estadoEstacionamiento( estadoEstacionamientoEntityToEstadoEstacionamientoBasicDTO( estacionamientoEntity.getEstadoEstacionamiento() ) );
        estacionamientoResponseDTO.idEstacionamiento( estacionamientoEntity.getIdEstacionamiento() );
        estacionamientoResponseDTO.nroEstacionamiento( estacionamientoEntity.getNroEstacionamiento() );
        estacionamientoResponseDTO.tipoEstacionamiento( tipoEstacionamientoMapper.toBasicDTO( estacionamientoEntity.getTipoEstacionamiento() ) );

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
    }

    protected EstadoEstacionamientoBasicDTO estadoEstacionamientoEntityToEstadoEstacionamientoBasicDTO(EstadoEstacionamientoEntity estadoEstacionamientoEntity) {
        if ( estadoEstacionamientoEntity == null ) {
            return null;
        }

        EstadoEstacionamientoBasicDTO estadoEstacionamientoBasicDTO = new EstadoEstacionamientoBasicDTO();

        estadoEstacionamientoBasicDTO.setDescEstadoEstacionamiento( estadoEstacionamientoEntity.getDescEstadoEstacionamiento() );

        return estadoEstacionamientoBasicDTO;
    }
}
