<%-- 
    Document   : actualizarFotosEdadAnotaciones
    Created on : 3/05/2017, 04:05:02 PM
    Author     : DJGOMEZ
--%>

<%@page import="valeria.metodos.ManejadorFechas"%>
<%@page import="formularios.entidades.Anotaciones"%>
<%@page import="javax.persistence.Persistence"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<%@page import="formularios.controlers.AnotacionesJpaController"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>



<%
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaP1PU");
   AnotacionesJpaController anotdao = new AnotacionesJpaController(emf);
    ManejadorFechas f = new ManejadorFechas();
    int i = 0;      
    
for(Anotaciones a : anotdao.findAnotacionesEntities()){
   if(a.getFormId().getFormId()==12){
    try{ 
        i++;
    a.setAnotFoto(a.getPaciId().getPaciFoto());
    a.setAnotEdad(f.getDiffDates(a.getPaciId().getPaciFechanacimiento(), f.getFechaHora()));
   anotdao.edit(a);
    }catch(Exception e){
        System.out.println("Anot::: "+ a.getAnotId());
        e.printStackTrace();
    
    }}

}
out.print(i);
%>


