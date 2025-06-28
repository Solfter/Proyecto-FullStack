package cl.alcoholicos.gestorestacionamiento.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import cl.alcoholicos.gestorestacionamiento.dto.VehiculoCreateDTO;
import cl.alcoholicos.gestorestacionamiento.dto.VehiculoResponseDTO;
import cl.alcoholicos.gestorestacionamiento.dto.VehiculoUpdateDTO;
import cl.alcoholicos.gestorestacionamiento.entity.ModeloEntity;
import cl.alcoholicos.gestorestacionamiento.entity.UsuarioEntity;
import cl.alcoholicos.gestorestacionamiento.entity.VehiculoEntity;
import cl.alcoholicos.gestorestacionamiento.mapper.VehiculoMapper;
import cl.alcoholicos.gestorestacionamiento.repository.ModeloRepository;
import cl.alcoholicos.gestorestacionamiento.repository.UsuarioRepository;
import cl.alcoholicos.gestorestacionamiento.repository.VehiculoRepository;
import cl.alcoholicos.gestorestacionamiento.service.IVehiculo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VehiculoService implements IVehiculo {
    private final VehiculoRepository vehiculoRepository;
    private final UsuarioRepository usuarioRepository;
    private final ModeloRepository modeloRepository;
    private final VehiculoMapper vehiculoMapper;

    @Override
    public VehiculoResponseDTO insert(VehiculoCreateDTO createDTO) {
        VehiculoEntity vehiculo = vehiculoMapper.toEntity(createDTO); // DTO a Entidad

        UsuarioEntity usuario = usuarioRepository.findById(createDTO.getRutUsuario())
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));

        ModeloEntity modelo = modeloRepository.findByNombreModelo(createDTO.getNombreModelo())
                .orElseThrow(() -> new EntityNotFoundException("Modelo no encontrado"));

        vehiculo.setUsuario(usuario);
        vehiculo.setModelo(modelo);

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

    @Override
    public List<VehiculoResponseDTO> getAllByUsuario(Integer rutUsuario) {
        if (rutUsuario == null) {
            throw new IllegalArgumentException("El RUT del usuario no puede ser nulo");
        }

        return vehiculoRepository.findByUsuarioRut(rutUsuario).stream()
                .map(vehiculoMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

}
