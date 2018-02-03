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
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ocupacional.JPA.controlers.ClientesServicioJpaController;
import ocupacional.JPA.controlers.ServiciosJpaController;
import ocupacional.JPA.valueobjects.ClientesServicio;
import ocupacional.JPA.valueobjects.Servicios;
import ocupacional.bdatos.facturacion.ExamenesDAO;
import ocupacional.bdatos.facturacion.ServiciosDAO;
import ocupacional.bdatos.facturacion.ServiciosExamenesDAO;
import ocupacional.valueobjects.facturacion.ExamenesVO;
import ocupacional.valueobjects.facturacion.ServiciosExamenesVO;
import ocupacional.valueobjects.facturacion.ServiciosVO;
import valeria.metodos.ManejadorFechas;
import valeria.response.Mediador;
import valeria.response.ObjetoRespuestaVO;
import valeria.response.RespuestaXML;

/**
 *
 * @author D4V3
 */
@WebServlet(name = "Servlet_ctr_servicios", urlPatterns = {"/Servicios"})
public class Servlet_ctr_servicios extends HttpServlet {

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
            if (action.equals("newServicio")) {
                 
              
                 String nombre = e.o.getvariable("nd");
                String Observacion = e.o.getvariable("ob");
                
                    boolean bandera = false;
                String exam[]= request.getParameterValues("exam");
             if(exam == null){exam = new String[0]; bandera= true;}
                
                ServiciosVO ent = new ServiciosVO();
                
                ent.setServ_nombre(nombre);
                ent.setServ_observacion(Observacion);
                ent.setServ_registradopor(e.getUsuarioVO().getIdpersona());
                ent.setServ_fechacambio(fechas.getFechaHoraTimeStamp());
                if (new ServiciosDAO(e).Insertar(ent)) {

//                    Insertar examenes Servicio
                    
                    for (String exam1 : exam) {
                        ServiciosExamenesVO s= new ServiciosExamenesVO();
                        s.setServ_id(ent.getServ_id());
                        s.setExam_id(exam1);
                        s.setSeex_registradopor(e.getUsuarioVO().getIdpersona());
                        s.setSeex_fechacambio(fechas.getFechaHoraTimeStamp());
                        
                        if(new ServiciosExamenesDAO(e).Insertar(s)){
                        bandera= true;
                        }else{
                        bandera= false;
                        break;
                        }
                          
                    }
                    
                    if(bandera){
                    e.getObjetoRespuestaVO().setTipooperacion("ejecutarhtml");
                    e.getObjetoRespuestaVO().setRespuesta("1");
                    e.getObjetoRespuestaVO().setHtml("mensajeOK('Insertado Correctamente','reloadTabla','../panels/formularios/basicas/servicios.jsp');");
                    }else{
                    e.getObjetoRespuestaVO().setTipooperacion("error");
                    e.getObjetoRespuestaVO().setRespuesta("0");
                    e.getObjetoRespuestaVO().setHtml("La operación no se realizo correctamente..!!");
                    
                    }
                } else {
                    e.getObjetoRespuestaVO().setTipooperacion("error");
                    e.getObjetoRespuestaVO().setRespuesta("0");
                    e.getObjetoRespuestaVO().setHtml("La operación no se realizo correctamente..!!");

                }

            } else if (action.equals("editServicio")) {

                String id = e.o.getvariable("id");
              
                 String nombre = e.o.getvariable("nd");
                String Observacion = e.o.getvariable("ob");
                String est = e.o.getvariable("est");
                
                String exam[]= request.getParameterValues("exam");
             
                
                ServiciosVO ent = new ServiciosVO();
                ent.setServ_id(id);
                ent.setServ_nombre(nombre);
                ent.setServ_observacion(Observacion);
                ent.setServ_estado(est);
                ent.setServ_registradopor(e.getUsuarioVO().getIdpersona());
                ent.setServ_fechacambio(fechas.getFechaHoraTimeStamp());

                if (new ServiciosDAO(e).Actualizar(ent)) {
                    System.out.println("entre");
                    
//                    borrar examenesservicio
                    
                    if(new ServiciosExamenesDAO(e).EliminarTodos(id)){
                    
                    //                    Insertar examenes Servicio
                    boolean bandera = false;
                    
                    for (String exam1 : exam) {
                        ServiciosExamenesVO s= new ServiciosExamenesVO();
                        s.setServ_id(ent.getServ_id());
                        s.setExam_id(exam1);
                        s.setSeex_registradopor(e.getUsuarioVO().getIdpersona());
                        s.setSeex_fechacambio(fechas.getFechaHoraTimeStamp());
                        
                        if(new ServiciosExamenesDAO(e).Insertar(s)){
                        bandera= true;
                        }else{
                        bandera= false;
                        break;
                        }
                          
                    }
                    
                    if(bandera){
                    e.getObjetoRespuestaVO().setTipooperacion("ejecutarhtml");
                    e.getObjetoRespuestaVO().setRespuesta("1");
                    e.getObjetoRespuestaVO().setHtml("mensajeOK('Actualizado Correctamente','reloadTabla','../panels/formularios/basicas/servicios.jsp');");
                    }else{
                    e.getObjetoRespuestaVO().setTipooperacion("error");
                    e.getObjetoRespuestaVO().setRespuesta("0");
                    e.getObjetoRespuestaVO().setHtml("La operación no se realizo correctamente..!!");
                    
                    }}
                } else {
                    e.getObjetoRespuestaVO().setTipooperacion("error");
                    e.getObjetoRespuestaVO().setRespuesta("0");
                    e.getObjetoRespuestaVO().setHtml("La operación no se realizo correctamente..!!");

                }

            } else if (action.equals("eliminarServicio")) {
                String id = e.o.getvariable("id");
                EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaP");
                 Servicios serv = new ServiciosJpaController(emf).findServicios(Integer.parseInt(id));
                 
                 serv.setServEstado("ELIMINADO");
                 serv.setServFechacambio(fechas.getFechaHoraTimeStamp());
                 serv.setServRegistradopor(e.getUsuarioVO().getIdUsuario());
                 new ServiciosJpaController(emf).edit(serv);
              
                 for(ClientesServicio cs : serv.getClientesServicioList()){
                 cs.setClseEstado("INACTIVO");
                 cs.setClseFechacambio(fechas.getFechaHoraTimeStamp());
                 cs.setClseRegistradopor(e.getUsuarioVO().getIdUsuario());
                 new ClientesServicioJpaController(emf).edit(cs);
                 
                 }
                 
                 
                    e.getObjetoRespuestaVO().setTipooperacion("ejecutarhtml");
                    e.getObjetoRespuestaVO().setRespuesta("1");
                    e.getObjetoRespuestaVO().setHtml("mensajeOK('Eliminado Correctamente','reloadTabla2','../panels/formularios/basicas/servicios.jsp');");

           

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
            Logger.getLogger(Servlet_ctr_servicios.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Servlet_ctr_servicios.class.getName()).log(Level.SEVERE, null, ex);
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