package com.registro.empleados.springregistroempleadosback.dominio.excepciones;

public class EmailExisteException extends Exception {
    public EmailExisteException(String message) {
        super(message);
    }
}
