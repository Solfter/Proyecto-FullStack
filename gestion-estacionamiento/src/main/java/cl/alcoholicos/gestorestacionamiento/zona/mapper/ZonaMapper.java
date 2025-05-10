package cl.alcoholicos.gestorestacionamiento.zona.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import cl.alcoholicos.gestorestacionamiento.zona.dto.ZonaCreateDTO;
import cl.alcoholicos.gestorestacionamiento.zona.dto.ZonaResponseDTO;
import cl.alcoholicos.gestorestacionamiento.zona.dto.ZonaUpdateDTO;
import cl.alcoholicos.gestorestacionamiento.zona.entity.ZonaEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ZonaMapper {
    ZonaResponseDTO toResponseDTO(ZonaEntity zonaEntity);
    ZonaEntity toEntity(ZonaCreateDTO zonaCreateDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromUpdateDTO(ZonaUpdateDTO updateDTO, @MappingTarget ZonaEntity entity);
}
