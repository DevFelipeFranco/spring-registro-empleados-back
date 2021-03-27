package com.registro.empleados.springregistroempleadosback.dominio.repositorio;

import com.registro.empleados.springregistroempleadosback.dominio.modelo.Rol;

import java.util.List;

public interface RolRepositorio {

    List<Rol> consultarRoles();
}

