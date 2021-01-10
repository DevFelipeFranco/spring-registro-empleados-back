package com.registro.empleados.springregistroempleadosback.infraestructura.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioRolIdEntidad implements Serializable {

    private static final long serialVersionUID = -8949339099300844564L;

//    private Long idUsuario;
//    private Long idRol;

    private UsuarioEntidad usuarioEntidad;
    private RolEntidad rolEntidad;
}
