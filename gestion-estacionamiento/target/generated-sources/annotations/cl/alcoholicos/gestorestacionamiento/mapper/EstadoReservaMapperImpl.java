package cl.alcoholicos.gestorestacionamiento.mapper;

import cl.alcoholicos.gestorestacionamiento.dto.EstadoReservaResponseDTO;
import cl.alcoholicos.gestorestacionamiento.dto.ReservaBasicDTO;
import cl.alcoholicos.gestorestacionamiento.entity.EstadoReservaEntity;
import cl.alcoholicos.gestorestacionamiento.entity.ReservaEntity;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-09T23:23:57-0400",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.42.0.v20250514-1000, environment: Java 21.0.7 (Eclipse Adoptium)"
)
@Component
public class EstadoReservaMapperImpl implements EstadoReservaMapper {

    @Autowired
    private TipoEstadoReservaMapper tipoEstadoReservaMapper;

    @Override
    public EstadoReservaResponseDTO toResponseDTO(EstadoReservaEntity marcaEntity) {
        if ( marcaEntity == null ) {
            return null;
        }

        EstadoReservaResponseDTO estadoReservaResponseDTO = new EstadoReservaResponseDTO();

        estadoReservaResponseDTO.setFechaEstadoReserva( marcaEntity.getFechaEstadoReserva() );
        estadoReservaResponseDTO.setIdEstadoReserva( marcaEntity.getIdEstadoReserva() );
        estadoReservaResponseDTO.setReserva( reservaEntityToReservaBasicDTO( marcaEntity.getReserva() ) );
        estadoReservaResponseDTO.setTipoEstadoReserva( tipoEstadoReservaMapper.toBasicDTO( marcaEntity.getTipoEstadoReserva() ) );

        return estadoReservaResponseDTO;
    }

    protected ReservaBasicDTO reservaEntityToReservaBasicDTO(ReservaEntity reservaEntity) {
        if ( reservaEntity == null ) {
            return null;
        }

        ReservaBasicDTO reservaBasicDTO = new ReservaBasicDTO();

        reservaBasicDTO.setFechaCreacionReserva( reservaEntity.getFechaCreacionReserva() );
        reservaBasicDTO.setFechaReserva( reservaEntity.getFechaReserva() );
        reservaBasicDTO.setHoraFin( reservaEntity.getHoraFin() );
        reservaBasicDTO.setHoraInicio( reservaEntity.getHoraInicio() );

        return reservaBasicDTO;
    }
}
