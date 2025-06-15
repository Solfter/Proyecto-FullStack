package cl.alcoholicos.gestorestacionamiento.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import cl.alcoholicos.gestorestacionamiento.dto.LoginRequest;
import cl.alcoholicos.gestorestacionamiento.dto.UsuarioCreateDTO;
import cl.alcoholicos.gestorestacionamiento.dto.UsuarioResponseDTO;
import cl.alcoholicos.gestorestacionamiento.dto.UsuarioUpdateDTO;
import cl.alcoholicos.gestorestacionamiento.entity.TipoUsuarioEntity;
import cl.alcoholicos.gestorestacionamiento.entity.UsuarioEntity;
import cl.alcoholicos.gestorestacionamiento.mapper.UsuarioMapper;
import cl.alcoholicos.gestorestacionamiento.repository.TipoUsuarioRepository;
import cl.alcoholicos.gestorestacionamiento.repository.UsuarioRepository;
import cl.alcoholicos.gestorestacionamiento.service.IUsuario;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class UsuarioService implements IUsuario {
    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final PasswordEncoder passwordEncoder;
    private final TipoUsuarioRepository tipoUsuarioRepository;

    @Override
    public UsuarioResponseDTO insert(UsuarioCreateDTO createDTO) {
        //DTO a Entity
        UsuarioEntity usuario = usuarioMapper.toEntity(createDTO);
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));

        TipoUsuarioEntity tipoUsuario = tipoUsuarioRepository.findById(createDTO.getIdTipoUsuario())
                        .orElseThrow(() -> new EntityNotFoundException("Tipo de Usuario no encontrado"));

        usuario.setTipoUsuario(tipoUsuario);
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

    public UsuarioResponseDTO findByCorreo(String correo) {
        return usuarioRepository.findByCorreo(correo)
                .map(usuarioMapper::toResponseDTO)
                .orElse(null); 
    }

    public UsuarioResponseDTO validateUser(LoginRequest loginRequest) {
        // Buscar el usuario por email
        UsuarioEntity usuario = usuarioRepository.findByCorreo(loginRequest.getCorreo())
            .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        // Validar contraseña
        if (!passwordEncoder.matches(loginRequest.getPassword(), usuario.getPassword())) {
            throw new BadCredentialsException("Contraseña incorrecta");
        }

        return usuarioRepository.findByCorreo(loginRequest.getCorreo())
                .map(usuarioMapper::toResponseDTO)
                .orElse(null); 
    }

}
