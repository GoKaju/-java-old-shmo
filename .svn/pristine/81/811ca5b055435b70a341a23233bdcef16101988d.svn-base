/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ocupacional.servlets;

import formularios.entidades.Pacientes;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
import ocupacional.JPA.valueobjects.Usuariorol;
import ocupacional.JPA.valueobjects.Usuarios;
import ocupacional.bdatos.UsuariosDAO;
import ocupacional.valueobjects.UsuarioRolVO;
import valeria.metodos.Encriptacion;
import valeria.metodos.ManejadorFechas;
import valeria.response.Mediador;
import valeria.response.ObjetoRespuestaVO;
import valeria.response.RespuestaXML;
import valeria.session.UsuarioVO;

/**
 *
 * @author Alejandro
 */
@WebServlet(name = "Servlet_ctr_log", urlPatterns = {"/Sistema"})
public class Servlet_ctr_log extends HttpServlet {

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
        try{
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
        e=null;
        
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
            String pwd = e.o.getvariable("password");
            String ip = e.o.getvariable("ip_ocu");
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
//                UsuarioVO.setIpAcceso(ip);
                if(UsuarioVO.getUsuaEstado().equals("ACTIVO")||UsuarioVO.getUsuaEstado().equals("INSCRITO")){
                Encriptacion Encriptacion = new Encriptacion();
                String password = UsuarioVO.getUsuaContrasena();
                String pass = "";
                try {
//                   password = Encriptacion.decrypt(UsuarioVO.getPassword_usuario());
                  pass = Encriptacion.encrypt(pwd);
                } catch (Exception ex) {
                    ex.printStackTrace();

                }


                if (e.o.Compara(pass, password)) {

                    if(UsuarioVO.getUsuaEstado().equals("ACTIVO")){
                        System.out.println("id usuario::: "+UsuarioVO.getUsuaId());
                    session.removeAttribute("Mediador");
                    session.setAttribute("Mediador", e);
                    session.setMaxInactiveInterval(30 * 60);
                    session.setAttribute("sede_id", UsuarioVO.getSedeId());
                    System.out.println("----------------ACCESO---------------");
                    System.out.println("USUA: "+ UsuarioVO.getUsuaId());
                    System.out.println("HORA: "+ new ManejadorFechas().getFechaHoraTimeStamp());
                    System.out.println("IP: "+ ip);
                    System.out.println("--------------ACCESO--FIN------------");
//                    System.out.println("---->"+UsuarioVO.getRoles());
                  UsuarioVO u = new UsuarioVO();
                    u.setHora_acceso(new ManejadorFechas().getFechaHoraTimeStamp());
                    u.setIdpersona(UsuarioVO.getUsuaIdpersona().toString());
                    u.setIdUsuario(UsuarioVO.getUsuaId().toString());
                    u.setNombre_usuario(UsuarioVO.getUsuaUsuario());
                    u.setSede_id(UsuarioVO.getSedeId().toString());
                    u.setUsua_estado(UsuarioVO.getUsuaEstado());
                    u.setUsua_tipo(UsuarioVO.getUsuaTipo());
                    u.setPassword_usuario(password);
                    for(Usuariorol ur : UsuarioVO.getUsuariorolList()){
                    u.setRol(ur.getRoleId());
                    break;
                    }
//                    UsuariosDAO UsuariosDAO = new UsuariosDAO(e);
//                    ArrayList<UsuarioRolVO> ListaRoles = (ArrayList<UsuarioRolVO>) UsuariosDAO.ListarRolesUsuario(UsuarioVO.getUsuaId().toString());
//                    u.setRoles(ListaRoles);
                    e.setUsuarioVO(u);
                    
                    e.getObjetoRespuestaVO().setTipooperacion("ejecutarhtml");
                    e.getObjetoRespuestaVO().setRespuesta("1");
                    e.getObjetoRespuestaVO().setHtml("location.href = 'app/Inicio'");
                    
                    }else{
                    session.removeAttribute("usercampass");
                    session.setAttribute("usercampass",UsuarioVO);
                    e.getObjetoRespuestaVO().setTipooperacion("ejecutarhtml");
                    e.getObjetoRespuestaVO().setRespuesta("1");
                    e.getObjetoRespuestaVO().setHtml("location.href = 'cam_pass.jsp'");
                    
                    
                    }
                    
                } else {
                    e.getObjetoRespuestaVO().setRespuesta("0");
                    e.getObjetoRespuestaVO().setTipooperacion("error");
                    e.getObjetoRespuestaVO().setHtml("Error en el proceso de Autenticacion verifique su informacion.");
                }
                }else{
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

    }catch(Exception ex ){
    ex.printStackTrace();}
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
            Logger.getLogger(Servlet_ctr_log.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Servlet_ctr_log.class.getName()).log(Level.SEVERE, null, ex);
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
