/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ocupacional.servlets.historiaclinica;

import formularios.controlers.AnotacionesJpaController;
import formularios.controlers.HuellafirmaJpaController;
import formularios.controlers.PacientesJpaController;
import formularios.controlers.RespuestasJpaController;
import formularios.entidades.Anotaciones;
import formularios.entidades.Campos;
import formularios.entidades.Categorias;
import formularios.entidades.Formularios;
import formularios.entidades.Huellafirma;
import formularios.entidades.Pacientes;
import formularios.entidades.Respuestas;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
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
import ocupacional.JPA.controlers.TicketJpaController;
import ocupacional.JPA.valueobjects.Ticket;
import ocupacional.servlets.Servlet_ctr_entidades;
import valeria.metodos.ManejadorFechas;
import valeria.response.Mediador;
import valeria.response.ObjetoRespuestaVO;
import valeria.response.RespuestaXML;

/**
 *
 * @author D4V3
 */
@WebServlet(name = "Servlet_ctr_forms", urlPatterns = {"/Formularios"})
public class Servlet_ctr_forms extends HttpServlet {

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
        ManejadorFechas f = new ManejadorFechas();
        if (e != null) {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaP1PU");
            EntityManagerFactory emf2 = Persistence.createEntityManagerFactory("JavaP");
            PacientesJpaController pacic = new PacientesJpaController(emf);
            TicketJpaController tDAO = new TicketJpaController(emf2);
            e.LimpiarErrores();
            e.o.setRequest(request);
            String action = e.o.getvariable("action");
            System.out.println(action);
            if (action.equals("procesaForm")) {
                boolean error = false;

                System.out.println("entre  procesaForm");
                Pacientes paci = (Pacientes) session.getAttribute("Paciente");
                Ticket tick = (Ticket) session.getAttribute("Ticket");
                Formularios form = (Formularios) session.getAttribute("FormularioActual");

//                crear o actualizar anotacion
                //1-crea 2-actualiza
                AnotacionesJpaController anotDAO = new AnotacionesJpaController(emf);
                Anotaciones anot = new Anotaciones();
                

                if (!e.o.getvariable("anot_id").equals("")) {

                    anot = anotDAO.findAnotaciones(Integer.parseInt(e.o.getvariable("anot_id")));

                }

                if (!e.o.getvariable("visiblexCliente").equals("")) {
                    anot.setAnotvisiblePorClie(1);
                } else {
                    anot.setAnotvisiblePorClie(0);
                }
                if (form.getFormId().equals(ConstantesReportes.CertificadoMedicoForm)) {
                    anot.setAnotvisiblePorClie(1);
                    anot.setAnotFoto(paci.getPaciFoto());
                    anot.setAnotEdad(f.getDiffDates(paci.getPaciFechanacimiento(), f.getFechaHora()));
                }

                anot.setAnotEstado("PRE EDICION");
//                anot.setAnotEstado("FINALIZADA");
                anot.setAnotFechacambio(f.getFechaHoraTimeStamp());
                anot.setAnotRegistradopor(e.getUsuarioVO().getIdUsuario());

                anotDAO.edit(anot);

                
                
//                guardar el formulario
                RespuestasJpaController respDAO = new RespuestasJpaController(emf);

//                int tamano = 0;
                    List<Respuestas> listaResp = new ArrayList<Respuestas>();
                for (Categorias cate : form.getCategoriasList()) {
//                    hacer un hilo por cada categoria
                    
//                    o.notEmpty(cate.getCateDescripcion());

//                    tamano += cate.getCamposList().size();
                    for (Campos campo : cate.getCamposList()) {
                        Respuestas resp = new Respuestas();
                        int tipo = 1;
//                           buscar respuesta en anotacion

                        for (Respuestas r : anot.getRespuestasList()) {
                            if (r.getCampId().equals(campo)) {
                                resp = r;
                                tipo = 2;
                                break;
                            }
                        }

                        if (tipo == 1) {
                            resp.setAnotId(anot);
                            resp.setCampId(campo);
                        }

                        resp.setRespFechacambio(f.getFechaHoraTimeStamp());
                        resp.setRespRegistradopor(e.getUsuarioVO().getIdUsuario());
                        String desc = "";
                        if (campo.getCampTipo().equals("text") || campo.getCampTipo().equals("audiometria")) {
                            desc = e.o.getvariable(campo.getCampName());
                        } else if (campo.getCampTipo().equals("textarea")) {
                            desc = e.o.getvariable(campo.getCampName());
                        } else if (campo.getCampTipo().equals("selectmultiple")) {
                            String[] items = request.getParameterValues(campo.getCampName());
                            if (items != null) {

                                for (String i : items) {
                                    desc += i + ",";
                                }
                                desc = desc.substring(0, desc.length() - 1);

                            }
                        } else if (campo.getCampTipo().equals("select")) {
                            desc = e.o.getvariable(campo.getCampName());

                        } else if (campo.getCampTipo().equals("hr")) {

                        } else if (campo.getCampTipo().equals("adjunto")) {
                            if (anot.getDocumentosList() == null || anot.getDocumentosList().isEmpty()) {
                                e.getObjetoRespuestaVO().setTipooperacion("error");
                                e.getObjetoRespuestaVO().setRespuesta("0");
                                e.getObjetoRespuestaVO().setHtml("El registro de los adjuntos es obligatorio. ..!!");
                                error = true;

                            }

                        } else if (campo.getCampTipo().equals("huellaFirma")) {
                            if (!anot.getHuellafirmaList().isEmpty()) {
                                Huellafirma hf = anot.getHuellafirmaList().get(0);
                                if (hf != null) {
                                    if (hf.getAnotFirma() == null || hf.getAnotFirma().isEmpty()) {
                                        e.getObjetoRespuestaVO().setTipooperacion("error");
                                        e.getObjetoRespuestaVO().setRespuesta("0");
                                        e.getObjetoRespuestaVO().setHtml("El registro de la firma es obligatorio..!!");
                                        error = true;

                                    }
                                    if (hf.getAnotHuella() == null || hf.getAnotHuella().isEmpty()) {
                                        e.getObjetoRespuestaVO().setTipooperacion("error");
                                        e.getObjetoRespuestaVO().setRespuesta("0");
                                        e.getObjetoRespuestaVO().setHtml("El registro de la huella es obligatorio..!!");
                                        error = true;
                                    }

                                } else {
                                    e.getObjetoRespuestaVO().setTipooperacion("error");
                                    e.getObjetoRespuestaVO().setRespuesta("0");
                                    e.getObjetoRespuestaVO().setHtml("los registros de huella y firma son obligatorios..!!");
                                    error = true;
                                }

                            } else {

                                e.getObjetoRespuestaVO().setTipooperacion("error");
                                e.getObjetoRespuestaVO().setRespuesta("0");
                                e.getObjetoRespuestaVO().setHtml("los registros de huella y firma son obligatorios..!!");
                                error = true;
                            }

                        } else if (campo.getCampTipo().equals("fecha")) {
                            desc = e.o.getvariable(campo.getCampName());
                        }
                        resp.setRespDescripcion(desc);
                        if (tipo == 1) {
//                            respDAO.create(resp); //1 seg
                            listaResp.add(resp);
                        } else {
                            respDAO.edit(resp); //0.001 seg

                        }
                    }
                }
                if(!listaResp.isEmpty()){

                     System.out.println("Insertar multiple:i: "+f.getFechaHoraTimeStamp());
                    respDAO.create(listaResp);
                    System.out.println("Insertar multiple:: "+f.getFechaHoraTimeStamp());
                    
                    
                }

                if (!error) {
//                 esto haria que el formulario sea visible solo si todo esta bn   
                anot.setAnotEstado("EN EDICION");
                anotDAO.edit(anot);
                    
                    e.getObjetoRespuestaVO().setTipooperacion("ejecutarhtml");
                    e.getObjetoRespuestaVO().setRespuesta("1");
                    e.getObjetoRespuestaVO().setHtml("mensajeOK('Registrado Correctamente','reloadTabla2','../panels/formularios/hc/timeLine.jsp?tick=" + tick.getTickId() + "&tipo=" + e.o.getvariable("tipo") + "');");
                }
            }  else if(action.equals("guardarHf")){

                AnotacionesJpaController anotdao = new AnotacionesJpaController(emf);
                HuellafirmaJpaController hfdao = new HuellafirmaJpaController(emf);
              
                Anotaciones anot = anotdao.findAnotaciones(Integer.parseInt(e.o.getvariable("anot")));
                Huellafirma hf = new  Huellafirma();
                for (Huellafirma h : anot.getHuellafirmaList()) {
                    hf = h;
                }

                    if (hf.getHufiId() == null && anot.getAnotId()!=null) {
              
                    hf.setAnotId(anot);
                    hfdao.create(hf);
                    }
                    
                    
            }else{
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
