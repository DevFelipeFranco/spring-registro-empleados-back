package com.registro.empleados.springregistroempleadosback.infraestructura.repositorio;

import com.registro.empleados.springregistroempleadosback.dominio.excepciones.UsuarioNoExisteException;
import com.registro.empleados.springregistroempleadosback.dominio.modelo.Persona;
import com.registro.empleados.springregistroempleadosback.dominio.modelo.Rol;
import com.registro.empleados.springregistroempleadosback.dominio.modelo.Usuario;
import com.registro.empleados.springregistroempleadosback.dominio.repositorio.UsuarioRepositorio;
import com.registro.empleados.springregistroempleadosback.infraestructura.modelo.RolEntidad;
import com.registro.empleados.springregistroempleadosback.infraestructura.modelo.UsuarioEntidad;
import com.registro.empleados.springregistroempleadosback.infraestructura.transformadores.UsuarioTransformador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
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
    default List<Usuario> consultarUsuarios() {
        return UsuarioTransformador.listadoUsuarioEntidadToModel(findAll());
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

    @Override
    default void actualizarFechaIngreso(Usuario usuarioEncontrado) {
        actualizarFechas(usuarioEncontrado.getFechaUltimoIngreso(), usuarioEncontrado.getFechaUltimoIngresoVisualizacion(), usuarioEncontrado.getIdUsuario());
    }

    @Override
    default Usuario consultarUsuarioPorId(Long idUsuario) {
        return findByIdUsuario(idUsuario)
                .map(UsuarioTransformador::usuarioEntidadToModel)
                .orElseThrow(() -> new UsuarioNoExisteException("No existe el usuario"));
    }

    @Override
    default void actualizarInformacionUsuario(Usuario usuario) {
        UsuarioEntidad usuarioEntidad = UsuarioTransformador.usuarioModeloToEntidad(usuario);
        actualizarUsuario(usuarioEntidad.getNombres(), usuarioEntidad.getApellidos(), usuarioEntidad.getCargo(), usuarioEntidad.getCorreoElectronico(), usuarioEntidad.getCelular(), usuarioEntidad.getUsuario(), usuarioEntidad.getEstado(), usuarioEntidad.getSnNoBloqueado(), usuarioEntidad.getIdUsuario());
    }

    @Override
    default void eliminarUsuario(Long idUsuario) {
        deleteById(idUsuario);
    }

    Optional<UsuarioEntidad> findByUsuario(String usuario);
    Optional<UsuarioEntidad> findByIdUsuario(Long idUsuario);

    @Modifying
    @Query("UPDATE UsuarioEntidad u SET u.estado = ?1 WHERE u.idUsuario = ?2")
    void actualizarEstadoUsuario(String activo, Long idUsuario);

    @Modifying
    @Query("UPDATE UsuarioEntidad u SET u.imagenPerfilUrl = ?1 WHERE u.idUsuario = ?2")
    void actualizarRutaImagen(String ruta, Long idUsuario);

    @Modifying
    @Query("UPDATE UsuarioEntidad u SET u.fechaUltimoIngreso = ?1, u.fechaUltimoIngresoVisualizacion = ?2 WHERE u.idUsuario = ?3")
    void actualizarFechas(LocalDateTime fechaUltimoIngreso, LocalDateTime fechaUltimoIngresoVisualizacion, Long idUsuario);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE UsuarioEntidad u SET u.nombres = ?1, u.apellidos = ?2, u.cargo = ?3, u.correoElectronico = ?4, u.celular = ?5, u.usuario = ?6, u.estado =?7, u.snNoBloqueado = ?8 WHERE u.idUsuario = ?9")
    void actualizarUsuario(String nombres, String apellidos, String cargo, String correoElectronico, String celular, String usuario, String estado, String snNoBloqueado, Long idUsuario);
}
