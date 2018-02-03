/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ocupacional.servlets;

import com.lowagie.text.Document;
import formularios.controlers.EmpleadoexamenJpaController;
import formularios.controlers.EmpleadosJpaController;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ocupacional.JPA.controlers.ExamenesJpaController;
import ocupacional.JPA.controlers.SedeJpaController;
import ocupacional.JPA.controlers.TipodocumentoJpaController;
import ocupacional.JPA.valueobjects.Empleadoexamen;
import ocupacional.JPA.valueobjects.Empleados;
import valeria.metodos.ManejadorFechas;
import valeria.response.Mediador;
import valeria.response.ObjetoRespuestaVO;
import valeria.response.RespuestaXML;

/**
 *
 * @author Sebas
 */
@WebServlet(name = "Servlet_ctr_empleados", urlPatterns = {"/Empleados"})
public class Servlet_ctr_empleados extends HttpServlet {

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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession();
        Mediador e = (Mediador) session.getAttribute("Mediador");
        ManejadorFechas fechas = new ManejadorFechas();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaP");
        EmpleadosJpaController empleadosDAO = new EmpleadosJpaController(emf);
        EntityManager entitymanager = emf.createEntityManager();
try{
        if (e != null) {
            e.LimpiarErrores();
            e.o.setRequest(request);
            String action = e.o.getvariable("action");
            System.out.println(action);
            if (action.equals("validarEmpleado")) {
                System.out.println("1");

                String tido = e.o.getvariable("tido");
                String doc = e.o.getvariable("doc");

                System.out.println("2");
                try {
                    Query query = entitymanager.createNamedQuery("verificaExistencia");
                    query.setParameter("documento", doc);
                    query.setParameter("tido", new TipodocumentoJpaController(emf).findTipodocumento(Integer.parseInt(tido)));


                    if (query.getResultList().size() > 0) {

                        System.out.println("YA ESTA");

                        e.getObjetoRespuestaVO().setRespuesta("0");
                        e.getObjetoRespuestaVO().setTipooperacion("error");
                        e.getObjetoRespuestaVO().setHtml("El empleado ya se encuentra registrado...!");

                    } else {

                        session.removeAttribute("listaEmpleadoExamen");

                        e.getObjetoRespuestaVO().setRespuesta("1");
                        e.getObjetoRespuestaVO().setTipooperacion("ejecutarhtml");
                        e.getObjetoRespuestaVO().setHtml("mensajeOK('Cargado correctamente','reloadTabla','../panels/formularios/basicas/Empleados_nuevo.jsp?tido=" + tido + "&doc=" + doc + "');");

                    }
                } catch (Exception ex) {

                    ex.printStackTrace();

                }

//
//                session.removeAttribute("listaClienteServicio");
//                e.getObjetoRespuestaVO().setTipooperacion("ejecutarhtml");
//                e.getObjetoRespuestaVO().setRespuesta("1");
//                e.getObjetoRespuestaVO().setHtml("mensajeOK('Cargado correctamente','reloadTabla','../panels/formularios/basicas/Clientes_nuevo.jsp?tido=" + tido + "&doc=" + doc + "');");
            }
            if (action.equals("AgregarExamen")) {
                System.out.println("1");

                String id = e.o.getvariable("Exam_sel");

                System.out.println("2");
                try {

                    List<Empleadoexamen> lista = (List<Empleadoexamen>) session.getAttribute("listaEmpleadoExamen");

                    if (lista == null) {

                        lista = new ArrayList<Empleadoexamen>();

                    }

                    if (id != null && !id.equals("")) {

                        Empleadoexamen evo = new Empleadoexamen();

                        evo.setExamId(new ExamenesJpaController(emf).findExamenes(Integer.parseInt(id)));

                        if (lista.add(evo)) {

                            session.setAttribute("listaEmpleadoExamen", lista);

                            e.getObjetoRespuestaVO().setRespuesta("1");
                            e.getObjetoRespuestaVO().setTipooperacion("ejecutarhtml");
//                    e.getObjetoRespuestaVO().setHtml("mensajeOK('Agregado correctamente','reloadTabla','../panels/formularios/basicas/Empleados_nuevo.jsp?tido=" + tido + "&doc=" + doc + "');");
                            e.getObjetoRespuestaVO().setHtml("$('#cajaFormExamenes').load('../panels/formularios/basicas/Empleados_nuevo.jsp #tablaFormExamenes');alertify.success('Agregado Correctamente');");

                        }

                    }

                } catch (Exception ex) {

                    ex.printStackTrace();

                }

//
//                session.removeAttribute("listaClienteServicio");
//                e.getObjetoRespuestaVO().setTipooperacion("ejecutarhtml");
//                e.getObjetoRespuestaVO().setRespuesta("1");
//                e.getObjetoRespuestaVO().setHtml("mensajeOK('Cargado correctamente','reloadTabla','../panels/formularios/basicas/Clientes_nuevo.jsp?tido=" + tido + "&doc=" + doc + "');");
            }
            if (action.equals("removerExamen")) {
                System.out.println("1");

                String id = e.o.getvariable("hash");

                System.out.println("2");
                try {

                    List<Empleadoexamen> lista = (List<Empleadoexamen>) session.getAttribute("listaEmpleadoExamen");

                    if (lista != null) {

                        for (Empleadoexamen lista1 : lista) {

                            System.out.println(lista1.hashCode() + " ----- " + id);

                            if (lista1.hashCode() == Integer.parseInt(id)) {

                                if (lista.remove(lista1)) {

                                    session.setAttribute("listaEmpleadoExamen", lista);

                                    e.getObjetoRespuestaVO().setRespuesta("1");
                                    e.getObjetoRespuestaVO().setTipooperacion("ejecutarhtml");
//                    e.getObjetoRespuestaVO().setHtml("mensajeOK('Agregado correctamente','reloadTabla','../panels/formularios/basicas/Empleados_nuevo.jsp?tido=" + tido + "&doc=" + doc + "');");
                                    e.getObjetoRespuestaVO().setHtml("$('#cajaFormExamenes').load('../panels/formularios/basicas/Empleados_nuevo.jsp #tablaFormExamenes');alertify.success('Eliminado Correctamente');");

                                    break;

                                }
                            }

                        }

                    }else{
                    
                        System.out.println("LISTA EL NULL");
                    
                    }

                } catch (Exception ex) {

                    ex.printStackTrace();

                }

//
//                session.removeAttribute("listaClienteServicio");
//                e.getObjetoRespuestaVO().setTipooperacion("ejecutarhtml");
//                e.getObjetoRespuestaVO().setRespuesta("1");
//                e.getObjetoRespuestaVO().setHtml("mensajeOK('Cargado correctamente','reloadTabla','../panels/formularios/basicas/Clientes_nuevo.jsp?tido=" + tido + "&doc=" + doc + "');");
            }
            if (action.equals("crearEmpleado") || action.equals("editarEmpleado")) {
//                System.out.println("1");

//                emple.setEmplRegistradopor(e.o.getvariable(""));
//                emple.setEmplfechaCambio(e.o.getvariable(""));
                try {
                    EmpleadoexamenJpaController eedao = new EmpleadoexamenJpaController(emf);
                    EmpleadosJpaController edao = new EmpleadosJpaController(emf);

                    List<Empleadoexamen> lista = (List<Empleadoexamen>) session.getAttribute("listaEmpleadoExamen");
         

                    Empleados emple = new Empleados();
                    if (request.getParameter("id_ocu") != null) {
                        emple = edao.findEmpleados(Integer.parseInt(request.getParameter("id_ocu")));
                    }
                    emple.setEmplRegistradopor(e.getUsuarioVO().getIdUsuario());
                    emple.setEmplfechaCambio(fechas.getFechaHoraTimeStamp());
                    emple.setEmplCargo(e.o.getvariable("cargo"));
                    emple.setEmplDocumento(e.o.getvariable("documento"));
                    emple.setEmplEstado("ACTIVO");
                    emple.setEmplNombres(e.o.getvariable("nombres"));
                    emple.setEmplFirma(e.o.getvariable("firma_txt"));
                    
//                    emple.setEmpleadoexamenList(lista);
                    emple.setSedeId(new SedeJpaController(emf).findSede(Integer.parseInt(e.o.getvariable("sede"))));
                    emple.setTidoId(new TipodocumentoJpaController(emf).findTipodocumento(Integer.parseInt(e.o.getvariable("tipo_documento").trim())));
  if (request.getParameter("id_ocu") != null) {
                    edao.edit(emple);
                    e.getObjetoRespuestaVO().setHtml("mensajeOK('Actualizado Correctamente','reloadTabla2','../panels/formularios/basicas/Empleados.jsp');");
                    }else{
                    edao.create(emple);
                    e.getObjetoRespuestaVO().setHtml("mensajeOK('Insertado Correctamente','reloadTabla2','../panels/formularios/basicas/Empleados.jsp');");
  }
  
  if (request.getParameter("id_ocu") != null) {
  List<Empleadoexamen> antiguos = emple.getEmpleadoexamenList();
  antiguos.removeAll(lista);
                       for(Empleadoexamen ee : antiguos){
                       eedao.destroy(ee.getEmexId());
                       }
    }
                       for(Empleadoexamen ee : lista){
                         ee.setEmplId(emple);
                         ee.setEmexFechacambio(fechas.getFechaHoraTimeStamp());
                         ee.setEmexRegistradopor(e.getUsuarioVO().getIdUsuario());
                           
                    if(ee.getEmexId()!=null){
                    eedao.edit(ee);
                    }else{
                    eedao.create(ee);
                    }
                    }
  
  
                    e.getObjetoRespuestaVO().setRespuesta("1");
                    e.getObjetoRespuestaVO().setTipooperacion("ejecutarhtml");
session.removeAttribute("listaEmpleadoExamen");
                } catch (Exception ex) {

                    ex.printStackTrace();

                }
         }else if (action.equals("eliminarEmpleado")){
         
                try {
                    EmpleadosJpaController edao = new EmpleadosJpaController(emf);
                    Empleados em = edao.findEmpleados(Integer.parseInt(e.o.getvariable("id")));
                    em.setEmplEstado("ELIMINADO");
                    em.setEmplfechaCambio(fechas.getFechaHoraTimeStamp());
                    em.setEmplRegistradopor(e.getUsuarioVO().getIdUsuario());
                    edao.edit(em);
                       e.getObjetoRespuestaVO().setRespuesta("1");
                    e.getObjetoRespuestaVO().setTipooperacion("ejecutarhtml");
//                    e.getObjetoRespuestaVO().setHtml("mensajeOK('Agregado correctamente','reloadTabla','../panels/formularios/basicas/Empleados_nuevo.jsp?tido=" + tido + "&doc=" + doc + "');");
                    e.getObjetoRespuestaVO().setHtml("mensajeOK('Eliminado Correctamente','reloadTabla2','../panels/formularios/basicas/Empleados.jsp');");

                    
                } catch (Exception ex) {
                    Logger.getLogger(Servlet_ctr_empleados.class.getName()).log(Level.SEVERE, null, ex);
                }
        
             
         
         
         }
        } else {

            e.getObjetoRespuestaVO().setRespuesta("0");
            e.getObjetoRespuestaVO().setTipooperacion("error");
            e.getObjetoRespuestaVO().setHtml("Variable de Sesion no encontrada");
        }
             }catch(Exception ex ){
                ex.printStackTrace();
            }
            finally{
            entitymanager.close();
            }

//        System.out.println(new RespuestaXML().GenerarXML(e.getObjetoRespuestaVO()));
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
        processRequest(request, response);
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
        processRequest(request, response);
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
