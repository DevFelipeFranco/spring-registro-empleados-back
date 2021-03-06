package com.registro.empleados.springregistroempleadosback.dominio.repositorio;

import com.registro.empleados.springregistroempleadosback.dominio.modelo.Persona;
import com.registro.empleados.springregistroempleadosback.dominio.modelo.Usuario;
import com.registro.empleados.springregistroempleadosback.infraestructura.modelo.UsuarioEntidad;

import java.util.List;

public interface PersonaRepositorio {

    Persona registrarPersona(Persona persona);

    List<Persona> consultarPersonas(String esActivo);

    void eliminarPersonaPorId(Long idPersona, String motivo);

    List<Persona> consultarPersonarPorUsuario(Usuario usuario);
}
