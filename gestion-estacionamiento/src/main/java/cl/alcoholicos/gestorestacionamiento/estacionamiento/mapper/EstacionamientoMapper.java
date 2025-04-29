package cl.alcoholicos.gestorestacionamiento.estacionamiento.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import cl.alcoholicos.gestorestacionamiento.estacionamiento.dto.EstacionamientoResponseDTO;
import cl.alcoholicos.gestorestacionamiento.estacionamiento.entity.EstacionamientoEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EstacionamientoMapper {
    EstacionamientoResponseDTO toResponseDTO(EstacionamientoEntity estacionamientoEntity);
}
