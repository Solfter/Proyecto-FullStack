package cl.alcoholicos.gestorestacionamiento.mapper;

import org.mapstruct.Mapper;

import org.mapstruct.ReportingPolicy;

import cl.alcoholicos.gestorestacionamiento.dto.EstadoReservaResponseDTO;
import cl.alcoholicos.gestorestacionamiento.entity.EstadoReservaEntity;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EstadoReservaMapper {
    
    EstadoReservaResponseDTO toResponseDTO (EstadoReservaEntity estadoReservaEntity);

}