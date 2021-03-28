package com.registro.empleados.springregistroempleadosback.infraestructura.modelo;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "AUTORIZACION", schema = "DB_REGISTRO_EMPLEADOS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AutorizacionEntidad implements Serializable {

    private static final long serialVersionUID = 6118850073368242222L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_AUTORIZACION")
    private Long idAutorizacion;

    private String autorizacion;
}
