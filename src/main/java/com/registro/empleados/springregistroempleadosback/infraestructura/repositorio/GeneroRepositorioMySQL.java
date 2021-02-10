package com.registro.empleados.springregistroempleadosback.infraestructura.repositorio;

import com.registro.empleados.springregistroempleadosback.dominio.modelo.Genero;
import com.registro.empleados.springregistroempleadosback.dominio.repositorio.GeneroRepositorio;
import com.registro.empleados.springregistroempleadosback.infraestructura.modelo.GeneroEntidad;
import com.registro.empleados.springregistroempleadosback.infraestructura.transformadores.GeneroTransformador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GeneroRepositorioMySQL extends JpaRepository<GeneroEntidad, Long>, GeneroRepositorio {

    @Override
    default List<Genero> consultarGenero() {
        return GeneroTransformador.generosEntidadToModelo(findAll());
    }
}
