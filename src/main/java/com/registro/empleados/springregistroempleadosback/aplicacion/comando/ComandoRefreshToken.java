package com.registro.empleados.springregistroempleadosback.aplicacion.comando;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ComandoRefreshToken {

    @NotBlank
    private String refreshToken;
    private String usuario;
}
