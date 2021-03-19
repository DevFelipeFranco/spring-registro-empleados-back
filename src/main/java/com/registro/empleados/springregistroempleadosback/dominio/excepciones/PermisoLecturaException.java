package com.registro.empleados.springregistroempleadosback.dominio.excepciones;

public class PermisoLecturaException extends Exception {

    private static final long serialVersionUID = 6344063516061192443L;

    public PermisoLecturaException(String exMessage) {
        super(exMessage);
    }
}
