package cl.alcoholicos.gestorestacionamiento.mapper;

import cl.alcoholicos.gestorestacionamiento.dto.MarcaCreateDTO;
import cl.alcoholicos.gestorestacionamiento.dto.MarcaResponseDTO;
import cl.alcoholicos.gestorestacionamiento.dto.MarcaUpdateDTO;
import cl.alcoholicos.gestorestacionamiento.entity.MarcaEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-02T18:31:14-0400",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.42.0.v20250514-1000, environment: Java 21.0.7 (Eclipse Adoptium)"
)
@Component
public class MarcaMapperImpl implements MarcaMapper {

    @Override
    public MarcaResponseDTO toResponseDTO(MarcaEntity marcaEntity) {
        if ( marcaEntity == null ) {
            return null;
        }

        MarcaResponseDTO marcaResponseDTO = new MarcaResponseDTO();

        marcaResponseDTO.setIdMarca( marcaEntity.getIdMarca() );
        marcaResponseDTO.setNombreMarca( marcaEntity.getNombreMarca() );

        return marcaResponseDTO;
    }

    @Override
    public MarcaEntity toEntity(MarcaCreateDTO marcaCreateDTO) {
        if ( marcaCreateDTO == null ) {
            return null;
        }

        MarcaEntity marcaEntity = new MarcaEntity();

        marcaEntity.setNombreMarca( marcaCreateDTO.getNombreMarca() );

        return marcaEntity;
    }

    @Override
    public void updateFromUpdateDTO(MarcaUpdateDTO updateDTO, MarcaEntity marca) {
        if ( updateDTO == null ) {
            return;
        }

        if ( updateDTO.getNombreMarca() != null ) {
            marca.setNombreMarca( updateDTO.getNombreMarca() );
        }
    }
}
