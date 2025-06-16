package cl.alcoholicos.gestorestacionamiento.mapper;

import cl.alcoholicos.gestorestacionamiento.dto.TipoEstadoReservaResponseDTO;
import cl.alcoholicos.gestorestacionamiento.entity.TipoEstadoReservaEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-15T20:27:32-0400",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.42.0.v20250514-1000, environment: Java 21.0.7 (Eclipse Adoptium)"
)
@Component
public class TipoEstadoReservaMapperImpl implements TipoEstadoReservaMapper {

    @Override
    public TipoEstadoReservaResponseDTO toResponseDTO(TipoEstadoReservaEntity tipoEstadoReservaEntity) {
        if ( tipoEstadoReservaEntity == null ) {
            return null;
        }

        TipoEstadoReservaResponseDTO.TipoEstadoReservaResponseDTOBuilder tipoEstadoReservaResponseDTO = TipoEstadoReservaResponseDTO.builder();

        tipoEstadoReservaResponseDTO.descTipoEstadoReserva( tipoEstadoReservaEntity.getDescTipoEstadoReserva() );
        tipoEstadoReservaResponseDTO.idTipoEstadoReserva( tipoEstadoReservaEntity.getIdTipoEstadoReserva() );

        return tipoEstadoReservaResponseDTO.build();
    }
}
