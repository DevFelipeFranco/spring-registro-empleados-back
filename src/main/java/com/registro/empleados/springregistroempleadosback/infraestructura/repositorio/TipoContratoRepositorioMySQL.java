package com.registro.empleados.springregistroempleadosback.infraestructura.repositorio;

import com.registro.empleados.springregistroempleadosback.dominio.modelo.TipoContrato;
import com.registro.empleados.springregistroempleadosback.dominio.repositorio.TipoContratoRepositorio;
import com.registro.empleados.springregistroempleadosback.infraestructura.modelo.TipoContratoEntidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TipoContratoRepositorioMySQL extends JpaRepository<TipoContratoEntidad, Long>, TipoContratoRepositorio {

    @Override
    default List<TipoContrato> consultarTipoContratos() {
        return null;
    }
}
