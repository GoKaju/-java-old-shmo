/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ocupacional.utils;

//import baseDatos.ControladorBD;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import ocupacional.bdatos.ControladorBD;
import valeria.conexion.ConexionAplicacion;


/**
 *
 * @author OperadoR
 */
public class GenerarReporte {

    HttpServletRequest request;
    String direccion;
    HttpServletResponse response;

    Map parametros;

    public GenerarReporte(HttpServletRequest request, HttpServletResponse response, String direccion, Map parematros) {
        this.request = request;
        this.response = response;
        this.direccion = direccion;

        this.parametros = parematros;

    }

    public void devuelveReporte() {

        try {
            ConexionAplicacion conn = new ConexionAplicacion(null);
//            ControladorBD controlador = new ControladorBD();

            //que tipo de respuesta sera
            response.setContentType("application/pdf");

            //para enviar respuesta al cliente
            ServletOutputStream out = this.response.getOutputStream();

            System.out.println("direccion"+request.getSession(true).getServletContext().getRealPath(this.direccion));
            //genramos y cargamos el reporte
            JasperReport reporte = (JasperReport) JRLoader.loadObject(new File(request.getSession(true).getServletContext().getRealPath(this.direccion)));

            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, this.parametros, conn.getCon());

            JRExporter exporter = new JRPdfExporter();
            exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, "reporte.pdf");
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
            exporter.exportReport();
        } catch (JRException ex) {
            ex.printStackTrace();
            Logger.getLogger(GenerarReporte.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            ex.printStackTrace();

            Logger.getLogger(GenerarReporte.class.getName()).log(Level.SEVERE, null, ex);

        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(GenerarReporte.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
