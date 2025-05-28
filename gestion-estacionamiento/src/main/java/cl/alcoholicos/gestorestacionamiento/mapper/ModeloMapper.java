package cl.alcoholicos.gestorestacionamiento.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import cl.alcoholicos.gestorestacionamiento.dto.ModeloCreateDTO;
import cl.alcoholicos.gestorestacionamiento.dto.ModeloResponseDTO;
import cl.alcoholicos.gestorestacionamiento.entity.ModeloEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ModeloMapper {
    ModeloResponseDTO toResponseDTO (ModeloEntity modeloEntity);
    ModeloEntity toEntity (ModeloCreateDTO modelo);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromUpdateDTO(ModeloResponseDTO updateDTO, @MappingTarget ModeloEntity modelo);
}