package com.registro.empleados.springregistroempleadosback.infraestructura.modelo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "GENERO", schema = "DB_REGISTRO_EMPLEADOS")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GeneroEntidad implements Serializable {

    private static final long serialVersionUID = -8878118085363789656L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idGenero;

    @Column(name = "CDGENERO")
    private String cdGenero;
    private String descripcion;
}
