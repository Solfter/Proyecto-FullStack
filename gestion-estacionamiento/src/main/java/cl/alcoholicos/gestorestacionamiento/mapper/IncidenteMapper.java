package cl.alcoholicos.gestorestacionamiento.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import cl.alcoholicos.gestorestacionamiento.dto.IncidenteBasicDTO;
import cl.alcoholicos.gestorestacionamiento.dto.IncidenteCreateDTO;
import cl.alcoholicos.gestorestacionamiento.dto.IncidenteResponseDTO;
import cl.alcoholicos.gestorestacionamiento.entity.IncidenteEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IncidenteMapper {
    IncidenteResponseDTO toResponseDTO(IncidenteEntity entity);
    IncidenteEntity toEntity(IncidenteCreateDTO createDTO);
    IncidenteBasicDTO toBasicDTO(IncidenteEntity BasicDTO);

}
