package cl.alcoholicos.gestorestacionamiento.mapper;

import cl.alcoholicos.gestorestacionamiento.dto.MarcaResponseDTO;
import cl.alcoholicos.gestorestacionamiento.dto.ModeloCreateDTO;
import cl.alcoholicos.gestorestacionamiento.dto.ModeloResponseDTO;
import cl.alcoholicos.gestorestacionamiento.entity.MarcaEntity;
import cl.alcoholicos.gestorestacionamiento.entity.ModeloEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-04T18:29:20-0400",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.42.50.v20250628-1110, environment: Java 21.0.7 (Eclipse Adoptium)"
)
@Component
public class ModeloMapperImpl implements ModeloMapper {

    @Override
    public ModeloResponseDTO toResponseDTO(ModeloEntity modeloEntity) {
        if ( modeloEntity == null ) {
            return null;
        }

        ModeloResponseDTO modeloResponseDTO = new ModeloResponseDTO();

        modeloResponseDTO.setIdModelo( modeloEntity.getIdModelo() );
        modeloResponseDTO.setMarca( marcaEntityToMarcaResponseDTO( modeloEntity.getMarca() ) );
        modeloResponseDTO.setNombreModelo( modeloEntity.getNombreModelo() );

        return modeloResponseDTO;
    }

    @Override
    public ModeloEntity toEntity(ModeloCreateDTO modelo) {
        if ( modelo == null ) {
            return null;
        }

        ModeloEntity modeloEntity = new ModeloEntity();

        modeloEntity.setNombreModelo( modelo.getNombreModelo() );

        return modeloEntity;
    }

    @Override
    public void updateFromUpdateDTO(ModeloResponseDTO updateDTO, ModeloEntity modelo) {
        if ( updateDTO == null ) {
            return;
        }

        modelo.setIdModelo( updateDTO.getIdModelo() );
        if ( updateDTO.getMarca() != null ) {
            if ( modelo.getMarca() == null ) {
                modelo.setMarca( new MarcaEntity() );
            }
            marcaResponseDTOToMarcaEntity( updateDTO.getMarca(), modelo.getMarca() );
        }
        if ( updateDTO.getNombreModelo() != null ) {
            modelo.setNombreModelo( updateDTO.getNombreModelo() );
        }
    }

    protected MarcaResponseDTO marcaEntityToMarcaResponseDTO(MarcaEntity marcaEntity) {
        if ( marcaEntity == null ) {
            return null;
        }

        MarcaResponseDTO marcaResponseDTO = new MarcaResponseDTO();

        marcaResponseDTO.setIdMarca( marcaEntity.getIdMarca() );
        marcaResponseDTO.setNombreMarca( marcaEntity.getNombreMarca() );

        return marcaResponseDTO;
    }

    protected void marcaResponseDTOToMarcaEntity(MarcaResponseDTO marcaResponseDTO, MarcaEntity mappingTarget) {
        if ( marcaResponseDTO == null ) {
            return;
        }

        mappingTarget.setIdMarca( marcaResponseDTO.getIdMarca() );
        if ( marcaResponseDTO.getNombreMarca() != null ) {
            mappingTarget.setNombreMarca( marcaResponseDTO.getNombreMarca() );
        }
    }
}
