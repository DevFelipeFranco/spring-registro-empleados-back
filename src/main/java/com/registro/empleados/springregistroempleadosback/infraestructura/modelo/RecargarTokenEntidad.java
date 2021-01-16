package com.registro.empleados.springregistroempleadosback.infraestructura.modelo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

@Entity
@Table(name = "RECARGAR_TOKEN", schema = "DB_REGISTRO_EMPLEADOS")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecargarTokenEntidad implements Serializable {

    private static final long serialVersionUID = -4993748620102402290L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRecargarToken;
    private String token;
    private Instant fechaCreacion;
}
