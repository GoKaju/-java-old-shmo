<%-- 
        DESAROLLADOR : Alejandro 
        NOMBRE DEL DOCUMENTO   : left_men
        FECHA DE CREACION : 05-nov-2015, 22:20:05  
        HISTORIA DE USUARIO :
        DESCRIPCION FUNCIONAL DEL DOCUMENTO : 
--%>
<%@page import="ocupacional.JPA.valueobjects.Roles"%>
<%@page import="ocupacional.JPA.valueobjects.Usuariorol"%>
<%@page import="ocupacional.valueobjects.RolFuncionalidadVOs"%>
<%@page import="ocupacional.bdatos.RolFuncionalidadDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="ocupacional.bdatos.UsuariosDAO"%>
<%@page import="ocupacional.valueobjects.RolFuncionalidadVO"%>
<%@page import="ocupacional.valueobjects.UsuarioRolVO"%>
<%@page import="valeria.response.Mediador"%>
<div class="navbar-default sidebar" role="navigation">
    <div class="sidebar-nav navbar-collapse" id="menu-left">
        <ul class="nav" id="side-menu">
            <%
                Mediador e = (Mediador) session.getAttribute("Mediador");
                if (e != null) {
                    if (e.getUsuarioVO() != null) {
                        Roles UsuarioRolVO = (Roles) e.getUsuarioVO().getRol();
                        if (UsuarioRolVO != null) {
                            UsuariosDAO UsuariosDAO = new UsuariosDAO(e);
            %>
            <li class="sidebar-search">
<!--                <div class="input-group custom-search-form">
                    
                        <input type="text" class="form-control" placeholder="Search...">
                        <span class="input-group-btn">
                            <button class="btn btn-default" type="button">
                                <i class="fa fa-search"></i>
                            </button>
                        </span>
                    /input-group 
                </div>-->
            </li>
            <li>
                <a class="active" href="Inicio"><i class="fa fa-dashboard fa-fw"></i> Inicio</a>
            </li>
            <%
                            out.print(UsuariosDAO.Dibujar_Arbol2(UsuarioRolVO));
                        
                        }
                    }
                }
            %>
            <!--<li><a href="../logout.jsp">Terminar Session</a></li>-->
        </ul>
        <script>
            $(document).ready(function () {
                Menu();
            });
        </script>

    </div>
            
                
<!-- Trigger the modal with a button -->
<!--<button hidden="hidden" type="button" class="btn btn-info btn-lg btn-circle" data-toggle="modal" data-target="#chatModal"><i class="fa fa-users"></i></button>-->
<!--<script type="text/javascript" src="../panels/Chat/js/chat.js">
</script>-->
<!--
<script type="text/javascript">
    
    conectarChat(<%//e.getUsuarioVO().getIdUsuario()%>);
    
</script>-->

            
    <!-- /.sidebar-collapse -->
</div>
<!-- /.navbar-static-side -->
        