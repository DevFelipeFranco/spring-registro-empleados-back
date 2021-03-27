package com.registro.empleados.springregistroempleadosback.aplicacion.fabrica;

import com.registro.empleados.springregistroempleadosback.aplicacion.comando.ComandoUsuario;
import com.registro.empleados.springregistroempleadosback.dominio.modelo.Usuario;

import java.util.Arrays;
import java.util.Collections;

public final class FabricaUsuario {

    public static Usuario comandoUsuarioToModelo(ComandoUsuario comandoUsuario) {
        return Usuario.builder()
                .conIdUsuario(comandoUsuario.getIdUsuario())
                .conUsuario(comandoUsuario.getUsuario())
                .conClave(comandoUsuario.getClave())
                .conEmail(comandoUsuario.getEmail())
                .conNombres(comandoUsuario.getNombres())
                .conApellidos(comandoUsuario.getApellidos())
                .conEstado(comandoUsuario.getEstado())
                .conSnNoBloqueado(comandoUsuario.getSnNoBloqueado())
                .conCargo(comandoUsuario.getCargo())
                .conCelular(comandoUsuario.getCelular())
                .conRoles(comandoUsuario.getRoles())
                .build();
    }
}
