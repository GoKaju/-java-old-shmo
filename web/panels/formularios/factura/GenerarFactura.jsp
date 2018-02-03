<%-- 
    Document   : GenerarFactura
    Created on : 16/06/2016, 12:42:23 AM
    Author     : D4V3
--%>

<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="ocupacional.utils.GenerarReporte"%>
<%

    
    
    GenerarReporte rep = new GenerarReporte(request, response, "reportes/report1.jasper", new HashMap());
    rep.devuelveReporte();


%>