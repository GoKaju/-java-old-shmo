/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ocupacional.servlets;

import com.google.gson.Gson;
import formularios.controlers.AnotacionesJpaController;
import formularios.controlers.DocumentosJpaController;
import formularios.controlers.HuellafirmaJpaController;
import formularios.controlers.PacientesJpaController;
import formularios.controlers.RespuestasJpaController;
import formularios.controlers.SincroPacientesJpaController;
import formularios.entidades.Anotaciones;
import formularios.entidades.Documentos;
import formularios.entidades.Huellafirma;
import formularios.entidades.Pacientes;
import formularios.entidades.Respuestas;
import formularios.entidades.SincroPacientes;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ocupacional.JPA.controlers.SincroTicketJpaController;
import ocupacional.JPA.controlers.TicketClienteservicioJpaController;
import ocupacional.JPA.controlers.TicketJpaController;
import ocupacional.JPA.valueobjects.SincroTicket;
import ocupacional.JPA.valueobjects.Ticket;
import ocupacional.JPA.valueobjects.TicketClienteservicio;

import valeria.metodos.ManejadorFechas;
import valeria.response.Mediador;
import valeria.response.ObjetoRespuestaVO;
import valeria.response.RespuestaXML;

/**
 *
 * @author Alejandro
 */
@WebServlet(name = "Servlet_ctr_sincro", urlPatterns = {"/Sincronizacion"})
public class Servlet_ctr_sincro extends HttpServlet {

  
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
      try{
        if (e != null) {
            e.LimpiarErrores();
            e.o.setRequest(request);
            String action = e.o.getvariable("action");
            System.out.println(action);
            if (action.equals("sincronizar")) {
                
                EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaP");
                 EntityManagerFactory emfhc = Persistence.createEntityManagerFactory("JavaP1PU");
                 
                EntityManagerFactory emf_rem = Persistence.createEntityManagerFactory("JavaPREMOTO");
                 EntityManagerFactory emfhc_rem = Persistence.createEntityManagerFactory("JavaP1PUREMOTO");
                Gson gson = new Gson();
                
                
//                aqui se realiza la sincronizacion
                boolean b = true;
//                primero los tickets
                            
                        System.out.println("#############################################");
                        System.out.println("sincronizacion "+ new ManejadorFechas().getFechaHoraTimeStamp());
                        System.out.println("#############################################");

                    Iterator <SincroTicket> it = new SincroTicketJpaController(emf).findSincroTicketEntities().iterator();
                    int cont = 1;
                    int cont2 = 1;
                   
                    while (it.hasNext()) {
                        cont++;
                        SincroTicket s = it.next();
                       Ticket t = new TicketJpaController(emf).findTicket(s.getTickId());
                    if(t!= null){
//                      validar si el paciente es nuevo
//                        named query
                        EntityManager emhc = emfhc.createEntityManager();
                    TypedQuery<SincroPacientes> consultaDocumento = emhc.createNamedQuery("SincroPacientes.findByPaciId", SincroPacientes.class);
                    consultaDocumento.setParameter("paciId", new PacientesJpaController(emfhc).findPacientes(t.getTickPaciente()).getPaciId());
                    List<SincroPacientes> lista = consultaDocumento.getResultList();
                        if(lista != null && !lista.isEmpty()){
                        // debo enviar el paciente a la bd
                            Pacientes p = new PacientesJpaController(emfhc).findPacientes(t.getTickPaciente());
                            p.setPaciId(null);
                            
//                        con json no funciona toca campo por campo :(
                            Pacientes p2 = new Pacientes();
                            p2.setPaciApellidos(p.getPaciApellidos());
                            p2.setPaciArl(p.getPaciArl());
                            p2.setPaciDireccion(p.getPaciDireccion());
                            p2.setPaciDocumento(p.getPaciDocumento());
                            p2.setPaciDominancia(p.getPaciDominancia());
                            p2.setPaciEcivil(p.getPaciEcivil());
                            p2.setPaciEps(p.getPaciEps());
                            p2.setPaciEscolaridad(p.getPaciEscolaridad());
                            p2.setPaciFechacambio(p.getPaciFechacambio());
                            p2.setPaciFechanacimiento(p.getPaciFechanacimiento());
//                            p2.setPaciFirma(p.getPaciFirma());
                            p2.setPaciGenero(p.getPaciGenero());
//                            p2.setPaciHuella(p.getPaciHuella());
                            p2.setPaciNombres(p.getPaciNombres());
                            p2.setPaciObservaciones(p.getPaciObservaciones());
                            p2.setPaciRegistradopor(p.getPaciRegistradopor());
                            p2.setPaciRh(p.getPaciRh());
                            p2.setPaciTelefono(p.getPaciTelefono());
                            new PacientesJpaController(emfhc_rem).create(p2);
                            t.setTickPaciente(p2.getPaciId());
                            System.out.println("Se creo el paciente en REMOTO id "+ p2.getPaciId());
                        
                        }else{
                          // significa q el paciente ya esta el el server el id es igual 
                            
                        }
                        
//                      ****************************
                        List<TicketClienteservicio> l = t.getTicketClienteservicioList();
                        Ticket tt = new Ticket();
                        tt.setCecoId(t.getCecoId());
                        tt.setSedeId(t.getSedeId());
                        tt.setTemeId(t.getTemeId());
                        tt.setTickEstado(t.getTickEstado());
                        tt.setTickFechacambio(t.getTickFechacambio());
                        tt.setTickFechaprocesado(t.getTickFechaprocesado());
                        tt.setTickFecharecepcion(t.getTickFecharecepcion());
                        tt.setTickFecharegistro(t.getTickFecharegistro());
                        tt.setTickPaciente(t.getTickPaciente());
                        tt.setTickRegistradopor(t.getTickRegistradopor());
                      
                        new TicketJpaController(emf_rem).create(tt);
                          System.out.println(">>>>>>>>>>>>>>>>>>>>>> "+cont2+" Se creo el ticket en REMOTO id "+ tt.getTickId());

                        for(TicketClienteservicio tcl : l){
                          TicketClienteservicio tcl2 = new TicketClienteservicio();
                        tcl2.setTickId(tt);
                        tcl2.setClseId(tcl.getClseId());
                        tcl2.setTicsFechacambio(tcl.getTicsFechacambio());
                        tcl2.setTicsRegistradopor(tcl.getTicsRegistradopor());
                        new TicketClienteservicioJpaController(emf_rem).create(tcl2);
                        System.out.println("Se creo el ticketClienteServicio en REMOTO id "+ tcl2.getTicsId());

                        // se debe enviar las anotaciones por cada tcs
                          EntityManager emhc2 = emfhc.createEntityManager();
                    TypedQuery<Anotaciones> consultaanot = emhc2.createNamedQuery("Anotaciones.t", Anotaciones.class);
                    consultaanot.setParameter("id", tcl.getTicsId());
                    List<Anotaciones> listaAnot = consultaanot.getResultList();
                        for(Anotaciones a : listaAnot){
                        Anotaciones a2 = new Anotaciones();
                        a2.setTicsId(tcl2.getTicsId());
                        a2.setAnotEstado(a.getAnotEstado());
                        a2.setAnotFechacambio( a.getAnotFechacambio());
                        a2.setAnotRegistradopor(a.getAnotRegistradopor());
                        a2.setFormId(a.getFormId());
                        a2.setPaciId(new PacientesJpaController(emfhc_rem).findPacientes(tt.getTickPaciente()));
                      
                        new AnotacionesJpaController(emfhc_rem).create(a2);
                       System.out.println("Se creo la anotacion en REMOTO id "+ a2.getAnotId());
                      // respuestas 
                       for(Respuestas r : a.getRespuestasList()){
                       Respuestas r2  = new Respuestas();
                       r2.setAnotId(a2);
                       r2.setCampId(r.getCampId());
                       r2.setRespDescripcion(r.getRespDescripcion());
                       r2.setRespFechacambio(r.getRespFechacambio());
                       r2.setRespObservacion(r.getRespObservacion());
                       r2.setRespRegistradopor(r.getRespRegistradopor());
                       new RespuestasJpaController(emfhc_rem).create(r2);
                        System.out.println("Se creo la respuesta  en REMOTO id "+ r.getRespId());
                        
                       }
                      // huella firma
                       for(Huellafirma r : a.getHuellafirmaList()){
                       Huellafirma r2  = new Huellafirma();
                       r2.setAnotId(a2);
                       r2.setAnotFechacambio(r.getAnotFechacambio());
                       r2.setAnotFirma(r.getAnotFirma());
                       r2.setAnotHuella(r.getAnotHuella());
                       r2.setAnotRegistradopor(r.getAnotRegistradopor());
                       new HuellafirmaJpaController(emfhc_rem).create(r2);
                        System.out.println("Se creo la huella firma  en REMOTO id "+ r.getHufiId());
                        
                       }
                      //documentos aunque no se puede enviar el archivo como tal 
                       for(Documentos r : a.getDocumentosList()){
                       Documentos r2  = new  Documentos();
                       r2.setAnotId(a2);
                       r2.setDocFechacambio(r.getDocFechacambio());
                       r2.setDocuExtencion(r.getDocuExtencion());
                       r2.setDocuNombre("SINCRONIZACION");
                       r2.setDocuPeso(r.getDocuPeso());
                       r2.setDocuRegistradopor(r.getDocuRegistradopor());
                       r2.setDocuRuta(r.getDocuRuta());
                       new DocumentosJpaController(emfhc_rem).create(r2);
                        System.out.println("Se creo el Documento en REMOTO id "+ r.getDocuId());
                        
                       }
                        
                        }  
                      }
                    }
//                    borrar los sincro paciente 
                      Iterator <SincroPacientes> itp = new SincroPacientesJpaController(emfhc).findSincroPacientesEntities().iterator();
                   
                    while (itp.hasNext()) {
                        cont++;
                        SincroPacientes sp = itp.next();
                   new SincroPacientesJpaController(emfhc).destroy(sp.getSipaId());
                    }
                    
                    new SincroTicketJpaController(emf).destroy(s.getSincId());
                        it.remove();
                    }
            
                
                
              if(b){
                e.getObjetoRespuestaVO().setTipooperacion("ejecutarhtml");
                    e.getObjetoRespuestaVO().setRespuesta("1");
                    e.getObjetoRespuestaVO().setHtml("mensajeOK('Sincronizado correctamente ','reloadTabla','../Sincro.jsp');");
                   
              }else
                {

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
      }catch(Exception ex){ex.printStackTrace();}

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