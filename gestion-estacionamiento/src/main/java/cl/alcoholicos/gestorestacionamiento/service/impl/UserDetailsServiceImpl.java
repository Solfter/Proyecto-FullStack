package cl.alcoholicos.gestorestacionamiento.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import cl.alcoholicos.gestorestacionamiento.entity.UsuarioEntity;
import cl.alcoholicos.gestorestacionamiento.repository.UsuarioRepository;

import java.util.Collections;
import java.util.List;

/**
 * Implementación de UserDetailsService que carga usuarios desde la base de datos.
 * Esta clase es usada por Spring Security para cargar información del usuario
 * durante la autenticación.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
        // Buscar el usuario en la base de datos por correo
        UsuarioEntity usuario = usuarioRepository.findByCorreo(correo).orElseThrow(() -> new UsernameNotFoundException("Correo no encontrado: " + correo));

        // Convertir roles/permisos a formato de Spring Security
        List<SimpleGrantedAuthority> authorities = Collections.emptyList();

        // Crear un UserDetails con la información del usuario
        return new User(
                usuario.getCorreo(), // Correo como Username
                usuario.getPassword(),
                true,
                true,
                true,
                true,
                authorities
        );
    }
}

