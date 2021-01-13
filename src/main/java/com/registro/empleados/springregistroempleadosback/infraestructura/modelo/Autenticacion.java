package com.registro.empleados.springregistroempleadosback.infraestructura.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Autenticacion {

    private String tokenAutenticacion;
    private String usuario;
}
