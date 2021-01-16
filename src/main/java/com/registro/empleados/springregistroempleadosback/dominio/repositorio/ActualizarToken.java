package com.registro.empleados.springregistroempleadosback.dominio.repositorio;

import com.registro.empleados.springregistroempleadosback.dominio.modelo.RecargarToken;

import java.util.Optional;

public interface ActualizarToken {

    Optional<RecargarToken> buscarRefreshToken(String token);

    RecargarToken guardarRefreshToken(RecargarToken recargarToken);

    void eliminarTokenPorToken(String token);
}
