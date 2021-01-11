package com.registro.empleados.springregistroempleadosback.infraestructura.modelo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
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

    private String usuario;
    private String clave;
    private String correoElectronico;
    private String estado;

    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;

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
}
