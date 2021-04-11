package com.registro.empleados.springregistroempleadosback.aplicacion.fabrica;

import com.registro.empleados.springregistroempleadosback.aplicacion.comando.ComandoPersona;
import com.registro.empleados.springregistroempleadosback.dominio.modelo.Persona;

public final class FabricaPersona {

    public static Persona comandoPersonaToPersona(ComandoPersona comandoPersona) {
        return Persona.builder()
                .idPersona(comandoPersona.getIdPersona())
                .identificacion(comandoPersona.getIdentificacion())
                .primerNombre(comandoPersona.getPrimerNombre())
                .segundoNombre(comandoPersona.getSegundoNombre())
                .primerApellido(comandoPersona.getPrimerApellido())
                .segundoApellido(comandoPersona.getSegundoApellido())
                .fechaNacimiento(comandoPersona.getFechaNacimiento())
                .edad(comandoPersona.getEdad())
                .email(comandoPersona.getEmail())
                .direccion(comandoPersona.getDireccion())
                .tipoDocumento(FabricaTipoDocumento.comandoTipoDocumentoToTipoDocumento(comandoPersona.getTipoDocumento()))
                .usuario(FabricaUsuario.comandoUsuarioToModelo(comandoPersona.getUsuario()))
                .genero(FabricaGenero.comandoGeneroToModelo(comandoPersona.getGenero()))
                .fechaIngreso(comandoPersona.getFechaIngreso())
                .build();

    }
}
