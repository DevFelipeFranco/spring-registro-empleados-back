package com.registro.empleados.springregistroempleadosback.dominio.excepciones;

public class CertificadoException extends RuntimeException {

    public CertificadoException(String exMessage, Exception e) {
        super(exMessage, e);
    }
}
