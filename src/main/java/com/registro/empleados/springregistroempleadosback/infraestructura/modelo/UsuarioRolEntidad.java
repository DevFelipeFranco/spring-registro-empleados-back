//package com.registro.empleados.springregistroempleadosback.infraestructura.modelo;
//
//import com.fasterxml.jackson.annotation.JsonBackReference;
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import lombok.*;
//
//import javax.persistence.*;
//import java.io.Serializable;
//
//@Entity
//@Table(name = "USUARIO_ROL", schema = "DB_REGISTRO_EMPLEADOS")
//@IdClass(UsuarioRolIdEntidad.class)
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
//public class UsuarioRolEntidad implements Serializable {
//
//    private static final long serialVersionUID = 742282237273253447L;
//
////    @Id
////    @Column(name = "ID_USUARIO")
////    private Long idUsuario;
////    @Id
////    @Column(name = "ID_ROL")
////    private Long idRol;
//
//    @Id
//    @JsonBackReference
////    @MapsId("idUsuario")
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "ID_USUARIO")
//    private UsuarioEntidad usuarioEntidad;
//
//    @Id
//    @JsonBackReference
////    @MapsId("idRol")
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "ID_ROL")
//    private RolEntidad rolEntidad;
//}
