package cl.alcoholicos.gestorestacionamiento.marca.mapper;

import cl.alcoholicos.gestorestacionamiento.marca.dto.MarcaResponseDTO;
import cl.alcoholicos.gestorestacionamiento.marca.entity.MarcaEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-29T19:50:58-0400",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.42.0.z20250331-1358, environment: Java 21.0.6 (Eclipse Adoptium)"
)
@Component
public class MarcaMapperImpl implements MarcaMapper {

    @Override
    public MarcaResponseDTO toResponseDTO(MarcaEntity marcaEntity) {
        if ( marcaEntity == null ) {
            return null;
        }

        MarcaResponseDTO marcaResponseDTO = new MarcaResponseDTO();

        return marcaResponseDTO;
    }

    @Override
    public MarcaEntity toEntity(MarcaResponseDTO marcaResponseDTO) {
        if ( marcaResponseDTO == null ) {
            return null;
        }

        MarcaEntity marcaEntity = new MarcaEntity();

        return marcaEntity;
    }
}
