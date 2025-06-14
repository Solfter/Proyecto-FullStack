package cl.alcoholicos.gestorestacionamiento.mapper;

import cl.alcoholicos.gestorestacionamiento.dto.EstacionamientoBasicDTO;
import cl.alcoholicos.gestorestacionamiento.dto.EstadoReservaBasicDTO;
import cl.alcoholicos.gestorestacionamiento.dto.ReservaBasicDTO;
import cl.alcoholicos.gestorestacionamiento.dto.ReservaCreateDTO;
import cl.alcoholicos.gestorestacionamiento.dto.ReservaResponseDTO;
import cl.alcoholicos.gestorestacionamiento.dto.UsuarioBasicDTO;
import cl.alcoholicos.gestorestacionamiento.entity.EstacionamientoEntity;
import cl.alcoholicos.gestorestacionamiento.entity.EstadoReservaEntity;
import cl.alcoholicos.gestorestacionamiento.entity.ReservaEntity;
import cl.alcoholicos.gestorestacionamiento.entity.UsuarioEntity;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-14T11:27:53-0400",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.42.0.v20250514-1000, environment: Java 21.0.7 (Eclipse Adoptium)"
)
@Component
public class ReservaMapperImpl implements ReservaMapper {

    private final DatatypeFactory datatypeFactory;

    public ReservaMapperImpl() {
        try {
            datatypeFactory = DatatypeFactory.newInstance();
        }
        catch ( DatatypeConfigurationException ex ) {
            throw new RuntimeException( ex );
        }
    }

    @Override
    public ReservaResponseDTO toResponseDTO(ReservaEntity reservaEntity) {
        if ( reservaEntity == null ) {
            return null;
        }

        ReservaResponseDTO.ReservaResponseDTOBuilder reservaResponseDTO = ReservaResponseDTO.builder();

        reservaResponseDTO.estacionamiento( estacionamientoEntityToEstacionamientoBasicDTO( reservaEntity.getEstacionamiento() ) );
        reservaResponseDTO.estadosReservas( estadoReservaEntityListToEstadoReservaBasicDTOList( reservaEntity.getEstadosReservas() ) );
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

        reservaEntity.setFechaCreacionReserva( xmlGregorianCalendarToLocalDate( localDateTimeToXmlGregorianCalendar( reserva.getFechaCreacionReserva() ) ) );
        reservaEntity.setFechaReserva( reserva.getFechaReserva() );
        reservaEntity.setHoraFin( reserva.getHoraFin() );
        reservaEntity.setHoraInicio( reserva.getHoraInicio() );

        return reservaEntity;
    }

    @Override
    public ReservaBasicDTO toBasicDTO(ReservaEntity reservaEntity) {
        if ( reservaEntity == null ) {
            return null;
        }

        ReservaBasicDTO reservaBasicDTO = new ReservaBasicDTO();

        reservaBasicDTO.setIdReserva( reservaEntity.getIdReserva() );

        return reservaBasicDTO;
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
        if ( modelo.getEstadosReservas() != null ) {
            List<EstadoReservaEntity> list = estadoReservaBasicDTOListToEstadoReservaEntityList( updateDTO.getEstadosReservas() );
            if ( list != null ) {
                modelo.getEstadosReservas().clear();
                modelo.getEstadosReservas().addAll( list );
            }
        }
        else {
            List<EstadoReservaEntity> list = estadoReservaBasicDTOListToEstadoReservaEntityList( updateDTO.getEstadosReservas() );
            if ( list != null ) {
                modelo.setEstadosReservas( list );
            }
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

    private XMLGregorianCalendar localDateToXmlGregorianCalendar( LocalDate localDate ) {
        if ( localDate == null ) {
            return null;
        }

        return datatypeFactory.newXMLGregorianCalendarDate(
            localDate.getYear(),
            localDate.getMonthValue(),
            localDate.getDayOfMonth(),
            DatatypeConstants.FIELD_UNDEFINED );
    }

    private XMLGregorianCalendar localDateTimeToXmlGregorianCalendar( LocalDateTime localDateTime ) {
        if ( localDateTime == null ) {
            return null;
        }

        return datatypeFactory.newXMLGregorianCalendar(
            localDateTime.getYear(),
            localDateTime.getMonthValue(),
            localDateTime.getDayOfMonth(),
            localDateTime.getHour(),
            localDateTime.getMinute(),
            localDateTime.getSecond(),
            localDateTime.get( ChronoField.MILLI_OF_SECOND ),
            DatatypeConstants.FIELD_UNDEFINED );
    }

    private static LocalDate xmlGregorianCalendarToLocalDate( XMLGregorianCalendar xcal ) {
        if ( xcal == null ) {
            return null;
        }

        return LocalDate.of( xcal.getYear(), xcal.getMonth(), xcal.getDay() );
    }

    private static LocalDateTime xmlGregorianCalendarToLocalDateTime( XMLGregorianCalendar xcal ) {
        if ( xcal == null ) {
            return null;
        }

        if ( xcal.getYear() != DatatypeConstants.FIELD_UNDEFINED
            && xcal.getMonth() != DatatypeConstants.FIELD_UNDEFINED
            && xcal.getDay() != DatatypeConstants.FIELD_UNDEFINED
            && xcal.getHour() != DatatypeConstants.FIELD_UNDEFINED
            && xcal.getMinute() != DatatypeConstants.FIELD_UNDEFINED
        ) {
            if ( xcal.getSecond() != DatatypeConstants.FIELD_UNDEFINED
                && xcal.getMillisecond() != DatatypeConstants.FIELD_UNDEFINED ) {
                return LocalDateTime.of(
                    xcal.getYear(),
                    xcal.getMonth(),
                    xcal.getDay(),
                    xcal.getHour(),
                    xcal.getMinute(),
                    xcal.getSecond(),
                    Duration.ofMillis( xcal.getMillisecond() ).getNano()
                );
            }
            else if ( xcal.getSecond() != DatatypeConstants.FIELD_UNDEFINED ) {
                return LocalDateTime.of(
                    xcal.getYear(),
                    xcal.getMonth(),
                    xcal.getDay(),
                    xcal.getHour(),
                    xcal.getMinute(),
                    xcal.getSecond()
                );
            }
            else {
                return LocalDateTime.of(
                    xcal.getYear(),
                    xcal.getMonth(),
                    xcal.getDay(),
                    xcal.getHour(),
                    xcal.getMinute()
                );
            }
        }
        return null;
    }

    protected EstacionamientoBasicDTO estacionamientoEntityToEstacionamientoBasicDTO(EstacionamientoEntity estacionamientoEntity) {
        if ( estacionamientoEntity == null ) {
            return null;
        }

        EstacionamientoBasicDTO estacionamientoBasicDTO = new EstacionamientoBasicDTO();

        estacionamientoBasicDTO.setNroEstacionamiento( estacionamientoEntity.getNroEstacionamiento() );

        return estacionamientoBasicDTO;
    }

    protected EstadoReservaBasicDTO estadoReservaEntityToEstadoReservaBasicDTO(EstadoReservaEntity estadoReservaEntity) {
        if ( estadoReservaEntity == null ) {
            return null;
        }

        EstadoReservaBasicDTO.EstadoReservaBasicDTOBuilder estadoReservaBasicDTO = EstadoReservaBasicDTO.builder();

        estadoReservaBasicDTO.fechaEstadoReserva( xmlGregorianCalendarToLocalDate( localDateTimeToXmlGregorianCalendar( estadoReservaEntity.getFechaEstadoReserva() ) ) );

        return estadoReservaBasicDTO.build();
    }

    protected List<EstadoReservaBasicDTO> estadoReservaEntityListToEstadoReservaBasicDTOList(List<EstadoReservaEntity> list) {
        if ( list == null ) {
            return null;
        }

        List<EstadoReservaBasicDTO> list1 = new ArrayList<EstadoReservaBasicDTO>( list.size() );
        for ( EstadoReservaEntity estadoReservaEntity : list ) {
            list1.add( estadoReservaEntityToEstadoReservaBasicDTO( estadoReservaEntity ) );
        }

        return list1;
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

    protected EstadoReservaEntity estadoReservaBasicDTOToEstadoReservaEntity(EstadoReservaBasicDTO estadoReservaBasicDTO) {
        if ( estadoReservaBasicDTO == null ) {
            return null;
        }

        EstadoReservaEntity estadoReservaEntity = new EstadoReservaEntity();

        estadoReservaEntity.setFechaEstadoReserva( xmlGregorianCalendarToLocalDateTime( localDateToXmlGregorianCalendar( estadoReservaBasicDTO.getFechaEstadoReserva() ) ) );

        return estadoReservaEntity;
    }

    protected List<EstadoReservaEntity> estadoReservaBasicDTOListToEstadoReservaEntityList(List<EstadoReservaBasicDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<EstadoReservaEntity> list1 = new ArrayList<EstadoReservaEntity>( list.size() );
        for ( EstadoReservaBasicDTO estadoReservaBasicDTO : list ) {
            list1.add( estadoReservaBasicDTOToEstadoReservaEntity( estadoReservaBasicDTO ) );
        }

        return list1;
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
