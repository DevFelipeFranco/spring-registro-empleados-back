package com.registro.empleados.springregistroempleadosback.infraestructura.repositorio;

import com.registro.empleados.springregistroempleadosback.dominio.modelo.RecargarToken;
import com.registro.empleados.springregistroempleadosback.dominio.repositorio.ActualizarToken;
import com.registro.empleados.springregistroempleadosback.infraestructura.modelo.RecargarTokenEntidad;
import com.registro.empleados.springregistroempleadosback.infraestructura.transformadores.ActualizarTokenTransformador;
import com.registro.empleados.springregistroempleadosback.infraestructura.transformadores.TokenTransformador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecargarTokenRepositorioMySQL extends JpaRepository<RecargarTokenEntidad, Long>, ActualizarToken {

    @Override
    default Optional<RecargarToken> buscarRefreshToken(String token) {
        return TokenTransformador.refreshTokenEntidadOptToTokenOpt(findByToken(token));
    }

    @Override
    default RecargarToken guardarRefreshToken(RecargarToken recargarToken) {
        RecargarTokenEntidad recargarTokenEntidad = ActualizarTokenTransformador.actualizarTokenToEntidad(recargarToken);
        return ActualizarTokenTransformador.actualizarTokenEntidadToModelo(save(recargarTokenEntidad));
    }

    @Override
    default void eliminarTokenPorToken(String token) {
        deleteByToken(token);
    }

    void deleteByToken(String token);

    Optional<RecargarTokenEntidad> findByToken(String token);
}
