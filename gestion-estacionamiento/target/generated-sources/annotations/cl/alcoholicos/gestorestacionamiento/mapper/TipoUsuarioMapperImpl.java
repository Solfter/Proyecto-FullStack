package cl.alcoholicos.gestorestacionamiento.mapper;

import cl.alcoholicos.gestorestacionamiento.dto.TipoUsuarioResponseDTO;
import cl.alcoholicos.gestorestacionamiento.entity.TipoUsuarioEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-09T23:08:04-0400",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.42.0.v20250514-1000, environment: Java 21.0.7 (Eclipse Adoptium)"
)
@Component
public class TipoUsuarioMapperImpl implements TipoUsuarioMapper {

    @Override
    public TipoUsuarioResponseDTO toResponseDTO(TipoUsuarioEntity modeloEntity) {
        if ( modeloEntity == null ) {
            return null;
        }

        TipoUsuarioResponseDTO tipoUsuarioResponseDTO = new TipoUsuarioResponseDTO();

        tipoUsuarioResponseDTO.setDescTipoUsuario( modeloEntity.getDescTipoUsuario() );
        tipoUsuarioResponseDTO.setIdTipoUsuario( modeloEntity.getIdTipoUsuario() );

        return tipoUsuarioResponseDTO;
    }

    @Override
    public TipoUsuarioEntity toEntity(TipoUsuarioResponseDTO tipoUsuarioResponseDTO) {
        if ( tipoUsuarioResponseDTO == null ) {
            return null;
        }

        TipoUsuarioEntity tipoUsuarioEntity = new TipoUsuarioEntity();

        tipoUsuarioEntity.setDescTipoUsuario( tipoUsuarioResponseDTO.getDescTipoUsuario() );
        tipoUsuarioEntity.setIdTipoUsuario( tipoUsuarioResponseDTO.getIdTipoUsuario() );

        return tipoUsuarioEntity;
    }

    @Override
    public void updateFromUpdateDTO(TipoUsuarioResponseDTO updateDTO, TipoUsuarioEntity tipoUsuarioEntity) {
        if ( updateDTO == null ) {
            return;
        }

        if ( updateDTO.getDescTipoUsuario() != null ) {
            tipoUsuarioEntity.setDescTipoUsuario( updateDTO.getDescTipoUsuario() );
        }
        tipoUsuarioEntity.setIdTipoUsuario( updateDTO.getIdTipoUsuario() );
    }
}
