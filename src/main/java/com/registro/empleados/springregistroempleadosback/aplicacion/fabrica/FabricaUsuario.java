package com.registro.empleados.springregistroempleadosback.aplicacion.fabrica;

import com.registro.empleados.springregistroempleadosback.aplicacion.comando.ComandoUsuario;
import com.registro.empleados.springregistroempleadosback.dominio.modelo.Usuario;

public final class FabricaUsuario {

    public static Usuario comandoUsuarioToModelo(ComandoUsuario comandoUsuario) {
        return Usuario.builder()
                .conUsuario(comandoUsuario.getUsuario())
                .conClave(comandoUsuario.getClave())
                .conEmail(comandoUsuario.getEmail())
                .build();
    }
}
