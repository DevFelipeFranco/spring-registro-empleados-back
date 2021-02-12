package com.registro.empleados.springregistroempleadosback.infraestructura.repositorio;

import com.registro.empleados.springregistroempleadosback.dominio.modelo.Contrato;
import com.registro.empleados.springregistroempleadosback.dominio.repositorio.ContratoRepositorio;
import com.registro.empleados.springregistroempleadosback.infraestructura.modelo.ContratoEntidad;
import com.registro.empleados.springregistroempleadosback.infraestructura.transformadores.ContratoTransformador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContratoRepositorioMySQL extends JpaRepository<ContratoEntidad, Long>, ContratoRepositorio {

    @Override
    default List<Contrato> consultarContratos() {
        return ContratoTransformador.contratosEntidadToModelo(findAll());
    }

    @Override
    default Contrato registrarContrato(Contrato contrato) {
        ContratoEntidad contratoEntidad = ContratoTransformador.controToEntidad(contrato);
        return ContratoTransformador.contratoEntidadToModelo(save(contratoEntidad));
    }
}
