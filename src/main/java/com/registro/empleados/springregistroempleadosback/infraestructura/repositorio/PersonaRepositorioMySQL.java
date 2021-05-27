package com.registro.empleados.springregistroempleadosback.infraestructura.repositorio;

import com.registro.empleados.springregistroempleadosback.dominio.modelo.CantidadEmpleadosContratadosMes;
import com.registro.empleados.springregistroempleadosback.dominio.modelo.Persona;
import com.registro.empleados.springregistroempleadosback.dominio.modelo.Usuario;
import com.registro.empleados.springregistroempleadosback.dominio.repositorio.PersonaRepositorio;
import com.registro.empleados.springregistroempleadosback.infraestructura.modelo.PersonaEntidad;
import com.registro.empleados.springregistroempleadosback.infraestructura.modelo.UsuarioEntidad;
import com.registro.empleados.springregistroempleadosback.infraestructura.transformadores.PersonaTransformador;
import com.registro.empleados.springregistroempleadosback.infraestructura.transformadores.UsuarioTransformador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PersonaRepositorioMySQL extends JpaRepository<PersonaEntidad, Long>, PersonaRepositorio {

    @Override
    default Persona registrarPersona(Persona persona) {
        PersonaEntidad personaEntidad = PersonaTransformador.personaToPersonaEntidad(persona);
        return PersonaTransformador.personaEntidadToModelo(save((personaEntidad)));
    }

    @Override
    default List<Persona> consultarPersonas() {
        return PersonaTransformador.listaPersonasEntidadToModelo(findAll());
    }

    @Override
    default List<Persona> consultarPersonarPorUsuario(Usuario usuario) {
        UsuarioEntidad usuarioEntidad = UsuarioTransformador.usuarioModeloToEntidad(usuario);
        return PersonaTransformador.listaPersonasEntidadToModelo(findByUsuarioEntidad(usuarioEntidad));
    }

    @Override
    default void eliminarPersonaPorId(Long idPersona) {
        deleteById(idPersona);
    }

    @Query(value = "SELECT count(*) AS cantidad, DATE_FORMAT(FECHA_CREACION, '%Y/%m/%d') AS fechaIngreso FROM DB_REGISTRO_EMPLEADOS.PERSONAS GROUP BY DATE_FORMAT(FECHA_CREACION, '%m/%Y')", nativeQuery = true)
    List<Map<String, Object>> consultarCantidadEmpleadosContratadosPorMes();

    @Query(value = "SELECT count(*) AS cantidad, DATE_FORMAT(FECHA_CREACION, '%Y/%m/%d') AS fechaIngreso FROM DB_REGISTRO_EMPLEADOS.PERSONAS GROUP BY DATE_FORMAT(FECHA_CREACION, '%m/%Y')", nativeQuery = true)
    List<CantidadEmpleadosContratadosMes> consultarCantidadEmpleadosContratadosPorMesTest();

    List<PersonaEntidad> findByUsuarioEntidad(UsuarioEntidad usuarioEntidad);
}
