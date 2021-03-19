package com.registro.empleados.springregistroempleadosback.dominio.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class UsuarioPrincipal implements UserDetails {

    private static final long serialVersionUID = 4380012601494834962L;

    private Usuario usuario;

    public UsuarioPrincipal(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();

        this.usuario.getRoles().forEach(role -> {
            GrantedAuthority authority = new SimpleGrantedAuthority("ROL_" + role);
            authorities.add(authority);
        });

        List<Autorizacion> autorizaciones =
                this.usuario.getRoles().stream()
                        .map(Rol::getAutorizaciones)
                        .flatMap(Collection::stream)
                        .collect(Collectors.toList());

        return autorizaciones.stream()
                .map(autorizacion -> new SimpleGrantedAuthority(autorizacion.getAutorizacion()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return this.usuario.getClave();
    }

    @Override
    public String getUsername() {
        return this.usuario.getUsuario();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.usuario.getEstado();
    }
}
