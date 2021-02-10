package com.registro.empleados.springregistroempleadosback.dominio.modelo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Genero {

    private Long idGenero;
    private String codigoGenero;
    private String descripcion;
}
