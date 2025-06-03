package cl.alcoholicos.gestorestacionamiento.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import cl.alcoholicos.gestorestacionamiento.entity.UsuarioEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UsuarioDetails implements UserDetails {

    private int rut;
    private String correo;
    private String password;
    private boolean activo;
    private List<String> roles = new ArrayList<>();
    private final UsuarioEntity usuario;

    public UsuarioDetails(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(role -> new SimpleGrantedAuthority(role))
                .collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        // Utilizamos el rut como nombre de usuario único
        return String.valueOf(this.rut);
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.activo; // La cuenta está activa si no ha expirado
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.activo; // La cuenta está activa si no está bloqueada
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Asumimos que las credenciales no expiran
    }

    @Override
    public boolean isEnabled() {
        return this.activo; // El usuario está habilitado si está activo
    }
}
