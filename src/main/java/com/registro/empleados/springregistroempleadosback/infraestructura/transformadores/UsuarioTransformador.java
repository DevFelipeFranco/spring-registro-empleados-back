package com.registro.empleados.springregistroempleadosback.infraestructura.transformadores;

import com.registro.empleados.springregistroempleadosback.dominio.modelo.Usuario;
import com.registro.empleados.springregistroempleadosback.infraestructura.modelo.UsuarioEntidad;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public final class UsuarioTransformador {

    public static UsuarioEntidad usuarioModeloToEntidad(Usuario usuario) {
        return UsuarioEntidad.builder()
                .idUsuario(usuario.getIdUsuario())
                .nombres(usuario.getNombres())
                .apellidos(usuario.getApellidos())
                .usuario(usuario.getUsuario())
                .clave(usuario.getClave())
                .correoElectronico(usuario.getEmail())
                .estado(transformBooleanToString(usuario.getEstado()))
                .snNoBloqueado(transformBooleanToString(usuario.getSnNoBloqueado()))
                .fechaCreacion(usuario.getFechaCreacion())
                .fechaUltimoIngreso(usuario.getFechaUltimoIngreso())
                .fechaUltimoIngresoVisualizacion(usuario.getFechaUltimoIngresoVisualizacion())
                .imagenPerfilUrl(usuario.getImagenPerfilUrl())
                .cargo(usuario.getCargo())
                .celular(usuario.getCelular())
                .roles(RolTransformador.rolesToRolesEntidad(usuario.getRoles()))
                .build();
    }

    public static Usuario usuarioEntidadToModel(UsuarioEntidad usuarioEntidad) {
        return Usuario.builder()
                .conIdUsuario(usuarioEntidad.getIdUsuario())
                .conNombres(usuarioEntidad.getNombres())
                .conApellidos(usuarioEntidad.getApellidos())
                .conUsuario(usuarioEntidad.getUsuario())
                .conClave(usuarioEntidad.getClave())
                .conEmail(usuarioEntidad.getCorreoElectronico())
                .conEstado(transformStringToBoolean(usuarioEntidad.getEstado()))
                .conSnNoBloqueado(transformStringToBoolean(usuarioEntidad.getSnNoBloqueado()))
                .conFechaCreacion(usuarioEntidad.getFechaCreacion())
                .conFechaUltimoIngreso(usuarioEntidad.getFechaUltimoIngreso())
                .conFechaUltimoIngresoVisualizacion(usuarioEntidad.getFechaUltimoIngresoVisualizacion())
                .conImagenPerfilUrl(usuarioEntidad.getImagenPerfilUrl())
                .conCargo(usuarioEntidad.getCargo())
                .conCelular(usuarioEntidad.getCelular())
                .conRoles(RolTransformador.rolesEntidadToRoles(usuarioEntidad.getRoles()))
                .build();
    }

    public static Optional<Usuario> usuarioEntidadOptToModelOpt(Optional<UsuarioEntidad> usuarioEntidad) {
        return usuarioEntidad.map(UsuarioTransformador::usuarioEntidadToModel);
    }

    public static List<Usuario> listadoUsuarioEntidadToModel(List<UsuarioEntidad> usuariosEntidad) {
        return usuariosEntidad.stream()
                .map(UsuarioTransformador::usuarioEntidadToModel)
                .collect(Collectors.toList());
    }

    public static String transformBooleanToString(Boolean estado) {
        return estado != null && estado ? "S" : "N";
    }

    public static Boolean transformStringToBoolean(String estado) {
        return "S".equals(estado);
    }
}
