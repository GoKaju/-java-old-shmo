<%-- 
        DESAROLLADOR : Alejandro 
        NOMBRE DEL DOCUMENTO   : logout
        FECHA DE CREACION : 30-oct-2015, 6:42:25  
        HISTORIA DE USUARIO :
        DESCRIPCION FUNCIONAL DEL DOCUMENTO : 
--%>

<%
//    response.setHeader("Pragma", "no-cache");
//response.setHeader("Cache-Control", "no-cache");
//response.setDateHeader("Expires", 0); 

    System.out.println("session quemada"+ session.getId());
    session.removeAttribute("Mediador");
    session.invalidate();
    response.sendRedirect("/JavaP1");
%>