package cl.alcoholicos.gestorestacionamiento.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import cl.alcoholicos.gestorestacionamiento.dto.MarcaCreateDTO;
import cl.alcoholicos.gestorestacionamiento.dto.MarcaResponseDTO;
import cl.alcoholicos.gestorestacionamiento.dto.MarcaUpdateDTO;
import cl.alcoholicos.gestorestacionamiento.entity.MarcaEntity;
import cl.alcoholicos.gestorestacionamiento.mapper.MarcaMapper;
import cl.alcoholicos.gestorestacionamiento.repository.MarcaRepository;
import cl.alcoholicos.gestorestacionamiento.service.IMarca;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MarcaService implements IMarca {

    private final MarcaRepository marcaRepository;
    private final MarcaMapper marcaMapper;

    @Override
    public MarcaResponseDTO insert(MarcaCreateDTO marcaCreateDTO) {
        MarcaEntity marca = marcaMapper.toEntity(marcaCreateDTO);
        MarcaEntity marcaGuardada = marcaRepository.save(marca);
        MarcaResponseDTO responseDTO = marcaMapper.toResponseDTO(marcaGuardada);
        return responseDTO;
    }

    @Override
    public MarcaResponseDTO update(int idMarca, MarcaUpdateDTO marca) {
        return marcaRepository.findById(idMarca)
                .map(marcaExistente -> {
                    marcaMapper.updateFromUpdateDTO(marca, marcaExistente);
                    MarcaEntity marcaActualizada = marcaRepository.save(marcaExistente);
                    return marcaMapper.toResponseDTO(marcaActualizada);
                })
                .orElse(null);
    }
    @Override
    public boolean delete(int idMarca) {
        if (marcaRepository.existsById(idMarca)) {
            marcaRepository.deleteById(idMarca);
            return true;
        }
        return false;
    }
    @Override
    public MarcaResponseDTO getById(int idMarca) {
        return marcaRepository.findById(idMarca)
                .map(marcaMapper::toResponseDTO)
                .orElse(null);
                
    }
    @Override
    public List<MarcaResponseDTO> getAll() {
        return marcaRepository.findAll().stream()
                .map(marcaMapper::toResponseDTO)
                .collect(Collectors.toList());
    }



}
