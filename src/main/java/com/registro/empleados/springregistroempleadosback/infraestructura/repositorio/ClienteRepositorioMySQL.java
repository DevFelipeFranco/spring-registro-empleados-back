package com.registro.empleados.springregistroempleadosback.infraestructura.repositorio;

import com.registro.empleados.springregistroempleadosback.dominio.modelo.Cliente;
import com.registro.empleados.springregistroempleadosback.dominio.repositorio.ClienteRepositorio;
import com.registro.empleados.springregistroempleadosback.infraestructura.modelo.ClienteEntidad;
import com.registro.empleados.springregistroempleadosback.infraestructura.transformadores.ClienteTransformador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.registro.empleados.springregistroempleadosback.infraestructura.transformadores.ClienteTransformador.entidadToModelo;
import static com.registro.empleados.springregistroempleadosback.infraestructura.transformadores.ClienteTransformador.modelToEntidad;

@Repository
public interface ClienteRepositorioMySQL extends JpaRepository<ClienteEntidad, Long>, ClienteRepositorio {

    @Override
    default List<Cliente> consultarClientes(String esActivo) {
        return entidadToModelo(findBySnProyectoActivo(esActivo));
    }

    @Override
    default Cliente crearCliente(Cliente cliente) {
        ClienteEntidad clienteEntidad = modelToEntidad(cliente);
        return entidadToModelo(save(clienteEntidad));
    }

    @Override
    default void eliminarCliente(Long idCliente) {
        eliminadoLogicoCliente(idCliente);
    }

    List<ClienteEntidad> findBySnProyectoActivo(String esActivo);

    @Modifying
    @Query("UPDATE ClienteEntidad c SET c.snProyectoActivo = 'N' WHERE c.idCliente = ?1")
    void eliminadoLogicoCliente(Long idCliente);
}
