package com.registro.empleados.springregistroempleadosback.infraestructura.modelo;

import com.registro.empleados.springregistroempleadosback.dominio.modelo.Usuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Autenticacion {

    private String tokenAutenticacion;
    private String refreshToken;
    private Instant expiresAt;
    private Usuario usuario;
}
