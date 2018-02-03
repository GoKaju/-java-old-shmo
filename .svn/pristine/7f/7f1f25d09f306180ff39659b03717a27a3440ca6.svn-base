<%-- 
    Document   : cuadro_de_ordenes
    Created on : 16/03/2017, 01:25:14 PM
    Author     : D4V3
--%>
<%@page import="javax.persistence.TypedQuery"%>
<%@page import="javax.persistence.EntityManager"%>
<%@page import="ocupacional.valueobjects.facturacion.FechasCuentasCliente"%>
<%@page import="ocupacional.JPA.valueobjects.Centrocostos"%>
<%@page import="ocupacional.JPA.valueobjects.TicketClienteservicio"%>
<%@page import="ocupacional.JPA.controlers.ClientesJpaController"%>
<%@page import="ocupacional.JPA.valueobjects.Clientes"%>
<%@page import="ocupacional.JPA.controlers.TicketJpaController"%>
<%@page import="ocupacional.JPA.valueobjects.Ticket"%>
<%@page import="javax.persistence.Persistence"%>
<%@page import="javax.persistence.EntityManagerFactory"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="valeria.metodos.ManejadorFechas"%>
<%@page import="valeria.metodos.ProcesaCaracEspeciales"%>

<%@page import="java.util.List"%>
<%@page import="ocupacional.bdatos.RolFuncionalidadDAO"%>
<%@page import="ocupacional.valueobjects.RolFuncionalidadVOs"%>
<%@page import="valeria.metodos.Cadenas"%>

<%@page import="java.util.ArrayList"%>
<%@page import="valeria.response.Mediador"%>
<%

    Mediador e = (Mediador) session.getAttribute("Mediador");
    Cadenas pc = new Cadenas();
    ManejadorFechas manejadorFechas = new ManejadorFechas();

    if (e != null) {

        String fini = request.getParameter("fini");
        String ffin = request.getParameter("ffin") + " 23:59:59";
        String estado = request.getParameter("estado");
%>
<div class="alert alert-info fade in">
    <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
    <% if (estado.equals("POR PROCESAR") || estado.equals("PROCESANDO") || estado.equals("ANULADO")) { %>
    <strong>Nota!</strong> Para realizar la consulta se tuvo como referencia la fecha de creación de la orden.
    <%} else {%>
    <strong>Nota!</strong> Para realizar la consulta se tuvo como referencia la fecha de cierre de la orden.

    <%}%>

</div>
<table id="tabla" class="table table-hover ">
    <thead>
        <tr>
            <th>#</th>

            <th>No. DOCUMENTO</th>
            <th>NOMBRE</th>
            <th>TELEFONO</th>

            <th># TICKETS</th>
            <th>ACCIONES</th>
        </tr>
    </thead>
    <tbody>
        <%                            EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaP");

            ClientesJpaController cdao = new ClientesJpaController(emf);
            int cont = 1;
            for (Clientes c : cdao.findClientesEntities()) {
                if (c.getClieEstado().equals("ACTIVO")) {

//        tiped query
                    String queryname = "";
                    if (estado.equals("POR PROCESAR") || estado.equals("PROCESANDO") || estado.equals("ANULADO")) {
                        queryname = "Ticket.findByFechasEstadoCreacion";
                    } else {
                        queryname = "Ticket.findByFechasEstadoCierre";
                    }

                    EntityManager em = emf.createEntityManager();
                    TypedQuery<Ticket> contick = em.createNamedQuery(queryname, Ticket.class);
                    contick.setParameter("clieId", c.getClieId());
                    //contick.setParameter("estado","PROCESADO");
//                    System.out.println("estado :::: "+ request.getParameter("estado"));
                    contick.setParameter("estado", estado);
                    contick.setParameter("inicio", manejadorFechas.StringToTimeStamp(fini));
                    contick.setParameter("fin", manejadorFechas.StringToTimeStampHora(ffin));

                    List<Ticket> lista = contick.getResultList();

                    if (lista != null && lista.size() > 0) {
        %>
        <tr>
            <td><%=cont++%></td>

            <td><%=c.getClieDocumento()%></td>
            <td><%=c.getClieNombre()%></td>
            <td><%=c.getClieTelefonos()%></td>

            <td style="font-weight: bold"><%=lista.size()%></td>
            <td>
                <% if (estado.equals("POR PROCESAR") || estado.equals("PROCESANDO") || estado.equals("ANULADO")) {%>
<%  for(Ticket t:lista){
out.print(t.getTickId()+", ");
}%>
                <%}else{%>
                <button type="button" value="<%=c.getClieId()%>" title="Generar reporte" class="btn-circle btn-success bottom-right " onclick="
                        window.open('reportes/reportExcel/ordenes_servicio_x_empresa_estado?clie=<%=c.getClieId()%>&fini=<%=fini%>&ffin=<%=ffin%>&estado=<%=estado%>');
                        "><i class="glyphicon glyphicon-print"></i> </button>
                <%}%>

        </tr>
        <%
                    }
                }
            }
        %>
    </tbody>






</table>






<script>


</script>
<%} else {%>
<script type="text/javascript">
    location.href = '../logout.jsp';
</script>
<%}%>