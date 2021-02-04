package com.registro.empleados.springregistroempleadosback.dominio.modelo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "con")
public class Usuario {

    private Long idUsuario;
    private String usuario;
    private String clave;
    private String email;
    private Boolean estado;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fechaCreacion;
    private List<Rol> roles;
}
