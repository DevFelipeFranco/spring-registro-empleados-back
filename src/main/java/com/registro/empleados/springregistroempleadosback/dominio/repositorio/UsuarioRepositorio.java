package com.registro.empleados.springregistroempleadosback.dominio.repositorio;

import com.registro.empleados.springregistroempleadosback.dominio.modelo.Usuario;

import java.util.Optional;

public interface UsuarioRepositorio {

    Usuario registrarUsuario(Usuario usuario);

    Optional<Usuario> buscarUsuario(String usuario);
}
