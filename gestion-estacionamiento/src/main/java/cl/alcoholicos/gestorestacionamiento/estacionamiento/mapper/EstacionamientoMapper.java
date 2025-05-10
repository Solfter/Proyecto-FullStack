package cl.alcoholicos.gestorestacionamiento.estacionamiento.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import cl.alcoholicos.gestorestacionamiento.estacionamiento.dto.EstacionamientoCreateDTO;
import cl.alcoholicos.gestorestacionamiento.estacionamiento.dto.EstacionamientoResponseDTO;
import cl.alcoholicos.gestorestacionamiento.estacionamiento.dto.EstacionamientoUpdateDTO;
import cl.alcoholicos.gestorestacionamiento.estacionamiento.entity.EstacionamientoEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EstacionamientoMapper {
    EstacionamientoResponseDTO toResponseDTO(EstacionamientoEntity estacionamientoEntity);
    EstacionamientoEntity toEntity(EstacionamientoCreateDTO estacionamiento);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromUpdateDTO(EstacionamientoUpdateDTO updateDTO, @MappingTarget EstacionamientoEntity entity);
}
