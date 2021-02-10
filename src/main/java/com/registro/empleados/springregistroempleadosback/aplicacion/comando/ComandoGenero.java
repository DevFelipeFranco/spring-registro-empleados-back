package com.registro.empleados.springregistroempleadosback.aplicacion.comando;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "con")
public class ComandoGenero {

    private Long idGenero;
    private String codigoGenero;
    private String descripcion;
}
