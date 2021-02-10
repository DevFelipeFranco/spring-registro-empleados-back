package com.registro.empleados.springregistroempleadosback.infraestructura.transformadores;

import com.registro.empleados.springregistroempleadosback.dominio.modelo.Persona;
import com.registro.empleados.springregistroempleadosback.infraestructura.modelo.PersonaEntidad;

import java.util.List;
import java.util.stream.Collectors;

public final class PersonaTransformador {

    public static PersonaEntidad personaToPersonaEntidad(Persona persona) {
        return PersonaEntidad.builder()
                .idPersona(persona.getIdPersona())
                .identificacion(persona.getIdentificacion())
                .primerNombre(persona.getPrimerNombre())
                .segundoNombre(persona.getSegundoNombre())
                .primerApellido(persona.getPrimerApellido())
                .segundoApellido(persona.getSegundoApellido())
                .fechaNacimiento(persona.getFechaNacimiento())
                .edad(persona.getEdad())
                .correoElectronico(persona.getEmail())
                .direccion(persona.getDireccion())
                .tipoDocumento(TipoDocumentoTransformador.tipoDocumentoToEntidad(persona.getTipoDocumento()))
                .usuarioEntidad(UsuarioTransformador.usuarioModeloToEntidad(persona.getUsuario()))
                .generoEntidad(GeneroTransformador.genetoModeloToEntidad(persona.getGenero()))
                .build();
    }

    public static Persona personaEntidadToModelo(PersonaEntidad personaEntidad) {
        return Persona.builder()
                .idPersona(personaEntidad.getIdPersona())
                .identificacion(personaEntidad.getIdentificacion())
                .primerNombre(personaEntidad.getPrimerNombre())
                .segundoNombre(personaEntidad.getSegundoNombre())
                .primerApellido(personaEntidad.getPrimerApellido())
                .segundoApellido(personaEntidad.getSegundoApellido())
                .fechaNacimiento(personaEntidad.getFechaNacimiento())
                .edad(personaEntidad.getEdad())
                .email(personaEntidad.getCorreoElectronico())
                .direccion(personaEntidad.getDireccion())
                .tipoDocumento(TipoDocumentoTransformador.tipoDocumentoEntidadToModelo(personaEntidad.getTipoDocumento()))
                .usuario(UsuarioTransformador.usuarioEntidadToModel(personaEntidad.getUsuarioEntidad()))
                .genero(GeneroTransformador.genetoEntidadToModelo(personaEntidad.getGeneroEntidad()))
                .build();
    }

    public static List<Persona> listaPersonasEntidadToModelo(List<PersonaEntidad> personaEntidads) {
        return personaEntidads.stream().map(PersonaTransformador::personaEntidadToModelo).collect(Collectors.toList());
    }
}
