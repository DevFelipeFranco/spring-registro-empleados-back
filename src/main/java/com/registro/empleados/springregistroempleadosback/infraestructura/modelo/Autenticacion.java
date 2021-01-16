package com.registro.empleados.springregistroempleadosback.infraestructura.modelo;

import com.registro.empleados.springregistroempleadosback.dominio.modelo.Usuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Autenticacion {

    private String tokenAutenticacion;
    private String refreshToken;
    private LocalDateTime expiresAt;
    private Usuario usuario;
}
