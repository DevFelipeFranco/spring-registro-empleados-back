package com.registro.empleados.springregistroempleadosback.infraestructura.modelo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Date;

@Entity
@Table(name = "PERSONAS", schema = "DB_REGISTRO_EMPLEADOS")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonaEntidad implements Serializable {

    private static final long serialVersionUID = -5460165167174724200L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPersona;

    private Integer identificacion;
    private String primerNombre;
    private String segundoNombre;
    private String primerApellido;
    private String segundoApellido;

    @Column(name = "FECHA_NACIMIENTO", columnDefinition = "TIMESTAMP")
    private LocalDateTime fechaNacimiento;

    @Transient
    private int edad;

    private String correoElectronico;
    private String direccion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_TIPO_DOCUMENTO")
    private TipoDocumentoEntidad tipoDocumento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_USUARIO")
    private UsuarioEntidad usuarioEntidad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_GENERO")
    private GeneroEntidad generoEntidad;

    @Column(name = "FECHA_CREACION", columnDefinition = "TIMESTAMP")
    private LocalDateTime fechaCreacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_CLIENTE")
    private ClienteEntidad clienteEntidad;

    @Column(name = "SNPERSONA_ACTIVA")
    private String snPersonaActiva;

    public int getEdad() {
        return Period.between(fechaNacimiento.toLocalDate(), LocalDate.now()).getYears();
    }
}
