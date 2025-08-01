package cl.alcoholicos.gestorestacionamiento.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import cl.alcoholicos.gestorestacionamiento.dto.EstacionamientoCreateDTO;
import cl.alcoholicos.gestorestacionamiento.dto.EstacionamientoResponseDTO;
import cl.alcoholicos.gestorestacionamiento.dto.EstacionamientoUpdateDTO;
import cl.alcoholicos.gestorestacionamiento.entity.EstacionamientoEntity;

@Mapper(componentModel = "spring", uses = { SensorMapper.class,
        TipoEstacionamientoMapper.class }, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EstacionamientoMapper {
    @Mapping(target = "sensor", ignore = true)
    EstacionamientoResponseDTO toResponseDTO(EstacionamientoEntity estacionamientoEntity);

    EstacionamientoEntity toEntity(EstacionamientoCreateDTO estacionamiento);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromUpdateDTO(EstacionamientoUpdateDTO updateDTO, @MappingTarget EstacionamientoEntity entity);

}
