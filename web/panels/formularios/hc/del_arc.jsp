<%-- 
    Document   : del_arc
    Created on : 12/08/2016, 07:00:59 PM
    Author     : D4V3
--%>

<%@page import="valeria.response.RespuestaXML"%>
<%@page import="valeria.metodos.Cadenas"%>
<%@page import="valeria.response.Mediador"%>
<%@page import="formularios.entidades.Documentos"%>
<%@page import="formularios.controlers.DocumentosJpaController"%>
<%@page import="javax.persistence.Persistence"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.io.File"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%

    Mediador e = (Mediador) session.getAttribute("Mediador");
    if (e != null) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaP1PU");
        DocumentosJpaController ddao = new DocumentosJpaController(emf);
        Documentos d = ddao.findDocumentos(Integer.parseInt(request.getParameter("docu_id")));

        File file;
        /* hala el separador del sistema operativo*/
        String separador = System.getProperty("file.separator");
        /* se obtiene la ruta de la aplicacion mas el separador mas la carpeta dentro de la aplicacion ***tiene que estar creada*** mas el separador nuevamente  */
        String ruta = application.getRealPath("") + separador + "Adjuntos" + separador;

        try {
            file = new File(ruta + d.getDocuRuta());
            if (file.delete()) {

                ddao.destroy(d.getDocuId());
                response.getWriter().write("1");

            }
        } catch (Exception ex) {
            ex.printStackTrace();

        }

    } else {%>
<script type="text/javascript">
    location.href='../logout.jsp';
</script>
<%}%>
