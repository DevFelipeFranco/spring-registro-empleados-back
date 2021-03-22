package com.registro.empleados.springregistroempleadosback.infraestructura.listener;

import com.registro.empleados.springregistroempleadosback.dominio.modelo.UsuarioPrincipal;
import com.registro.empleados.springregistroempleadosback.infraestructura.security.LoginAttemptService;
import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AuthenticationSuccessListener {

    private final LoginAttemptService loginAttemptService;

    @EventListener
    public void onAuthenticationSuccess(AuthenticationSuccessEvent event) {
        Object principal = event.getAuthentication().getPrincipal();
        if(principal instanceof UsuarioPrincipal) {
            UsuarioPrincipal usuarioPrincipal = (UsuarioPrincipal) event.getAuthentication().getPrincipal();
            loginAttemptService.evictUserFromLoginAttemptCache(usuarioPrincipal.getUsername());
        }
    }
}
