package com.registro.empleados.springregistroempleadosback.infraestructura.modelo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @JsonManagedReference
    @JsonIgnoreProperties("rolEntidad")
//    @OneToMany(mappedBy = "rolEntidad", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "USUARIO_ROL",
            joinColumns = @JoinColumn(
                    name = "ID_ROL",
                    referencedColumnName = "ID_ROL"),
            inverseJoinColumns = @JoinColumn(
                    name = "ID_USUARIO",
                    referencedColumnName = "ID_USUARIO"))
    private List<UsuarioEntidad> usuarios;
}
