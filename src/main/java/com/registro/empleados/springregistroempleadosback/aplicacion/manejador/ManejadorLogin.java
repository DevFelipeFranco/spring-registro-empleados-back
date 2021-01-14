package com.registro.empleados.springregistroempleadosback.aplicacion.manejador;

import com.registro.empleados.springregistroempleadosback.aplicacion.comando.ComandoUsuario;
import com.registro.empleados.springregistroempleadosback.aplicacion.fabrica.FabricaUsuario;
import com.registro.empleados.springregistroempleadosback.dominio.modelo.Usuario;
import com.registro.empleados.springregistroempleadosback.dominio.servicio.AuthService;
import com.registro.empleados.springregistroempleadosback.infraestructura.modelo.Autenticacion;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Optional;

@Component
@AllArgsConstructor
public class ManejadorLogin {

    private final AuthService authService;

    @Transactional
    public Optional<Autenticacion> ejecutar(ComandoUsuario comandoUsuario) {
        Usuario usuario = FabricaUsuario.comandoUsuarioToModelo(comandoUsuario);
        return authService.login(usuario);
    }
}
