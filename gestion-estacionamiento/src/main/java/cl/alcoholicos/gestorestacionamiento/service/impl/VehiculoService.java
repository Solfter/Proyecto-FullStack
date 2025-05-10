package cl.alcoholicos.gestorestacionamiento.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import cl.alcoholicos.gestorestacionamiento.dto.VehiculoCreateDTO;
import cl.alcoholicos.gestorestacionamiento.dto.VehiculoResponseDTO;
import cl.alcoholicos.gestorestacionamiento.dto.VehiculoUpdateDTO;
import cl.alcoholicos.gestorestacionamiento.entity.VehiculoEntity;
import cl.alcoholicos.gestorestacionamiento.mapper.VehiculoMapper;
import cl.alcoholicos.gestorestacionamiento.repository.VehiculoRepository;
import cl.alcoholicos.gestorestacionamiento.service.IVehiculo;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VehiculoService implements IVehiculo {
    private final VehiculoRepository vehiculoRepository;
    private final VehiculoMapper vehiculoMapper;

    @Override
    public VehiculoResponseDTO insert(VehiculoCreateDTO vehiculoCreateDTO) {
        VehiculoEntity vehiculo = vehiculoMapper.toEntity(vehiculoCreateDTO); // Entidad
        VehiculoEntity vehiculoGuardado = vehiculoRepository.save(vehiculo); // Para guardar a la BD
        VehiculoResponseDTO responseDTO = vehiculoMapper.toResponseDTO(vehiculoGuardado); // DTO
        return responseDTO;
    }

    @Override
    public VehiculoResponseDTO update(String patente, VehiculoUpdateDTO vehiculoUpdateDTO) {
        return vehiculoRepository.findById(patente)
                .map(vehiculoExistente -> {
                    vehiculoMapper.updateFromUpdateDTO(vehiculoUpdateDTO, vehiculoExistente);

                    VehiculoEntity vehiculoActualizado = vehiculoRepository.save(vehiculoExistente);

                    return vehiculoMapper.toResponseDTO(vehiculoActualizado);
                })
                .orElse(null);
    }

    @Override
    public boolean delete(String patente) {
        if (vehiculoRepository.existsById(patente)) {
            vehiculoRepository.deleteById(patente);
            return true;
        }
        return false;
    }

    @Override
    public VehiculoResponseDTO getById(String patente) {
        return vehiculoRepository.findById(patente)
                .map(vehiculoMapper::toResponseDTO)
                .orElse(null);
    }

    @Override
    public List<VehiculoResponseDTO> getAll() {
        return vehiculoRepository.findAll().stream()
                .map(vehiculoMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

}
