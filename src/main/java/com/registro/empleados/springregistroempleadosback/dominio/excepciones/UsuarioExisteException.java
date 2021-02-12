package com.registro.empleados.springregistroempleadosback.dominio.excepciones;

public class UsuarioExisteException extends RuntimeException {

    private static final long serialVersionUID = 1588570145280257694L;

    public UsuarioExisteException(String exMenssage) {
        super(exMenssage);
    }
}
