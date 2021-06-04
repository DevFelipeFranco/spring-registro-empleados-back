package com.registro.empleados.springregistroempleadosback.aplicacion.comando;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ComandoCliente {

    private Long idCliente;
    private String nombreCliente;
    private String nombreProyecto;
    private Long avanceProyecto;
    private String descripcion;
    private Long cantidadTrabajadores;
    private Boolean estadoProyecto;
    private Long cantidadSprint;
}
