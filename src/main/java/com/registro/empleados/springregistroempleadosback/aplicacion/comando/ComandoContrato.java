package com.registro.empleados.springregistroempleadosback.aplicacion.comando;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "con")
public class ComandoContrato {

    private Long idContrato;
    private LocalDateTime fechaIngreso;
    private LocalDateTime fechaSalida;
    private String documentoContrato;
    private ComandoPersona persona;
    private ComandoTipoContrato tipoContrato;

}
