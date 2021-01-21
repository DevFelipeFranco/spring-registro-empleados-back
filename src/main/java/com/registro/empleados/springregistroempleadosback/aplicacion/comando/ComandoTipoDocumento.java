package com.registro.empleados.springregistroempleadosback.aplicacion.comando;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ComandoTipoDocumento {

    private Long idTipoDocumento;
    private String tipoDocumento;
    private String descripcion;
}
