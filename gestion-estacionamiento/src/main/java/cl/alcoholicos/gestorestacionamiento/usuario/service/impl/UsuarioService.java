package cl.alcoholicos.gestorestacionamiento.usuario.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private final UsuarioRepository usuarioRepository;

    @Autowired
    private final UsuarioMapper usuarioMapper;

    @Override
    public UsuarioResponseDTO insert(UsuarioCreateDTO usuarioCreateDTO) {
        // Log antes de transformar
        System.out.println("DTO de entrada - pNombre: " + usuarioCreateDTO.getPrimerNombre() + ", sNombre: " + usuarioCreateDTO.getSegundoNombre());
        
        //DTO a Entity
        UsuarioEntity usuario = usuarioMapper.toEntity(usuarioCreateDTO);
        System.out.println("Entity después de mapeo - pNombre: " + usuario.getPrimerNombre() + ", sNombre: " + usuario.getSegundoNombre());

        // Guardar en BD
        UsuarioEntity usuarioGuardado = usuarioRepository.save(usuario);
        System.out.println("Entity después de guardar - pNombre: " + usuarioGuardado.getPrimerNombre() + ", sNombre: " + usuarioGuardado.getSegundoNombre());

        // Convertir a DTO de respuesta
        UsuarioResponseDTO responseDTO = usuarioMapper.toResponseDTO(usuarioGuardado);
        System.out.println("DTO de respuesta - pNombre: " + responseDTO.getPrimerNombre() + ", sNombre: " + responseDTO.getSegundoNombre());
        
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
