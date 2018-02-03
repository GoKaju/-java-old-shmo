

<%@page import="ocupacional.JPA.valueobjects.Usuariorol"%>
<%@page import="ocupacional.JPA.controlers.RolesJpaController"%>
<%@page import="ocupacional.JPA.valueobjects.Roles"%>
<%@page import="ocupacional.JPA.controlers.SedeJpaController"%>
<%@page import="ocupacional.JPA.valueobjects.Sede"%>
<%@page import="valeria.metodos.ManejadorFechas"%>
<%@page import="ocupacional.JPA.controlers.UsuariosJpaController"%>
<%@page import="ocupacional.JPA.valueobjects.Usuarios"%>
<%-- 
    Document   : usuarios_datos
    Created on : 18/08/2016, 11:37:09 AM
    Author     : D4V3
--%>



<%@page import="javax.persistence.Persistence"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<%@page import="ocupacional.valueobjects.RolFuncionalidadVOs"%>
<%@page import="valeria.metodos.Cadenas"%>
<%@page import="valeria.response.Mediador"%>
<%    Mediador e = (Mediador) session.getAttribute("Mediador");
    if (e != null) {
        Cadenas o = new Cadenas();
        ManejadorFechas f = new ManejadorFechas();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaP");
        String id = request.getParameter("user_id");
        Usuarios user = new Usuarios();
        if (id != null) {
            user = new UsuariosJpaController(emf).findUsuarios(Integer.parseInt(id));
        }


%>


<div class="panel panel-default">
    <form   id="Form-Data"  name="Form-Data"  method="POST" action="../Usuarios" autocomplete="off">

    <div class="panel-heading">
        <div class="row">
            <div class="col-md-6">
                <h2 class="text-success">Creación de usuario: <%%></h2>
            </div>


        </div>
    </div>
    <div class="panel-body">
        <div class="row">
            <% if(user.getUsuaId()==null){%>
            <fieldset>
                
                <legend>Datos basicos:</legend>

                <div class="form-group col-md-3">

                    <label class="control-label" for="tipo">tipo *</label>
                    <input  id="tipo" name="" readonly placeholder=""  class="form-control " type="text" required  value="" >
                    <input type="hidden" name="tipo_usua" id="tipo_usua" value="">
                    <input type="hidden" name="codigo_usua" id="codigo_usua" value="">
                    <span class="help-block"></span>

                </div> 
                <div class="form-group col-md-4">

                    <label class="control-label" for="documento">No. doc. *</label>
                    <input  id="documento" name="documento" placeholder=""  readonly=""  class="form-control " type="text" required  value="" >
                    <span class="help-block"></span>
                </div>


                <div class="form-group col-md-4">

                    <label class="control-label" for="nombres">
                        Nombres*
                    </label>
                    <input  id="nombres" name="nombres" placeholder="" readonly   class="form-control " type="text" required  value="<%=o.notEmpty("")%>" >
                    <span class="help-block"></span>
                </div>
                <div class="col-md-1">
                    <button type="button" data-toggle="modal" data-target="#myModal" style="margin-top: 25px" class="btn btn-primary btn-circle" title="Seleccionar persona" ><span class="glyphicon glyphicon-search"></span></button>

                </div>
            </fieldset>
                    <%}%>
            <fieldset>
                <legend>Datos usuario:</legend>
                <div class="form-group col-md-3">
                    <label for="sede_usua" class="control-label" >Sede *</label>
                    <select name="sede_usua" id="sede_usua" class="form-control" required>
                        <%
                            for (Sede s : new SedeJpaController(emf).findSedeEntities()) {
                        %>
                        <option value="<%=s.getSedeId()%>"><%=s.getSedeNombre().toUpperCase()%></option>
                        <%}%>
                    </select>
                    <%if(user.getSedeId()!=null){%>
                    <script type="text/javascript">
                        $("#sede_usua").val(<%=user.getSedeId() %>);
                    </script>
                    <%}%>
                    <span class="help-block"></span>
                </div>
                <div class="form-group col-md-4">

                    <label class="control-label" for="user_usua">Usuario *</label>
                    <input <%if(user.getUsuaUsuario()!=null){%>
                        readonly
                    <%}%>
                        id="user_usua" name="user_usua"  placeholder=""  class="form-control " type="text" required  value="<%=o.notEmpty(user.getUsuaUsuario())%>" >
                    <span class="help-block"></span>

                </div> 
                <div class="form-group col-md-4">

                    <label class="control-label" for="temp_pass">Contraseña temporal*</label>
                    <input <%if(user.getUsuaUsuario()!=null){%>disabled<%}%>  id="temp_pass" name="temp_pass" placeholder=""  class="form-control " type="text" required  value="" >
                    <span class="help-block"></span>
                </div>
                <div class="col-md-1">
                    <button type="button" onclick="$('#temp_pass').removeAttr('disabled','disabled');$('#temp_pass').val(cadenaAleatoria(8))"  style="margin-top: 25px" class="btn btn-warning btn-circle" title=" Generar contraseña aleatoria" ><span class="glyphicon glyphicon-lock"></span></button>

                </div>
                    
                    <%if(user.getUsuaId()!=null){%>
                    
                    <div class="form-group col-md-3">
                    <label for="estado_usua" class="control-label" >Estado *</label>
                    <select name="estado_usua" id="estado_usua" class="form-control" >
                        <%if(!user.getUsuaEstado().equals("INSCRITO")){%> 
                        <option <%if(user.getUsuaEstado().equals("ACTIVO")){%> selected"<%}%> value="ACTIVO">ACTIVO</option>
                        <option <%if(user.getUsuaEstado().equals("INACTIVO")){%> selected"<%}%> value="INACTIVO">INACTIVO</option>
                        <%}else{%>
                        <option value="INSCRITO">INSCRITO</option>
                        <%}%>
                    </select>
                 
                    <span class="help-block"></span>
                </div>
                   
                    <%}%>
                    
            </fieldset>
                    
                    <fieldset>
                        <legend>Roles</legend>
                        <ul class="list-group">
                        <%
                        for(Roles r : new RolesJpaController(emf).findRolesEntities()){
                            
                            if(r.getRoleEstado().equals("ACTIVO")){
                            
%>
                        
                            <li class="list-group-item">
                                <div class="form-group">
                                    <input  
                        <%if(user.getUsuariorolList()!=null){
                        for(Usuariorol ur : user.getUsuariorolList()){
                        if(ur.getRoleId().getRoleId() == r.getRoleId()){%>
                        checked=""
                    <%}}}%>
                                        value="<%=r.getRoleId()%>" type="checkbox" name="roles" id="rol<%=r.hashCode()%>" />
                                    <label for="rol<%=r.hashCode()%>"><%=r.getRoleDescripcion()%></label>
                                </div>
                            </li>
                            <%}}%>
                        </ul>
                        
                   
                        
                    </fieldset>





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
                    <input type="hidden" name="action" value="newUser" />
                    <%if(user.getUsuaId()!=null){%>
                    <input type="hidden" name="user_id" value="<%=o.notEmpty(user.getUsuaId().toString())%>" />
                    <%}%>
                    <button onclick="RecargaPanel('../panels/formularios/basicas/Usuarios.jsp', 'panelprincipal')" type="button" class="btn btn-default bottom-right btn-outline">Atras</button>
                    <button onclick="
                        if( validarRequiered($('#Form-Data').serialize())){
                            
                            peticionAjax('../Usuarios',$('#Form-Data').serialize());
                            
                            
                        } 
                            " type="button" class="btn btn-success bottom-right btn-outline">Guardar</button>

                </div>
            </div>
        </div>


    </div>
    </form>
</div>

<!-- modal crear modifica-->
<!-- Modal -->
<div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog modal-lg">
        <div class="modal-content"  id="modalContent">

            <div class="modal-header" style="padding: 5px auto;">
                <h4 class="modal-title" id="myModalLabel">Busqueda por documento o nombre:</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-12">
                        <div class="">
                            <%
                            %>
                            <div class="form-group col-md-3">
                                <label for="tipo_persona" class="control-label" >tipo:</label>
                                <select name="tipo_persona" id="tipo_persona" class="form-control">
                                    <option value="C">CLIENTE</option>
                                    <option value="E">EMPLEADO</option>
                                    <option value="L">LABORATORIO</option>

                                </select>
                                <span class="help-block"></span>
                            </div>

                            <div class="form-group col-md-4">
                                <label for="numDoc" class="control-label">No. Documento:</label>
                                <input  type="number" class="form-control" id="numDoc" placeholder="Numero de documento" name="numDoc" value="" >
                                <span class="help-block"></span>
                            </div>
                            <div class="form-group col-md-4">
                                <label for="nombres_dial" class="control-label">Nombres:</label>
                                <input  type="text" class="form-control" id="nombres_dial" placeholder="Nombres" name="nombres_dial" value="" >
                                <span class="help-block"></span>
                            </div>
                            <div class="col-md-1">
                                <button type="button" 
                                    onclick="
                                $('#cajaBusqueda').load('../panels/formularios/basicas/Usuarios_nuevo_tablaBusqueda.jsp?tipo=' + $('#tipo_persona').val() + '&docu=' + $('#numDoc').val() + '&nom=' + $('#nombres_dial').val());
                                    "  style="margin-top: 25px" class="btn btn-primary btn-circle" title="Buscar" ><span class="glyphicon glyphicon-search"></span></button>

                            </div>



                        </div>
                    </div>
                        
                                <legend>Seleccione:</legend>
                                <div  class=" col-md-12"id="cajaBusqueda">

                                </div>

                            

                </div>
            </div>  
        </div>
    </div>
</div>
<!--fin-->


<script>
//    $(document).ready(function () {
//        ValidarFormID();
//    });
//    $(".close").click(function () {
//        $('#Form-Data').bootstrapValidator('resetForm', true);
//    });

    function seleccionar(nom, doc, t, tipo, id) {

        $('#tipo_usua').val(tipo);
        $('#codigo_usua').val(id);
        $('#documento').val(doc);
        $('#nombres').val(nom);
        $('#tipo').val(t);

        $("#myModal").modal("toggle");
    }
</script>
<% }else{%>
<script type="text/javascript">
    location.href='../logout.jsp';
</script>
<% }%>