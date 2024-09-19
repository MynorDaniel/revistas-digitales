/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.revistas.digitales.controladores;

import com.mycompany.revistas.digitales.backend.reportes.Ganancia;
import com.mycompany.revistas.digitales.backend.reportes.Reporte;
import com.mycompany.revistas.digitales.backend.reportes.SuscripcionReporte;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 *
 * @author mynordma
 */
@WebServlet(name = "ReporteServlet", urlPatterns = {"/ReporteServlet"})
public class ReporteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletOutputStream servletOutputStream = resp.getOutputStream();
        List<SuscripcionReporte> suscripciones = (new Reporte()).getSuscripciones();
        try {

            //JasperReport template
            InputStream reportTemplate = this.getServletConfig()
                    .getServletContext()
                    .getResourceAsStream("reports/Suscripciones.jasper");

            //Create a JRBeanCollectionDataSource from a List
            JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(suscripciones);

            //Create a map of parameters to pass to the report
            Map<String , Object> params = new HashMap<>();
            params.put("dataSource", ds);

            //Create a JasperReport object from the template
            JasperReport report = (JasperReport) JRLoader.loadObject(reportTemplate);

            //Fill the report with data and export it to a PDF file
            JasperPrint jasperPrint = JasperFillManager.fillReport(report, params, ds);
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);

            //Set the content type and attachment header.
            resp.setContentType("application/pdf");
            resp.addHeader("Content-Disposition", "inline; filename=ReporteAnuncios.pdf");

            //Close the output stream.
            servletOutputStream.flush();
            servletOutputStream.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletOutputStream servletOutputStream = resp.getOutputStream();
        List<Ganancia> ganancias = (new Reporte()).getGanancia();
        try {

            //JasperReport template
            InputStream reportTemplate = this.getServletConfig()
                    .getServletContext()
                    .getResourceAsStream("reports/admin.jasper");

            //Create a JRBeanCollectionDataSource from a List
            JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(ganancias);

            //Create a map of parameters to pass to the report
            Map<String , Object> params = new HashMap<>();
            params.put("dataSource", ds);

            //Create a JasperReport object from the template
            JasperReport report = (JasperReport) JRLoader.loadObject(reportTemplate);

            //Fill the report with data and export it to a PDF file
            JasperPrint jasperPrint = JasperFillManager.fillReport(report, params, ds);
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);

            //Set the content type and attachment header.
            resp.setContentType("application/pdf");
            resp.addHeader("Content-Disposition", "inline; filename=ReporteAnuncios.pdf");

            //Close the output stream.
            servletOutputStream.flush();
            servletOutputStream.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }
}
