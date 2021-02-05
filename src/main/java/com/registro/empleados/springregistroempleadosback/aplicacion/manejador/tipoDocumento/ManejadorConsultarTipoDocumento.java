package com.registro.empleados.springregistroempleadosback.aplicacion.manejador.tipoDocumento;

import com.registro.empleados.springregistroempleadosback.dominio.modelo.TipoDocumento;
import com.registro.empleados.springregistroempleadosback.dominio.servicio.PersonaServicio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
@AllArgsConstructor
public class ManejadorConsultarTipoDocumento {

    private final PersonaServicio personaServicio;

    @Transactional
    public List<TipoDocumento> ejecutar() {
        return personaServicio.consultarTipoDocumentos();
    }
}
