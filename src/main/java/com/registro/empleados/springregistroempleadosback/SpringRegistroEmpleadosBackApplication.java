package com.registro.empleados.springregistroempleadosback;

import com.registro.empleados.springregistroempleadosback.dominio.constants.FileConstant;
import com.registro.empleados.springregistroempleadosback.infraestructura.config.SwaggerConfiguration;
import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;

import java.io.File;

import static com.registro.empleados.springregistroempleadosback.dominio.constants.FileConstant.USER_FOLDER;

@SpringBootApplication
@EnableAsync
@Import(SwaggerConfiguration.class)
public class SpringRegistroEmpleadosBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringRegistroEmpleadosBackApplication.class, args);
        new File(USER_FOLDER).mkdirs();
    }

//    @Bean
//    public ServletWebServerFactory servletContainer() {
//
//        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
//            @Override
//            protected void postProcessContext(Context context) {
//                SecurityConstraint securityConstraint = new SecurityConstraint();
//                securityConstraint.setUserConstraint("CONFIDENTIAL");
//                SecurityCollection collection = new SecurityCollection();
//                collection.addPattern("/*");
//                securityConstraint.addCollection(collection);
//                context.addConstraint(securityConstraint);
//            }
//        };
//
//        tomcat.addAdditionalTomcatConnectors(httpToHttpsRedirectConnector());
//        return tomcat;
//    }
//
//    private Connector httpToHttpsRedirectConnector() {
//        Connector connector = new Connector(TomcatServletWebServerFactory.DEFAULT_PROTOCOL);
//        connector.setScheme("http");
//        connector.setPort(8081);
//        connector.setSecure(false);
//        connector.setRedirectPort(9003);
//        return connector;
//    }

}
