package cl.alcoholicos.gestorestacionamiento.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import cl.alcoholicos.gestorestacionamiento.dto.UsuarioCreateDTO;
import cl.alcoholicos.gestorestacionamiento.dto.UsuarioResponseDTO;
import cl.alcoholicos.gestorestacionamiento.dto.UsuarioUpdateDTO;
import cl.alcoholicos.gestorestacionamiento.entity.UsuarioEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {FeedbackMapper.class})
public interface UsuarioMapper {
    UsuarioResponseDTO toResponseDTO(UsuarioEntity usuarioEntity);
    UsuarioEntity toEntity(UsuarioCreateDTO usuarioCreateDTO);
    
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromUpdateDTO(UsuarioUpdateDTO updateDTO, @MappingTarget UsuarioEntity entity);
}