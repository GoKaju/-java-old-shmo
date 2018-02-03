/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ocupacional.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ocupacional.bdatos.facturacion.ExamenesDAO;
import ocupacional.valueobjects.facturacion.ExamenesVO;
import valeria.metodos.ManejadorFechas;
import valeria.response.Mediador;
import valeria.response.ObjetoRespuestaVO;
import valeria.response.RespuestaXML;

/**
 *
 * @author D4V3
 */
@WebServlet(name = "Servlet_ctr_examenes", urlPatterns = {"/Examenes"})
public class Servlet_ctr_examenes extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        response.setContentType("text/xml;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        Mediador e = (Mediador) session.getAttribute("Mediador");
        ManejadorFechas fechas = new ManejadorFechas();
        if (e != null) {
            e.LimpiarErrores();
            e.o.setRequest(request);
            String action = e.o.getvariable("action");
            System.out.println(action);
            if (action.equals("newExamen")) {
                String te = e.o.getvariable("te");
                String nombre = e.o.getvariable("nd");
                String Observacion = e.o.getvariable("ob");
                ExamenesVO ent = new ExamenesVO();
                ent.setExam_tipo(te);
                ent.setExam_descripcion(nombre);
                ent.setExam_observacion(Observacion);
                ent.setExam_registradopor(e.getUsuarioVO().getIdpersona());
                ent.setExam_fechacambio(fechas.getFechaHoraTimeStamp());
                if (new ExamenesDAO(e).Insertar(ent)) {

                    e.getObjetoRespuestaVO().setTipooperacion("ejecutarhtml");
                    e.getObjetoRespuestaVO().setRespuesta("1");
                    e.getObjetoRespuestaVO().setHtml("mensajeOK('Insertado Correctamente','reloadTabla','../panels/formularios/basicas/examenes.jsp');");

                } else {
                    e.getObjetoRespuestaVO().setTipooperacion("error");
                    e.getObjetoRespuestaVO().setRespuesta("0");
                    e.getObjetoRespuestaVO().setHtml("La operación no se realizo correctamente..!!");

                }
 } else if (action.equals("editExamen")) {
                 String te = e.o.getvariable("te");
                String nombre = e.o.getvariable("nd");
                String Observacion = e.o.getvariable("ob");
                ExamenesVO ent = new ExamenesVO();
                ent.setExam_tipo(te);
                ent.setExam_descripcion(nombre);
                ent.setExam_observacion(Observacion);
                ent.setExam_registradopor(e.getUsuarioVO().getIdpersona());
                ent.setExam_fechacambio(fechas.getFechaHoraTimeStamp());
                ent.setExam_id(e.o.getvariable("id"));
                ent.setExam_estado(e.o.getvariable("est"));
                if (new ExamenesDAO(e).Actualizar(ent)) {

                    e.getObjetoRespuestaVO().setTipooperacion("ejecutarhtml");
                    e.getObjetoRespuestaVO().setRespuesta("1");
                    e.getObjetoRespuestaVO().setHtml("mensajeOK('Actualizado Correctamente','reloadTabla','../panels/formularios/basicas/examenes.jsp');");

                }else {
                    e.getObjetoRespuestaVO().setTipooperacion("error");
                    e.getObjetoRespuestaVO().setRespuesta("0");
                    e.getObjetoRespuestaVO().setHtml("La operación no se realizo correctamente..!!");

                }
     
     
     
     
            } else if (action.equals("addServicio")) {

                String te = e.o.getvariable("te");
                String nombre = e.o.getvariable("nd");
                String id = e.o.getvariable("id");
                String Observacion = e.o.getvariable("ob");
                String est = e.o.getvariable("est");
                ExamenesVO ent = new ExamenesVO();
                ent.setExam_id(id);
                ent.setExam_tipo(te);
                ent.setExam_descripcion(nombre);
                ent.setExam_observacion(Observacion);
                ent.setExam_estado(est);
                ent.setExam_registradopor(e.getUsuarioVO().getIdpersona());
                ent.setExam_fechacambio(fechas.getFechaHoraTimeStamp());

                if (new ExamenesDAO(e).Actualizar(ent)) {
                    System.out.println("entre");

                    e.getObjetoRespuestaVO().setTipooperacion("ejecutarhtml");
                    e.getObjetoRespuestaVO().setRespuesta("1");
                    e.getObjetoRespuestaVO().setHtml("mensajeOK('Actualizado Correctamente','reloadTabla','../panels/formularios/basicas/examenes.jsp');");

                } else {
                    e.getObjetoRespuestaVO().setTipooperacion("error");
                    e.getObjetoRespuestaVO().setRespuesta("0");
                    e.getObjetoRespuestaVO().setHtml("La operación no se realizo correctamente..!!");

                }

            } else if (action.equals("eliminarExamen")) {
                String id = e.o.getvariable("id");
                ExamenesVO ent = new ExamenesVO();
                ent.setExam_id(id);

                if (new ExamenesDAO(e).Eliminar(ent)) {
                    e.getObjetoRespuestaVO().setTipooperacion("ejecutarhtml");
                    e.getObjetoRespuestaVO().setRespuesta("1");
                    e.getObjetoRespuestaVO().setHtml("mensajeOK('Eliminado Correctamente','reloadTabla2','../panels/formularios/basicas/examenes.jsp');");

                } else {

                    e.getObjetoRespuestaVO().setTipooperacion("error");
                    e.getObjetoRespuestaVO().setRespuesta("0");
                    e.getObjetoRespuestaVO().setHtml("La operación no se realizo correctamente..!!");
                }

            } else {
                e.getObjetoRespuestaVO().setTipooperacion("ejecutarhtml");
                e.getObjetoRespuestaVO().setRespuesta("1");
                e.getObjetoRespuestaVO().setHtml("alert('no hay accion')");

            }

        } else {
            ObjetoRespuestaVO ObjetoRespuestaVO = new ObjetoRespuestaVO();
            e = new Mediador(ObjetoRespuestaVO);
            e.getObjetoRespuestaVO().setRespuesta("0");
            e.getObjetoRespuestaVO().setTipooperacion("error");
            e.getObjetoRespuestaVO().setHtml("Variable de Sesion no encontrada");
        }

        System.out.println(new RespuestaXML().GenerarXML(e.getObjetoRespuestaVO()));
        out.print(new RespuestaXML().GenerarXML(e.getObjetoRespuestaVO()));
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(Servlet_ctr_entidades.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(Servlet_ctr_entidades.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
