package cl.alcoholicos.gestorestacionamiento.mapper;

import cl.alcoholicos.gestorestacionamiento.dto.ReservaCreateDTO;
import cl.alcoholicos.gestorestacionamiento.dto.ReservaResponseDTO;
import cl.alcoholicos.gestorestacionamiento.entity.ReservaEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-02T18:31:15-0400",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.42.0.v20250514-1000, environment: Java 21.0.7 (Eclipse Adoptium)"
)
@Component
public class ReservaMapperImpl implements ReservaMapper {

    @Override
    public ReservaResponseDTO toResponseDTO(ReservaEntity reservaEntity) {
        if ( reservaEntity == null ) {
            return null;
        }

        ReservaResponseDTO.ReservaResponseDTOBuilder reservaResponseDTO = ReservaResponseDTO.builder();

        reservaResponseDTO.fechaCreacionReserva( reservaEntity.getFechaCreacionReserva() );
        reservaResponseDTO.fechaReserva( reservaEntity.getFechaReserva() );
        reservaResponseDTO.horaFin( reservaEntity.getHoraFin() );
        reservaResponseDTO.horaInicio( reservaEntity.getHoraInicio() );
        reservaResponseDTO.idReserva( reservaEntity.getIdReserva() );

        return reservaResponseDTO.build();
    }

    @Override
    public ReservaEntity toEntity(ReservaCreateDTO reserva) {
        if ( reserva == null ) {
            return null;
        }

        ReservaEntity reservaEntity = new ReservaEntity();

        reservaEntity.setFechaCreacionReserva( reserva.getFechaCreacionReserva() );
        reservaEntity.setFechaReserva( reserva.getFechaReserva() );
        reservaEntity.setHoraFin( reserva.getHoraFin() );
        reservaEntity.setHoraInicio( reserva.getHoraInicio() );

        return reservaEntity;
    }

    @Override
    public void updateFromUpdateDTO(ReservaResponseDTO updateDTO, ReservaEntity modelo) {
        if ( updateDTO == null ) {
            return;
        }

        if ( updateDTO.getFechaCreacionReserva() != null ) {
            modelo.setFechaCreacionReserva( updateDTO.getFechaCreacionReserva() );
        }
        if ( updateDTO.getFechaReserva() != null ) {
            modelo.setFechaReserva( updateDTO.getFechaReserva() );
        }
        if ( updateDTO.getHoraFin() != null ) {
            modelo.setHoraFin( updateDTO.getHoraFin() );
        }
        if ( updateDTO.getHoraInicio() != null ) {
            modelo.setHoraInicio( updateDTO.getHoraInicio() );
        }
        modelo.setIdReserva( updateDTO.getIdReserva() );
    }
}
