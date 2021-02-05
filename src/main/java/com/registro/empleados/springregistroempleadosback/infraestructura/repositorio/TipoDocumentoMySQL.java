package com.registro.empleados.springregistroempleadosback.infraestructura.repositorio;

import com.registro.empleados.springregistroempleadosback.dominio.modelo.TipoDocumento;
import com.registro.empleados.springregistroempleadosback.dominio.repositorio.TipoDocumentoRepositorio;
import com.registro.empleados.springregistroempleadosback.infraestructura.modelo.TipoDocumentoEntidad;
import com.registro.empleados.springregistroempleadosback.infraestructura.transformadores.TipoDocumentoTransformador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TipoDocumentoMySQL extends JpaRepository<TipoDocumentoEntidad, Long>, TipoDocumentoRepositorio {

    @Override
    default List<TipoDocumento> consultarTipoDocumentos() {
        return TipoDocumentoTransformador.tipoDocumentosEntidadToTipoDocumentosModel(findAll());
    }
}
