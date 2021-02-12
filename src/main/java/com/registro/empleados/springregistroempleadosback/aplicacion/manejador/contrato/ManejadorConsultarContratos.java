package com.registro.empleados.springregistroempleadosback.aplicacion.manejador.contrato;

import com.registro.empleados.springregistroempleadosback.dominio.modelo.Contrato;
import com.registro.empleados.springregistroempleadosback.dominio.servicio.ContratoServicio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@AllArgsConstructor
public class ManejadorConsultarContratos {

    private final ContratoServicio contratoServicio;

    @Transactional
    public List<Contrato> ejecutar() {
        return contratoServicio.consultarContratos();
    }
}
