package com.registro.empleados.springregistroempleadosback.dominio.excepciones;

public class UsuarioNoExisteException extends RuntimeException {

    private static final long serialVersionUID = 8928383237890171209L;

    public UsuarioNoExisteException(String exMessage) {
        super(exMessage);
    }
}
