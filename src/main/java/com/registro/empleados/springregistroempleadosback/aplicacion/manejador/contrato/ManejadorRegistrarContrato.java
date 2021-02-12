package com.registro.empleados.springregistroempleadosback.aplicacion.manejador.contrato;

import com.registro.empleados.springregistroempleadosback.aplicacion.comando.ComandoContrato;
import com.registro.empleados.springregistroempleadosback.aplicacion.fabrica.FabricaContrato;
import com.registro.empleados.springregistroempleadosback.dominio.modelo.Contrato;
import com.registro.empleados.springregistroempleadosback.dominio.servicio.ContratoServicio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ManejadorRegistrarContrato {

    private final ContratoServicio contratoServicio;

    public Contrato ejecutar(ComandoContrato comandoContrato) {
        Contrato contrato = FabricaContrato.comandoContratoToModelo(comandoContrato);
        return contratoServicio.registrarContrato(contrato);
    }
}
