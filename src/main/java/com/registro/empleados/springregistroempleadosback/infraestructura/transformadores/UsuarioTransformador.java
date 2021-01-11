package com.registro.empleados.springregistroempleadosback.infraestructura.transformadores;

import com.registro.empleados.springregistroempleadosback.dominio.modelo.Usuario;
import com.registro.empleados.springregistroempleadosback.infraestructura.modelo.UsuarioEntidad;

import java.util.List;
import java.util.stream.Collectors;

public final class UsuarioTransformador {

    public static UsuarioEntidad usuarioModeloToEntidad(Usuario usuario) {
        return UsuarioEntidad.builder()
                .idUsuario(usuario.getIdUsuario())
                .usuario(usuario.getUsuario())
                .clave(usuario.getClave())
                .correoElectronico(usuario.getEmail())
                .estado(transformBooleanToString(usuario.getEstado()))
                .fechaCreacion(usuario.getFechaCreacion())
                .roles(RolTransformador.rolesToRolesEntidad(usuario.getRoles()))
                .build();
    }

    public static Usuario usuarioEntidadToModel(UsuarioEntidad usuarioEntidad) {
        return Usuario.builder()
                .conIdUsuario(usuarioEntidad.getIdUsuario())
                .conUsuario(usuarioEntidad.getUsuario())
                .conClave(usuarioEntidad.getClave())
                .conEmail(usuarioEntidad.getCorreoElectronico())
                .conEstado(transformStringToBoolean(usuarioEntidad.getEstado()))
                .conFechaCreacion(usuarioEntidad.getFechaCreacion())
                .conRoles(RolTransformador.rolesEntidadToRoles(usuarioEntidad.getRoles()))
                .build();
    }

    public static List<Usuario> listadoUsuarioEntidadToModel(List<UsuarioEntidad> usuariosEntidad) {
        return usuariosEntidad.stream()
                .map(UsuarioTransformador::usuarioEntidadToModel)
                .collect(Collectors.toList());
    }

    public static String transformBooleanToString(Boolean estado) {
        return estado ? "S" : "N";
    }

    public static Boolean transformStringToBoolean(String estado) {
        return "S".equals(estado);
    }
}
