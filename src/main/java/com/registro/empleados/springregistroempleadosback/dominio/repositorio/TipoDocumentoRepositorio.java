package com.registro.empleados.springregistroempleadosback.dominio.repositorio;

import com.registro.empleados.springregistroempleadosback.dominio.modelo.TipoDocumento;

import java.util.List;

public interface TipoDocumentoRepositorio {

    List<TipoDocumento> consultarTipoDocumentos();
}
