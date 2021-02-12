package com.registro.empleados.springregistroempleadosback.infraestructura.modelo;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "CONTRATO", schema = "DB_REGISTRO_EMPLEADOS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContratoEntidad implements Serializable {

    private static final long serialVersionUID = 7811665474351078720L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idContrato;

    @Column(name = "FECHA_INGRESO", columnDefinition = "TIMESTAMP")
    private LocalDateTime fechaIngreso;
    @Column(name = "FECHA_SALIDA", columnDefinition = "TIMESTAMP")
    private LocalDateTime fechaSalida;

    private String documentoContrato;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PERSONA")
    private PersonaEntidad personaEntidad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_TIPO_CONTRATO")
    private TipoContratoEntidad tipoContratoEntidad;
}
