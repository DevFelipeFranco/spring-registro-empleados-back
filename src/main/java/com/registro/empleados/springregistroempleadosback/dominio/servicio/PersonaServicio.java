package com.registro.empleados.springregistroempleadosback.dominio.servicio;

import com.registro.empleados.springregistroempleadosback.dominio.excepciones.UsuarioNoExisteException;
import com.registro.empleados.springregistroempleadosback.dominio.modelo.Persona;
import com.registro.empleados.springregistroempleadosback.dominio.modelo.Usuario;
import com.registro.empleados.springregistroempleadosback.infraestructura.repositorio.PersonaRepositorioMySQL;
import com.registro.empleados.springregistroempleadosback.infraestructura.repositorio.UsuarioRepositorioMySQL;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PersonaServicio {

    private final PersonaRepositorioMySQL personaRepositorioMySQL;
    private final UsuarioRepositorioMySQL usuarioRepositorioMySQL;

    public Persona registrarPersona(Persona persona) {
        Usuario usuario = usuarioRepositorioMySQL.buscarUsuario(persona.getUsuario().getUsuario())
                .orElseThrow(() -> new UsuarioNoExisteException("No existe el usuario: " + persona.getUsuario().getUsuario()));
        persona.setUsuario(usuario);
        return personaRepositorioMySQL.registrarPersona(persona);
    }

    public List<Persona> consultarPersonas() {
        return personaRepositorioMySQL.consultarPersonas();
    }
}
