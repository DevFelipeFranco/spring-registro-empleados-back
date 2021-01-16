package com.registro.empleados.springregistroempleadosback.infraestructura.modelo;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "USUARIOS", schema = "DB_REGISTRO_EMPLEADOS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioEntidad implements Serializable {

    private static final long serialVersionUID = -3747748698614828470L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_USUARIO")
    private Long idUsuario;

    @Column(name = "USUARIO", unique = true)
    private String usuario;
    private String clave;
    private String correoElectronico;
    private String estado;

    @Column(name = "FECHA_CREACION", columnDefinition = "TIMESTAMP")
    private LocalDateTime fechaCreacion;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinTable(
            name = "USUARIO_ROL",
            joinColumns = @JoinColumn(
                    name = "ID_USUARIO",
                    referencedColumnName = "ID_USUARIO"),
            inverseJoinColumns = @JoinColumn(
                    name = "ID_ROL",
                    referencedColumnName = "ID_ROL"))
    private List<RolEntidad> roles;

    @OneToOne(mappedBy = "usuarioEntidad", cascade = CascadeType.ALL)
    private TokenEntidad tokenEntidad;
}
