package cl.alcoholicos.gestorestacionamiento.usuario.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import cl.alcoholicos.gestorestacionamiento.usuario.dto.UsuarioCreateDTO;
import cl.alcoholicos.gestorestacionamiento.usuario.dto.UsuarioResponseDTO;
import cl.alcoholicos.gestorestacionamiento.usuario.dto.UsuarioUpdateDTO;
import cl.alcoholicos.gestorestacionamiento.usuario.entity.UsuarioEntity;
import cl.alcoholicos.gestorestacionamiento.usuario.mapper.UsuarioMapper;
import cl.alcoholicos.gestorestacionamiento.usuario.repository.UsuarioRepository;
import cl.alcoholicos.gestorestacionamiento.usuario.service.IUsuarioService;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class UsuarioService implements IUsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    @Override
    public UsuarioResponseDTO insert(UsuarioCreateDTO usuarioCreateDTO) {
        //DTO a Entity
        UsuarioEntity usuario = usuarioMapper.toEntity(usuarioCreateDTO);

        // Guardar en BD
        UsuarioEntity usuarioGuardado = usuarioRepository.save(usuario);

        // Convertir a DTO de respuesta
        UsuarioResponseDTO responseDTO = usuarioMapper.toResponseDTO(usuarioGuardado);        
        return responseDTO;
    }

    @Override
    public UsuarioResponseDTO update(Integer rut, UsuarioUpdateDTO usuarioUpdateDTO) {
        return usuarioRepository.findById(rut)
                .map(usuarioExistente -> {
                    // Actualizar solo campos no nulos del DTO
                    usuarioMapper.updateFromUpdateDTO(usuarioUpdateDTO, usuarioExistente);
                    
                    // Guardar cambios
                    UsuarioEntity usuarioActualizado = usuarioRepository.save(usuarioExistente);
                    
                    // Retornar como DTO
                    return usuarioMapper.toResponseDTO(usuarioActualizado);
                })
                .orElse(null);    
    }

    @Override
    public boolean delete(Integer rut) {
        if (usuarioRepository.existsById(rut)) {
            usuarioRepository.deleteById(rut);
            return true;
        }
        return false;
    }

    @Override
    public UsuarioResponseDTO getById(Integer rut) {
        return usuarioRepository.findById(rut)
                .map(usuarioMapper::toResponseDTO)
                .orElse(null);
    }

    @Override
    public List<UsuarioResponseDTO> getAll() {
        return usuarioRepository.findAll().stream()
                .map(usuarioMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

}
