package com.registro.empleados.springregistroempleadosback.infraestructura.repositorio;

import com.registro.empleados.springregistroempleadosback.dominio.modelo.Usuario;
import com.registro.empleados.springregistroempleadosback.dominio.repositorio.UsuarioRepositorio;
import com.registro.empleados.springregistroempleadosback.infraestructura.modelo.UsuarioEntidad;
import com.registro.empleados.springregistroempleadosback.infraestructura.transformadores.UsuarioTransformador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepositorioMySQL extends JpaRepository<UsuarioEntidad, Long>, UsuarioRepositorio {

    @Override
    default Usuario registrarUsuario(Usuario usuario) {
        UsuarioEntidad usuarioEntidad = UsuarioTransformador.usuarioModeloToEntidad(usuario);
        return UsuarioTransformador.usuarioEntidadToModel(save(usuarioEntidad));
    }

    @Override
    default Optional<Usuario> buscarUsuario(String usuario) {
        return UsuarioTransformador.usuarioEntidadOptToModelOpt(findByUsuario(usuario));
    }

    @Override
    default Optional<Usuario> buscarUsuarioPorId(Long id) {
        return UsuarioTransformador.usuarioEntidadOptToModelOpt(findByIdUsuario(id));
    }

    @Override
    default void activarUsuario(String estado, Long idUsuario) {
        actualizarEstadoUsuario(estado, idUsuario);
    }

    @Override
    default void actualizarRutaImagenPerfil(String ruta, Long idUsuario) {
        actualizarRutaImagen(ruta, idUsuario);
    }

    Optional<UsuarioEntidad> findByUsuario(String usuario);
    Optional<UsuarioEntidad> findByIdUsuario(Long idUsuario);

    @Modifying
    @Query("UPDATE UsuarioEntidad u SET u.estado = ?1 WHERE u.idUsuario = ?2")
    void actualizarEstadoUsuario(String activo, Long idUsuario);

    @Modifying
    @Query("UPDATE UsuarioEntidad u SET u.imagenPerfilUrl = ?1 WHERE u.idUsuario = ?2")
    void actualizarRutaImagen(String ruta, Long idUsuario);
}
