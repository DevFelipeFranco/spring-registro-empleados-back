package com.registro.empleados.springregistroempleadosback.dominio.repositorio;

import com.registro.empleados.springregistroempleadosback.dominio.modelo.TipoContrato;

import java.util.List;

public interface TipoContratoRepositorio {

    List<TipoContrato> consultarTipoContratos();
}
