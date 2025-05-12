package cl.alcoholicos.gestorestacionamiento.mapper;

import cl.alcoholicos.gestorestacionamiento.dto.ZonaCreateDTO;
import cl.alcoholicos.gestorestacionamiento.dto.ZonaResponseDTO;
import cl.alcoholicos.gestorestacionamiento.dto.ZonaUpdateDTO;
import cl.alcoholicos.gestorestacionamiento.entity.ZonaEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-12T18:39:23-0400",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.42.0.z20250331-1358, environment: Java 21.0.6 (Eclipse Adoptium)"
)
@Component
public class ZonaMapperImpl implements ZonaMapper {

    @Override
    public ZonaResponseDTO toResponseDTO(ZonaEntity zonaEntity) {
        if ( zonaEntity == null ) {
            return null;
        }

        ZonaResponseDTO.ZonaResponseDTOBuilder zonaResponseDTO = ZonaResponseDTO.builder();

        zonaResponseDTO.capacidad( zonaEntity.getCapacidad() );
        zonaResponseDTO.descripcion( zonaEntity.getDescripcion() );
        zonaResponseDTO.idZona( zonaEntity.getIdZona() );
        zonaResponseDTO.nombreZona( zonaEntity.getNombreZona() );

        return zonaResponseDTO.build();
    }

    @Override
    public ZonaEntity toEntity(ZonaCreateDTO zonaCreateDTO) {
        if ( zonaCreateDTO == null ) {
            return null;
        }

        ZonaEntity zonaEntity = new ZonaEntity();

        zonaEntity.setCapacidad( zonaCreateDTO.getCapacidad() );
        zonaEntity.setDescripcion( zonaCreateDTO.getDescripcion() );
        zonaEntity.setIdZona( zonaCreateDTO.getIdZona() );
        zonaEntity.setNombreZona( zonaCreateDTO.getNombreZona() );

        return zonaEntity;
    }

    @Override
    public void updateFromUpdateDTO(ZonaUpdateDTO updateDTO, ZonaEntity entity) {
        if ( updateDTO == null ) {
            return;
        }

        entity.setCapacidad( updateDTO.getCapacidad() );
        if ( updateDTO.getDescripcion() != null ) {
            entity.setDescripcion( updateDTO.getDescripcion() );
        }
        if ( updateDTO.getNombreZona() != null ) {
            entity.setNombreZona( updateDTO.getNombreZona() );
        }
    }
}
