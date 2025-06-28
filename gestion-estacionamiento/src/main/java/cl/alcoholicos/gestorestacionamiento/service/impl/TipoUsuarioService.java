package cl.alcoholicos.gestorestacionamiento.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.alcoholicos.gestorestacionamiento.dto.TipoUsuarioCreateDTO;
import cl.alcoholicos.gestorestacionamiento.dto.TipoUsuarioResponseDTO;
import cl.alcoholicos.gestorestacionamiento.entity.TipoUsuarioEntity;
import cl.alcoholicos.gestorestacionamiento.mapper.TipoUsuarioMapper;
import cl.alcoholicos.gestorestacionamiento.repository.TipoUsuarioRepository;
import cl.alcoholicos.gestorestacionamiento.service.ITipoUsuario;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TipoUsuarioService implements ITipoUsuario {

    private final TipoUsuarioMapper tipoUsuarioMapper;

    @Autowired
    private TipoUsuarioRepository tipoUsuarioRepository;
    @Override
    public TipoUsuarioResponseDTO insert(TipoUsuarioCreateDTO createDTO) {
        TipoUsuarioEntity tipoUsuario = tipoUsuarioMapper.toEntity(createDTO);
        TipoUsuarioEntity tipoUsuarioGuardado = tipoUsuarioRepository.save(tipoUsuario);
        TipoUsuarioResponseDTO responseDTO = tipoUsuarioMapper.toResponseDTO(tipoUsuarioGuardado);
        return responseDTO;
    }

    @Override
    public TipoUsuarioResponseDTO update(Integer idTipoUsuario, TipoUsuarioResponseDTO tipoUsuarioResponseDTO) {
        return tipoUsuarioRepository.findById(idTipoUsuario)
            .map(tipoUsuarioExistente -> {
                tipoUsuarioMapper.updateFromUpdateDTO(tipoUsuarioResponseDTO, tipoUsuarioExistente);
                TipoUsuarioEntity tipoUsuarioActualizado = tipoUsuarioRepository.save(tipoUsuarioExistente);

                return tipoUsuarioMapper.toResponseDTO(tipoUsuarioActualizado);
            })
            .orElse(null);
    }

    @Override
    public boolean delete(Integer idTipoUsuario) {
        if (tipoUsuarioRepository.existsById(idTipoUsuario)) {
            tipoUsuarioRepository.deleteById(idTipoUsuario);
            return true;
        }
        return false;
    }

    @Override
    public TipoUsuarioResponseDTO getById(Integer idTipoUsuario) {
        return tipoUsuarioRepository.findById(idTipoUsuario)
                .map(tipoUsuarioMapper::toResponseDTO)
                .orElse(null);
    }

    @Override
    public List<TipoUsuarioResponseDTO> getAll() {
        return tipoUsuarioRepository.findAll().stream()
                .map(tipoUsuarioMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    

}
