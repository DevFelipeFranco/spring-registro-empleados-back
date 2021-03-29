package com.registro.empleados.springregistroempleadosback.dominio.repositorio;

import com.registro.empleados.springregistroempleadosback.dominio.modelo.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepositorio {

    Usuario registrarUsuario(Usuario usuario);

    Optional<Usuario> buscarUsuario(String usuario);

    void activarUsuario(String estado, Long idUsuario);

    Optional<Usuario> buscarUsuarioPorId(Long id);

    void actualizarRutaImagenPerfil(String ruta, Long idUsuario);

    void actualizarFechaIngreso(Usuario usuarioEncontrado);

    List<Usuario> consultarUsuarios();

    Usuario consultarUsuarioPorId(Long idUsuario);

    void actualizarInformacionUsuario(Usuario usuario);

    void eliminarUsuario(Long idUsuario);
}
