package com.registro.empleados.springregistroempleadosback.dominio.repositorio;

import com.registro.empleados.springregistroempleadosback.dominio.modelo.Usuario;

import java.util.List;

public interface UsuarioRepositorio {

    List<Usuario> listadoUsuarios();
    Usuario registrarUsuario(Usuario usuario);
}
