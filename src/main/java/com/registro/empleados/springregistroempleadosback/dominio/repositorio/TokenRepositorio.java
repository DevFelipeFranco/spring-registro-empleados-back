package com.registro.empleados.springregistroempleadosback.dominio.repositorio;

import com.registro.empleados.springregistroempleadosback.dominio.modelo.Token;
import com.registro.empleados.springregistroempleadosback.infraestructura.modelo.TokenEntidad;

import java.util.Optional;

public interface TokenRepositorio {

    Token registrarToken(Token token);

    Optional<Token> buscarToken(String token);
}
