package com.registro.empleados.springregistroempleadosback.dominio.modelo;

import com.registro.empleados.springregistroempleadosback.infraestructura.modelo.TokenEntidad;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "con")
public class UsuarioModelo {

    private Long idUsuario;
    private String usuario;
    private String clave;
    private String email;
    private Boolean estado;
    private Date fechaCreacion;
    private List<RolModelo> lstUsuarioRol;
    private TokenModelo tokenModelo;
}
