/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ocupacional.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ocupacional.JPA.controlers.RolesJpaController;
import ocupacional.JPA.controlers.UsuariorolJpaController;
import ocupacional.JPA.controlers.UsuariosJpaController;
import ocupacional.JPA.valueobjects.Usuariorol;
import ocupacional.JPA.valueobjects.Usuarios;
import ocupacional.bdatos.PersonaGeneralDAO;
import ocupacional.bdatos.UsuarioRolDAO;
import ocupacional.bdatos.UsuariosDAO;
import ocupacional.servlets.facturacion.Servlet_ctr_clientes;
import ocupacional.valueobjects.PersonaGeneralVO;
import ocupacional.valueobjects.UsuarioRolVO;
import ocupacional.valueobjects.UsuarioVO;
import valeria.metodos.Encriptacion;
import valeria.metodos.ManejadorFechas;
import valeria.response.Mediador;
import valeria.response.ObjetoRespuestaVO;
import valeria.response.RespuestaXML;

/**
 *
 * @author D4V3
 */
@WebServlet(name = "Servlet_ctr_usuarios", urlPatterns = {"/Usuarios"})
public class Servlet_ctr_usuarios extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        response.setContentType("text/xml;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        Mediador e = (Mediador) session.getAttribute("Mediador");
        ManejadorFechas fechas = new ManejadorFechas();
                 EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaP");
            EntityManager em = emf.createEntityManager();
            try{
        if (e != null) {
            e.LimpiarErrores();
            e.o.setRequest(request);
            String action = e.o.getvariable("action");
            System.out.println(action);
   
            UsuariosJpaController udao = new UsuariosJpaController(emf);
            UsuariorolJpaController urdao = new UsuariorolJpaController(emf);
            Encriptacion Encriptacion = new Encriptacion();

            if (action.equals("cam_pass")) {
                String pw_ant = e.o.getvariable("password_ant");
                String pw_new = e.o.getvariable("password_new");
                String pw_new_conf = e.o.getvariable("password_new_confirm");
                if (e.o.Compara(Encriptacion.encrypt(pw_ant), e.getUsuarioVO().getPassword_usuario())) {
                    if (e.o.Compara(pw_new, pw_new_conf)) {

                        Usuarios u = udao.findUsuarios(Integer.parseInt(e.getUsuarioVO().getIdUsuario()));

                        u.setUsuaContrasena(Encriptacion.encrypt(pw_new));
                        u.setUsuaFechacambio(new ManejadorFechas().getFechaHoraTimeStamp());
                        u.setUsuaRegistradopor(Integer.parseInt(e.getUsuarioVO().getIdUsuario()));
                        udao.edit(u);

                        e.getObjetoRespuestaVO().setTipooperacion("ejecutarhtml");
                        e.getObjetoRespuestaVO().setRespuesta("1");
                        e.getObjetoRespuestaVO().setHtml("alertify.success('Actualizado correctamente, inicie sesion nuevamente.',3000);\n"
                                + "    setTimeout(function(e){location.href = '../logout.jsp';},2000);");

                    } else {
                        e.getObjetoRespuestaVO().setRespuesta("0");
                        e.getObjetoRespuestaVO().setTipooperacion("error");
                        e.getObjetoRespuestaVO().setHtml("Las contrase&ntilde;as no coinciden.");
                    }
                } else {
                    e.getObjetoRespuestaVO().setRespuesta("0");
                    e.getObjetoRespuestaVO().setTipooperacion("error");
                    e.getObjetoRespuestaVO().setHtml("Error en el proceso de Autenticacion verifique su informacion.");
                }

            } else if (action.equals("validarUsuario")) {
                String td = e.o.getvariable("td");
                String nd = e.o.getvariable("nd");

                PersonaGeneralVO pege = new PersonaGeneralDAO(e).Verificar(td, nd);

                if (pege.getPege_id() != null) {
                    UsuarioVO clie = new UsuariosDAO(e).Cargar(pege.getPege_id());
                    if (clie != null) {
                        e.getObjetoRespuestaVO().setTipooperacion("error");
                        e.getObjetoRespuestaVO().setRespuesta("0");
                        e.getObjetoRespuestaVO().setHtml("Este Numero de Identificacion ya tiene Usuario asignado..!!");

                    } else {
//                     cargar del nombre en el form   
                        e.getObjetoRespuestaVO().setTipooperacion("ejecutarhtml");
                        e.getObjetoRespuestaVO().setRespuesta("1");
                        e.getObjetoRespuestaVO().setHtml("$('#content').show();");

                    }

                } else {
                    e.getObjetoRespuestaVO().setTipooperacion("error");
                    e.getObjetoRespuestaVO().setRespuesta("0");
                    e.getObjetoRespuestaVO().setHtml("Para crear el usuario se debe estar creada la persona..!!");

                }

            } else if (action.equals("newUser")) {

//                aqwui se creara el usuario hay que validar
//                si es mdf y si el nombre de usuario ya existe
                String usua_id = e.o.getvariable("user_id");
                String tipo = e.o.getvariable("tipo_usua");
                String id_persona = e.o.getvariable("codigo_usua");
                String sede = e.o.getvariable("sede_usua");
                String nombreUsuario = e.o.getvariable("user_usua");
                String temp_pass = e.o.getvariable("temp_pass");
                String estado_usua = e.o.getvariable("estado_usua");
                String roles[] = request.getParameterValues("roles");

                if (roles != null) {
                    if (usua_id.equals("")) {
//                    validar nombre de usuario
                   

                        TypedQuery<Usuarios> consultaDocumento = em.createNamedQuery("Usuarios.findByUsuaUsuario", Usuarios.class);
                        consultaDocumento.setParameter("usuaUsuario", e.o.notEmpty(nombreUsuario).toUpperCase());
                        List<Usuarios> lista = consultaDocumento.getResultList();
                        System.out.println("lista empity " + lista.isEmpty());
                        for (Usuarios us : lista) {
                            System.out.println("userr " + us.getUsuaUsuario());

                        }

                        if (lista.isEmpty()) {
                            Usuarios u = new Usuarios();
                            u.setUsuaTipo(tipo);
                            u.setUsuaIdpersona(Integer.parseInt(id_persona));
                            u.setSedeId(Integer.parseInt(sede));
                            u.setUsuaUsuario(nombreUsuario);
                            u.setUsuaContrasena(Encriptacion.encrypt(temp_pass));
                            u.setUsuaEstado("INSCRITO");
                            u.setUsuaRegistradopor(Integer.parseInt(e.getUsuarioVO().getIdUsuario()));
                            u.setUsuaFechacambio(fechas.getFechaHoraTimeStamp());
                            udao.create(u);
                            for (String rol : roles) {
                                Usuariorol ur = new Usuariorol();
                                ur.setUsuaId(u);
                                ur.setRoleId(new RolesJpaController(emf).findRoles(Integer.parseInt(rol)));
                                ur.setUsroRegistradopor(Integer.parseInt(e.getUsuarioVO().getIdUsuario()));
                                ur.setUsroFechacambio(fechas.getFechaHoraTimeStamp());
                                urdao.create(ur);

                            }
                            e.getObjetoRespuestaVO().setTipooperacion("ejecutarhtml");
                            e.getObjetoRespuestaVO().setRespuesta("1");
                            e.getObjetoRespuestaVO().setHtml("mensajeOK('Insertado Correctamente','reloadTabla2','../panels/formularios/basicas/Usuarios.jsp');");
                        } else {

                            e.getObjetoRespuestaVO().setTipooperacion("error");
                            e.getObjetoRespuestaVO().setRespuesta("0");
                            e.getObjetoRespuestaVO().setHtml("Ese nombre de usuario ya est&aacute; en uso!");

                        }

                    } else {
//                el usuario existe hay que actualizarlo 

                        Usuarios u = udao.findUsuarios(Integer.parseInt(usua_id));
                        u.setSedeId(Integer.parseInt(sede));

                        if (!u.getUsuaEstado().equals("INSCRITO")) {
                            u.setUsuaEstado(estado_usua);
                        }
                        if (!temp_pass.equals("")) {
                            u.setUsuaContrasena(Encriptacion.encrypt(temp_pass));
                            u.setUsuaEstado("INSCRITO");
                        }
                        u.setUsuaRegistradopor(Integer.parseInt(e.getUsuarioVO().getIdUsuario()));
                        u.setUsuaFechacambio(fechas.getFechaHoraTimeStamp());
                      EntityTransaction tx = udao.getEntityManager().getTransaction();
                            tx.begin();
                        udao.edit(u);
                    tx.commit();
                        for (Usuariorol ur : u.getUsuariorolList()) {
                            urdao.destroy(ur.getUsroId());
                        }

                        for (String rol : roles) {
                            Usuariorol ur = new Usuariorol();
                            ur.setUsuaId(u);
                            ur.setRoleId(new RolesJpaController(emf).findRoles(Integer.parseInt(rol)));
                            ur.setUsroRegistradopor(Integer.parseInt(e.getUsuarioVO().getIdUsuario()));
                            ur.setUsroFechacambio(fechas.getFechaHoraTimeStamp());
                            urdao.create(ur);

                        }
                        e.getObjetoRespuestaVO().setTipooperacion("ejecutarhtml");
                        e.getObjetoRespuestaVO().setRespuesta("1");
                        e.getObjetoRespuestaVO().setHtml("mensajeOK('Actualizado correctamente','reloadTabla2','../panels/formularios/basicas/Usuarios.jsp');");

                    }
                } else {
                    e.getObjetoRespuestaVO().setTipooperacion("error");
                    e.getObjetoRespuestaVO().setRespuesta("0");
                    e.getObjetoRespuestaVO().setHtml("Debe seleccionar mínimo un rol!");

                }

            } else if (action.equals("eliminarUsuario")) {
                String id = e.o.getvariable("user_id");
                Usuarios u = udao.findUsuarios(Integer.parseInt(id));
                u.setUsuaEstado("ELIMINADO");
                u.setUsuaRegistradopor(Integer.parseInt(e.getUsuarioVO().getIdUsuario()));
                u.setUsuaFechacambio(fechas.getFechaHoraTimeStamp());
udao.edit(u);
                e.getObjetoRespuestaVO().setTipooperacion("ejecutarhtml");
                e.getObjetoRespuestaVO().setRespuesta("1");
                e.getObjetoRespuestaVO().setHtml("mensajeOK('Eliminado Correctamente','reloadTabla2','../panels/formularios/basicas/Usuarios.jsp');");

            } 
           else {
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
