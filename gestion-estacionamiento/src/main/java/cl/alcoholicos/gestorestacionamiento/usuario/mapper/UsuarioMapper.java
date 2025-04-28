package cl.alcoholicos.gestorestacionamiento.usuario.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import cl.alcoholicos.gestorestacionamiento.usuario.dto.UsuarioCreateDTO;
import cl.alcoholicos.gestorestacionamiento.usuario.dto.UsuarioResponseDTO;
import cl.alcoholicos.gestorestacionamiento.usuario.dto.UsuarioUpdateDTO;
import cl.alcoholicos.gestorestacionamiento.usuario.entity.UsuarioEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UsuarioMapper {
    UsuarioResponseDTO toResponseDTO(UsuarioEntity usuario);
    UsuarioEntity toEntity(UsuarioCreateDTO usuarioCreateDTO);
    
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromUpdateDTO(UsuarioUpdateDTO updateDTO, @MappingTarget UsuarioEntity entity);
}