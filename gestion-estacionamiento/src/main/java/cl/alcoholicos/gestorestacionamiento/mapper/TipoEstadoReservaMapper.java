package cl.alcoholicos.gestorestacionamiento.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import cl.alcoholicos.gestorestacionamiento.dto.TipoEstadoReservaBasicDTO;
import cl.alcoholicos.gestorestacionamiento.dto.TipoEstadoReservaResponseDTO;
import cl.alcoholicos.gestorestacionamiento.entity.TipoEstadoReservaEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TipoEstadoReservaMapper {
    
    TipoEstadoReservaResponseDTO toResponseDTO(TipoEstadoReservaEntity tipoEstadoReservaEntity);

    TipoEstadoReservaBasicDTO toBasicDTO(TipoEstadoReservaEntity tipoEstadoReservaEntity); 

}
