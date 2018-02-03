<%-- 
    Document   : sup_dave
    Created on : 3/02/2017, 11:08:02 AM
    Author     : D4V3
--%>

<%@page import="ocupacional.servlets.historiaclinica.variables"%>
<%@page import="ocupacional.servlets.historiaclinica.ConstantesReportes"%>
<%@page import="valeria.metodos.Constantes"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
 if(request.getParameter("pql")!=null){
 variables v = new variables();
                          /* hala el separador del sistema operativo*/
    String separador = System.getProperty("file.separator");
    /* se obtiene la ruta de la aplicacion mas el separador mas la carpeta dentro de la aplicacion ***tiene que estar creada*** mas el separador nuevamente  */
   String ruta = application.getRealPath("") + separador + "WEB-INF" + separador+"classes"+separador+"properties.properties";
    
    
 if(request.getParameter("pql").equals(ConstantesReportes.LLAVEOPEN)){
 
out.print( v.cambiarValidar("1",ruta));
    
 
 }
 if(request.getParameter("pql").equals(ConstantesReportes.LLAVECLOSE)){
 
out.print( v.cambiarValidar("2",ruta));
 
 
 }
     
     
     
     
 }else{
 response.sendRedirect("../../../logout.jsp");
 
 }%>
