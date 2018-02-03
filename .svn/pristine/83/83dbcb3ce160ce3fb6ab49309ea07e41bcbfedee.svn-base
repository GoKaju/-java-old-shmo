<%-- 
    Document   : cuadro_de_ordenes
    Created on : 16/03/2017, 01:25:14 PM
    Author     : D4V3
--%>
<%@page import="javax.persistence.Query"%>
<%@page import="javax.persistence.TypedQuery"%>
<%@page import="javax.persistence.EntityManager"%>
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
        String ffin = request.getParameter("ffin") ;
        String tipo = request.getParameter("tipo");
        String sede = request.getParameter("sede");
        String doc = request.getParameter("doc");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaP");
        EntityManager em = emf.createEntityManager();

        try {
%>



<table id="tablaRpt" class="table table-hover ">
    <thead>
        <tr>
            
            <th>FECHA</th>
            <th>SEDE</th>
            <th>DOCUMENTO</th>
            <th>NOMBRE</th>
            <th>CANT. TICKETS</th>
            <th>TIPO</th>
        </tr>
    </thead>
    <tbody>
        <%
           
            Query q = em.createNativeQuery("SELECT *  FROM `view_rpt_ticket_empleado`"
                    + " WHERE `fecha_procesado` BETWEEN ? AND ? "
                    + "AND `sede_nombre` LIKE ? "
                    + "AND `empl_documento` LIKE ? "
                    + "AND `tipo` LIKE ? "
                    + "ORDER BY `fecha_procesado` DESC");
            q.setParameter(1,fini);
            q.setParameter(2,ffin);
            q.setParameter(3,"%"+sede+"%");
            q.setParameter(4,"%"+doc+"%");
            q.setParameter(5,"%"+tipo+"%");

            
            List<Object[]> results = q.getResultList();

            for (Object[] a : results) {
        %>
        <tr>
            <td><%=a[0]%></td>
            <td><%=a[1]%></td>
            <td><%=a[2]%></td>
            <td><%=a[3]%></td>
            <td><%=a[4]%></td>
            <td><%=a[5]%></td>

        </tr>
        <%
            }

        %>
    </tbody>






</table>
 
 

<%    } catch (Exception ex) {
        ex.printStackTrace();
    } finally {
        em.close();
    }
} else {%>
<script type="text/javascript">
    location.href = '../logout.jsp';
</script>
<%}%>