/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ocupacional.servlets.facturacion;

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
import ocupacional.JPA.controlers.CentrocostosJpaController;
import ocupacional.JPA.controlers.CiudadesJpaController;
import ocupacional.JPA.controlers.ClientesJpaController;
import ocupacional.JPA.controlers.ClientesServicioJpaController;
import ocupacional.JPA.controlers.ServiciosJpaController;
import ocupacional.JPA.controlers.TipodocumentoJpaController;
import ocupacional.JPA.valueobjects.Centrocostos;
import ocupacional.JPA.valueobjects.Clientes;
import ocupacional.JPA.valueobjects.ClientesServicio;
import ocupacional.JPA.valueobjects.Usuarios;
import ocupacional.bdatos.facturacion.ClientesDAO;
import ocupacional.bdatos.facturacion.ClientesServiciosDAO;
import ocupacional.valueobjects.EntidadesVO;
import ocupacional.valueobjects.facturacion.ClientesServiciosVO;
import ocupacional.valueobjects.facturacion.ClientesVO;
import valeria.metodos.ManejadorFechas;
import valeria.response.Mediador;
import valeria.response.ObjetoRespuestaVO;
import valeria.response.RespuestaXML;

/**
 *
 * @author D4V3
 */
@WebServlet(name = "Servlet_ctr_clientes", urlPatterns = {"/Clientes"})
public class Servlet_ctr_clientes extends HttpServlet {

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
            if (action.equals("validarCliente")) {
                String tipo = e.o.getvariable("tipocliente");
                String tido = e.o.getvariable("tido");
                String doc = e.o.getvariable("doc");
                session.removeAttribute("listaClienteServicio");
                e.getObjetoRespuestaVO().setTipooperacion("ejecutarhtml");
                e.getObjetoRespuestaVO().setRespuesta("1");
                e.getObjetoRespuestaVO().setHtml("mensajeOK('Cargado correctamente','reloadTabla','../panels/formularios/basicas/Clientes_nuevo.jsp?tipocliente=" + tipo + "&tido=" + tido + "&doc=" + doc + "');");

            } else if (action.equals("newCliente")) {
                String id = request.getParameter("id");
                EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaP");
                ClientesJpaController clieDAO = new ClientesJpaController(emf);
                CiudadesJpaController ciudDAO = new CiudadesJpaController(emf);
                ClientesServicioJpaController clseDAO = new ClientesServicioJpaController(emf);
                List<ClientesServicio> listaClienteServicio = (List<ClientesServicio>) session.getAttribute("listaClienteServicio");
                if (!id.equals("")) {
//                    System.out.println("id >" + id);
                    Clientes clie = clieDAO.findClientes(Integer.parseInt(id));
                    clie.setClieTipo(e.o.getvariable("tipo"));

                    clie.setClieNombre(e.o.getvariable("nombre"));
                    clie.setCiudId(ciudDAO.findCiudades(Integer.parseInt(e.o.getvariable("ciudad"))));
                    clie.setClieDireccion(e.o.getvariable("direccion"));
                    clie.setClieTelefonos(e.o.getvariable("telefonos"));
                    clie.setClieEmail(e.o.getvariable("email"));
                    clie.setClierepLegal(e.o.getvariable("repLegal"));
                    clie.setClieObservacion(e.o.getvariable("observaciones"));

                    clie.setClieRegistradopor(e.getUsuarioVO().getIdUsuario());
                    clie.setClieFechacambio(fechas.getFechaHoraTimeStamp());

                    try {
                        clieDAO.edit(clie);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    List<ClientesServicio> listaClienteServicioant = clie.getClientesServicioList();

                    for (ClientesServicio l1 : listaClienteServicio) {
                        if (listaClienteServicioant.remove(l1)) {
                            System.out.println("lo borre id " + l1.getClseId());
                        }
                        l1.setClieId(clie);
                        l1.setClseEstado("ACTIVO");
                        if (l1.getClseId() == null) {
                            clseDAO.create(l1);
                        }
                    }
                    System.out.println("quedan " + listaClienteServicioant.size());
                    for (ClientesServicio l1 : listaClienteServicioant) {
//                        System.out.println(l1.getClieId().getClieNombre());
//                        System.out.println(l1.getClseEstado());
//                        System.out.println(l1.getClseId());
//                        System.out.println(l1.getServId().getServNombre());

                        l1.setClieId(clie);
                        l1.setClseEstado("INACTIVO");
                        l1.setClseFechacambio(fechas.getFechaHoraTimeStamp());
                        l1.setClseRegistradopor(e.getUsuarioVO().getIdUsuario());
                        clseDAO.edit(l1);
                    }
//                    clie.setClientesServicioList(listaClienteServicio);
                    e.getObjetoRespuestaVO().setTipooperacion("ejecutarhtml");
                    e.getObjetoRespuestaVO().setRespuesta("1");
                    e.getObjetoRespuestaVO().setHtml("mensajeOK('Actualizado Correctamente','reloadTabla2','../panels/formularios/basicas/Clientes.jsp');");

                } else {
                    Clientes clie = new Clientes();
                    clie.setTidoId(new TipodocumentoJpaController(emf).findTipodocumento(Integer.parseInt(e.o.getvariable("tipo_documento"))));
                    clie.setClieDocumento(e.o.getvariable("documento"));
                    clie.setClieNombre(e.o.getvariable("nombre"));
                    clie.setClieTipo(e.o.getvariable("tipo"));

                    clie.setCiudId(ciudDAO.findCiudades(Integer.parseInt(e.o.getvariable("ciudad"))));
                    clie.setClieDireccion(e.o.getvariable("direccion"));
                    clie.setClieTelefonos(e.o.getvariable("telefonos"));
                    clie.setClieEmail(e.o.getvariable("email"));
                    clie.setClierepLegal(e.o.getvariable("repLegal"));
                    clie.setClieObservacion(e.o.getvariable("observaciones"));
                    clie.setClieRegistradopor(e.getUsuarioVO().getIdUsuario());
                    clie.setClieFechacambio(fechas.getFechaHoraTimeStamp());

                    clie.setClieEstado("ACTIVO");

                    clieDAO.create(clie);

                    if (listaClienteServicio != null) {
                        for (ClientesServicio l1 : listaClienteServicio) {
                            l1.setClieId(clie);
                            if (l1.getClseId() == null) {
                                clseDAO.create(l1);
                            }
                        }
                    }

                    CentrocostosJpaController cecoDAO = new CentrocostosJpaController(emf);
                    Centrocostos ceco = new Centrocostos();
                    ceco.setClieId(clie);
                    ceco.setCecoObservacion("Centro costo Principal");
                    ceco.setCecoEstado("ACTIVO");
                    ceco.setCecoRegistradopor(e.getUsuarioVO().getIdUsuario());
                    ceco.setCecoFechacambio(fechas.getFechaHoraTimeStamp());
                    cecoDAO.create(ceco);
                    e.getObjetoRespuestaVO().setTipooperacion("ejecutarhtml");
                    e.getObjetoRespuestaVO().setRespuesta("1");
                    e.getObjetoRespuestaVO().setHtml("mensajeOK('Insertado Correctamente','reloadTabla2','../panels/formularios/basicas/Clientes.jsp');");

                }
            } else if (action.equals("eliminarCliente")) {
                String id = e.o.getvariable("id");
                ClientesVO ent = new ClientesVO();
                ent.setClie_id(id);
                ent.setClie_registradopor(e.getUsuarioVO().getIdUsuario());
                ent.setClie_fechacambio(fechas.getFechaHoraTimeStamp());
                System.out.println(">>" + id);

                for (ClientesServiciosVO c : new ClientesServiciosDAO(e).Listar(id)) {

                    new ClientesServiciosDAO(e).Eliminar(c);

                }

                if (new ClientesDAO(e).Eliminar(ent)) {
                    e.getObjetoRespuestaVO().setTipooperacion("ejecutarhtml");
                    e.getObjetoRespuestaVO().setRespuesta("1");
                    e.getObjetoRespuestaVO().setHtml("mensajeOK('Eliminado Correctamente','reloadTabla2','../panels/formularios/basicas/Clientes.jsp');");

                } else {

                    e.getObjetoRespuestaVO().setTipooperacion("error");
                    e.getObjetoRespuestaVO().setRespuesta("0");
                    e.getObjetoRespuestaVO().setHtml("La operación no se realizo correctamente..!!");
                }

            } else if (action.equals("AgregarServicio")) {
                String id = e.o.getvariable("Servicio_sel");
                String valor = e.o.getvariable("precio_text");
                List<ClientesServicio> listaClienteServicio = (List<ClientesServicio>) session.getAttribute("listaClienteServicio");

                if (listaClienteServicio == null) {
                    System.out.println("entre a esta parte ");
                    listaClienteServicio = (List<ClientesServicio>) new ArrayList<ClientesServicio>();
                }
                EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaP");
//                ClientesJpaController clieDAO = new ClientesJpaController(emf);

                ClientesServicio c = new ClientesServicio();
                c.setServId(new ServiciosJpaController(emf).findServicios(Integer.parseInt(id)));
                c.setClseValor(Integer.parseInt(valor));
                c.setClseEstado("ACTIVO");
                c.setClseRegistradopor(e.getUsuarioVO().getIdpersona());
                c.setClseFechacambio(fechas.getFechaHoraTimeStamp());
                listaClienteServicio.add(c);

                session.removeAttribute("listaClienteServicio");
                session.setAttribute("listaClienteServicio", listaClienteServicio);

                e.getObjetoRespuestaVO().setTipooperacion("ejecutarhtml");
                e.getObjetoRespuestaVO().setRespuesta("1");
                e.getObjetoRespuestaVO().setHtml("mensajeOK('Agregado Correctamente','','');RecargaSelect('../panels/formularios/basicas/Clientes_nuevo.jsp?id_ocu=" +/*e.o.getvariable("id_ocu")+*/ "','cajaFormServicios','tablaFormServicios')");

            } else if (action.equals("RemoverServicio")) {
                EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaP");

                int id = Integer.parseInt(e.o.getvariable("hash"));
                List<ClientesServicio> lista = (List<ClientesServicio>) session.getAttribute("listaClienteServicio");
                System.out.println("tamaño de la lista " + lista.size());
                System.out.println("ob " + lista.get(id).getServId().getServNombre());
                lista.remove(id);

                session.removeAttribute("listaClienteServicio");
                session.setAttribute("listaClienteServicio", lista);

                e.getObjetoRespuestaVO().setTipooperacion("ejecutarhtml");
                e.getObjetoRespuestaVO().setRespuesta("1");
                e.getObjetoRespuestaVO().setHtml("mensajeOK('Removido Correctamente','','');RecargaSelect('../panels/formularios/basicas/Clientes_nuevo.jsp?id_ocu="/*+e.o.getvariable("id_ocu")*/ + "','cajaFormServicios','tablaFormServicios')");

            } else if (action.equals("CargarServicios")) {
                String id = e.o.getvariable("id");
                EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaP");
                EntityManager em = emf.createEntityManager();

//                TypedQuery<ClientesServicio> consultaDocumento = em.createNamedQuery("", ClientesServicio.class);
//                consultaDocumento.setParameter("id", e.o.notEmpty(id));
//                List<ClientesServicio> listaClienteServicio = consultaDocumento.getResultList();
//
//                session.removeAttribute("listaClienteServicio");
//                session.setAttribute("listaClienteServicio", listaClienteServicio);
                e.getObjetoRespuestaVO().setTipooperacion("ejecutarhtml");
                e.getObjetoRespuestaVO().setRespuesta("1");
                e.getObjetoRespuestaVO().setHtml("mensajeOK('Cargado Correctamente','','');RecargaSelect('../panels/formularios/basicas/Clientes.jsp','cajaFormServicios','tablaFormServicios')");

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
