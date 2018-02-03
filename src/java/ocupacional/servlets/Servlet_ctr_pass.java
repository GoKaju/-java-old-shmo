/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ocupacional.servlets;

import formularios.entidades.Pacientes;
import java.io.IOException;
import java.io.PrintWriter;
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
import ocupacional.JPA.controlers.UsuariosJpaController;
import ocupacional.JPA.valueobjects.Usuarios;
import valeria.metodos.Encriptacion;
import valeria.metodos.ManejadorFechas;
import valeria.response.Mediador;
import valeria.response.ObjetoRespuestaVO;
import valeria.response.RespuestaXML;

/**
 *
 * @author Alejandro
 */
@WebServlet(name = "Servlet_ctr_pass", urlPatterns = {"/Pass"})
public class Servlet_ctr_pass extends HttpServlet {

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
        //response.setContentType("text/html;charset=UTF-8");
        response.setContentType("text/xml;charset=UTF-8");

        PrintWriter out = response.getWriter();

        /*ServletContext context = getServletContext();
         RequestDispatcher rd = context.getRequestDispatcher("/Inicio.jsp");
         rd.forward(request, response);
         */
        // get request parameters for userID and password
        HttpSession session = request.getSession();
        Mediador e = (Mediador) session.getAttribute("Mediador");

        if (e != null) {
            if (e.getUsuarioVO() != null) {
                session.invalidate();
                e = new Mediador(new ObjetoRespuestaVO());
            }
        } else {
            e = new Mediador(new ObjetoRespuestaVO());
        }
        session.isNew();

        System.out.println("1");

//        if (e != null) {
        System.out.println("2");

        e.LimpiarErrores();
        e.o.setRequest(request);
        String user = e.o.getvariable("usuario");
        String pw_ant = e.o.getvariable("password_ant");
        String pw_new = e.o.getvariable("password_new");
        String pw_new_conf = e.o.getvariable("password_new_confirm");

        Usuarios UsuarioVO = null;

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaP");
        EntityManager em = emf.createEntityManager();
        TypedQuery<Usuarios> consultaDocumento = em.createNamedQuery("Usuarios.findByUsuaUsuario", Usuarios.class);
        consultaDocumento.setParameter("usuaUsuario", user);
        List<Usuarios> lista = consultaDocumento.getResultList();
        em.close();
        Pacientes paci = null;
        for (Usuarios p : lista) {
            UsuarioVO = p;
        }

        if (UsuarioVO != null) {

            if (UsuarioVO.getUsuaEstado().equals("ACTIVO") || UsuarioVO.getUsuaEstado().equals("INSCRITO")) {
                Encriptacion Encriptacion = new Encriptacion();
                String password = UsuarioVO.getUsuaContrasena();
                String pass = "";
                try {
//                   password = Encriptacion.decrypt(UsuarioVO.getPassword_usuario());
                    pass = Encriptacion.encrypt(pw_ant);
                } catch (Exception ex) {
                    ex.printStackTrace();

                }

                if (e.o.Compara(pass, password)) {
                    System.out.println("Pase la pass:::: estado :: " + UsuarioVO.getUsuaEstado());
                    if (UsuarioVO.getUsuaEstado().equals("ACTIVO")) {
                        //                        cambio de contraseña para usuario ACTIVO

//                        ArrayList<UsuarioRolVO> ListaRoles = (ArrayList<UsuarioRolVO>) UsuariosDAO.ListarRolesUsuario(UsuarioVO.getIdUsuario());
//                    UsuarioVO.setRoles(ListaRoles);
//                    e.setUsuarioVO(UsuarioVO);
//                    session.removeAttribute("Mediador");
//                    session.setAttribute("Mediador", e);
//                    session.setMaxInactiveInterval(30 * 60);
//                    session.setAttribute("sede_id", UsuarioVO.getSede_id());
//                    System.out.println("----------------ACCESO---------------");
//                    System.out.println("USUA: "+ UsuarioVO.getIdUsuario());
//                    System.out.println("HORA: "+ UsuarioVO.getHora_acceso());
//                    System.out.println("IP: "+ UsuarioVO.getIpAcceso());
//                    System.out.println("--------------ACCESO--FIN------------");
//                    
//                    e.getObjetoRespuestaVO().setTipooperacion("ejecutarhtml");
//                    e.getObjetoRespuestaVO().setRespuesta("1");
//                    e.getObjetoRespuestaVO().setHtml("location.href = 'app/Inicio'");
                    } else {
//                        cambio de contraseña para usuario INSCRITO
                        if (e.o.Compara(pw_new, pw_new_conf)) {

                            UsuariosJpaController udao = new UsuariosJpaController(emf);

                            UsuarioVO.setUsuaEstado("ACTIVO");
                            UsuarioVO.setUsuaContrasena(Encriptacion.encrypt(pw_new));
                            UsuarioVO.setUsuaFechacambio(new ManejadorFechas().getFechaHoraTimeStamp());
                            EntityTransaction tx = udao.getEntityManager().getTransaction();
                            tx.begin();
                            udao.edit(UsuarioVO);
                            tx.commit();

                            session.removeAttribute("usercampass");
                            session.setAttribute("usercampass", UsuarioVO);
                            e.getObjetoRespuestaVO().setTipooperacion("ejecutarhtml");
                            e.getObjetoRespuestaVO().setRespuesta("1");
                            e.getObjetoRespuestaVO().setHtml("alertify.success('Actualizado correctamente, inicie sesion nuevamente.',3000);\n"
                                    + "setTimeout(function(e){location.href = 'login.jsp';},2000);");
                        } else {

                            e.getObjetoRespuestaVO().setRespuesta("0");
                            e.getObjetoRespuestaVO().setTipooperacion("error");
                            e.getObjetoRespuestaVO().setHtml("Las contrase&ntilde;as no coinciden.");

                        }

                    }

                } else {
                    e.getObjetoRespuestaVO().setRespuesta("0");
                    e.getObjetoRespuestaVO().setTipooperacion("error");
                    e.getObjetoRespuestaVO().setHtml("Error en el proceso de Autenticacion verifique su informacion.");
                }
            } else {
                e.getObjetoRespuestaVO().setRespuesta("0");
                e.getObjetoRespuestaVO().setTipooperacion("error");
                e.getObjetoRespuestaVO().setHtml("<strong>Usuario inactivo.</strong>");

            }
        } else {
            e.getObjetoRespuestaVO().setRespuesta("0");
            e.getObjetoRespuestaVO().setTipooperacion("error");
            e.getObjetoRespuestaVO().setHtml("<strong>Proceso de Identificacion Erroneo.</strong>");
        }
//        } else {
//            ObjetoRespuestaVO ObjetoRespuestaVO = new ObjetoRespuestaVO();
//            e = new Mediador(ObjetoRespuestaVO);
//            e.getObjetoRespuestaVO().setRespuesta("0");
//            e.getObjetoRespuestaVO().setTipooperacion("error");
//            e.getObjetoRespuestaVO().setHtml("Variable de Sesion no encontrada");
//            System.out.println("3");
//
//        }
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
            Logger.getLogger(Servlet_ctr_pass.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Servlet_ctr_pass.class.getName()).log(Level.SEVERE, null, ex);
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