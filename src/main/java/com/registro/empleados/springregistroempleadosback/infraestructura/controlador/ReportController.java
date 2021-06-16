package com.registro.empleados.springregistroempleadosback.infraestructura.controlador;

import com.registro.empleados.springregistroempleadosback.dominio.servicio.ReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping(value = "/api/report")
@RequiredArgsConstructor
@Slf4j
public class ReportController {

    private final ReportService reportService;

    @GetMapping(value = "/rptNotasExcel")
    public void rptNotas(HttpServletResponse response) {
        try {
            response.setHeader("Content-Disposition", "attachment;filename=UsuariosActivos.xlsx");
            response.setContentType("application/octet-stream");

            JasperPrint jasperPrint = reportService.processReport();

            JRXlsxExporter exporter = new JRXlsxExporter();
            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));

            SimpleXlsxReportConfiguration configurationReport = new SimpleXlsxReportConfiguration();
            configurationReport.setSheetNames(new String[] {"Personas"});
            configurationReport.setOnePagePerSheet(true);
            configurationReport.setDetectCellType(true);

            exporter.setConfiguration(configurationReport);
            exporter.exportReport();

        } catch (JRException | IOException e) {
            e.printStackTrace();
        }
    }

}
