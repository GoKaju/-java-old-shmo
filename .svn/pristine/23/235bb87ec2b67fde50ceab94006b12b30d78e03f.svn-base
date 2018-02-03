<%-- 
    Document   : Usuarios_nuevo_tablaBusqueda
    Created on : 19/08/2016, 10:03:49 PM
    Author     : D4V3
--%>

<%@page import="ocupacional.JPA.controlers.PersonageneralJpaController"%>
<%@page import="ocupacional.JPA.valueobjects.Personageneral"%>
<%@page import="ocupacional.JPA.controlers.JuridicasJpaController"%>
<%@page import="ocupacional.JPA.valueobjects.Juridicas"%>
<%@page import="ocupacional.JPA.valueobjects.Proveedores"%>
<%@page import="ocupacional.JPA.valueobjects.Clientes"%>
<%@page import="java.util.List"%>
<%@page import="javax.persistence.TypedQuery"%>
<%@page import="javax.persistence.EntityManager"%>
<%@page import="ocupacional.JPA.valueobjects.Empleados"%>
<%@page import="javax.persistence.Persistence"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<%@page import="valeria.metodos.ManejadorFechas"%>
<%@page import="valeria.metodos.Cadenas"%>
<%@page import="valeria.response.Mediador"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>



<%
    
      Mediador e = (Mediador) session.getAttribute("Mediador");
    if (e != null) {
        Cadenas o = new Cadenas();
        ManejadorFechas f = new ManejadorFechas();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaP");
String tipo = request.getParameter("tipo");
String docu = request.getParameter("docu");
String nombre = request.getParameter("nom");
%>
<table class="table table-hover">
    <thead>
        <tr>
            <td>Nombre</td>
            <td>Documento</td>
            <td></td>
        </tr>
    </thead>
    <tbody>

<%
EntityManager em = emf.createEntityManager();
if(tipo.equals("E")){
                    TypedQuery<Empleados> consultaDocumento = em.createNamedQuery("Empleados.busqueda", Empleados.class);
                    consultaDocumento.setParameter("emplDocumento","%"+o.notEmpty(docu)+"%");
                    consultaDocumento.setParameter("emplNombres","%"+o.notEmpty(nombre)+"%");
                    List<Empleados> lista = consultaDocumento.getResultList();
    for(Empleados emple:lista){
    
    
%>

        <tr>
            <td><%=o.notEmpty(emple.getEmplNombres())%></td>
            <td><%=o.notEmpty(emple.getEmplDocumento())%></td>
            <td>
                                    <button  onclick="seleccionar('<%=o.notEmpty(emple.getEmplNombres())%>','<%=o.notEmpty(emple.getEmplDocumento())%>','EMPLEADO','<%=o.notEmpty(tipo)%>','<%=emple.getEmplId()%>');"  style="margin-top: 25px" class="btn btn-success btn-circle" title="Seleccionar" ><span class="glyphicon glyphicon-user"></span></button>

                
            </td>
        </tr>

<%}}else if(tipo.equals("C")){
      TypedQuery<Clientes> consultaDocumento = em.createNamedQuery("Clientes.busqueda", Clientes.class);
                    consultaDocumento.setParameter("Documento","%"+o.notEmpty(docu)+"%");
                    consultaDocumento.setParameter("Nombres","%"+o.notEmpty(nombre)+"%");
                    List<Clientes> lista = consultaDocumento.getResultList();
    for(Clientes emple:lista){
    
%>


        <tr>
            <td><%=o.notEmpty(emple.getClieNombre())%></td>
            <td><%=o.notEmpty(emple.getClieDocumento())%></td>
            <td>
                    <button  onclick="seleccionar('<%=o.notEmpty(emple.getClieNombre())%>','<%=o.notEmpty(emple.getClieDocumento())%>','CLIENTE','<%=o.notEmpty(tipo)%>','<%=emple.getClieId()%>');"  style="margin-top: 25px" class="btn btn-success btn-circle" title="Seleccionar" ><span class="glyphicon glyphicon-user"></span></button>
                    
                
            </td>
  

<%}}else if(tipo.equals("L")){
    
      TypedQuery<Proveedores> consultaDocumento = em.createNamedQuery("Proveedores.busqueda", Proveedores.class);
                    consultaDocumento.setParameter("Documento","%"+o.notEmpty(docu)+"%");
                    consultaDocumento.setParameter("Nombres","%"+o.notEmpty(nombre)+"%");
                    List<Proveedores> lista = consultaDocumento.getResultList();
    for(Proveedores emple:lista){
        Personageneral pege = new PersonageneralJpaController(emf).findPersonageneral(emple.getPegeId());
    Juridicas juri = new  JuridicasJpaController(emf).findJuridicas(emple.getPegeId());
%>


        <tr>
            <td><%=o.notEmpty(juri.getJuriRazonsocial()) %></td>
            <td><%=o.notEmpty(pege.getPegeDocumento()) %></td>
            <td>
                    <button  onclick="seleccionar('<%=o.notEmpty(juri.getJuriRazonsocial())%>','<%=o.notEmpty(pege.getPegeDocumento())%>','CLIENTE','<%=o.notEmpty(tipo)%>','<%=emple.getProvId()%>');"  style="margin-top: 25px" class="btn btn-success btn-circle" title="Seleccionar" ><span class="glyphicon glyphicon-user"></span></button>
                    
                
            </td>
  

<%}}
    System.out.println("entre >>><<<>ss "+tipo);
%>




        </tr>
    </tbody>
    
</table>

<% }else{%>
<script type="text/javascript">
    location.href='../logout.jsp';
</script>
<% }%>