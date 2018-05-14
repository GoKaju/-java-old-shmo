/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ocupacional.servlets.facturacion;

import formularios.controlers.AnotacionesJpaController;
import formularios.controlers.EmpleadosJpaController;
import formularios.controlers.PacientesJpaController;
import formularios.entidades.Anotaciones;
import formularios.entidades.Pacientes;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ocupacional.JPA.controlers.CentrocostosJpaController;
import ocupacional.JPA.controlers.ClientesJpaController;
import ocupacional.JPA.controlers.TicketJpaController;
import ocupacional.JPA.valueobjects.Centrocostos;
import ocupacional.JPA.valueobjects.Clientes;
import ocupacional.JPA.valueobjects.Empleados;
import ocupacional.JPA.valueobjects.TicketClienteservicio;
import ocupacional.bdatos.facturacion.ClientesDAO;
import ocupacional.bdatos.facturacion.TicketClienteServicioDAO;
import ocupacional.bdatos.facturacion.TicketsDAO;
import ocupacional.servlets.Servlet_ctr_entidades;
import ocupacional.valueobjects.facturacion.ClientesVO;
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
@WebServlet(name = "Servlet_ctr_OrdenServicio", urlPatterns = {"/OrdenServicio"})
public class Servlet_ctr_OrdenServicio extends HttpServlet {

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

            if (action.equals("valPaciente")) {

                EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaP1PU");
                emf.getCache().evictAll();

                PacientesJpaController pacic = new PacientesJpaController(emf);

                String doc = e.o.getvariable("doc");
                String caja = e.o.getvariable("caja_id");
                if (!doc.equals("")) {
                    EntityManager em = emf.createEntityManager();
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
                        e.getObjetoRespuestaVO().setHtml("$('#p" + caja + "').find('#paci_nombres" + caja + "').val('" + paci.getPaciNombres() + "');$('#p" + caja + "').find('#paci_apellidos" + caja + "').val('" + paci.getPaciApellidos() + "');$('#p" + caja + "').find('#paci_nombres" + caja + "').attr('readonly','');$('#p" + caja + "').find('#paci_apellidos" + caja + "').attr('readonly',''); ");

                    } else {
                        e.getObjetoRespuestaVO().setTipooperacion("ejecutarhtml");
                        e.getObjetoRespuestaVO().setRespuesta("1");
                        e.getObjetoRespuestaVO().setHtml("$('#p" + caja + "').find('#paci_nombres" + caja + "').val('');$('#p" + caja + "').find('#paci_apellidos" + caja + "').val('');$('#p" + caja + "').find('#paci_nombres" + caja + "').removeAttr('readonly');$('#p" + caja + "').find('#paci_apellidos" + caja + "').removeAttr('readonly');  ");

                    }
                } else {
                    e.getObjetoRespuestaVO().setTipooperacion("ejecutarhtml");
                    e.getObjetoRespuestaVO().setRespuesta("1");
                    e.getObjetoRespuestaVO().setHtml("$('#p" + caja + "').find('#paci_nombres" + caja + "').val('');$('#p" + caja + "').find('#paci_apellidos" + caja + "').val('');$('#p" + caja + "').find('#paci_nombres" + caja + "').removeAttr('readonly');$('#p" + caja + "').find('#paci_apellidos" + caja + "').removeAttr('readonly'); ");

                }

            } else if (action.equals("nuevaOrdenServicio")) {
                ArrayList<TicketClienteServicio> listaTCServicio = (ArrayList<TicketClienteServicio>) session.getAttribute("listaTCServicio");
                if (listaTCServicio != null && listaTCServicio.size() > 0) {

                    TicketsDAO tdao = new TicketsDAO(e);

                    EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaP1PU");
                    emf.getCache().evictAll();

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
                        }

                        Ticket t = new Ticket();
                        t.setSede_id(e.o.getvariable("sede_sel"));
                        t.setTick_clsede(e.o.getvariable("sedefinca_sel"));
                        t.setCeco_id(e.o.getvariable("centro_costo"));
                        t.setTeme_id(e.o.getvariable("tipo_examen"));
                        t.setTick_brigada(e.o.getvariable("nombreBrigada"));
                        t.setTick_otroexamen(e.o.getvariable("tipoExamenotro"));
                        t.setTick_estado("POR PROCESAR");
                        t.setTick_paciente(paci.getPaciId().toString());
                        t.setTick_registradopor(e.getUsuarioVO().getIdpersona());
                        t.setTick_fechacambio(fechas.getFechaHoraTimeStamp());
                        t.setTick_fecharegistro(fechas.getFechaHoraTimeStamp());
                        t.setTick_id(e.o.getvariable("id"));
                        if (!t.getTick_id().equals("")) {
//                    Aqui actualizar
                            if (tdao.Cargar(t.getTick_id()).getTick_estado().equals("POR PROCESAR")) {

                                if (tdao.Actualizar(t)) {
                                    TicketClienteServicioDAO tcsdao = new TicketClienteServicioDAO(e);
                                    int c = 0;
                                    int d = 0;
                                    ArrayList<TicketClienteServicio> listaEliminar = tcsdao.ListarxTicket(t.getTick_id());
                                    if (listaEliminar != null) {
                                        for (TicketClienteServicio listaTCServicio1 : listaEliminar) {
                                            if (tcsdao.Eliminar(listaTCServicio1)) {
                                                d++;
                                            }
                                        }
                                    }

                                    for (TicketClienteServicio l : listaTCServicio) {
                                        l.setTick_id(t.getTick_id());
                                        l.setTics_registradopor(e.getUsuarioVO().getIdpersona());
                                        l.setTics_fechacambio(fechas.getFechaHoraTimeStamp());
                                        if (tcsdao.Insertar(l)) {
                                            c++;
                                        }

                                    }

                                    if (c == listaTCServicio.size() && d == listaEliminar.size()) {
                                        e.getObjetoRespuestaVO().setTipooperacion("ejecutarhtml");
                                        e.getObjetoRespuestaVO().setRespuesta("1");
                                        if (e.getUsuarioVO().getUsua_tipo().equals("C")) {
                                            e.getObjetoRespuestaVO().setHtml("$('#panelprincipal').load('../panels/formularios/factura/ordenServicio.jsp',function(){alertify.success('Actualizado Correctamente')})");
                                        } else {
                                            e.getObjetoRespuestaVO().setHtml("$('#panelprincipal').load('../panels/formularios/factura/ordenServicio.jsp?clie_ocu=" + e.o.getvariable("id_ocu") + "',function(){alertify.success('Actualizado Correctamente')})");
                                        }

                                    } else {
                                        e.getObjetoRespuestaVO().setTipooperacion("error");
                                        e.getObjetoRespuestaVO().setRespuesta("0");
                                        e.getObjetoRespuestaVO().setHtml("La operación no se realizo correctamente..!!");

                                    }

                                }
                            } else {

                                e.getObjetoRespuestaVO().setTipooperacion("error");
                                e.getObjetoRespuestaVO().setRespuesta("0");
                                e.getObjetoRespuestaVO().setHtml("Ya se ha comenzado a atender esta orden de servicio, ya no es posible modificarla.!");

                            }

                        } else {

                            if (tdao.Insertar(t)) {
                                TicketClienteServicioDAO tcsdao = new TicketClienteServicioDAO(e);
                                int c = 0;
                                for (TicketClienteServicio l : listaTCServicio) {
                                    l.setTick_id(t.getTick_id());

                                    l.setTics_registradopor(e.getUsuarioVO().getIdpersona());
                                    l.setTics_fechacambio(fechas.getFechaHoraTimeStamp());
                                    if (tcsdao.Insertar(l)) {
                                        c++;
                                    }

                                }

                                if (c == listaTCServicio.size()) {
                                    e.getObjetoRespuestaVO().setTipooperacion("ejecutarhtml");
                                    e.getObjetoRespuestaVO().setRespuesta("1");
                                    if (e.getUsuarioVO().getUsua_tipo().equals("C")) {
                                        e.getObjetoRespuestaVO().setHtml("$('#panelprincipal').load('../panels/formularios/factura/ordenServicio.jsp',function(){alertify.success('Insertado Correctamente')})");
                                    } else {
                                        e.getObjetoRespuestaVO().setHtml("$('#panelprincipal').load('../panels/formularios/factura/ordenServicio.jsp?clie_ocu=" + e.o.getvariable("id_ocu") + "',function(){alertify.success('Insertado Correctamente')})");
                                    }

                                } else {
                                    e.getObjetoRespuestaVO().setTipooperacion("error");
                                    e.getObjetoRespuestaVO().setRespuesta("0");
                                    e.getObjetoRespuestaVO().setHtml("La operación no se realizo correctamente..!!");

                                }

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

            } else if (action.equals("ModificarOrdenServicio")) {
                ArrayList<TicketClienteServicio> listaTCServicio = (ArrayList<TicketClienteServicio>) session.getAttribute("listaTCServicio");
                if (listaTCServicio != null && listaTCServicio.size() > 0) {

                    TicketsDAO tdao = new TicketsDAO(e);

                    EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaP1PU");
                    emf.getCache().evictAll();

                    EntityManagerFactory emf2 = Persistence.createEntityManagerFactory("JavaP");
                    emf2.getCache().evictAll();

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

                        Ticket t = tdao.Cargar(e.o.getvariable("id"));
                        t.setSede_id(e.o.getvariable("sede_sel"));
                        t.setTick_clsede(e.o.getvariable("sedefinca_sel"));

                        t.setCeco_id(e.o.getvariable("centro_costo"));
                        t.setTeme_id(e.o.getvariable("tipo_examen"));
                        t.setTick_estado(e.o.getvariable("tick_estado"));
                        if (t.getTick_estado().equals("PROCESADO")) {
                            if (t.getTick_fechaprocesado() == null) {
                                t.setTick_fechaprocesado(fechas.getFechaHoraTimeStamp());
                            }
                        }

                        t.setTick_brigada(e.o.getvariable("nombreBrigada"));
                        t.setTick_otroexamen(e.o.getvariable("tipoExamenotro"));
                        System.out.println("BRIGADA:::: " + t.getTick_brigada() + " " + t.getTick_otroexamen());
//                        si el estado es procesando o por procesar cambiar el estado de las anotaciones para los tcs

                        t.setTick_paciente(paci.getPaciId().toString());
                        t.setTick_registradopor(e.getUsuarioVO().getIdpersona());
                        t.setTick_fechacambio(fechas.getFechaHoraTimeStamp());
//                        t.setTick_fecharegistro(fechas.getFechaHoraTimeStamp());
//                        t.setTick_id(e.o.getvariable("id"));
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
//                        si el estado es procesando o por procesar cambiar el estado de las anotaciones para los tcs
//                                aqui estoy listar tcs
                                if (t.getTick_estado().equals("PROCESANDO") || t.getTick_estado().equals("POR PROCESAR")) {
                                    ocupacional.JPA.valueobjects.Ticket ti = new TicketJpaController(emf2).findTicket(Integer.parseInt(t.getTick_id()));

                                    for (TicketClienteservicio tcls : ti.getTicketClienteservicioList()) {

                                        TypedQuery<Anotaciones> consultaxtcls = em.createNamedQuery("Anotaciones.t", Anotaciones.class);
                                        consultaxtcls.setParameter("id", tcls.getTicsId());
                                        List<Anotaciones> l = consultaxtcls.getResultList();
                                        if (!l.isEmpty()) {
                                            for (Anotaciones a : l) {
                                                a.setAnotEstado("EN EDICION");
//                                                a.setAnotFechacambio(fechas.getFechaHoraTimeStamp());
//                                                a.setAnotRegistradopor(e.getUsuarioVO().getIdUsuario());
                                                new AnotacionesJpaController(emf).edit(a);

                                            }
                                        }
                                    }
                                }
                                if (t.getTick_estado().equals("PROCESADO")) {
                                    ocupacional.JPA.valueobjects.Ticket ti = new TicketJpaController(emf2).findTicket(Integer.parseInt(t.getTick_id()));

                                    for (TicketClienteservicio tcls : ti.getTicketClienteservicioList()) {

                                        TypedQuery<Anotaciones> consultaxtcls = em.createNamedQuery("Anotaciones.t", Anotaciones.class);
                                        consultaxtcls.setParameter("id", tcls.getTicsId());
                                        List<Anotaciones> l = consultaxtcls.getResultList();
                                        if (!l.isEmpty()) {
                                            for (Anotaciones a : l) {
                                                a.setAnotEstado("FINALIZADO");
//                                                a.setAnotFechacambio(fechas.getFechaHoraTimeStamp());
//                                                a.setAnotRegistradopor(e.getUsuarioVO().getIdUsuario());
                                                new AnotacionesJpaController(emf).edit(a);

                                            }
                                        }
                                    }

                                }
//                                System.out.println("Voy a hacer Flush");
//                                em.flush();
////                                emf2.createEntityManager().flush();
//                                System.out.println("Flush ejecutado correctamente...");
//                                

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

            } else if (action.equals("addServicio")) {

                ArrayList<TicketClienteServicio> listaTCServicio = (ArrayList<TicketClienteServicio>) session.getAttribute("listaTCServicio");
                if (listaTCServicio == null) {
                    listaTCServicio = new ArrayList<TicketClienteServicio>();
                }
                TicketClienteServicio t = new TicketClienteServicio();
                t.setClse_id(e.o.getvariable("Servicio_sel"));
                t.setTics_registradopor(e.getUsuarioVO().getIdpersona());
                t.setTics_fechacambio(fechas.getFechaHoraTimeStamp());
                if (listaTCServicio.add(t)) {
                    session.removeAttribute("listaTCServicio");
                    session.setAttribute("listaTCServicio", listaTCServicio);
                    e.getObjetoRespuestaVO().setTipooperacion("ejecutarhtml");
                    e.getObjetoRespuestaVO().setRespuesta("1");
                    e.getObjetoRespuestaVO().setHtml("$('#cajaServicios').load('../panels/formularios/factura/ordenServicio_nueva.jsp?id_ocu=" + request.getParameter("id_ocu") + "  #tablaServicios',function(){alertify.success('Agregado con exito')})");

                } else {
                    e.getObjetoRespuestaVO().setTipooperacion("error");
                    e.getObjetoRespuestaVO().setRespuesta("0");
                    e.getObjetoRespuestaVO().setHtml("La operación no se realizo correctamente..!!");

                }

            } else if (action.equals("eliminartcs")) {
                int id = Integer.parseInt(e.o.getvariable("id"));

                ArrayList<TicketClienteServicio> listaTCServicio = (ArrayList<TicketClienteServicio>) session.getAttribute("listaTCServicio");
                for (TicketClienteServicio listaTCServicio1 : listaTCServicio) {

                    if (listaTCServicio1.hashCode() == id) {
                        if (listaTCServicio.remove(listaTCServicio1)) {
                            session.removeAttribute("listaTCServicio");
                            session.setAttribute("listaTCServicio", listaTCServicio);
                            e.getObjetoRespuestaVO().setTipooperacion("ejecutarhtml");
                            e.getObjetoRespuestaVO().setRespuesta("1");
                            e.getObjetoRespuestaVO().setHtml("$('#cajaServicios').load('../panels/formularios/factura/ordenServicio_nueva.jsp?id_ocu=" + request.getParameter("id_ocu") + "  #tablaServicios',function(){alertify.success('Removido con exito')})");

                        } else {
                            e.getObjetoRespuestaVO().setTipooperacion("error");
                            e.getObjetoRespuestaVO().setRespuesta("0");
                            e.getObjetoRespuestaVO().setHtml("La operación no se realizo correctamente..!!");

                        }
                        break;

                    }
                }

            } else if (action.equals("eliminarTicket")) {
                String id = e.o.getvariable("id");
                TicketClienteServicioDAO tcsdao = new TicketClienteServicioDAO(e);
                ArrayList<TicketClienteServicio> listaTCServicio = tcsdao.ListarxTicket(id);
                int c = 0;
                if (listaTCServicio != null) {
                    for (TicketClienteServicio listaTCServicio1 : listaTCServicio) {
                        if (tcsdao.Eliminar(listaTCServicio1)) {
                            c++;
                        }
                    }
                }
                if (listaTCServicio == null || c == listaTCServicio.size()) {

                    if (new TicketsDAO(e).Eliminar(id)) {

                        e.getObjetoRespuestaVO().setTipooperacion("ejecutarhtml");
                        e.getObjetoRespuestaVO().setRespuesta("1");
                        if (e.getUsuarioVO().getUsua_tipo().equals("C")) {
                            e.getObjetoRespuestaVO().setHtml("$('#panelprincipal').load('../panels/formularios/factura/ordenServicio.jsp',function(){alertify.success('Removido con exito')})");
                        } else {
                            e.getObjetoRespuestaVO().setHtml("$('#panelprincipal').load('../panels/formularios/factura/ordenServicio.jsp?clie_ocu=" + e.o.getvariable("id_ocu") + "',function(){alertify.success('Removido con exito')})");
                        }
                    } else {
                        e.getObjetoRespuestaVO().setTipooperacion("error");
                        e.getObjetoRespuestaVO().setRespuesta("0");
                        e.getObjetoRespuestaVO().setHtml("La operación no se realizo correctamente..!!");

                    }

                } else {
                    e.getObjetoRespuestaVO().setTipooperacion("error");
                    e.getObjetoRespuestaVO().setRespuesta("0");
                    e.getObjetoRespuestaVO().setHtml("La operación no se realizo correctamente..!!");

                }

            } else if (action.equals("eliminarSSession")) {
                String id = e.o.getvariable("id_ocu");
                session.removeAttribute("listaTCServicio");
                e.getObjetoRespuestaVO().setTipooperacion("ejecutarhtml");
                e.getObjetoRespuestaVO().setRespuesta("1");
                if (request.getParameter("id") != null) {
                    e.getObjetoRespuestaVO().setHtml("RecargaPanel('../panels/formularios/factura/ordenServicio_nueva.jsp?id_ocu=" + id + "&id=" + request.getParameter("id") + "','panelprincipal')");
                } else {
                    e.getObjetoRespuestaVO().setHtml("RecargaPanel('../panels/formularios/factura/ordenServicio_nueva.jsp?id_ocu=" + id + "','panelprincipal')");
                }

            } else if (action.equals("listarClientes")) {
                session.removeAttribute("listaClientesOrdenS");
                e.getObjetoRespuestaVO().setTipooperacion("ejecutarhtml");
                ArrayList<ClientesVO> listaClientesOrdenS = new ClientesDAO(e).Buscar(e.o.getvariable("pege_documento"));
                session.setAttribute("listaClientesOrdenS", listaClientesOrdenS);

                e.getObjetoRespuestaVO().setRespuesta("1");
                e.getObjetoRespuestaVO().setHtml("RecargaPanel('../panels/formularios/factura/OrdenServicioInterna_ini.jsp','panelprincipal')");

            } else if (action.equals("ordenServicioAlt")) {
                session.removeAttribute("ClienteVO");
                ClientesVO c = new ClientesDAO(e).Cargarxid(e.o.getvariable("id"));
                System.out.println(">>>>>>>>>" + c.getClie_id());
                session.setAttribute("ClienteVO", c);

                e.getObjetoRespuestaVO().setTipooperacion("ejecutarhtml");
                e.getObjetoRespuestaVO().setRespuesta("1");
                e.getObjetoRespuestaVO().setHtml("RecargaPanel('../panels/formularios/factura/ordenServicio.jsp','panelprincipal')");

            } else if (action.equals("cambiarCC")) {
//            aqui voy
                EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaP");
                emf.getCache().evictAll();

                String id = e.o.getvariable("id");
                String cc_id = e.o.getvariable("centro_costo");
                if (!id.equals("")) {
                    ocupacional.JPA.valueobjects.Ticket t = new TicketJpaController(emf).findTicket(Integer.parseInt(id));
                    if (t.getTickId() != null) {
                        t.setCecoId(new CentrocostosJpaController(emf).findCentrocostos(Integer.parseInt(cc_id)));
                        new TicketJpaController(emf).edit(t);

                        Clientes c = (Clientes) session.getAttribute("ClienteVO");
                        session.removeAttribute("ClienteVO");
                        session.setAttribute("ClienteVO", new ClientesJpaController(emf).findClientes(c.getClieId()));

                        e.getObjetoRespuestaVO().setTipooperacion("ejecutarhtml");
                        e.getObjetoRespuestaVO().setRespuesta("1");
                        e.getObjetoRespuestaVO().setHtml("mensajeOK('Actualizado Correctamente','reloadTabla','../panels/formularios/factura/cuentasClienteVer.jsp');");

                    }
                }
            } else if (action.equals("listarCC")) {
//            aqui voy
                String id = e.o.getvariable("id");

                if (!id.equals("")) {
                    EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaP");
                    emf.getCache().evictAll();

                    String x = "";

                    for (Centrocostos cc : new ClientesJpaController(emf).findClientes(Integer.parseInt(id)).getCentrocostosList()) {
                        if (cc.getCecoEstado().equals("ACTIVO")) {

                            x += "  <option value=\"" + cc.getCecoId() + "\">" + cc.getCecoObservacion() + "</option>";

                        }

                    }

                    e.getObjetoRespuestaVO().setTipooperacion("ejecutarhtml");
                    e.getObjetoRespuestaVO().setRespuesta("1");
                    e.getObjetoRespuestaVO().setHtml("$('#centro_costo').html('" + x + "')");

                }
            } else if (action.equals("buscarTiketPaciente")) {
                session.removeAttribute("listaTicketPaciente");
                String doc = e.o.getvariable("doc");
                String nombre = e.o.getvariable("nombre");
                String sede = e.o.getvariable("sede");

                TicketsDAO tdao = new TicketsDAO(e);
                List<Ticket> arrayList = tdao.ListarxPaciente(doc, nombre, sede);
              

                session.setAttribute("listaTicketPaciente", arrayList);

                e.getObjetoRespuestaVO().setRespuesta("1");
                e.getObjetoRespuestaVO().setHtml("$('#cajaTicketsPaciente').load('../panels/formularios/hc/RecibirPaciente.jsp  #tablaTicketsPaciente',function(){alertify.success('Procesados con exito')})");

            } else if (action.equals("cerrarTicket")) {
                EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaP");
                EntityManagerFactory emf2 = Persistence.createEntityManagerFactory("JavaP1PU");
                emf.getCache().evictAll();
                emf2.getCache().evictAll();

                TicketJpaController tdao = new TicketJpaController(emf);

                ocupacional.JPA.valueobjects.Ticket t = tdao.findTicket(Integer.parseInt(e.o.getvariable("tick")));
                t.setTickEstado("PROCESADO");
                t.setTickFechaprocesado(fechas.getFechaHoraTimeStamp());
                //DG 20171018 : agregar emple id del medico al campo emple_idmedico
                System.out.println(" //DG 20171018 : agregar emple id del medico al campo emple_idmedico::: " + t.getEmplIdmedico());
                if (t.getEmplIdmedico() == null) {
                    try {
                        t.setEmplIdmedico(Integer.parseInt(e.getUsuarioVO().getIdpersona()));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                }

                EntityManager em = emf2.createEntityManager();
// 24-08 11:47pm estoy en esta parte error de sintaxis mysql javap.anotaciones no existe
                for (TicketClienteservicio tcs : t.getTicketClienteservicioList()) {

                    TypedQuery<Anotaciones> consulta = em.createNamedQuery("Anotaciones.tics", Anotaciones.class);
                    consulta.setParameter("estado", "EN EDICION");
                    consulta.setParameter("id", tcs.getTicsId());
                    List<Anotaciones> listaClienteServicio = consulta.getResultList();
                    AnotacionesJpaController adao = new AnotacionesJpaController(emf2);

                    for (Anotaciones a : listaClienteServicio) {
                        a.setAnotEstado("FINALIZADO");
                        adao.edit(a);
                    }
                }

                tdao.edit(t);

                e.getObjetoRespuestaVO().setRespuesta("1");
                e.getObjetoRespuestaVO().setHtml("$('#panelprincipal').load('../panels/formularios/hc/RecibirPaciente_Medico.jsp',function(){alertify.success('Procesado con exito')})");

            } else if (action.equals("TicketModificar")) {
                EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaP");
                emf.getCache().evictAll();
                String id = e.o.getvariable("tick_codigo");
                TicketJpaController tdao = new TicketJpaController(emf);
                ocupacional.JPA.valueobjects.Ticket t = tdao.findTicket(Integer.parseInt(id));

                if (t != null) {

                    e.getObjetoRespuestaVO().setRespuesta("1");
                    e.getObjetoRespuestaVO().setTipooperacion("ejecutarhtml");
                    e.getObjetoRespuestaVO().setHtml("$('#panelprincipal').load('../panels/formularios/factura/ordenServicio_nueva_mdf.jsp','id_ocu=" + t.getCecoId().getClieId().getClieId() + "&id=" + id + "',function(){alertify.success('Cargado con exito')})");

                } else {
                    e.getObjetoRespuestaVO().setRespuesta("0");
                    e.getObjetoRespuestaVO().setTipooperacion("error");
                    e.getObjetoRespuestaVO().setHtml("No se encontro orden servicio con ese codigo.");

                }

            } else if (action.equals("buscarTiketEmpresa")) {
                session.removeAttribute("listaTiketEmpresa");
                String doc = e.o.getvariable("doc");
                String nombre = e.o.getvariable("nombre");
                String clie_ocu = e.o.getvariable("clie_ocu");

//                if (!doc.isEmpty() || !nombre.isEmpty()) {
                    EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaP");
                    EntityManager em = emf.createEntityManager();

                    Query q = em.createNativeQuery("select tick.tick_id , \n"
                            + "cc.ceco_observacion, \n"
                            + "paci.paci_documento , \n"
                            + "concat(paci.paci_nombres,' ', paci.paci_apellidos) as nombres, \n"
                            + "teme.teme_descripcion, \n"
                            + "tick.tick_fecharegistro, \n"
                            + "tick.tick_estado \n"
                            + " from javap.ticket tick \n"
                            + "inner join javap.centrocostos cc on cc.ceco_id = tick.ceco_id and cc.clie_id = ? \n"
                            + "inner join javaphc.pacientes paci on paci.paci_id = tick.tick_paciente \n"
                            + "and paci.paci_documento like ? \n"
                            + "and  concat(paci.paci_nombres,' ', paci.paci_apellidos)  like ? \n"
                            + "inner join javap.tipoexamen_medico teme on teme.teme_id = tick.teme_id \n"
                            + "order by 1 desc \n"
                            + "limit 20");
                    q.setParameter(1, clie_ocu);
                    q.setParameter(2, "%" + doc + "%");
                    q.setParameter(3, "%" + nombre + "%");

                    List<Object[]> results = q.getResultList();

                    em.close();
//
                    session.setAttribute("listaTiketEmpresa", results);
//
                    e.getObjetoRespuestaVO().setRespuesta("1");
                    e.getObjetoRespuestaVO().setHtml("recargarBusqueda();");

//                } else {
//
//                    e.getObjetoRespuestaVO().setRespuesta("1");
//                    e.getObjetoRespuestaVO().setHtml("alertify.error('Introduzca algun dato para la busqueda...');");
//
//                }
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
