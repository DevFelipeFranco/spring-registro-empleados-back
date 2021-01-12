package com.registro.empleados.springregistroempleadosback.dominio.excepciones;

public class NoExisteTokenException extends RuntimeException {

    public NoExisteTokenException(String exMessage) {
        super(exMessage);
    }
}
