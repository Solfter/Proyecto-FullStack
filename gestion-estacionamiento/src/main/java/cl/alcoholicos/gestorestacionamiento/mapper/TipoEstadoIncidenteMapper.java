package cl.alcoholicos.gestorestacionamiento.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import cl.alcoholicos.gestorestacionamiento.dto.TipoEstadoIncidenteResponseDTO;
import cl.alcoholicos.gestorestacionamiento.entity.TipoEstadoIncidenteEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TipoEstadoIncidenteMapper {

    TipoEstadoIncidenteResponseDTO toResponseDTO(TipoEstadoIncidenteEntity sensorEntity);

}
