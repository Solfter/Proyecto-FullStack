package cl.alcoholicos.gestorestacionamiento.mapper;

import cl.alcoholicos.gestorestacionamiento.dto.TipoEstadoReservaCreateDTO;
import cl.alcoholicos.gestorestacionamiento.dto.TipoEstadoReservaResponseDTO;
import cl.alcoholicos.gestorestacionamiento.entity.TipoEstadoReservaEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-29T10:02:44-0400",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.42.50.v20250624-0847, environment: Java 21.0.7 (Eclipse Adoptium)"
)
@Component
public class TipoEstadoReservaMapperImpl implements TipoEstadoReservaMapper {

    @Override
    public TipoEstadoReservaResponseDTO toResponseDTO(TipoEstadoReservaEntity tipoEstadoReservaEntity) {
        if ( tipoEstadoReservaEntity == null ) {
            return null;
        }

        TipoEstadoReservaResponseDTO tipoEstadoReservaResponseDTO = new TipoEstadoReservaResponseDTO();

        tipoEstadoReservaResponseDTO.setDescTipoEstadoReserva( tipoEstadoReservaEntity.getDescTipoEstadoReserva() );
        tipoEstadoReservaResponseDTO.setIdTipoEstadoReserva( tipoEstadoReservaEntity.getIdTipoEstadoReserva() );

        return tipoEstadoReservaResponseDTO;
    }

    @Override
    public TipoEstadoReservaEntity toEntity(TipoEstadoReservaCreateDTO tipoEstadoReservaCreateDTO) {
        if ( tipoEstadoReservaCreateDTO == null ) {
            return null;
        }

        TipoEstadoReservaEntity tipoEstadoReservaEntity = new TipoEstadoReservaEntity();

        tipoEstadoReservaEntity.setDescTipoEstadoReserva( tipoEstadoReservaCreateDTO.getDescTipoEstadoReserva() );

        return tipoEstadoReservaEntity;
    }
}
