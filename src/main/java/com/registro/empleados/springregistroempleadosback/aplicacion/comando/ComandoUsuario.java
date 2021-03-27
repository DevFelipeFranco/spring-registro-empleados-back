package com.registro.empleados.springregistroempleadosback.aplicacion.comando;

import com.registro.empleados.springregistroempleadosback.dominio.modelo.Rol;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "con")
public class ComandoUsuario {

    private Long idUsuario;
    private String usuario;
    private String clave;
    private String email;
    private String nombres;
    private String apellidos;
    private Boolean estado;
    private Boolean snNoBloqueado;
    private String cargo;
    private String celular;
    private List<Rol> roles;
}
