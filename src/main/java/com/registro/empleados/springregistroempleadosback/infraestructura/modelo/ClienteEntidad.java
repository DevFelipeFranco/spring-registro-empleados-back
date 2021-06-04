package com.registro.empleados.springregistroempleadosback.infraestructura.modelo;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "CLIENTES", schema = "DB_REGISTRO_EMPLEADOS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClienteEntidad implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCliente;

    private String nombreCliente;
    private String nombreProyecto;
    private Long avanceProyecto;

    @Column(name = "DESCRIPCION_PROYECTO")
    private String descripcion;
    private Long cantidadTrabajadores;

    @Column(name = "SNPROYECTO_ACTIVO")
    private String snProyectoActivo;

    private LocalDateTime fechaRegistro;

    private Long cantidadSprint;
}
