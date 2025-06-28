package cl.alcoholicos.gestorestacionamiento.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import cl.alcoholicos.gestorestacionamiento.dto.TipoEstadoReservaCreateDTO;
import cl.alcoholicos.gestorestacionamiento.dto.TipoEstadoReservaResponseDTO;
import cl.alcoholicos.gestorestacionamiento.entity.TipoEstadoReservaEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TipoEstadoReservaMapper {
    
    TipoEstadoReservaResponseDTO toResponseDTO(TipoEstadoReservaEntity tipoEstadoReservaEntity);

    TipoEstadoReservaEntity toEntity (TipoEstadoReservaCreateDTO tipoEstadoReservaCreateDTO);

}
