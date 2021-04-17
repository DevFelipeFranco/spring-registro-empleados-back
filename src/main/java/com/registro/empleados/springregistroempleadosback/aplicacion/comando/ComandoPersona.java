package com.registro.empleados.springregistroempleadosback.aplicacion.comando;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ComandoPersona {

    private Long idPersona;
    private Integer identificacion;
    private String primerNombre;
    private String segundoNombre;
    private String primerApellido;
    private String segundoApellido;
    private LocalDateTime fechaNacimiento;
    private int edad;
    private String email;
    private String direccion;
    private ComandoTipoDocumento tipoDocumento;
    private ComandoUsuario usuario;
    private ComandoGenero genero;
    private LocalDateTime fechaIngreso;
    private ComandoCliente proyecto;
}
