package com.registro.empleados.springregistroempleadosback.infraestructura.modelo;

import com.registro.empleados.springregistroempleadosback.dominio.modelo.Usuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Autenticacion {

    private String tokenAutenticacion;
    private Usuario usuario;
}
