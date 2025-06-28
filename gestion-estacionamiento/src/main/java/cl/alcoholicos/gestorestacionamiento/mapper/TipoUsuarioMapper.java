package cl.alcoholicos.gestorestacionamiento.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import cl.alcoholicos.gestorestacionamiento.dto.TipoUsuarioCreateDTO;
import cl.alcoholicos.gestorestacionamiento.dto.TipoUsuarioResponseDTO;
import cl.alcoholicos.gestorestacionamiento.entity.TipoUsuarioEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TipoUsuarioMapper {
    TipoUsuarioResponseDTO toResponseDTO (TipoUsuarioEntity modeloEntity);
    TipoUsuarioEntity toEntity (TipoUsuarioCreateDTO tipoUsuarioCreateDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromUpdateDTO(TipoUsuarioResponseDTO updateDTO, @MappingTarget TipoUsuarioEntity tipoUsuarioEntity);
}