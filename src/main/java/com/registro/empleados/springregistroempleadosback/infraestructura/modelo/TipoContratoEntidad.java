package com.registro.empleados.springregistroempleadosback.infraestructura.modelo;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "TIPO_CONTRATO", schema = "DB_REGISTRO_EMPLEADOS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TipoContratoEntidad implements Serializable {

    private static final long serialVersionUID = 4543865957308514279L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTipoContrato;

    private String tipoContrato;
}
