package cl.alcoholicos.gestorestacionamiento.mapper;

import cl.alcoholicos.gestorestacionamiento.dto.IncidenteBasicDTO;
import cl.alcoholicos.gestorestacionamiento.dto.IncidenteCreateDTO;
import cl.alcoholicos.gestorestacionamiento.dto.IncidenteResponseDTO;
import cl.alcoholicos.gestorestacionamiento.entity.IncidenteEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-04T18:29:20-0400",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.42.50.v20250628-1110, environment: Java 21.0.7 (Eclipse Adoptium)"
)
@Component
public class IncidenteMapperImpl implements IncidenteMapper {

    @Override
    public IncidenteResponseDTO toResponseDTO(IncidenteEntity entity) {
        if ( entity == null ) {
            return null;
        }

        IncidenteResponseDTO.IncidenteResponseDTOBuilder incidenteResponseDTO = IncidenteResponseDTO.builder();

        incidenteResponseDTO.descripcion( entity.getDescripcion() );
        incidenteResponseDTO.fechaIncidente( entity.getFechaIncidente() );
        incidenteResponseDTO.idIncidente( entity.getIdIncidente() );

        return incidenteResponseDTO.build();
    }

    @Override
    public IncidenteEntity toEntity(IncidenteCreateDTO createDTO) {
        if ( createDTO == null ) {
            return null;
        }

        IncidenteEntity incidenteEntity = new IncidenteEntity();

        return incidenteEntity;
    }

    @Override
    public IncidenteBasicDTO toBasicDTO(IncidenteEntity BasicDTO) {
        if ( BasicDTO == null ) {
            return null;
        }

        IncidenteBasicDTO incidenteBasicDTO = new IncidenteBasicDTO();

        return incidenteBasicDTO;
    }
}
