package cl.alcoholicos.gestorestacionamiento.mapper;

import cl.alcoholicos.gestorestacionamiento.dto.TipoEstadoIncidenteResponseDTO;
import cl.alcoholicos.gestorestacionamiento.entity.TipoEstadoIncidenteEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-15T20:11:45-0400",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.42.0.v20250514-1000, environment: Java 21.0.7 (Eclipse Adoptium)"
)
@Component
public class TipoEstadoIncidenteMapperImpl implements TipoEstadoIncidenteMapper {

    @Override
    public TipoEstadoIncidenteResponseDTO toResponseDTO(TipoEstadoIncidenteEntity sensorEntity) {
        if ( sensorEntity == null ) {
            return null;
        }

        TipoEstadoIncidenteResponseDTO tipoEstadoIncidenteResponseDTO = new TipoEstadoIncidenteResponseDTO();

        tipoEstadoIncidenteResponseDTO.setDescTipoEstadoIncidente( sensorEntity.getDescTipoEstadoIncidente() );
        tipoEstadoIncidenteResponseDTO.setIdTipoEstadoIncidente( sensorEntity.getIdTipoEstadoIncidente() );

        return tipoEstadoIncidenteResponseDTO;
    }
}
