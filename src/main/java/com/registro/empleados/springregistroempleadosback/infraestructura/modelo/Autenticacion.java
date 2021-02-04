package com.registro.empleados.springregistroempleadosback.infraestructura.modelo;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime expiresAt;
    private Usuario usuario;
}
