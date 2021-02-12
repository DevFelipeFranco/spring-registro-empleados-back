package com.registro.empleados.springregistroempleadosback.dominio.servicio;

import com.registro.empleados.springregistroempleadosback.dominio.modelo.Contrato;
import com.registro.empleados.springregistroempleadosback.infraestructura.repositorio.ContratoRepositorioMySQL;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ContratoServicio {

    private final ContratoRepositorioMySQL contratoRepositorioMySQL;

    public List<Contrato> consultarContratos() {
        return contratoRepositorioMySQL.consultarContratos();
    }


}
