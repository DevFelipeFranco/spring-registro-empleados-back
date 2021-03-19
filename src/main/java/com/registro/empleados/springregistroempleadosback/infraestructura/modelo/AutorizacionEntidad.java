package com.registro.empleados.springregistroempleadosback.infraestructura.modelo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "AUTORIZACION", schema = "DB_REGISTRO_EMPLEADOS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AutorizacionEntidad implements Serializable {

    private static final long serialVersionUID = 6118850073368242222L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAutorizacion;

    private String autorizacion;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ROL")
    private RolEntidad rolEntidad;
}
