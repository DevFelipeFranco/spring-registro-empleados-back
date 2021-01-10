package com.registro.empleados.springregistroempleadosback.dominio.repositorio;

import com.registro.empleados.springregistroempleadosback.dominio.modelo.UsuarioModelo;

import java.util.List;

public interface UsuarioRepositorio {

    List<UsuarioModelo> listadoUsuarios();
    UsuarioModelo registrarUsuario(UsuarioModelo usuarioModelo);
}
