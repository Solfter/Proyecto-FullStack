package cl.alcoholicos.gestorestacionamiento.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.alcoholicos.gestorestacionamiento.dto.ModeloCreateDTO;
import cl.alcoholicos.gestorestacionamiento.dto.ModeloResponseDTO;
import cl.alcoholicos.gestorestacionamiento.entity.MarcaEntity;
import cl.alcoholicos.gestorestacionamiento.entity.ModeloEntity;
import cl.alcoholicos.gestorestacionamiento.mapper.ModeloMapper;
import cl.alcoholicos.gestorestacionamiento.repository.MarcaRepository;
import cl.alcoholicos.gestorestacionamiento.repository.ModeloRepository;
import cl.alcoholicos.gestorestacionamiento.service.IModelo;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ModeloService implements IModelo {

    private final ModeloMapper modeloMapper;
    private final MarcaRepository marcaRepository;

    @Autowired
    private ModeloRepository modeloRepository;

    @Override
    public ModeloResponseDTO insert(ModeloCreateDTO createDTO) {
        ModeloEntity modelo = modeloMapper.toEntity(createDTO);

        MarcaEntity marca = marcaRepository.findById(createDTO.getIdMarca())
            .orElseThrow(() -> new EntityNotFoundException("Marca no Encontrada"));

        modelo.setMarca(marca);

        ModeloEntity modeloGuardado = modeloRepository.save(modelo);
        ModeloResponseDTO responseDTO = modeloMapper.toResponseDTO(modeloGuardado);
        return responseDTO;
    }

    @Override
    public ModeloResponseDTO update(Integer idModelo, ModeloResponseDTO modelo) {
        return modeloRepository.findById(idModelo)
                .map(modeloExistente -> {
                    modeloMapper.updateFromUpdateDTO(modelo, modeloExistente);
                    ModeloEntity modeloActualizado = modeloRepository.save(modeloExistente);
                    return modeloMapper.toResponseDTO(modeloActualizado);
                })
                .orElse(null);
    }

    @Override
    public boolean delete(Integer idModelo) {
        if (modeloRepository.existsById(idModelo)) {
            modeloRepository.deleteById(idModelo);
            return true;
        }
        return false;
    }

    @Override
    public ModeloResponseDTO getById(Integer idModelo) {
        return modeloRepository.findById(idModelo)
            .map(modeloMapper::toResponseDTO)
            .orElse(null);
    }

    @Override
    public List<ModeloResponseDTO> getAll() {
        return modeloRepository.findAll().stream()
                .map(modeloMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

}
