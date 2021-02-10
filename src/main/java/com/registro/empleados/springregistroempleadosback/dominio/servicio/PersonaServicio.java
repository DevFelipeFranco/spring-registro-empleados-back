package com.registro.empleados.springregistroempleadosback.dominio.servicio;

import com.registro.empleados.springregistroempleadosback.dominio.excepciones.UsuarioNoExisteException;
import com.registro.empleados.springregistroempleadosback.dominio.modelo.Persona;
import com.registro.empleados.springregistroempleadosback.dominio.modelo.TipoDocumento;
import com.registro.empleados.springregistroempleadosback.dominio.modelo.Usuario;
import com.registro.empleados.springregistroempleadosback.infraestructura.repositorio.PersonaRepositorioMySQL;
import com.registro.empleados.springregistroempleadosback.infraestructura.repositorio.TipoDocumentoMySQL;
import com.registro.empleados.springregistroempleadosback.infraestructura.repositorio.UsuarioRepositorioMySQL;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PersonaServicio {

    private final PersonaRepositorioMySQL personaRepositorioMySQL;
    private final UsuarioRepositorioMySQL usuarioRepositorioMySQL;
    private final TipoDocumentoMySQL tipoDocumentoMySQL;

    public Persona registrarPersona(Persona persona) {
        Usuario usuario = consultarUsuario(persona.getUsuario().getUsuario());
        persona.setUsuario(usuario);
        return personaRepositorioMySQL.registrarPersona(persona);
    }

    public List<Persona> consultarPersonas() {
        return personaRepositorioMySQL.consultarPersonas();
    }

    public Persona actualizarPersona(Persona persona) {
        Usuario usuario = consultarUsuario(persona.getUsuario().getUsuario());
        persona.setUsuario(usuario);
        return personaRepositorioMySQL.registrarPersona(persona);
    }

    private Usuario consultarUsuario(String usuario) {
        return usuarioRepositorioMySQL.buscarUsuario(usuario)
                .orElseThrow(() -> new UsuarioNoExisteException("No existe el usuario: " + usuario));
    }

    public void eliminarPersona(Long idPersona) {
        personaRepositorioMySQL.eliminarPersonaPorId(idPersona);
    }

    @Cacheable(value = "userCache")
    public List<TipoDocumento> consultarTipoDocumentos() {
        return tipoDocumentoMySQL.consultarTipoDocumentos();
    }
}
