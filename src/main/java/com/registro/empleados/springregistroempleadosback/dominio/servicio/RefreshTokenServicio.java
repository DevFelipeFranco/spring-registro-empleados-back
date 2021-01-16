package com.registro.empleados.springregistroempleadosback.dominio.servicio;

import com.registro.empleados.springregistroempleadosback.dominio.excepciones.NoExisteTokenException;
import com.registro.empleados.springregistroempleadosback.dominio.modelo.RecargarToken;
import com.registro.empleados.springregistroempleadosback.infraestructura.repositorio.RecargarTokenRepositorioMySQL;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
public class RefreshTokenServicio {

    private final RecargarTokenRepositorioMySQL recargarTokenRepositorioMySQL;

    public RecargarToken generarRefreshToken() {
        RecargarToken recargarToken = RecargarToken.builder()
                .token(UUID.randomUUID().toString())
                .fechaCreacion(LocalDateTime.now())
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
