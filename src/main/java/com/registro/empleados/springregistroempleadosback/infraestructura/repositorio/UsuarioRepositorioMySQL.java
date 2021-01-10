package com.registro.empleados.springregistroempleadosback.infraestructura.repositorio;

import com.registro.empleados.springregistroempleadosback.dominio.modelo.UsuarioModelo;
import com.registro.empleados.springregistroempleadosback.dominio.repositorio.UsuarioRepositorio;
import com.registro.empleados.springregistroempleadosback.infraestructura.modelo.UsuarioEntidad;
import com.registro.empleados.springregistroempleadosback.infraestructura.transformadores.UsuarioTransformador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepositorioMySQL extends JpaRepository<UsuarioEntidad, Long>, UsuarioRepositorio {

    @Override
    default List<UsuarioModelo> listadoUsuarios() {
        return UsuarioTransformador.listadoUsuarioEntidadToModel(findAll());
    }

    @Override
    default UsuarioModelo registrarUsuario(UsuarioModelo usuarioModelo) {
        UsuarioEntidad usuarioEntidad = UsuarioTransformador.usuarioModeloToEntidad(usuarioModelo);
        return UsuarioTransformador.usuarioEntidadToModel(save(usuarioEntidad));
    }
}
