package cl.alcoholicos.gestorestacionamiento.mapper;

import cl.alcoholicos.gestorestacionamiento.dto.EstacionamientoBasicDTO;
import cl.alcoholicos.gestorestacionamiento.dto.ReservaCreateDTO;
import cl.alcoholicos.gestorestacionamiento.dto.ReservaResponseDTO;
import cl.alcoholicos.gestorestacionamiento.dto.UsuarioBasicDTO;
import cl.alcoholicos.gestorestacionamiento.entity.EstacionamientoEntity;
import cl.alcoholicos.gestorestacionamiento.entity.ReservaEntity;
import cl.alcoholicos.gestorestacionamiento.entity.UsuarioEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-09T23:08:04-0400",
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

        reservaResponseDTO.estacionamiento( estacionamientoEntityToEstacionamientoBasicDTO( reservaEntity.getEstacionamiento() ) );
        reservaResponseDTO.fechaCreacionReserva( reservaEntity.getFechaCreacionReserva() );
        reservaResponseDTO.fechaReserva( reservaEntity.getFechaReserva() );
        reservaResponseDTO.horaFin( reservaEntity.getHoraFin() );
        reservaResponseDTO.horaInicio( reservaEntity.getHoraInicio() );
        reservaResponseDTO.idReserva( reservaEntity.getIdReserva() );
        reservaResponseDTO.usuario( usuarioEntityToUsuarioBasicDTO( reservaEntity.getUsuario() ) );

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

        if ( updateDTO.getEstacionamiento() != null ) {
            if ( modelo.getEstacionamiento() == null ) {
                modelo.setEstacionamiento( new EstacionamientoEntity() );
            }
            estacionamientoBasicDTOToEstacionamientoEntity( updateDTO.getEstacionamiento(), modelo.getEstacionamiento() );
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
        if ( updateDTO.getUsuario() != null ) {
            if ( modelo.getUsuario() == null ) {
                modelo.setUsuario( new UsuarioEntity() );
            }
            usuarioBasicDTOToUsuarioEntity( updateDTO.getUsuario(), modelo.getUsuario() );
        }
    }

    protected EstacionamientoBasicDTO estacionamientoEntityToEstacionamientoBasicDTO(EstacionamientoEntity estacionamientoEntity) {
        if ( estacionamientoEntity == null ) {
            return null;
        }

        EstacionamientoBasicDTO estacionamientoBasicDTO = new EstacionamientoBasicDTO();

        estacionamientoBasicDTO.setNroEstacionamiento( estacionamientoEntity.getNroEstacionamiento() );

        return estacionamientoBasicDTO;
    }

    protected UsuarioBasicDTO usuarioEntityToUsuarioBasicDTO(UsuarioEntity usuarioEntity) {
        if ( usuarioEntity == null ) {
            return null;
        }

        UsuarioBasicDTO usuarioBasicDTO = new UsuarioBasicDTO();

        usuarioBasicDTO.setApMaterno( usuarioEntity.getApMaterno() );
        usuarioBasicDTO.setApPaterno( usuarioEntity.getApPaterno() );
        usuarioBasicDTO.setCorreo( usuarioEntity.getCorreo() );
        usuarioBasicDTO.setDv( usuarioEntity.getDv() );
        usuarioBasicDTO.setNroCelular( usuarioEntity.getNroCelular() );
        usuarioBasicDTO.setPrimerNombre( usuarioEntity.getPrimerNombre() );
        usuarioBasicDTO.setRut( usuarioEntity.getRut() );
        usuarioBasicDTO.setSegundoNombre( usuarioEntity.getSegundoNombre() );

        return usuarioBasicDTO;
    }

    protected void estacionamientoBasicDTOToEstacionamientoEntity(EstacionamientoBasicDTO estacionamientoBasicDTO, EstacionamientoEntity mappingTarget) {
        if ( estacionamientoBasicDTO == null ) {
            return;
        }

        mappingTarget.setNroEstacionamiento( estacionamientoBasicDTO.getNroEstacionamiento() );
    }

    protected void usuarioBasicDTOToUsuarioEntity(UsuarioBasicDTO usuarioBasicDTO, UsuarioEntity mappingTarget) {
        if ( usuarioBasicDTO == null ) {
            return;
        }

        if ( usuarioBasicDTO.getApMaterno() != null ) {
            mappingTarget.setApMaterno( usuarioBasicDTO.getApMaterno() );
        }
        if ( usuarioBasicDTO.getApPaterno() != null ) {
            mappingTarget.setApPaterno( usuarioBasicDTO.getApPaterno() );
        }
        if ( usuarioBasicDTO.getCorreo() != null ) {
            mappingTarget.setCorreo( usuarioBasicDTO.getCorreo() );
        }
        if ( usuarioBasicDTO.getDv() != null ) {
            mappingTarget.setDv( usuarioBasicDTO.getDv() );
        }
        mappingTarget.setNroCelular( usuarioBasicDTO.getNroCelular() );
        if ( usuarioBasicDTO.getPrimerNombre() != null ) {
            mappingTarget.setPrimerNombre( usuarioBasicDTO.getPrimerNombre() );
        }
        mappingTarget.setRut( usuarioBasicDTO.getRut() );
        if ( usuarioBasicDTO.getSegundoNombre() != null ) {
            mappingTarget.setSegundoNombre( usuarioBasicDTO.getSegundoNombre() );
        }
    }
}
