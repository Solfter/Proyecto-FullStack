package cl.alcoholicos.gestorestacionamiento.marca.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import cl.alcoholicos.gestorestacionamiento.marca.dto.MarcaCreateDTO;
import cl.alcoholicos.gestorestacionamiento.marca.dto.MarcaResponseDTO;
import cl.alcoholicos.gestorestacionamiento.marca.dto.MarcaUpdateDTO;
import cl.alcoholicos.gestorestacionamiento.marca.entity.MarcaEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MarcaMapper {
    MarcaResponseDTO toResponseDTO (MarcaEntity marcaEntity);
    MarcaEntity toEntity (MarcaCreateDTO marcaResponseDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromUpdateDTO(MarcaUpdateDTO updateDTO, @MappingTarget MarcaEntity marca);
}
