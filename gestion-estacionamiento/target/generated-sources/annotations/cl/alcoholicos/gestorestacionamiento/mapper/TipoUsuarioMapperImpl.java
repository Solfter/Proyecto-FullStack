package cl.alcoholicos.gestorestacionamiento.mapper;

import cl.alcoholicos.gestorestacionamiento.dto.TipoUsuarioCreateDTO;
import cl.alcoholicos.gestorestacionamiento.dto.TipoUsuarioResponseDTO;
import cl.alcoholicos.gestorestacionamiento.entity.TipoUsuarioEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-29T20:30:04-0400",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.42.50.v20250624-0847, environment: Java 21.0.7 (Eclipse Adoptium)"
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
    public TipoUsuarioEntity toEntity(TipoUsuarioCreateDTO tipoUsuarioCreateDTO) {
        if ( tipoUsuarioCreateDTO == null ) {
            return null;
        }

        TipoUsuarioEntity tipoUsuarioEntity = new TipoUsuarioEntity();

        tipoUsuarioEntity.setDescTipoUsuario( tipoUsuarioCreateDTO.getDescTipoUsuario() );

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
