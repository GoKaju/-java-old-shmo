/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ocupacional.servlets.facturacion;

import formularios.controlers.PacientesJpaController;
import formularios.entidades.Pacientes;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Iterator;
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
import ocupacional.JPA.controlers.FacturasJpaController;
import ocupacional.JPA.controlers.ItemfacturaJpaController;
import ocupacional.JPA.controlers.MovimientosfacturacionJpaController;
import ocupacional.JPA.valueobjects.Centrocostos;
import ocupacional.JPA.valueobjects.Facturas;
import ocupacional.JPA.valueobjects.Itemfactura;
import ocupacional.JPA.valueobjects.Movimientosfacturacion;
import ocupacional.bdatos.facturacion.TicketClienteServicioDAO;
import ocupacional.bdatos.facturacion.TicketsDAO;
import ocupacional.servlets.Servlet_ctr_entidades;
import ocupacional.valueobjects.facturacion.Ticket;
import ocupacional.valueobjects.facturacion.TicketClienteServicio;
import valeria.metodos.ManejadorFechas;
import valeria.response.Mediador;
import valeria.response.ObjetoRespuestaVO;
import valeria.response.RespuestaXML;

/**
 *
 * @author D4V3
 */
@WebServlet(name = "Servlet_ctr_Facturas", urlPatterns = {"/Facturas"})
public class Servlet_ctr_facturas extends HttpServlet {
    
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
            
            if (action.equals("ModificarOrdenServicio")) {
                ArrayList<TicketClienteServicio> listaTCServicio = (ArrayList<TicketClienteServicio>) session.getAttribute("listaTCServicio");
                if (listaTCServicio != null && listaTCServicio.size() > 0) {
                    
                    EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaP1PU");
                    TicketsDAO tdao = new TicketsDAO(e);
                    
                    PacientesJpaController pacic = new PacientesJpaController(emf);
//                   se debe cargar por numero de documento 
                    EntityManager em = emf.createEntityManager();
                    TypedQuery<Pacientes> consultaDocumento = em.createNamedQuery("Pacientes.findByPaciDocumento", Pacientes.class);
                    
                    String documentos[] = request.getParameterValues("paci_documento");
                    String nombres[] = request.getParameterValues("paci_nombres");
                    String apellidos[] = request.getParameterValues("paci_apellidos");
                    int x = 0;
                    for (String documento : documentos) {
                        
                        consultaDocumento.setParameter("paciDocumento", documento);
                        List<Pacientes> lista = consultaDocumento.getResultList();
                        Pacientes paci = new Pacientes();
                        em.close();
                        for (Pacientes p : lista) {
                            paci = p;
                        }
                        
                        if (paci.getPaciId() == null) {
                            
                            paci = new Pacientes();
                            paci.setPaciDocumento(documentos[x]);
                            paci.setPaciNombres(nombres[x].toUpperCase());
                            paci.setPaciApellidos(apellidos[x].toUpperCase());
                            
                            paci.setPaciRegistradopor(e.getUsuarioVO().getIdpersona());
                            paci.setPaciFechacambio(fechas.getFechaHoraTimeStamp());
                            pacic.create(paci);
                        } else {
                            paci.setPaciDocumento(documentos[x]);
                            paci.setPaciNombres(nombres[x].toUpperCase());
                            paci.setPaciApellidos(apellidos[x].toUpperCase());
                            
                            paci.setPaciRegistradopor(e.getUsuarioVO().getIdpersona());
                            paci.setPaciFechacambio(fechas.getFechaHoraTimeStamp());
                            pacic.edit(paci);
                        }
                        
                        Ticket t = new Ticket();
                        t.setSede_id(e.o.getvariable("sede_sel"));
                        t.setTick_clsede(e.o.getvariable("sedefinca_sel"));
                        
                        t.setCeco_id(e.o.getvariable("centro_costo"));
                        t.setTeme_id(e.o.getvariable("tipo_examen"));
                        t.setTick_estado(e.o.getvariable("tick_estado"));
                        t.setTick_paciente(paci.getPaciId().toString());
                        t.setTick_registradopor(e.getUsuarioVO().getIdpersona());
                        t.setTick_fechacambio(fechas.getFechaHoraTimeStamp());
                        t.setTick_fecharegistro(fechas.getFechaHoraTimeStamp());
                        t.setTick_id(e.o.getvariable("id"));
                        if (!t.getTick_id().equals("")) {
//                    Aqui actualizar estoy hay que verificar la eliminacion con la restriccion de las anotaciones 

                            if (tdao.Actualizar(t)) {
                                TicketClienteServicioDAO tcsdao = new TicketClienteServicioDAO(e);
                                int c = 0;
                                int d = 0;
                                ArrayList<TicketClienteServicio> listaEliminar = tcsdao.ListarxTicket(t.getTick_id());
                                
                                Iterator<TicketClienteServicio> it = null;
                                if (listaEliminar != null) {
                                    it = listaEliminar.iterator();
                                }
                                
                                while (it.hasNext()) {
                                    TicketClienteServicio next = it.next();
                                    for (TicketClienteServicio l : listaTCServicio) {
                                        if (l.getTics_id() != null) {
                                            if (next.getTics_id().equals(l.getTics_id())) {
                                                it.remove();
                                                
                                            }
                                        } else {
                                            
                                            l.setTick_id(t.getTick_id());
                                            l.setTics_registradopor(e.getUsuarioVO().getIdpersona());
                                            l.setTics_fechacambio(fechas.getFechaHoraTimeStamp());
                                            if (tcsdao.Insertar(l)) {
                                                c++;
                                            }
                                        }
                                    }
                                }
                                
                                Iterator<TicketClienteServicio> it2 = null;
                                if (listaEliminar.size() > 0) {
                                    it2 = listaEliminar.iterator();
                                    
                                    while (it2.hasNext()) {
                                        TicketClienteServicio next = it2.next();
                                        tcsdao.Eliminar(next);
                                    }
                                }
                                
                                e.getObjetoRespuestaVO().setTipooperacion("ejecutarhtml");
                                e.getObjetoRespuestaVO().setRespuesta("1");
                                
                                e.getObjetoRespuestaVO().setHtml("$('#panelprincipal').load('../panels/formularios/factura/OrdenServiciomdf.jsp',function(){alertify.success('Actualizado Correctamente')})");
                                
                            } else {
                                e.getObjetoRespuestaVO().setTipooperacion("error");
                                e.getObjetoRespuestaVO().setRespuesta("0");
                                e.getObjetoRespuestaVO().setHtml("La operación no se realizo correctamente..!!");
                                
                            }
                            
                        }
                        
                        x++;
                    }
                } else {
                    
                    e.getObjetoRespuestaVO().setTipooperacion("error");
                    e.getObjetoRespuestaVO().setRespuesta("0");
                    e.getObjetoRespuestaVO().setHtml("Debe agregar minimo un servicio..!!");
                    
                }
                
            } else if (action.equals("eliminarItem")) {
                try {
                    EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaP");
                    Itemfactura fac = new ItemfacturaJpaController(emf).findItemfactura(Integer.parseInt(e.o.getvariable("id")));
                    
                    
                    new ItemfacturaJpaController(emf).destroy(fac.getItfaId());
                    Facturas factura = new FacturasJpaController(emf).findFacturas(fac.getFactId().getFactId());
//            cambiar total a la factura
                    int total = 0;
                    for (Itemfactura i : factura.getItemfacturaList()) {
                        
                        total += i.getIdfaValor()*i.getItfaCantidad();
                    }
                    factura.setFactTotal(total);
                    factura.setFactRegistradopor(e.getUsuarioVO().getIdUsuario());
                    factura.setFactFechacambio(fechas.getFechaHoraTimeStamp());
                    new FacturasJpaController(emf).edit(factura);
                    e.getObjetoRespuestaVO().setRespuesta("1");
                    e.getObjetoRespuestaVO().setTipooperacion("ejecutarhtml");
                    e.getObjetoRespuestaVO().setHtml("$('#panelprincipal').load('../panels/formularios/factura/factura_view_mdf.jsp','id_ocu=" + factura.getFactId() + "',function(){alertify.success('Removido con exito')})");
                    
                } catch (Exception ex) {
                    ex.printStackTrace();
                    e.getObjetoRespuestaVO().setRespuesta("0");
                    e.getObjetoRespuestaVO().setTipooperacion("error");
                    e.getObjetoRespuestaVO().setHtml("Hubo un error al procesar.");
                    
                }                
            } else if (action.equals("AgregarItem")) {
                try {
                    EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaP");
                    Facturas fac = new FacturasJpaController(emf).findFacturas(Integer.parseInt(e.o.getvariable("id_ocu")));
                    fac.setFactFechacambio(fechas.getFechaHoraTimeStamp());
                    fac.setFactRegistradopor(e.getUsuarioVO().getIdUsuario());
                    
                    new FacturasJpaController(emf).edit(fac);
                    Itemfactura item = new Itemfactura();
                    item.setFactId(fac);
                    item.setItfaCantidad(Integer.parseInt(e.o.getvariable("cantidad_txt")));
                    item.setIdfaNombre(e.o.getvariable("descripcion_txt"));
                    item.setIdfaValor(Integer.parseInt(e.o.getvariable("valor_txt")));
                    new ItemfacturaJpaController(emf).create(item);
                    //            cambiar total a la factura
                    Facturas factura = item.getFactId();
                    int total = 0;
                    for (Itemfactura i : factura.getItemfacturaList()) {
                        
                        total += i.getIdfaValor()*i.getItfaCantidad();
                    }
                    factura.setFactTotal(total);
                    factura.setFactRegistradopor(e.getUsuarioVO().getIdUsuario());
                    factura.setFactFechacambio(fechas.getFechaHoraTimeStamp());
                    new FacturasJpaController(emf).edit(factura);
                    e.getObjetoRespuestaVO().setRespuesta("1");
                    e.getObjetoRespuestaVO().setTipooperacion("ejecutarhtml");
                    e.getObjetoRespuestaVO().setHtml("$('#panelprincipal').load('../panels/formularios/factura/factura_view_mdf.jsp','id_ocu=" + fac.getFactId() + "',function(){alertify.success('Agregado con exito')})");
                    
                } catch (Exception ex) {
                    ex.printStackTrace();
                    e.getObjetoRespuestaVO().setRespuesta("0");
                    e.getObjetoRespuestaVO().setTipooperacion("error");
                    e.getObjetoRespuestaVO().setHtml("Hubo un error al procesar.");
                    
                }                
            } else if (action.equals("cambiarEstado")) {
                try {
                    EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaP");
                    Facturas fac = new FacturasJpaController(emf).findFacturas(Integer.parseInt(e.o.getvariable("id")));
                    fac.setFactFechacambio(fechas.getFechaHoraTimeStamp());
                    fac.setFactRegistradopor(e.getUsuarioVO().getIdUsuario());
                    fac.setFactEstado(e.o.getvariable("fac_estado"));
                    new FacturasJpaController(emf).edit(fac);
                    e.getObjetoRespuestaVO().setRespuesta("1");
                    e.getObjetoRespuestaVO().setTipooperacion("ejecutarhtml");
                    e.getObjetoRespuestaVO().setHtml("$('#panelprincipal').load('../panels/formularios/factura/factura_view_mdf.jsp','id_ocu=" + fac.getFactId() + "',function(){alertify.success('Modificado con exito')})");
                    
                } catch (Exception ex) {
                    ex.printStackTrace();
                    e.getObjetoRespuestaVO().setRespuesta("0");
                    e.getObjetoRespuestaVO().setTipooperacion("error");
                    e.getObjetoRespuestaVO().setHtml("Hubo un error al procesar.");
                    
                }                
            } else if (action.equals("cambiarFecha")) {
                try {
                    EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaP");
                    Facturas fac = new FacturasJpaController(emf).findFacturas(Integer.parseInt(e.o.getvariable("id")));
                    fac.setFactFechacambio(fechas.getFechaHoraTimeStamp());
                    fac.setFactRegistradopor(e.getUsuarioVO().getIdUsuario());
                    fac.setFactfechaCreacion(fechas.StringToTimeStamp(e.o.getvariable("fecha")));
                    new FacturasJpaController(emf).edit(fac);
                    e.getObjetoRespuestaVO().setRespuesta("1");
                    e.getObjetoRespuestaVO().setTipooperacion("ejecutarhtml");
                    e.getObjetoRespuestaVO().setHtml("$('#panelprincipal').load('../panels/formularios/factura/factura_view_mdf.jsp','id_ocu=" + fac.getFactId() + "',function(){alertify.success('Modificado con exito')})");
                    
                } catch (Exception ex) {
                    ex.printStackTrace();
                    e.getObjetoRespuestaVO().setRespuesta("0");
                    e.getObjetoRespuestaVO().setTipooperacion("error");
                    e.getObjetoRespuestaVO().setHtml("Hubo un error al procesar.");
                    
                }                
            } else if (action.equals("eliminarItemNuevaFactura")) {
                List<Itemfactura> lista =(List<Itemfactura>) session.getAttribute("listaItemCrearFactura");
//                crear movimiento
                  
                
                    for(Itemfactura i : lista){
                        if(i.hashCode()== Integer.parseInt(e.o.getvariable("hash"))){
                    lista.remove(i);
                    break;
                    }
                    }
                   session.setAttribute("listaItemCrearFactura", lista);

                        e.getObjetoRespuestaVO().setRespuesta("1");
                                e.getObjetoRespuestaVO().setTipooperacion("ejecutarhtml");
                                e.getObjetoRespuestaVO().setHtml("$('#cajaitems').load('../panels/formularios/factura/FacturaNueva_p2.jsp  #tablaItems','clie_ocu="+e.o.getvariable("clie_ocu")+"',function(){alertify.success('Removido con exito')})");
                                
                
                    
                
            } else if (action.equals("GuardarNuevaFactura")) {
                try{   
                EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaP");
                List<Itemfactura> lista =(List<Itemfactura>) session.getAttribute("listaItemCrearFactura");
                if(!lista.isEmpty()){
//                crear movimiento
                    int total =0;
                    for(Itemfactura i : lista){
                    total+= (i.getIdfaValor()*i.getItfaCantidad());
                    }
                    Movimientosfacturacion mofa = new Movimientosfacturacion();
                    mofa.setMofaEstado("ACTIVA");
                    mofa.setMofaFechacambio(fechas.getFechaHoraTimeStamp());
                    mofa.setMofaFechacreacion(fechas.getFechaHoraTimeStamp());
                    mofa.setMofaRegistradopor(e.getUsuarioVO().getIdUsuario());
                    mofa.setMofaObservacion(e.o.getvariable("observacion"));
                    new MovimientosfacturacionJpaController(emf).create(mofa);
                    Facturas fac = new Facturas();
                    fac.setCecoId(new CentrocostosJpaController(emf).findCentrocostos(Integer.parseInt(e.o.getvariable("sede"))));
                    fac.setFactEstado("ACTIVA");
                    fac.setFactFechacambio(fechas.getFechaHoraTimeStamp());
                    fac.setFactRegistradopor(e.getUsuarioVO().getIdUsuario());
                    fac.setFactTotal(total);
                    fac.setFactfechaCreacion(fechas.StringToTimeStamp(e.o.getvariable("fecha")));
                    fac.setMofaId(mofa);
                    new FacturasJpaController(emf).create(fac);
//                    guardar items
                      for(Itemfactura i : lista){
                   i.setFactId(fac);
                   new ItemfacturaJpaController(emf).create(i);
                          
                    }
                      
                          e.getObjetoRespuestaVO().setRespuesta("1");
                                e.getObjetoRespuestaVO().setTipooperacion("ejecutarhtml");
                                e.getObjetoRespuestaVO().setHtml("$('#panelprincipal').load('../panels/formularios/factura/FacturaNueva.jsp','',function(){alertify.success('Factura creada con exito')})");
                                
                
                
                 
                    
                }else{
                              e.getObjetoRespuestaVO().setRespuesta("0");
                                e.getObjetoRespuestaVO().setTipooperacion("error");
                                e.getObjetoRespuestaVO().setHtml("Debe agregar minimo un item.");
                
                }
                      } catch (Exception ex) {
                    ex.printStackTrace();
                    e.getObjetoRespuestaVO().setRespuesta("0");
                    e.getObjetoRespuestaVO().setTipooperacion("error");
                    e.getObjetoRespuestaVO().setHtml("Hubo un error al procesar.");
                    
                }  
                
                
            } else if (action.equals("AgregarItemNuevaFac")) {
                Itemfactura itfac = new Itemfactura();
                itfac.setIdfaNombre(e.o.getvariable("descripcion_txt"));
                itfac.setIdfaValor(Integer.parseInt(e.o.getvariable("valor_txt")));
                itfac.setItfaCantidad(Integer.parseInt(e.o.getvariable("cantidad_txt")));
                List<Itemfactura> lista =(List<Itemfactura>) session.getAttribute("listaItemCrearFactura");
                lista.add(itfac);
                session.setAttribute("listaItemCrearFactura", lista);
                   e.getObjetoRespuestaVO().setRespuesta("1");
                                e.getObjetoRespuestaVO().setTipooperacion("ejecutarhtml");
                                e.getObjetoRespuestaVO().setHtml("$('#cajaitems').load('../panels/formularios/factura/FacturaNueva_p2.jsp  #tablaItems','clie_ocu="+e.o.getvariable("clie_ocu")+"',function(){alertify.success('Agregado con exito')})");
                                
                
                
                
            } else if (action.equals("facturaModificar")) {
                EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaP");
                String id = e.o.getvariable("fac_codigo");
                if (id != null && !id.isEmpty()) {
                    EntityManager em = emf.createEntityManager();
                    TypedQuery<Facturas> consultaDocumento = em.createNamedQuery("Facturas.findByFactConsecutivo", Facturas.class);
                    consultaDocumento.setParameter("factConsecutivo", Integer.parseInt(id));
                    
                    Facturas f = null;
                    if (!consultaDocumento.getResultList().isEmpty()) {
                        for (Facturas fac : consultaDocumento.getResultList()) {
                            f = fac;
                            if (fac != null) {
                                
                                e.getObjetoRespuestaVO().setRespuesta("1");
                                e.getObjetoRespuestaVO().setTipooperacion("ejecutarhtml");
                                e.getObjetoRespuestaVO().setHtml("$('#panelprincipal').load('../panels/formularios/factura/factura_view_mdf.jsp','id_ocu=" + fac.getFactId() + "',function(){alertify.success('Cargado con exito')})");
                                
                            } else {
                                
                                e.getObjetoRespuestaVO().setRespuesta("0");
                                e.getObjetoRespuestaVO().setTipooperacion("error");
                                e.getObjetoRespuestaVO().setHtml("No se encontro factura con ese codigo.");
                            }
                        }
                        
                    } else {
                        
                        e.getObjetoRespuestaVO().setRespuesta("0");
                        e.getObjetoRespuestaVO().setTipooperacion("error");
                        e.getObjetoRespuestaVO().setHtml("No se encontro factura con ese codigo.");
                        
                    }
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
        
        System.out.println(
                new RespuestaXML().GenerarXML(e.getObjetoRespuestaVO()));
        out.print(
                new RespuestaXML().GenerarXML(e.getObjetoRespuestaVO()));
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
            Logger.getLogger(Servlet_ctr_entidades.class
                    .getName()).log(Level.SEVERE, null, ex);
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
            Logger
                    .getLogger(Servlet_ctr_entidades.class
                            .getName()).log(Level.SEVERE, null, ex);
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
