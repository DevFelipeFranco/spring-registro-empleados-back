package com.registro.empleados.springregistroempleadosback.infraestructura.repositorio;

import com.registro.empleados.springregistroempleadosback.dominio.modelo.Token;
import com.registro.empleados.springregistroempleadosback.dominio.repositorio.TokenRepositorio;
import com.registro.empleados.springregistroempleadosback.infraestructura.modelo.TokenEntidad;
import com.registro.empleados.springregistroempleadosback.infraestructura.transformadores.TokenTransformador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepositorioMySQL extends JpaRepository<TokenEntidad, Long>, TokenRepositorio {

    @Override
    default Token registrarToken(Token token) {
        TokenEntidad tokenEntidad = TokenTransformador.tokenToTokenEntidad(token);
        return TokenTransformador.tokenEntidadToToken(save(tokenEntidad));
    }
}
