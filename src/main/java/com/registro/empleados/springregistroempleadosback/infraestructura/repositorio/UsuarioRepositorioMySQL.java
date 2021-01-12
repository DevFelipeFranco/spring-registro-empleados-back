package com.registro.empleados.springregistroempleadosback.infraestructura.repositorio;

import com.registro.empleados.springregistroempleadosback.dominio.modelo.Usuario;
import com.registro.empleados.springregistroempleadosback.dominio.repositorio.UsuarioRepositorio;
import com.registro.empleados.springregistroempleadosback.infraestructura.modelo.UsuarioEntidad;
import com.registro.empleados.springregistroempleadosback.infraestructura.transformadores.UsuarioTransformador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepositorioMySQL extends JpaRepository<UsuarioEntidad, Long>, UsuarioRepositorio {

    @Override
    default Usuario registrarUsuario(Usuario usuario) {
        UsuarioEntidad usuarioEntidad = UsuarioTransformador.usuarioModeloToEntidad(usuario);
        return UsuarioTransformador.usuarioEntidadToModel(saveAndFlush(usuarioEntidad));
    }

    @Override
    default Optional<Usuario> buscarUsuario(String usuario) {
        return UsuarioTransformador.usuarioEntidadOptToModelOpt(findByUsuario(usuario));
    }

    Optional<UsuarioEntidad> findByUsuario(String usuario);
}
