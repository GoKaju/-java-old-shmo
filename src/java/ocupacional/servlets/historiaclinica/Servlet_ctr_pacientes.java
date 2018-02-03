/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ocupacional.servlets.historiaclinica;

import formularios.controlers.AnotacionesJpaController;
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
import java.util.List;
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
@WebServlet(name = "Servlet_ctr_pacientes", urlPatterns = {"/Pacientes"})
public class Servlet_ctr_pacientes extends HttpServlet {

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
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaP1PU");
            EntityManagerFactory emf2 = Persistence.createEntityManagerFactory("JavaP");
            EntityManager em = emf.createEntityManager();
            try{
        if (e != null) {
            PacientesJpaController pacic = new PacientesJpaController(emf);
            TicketJpaController tDAO = new TicketJpaController(emf2);
            e.LimpiarErrores();
            e.o.setRequest(request);
            String action = e.o.getvariable("action");
            System.out.println("Pacientes Esta es la accion " + action);
            if (action.equals("actualizarPaciente")) {
                System.out.println("entre actualizarPaciente");
                if (!e.o.getvariable("paci_foto").isEmpty()) {
                    int tick = Integer.parseInt(e.o.getvariable("tick_id"));
                    Ticket t = tDAO.findTicket(tick);
                    Pacientes paci = pacic.findPacientes(t.getTickPaciente());
                    paci.setPaciNombres(e.o.getvariable("nombres"));
                    paci.setPaciFoto(e.o.getvariable("paci_foto"));
                    paci.setPaciApellidos(e.o.getvariable("apellidos"));
//                paci.setPaciFechanacimiento(f.StringToTimeStamp(e.o.getvariable("fechaNacimiento")));
                    paci.setPaciFechanacimiento(f.retornaDate(e.o.getvariable("fechaNacimiento")));
                    paci.setPaciEcivil(e.o.getvariable("estadoCivil"));
                    paci.setPaciRh(e.o.getvariable("rh"));
                    paci.setPaciEps(e.o.getvariable("eps"));
                    paci.setPaciArl(e.o.getvariable("arl"));
                    paci.setPaciEscolaridad(e.o.getvariable("Escolaridad"));
                    paci.setPaciDominancia(e.o.getvariable("Dominancia"));
                    paci.setPaciGenero(e.o.getvariable("Genero"));
                    paci.setCiudId(Integer.parseInt(e.o.getvariable("ciudad")));
                    paci.setPaciDireccion(e.o.getvariable("pege_direccion"));
                    paci.setPaciTelefono(e.o.getvariable("pege_telefono"));
                    paci.setPaciObservaciones(e.o.getvariable("pege_observacion"));
                    paci.setPaciRegistradopor(e.getUsuarioVO().getIdUsuario());
                    paci.setPaciFechacambio(f.getFechaHoraTimeStamp());
                    pacic.edit(paci);
                    if (paci.getPaciId() != null) {
                        session.removeAttribute("Paciente");
                        session.setAttribute("Paciente", paci);
                        session.removeAttribute("Ticket");
                        t.setTickEstado("PROCESANDO");
                        t.setTickFecharecepcion(t.getTickFecharecepcion() == null ? f.getFechaHoraTimeStamp() : t.getTickFecharecepcion());
                        //DG 20171018 : agregar emple id del auxiliar al campo emple_idauxiliar
                        System.out.println("DG 20171018 : agregar emple id del auxiliar al campo emple_idauxiliar ::: "+ t.getEmplIdauxiliar());
                        if (t.getEmplIdauxiliar() == null) {
                           try{
                                t.setEmplIdauxiliar(Integer.parseInt(e.getUsuarioVO().getIdpersona()));
                           }catch(Exception ex){
                           ex.printStackTrace();}
                        }

                        tDAO.edit(t);
                        session.setAttribute("Ticket", t);
                        e.getObjetoRespuestaVO().setTipooperacion("ejecutarhtml");
                        e.getObjetoRespuestaVO().setRespuesta("1");
                        e.getObjetoRespuestaVO().setHtml("  mensajeOK('Actualizado Correctamente','reloadTabla','../panels/formularios/hc/timeLine.jsp?tipo=aux&tick=" + tick + "');");

                    } else {
                        e.getObjetoRespuestaVO().setTipooperacion("error");
                        e.getObjetoRespuestaVO().setRespuesta("0");
                        e.getObjetoRespuestaVO().setHtml("La operación no se realizo correctamente..!!");

                    }
                } else {
                    e.getObjetoRespuestaVO().setTipooperacion("error");
                    e.getObjetoRespuestaVO().setRespuesta("0");
                    e.getObjetoRespuestaVO().setHtml("Debe tomar la fotografía al paciente.!");
                }
            } else if (action.equals("timelineMedico")) {

                int tick = Integer.parseInt(e.o.getvariable("tick_id"));
                Ticket t = tDAO.findTicket(tick);
                Pacientes paci = pacic.findPacientes(t.getTickPaciente());

                if (paci.getPaciId() != null) {
                    session.removeAttribute("Paciente");
                    session.setAttribute("Paciente", paci);
                    session.removeAttribute("Ticket");
                    session.setAttribute("Ticket", t);
                    e.getObjetoRespuestaVO().setTipooperacion("ejecutarhtml");
                    e.getObjetoRespuestaVO().setRespuesta("1");
                    e.getObjetoRespuestaVO().setHtml("mensajeOK('Cargado Correctamente','reloadTabla','../panels/formularios/hc/timeLine.jsp?tipo=medico&tick=" + tick + "');");

                }

            } else if (action.equals("timelineLaboratorio")) {

                int tick = Integer.parseInt(e.o.getvariable("tick_id"));
                Ticket t = tDAO.findTicket(tick);
                Pacientes paci = pacic.findPacientes(t.getTickPaciente());

                if (paci.getPaciId() != null) {
                    session.removeAttribute("Paciente");
                    session.setAttribute("Paciente", paci);
                    session.removeAttribute("Ticket");
                    session.setAttribute("Ticket", t);
                    e.getObjetoRespuestaVO().setTipooperacion("ejecutarhtml");
                    e.getObjetoRespuestaVO().setRespuesta("1");
                    e.getObjetoRespuestaVO().setHtml("mensajeOK('Cargado Correctamente','reloadTabla','../panels/formularios/hc/timeLine.jsp?tipo=laboratorio&tick=" + tick + "');");

                }

            } else if (action.equals("procesaForm")) {
                boolean error = false;

                System.out.println("entre  procesaForm");
                System.out.println(":::::::::::::::::::::::::");
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

                anot.setAnotEstado("EN EDICION");
//                anot.setAnotEstado("FINALIZADA");
                anot.setAnotFechacambio(f.getFechaHoraTimeStamp());
                anot.setAnotRegistradopor(e.getUsuarioVO().getIdUsuario());

                anotDAO.edit(anot);

//                guardar el formulario
                RespuestasJpaController respDAO = new RespuestasJpaController(emf);

//                int tamano = 0;
                for (Categorias cate : form.getCategoriasList()) {
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
                            System.out.println("Insertar:i: " + f.getFechaHoraTimeStamp());
                            respDAO.create(resp);
                            System.out.println("Insertar:: " + f.getFechaHoraTimeStamp());
                        } else {
                            System.out.println("Editar:i: " + f.getFechaHoraTimeStamp());
                            respDAO.edit(resp);
                            System.out.println("Editar:: " + f.getFechaHoraTimeStamp());

                        }
                    }
                }
                System.out.println("finalize  procesaForm");
                System.out.println(":::::::::::::::::::::::::");

                if (!error) {
                    e.getObjetoRespuestaVO().setTipooperacion("ejecutarhtml");
                    e.getObjetoRespuestaVO().setRespuesta("1");
                    e.getObjetoRespuestaVO().setHtml("mensajeOK('Registrado Correctamente','reloadTabla2','../panels/formularios/hc/timeLine.jsp?tick=" + tick.getTickId() + "&tipo=" + e.o.getvariable("tipo") + "');");
                }
            } else if (action.equals("actualizarConceptoRespuesta")) {
                String id = e.o.getvariable("id");
                String val = e.o.getvariable("val");
                Respuestas r = new RespuestasJpaController(emf).findRespuestas(Integer.parseInt(id));

                if (r != null) {
                    r.setRespDescripcion(val);
                    new RespuestasJpaController(emf).edit(r);

                    e.getObjetoRespuestaVO().setTipooperacion("ejecutarhtml");
                    e.getObjetoRespuestaVO().setRespuesta("1");
                    e.getObjetoRespuestaVO().setHtml("alertify.success('Actualizado correctamente.')");

                }
//                ent.setExam_id(id);
//
//                if (new ExamenesDAO(e).Eliminar(ent)) {
//                } else {
//
//                    e.getObjetoRespuestaVO().setTipooperacion("error");
//                    e.getObjetoRespuestaVO().setRespuesta("0");
//                    e.getObjetoRespuestaVO().setHtml("La operación no se realizo correctamente..!!");
//                }

            } else if (action.equals("busPaciente")) {

                String doc = e.o.getvariable("doc");
                if (!doc.equals("")) {
                    
                    TypedQuery<Pacientes> consultaDocumento = em.createNamedQuery("Pacientes.findByPaciDocumento", Pacientes.class);
                    consultaDocumento.setParameter("paciDocumento", doc);
                    List<Pacientes> lista = consultaDocumento.getResultList();
                    Pacientes paci = null;
                    for (Pacientes p : lista) {
                        paci = p;
                    }
                    if (paci != null) {

                        e.getObjetoRespuestaVO().setTipooperacion("ejecutarhtml");
                        e.getObjetoRespuestaVO().setRespuesta("1");
                        e.getObjetoRespuestaVO().setHtml("mensajeOK('Cargado Correctamente','reloadTabla','../panels/formularios/hc/timeLine_verPaci.jsp?paci=" + paci.getPaciId() + "');");

                    } else {
                        e.getObjetoRespuestaVO().setTipooperacion("ejecutarhtml");
                        e.getObjetoRespuestaVO().setRespuesta("1");
                        e.getObjetoRespuestaVO().setHtml("alertify.error('No se encontro paciente con ese numero de documento')");

                    }
                } else {

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
             }catch(Exception ex ){
                ex.printStackTrace();
            }
            finally{
            em.close();
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
