<%-- 
    Document   : mostrarImg
    Created on : 19/06/2016, 04:59:24 PM
    Author     : D4V3
--%>

<%@page import="java.io.File"%>
<%@page import="formularios.entidades.Ayudas"%>
<%@page import="formularios.controlers.AyudasJpaController"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<%@page import="javax.persistence.Persistence"%>
<%@page import="javax.persistence.EntityManager"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.InputStream"%>
<%
 EntityManagerFactory emf =
 Persistence.createEntityManagerFactory("JavaP1PU");
//InputStream imagen = null;
    byte[] buffer = null;
int id = Integer.parseInt(request.getParameter("id"));
AyudasJpaController ac = new AyudasJpaController(emf);
Ayudas a  = ac.findAyudas(id);
if (a.getAyudImg()!=null) {
    System.out.println("lA IMAGEN SI TIENE BITES");
buffer = a.getAyudImg(); //Obtenemos el binario del campo Blob
} 

ServletOutputStream bOut = response.getOutputStream();
//Aqui es la salida del servlet
//for (;;) {
//int nBytes = imagen.read(buffer);
//if (nBytes == -1) {
//break;
//}
bOut.write(buffer);
//}
//imagen.close();
bOut.flush();
bOut.close();
%>
