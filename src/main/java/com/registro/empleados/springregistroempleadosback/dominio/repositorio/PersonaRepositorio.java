package com.registro.empleados.springregistroempleadosback.dominio.repositorio;

import com.registro.empleados.springregistroempleadosback.dominio.modelo.Persona;

import java.util.List;

public interface PersonaRepositorio {

    Persona registrarPersona(Persona persona);

    List<Persona> consultarPersonas();

    void eliminarPersonaPorId(Long idPersona);
}
