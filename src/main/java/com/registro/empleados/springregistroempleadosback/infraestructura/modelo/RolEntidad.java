package com.registro.empleados.springregistroempleadosback.infraestructura.modelo;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "ROLES", schema = "DB_REGISTRO_EMPLEADOS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RolEntidad implements Serializable {

    private static final long serialVersionUID = -7072936500039547632L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ROL")
    private Long idRol;

    private String descripcion;

    @ManyToMany(mappedBy = "roles", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private List<UsuarioEntidad> usuarios;
}
