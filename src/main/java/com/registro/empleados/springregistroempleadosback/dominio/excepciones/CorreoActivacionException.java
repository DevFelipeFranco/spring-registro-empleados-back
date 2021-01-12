package com.registro.empleados.springregistroempleadosback.dominio.excepciones;

public class CorreoActivacionException extends RuntimeException {

    public CorreoActivacionException(String exMessage, Exception exception) {
        super(exMessage, exception);
    }

}
