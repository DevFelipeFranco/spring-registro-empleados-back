package com.registro.empleados.springregistroempleadosback.dominio.servicio;

import com.registro.empleados.springregistroempleadosback.dominio.excepciones.UsuarioNoExisteException;
import com.registro.empleados.springregistroempleadosback.dominio.modelo.*;
import com.registro.empleados.springregistroempleadosback.infraestructura.repositorio.GeneroRepositorioMySQL;
import com.registro.empleados.springregistroempleadosback.infraestructura.repositorio.PersonaRepositorioMySQL;
import com.registro.empleados.springregistroempleadosback.infraestructura.repositorio.TipoDocumentoMySQL;
import com.registro.empleados.springregistroempleadosback.infraestructura.repositorio.UsuarioRepositorioMySQL;
import com.registro.empleados.springregistroempleadosback.infraestructura.transformadores.UsuarioTransformador;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PersonaServicio {

    private final PersonaRepositorioMySQL personaRepositorioMySQL;
    private final UsuarioRepositorioMySQL usuarioRepositorioMySQL;
    private final TipoDocumentoMySQL tipoDocumentoMySQL;
    private final GeneroRepositorioMySQL generoRepositorioMySQL;

    public Persona registrarPersona(Persona persona) {
        Usuario usuario = consultarUsuario(persona.getUsuario().getUsuario());
        persona.setUsuario(usuario);
        persona.setFechaIngreso(LocalDateTime.now());
        return personaRepositorioMySQL.registrarPersona(persona);
    }

    public List<Persona> consultarPersonas(Boolean esActivo) {
        return personaRepositorioMySQL.consultarPersonas(UsuarioTransformador.transformBooleanToString(esActivo));
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

    @Cacheable(value = "tipoDocumento")
    public List<TipoDocumento> consultarTipoDocumentos() {
        return tipoDocumentoMySQL.consultarTipoDocumentos();
    }

    @Cacheable(value = "genero")
    public List<Genero> consultarGenero() {
        return generoRepositorioMySQL.consultarGenero();
    }

    public List<CantidadEmpleadosContratadosMes> consultarPersonasContratadasPorMes() {
        return personaRepositorioMySQL.consultarCantidadEmpleadosContratadosPorMesTest();
    }
}
