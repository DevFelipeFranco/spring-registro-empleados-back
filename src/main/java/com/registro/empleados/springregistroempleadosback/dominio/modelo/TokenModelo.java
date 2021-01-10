package com.registro.empleados.springregistroempleadosback.dominio.modelo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "con")
public class TokenModelo {

    private Long idToken;
    private Date fechaExpiracion;
    private String token;
    private UsuarioModelo usuarioModelo;
}
