package com.registro.empleados.springregistroempleadosback.dominio.modelo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cliente {

    private Long idCliente;
    private String nombreCliente;
    private String nombreProyecto;
    private Long avanceProyecto;
    private String descripcion;
    private Long cantidadTrabajadores;
    private Boolean estadoProyecto;
}
