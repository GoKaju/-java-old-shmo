<%-- 
        DESAROLLADOR : VALERIA
        NOMBRE DEL DOCUMENTO   : arbol
        FECHA DE CREACION : 28/11/2015, 02:46:37 PM
        HISTORIA DE USUARIO :
        DESCRIPCION FUNCIONAL DEL DOCUMENTO : 
--%>

<%@page import="ocupacional.JPA.controlers.RolesJpaController"%>
<%@page import="ocupacional.JPA.valueobjects.Roles"%>
<%@page import="ocupacional.JPA.controlers.UsuariorolJpaController"%>
<%@page import="ocupacional.JPA.valueobjects.Usuariorol"%>
<%@page import="javax.persistence.Persistence"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<%@page import="ocupacional.bdatos.UsuariosDAO"%>
<%@page import="ocupacional.valueobjects.RolFuncionalidadVO"%>
<%@page import="ocupacional.valueobjects.UsuarioRolVO"%>
<%@page import="valeria.response.Mediador"%>
<ul class="nav" id="side-menu">
    <li class="sidebar-search">
        <div class="input-group custom-search-form">
            <!--
                <input type="text" class="form-control" placeholder="Search...">
                <span class="input-group-btn">
                    <button class="btn btn-default" type="button">
                        <i class="fa fa-search"></i>
                    </button>
                </span>
            /input-group -->
        </div>
    </li>
    <li>
        <a  href="Inicio" ><i class="fa fa-dashboard fa-fw"></i> Inicio</a>
    </li>
 
    <%
        Mediador e = (Mediador) session.getAttribute("Mediador");
        if (e != null) {
            if (e.getUsuarioVO() != null) {
                e.o.setRequest(request);
                if (e.o.getvariable("ind") != "") {
                    System.out.println("ind:::"+e.o.getvariable("ind"));
                    EntityManagerFactory emf13 = Persistence.createEntityManagerFactory("JavaP");
                    Roles Rol = new RolesJpaController(emf13).findRoles(Integer.parseInt(e.o.getvariable("ind")));
                    System.out.println("rol :::"+Rol.getRoleDescripcion());
                    
                    
                    if (Rol != null) {
                        e.getUsuarioVO().setRol(Rol);
                        session.removeAttribute("Mediador");
                        session.setAttribute("Mediador", e);
                        UsuariosDAO UsuariosDAO = new UsuariosDAO(e);
                        out.print(UsuariosDAO.Dibujar_Arbol2(Rol));
            
                    }
                }
            }
        }
    %>
    <li><a href="../logout.jsp">Terminar Session</a></li>
</ul>
<script>
    $(document).ready(function () {
        Menu();
    });
</script>
