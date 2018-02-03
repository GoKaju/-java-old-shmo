<%-- 
        DESAROLLADOR : Alejandro 
        NOMBRE DEL DOCUMENTO   : top_menu
        FECHA DE CREACION : 05-nov-2015, 22:17:25  
        HISTORIA DE USUARIO :
        DESCRIPCION FUNCIONAL DEL DOCUMENTO : 
--%>

<%@page import="ocupacional.JPA.valueobjects.Juridicas"%>
<%@page import="ocupacional.JPA.controlers.JuridicasJpaController"%>
<%@page import="ocupacional.JPA.controlers.PersonageneralJpaController"%>
<%@page import="ocupacional.JPA.valueobjects.Personageneral"%>
<%@page import="ocupacional.JPA.controlers.ProveedoresJpaController"%>
<%@page import="ocupacional.JPA.valueobjects.Proveedores"%>
<%@page import="ocupacional.JPA.controlers.ClientesJpaController"%>
<%@page import="ocupacional.JPA.valueobjects.Clientes"%>
<%@page import="valeria.metodos.Cadenas"%>
<%@page import="valeria.metodos.ProcesaCaracEspeciales"%>
<%@page import="formularios.controlers.EmpleadosJpaController"%>
<%@page import="ocupacional.JPA.valueobjects.Empleados"%>
<%@page import="ocupacional.JPA.valueobjects.Usuariorol"%>
<%@page import="javax.persistence.Persistence"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<%@page import="ocupacional.JPA.controlers.UsuariosJpaController"%>
<%@page import="ocupacional.JPA.valueobjects.Usuarios"%>
<%@page import="java.util.ArrayList"%>
<%@page import="ocupacional.valueobjects.UsuarioRolVO"%>
<%@page import="valeria.response.Mediador"%>
<style type="text/css">
    
    @media(max-width: 768px){
        .dropdown{
            float: right;
        }
        
    }
</style>
<%
     int ind = 0;
                Mediador e = (Mediador) session.getAttribute("Mediador");
                if (e != null) {
                    
                    if (e.getUsuarioVO() != null) {
                        Cadenas pc = new Cadenas();
                        EntityManagerFactory emf12 = Persistence.createEntityManagerFactory("JavaP");
                        Usuarios u = new  UsuariosJpaController(emf12).findUsuarios(Integer.parseInt(e.getUsuarioVO().getIdUsuario()));
                        
%>
<ul class="nav navbar-top-links navbar-right">
    <li>
        
        <span class=" text-uppercase text-primary " >
            <strong>
                
             <% if (e.getUsuarioVO().getUsua_tipo().equals("E")) {
                    Empleados clie = new EmpleadosJpaController(emf12).findEmpleados(Integer.parseInt(e.getUsuarioVO().getIdpersona()));
            %>
            
               <%=pc.notEmpty(clie.getEmplNombres())%>
               - <%=pc.notEmpty(clie.getEmplCargo())%>

        
       
            <%} else if (e.getUsuarioVO().getUsua_tipo().equals("C")) {
                Clientes clie = new ClientesJpaController(emf12).findClientes(Integer.parseInt(e.getUsuarioVO().getIdpersona()));
                if (clie != null) {
            %>
         
                <%=pc.notEmpty(clie.getClieNombre())%>
            <% }
            } else if (e.getUsuarioVO().getUsua_tipo().equals("L")) {
                Proveedores clie = new ProveedoresJpaController(emf12).findProveedores(Long.parseLong(e.getUsuarioVO().getIdpersona()));
                if (clie != null) {
                    Personageneral pege = new PersonageneralJpaController(emf12).findPersonageneral(clie.getPegeId());
                    Juridicas juri = new JuridicasJpaController(emf12).findJuridicas(clie.getPegeId());
            %>
          <%=pc.notEmpty(juri.getJuriRazonsocial())%>
            <% }
           }%>
            
            </strong>
        </span>
    </li>
    <li class="dropdown" >
        <a class="dropdown-toggle" data-toggle="dropdown" href="#">
            <i class="fa fa-user fa-fw"></i>  <i class="fa fa-caret-down"></i>
        </a>
        <ul class="dropdown-menu dropdown-user">

            <%
           
                        if (u.getUsuariorolList()!= null) {
                            for (Usuariorol UsuarioRolVO : u.getUsuariorolList()) {
                                if(UsuarioRolVO.getRoleId().getRoleEstado().equalsIgnoreCase("ACTIVO")){
            %>
            <li><a href="javascript:void(0)" onclick="RecargaPanel('../panels/arbol.jsp?ind=<%=UsuarioRolVO.getRoleId().getRoleId()%>', 'menu-left');"><i class="fa fa-user fa-fw"></i> <%=e.o.notEmpty(UsuarioRolVO.getRoleId().getRoleDescripcion())%></a></li>
                <%
                                    ind++;
                                }
                        }}
                        }
                    }
                %>



            <li><a href="#" onclick="RecargaPanel('../panels/formularios/basicas/usuarios_datos.jsp','panelprincipal')" ><i class="fa fa-user fa-fw"></i> Datos de Usuario</a></li>

            <li class="divider"></li>
            <li><a href="#" onclick="
                   alertify.confirm('¿Está seguro de cerrar sesión?',function(e){
                       if(e){
                           
                           location.href='../logout.jsp';
                           
                       }
                       
                       
                   })
                   "><i class="fa fa-sign-out fa-fw"></i> Terminar Session</a>
            </li>
        </ul>
        <!-- /.dropdown-user -->
    </li>





    <!-- /.dropdown -->
</ul>
<!-- /.navbar-top-links -->
