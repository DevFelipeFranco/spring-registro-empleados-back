package com.registro.empleados.springregistroempleadosback.dominio.modelo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Data
@NoArgsConstructor
public class EmpleadosContratados {

    private Long cantidad;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate fechaIngreso;

    public EmpleadosContratados(Map<String, Object> empleadosContratos) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate dateTime = LocalDate.parse((String) empleadosContratos.get("fechaIngreso"), formatter);

        this.cantidad = ((BigInteger)empleadosContratos.get("cantidad")).longValue();
        this.fechaIngreso = dateTime;
    }
}
