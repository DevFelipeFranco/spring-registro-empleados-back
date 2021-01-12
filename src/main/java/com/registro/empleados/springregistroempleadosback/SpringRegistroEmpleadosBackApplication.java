package com.registro.empleados.springregistroempleadosback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SpringRegistroEmpleadosBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringRegistroEmpleadosBackApplication.class, args);
    }

}
