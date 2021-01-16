package com.registro.empleados.springregistroempleadosback.dominio.servicio;

import com.registro.empleados.springregistroempleadosback.dominio.excepciones.NoExisteTokenException;
import com.registro.empleados.springregistroempleadosback.dominio.modelo.RecargarToken;
import com.registro.empleados.springregistroempleadosback.infraestructura.repositorio.RecargarTokenRepositorioMySQL;
import com.registro.empleados.springregistroempleadosback.infraestructura.repositorio.TokenRepositorioMySQL;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
public class RefreshTokenServicio {

    private final RecargarTokenRepositorioMySQL recargarTokenRepositorioMySQL;

    public RecargarToken generarRefreshToken() {
        RecargarToken recargarToken = RecargarToken.builder()
                .token(UUID.randomUUID().toString())
                .fechaCreacion(Instant.now())
                .build();

        return recargarTokenRepositorioMySQL.guardarRefreshToken(recargarToken);
    }

    public void validateActualizacionToken(String token) {
        recargarTokenRepositorioMySQL.buscarRefreshToken(token)
                .orElseThrow(() -> new NoExisteTokenException("No existe el token"));
    }

    public void eliminarActualizacionToken(String token) {
        recargarTokenRepositorioMySQL.eliminarTokenPorToken(token);
    }
}
