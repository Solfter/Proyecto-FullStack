package cl.alcoholicos.gestorestacionamiento.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import cl.alcoholicos.gestorestacionamiento.dto.SensorBasicDTO;
import cl.alcoholicos.gestorestacionamiento.dto.SensorCreateDTO;
import cl.alcoholicos.gestorestacionamiento.dto.SensorResponseDTO;
import cl.alcoholicos.gestorestacionamiento.entity.SensorEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SensorMapper {
    
    @Mapping(target = "estacionamiento.sensor", ignore = true)
    SensorResponseDTO toResponseDTO(SensorEntity sensorEntity);
    SensorEntity toEntity(SensorCreateDTO sensorCreateDTO);

    SensorBasicDTO toBasicDTO(SensorEntity reservaEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromUpdateDTO(SensorResponseDTO updateDTO, @MappingTarget SensorEntity entity);
}
