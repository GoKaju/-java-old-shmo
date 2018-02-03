/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ocupacional.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
import ocupacional.JPA.controlers.RolesJpaController;
import ocupacional.JPA.valueobjects.Roles;
import ocupacional.bdatos.RolDAO;
import ocupacional.bdatos.RolFuncionalidadDAO;
import ocupacional.servlets.facturacion.Servlet_ctr_clientes;
import ocupacional.valueobjects.RolFuncionalidadVOs;
import ocupacional.valueobjects.RolVO;
import valeria.metodos.ManejadorFechas;
import valeria.response.Mediador;
import valeria.response.ObjetoRespuestaVO;
import valeria.response.RespuestaXML;

/**
 *
 * @author D4V3
 */
@WebServlet(name = "Servlet_ctr_roles", urlPatterns = {"/Roles"})
public class Servlet_ctr_roles extends HttpServlet {

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

            if (action.equals("newRol")) {
                String nd = e.o.getvariable("nd");
                String id = e.o.getvariable("id");

                RolDAO rdao = new RolDAO(e);
                RolVO rvo = new RolVO();
                rvo.setRole_id(id);
                rvo.setRole_descripcion(nd);
                System.out.println("--------------");
                System.out.println(rvo.getRole_id().equals(""));
                System.out.println(rvo.getRole_id());
//revisar no esta insertando
                if (!rvo.getRole_id().equals("")) {
                    System.out.println("Entre aqui y actualize");
                    rvo.setRole_estado(e.o.getvariable("est"));
                    rvo.setRole_registradopor(e.getUsuarioVO().getIdpersona());
                    rvo.setRole_fechacambio(fechas.getFechaHoraTimeStamp());

                    if (rdao.Actualizar(rvo)) {

                        RolFuncionalidadDAO rfdao = new RolFuncionalidadDAO(e);

                        boolean b = false;
                        boolean b2 = false;

                        if (rfdao.ListarXrol(rvo.getRole_id()) != null) {
                            RolFuncionalidadVOs l = new RolFuncionalidadVOs();
                            l.setRole_id(rvo.getRole_id());

                            if (rfdao.Eliminar(l)) {
                                b = true;
                            }

                        }

                        ArrayList<RolFuncionalidadVOs> listaRolFuncionalidad = (ArrayList<RolFuncionalidadVOs>) session.getAttribute("listaRolFuncionalidad");
                        if (listaRolFuncionalidad != null) {
                            int a = 0;
                            for (RolFuncionalidadVOs l : listaRolFuncionalidad) {
                                l.setRole_id(rvo.getRole_id());
                                l.setRofu_registradopor(e.getUsuarioVO().getIdpersona());
                                l.setRofu_fechacambio(fechas.getFechaHoraTimeStamp());

                                if (rfdao.Insertar(l)) {
                                    a++;
                                }
                            }
                            if (a == listaRolFuncionalidad.size()) {
                                b2 = true;
                            }
                        }

                        if (b && b2) {
                            session.removeAttribute("listaRolFuncionalidad");
                            e.getObjetoRespuestaVO().setTipooperacion("ejecutarhtml");
                            e.getObjetoRespuestaVO().setRespuesta("1");
                            e.getObjetoRespuestaVO().setHtml("mensajeOK('Actualizado Correctamente','reloadTabla','../panels/formularios/basicas/roles.jsp');");

                        } else {
                            session.removeAttribute("listaRolFuncionalidad");

                            e.getObjetoRespuestaVO().setTipooperacion("ejecutarhtml");
                            e.getObjetoRespuestaVO().setRespuesta("1");
                            e.getObjetoRespuestaVO().setHtml("alertify.error('Error al Actualizar Rol...!')");
                        }

                    } else {

                        e.getObjetoRespuestaVO().setTipooperacion("ejecutarhtml");
                        e.getObjetoRespuestaVO().setRespuesta("1");
                        e.getObjetoRespuestaVO().setHtml("alertify.error('Error al Actualizar Rol...!')");
                    }

                } else {

//                    rvo.setRole_estado(e.o.getvariable("est"));
                    rvo.setRole_registradopor(e.getUsuarioVO().getIdpersona());
                    rvo.setRole_fechacambio(fechas.getFechaHoraTimeStamp());

                    if (rdao.Insertar(rvo)) {

                        boolean b = false;
                        for (RolFuncionalidadVOs l : new RolFuncionalidadDAO(e).ListarXrol(rvo.getRole_id())) {

                            if (new RolFuncionalidadDAO(e).Eliminar(l)) {
                                b = true;

                            } else {
                                b = false;
                                break;

                            }

                        }

                        ArrayList<RolFuncionalidadVOs> listaRolFuncionalidad = (ArrayList<RolFuncionalidadVOs>) session.getAttribute("listaRolFuncionalidad");
                        if (listaRolFuncionalidad != null && !listaRolFuncionalidad.isEmpty()) {
                            for (RolFuncionalidadVOs l : listaRolFuncionalidad) {
                                l.setRole_id(rvo.getRole_id());
                                l.setRofu_registradopor(e.getUsuarioVO().getIdpersona());
                                l.setRofu_fechacambio(fechas.getFechaHoraTimeStamp());
                                if (new RolFuncionalidadDAO(e).Insertar(l)) {
                                    b = true;

                                } else {
                                    b = false;
                                    break;

                                }

                            }
                        } else {
                            b = true;
                        }

                        if (b) {
                            session.removeAttribute("listaRolFuncionalidad");
                            e.getObjetoRespuestaVO().setTipooperacion("ejecutarhtml");
                            e.getObjetoRespuestaVO().setRespuesta("1");
                            e.getObjetoRespuestaVO().setHtml("mensajeOK('Insertado Correctamente','reloadTabla','../panels/formularios/basicas/roles.jsp');");

                        } else {
                            session.removeAttribute("listaRolFuncionalidad");

                            e.getObjetoRespuestaVO().setTipooperacion("ejecutarhtml");
                            e.getObjetoRespuestaVO().setRespuesta("1");
                            e.getObjetoRespuestaVO().setHtml("alertify.error('Error al Insertar Rol...!')");
                        }

                    } else {

                        e.getObjetoRespuestaVO().setTipooperacion("ejecutarhtml");
                        e.getObjetoRespuestaVO().setRespuesta("1");
                        e.getObjetoRespuestaVO().setHtml("alertify.error('Error al Insertar Rol.!')");
                    }
                }

            } else if (action.equals("eliminarRol")) {
                String id = e.o.getvariable("id");
                try {
                    EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaP");
                    RolesJpaController rdao = new RolesJpaController(emf);

                    rdao.destroy(Integer.parseInt(id));
                    e.getObjetoRespuestaVO().setTipooperacion("ejecutarhtml");
                    e.getObjetoRespuestaVO().setRespuesta("1");
                    e.getObjetoRespuestaVO().setHtml("mensajeOK('Eliminado Correctamente','reloadTabla2','../panels/formularios/basicas/roles.jsp');");

                } catch (Exception ex) {
                    ex.printStackTrace();
                    e.getObjetoRespuestaVO().setTipooperacion("error");
                    e.getObjetoRespuestaVO().setRespuesta("0");
                    e.getObjetoRespuestaVO().setHtml("La operación no se realizo correctamente..!!");
                }

            } else if (action.equals("AgregarFuncionalidad")) {
                String id = e.o.getvariable("func_sel");
                String op = e.o.getvariable("op_text");
                System.out.println("op---->" + op);
                ArrayList<RolFuncionalidadVOs> listaRolFuncionalidad = (ArrayList<RolFuncionalidadVOs>) session.getAttribute("listaRolFuncionalidad");

                if (listaRolFuncionalidad == null) {
                    listaRolFuncionalidad = new ArrayList<RolFuncionalidadVOs>();
                }
                RolFuncionalidadVOs c = new RolFuncionalidadVOs();
                c.setFunc_id(id);
                c.setRofu_op(op);
                c.setRofu_registradopor(e.getUsuarioVO().getIdpersona());
                c.setRofu_fechacambio(fechas.getFechaHoraTimeStamp());
                listaRolFuncionalidad.add(c);

                session.removeAttribute("listaRolFuncionalidad");
                session.setAttribute("listaRolFuncionalidad", listaRolFuncionalidad);

                e.getObjetoRespuestaVO().setTipooperacion("ejecutarhtml");
                e.getObjetoRespuestaVO().setRespuesta("1");
                e.getObjetoRespuestaVO().setHtml("mensajeOK('Agregado Correctamente','','');RecargaSelect('../panels/formularios/basicas/roles.jsp','cajaF','tablaF')");

            } else if (action.equals("RemoverFuncionalidad")) {
                int id = Integer.parseInt(e.o.getvariable("hash"));
                ArrayList<RolFuncionalidadVOs> listaRolFuncionalidad = (ArrayList<RolFuncionalidadVOs>) session.getAttribute("listaRolFuncionalidad");
                for (RolFuncionalidadVOs l : listaRolFuncionalidad) {
                    if (l.hashCode() == id) {
                        listaRolFuncionalidad.remove(l);
                        break;
                    }
                }

                session.removeAttribute("listaRolFuncionalidad");
                session.setAttribute("listaRolFuncionalidad", listaRolFuncionalidad);

                e.getObjetoRespuestaVO().setTipooperacion("ejecutarhtml");
                e.getObjetoRespuestaVO().setRespuesta("1");
                e.getObjetoRespuestaVO().setHtml("mensajeOK('Removido Correctamente','','');RecargaSelect('../panels/formularios/basicas/roles.jsp','cajaF','tablaF')");

            } else if (action.equals("CargarFuncionalidades")) {
                String id = e.o.getvariable("id");
                ArrayList<RolFuncionalidadVOs> listaRolFuncionalidad = new RolFuncionalidadDAO(e).ListarXrol(id);

                session.removeAttribute("listaRolFuncionalidad");
                session.setAttribute("listaRolFuncionalidad", listaRolFuncionalidad);

                e.getObjetoRespuestaVO().setTipooperacion("ejecutarhtml");
                e.getObjetoRespuestaVO().setRespuesta("1");
                e.getObjetoRespuestaVO().setHtml("mensajeOK('Cargado Correctamente','','');RecargaSelect('../panels/formularios/basicas/roles.jsp','cajaF','tablaF')");

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
            Logger.getLogger(Servlet_ctr_clientes.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Servlet_ctr_clientes.class.getName()).log(Level.SEVERE, null, ex);
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
