package com.registro.empleados.springregistroempleadosback.dominio.repositorio;

import com.registro.empleados.springregistroempleadosback.dominio.modelo.Genero;

import java.util.List;

public interface GeneroRepositorio {

    List<Genero> consultarGenero();
}
