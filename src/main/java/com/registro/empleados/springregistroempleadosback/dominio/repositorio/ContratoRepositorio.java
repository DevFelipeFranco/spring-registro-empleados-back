package com.registro.empleados.springregistroempleadosback.dominio.repositorio;

import com.registro.empleados.springregistroempleadosback.dominio.modelo.Contrato;

import java.util.List;

public interface ContratoRepositorio {

    List<Contrato> consultarContratos();
}
