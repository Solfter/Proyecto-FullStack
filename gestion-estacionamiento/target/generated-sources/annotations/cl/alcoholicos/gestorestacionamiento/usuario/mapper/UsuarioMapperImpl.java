package cl.alcoholicos.gestorestacionamiento.usuario.mapper;

import cl.alcoholicos.gestorestacionamiento.usuario.dto.UsuarioCreateDTO;
import cl.alcoholicos.gestorestacionamiento.usuario.dto.UsuarioResponseDTO;
import cl.alcoholicos.gestorestacionamiento.usuario.dto.UsuarioUpdateDTO;
import cl.alcoholicos.gestorestacionamiento.usuario.entity.UsuarioEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-27T18:39:38-0400",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.42.0.z20250331-1358, environment: Java 21.0.6 (Eclipse Adoptium)"
)
@Component
public class UsuarioMapperImpl implements UsuarioMapper {

    @Override
    public UsuarioResponseDTO toResponseDTO(UsuarioEntity usuario) {
        if ( usuario == null ) {
            return null;
        }

        UsuarioResponseDTO.UsuarioResponseDTOBuilder usuarioResponseDTO = UsuarioResponseDTO.builder();

        usuarioResponseDTO.apMaterno( usuario.getApMaterno() );
        usuarioResponseDTO.apPaterno( usuario.getApPaterno() );
        usuarioResponseDTO.correo( usuario.getCorreo() );
        usuarioResponseDTO.dv( usuario.getDv() );
        usuarioResponseDTO.nroCelular( usuario.getNroCelular() );
        usuarioResponseDTO.primerNombre( usuario.getPrimerNombre() );
        usuarioResponseDTO.rut( usuario.getRut() );
        usuarioResponseDTO.segundoNombre( usuario.getSegundoNombre() );

        return usuarioResponseDTO.build();
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
        if ( updateDTO.getNroCelular() != null ) {
            entity.setNroCelular( updateDTO.getNroCelular() );
        }
    }
}
