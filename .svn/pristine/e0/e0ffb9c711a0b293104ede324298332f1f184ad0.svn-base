<%-- 
    Document   : ordenes_servicio_x_empresa
    Created on : 2/02/2017, 07:40:18 PM
    Author     : Sebas
--%>

<%@page import="java.util.AbstractList"%>
<%@page import="java.util.Iterator"%>
<%@page import="ocupacional.JPA.valueobjects.ClientesServicio"%>
<%@page import="valeria.metodos.Cadenas"%>
<%@page import="ocupacional.valueobjects.facturacion.FechasCuentasCliente"%>
<%@page import="ocupacional.JPA.controlers.EstadocivilJpaController"%>
<%@page import="valeria.metodos.Constantes"%>
<%@page import="java.util.ArrayList"%>
<%@page import="ocupacional.JPA.valueobjects.ServiciosExamenes"%>
<%@page import="formularios.controlers.CamposJpaController"%>
<%@page import="formularios.entidades.Respuestas"%>
<%@page import="ocupacional.servlets.historiaclinica.ConstantesReportes"%>
<%@page import="ocupacional.JPA.valueobjects.TicketClienteservicio"%>
<%@page import="ocupacional.valueobjects.facturacion.TicketClienteServicio"%>
<%@page import="javax.persistence.TypedQuery"%>
<%@page import="formularios.entidades.Anotaciones"%>
<%@page import="formularios.entidades.Pacientes"%>
<%@page import="formularios.controlers.PacientesJpaController"%>
<%@page import="valeria.metodos.ManejadorFechas"%>
<%@page import="ocupacional.JPA.valueobjects.Ticket"%>
<%@page import="java.util.List"%>
<%@page import="javax.persistence.EntityManager"%>
<%@page import="javax.persistence.Query"%>
<%@page import="javax.persistence.Persistence"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<%@page import="ocupacional.JPA.controlers.ClientesJpaController"%>
<%@page import="ocupacional.JPA.valueobjects.Clientes"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%!
    class conteo {

        ClientesServicio cs;
        int cont = 0;

    }


%>
<%
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaP");
    ClientesJpaController clientesJpaController = new ClientesJpaController(emf);
    ManejadorFechas manejadorFechas = new ManejadorFechas();
    Cadenas o = new Cadenas();
    String fini = request.getParameter("fini");
    String ffin = request.getParameter("ffin");
    String clie = request.getParameter("clie");
    String estado = request.getParameter("estado");
    String tipoReporte = request.getParameter("tipo");

    String queryname = "";
    if (estado.equals("POR PROCESAR") || estado.equals("PROCESANDO") || estado.equals("ANULADO")) {
        queryname = "Ticket.reporteExcelEstadoCreacion";
    } else {
        queryname = "Ticket.reporteExcelEstadoCierre";
    }

    Clientes clientes = clientesJpaController.findClientes(Integer.parseInt(clie));
    EntityManager em = emf.createEntityManager();
    Query q = em.createNamedQuery(queryname);
    q.setParameter("cliente", clientes);
    q.setParameter("estado", estado);
    q.setParameter("fechaInicio", manejadorFechas.StringToTimeStamp(fini));
    q.setParameter("fechaFin", manejadorFechas.StringToTimeStampHora(ffin));
//    q.setParameter("fechaInicio", manejadorFechas.StringToTimeStamp("2016-11-01"));
//    q.setParameter("fechaFin", manejadorFechas.StringToTimeStamp("2017-11-01"));
    List<Ticket> tickets = q.getResultList();
//    
    String filename = clientes.getClieNombre() + fini + ffin;
    response.setContentType("application/vnd.ms-excel");
    response.setHeader("Content-Disposition", "inline; filename=" + filename + ".xls");
    List<conteo> examenesConteo = new ArrayList();


%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

        <br /><br />
        <table  border="1" >
            <thead>
                <tr>
                    <th style="text-align: center" colspan="<%=tipoReporte != null && tipoReporte.equals("1") ? 14 : 16%>">

                        REPORTE DE ORDENES EN ESTADO <%=estado%> PARA  <%=clientes.getClieNombre()%> ENTRE <%=fini%> y <%=ffin%>

                    </th>
                </tr>
                <tr>
                    <th>CODIGO</th>
                    <th>ESTADO</th>
                    <th>FECHA</th>
                    <th>NOMBRE</th>
                    <th>CEDULA</th>
                    <th>RAZON SOCIAL</th>

                    <%                        if (tipoReporte != null && tipoReporte.equals("1")) {
                    %>

                    <%
                    } else {
                    %>
                    <th>EDAD</th>
                    <th>E. CIVIL</th>

                    <%
                        }

                    %>

                    <th>SEDE/FINCA</th>

                    <%                        if (tipoReporte != null && tipoReporte.equals("1")) {
                    %>

                    <th>CENTRO DE COSTO</th>

                    <%
                    } else {
                    %>

                    <th>CARGO</th>

                    <%
                        }

                    %>

                    <th>TIPO EXAMEN</th>

                    <%                        if (tipoReporte != null && tipoReporte.equals("1")) {
                    %>

                    <th>EXAMEN</th>
                    <th>COSTO EXAMEN</th>

                    <%
                    } else {
                    %>

                    <!--<th>CARGO</th>-->
                    <th>PARACLINICOS</th>
                    <th>CONDICIONADOS</th>

                    <%
                        }

                    %>

                    <th>RESTRICCIONES</th>
                    <th>RECOMENDACIONES</th>
                    <th>DIAGNOSTICO</th>
                    <th>LUGAR</th>
                </tr>

            </thead>
            <tbody>


                <%            System.out.println(clientes.getClieId());
                    EntityManagerFactory emf2 = Persistence.createEntityManagerFactory("JavaP1PU");
                    EstadocivilJpaController ecDAO = new EstadocivilJpaController(emf);
                    System.out.println(tickets.size());

                    for (Ticket t : tickets) {
                        String diagnostico = "";
                        String restricciones2 = "";
                        String recomendaciones = "";

                        Pacientes p = new PacientesJpaController(emf2).findPacientes(t.getTickPaciente());

                        //obtener cm ocupacional 1
                        Anotaciones anotcmo1 = new Anotaciones();
                        Anotaciones anotcmo2 = new Anotaciones();

                        EntityManager em2 = emf2.createEntityManager();

                        List<ClientesServicio> examenesFinales = new ArrayList();
                        List<String> examenesFinalesCostos = new ArrayList();
                        for (TicketClienteservicio elem : t.getTicketClienteservicioList()) {

                            List<ServiciosExamenes> exameneses = elem.getClseId().getServId().getServiciosExamenesList();

                            examenesFinales.add(elem.getClseId());
                            examenesFinalesCostos.add(o.getNumber(elem.getClseId().getClseValor()));
//                    generar conteo
                            boolean nuevo = true;
                            for (conteo cs : examenesConteo) {
                                if (cs.cs.getClseId().equals(elem.getClseId().getClseId())) {
                                    nuevo = false;
                                    cs.cont++;
                                }
                            }
                            if (nuevo) {
                                conteo cs = new conteo();
                                cs.cs = elem.getClseId();
                                cs.cont = 1;
                                examenesConteo.add(cs);
                            }

//                            String diagnostico2="";
                            TypedQuery<Anotaciones> consultaxtcls = em2.createNamedQuery("Anotaciones.t", Anotaciones.class);
                            consultaxtcls.setParameter("id", elem.getTicsId());
                            List<Anotaciones> lista = consultaxtcls.getResultList();
                            if (!lista.isEmpty()) {
                                for (Anotaciones a : lista) {
                                    if (a.getFormId().getFormId() == ConstantesReportes.formCMO1) {
                                        anotcmo1 = a;
                                    } else if (a.getFormId().getFormId() == ConstantesReportes.formCMO2) {

                                        anotcmo2 = a;

                                        for (Respuestas r : a.getRespuestasList()) {

                                            if (r.getCampId().getCampId().equals(ConstantesReportes.recomendaciones)) {
                                                recomendaciones = r.getRespDescripcion();
                                            }
                                            if (r.getCampId().getCampId().equals(ConstantesReportes.restricciones)) {
                                                restricciones2 = r.getRespDescripcion();
                                            }
                                            if (r.getCampId().getCampId().equals(ConstantesReportes.Diagnostico)) {
                                                diagnostico = r.getRespDescripcion();
                                            }

                                        }

                                    }

                                }
                            }

                        }

//                obtener cargo
                        String cargo = "";

                        TypedQuery<Respuestas> consultacargo = em2.createNamedQuery("Respuestas.findBycampAndAnot", Respuestas.class);
                        consultacargo.setParameter("camp", new CamposJpaController(emf2).findCampos(ConstantesReportes.cargoCMO1));
                        consultacargo.setParameter("anot", anotcmo1);
                        List<Respuestas> listacargo = consultacargo.getResultList();
                        if (!listacargo.isEmpty()) {
                            System.out.println("anot1 ::: " + anotcmo1.getAnotId());
                            System.out.println("lista::: " + listacargo.size());
                            for (Respuestas a : listacargo) {
                                System.out.println("cargo:: " + a.getRespDescripcion());
                                System.out.println("cargo:: " + a.getRespId());
                                cargo = a.getRespDescripcion();

                            }
                        }


                %>

                <tr>
                    <td><%= t.getTickId()%></td>
                    <td><%= t.getTickEstado()%></td>
                    <td><%= manejadorFechas.DevuelveFormatoSlash(t.getTickFechaprocesado())%></td>
                    <td><%= p.getPaciNombres() + " " + p.getPaciApellidos()%></td>
                    <td><%= p.getPaciDocumento()%></td>
                    <td><%= t.getCecoId().getClieId().getClieNombre()%></td>

                    <%                        if (tipoReporte != null && tipoReporte.equals("1")) {
                    %>

                    <%
                    } else {
                    %>

                    <td><%=manejadorFechas.getDiffDateLite(p.getPaciFechanacimiento(), manejadorFechas.getFechaHoraTimeStamp())%></td>
                    <td><%= ecDAO.findEstadocivil(Integer.parseInt(p.getPaciEcivil())).getEsciDescripcion()%></td>

                    <%
                        }

                    %>

                    <td><%= t.getCecoId().getCecoObservacion()%></td>

                    <%if (tipoReporte != null && tipoReporte.equals("1")) {
                    %>

                    <td><%= t.getCecoId().getCecoObservacion()%></td>

                    <%
                    } else {
                    %>

                    <td><%= cargo%></td>
                    <%
                        }

                    %>

                    <td><%= t.getTemeId().getTemeId().equals(7) ? t.getTickOtroexamen() : t.getTemeId().getTemeDescripcion()%></td>
                    <td><% for (ClientesServicio ses : examenesFinales) {
                            out.println(ses.getServId().getServNombre() + "<br style='mso-data-placement:same-cell;' />");
                        }
                        %>
                    </td>

                    <%                        if (tipoReporte != null && tipoReporte.equals("1")) {
                    %>

                    <td><% for (String ses : examenesFinalesCostos) {
                            out.println("$ " + ses + "<br style='mso-data-placement:same-cell;' />");
                        }
                        %></td>

                    <%
                    } else {
                    %>

                    <td><%  if (restricciones2 == null || restricciones2.equals("")) {

                            out.println("No");
                        } else {
                         if (restricciones2.trim().equalsIgnoreCase("NO") || restricciones2.trim().equalsIgnoreCase("ninguna")||restricciones2.trim().equalsIgnoreCase("ninguno")) {

                                out.println("No");
                            } else {
                                out.println("Si");
                            }

                        }%>
                    </td>

                    <%
                        }

                    %>

                    <td><%= restricciones2.equals("") ? "Ninguna" : restricciones2%></td>
                    <td><%= recomendaciones%></td>
                    <td><%= diagnostico%></td>
                    <td><%= t.getSedeId().getSedeNombre()%></td>
                </tr>
                <%                     }

                %>
                <tr></tr>
            </tbody>
        </table>

        <table border="1">
            <thead><tr>
                    <th>DESCRIPCION</th>
                    <th>CANTIDAD</th>
                    <th>VALOR U.</th>
                    <th>TOTAL</th></tr>
            </thead>
            <tbody>
                <%for (conteo cs : examenesConteo) {%> 
                <tr>
                    <td><%=cs.cs.getServId().getServNombre()%></td>
                    <td><%=cs.cont%></td>
                    <td>$ <%= o.getNumber(cs.cs.getClseValor())%></td>
                    <td>$ <%= o.getNumber(cs.cs.getClseValor() * cs.cont)%></td>
                </tr>
                <%}%>
            </tbody>
        </table>   
    </body>
</html>
