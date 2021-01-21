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
public class Persona {

    private Long idPersona;
    private Integer identificacion;
    private String primerNombre;
    private String segundoNombre;
    private String primerApellido;
    private String segundoApellido;
    private LocalDateTime fechaNacimiento;
    private int edad;
    private String correoElectronico;
    private String direccion;
    private TipoDocumento tipoDocumento;
    private Usuario usuario;
}
