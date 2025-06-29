package cl.alcoholicos.gestorestacionamiento.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import cl.alcoholicos.gestorestacionamiento.dto.TipoEstacionamientoBasicDTO;
import cl.alcoholicos.gestorestacionamiento.dto.TipoEstacionamientoCreateDTO;
import cl.alcoholicos.gestorestacionamiento.dto.TipoEstacionamientoResponseDTO;
import cl.alcoholicos.gestorestacionamiento.entity.TipoEstacionamientoEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TipoEstacionamientoMapper {

    TipoEstacionamientoResponseDTO toResponseDTO(TipoEstacionamientoEntity tipoEstacionamiento);

    TipoEstacionamientoEntity toEntity(TipoEstacionamientoCreateDTO createDTO);

    TipoEstacionamientoBasicDTO toBasicDTO(TipoEstacionamientoEntity entity);
    
}

