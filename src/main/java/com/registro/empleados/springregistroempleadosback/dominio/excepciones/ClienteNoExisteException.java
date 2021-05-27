package com.registro.empleados.springregistroempleadosback.dominio.excepciones;

public class ClienteNoExisteException extends RuntimeException {
    public ClienteNoExisteException(String exMessage) {
        super(exMessage);
    }
}
