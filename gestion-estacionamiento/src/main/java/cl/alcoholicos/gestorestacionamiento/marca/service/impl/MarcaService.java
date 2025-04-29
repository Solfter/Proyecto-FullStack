package cl.alcoholicos.gestorestacionamiento.marca.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import cl.alcoholicos.gestorestacionamiento.marca.dto.MarcaResponseDTO;
import cl.alcoholicos.gestorestacionamiento.marca.mapper.MarcaMapper;
import cl.alcoholicos.gestorestacionamiento.marca.repository.MarcaRepository;
import cl.alcoholicos.gestorestacionamiento.marca.service.IMarca;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MarcaService implements IMarca {

    private final MarcaRepository marcaRepository;
    private final MarcaMapper marcaMapper;

    @Override
    public MarcaResponseDTO insert(MarcaResponseDTO marca) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insert'");
    }
    @Override
    public MarcaResponseDTO update(Integer idMarca, MarcaResponseDTO marca) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }
    @Override
    public MarcaResponseDTO delete(Integer idMarca) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }
    @Override
    public MarcaResponseDTO getById(Integer idMarca) {
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
