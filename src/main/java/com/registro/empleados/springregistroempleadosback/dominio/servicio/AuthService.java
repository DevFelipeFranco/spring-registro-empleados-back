package com.registro.empleados.springregistroempleadosback.dominio.servicio;

import com.registro.empleados.springregistroempleadosback.dominio.modelo.UsuarioModelo;
import com.registro.empleados.springregistroempleadosback.infraestructura.modelo.UsuarioEntidad;
import com.registro.empleados.springregistroempleadosback.infraestructura.repositorio.UsuarioRepositorioMySQL;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class AuthService {

    private final UsuarioRepositorioMySQL usuarioRepositorioMySQL;

    @Transactional
    public UsuarioModelo registrarUsuario(UsuarioModelo usuarioModelo) {
        return usuarioRepositorioMySQL.registrarUsuario(usuarioModelo);
    }

    @Transactional
    public List<UsuarioModelo> listaUsuarios() {
        return usuarioRepositorioMySQL.listadoUsuarios();
    }

    public List<UsuarioEntidad> prueba() {
        return usuarioRepositorioMySQL.findAll();
    }

}
