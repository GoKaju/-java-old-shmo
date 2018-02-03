<%-- 
    Document   : usuarios_datos
    Created on : 18/08/2016, 11:37:09 AM
    Author     : D4V3
--%>


<%@page import="valeria.metodos.Encriptacion"%>
<%@page import="valeria.session.UsuarioVO"%>
<%@page import="ocupacional.JPA.controlers.SedeJpaController"%>
<%@page import="ocupacional.JPA.valueobjects.Sede"%>
<%@page import="ocupacional.JPA.valueobjects.ProveedoresExamenes"%>
<%@page import="ocupacional.JPA.controlers.JuridicasJpaController"%>
<%@page import="ocupacional.JPA.valueobjects.Juridicas"%>
<%@page import="ocupacional.JPA.controlers.PersonageneralJpaController"%>
<%@page import="ocupacional.JPA.valueobjects.Personageneral"%>
<%@page import="ocupacional.JPA.controlers.ProveedoresJpaController"%>
<%@page import="ocupacional.JPA.valueobjects.Proveedores"%>
<%@page import="ocupacional.JPA.valueobjects.ClientesServicio"%>
<%@page import="ocupacional.JPA.valueobjects.Empleadoexamen"%>
<%@page import="valeria.metodos.ManejadorFechas"%>
<%@page import="formularios.controlers.EmpleadosJpaController"%>
<%@page import="ocupacional.JPA.valueobjects.Empleados"%>
<%@page import="ocupacional.JPA.controlers.ClientesJpaController"%>
<%@page import="ocupacional.JPA.valueobjects.Clientes"%>
<%@page import="javax.persistence.Persistence"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<%@page import="ocupacional.valueobjects.RolFuncionalidadVOs"%>
<%@page import="valeria.metodos.Cadenas"%>
<%@page import="valeria.response.Mediador"%>
<%    Mediador e = (Mediador) session.getAttribute("Mediador");
    if (e != null) {
        Cadenas pc = new Cadenas();
        ManejadorFechas f = new ManejadorFechas();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaP");


%>


<div class="panel panel-default">

    <div class="panel-heading">
        <div class="row">
            <div class="col-md-6">
                <h2 class="text-success">Datos de usuario: <%=e.getUsuarioVO().getNombre_usuario()%></h2>
            </div>
                <div class="col-md-6">
                    <button data-toggle="modal" data-target="#myModal" style="margin-top: 25px" class="btn btn-primary btn-circle" title="Cambiar contraseña" ><span class="glyphicon glyphicon-lock"></span></button>
                    
                </div>

        </div>
    </div>
    <div class="panel-body">
        <div class="row">
            <% if (e.getUsuarioVO().getUsua_tipo().equals("E")) {
                    Empleados clie = new EmpleadosJpaController(emf).findEmpleados(Integer.parseInt(e.getUsuarioVO().getIdpersona()));
            %>
            <ul class="list-group">
                <li class="list-group-item"><strong>Sede: </strong> <%=pc.notEmpty(new SedeJpaController(emf).findSede(Integer.parseInt(e.getUsuarioVO().getSede_id())).getSedeNombre())%></li>
                <li class="list-group-item"><strong>Nombres: </strong> <%=pc.notEmpty(clie.getEmplNombres())%></li>
                <li class="list-group-item"><strong>Documento: </strong> <%=pc.notEmpty(clie.getEmplDocumento())%></li>
                <li class="list-group-item"><strong>Cargo: </strong> <%=pc.notEmpty(clie.getEmplCargo())%></li>
                <li class="list-group-item"><strong>Fecha ultima modificacion: </strong> <%=f.FechaLetrasHora(clie.getEmplfechaCambio())%></li>

            </ul>
            <table class="table table-hover">
                <caption class=""><h3>Examenes autorizados</h3></caption>
                <thead>
                    <tr>
                        <th>Examen</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (Empleadoexamen ee : clie.getEmpleadoexamenList()) {%>
                    <tr>
                        <td><%=ee.getExamId().getExamDescripcion()%></td>
                    </tr>
                    <%}%>
                </tbody>

            </table>


            <%} else if (e.getUsuarioVO().getUsua_tipo().equals("C")) {
                Clientes clie = new ClientesJpaController(emf).findClientes(Integer.parseInt(e.getUsuarioVO().getIdpersona()));
                if (clie != null) {
            %>
            <ul class="list-group">
                <li class="list-group-item"><strong>Sede: </strong> <%=pc.notEmpty(new SedeJpaController(emf).findSede(Integer.parseInt(e.getUsuarioVO().getSede_id())).getSedeNombre())%></li>
                <li class="list-group-item"><strong>Nombres: </strong> <%=pc.notEmpty(clie.getClieNombre())%></li>
                <li class="list-group-item"><strong>Documento: </strong> <%=pc.notEmpty(clie.getTidoId().getTidoDescripcion()) + " " + pc.notEmpty(clie.getClieDocumento())%></li>
                <li class="list-group-item"><strong>Ciudad: </strong> <%=pc.notEmpty(clie.getCiudId().getCiudNombre())%></li>
                <li class="list-group-item"><strong>Dirección: </strong> <%=pc.notEmpty(clie.getClieDireccion())%></li>
                <li class="list-group-item"><strong>Teléfonos: </strong> <%=pc.notEmpty(clie.getClieTelefonos())%></li>
                <li class="list-group-item"><strong>Email: </strong> <%=pc.notEmpty(clie.getClieEmail())%></li>
                <li class="list-group-item"><strong>Representante legal: </strong> <%=pc.notEmpty(clie.getClierepLegal())%></li>
                <li class="list-group-item"><strong>Fecha ultima modificacion: </strong> <%=f.FechaLetrasHora(clie.getClieFechacambio())%></li>
            </ul>
            <table class="table table-hover">
                <caption class=""><h3>Servicios autorizados</h3></caption>
                <thead>
                    <tr>
                        <th>Servicios</th>
                        <th>Valor</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (ClientesServicio ee : clie.getClientesServicioList()) {%>
                    <tr>
                        <td><%=ee.getServId().getServNombre()%></td>
                        <td>$<%=pc.getNumber(ee.getClseValor())%></td>
                    </tr>
                    <%}%>
                </tbody>

            </table>
            <% }
            } else if (e.getUsuarioVO().getUsua_tipo().equals("L")) {
                Proveedores clie = new ProveedoresJpaController(emf).findProveedores(Long.parseLong(e.getUsuarioVO().getIdpersona()));
                if (clie != null) {
                    Personageneral pege = new PersonageneralJpaController(emf).findPersonageneral(clie.getPegeId());
                    Juridicas juri = new JuridicasJpaController(emf).findJuridicas(clie.getPegeId());
            %>
            <ul class="list-group">
                <li class="list-group-item"><strong>Sede: </strong> <%=pc.notEmpty(new SedeJpaController(emf).findSede(Integer.parseInt(e.getUsuarioVO().getSede_id())).getSedeNombre())%></li>
                <li class="list-group-item"><strong>Nombres: </strong> <%=pc.notEmpty(juri.getJuriRazonsocial())%></li>
                <li class="list-group-item"><strong>Documento: </strong> <%=pc.notEmpty(pege.getPegeDocumento())%></li>
                <li class="list-group-item"><strong>Dirección: </strong> <%=pc.notEmpty(pege.getPegeDireccion())%></li>
                <li class="list-group-item"><strong>Teléfonos: </strong> <%=pc.notEmpty(pege.getPegeTelefono())%></li>
                <li class="list-group-item"><strong>Email: </strong> <%=pc.notEmpty(pege.getPegeEmail())%></li>
                <li class="list-group-item"><strong>Representante legal: </strong> <%=pc.notEmpty(juri.getJuriRepresentante())%></li>
                <li class="list-group-item"><strong>Fecha ultima modificacion: </strong> <%=f.FechaLetrasHora(clie.getProvFechacambio())%></li>
            </ul>
            <table class="table table-hover">
                <caption class=""><h3>Examenes autorizados</h3></caption>
                <thead>
                    <tr>
                        <th>Examen</th>
                        <th>Observacion</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (ProveedoresExamenes ee : clie.getProveedoresExamenesList()) {%>
                    <tr>
                        <td><%=ee.getExamId().getExamDescripcion()%></td>
                        <td>$<%=pc.getNumber(ee.getPrexObservacion())%></td>
                    </tr>
                    <%}%>
                </tbody>

            </table>
            <% }
           }%>


      <!-- Modal -->
                <div class="modal fade" id="myModal" role="dialog">
                    <div class="modal-dialog modal-sm">
                        <div class="modal-content"  id="modalContent">
                            
                    <div class="modal-header" style="padding: 5px auto;">
                        <h4 class="modal-title" id="myModalLabel">Cambio de contraseña</h4>
                    </div>
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="well">
                                    <%
                                    UsuarioVO user =e.getUsuarioVO();
                                    if(user!=null){
                                    %>
                                    <form role="form" name="Form-Data" id="Form-Data" method="POST" action="../Usuarios" autocomplete="off">
                                      
                                        <div class="form-group">
                                            <label for="password" class="control-label" >Contrase&ntilde;a actual</label>
                                            <input autocomplete="off" type="password" class="form-control" id="password_ant" minlength="6" maxlength="20"  name="password_ant" value="" required>
                                            <span class="help-block"></span>
                                        </div>
                                     
                                        <div class="form-group">
                                            <label for="password" class="control-label">Contrase&ntilde;a nueva</label>
                                            <input autocomplete="off" type="password" class="form-control" id="password_new" minlength="6" maxlength="20" placeholder="Contrase&ntilde;a nueva" name="password_new" value="" required>
                                            <span class="help-block"></span>
                                        </div>
                                        <div class="form-group">
                                            <label for="password" class="control-label">Confirmar contrase&ntilde;a </label>
                                            <input autocomplete="off" type="password" class="form-control" id="password_new_confirm" minlength="6" maxlength="20" placeholder="Contrase&ntilde;a confirme" name="password_new_confirm" value="" required>
                                            <span class="help-block"></span>
                                        </div>
                                        <input type="hidden" name="action" value="cam_pass" />
                                        <button type="submit" class="btn btn-success btn-block">Continuar</button>
                                    </form>
                                    <%
                                    }else{%>
                                    Está ingresando de manera no autorizada...
                                    <%}%>
                                </div>
                            </div>
                                
                </div>
            </div>  
                        </div>
                    </div>
                </div>
            <!--fin-->
        </div>   
        <!-- /.row (nested) -->
    </div>
    <!-- /.panel-body -->
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



<script>
    $(document).ready(function () {
        ValidarFormID();
    });
    $(".close").click(function () {
        $('#Form-Data').bootstrapValidator('resetForm', true);
    });



</script>
<%}else{%>
<script type="text/javascript">
    location.href='../logout.jsp';
</script>
<%}%>

 