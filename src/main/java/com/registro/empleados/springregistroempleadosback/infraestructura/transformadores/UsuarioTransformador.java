package com.registro.empleados.springregistroempleadosback.infraestructura.transformadores;

import com.registro.empleados.springregistroempleadosback.dominio.modelo.UsuarioModelo;
import com.registro.empleados.springregistroempleadosback.infraestructura.modelo.TokenEntidad;
import com.registro.empleados.springregistroempleadosback.infraestructura.modelo.UsuarioEntidad;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class UsuarioTransformador {

    public static UsuarioEntidad usuarioModeloToEntidad(UsuarioModelo usuarioModelo) {
        return UsuarioEntidad.builder()
                .idUsuario(usuarioModelo.getIdUsuario())
                .usuario(usuarioModelo.getUsuario())
                .clave(usuarioModelo.getClave())
                .correoElectronico(usuarioModelo.getEmail())
                .estado(transformBooleanToString(usuarioModelo.getEstado()))
                .fechaCreacion(usuarioModelo.getFechaCreacion())
                .roles(new ArrayList<>())
                .tokenEntidad(new TokenEntidad())
                .build();
    }

    public static UsuarioModelo usuarioEntidadToModel(UsuarioEntidad usuarioEntidad) {
        return UsuarioModelo.builder()
                .conIdUsuario(usuarioEntidad.getIdUsuario())
                .conUsuario(usuarioEntidad.getUsuario())
                .conClave(usuarioEntidad.getClave())
                .conEmail(usuarioEntidad.getCorreoElectronico())
                .conEstado(transformStringToBoolean(usuarioEntidad.getEstado()))
                .conFechaCreacion(usuarioEntidad.getFechaCreacion())
                .conLstUsuarioRol(RolTransformador.listadoUsuarioEntidadToModel(usuarioEntidad.getRoles()))
                .conTokenModelo(TokenTransformador.tokenEntidadToModelo(usuarioEntidad.getTokenEntidad()))
                .build();
    }

    public static List<UsuarioModelo> listadoUsuarioEntidadToModel(List<UsuarioEntidad> usuariosEntidad) {
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
