package com.registro.empleados.springregistroempleadosback.dominio.excepciones;

public class ExistePersonaRegistradaPorUsuarioException extends RuntimeException {

    public ExistePersonaRegistradaPorUsuarioException(String exMessage) {
        super(exMessage);
    }
}
