package com.registro.empleados.springregistroempleadosback.dominio.servicio;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.*;
import org.hibernate.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;

@Service
@AllArgsConstructor
@Slf4j
public class ReportService {

    @PersistenceContext
    private final EntityManager entityManager;

    @Transactional
    public JasperPrint processReport(String nombreReporte) {
        try {
            Connection connection = loadConnection(entityManager);
            JasperReport jasperReport = compileReport(nombreReporte);
            return JasperFillManager.fillReport(jasperReport, null, connection);
        } catch (JRException e) {
            e.printStackTrace();
            return null;
        }
    }

    private JasperReport compileReport(String nombreReporte) {
        try {
            File fileReport = ResourceUtils.getFile("classpath:report/" + nombreReporte + ".jrxml");
            return JasperCompileManager.compileReport(fileReport.getAbsolutePath());
        } catch (JRException jre) {
            log.error("Error compilando el reporte: ", jre.getMessage());
        } catch (FileNotFoundException file) {
            log.error("Error encontrando el reporte: ", file.getMessage());
        }
        return null;
    }

    private Connection loadConnection(EntityManager entityManager) {
        Session session = entityManager.unwrap(Session.class);
        session.doWork(connection -> log.info("Connection DB: ", connection.getSchema()));
        return session.doReturningWork(connection -> connection);
    }
}
