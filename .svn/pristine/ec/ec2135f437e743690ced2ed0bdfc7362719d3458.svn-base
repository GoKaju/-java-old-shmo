/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ocupacional.servlets.facturacion;

import formularios.controlers.ClienteSedesJpaController;
import formularios.entidades.ClienteSedes;
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
import ocupacional.JPA.controlers.ClientesJpaController;
import valeria.metodos.ManejadorFechas;
import valeria.response.Mediador;
import valeria.response.ObjetoRespuestaVO;
import valeria.response.RespuestaXML;

/**
 *
 * @author Sebas
 */
@WebServlet(name = "Servlet_ctr_Sedesfincas", urlPatterns = {"/Sedesfincas"})
public class Servlet_ctr_Sedesfincas extends HttpServlet {

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
            System.out.println(" -->" + action);

            EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaP");
            ClienteSedesJpaController csdao = new ClienteSedesJpaController(emf);

            boolean resultado = true;
            String errores = "";
            String html = "";

            if (action.equals("agregar")) {
                ClienteSedes cs = new ClienteSedes();

                cs.setClieId(new ClientesJpaController(emf).findClientes(Integer.parseInt(e.o.getvariable("clie_id"))));
                cs.setClsedDescripcion(e.o.getvariable("descripcion"));
                cs.setClsedRegistradopor(e.getUsuarioVO().getIdUsuario());
                cs.setClsedFechacambio(fechas.getFechaHoraTimeStamp());

                csdao.create(cs);
                if (cs.getClsedId() == null) {

                    resultado = false;
                    errores += "Error al insertar centro de costo...<br>";
                } else {

                    html += "mensajeOK('Operación exitosa', 'reloadTabla','../panels/formularios/basicas/clientes_sedefinca.jsp?clie_id=" + e.o.getvariable("clie_id") + "');";
                }

            } else if (action.equals("editarCC")) {

//                ClientesDAO cdao = new ClientesDAO(e);
//
//                CentroCostosVO ccvo = ccdao.Cargar(e.o.getvariable("cc_id"));
//
//                ccvo.setCeco_estado(e.o.getvariable("est"));
//                ccvo.setCeco_observacion(e.o.getvariable("nombre"));
//                ccvo.setCeco_registradoPor(e.getUsuarioVO().getIdpersona());
//                ccvo.setCeco_fecaCambio(fechas.getFechaHoraTimeStamp());
//
//                if (!ccdao.Actualizar(ccvo)) {
//
//                    resultado = false;
//                    errores += "Error al actualizar centro de costo...<br>";
//
//                } else {
//
//                    html += "mensajeOK('Operación exitosa', 'reloadTabla','../panels/formularios/factura/centroCostos.jsp?clie_id=" + e.o.getvariable("clie_id") + "');";
//                }

            }
            if (action.equals("eliminar")) {

                csdao.destroy(Integer.parseInt(e.o.getvariable("id")));

                if (csdao.findClienteSedes(Integer.parseInt(e.o.getvariable("id"))) != null) {

                    resultado = false;
                    errores += "Error al eliminar centro de costo...<br>";

                } else {

                    html += "mensajeOK('Operación exitosa', 'reloadTabla','../panels/formularios/basicas/clientes_sedefinca.jsp?clie_id=" + e.o.getvariable("clie_id") + "');";
                }

            }

            if (resultado) {

                e.getObjetoRespuestaVO().setRespuesta("1");
                e.getObjetoRespuestaVO().setTipooperacion("ejecutarhtml");
                e.getObjetoRespuestaVO().setHtml(html);

            } else {

                e.getObjetoRespuestaVO().setRespuesta("0");
                e.getObjetoRespuestaVO().setTipooperacion("error");
                e.getObjetoRespuestaVO().setHtml(errores);

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
            Logger.getLogger(Servlet_ctr_Sedesfincas.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Servlet_ctr_Sedesfincas.class.getName()).log(Level.SEVERE, null, ex);
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
