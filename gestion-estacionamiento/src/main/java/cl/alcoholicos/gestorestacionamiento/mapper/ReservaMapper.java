package cl.alcoholicos.gestorestacionamiento.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import cl.alcoholicos.gestorestacionamiento.dto.ReservaBasicDTO;
import cl.alcoholicos.gestorestacionamiento.dto.ReservaCreateDTO;
import cl.alcoholicos.gestorestacionamiento.dto.ReservaResponseDTO;
import cl.alcoholicos.gestorestacionamiento.entity.ReservaEntity;

@Mapper(componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ReservaMapper {
    ReservaResponseDTO toResponseDTO (ReservaEntity reservaEntity);
    ReservaEntity toEntity (ReservaCreateDTO reserva);
    ReservaBasicDTO toBasicDTO(ReservaEntity reservaEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromUpdateDTO(ReservaResponseDTO updateDTO, @MappingTarget ReservaEntity modelo);
}