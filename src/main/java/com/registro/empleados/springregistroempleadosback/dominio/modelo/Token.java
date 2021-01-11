package com.registro.empleados.springregistroempleadosback.dominio.modelo;

import com.registro.empleados.springregistroempleadosback.infraestructura.modelo.UsuarioEntidad;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "con")
public class Token {

    private Long idToken;
    private String token;
    private Date fechaExpiracion;
    private Usuario usuario;
}
