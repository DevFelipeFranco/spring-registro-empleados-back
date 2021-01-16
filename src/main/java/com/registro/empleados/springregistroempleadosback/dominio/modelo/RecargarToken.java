package com.registro.empleados.springregistroempleadosback.dominio.modelo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecargarToken {

    private Long idRecargarToken;
    private String token;
    private LocalDateTime fechaCreacion;
}
