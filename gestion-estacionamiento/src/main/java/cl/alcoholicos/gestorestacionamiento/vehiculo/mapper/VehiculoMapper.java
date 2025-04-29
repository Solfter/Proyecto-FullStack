package cl.alcoholicos.gestorestacionamiento.vehiculo.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import cl.alcoholicos.gestorestacionamiento.vehiculo.dto.VehiculoCreateDTO;
import cl.alcoholicos.gestorestacionamiento.vehiculo.dto.VehiculoResponseDTO;
import cl.alcoholicos.gestorestacionamiento.vehiculo.dto.VehiculoUpdateDTO;
import cl.alcoholicos.gestorestacionamiento.vehiculo.entity.VehiculoEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface VehiculoMapper {
    VehiculoResponseDTO toResponseDTO(VehiculoEntity vehiculo);
    VehiculoEntity toEntity(VehiculoCreateDTO vehiculoCreateDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromUpdateDTO(VehiculoUpdateDTO updateDTO, @MappingTarget VehiculoEntity entity);
}
