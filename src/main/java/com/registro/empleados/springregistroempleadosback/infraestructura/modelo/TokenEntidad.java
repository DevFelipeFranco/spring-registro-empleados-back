package com.registro.empleados.springregistroempleadosback.infraestructura.modelo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "TOKEN", schema = "DB_REGISTRO_EMPLEADOS")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TokenEntidad implements Serializable {

    private static final long serialVersionUID = 2883475298696767290L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idToken;

    private String token;

    @Column(name = "FECHA_EXPIRACION", columnDefinition = "TIMESTAMP")
    private LocalDateTime fechaExpiracion;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_USUARIO")
    private UsuarioEntidad usuarioEntidad;
}
