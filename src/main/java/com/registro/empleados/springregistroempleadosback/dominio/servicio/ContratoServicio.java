package com.registro.empleados.springregistroempleadosback.dominio.servicio;

import com.registro.empleados.springregistroempleadosback.dominio.excepciones.UsuarioNoExisteException;
import com.registro.empleados.springregistroempleadosback.dominio.modelo.Contrato;
import com.registro.empleados.springregistroempleadosback.dominio.modelo.Usuario;
import com.registro.empleados.springregistroempleadosback.infraestructura.repositorio.ContratoRepositorioMySQL;
import com.registro.empleados.springregistroempleadosback.infraestructura.repositorio.UsuarioRepositorioMySQL;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ContratoServicio {

    private final ContratoRepositorioMySQL contratoRepositorioMySQL;
    private final UsuarioRepositorioMySQL usuarioRepositorioMySQL;

    public List<Contrato> consultarContratos() {
        return contratoRepositorioMySQL.consultarContratos();
    }


    public Contrato registrarContrato(Contrato contrato) {
        Usuario usuario = usuarioRepositorioMySQL.buscarUsuario(contrato.getPersona().getUsuario().getUsuario())
                .orElseThrow(() -> new UsuarioNoExisteException("El usuario no existe"));
        contrato.getPersona().setUsuario(usuario);
        return contratoRepositorioMySQL.registrarContrato(contrato);
    }
}
