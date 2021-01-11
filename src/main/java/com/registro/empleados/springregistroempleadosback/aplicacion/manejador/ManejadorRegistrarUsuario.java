package com.registro.empleados.springregistroempleadosback.aplicacion.manejador;

import com.registro.empleados.springregistroempleadosback.aplicacion.comando.ComandoUsuario;
import com.registro.empleados.springregistroempleadosback.aplicacion.fabrica.FabricaUsuario;
import com.registro.empleados.springregistroempleadosback.dominio.modelo.Usuario;
import com.registro.empleados.springregistroempleadosback.dominio.servicio.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@AllArgsConstructor
public class ManejadorRegistrarUsuario {

    private final AuthService authService;

    @Transactional
    public Usuario ejecutar(ComandoUsuario comandoUsuario) {
        Usuario usuario = FabricaUsuario.comandoUsuarioToModelo(comandoUsuario);
        return authService.registrarUsuario(usuario);
    }
}
