package cl.alcoholicos.gestorestacionamiento.mapper;

import cl.alcoholicos.gestorestacionamiento.dto.EstadoReservaResponseDTO;
import cl.alcoholicos.gestorestacionamiento.dto.ReservaBasicDTO;
import cl.alcoholicos.gestorestacionamiento.dto.TipoEstadoReservaBasicDTO;
import cl.alcoholicos.gestorestacionamiento.entity.EstadoReservaEntity;
import cl.alcoholicos.gestorestacionamiento.entity.ReservaEntity;
import cl.alcoholicos.gestorestacionamiento.entity.TipoEstadoReservaEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-05T11:15:23-0400",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.42.50.v20250628-1110, environment: Java 21.0.7 (Eclipse Adoptium)"
)
@Component
public class EstadoReservaMapperImpl implements EstadoReservaMapper {

    @Override
    public EstadoReservaResponseDTO toResponseDTO(EstadoReservaEntity estadoReservaEntity) {
        if ( estadoReservaEntity == null ) {
            return null;
        }

        EstadoReservaResponseDTO estadoReservaResponseDTO = new EstadoReservaResponseDTO();

        estadoReservaResponseDTO.setFechaEstadoReserva( estadoReservaEntity.getFechaEstadoReserva() );
        estadoReservaResponseDTO.setIdEstadoReserva( estadoReservaEntity.getIdEstadoReserva() );
        estadoReservaResponseDTO.setReserva( reservaEntityToReservaBasicDTO( estadoReservaEntity.getReserva() ) );
        estadoReservaResponseDTO.setTipoEstadoReserva( tipoEstadoReservaEntityToTipoEstadoReservaBasicDTO( estadoReservaEntity.getTipoEstadoReserva() ) );

        return estadoReservaResponseDTO;
    }

    protected ReservaBasicDTO reservaEntityToReservaBasicDTO(ReservaEntity reservaEntity) {
        if ( reservaEntity == null ) {
            return null;
        }

        ReservaBasicDTO reservaBasicDTO = new ReservaBasicDTO();

        reservaBasicDTO.setIdReserva( reservaEntity.getIdReserva() );

        return reservaBasicDTO;
    }

    protected TipoEstadoReservaBasicDTO tipoEstadoReservaEntityToTipoEstadoReservaBasicDTO(TipoEstadoReservaEntity tipoEstadoReservaEntity) {
        if ( tipoEstadoReservaEntity == null ) {
            return null;
        }

        TipoEstadoReservaBasicDTO tipoEstadoReservaBasicDTO = new TipoEstadoReservaBasicDTO();

        tipoEstadoReservaBasicDTO.setDescTipoEstadoReserva( tipoEstadoReservaEntity.getDescTipoEstadoReserva() );

        return tipoEstadoReservaBasicDTO;
    }
}
