package cl.alcoholicos.gestorestacionamiento.mapper;

import cl.alcoholicos.gestorestacionamiento.dto.EspacioFavoritoResponseDTO;
import cl.alcoholicos.gestorestacionamiento.dto.EstacionamientoBasicDTO;
import cl.alcoholicos.gestorestacionamiento.dto.EstadoReservaBasicDTO;
import cl.alcoholicos.gestorestacionamiento.dto.IncidenteResponseDTO;
import cl.alcoholicos.gestorestacionamiento.dto.MarcaResponseDTO;
import cl.alcoholicos.gestorestacionamiento.dto.ModeloResponseDTO;
import cl.alcoholicos.gestorestacionamiento.dto.ReservaResponseDTO;
import cl.alcoholicos.gestorestacionamiento.dto.TipoEstadoReservaBasicDTO;
import cl.alcoholicos.gestorestacionamiento.dto.TipoUsuarioResponseDTO;
import cl.alcoholicos.gestorestacionamiento.dto.UsuarioBasicDTO;
import cl.alcoholicos.gestorestacionamiento.dto.UsuarioCreateDTO;
import cl.alcoholicos.gestorestacionamiento.dto.UsuarioResponseDTO;
import cl.alcoholicos.gestorestacionamiento.dto.UsuarioUpdateDTO;
import cl.alcoholicos.gestorestacionamiento.dto.VehiculoResponseDTO;
import cl.alcoholicos.gestorestacionamiento.entity.EspacioFavoritoEntity;
import cl.alcoholicos.gestorestacionamiento.entity.EstacionamientoEntity;
import cl.alcoholicos.gestorestacionamiento.entity.EstadoReservaEntity;
import cl.alcoholicos.gestorestacionamiento.entity.IncidenteEntity;
import cl.alcoholicos.gestorestacionamiento.entity.MarcaEntity;
import cl.alcoholicos.gestorestacionamiento.entity.ModeloEntity;
import cl.alcoholicos.gestorestacionamiento.entity.ReservaEntity;
import cl.alcoholicos.gestorestacionamiento.entity.TipoEstadoReservaEntity;
import cl.alcoholicos.gestorestacionamiento.entity.TipoUsuarioEntity;
import cl.alcoholicos.gestorestacionamiento.entity.UsuarioEntity;
import cl.alcoholicos.gestorestacionamiento.entity.VehiculoEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-01T21:51:46-0400",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.42.50.v20250624-0847, environment: Java 21.0.7 (Eclipse Adoptium)"
)
@Component
public class UsuarioMapperImpl implements UsuarioMapper {

    @Override
    public UsuarioResponseDTO toResponseDTO(UsuarioEntity usuarioEntity) {
        if ( usuarioEntity == null ) {
            return null;
        }

        UsuarioResponseDTO usuarioResponseDTO = new UsuarioResponseDTO();

        usuarioResponseDTO.setApMaterno( usuarioEntity.getApMaterno() );
        usuarioResponseDTO.setApPaterno( usuarioEntity.getApPaterno() );
        usuarioResponseDTO.setCorreo( usuarioEntity.getCorreo() );
        usuarioResponseDTO.setDv( usuarioEntity.getDv() );
        usuarioResponseDTO.setEspaciosFavoritos( espacioFavoritoEntityListToEspacioFavoritoResponseDTOList( usuarioEntity.getEspaciosFavoritos() ) );
        usuarioResponseDTO.setIncidentes( incidenteEntityListToIncidenteResponseDTOList( usuarioEntity.getIncidentes() ) );
        usuarioResponseDTO.setNroCelular( usuarioEntity.getNroCelular() );
        usuarioResponseDTO.setPassword( usuarioEntity.getPassword() );
        usuarioResponseDTO.setPrimerNombre( usuarioEntity.getPrimerNombre() );
        usuarioResponseDTO.setReservas( reservaEntityListToReservaResponseDTOList( usuarioEntity.getReservas() ) );
        usuarioResponseDTO.setRut( usuarioEntity.getRut() );
        usuarioResponseDTO.setSegundoNombre( usuarioEntity.getSegundoNombre() );
        usuarioResponseDTO.setTipoUsuario( tipoUsuarioEntityToTipoUsuarioResponseDTO( usuarioEntity.getTipoUsuario() ) );
        usuarioResponseDTO.setVehiculos( vehiculoEntityListToVehiculoResponseDTOList( usuarioEntity.getVehiculos() ) );

        return usuarioResponseDTO;
    }

    @Override
    public UsuarioEntity toEntity(UsuarioCreateDTO usuarioCreateDTO) {
        if ( usuarioCreateDTO == null ) {
            return null;
        }

        UsuarioEntity usuarioEntity = new UsuarioEntity();

        usuarioEntity.setApMaterno( usuarioCreateDTO.getApMaterno() );
        usuarioEntity.setApPaterno( usuarioCreateDTO.getApPaterno() );
        usuarioEntity.setCorreo( usuarioCreateDTO.getCorreo() );
        usuarioEntity.setDv( usuarioCreateDTO.getDv() );
        usuarioEntity.setNroCelular( usuarioCreateDTO.getNroCelular() );
        usuarioEntity.setPassword( usuarioCreateDTO.getPassword() );
        usuarioEntity.setPrimerNombre( usuarioCreateDTO.getPrimerNombre() );
        usuarioEntity.setRut( usuarioCreateDTO.getRut() );
        usuarioEntity.setSegundoNombre( usuarioCreateDTO.getSegundoNombre() );

        return usuarioEntity;
    }

    @Override
    public void updateFromUpdateDTO(UsuarioUpdateDTO updateDTO, UsuarioEntity entity) {
        if ( updateDTO == null ) {
            return;
        }

        if ( updateDTO.getApMaterno() != null ) {
            entity.setApMaterno( updateDTO.getApMaterno() );
        }
        if ( updateDTO.getApPaterno() != null ) {
            entity.setApPaterno( updateDTO.getApPaterno() );
        }
        if ( updateDTO.getCorreo() != null ) {
            entity.setCorreo( updateDTO.getCorreo() );
        }
        entity.setNroCelular( updateDTO.getNroCelular() );
        if ( updateDTO.getPassword() != null ) {
            entity.setPassword( updateDTO.getPassword() );
        }
        if ( updateDTO.getPrimerNombre() != null ) {
            entity.setPrimerNombre( updateDTO.getPrimerNombre() );
        }
        if ( updateDTO.getSegundoNombre() != null ) {
            entity.setSegundoNombre( updateDTO.getSegundoNombre() );
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

    protected EspacioFavoritoResponseDTO espacioFavoritoEntityToEspacioFavoritoResponseDTO(EspacioFavoritoEntity espacioFavoritoEntity) {
        if ( espacioFavoritoEntity == null ) {
            return null;
        }

        EspacioFavoritoResponseDTO.EspacioFavoritoResponseDTOBuilder espacioFavoritoResponseDTO = EspacioFavoritoResponseDTO.builder();

        espacioFavoritoResponseDTO.estacionamiento( estacionamientoEntityToEstacionamientoBasicDTO( espacioFavoritoEntity.getEstacionamiento() ) );
        espacioFavoritoResponseDTO.idEspacioFavorito( espacioFavoritoEntity.getIdEspacioFavorito() );
        espacioFavoritoResponseDTO.usuario( usuarioEntityToUsuarioBasicDTO( espacioFavoritoEntity.getUsuario() ) );

        return espacioFavoritoResponseDTO.build();
    }

    protected List<EspacioFavoritoResponseDTO> espacioFavoritoEntityListToEspacioFavoritoResponseDTOList(List<EspacioFavoritoEntity> list) {
        if ( list == null ) {
            return null;
        }

        List<EspacioFavoritoResponseDTO> list1 = new ArrayList<EspacioFavoritoResponseDTO>( list.size() );
        for ( EspacioFavoritoEntity espacioFavoritoEntity : list ) {
            list1.add( espacioFavoritoEntityToEspacioFavoritoResponseDTO( espacioFavoritoEntity ) );
        }

        return list1;
    }

    protected IncidenteResponseDTO incidenteEntityToIncidenteResponseDTO(IncidenteEntity incidenteEntity) {
        if ( incidenteEntity == null ) {
            return null;
        }

        IncidenteResponseDTO.IncidenteResponseDTOBuilder incidenteResponseDTO = IncidenteResponseDTO.builder();

        incidenteResponseDTO.descripcion( incidenteEntity.getDescripcion() );
        incidenteResponseDTO.fechaIncidente( incidenteEntity.getFechaIncidente() );
        incidenteResponseDTO.idIncidente( incidenteEntity.getIdIncidente() );

        return incidenteResponseDTO.build();
    }

    protected List<IncidenteResponseDTO> incidenteEntityListToIncidenteResponseDTOList(List<IncidenteEntity> list) {
        if ( list == null ) {
            return null;
        }

        List<IncidenteResponseDTO> list1 = new ArrayList<IncidenteResponseDTO>( list.size() );
        for ( IncidenteEntity incidenteEntity : list ) {
            list1.add( incidenteEntityToIncidenteResponseDTO( incidenteEntity ) );
        }

        return list1;
    }

    protected TipoEstadoReservaBasicDTO tipoEstadoReservaEntityToTipoEstadoReservaBasicDTO(TipoEstadoReservaEntity tipoEstadoReservaEntity) {
        if ( tipoEstadoReservaEntity == null ) {
            return null;
        }

        TipoEstadoReservaBasicDTO tipoEstadoReservaBasicDTO = new TipoEstadoReservaBasicDTO();

        tipoEstadoReservaBasicDTO.setDescTipoEstadoReserva( tipoEstadoReservaEntity.getDescTipoEstadoReserva() );

        return tipoEstadoReservaBasicDTO;
    }

    protected EstadoReservaBasicDTO estadoReservaEntityToEstadoReservaBasicDTO(EstadoReservaEntity estadoReservaEntity) {
        if ( estadoReservaEntity == null ) {
            return null;
        }

        EstadoReservaBasicDTO.EstadoReservaBasicDTOBuilder estadoReservaBasicDTO = EstadoReservaBasicDTO.builder();

        estadoReservaBasicDTO.fechaEstadoReserva( estadoReservaEntity.getFechaEstadoReserva() );
        estadoReservaBasicDTO.tipoEstadoReserva( tipoEstadoReservaEntityToTipoEstadoReservaBasicDTO( estadoReservaEntity.getTipoEstadoReserva() ) );

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

    protected ReservaResponseDTO reservaEntityToReservaResponseDTO(ReservaEntity reservaEntity) {
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

    protected List<ReservaResponseDTO> reservaEntityListToReservaResponseDTOList(List<ReservaEntity> list) {
        if ( list == null ) {
            return null;
        }

        List<ReservaResponseDTO> list1 = new ArrayList<ReservaResponseDTO>( list.size() );
        for ( ReservaEntity reservaEntity : list ) {
            list1.add( reservaEntityToReservaResponseDTO( reservaEntity ) );
        }

        return list1;
    }

    protected TipoUsuarioResponseDTO tipoUsuarioEntityToTipoUsuarioResponseDTO(TipoUsuarioEntity tipoUsuarioEntity) {
        if ( tipoUsuarioEntity == null ) {
            return null;
        }

        TipoUsuarioResponseDTO tipoUsuarioResponseDTO = new TipoUsuarioResponseDTO();

        tipoUsuarioResponseDTO.setDescTipoUsuario( tipoUsuarioEntity.getDescTipoUsuario() );
        tipoUsuarioResponseDTO.setIdTipoUsuario( tipoUsuarioEntity.getIdTipoUsuario() );

        return tipoUsuarioResponseDTO;
    }

    protected MarcaResponseDTO marcaEntityToMarcaResponseDTO(MarcaEntity marcaEntity) {
        if ( marcaEntity == null ) {
            return null;
        }

        MarcaResponseDTO marcaResponseDTO = new MarcaResponseDTO();

        marcaResponseDTO.setIdMarca( marcaEntity.getIdMarca() );
        marcaResponseDTO.setNombreMarca( marcaEntity.getNombreMarca() );

        return marcaResponseDTO;
    }

    protected ModeloResponseDTO modeloEntityToModeloResponseDTO(ModeloEntity modeloEntity) {
        if ( modeloEntity == null ) {
            return null;
        }

        ModeloResponseDTO modeloResponseDTO = new ModeloResponseDTO();

        modeloResponseDTO.setIdModelo( modeloEntity.getIdModelo() );
        modeloResponseDTO.setMarca( marcaEntityToMarcaResponseDTO( modeloEntity.getMarca() ) );
        modeloResponseDTO.setNombreModelo( modeloEntity.getNombreModelo() );

        return modeloResponseDTO;
    }

    protected VehiculoResponseDTO vehiculoEntityToVehiculoResponseDTO(VehiculoEntity vehiculoEntity) {
        if ( vehiculoEntity == null ) {
            return null;
        }

        VehiculoResponseDTO.VehiculoResponseDTOBuilder vehiculoResponseDTO = VehiculoResponseDTO.builder();

        vehiculoResponseDTO.anio( vehiculoEntity.getAnio() );
        vehiculoResponseDTO.color( vehiculoEntity.getColor() );
        vehiculoResponseDTO.modelo( modeloEntityToModeloResponseDTO( vehiculoEntity.getModelo() ) );
        vehiculoResponseDTO.patente( vehiculoEntity.getPatente() );

        return vehiculoResponseDTO.build();
    }

    protected List<VehiculoResponseDTO> vehiculoEntityListToVehiculoResponseDTOList(List<VehiculoEntity> list) {
        if ( list == null ) {
            return null;
        }

        List<VehiculoResponseDTO> list1 = new ArrayList<VehiculoResponseDTO>( list.size() );
        for ( VehiculoEntity vehiculoEntity : list ) {
            list1.add( vehiculoEntityToVehiculoResponseDTO( vehiculoEntity ) );
        }

        return list1;
    }
}
