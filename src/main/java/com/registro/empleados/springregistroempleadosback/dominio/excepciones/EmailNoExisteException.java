package com.registro.empleados.springregistroempleadosback.dominio.excepciones;

public class EmailNoExisteException extends Exception {

    private static final long serialVersionUID = 1662052359314581217L;

    public EmailNoExisteException(String message) {
        super(message);
    }
}
