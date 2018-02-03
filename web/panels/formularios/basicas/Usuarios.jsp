<%-- 
    Document   : users
    Created on : 21/04/2016, 06:50:49 PM
    Author     : D4V3
--%>


<%@page import="ocupacional.JPA.controlers.SedeJpaController"%>
<%@page import="valeria.metodos.Encriptacion"%>
<%@page import="ocupacional.JPA.controlers.JuridicasJpaController"%>
<%@page import="ocupacional.JPA.valueobjects.Juridicas"%>
<%@page import="ocupacional.JPA.controlers.PersonageneralJpaController"%>
<%@page import="ocupacional.JPA.valueobjects.Personageneral"%>
<%@page import="ocupacional.JPA.controlers.ProveedoresJpaController"%>
<%@page import="ocupacional.JPA.valueobjects.Proveedores"%>
<%@page import="ocupacional.JPA.controlers.ClientesJpaController"%>
<%@page import="ocupacional.JPA.valueobjects.Clientes"%>
<%@page import="formularios.controlers.EmpleadosJpaController"%>
<%@page import="ocupacional.JPA.valueobjects.Empleados"%>
<%@page import="ocupacional.JPA.valueobjects.Usuariorol"%>
<%@page import="ocupacional.JPA.valueobjects.Usuarios"%>
<%@page import="ocupacional.bdatos.RolFuncionalidadDAO"%>
<%@page import="ocupacional.JPA.controlers.UsuariosJpaController"%>
<%@page import="ocupacional.valueobjects.RolFuncionalidadVOs"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<%@page import="javax.persistence.Persistence"%>
<%@page import="valeria.metodos.Cadenas"%>

<%@page import="java.util.ArrayList"%>
<%@page import="valeria.response.Mediador"%>
<%    Mediador e = (Mediador) session.getAttribute("Mediador");
    try {
        if (e != null) {

            Cadenas pc = new Cadenas();

            String idf = request.getParameter("idf");
            if (idf != null) {
                session.removeAttribute("idf");
                session.setAttribute("idf", idf);

            } else {
                idf = (String) session.getAttribute("idf");

            }

            RolFuncionalidadVOs rf = new RolFuncionalidadDAO(e).Cargar(idf);

            EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaP");
            UsuariosJpaController udao = new UsuariosJpaController(emf);
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div class="panel panel-default">

    <div class="panel-heading">
        <div class="row">
            <div class="col-lg-9">
                <h2 class="text-success">Usuarios</h2>
            </div>

            <div class="col-lg-3">
                <div class="form-group">
                    <%if (rf.getRofu_op().indexOf("A") != -1) {%>
                    <button type="button" value="0"  class="btn btn-success bottom-right btn-outline" onclick="RecargaPanel('../panels/formularios/basicas/Usuarios_nuevo.jsp', 'panelprincipal')">Nuevo</button>
                    <%}%>
                </div>
            </div>
        </div>
    </div>
    <div class="panel-body">
        <div class="row">
            <fieldset id="caja">
                <legend></legend>
                <!-- Table -->
                <table id="tabla" class="table table-hover ">
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>SEDE</th>
                            <th>TIPO</th>
                            <th>NOMBRE</th>
                            <th>DOCUMENTO</th>
                            <th>USUARIO</th>
                            <th>CONTRASEÑA TEMPORAL</th>
                            <th>ROLES</th>
                            <th>ESTADO</th>
                            <th>ACCIONES</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%

                            if (udao.findUsuariosEntities() != null) {
                                int c = 1;
                                for (Usuarios u : udao.findUsuariosEntities()) {
                                    if(!u.getUsuaEstado().equals("ELIMINADO")){
                                    String nombre = "";
                                    String tipo = "";
                                    String documento = "";
                                    if (u.getUsuaTipo().equals("E")) {
                                        tipo = "EMPLEADO";

                                        Empleados clie = new EmpleadosJpaController(emf).findEmpleados(u.getUsuaIdpersona());
                                        if (clie != null) {
                                            nombre = clie.getEmplNombres();
                                            documento = clie.getEmplDocumento();
                                        }
                                    } else if (u.getUsuaTipo().equals("C")) {
                                        tipo = "CLIENTE";

                                        Clientes clie = new ClientesJpaController(emf).findClientes(u.getUsuaIdpersona());
                                        if (clie != null) {
                                            nombre = clie.getClieNombre();
                                            documento = clie.getClieDocumento();
                                        }
                                    } else if (u.getUsuaTipo().equals("L")) {
                                        tipo = "LABORATORIO";

                                        Proveedores clie = new ProveedoresJpaController(emf).findProveedores(Long.parseLong(u.getUsuaIdpersona().toString() ));
                                        if (clie != null) {
                                            Personageneral pege = new PersonageneralJpaController(emf).findPersonageneral(clie.getPegeId());
                                            Juridicas juri = new JuridicasJpaController(emf).findJuridicas(clie.getPegeId());
                                            nombre = juri.getJuriRazonsocial();
                                            documento = pege.getPegeDocumento();
                                        }
                                    }

                        %>
                        <tr>
                            <td><%=c++%></td>
                            <td><%=pc.notEmpty(new SedeJpaController(emf).findSede(u.getSedeId()).getSedeNombre())%></td>
                            <td><%=pc.notEmpty(tipo)%></td>
                            <td><%=pc.notEmpty(nombre)%></td>
                            <td><%=pc.notEmpty(documento)%></td>
                            <td><%=pc.notEmpty(u.getUsuaUsuario())%></td>
                            <%if (u.getUsuaEstado().equals("INSCRITO")) {%>
                            <td><%=pc.notEmpty(new Encriptacion().decrypt(u.getUsuaContrasena()))%></td>
                            <%} else {%>
                            <td></td>
                            <%}%>
                            <td>
                                <%
                                    for (Usuariorol ur : u.getUsuariorolList()) {
                                %>
                                <%=pc.notEmpty(ur.getRoleId().getRoleDescripcion())%> 
                                <br/>

                                <%
                                    }


                                %>





                            </td>
                            <td><%=pc.notEmpty(u.getUsuaEstado())%></td>
                            <td>
                                <%
                                    if (!u.getUsuaId().toString().equals(e.getUsuarioVO().getIdUsuario())) {
                                        if (rf.getRofu_op().indexOf("M") != -1) {%> <button title="Modificar" type="button" value="<%=u.getUsuaId()%>" data-toggle="modal" data-target=".modal-nuevo" class="btn-circle btn-default bottom-right btn-outline" onclick="RecargaPanel('../panels/formularios/basicas/Usuarios_nuevo.jsp?user_id=<%=u.getUsuaId()%>', 'panelprincipal')"><i class="glyphicon glyphicon-pencil"></i> </button><% }%>
                                        <%if (rf.getRofu_op().indexOf("E") != -1) {%> <button title="Eliminar" type="button" value="<%= u.getUsuaId()%>"  onclick="eliminarRegistro('../Usuarios', 'action=eliminarUsuario&user_id=' + this.value)" class="btn-circle btn-danger bottom-right btn-outline"><i class="glyphicon glyphicon-remove"></i> </button>
                                <% }
                                    }%></td>
                        </tr>
                        <%      }
                            }}
                        %>
                    </tbody>
                </table>
            </fieldset>
        </div>   
    </div>
    <div class="panel-footer">
        <div class="row">
            <div class="col-lg-9">
            </div>

            <div class="col-lg-3">
                <div class="form-group">
                    <!-- <button type="button" class="btn btn-default bottom-right btn-outline">Cancelar </button>
                     <button type="submit" class="btn btn-success bottom-right btn-outline">Registrar</button>
                    -->
                </div>
            </div>
        </div>


    </div>

</div>

<!-- modal crear modifica-->



<script data-config>
    var filtersConfig = {
        base_path: '../tablefilter/',
        col_0: 'none',
        col_1: 'select',
        col_2: 'select',
        col_8: 'select',
        col_9: 'none',
        alternate_rows: true,
        btn_reset: true,
        paging: true,
        results_per_page: ['Resultados por Pag.', [10, 25, 50, 100]],
        rows_counter: true,
        loader: true,
        status_bar: true,
        mark_active_columns: true,
        highlight_keywords: true,
        extensions: [{name: 'sort'}]
    };

    var tf = new TableFilter('tabla', filtersConfig);


    tf.init();


</script>

<script>
    $(document).ready(function () {
        ValidarFormID();
        $('.datepicker').datepicker();


    });
    $(".close").click(function () {
        $('#Form-Data').bootstrapValidator('resetForm', true);
    });

</script>
<% }else{%>
<script type="text/javascript">
    location.href='../logout.jsp';
</script>
<% }
    } catch (Exception ex) {
        ex.printStackTrace();

    }%>
