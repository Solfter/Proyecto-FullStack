package cl.alcoholicos.gestorestacionamiento.mapper;

import cl.alcoholicos.gestorestacionamiento.dto.UsuarioCreateDTO;
import cl.alcoholicos.gestorestacionamiento.dto.UsuarioResponseDTO;
import cl.alcoholicos.gestorestacionamiento.dto.UsuarioUpdateDTO;
import cl.alcoholicos.gestorestacionamiento.entity.UsuarioEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-12T19:01:25-0400",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.42.0.z20250331-1358, environment: Java 21.0.6 (Eclipse Adoptium)"
)
@Component
public class UsuarioMapperImpl implements UsuarioMapper {

    @Override
    public UsuarioResponseDTO toResponseDTO(UsuarioEntity usuarioEntity) {
        if ( usuarioEntity == null ) {
            return null;
        }

        UsuarioResponseDTO usuarioResponseDTO = new UsuarioResponseDTO();

        usuarioResponseDTO.setApMaterno( usuarioEntity.getApMaterno() );
        usuarioResponseDTO.setApPaterno( usuarioEntity.getApPaterno() );
        usuarioResponseDTO.setCorreo( usuarioEntity.getCorreo() );
        usuarioResponseDTO.setDv( usuarioEntity.getDv() );
        usuarioResponseDTO.setNroCelular( usuarioEntity.getNroCelular() );
        usuarioResponseDTO.setPassword( usuarioEntity.getPassword() );
        usuarioResponseDTO.setPrimerNombre( usuarioEntity.getPrimerNombre() );
        usuarioResponseDTO.setRut( usuarioEntity.getRut() );
        usuarioResponseDTO.setSegundoNombre( usuarioEntity.getSegundoNombre() );

        return usuarioResponseDTO;
    }

    @Override
    public UsuarioEntity toEntity(UsuarioCreateDTO usuarioCreateDTO) {
        if ( usuarioCreateDTO == null ) {
            return null;
        }

        UsuarioEntity usuarioEntity = new UsuarioEntity();

        usuarioEntity.setApMaterno( usuarioCreateDTO.getApMaterno() );
        usuarioEntity.setApPaterno( usuarioCreateDTO.getApPaterno() );
        usuarioEntity.setCorreo( usuarioCreateDTO.getCorreo() );
        usuarioEntity.setDv( usuarioCreateDTO.getDv() );
        usuarioEntity.setNroCelular( usuarioCreateDTO.getNroCelular() );
        usuarioEntity.setPassword( usuarioCreateDTO.getPassword() );
        usuarioEntity.setPrimerNombre( usuarioCreateDTO.getPrimerNombre() );
        usuarioEntity.setRut( usuarioCreateDTO.getRut() );
        usuarioEntity.setSegundoNombre( usuarioCreateDTO.getSegundoNombre() );

        return usuarioEntity;
    }

    @Override
    public void updateFromUpdateDTO(UsuarioUpdateDTO updateDTO, UsuarioEntity entity) {
        if ( updateDTO == null ) {
            return;
        }

        if ( updateDTO.getApMaterno() != null ) {
            entity.setApMaterno( updateDTO.getApMaterno() );
        }
        if ( updateDTO.getApPaterno() != null ) {
            entity.setApPaterno( updateDTO.getApPaterno() );
        }
        if ( updateDTO.getCorreo() != null ) {
            entity.setCorreo( updateDTO.getCorreo() );
        }
        entity.setNroCelular( updateDTO.getNroCelular() );
        if ( updateDTO.getPassword() != null ) {
            entity.setPassword( updateDTO.getPassword() );
        }
        if ( updateDTO.getPrimerNombre() != null ) {
            entity.setPrimerNombre( updateDTO.getPrimerNombre() );
        }
        if ( updateDTO.getSegundoNombre() != null ) {
            entity.setSegundoNombre( updateDTO.getSegundoNombre() );
        }
    }
}
