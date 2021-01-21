package com.registro.empleados.springregistroempleadosback.infraestructura.modelo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "TIPO_DOCUMENTO", schema = "DB_REGISTRO_EMPLEADOS")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TipoDocumentoEntidad implements Serializable {

    private static final long serialVersionUID = -8512164351459723115L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTipoDocumento;

    private String tipoDocumento;
    private String descripcion;
}
