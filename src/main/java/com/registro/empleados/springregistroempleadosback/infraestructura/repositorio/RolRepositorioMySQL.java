package com.registro.empleados.springregistroempleadosback.infraestructura.repositorio;

import com.registro.empleados.springregistroempleadosback.dominio.modelo.Rol;
import com.registro.empleados.springregistroempleadosback.dominio.repositorio.RolRepositorio;
import com.registro.empleados.springregistroempleadosback.infraestructura.modelo.RolEntidad;
import com.registro.empleados.springregistroempleadosback.infraestructura.transformadores.RolTransformador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RolRepositorioMySQL extends JpaRepository<RolEntidad, Long>, RolRepositorio {

    @Override
    default List<Rol> consultarRoles() {
        return RolTransformador.rolesEntidadToRoles(findAll());
    }
}
