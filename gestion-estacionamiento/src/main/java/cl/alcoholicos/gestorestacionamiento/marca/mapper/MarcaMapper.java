package cl.alcoholicos.gestorestacionamiento.marca.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import cl.alcoholicos.gestorestacionamiento.marca.dto.MarcaResponseDTO;
import cl.alcoholicos.gestorestacionamiento.marca.entity.MarcaEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MarcaMapper {
    MarcaResponseDTO toResponseDTO (MarcaEntity marcaEntity);
    MarcaEntity toEntity (MarcaResponseDTO marcaResponseDTO);
}
