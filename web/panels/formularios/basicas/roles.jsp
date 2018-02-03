<%-- 
    Document   : roles
    Created on : 21/04/2016, 06:50:49 PM
    Author     : D4V3
--%>




<%@page import="ocupacional.JPA.controlers.RolesJpaController"%>
<%@page import="ocupacional.JPA.valueobjects.Roles"%>
<%@page import="javax.persistence.Persistence"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<%@page import="ocupacional.valueobjects.FuncionalidadesVO"%>
<%@page import="ocupacional.bdatos.FuncionalidadesDAO"%>
<%@page import="ocupacional.valueobjects.RolFuncionalidadVOs"%>
<%@page import="ocupacional.bdatos.RolFuncionalidadDAO"%>
<%@page import="ocupacional.valueobjects.RolVO"%>
<%@page import="ocupacional.bdatos.RolDAO"%>
<%@page import="valeria.metodos.Cadenas"%>

<%@page import="java.util.ArrayList"%>
<%@page import="valeria.response.Mediador"%>
<%    Mediador e = (Mediador) session.getAttribute("Mediador");
    if (e != null) {
        
        Cadenas pc = new Cadenas(); 

      
       String idf = request.getParameter("idf");
       if(idf!=null){
       session.removeAttribute("idf");
       session.setAttribute("idf", idf);
       
       }else{
       idf=(String)session.getAttribute("idf");
       
       
       }
        
       RolFuncionalidadVOs rf = new  RolFuncionalidadDAO(e).Cargar(idf);



%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div class="panel panel-default">

    <div class="panel-heading">
        <div class="row">
            <div class="col-lg-9">
                <h2 class="text-success">Roles</h2>
            </div>

            <div class="col-lg-3">
                <div class="form-group">
 <%if(rf.getRofu_op().indexOf("A")!=-1){%>
                    <button type="button" value="0" data-toggle="modal" data-target=".modal-nuevo" class="btn btn-success bottom-right btn-outline" onclick="peticionAjax('../Roles', 'action=CargarFuncionalidades&id=' + this.value), RecargaForm('../panels/formularios/basicas/roles.jsp', 'cajaModal', 'modalContent')">Nuevo</button>
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
                            <th>ROL</th>
                            <th>FUNCIONALIDADES</th>
                            <th>ESTADO</th>
                            <th>ACCIONES</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
 
 EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaP");
RolesJpaController rdao = new RolesJpaController(emf);
                            if (rdao.findRolesEntities()!= null) {
                                int c = 1;
                                      
                                for (Roles s : rdao.findRolesEntities()) {
                                    if(!s.getRoleEstado().equals("ELIMINADO")){
                              
                        %>
                        <tr>
                            <td><%=c++%></td>
                            <td><%=pc.notEmpty(s.getRoleDescripcion())%></td>
                            <td>
                                <%  if (new RolFuncionalidadDAO(e).ListarXrol(s.getRoleId().toString()) != null) {
                                        System.out.println("entre2");
                                        for (RolFuncionalidadVOs se : new RolFuncionalidadDAO(e).ListarXrol(s.getRoleId().toString())) {
                                            FuncionalidadesDAO fdao = new FuncionalidadesDAO(e);

                                            FuncionalidadesVO f = fdao.Cargar(se.getFunc_id());

                                            if (f.getFunc_url().equals("-")) {
                                %>
                                *<%=pc.notEmpty(f.getFunc_descripcion())%><br/>
                                <%
                                } else {%>
                                ---><%=pc.notEmpty(f.getFunc_descripcion())%> 
                                <%if (se.getRofu_op().indexOf("V") != -1) {%><i title="Ver" class="glyphicon glyphicon-zoom-in " style="color: #0088cc"></i><% }%>
                                <%if (se.getRofu_op().indexOf("A") != -1) {%><i title="Agregar" class="glyphicon glyphicon-check " style="color: #00CE6F"></i><% }%>
                                <%if (se.getRofu_op().indexOf("M") != -1) {%><i title="Modificar" class="glyphicon glyphicon-pencil " style="color: #ffcc33"></i><% }%>
                                <%if (se.getRofu_op().indexOf("E") != -1) {%><i  title="Eliminar" class="glyphicon glyphicon-remove " style="color: #cc0000"></i><% }%>
                                <br/>

                                <%
                                            }
                                        }
                                    }%>




                            </td>
                            <td><%=pc.notEmpty(s.getRoleEstado())%></td>
                            <td>
                                <%if(rf.getRofu_op().indexOf("M")!=-1){%> <button type="button" value="<%=s.getRoleId()%>" data-toggle="modal" data-target=".modal-nuevo" class="btn-circle btn-default bottom-right btn-outline" onclick="peticionAjax('../Roles', 'action=CargarFuncionalidades&id=' + this.value), RecargaForm('../panels/formularios/basicas/roles.jsp?clie_ocu=' + this.value, 'cajaModal', 'modalContent')"><i class="glyphicon glyphicon-pencil"></i> </button><% }%>
                                <%if(rf.getRofu_op().indexOf("E")!=-1){%> <button type="button" value="<%= s.getRoleId()%>"  onclick="eliminarRegistro('../Roles', 'action=eliminarRol&id=' + this.value)" class="btn-circle btn-danger bottom-right btn-outline"><i class="glyphicon glyphicon-remove"></i> </button><% }%></td>

                        </tr>
                        <%      }
                                }
                            }


                        %>
                    </tbody>





                </table>




            </fieldset>




            <!-- /.col-lg-6 (nested) -->

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
<div class="modal fade modal-nuevo" tabindex="-1" role="dialog" aria-lexample-modalabelledby="myLargeModalLabel">
    <div class="modal-dialog modal-lg" id="cajaModal">
        <% if (true) {
                String title = "Nuevo Rol";
//            NaturalesVO natu = new NaturalesVO();
                Roles ent = new Roles();
                if (request.getParameter("clie_ocu") != null) {
                         for (Roles c : rdao.findRolesEntities()) {
                        if (c.getRoleId().toString().equals(request.getParameter("clie_ocu"))) {
                            ent = c;
                        }
                    }
                    title = "Modificar Rol";

                }
        %>
        <div class="modal-content" id="modalContent">
            <form   id="Form-Data" role="form" name="Form-Data"  method="POST" action="../Roles" autocomplete="off">
                <div class="modal-header" >
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title"><%=title%></h4>
                </div>
                <div class="modal-body" id="modal_crear">

                    <fieldset>
                        <legend>Datos Basicos</legend>
                        <div class="row col-xs-12">

                            <div class="form-group ">

                                <label class="control-label" for="pege_documento">Nombre del Rol*</label>
                                <input id="pege_documento" name="nd" placeholder=""  class="form-control " type="text" required  value="<%=pc.notNull(ent.getRoleDescripcion())%>" >
                                <span class="help-block"></span>
                            </div>

                            <%if (ent.getRoleId()!= null) {%>
                            <div class="form-group ">

                                <label class="control-label" for="est">Estado*</label>
                                <select name="est" class="form-control" required >
                                    <option value="ACTIVO" <%if (ent.getRoleEstado().equals("ACTIVO")) {%>selected<% }%>>ACTIVO</option>
                                    <option value="INACTIVO" <%if (ent.getRoleEstado().equals("INACTIVO")) {%>selected<% }%>>INACTIVO</option>
                                </select>
                                <span class="help-block"></span>
                            </div>
                            <% }%>

                    </fieldset>
                    <fieldset >
                        <legend>  <a href="#Servicios" class="" data-toggle="collapse">Funcionalidades Permitidas</a></legend>
                        <div class="row col-xs-12 collapse" id="Servicios" >

                            <div id="cajaF">

                                <%
                                    ArrayList<RolFuncionalidadVOs> listaRolFuncionalidad = (ArrayList<RolFuncionalidadVOs>) session.getAttribute("listaRolFuncionalidad");

                                    if (listaRolFuncionalidad != null) {
                                        System.out.println("rntre");
                                %>
                                <table id="tablaF" class="table table-hover">
                                    <thead>
                                        <tr>
                                            <th>Funcionalidad</th>
                                            <th>Operaciones Permitidas</th>
                                            <th></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <%
                                            for (RolFuncionalidadVOs c : listaRolFuncionalidad) {
                                                FuncionalidadesVO serv = new FuncionalidadesDAO(e).Cargar(c.getFunc_id());


                                        %>
                                        <tr>
                                            <td><%=serv.getFunc_descripcion()%></td>                                            
                                            <td>

                                                <%
                                                    if (!serv.getFunc_url().equals("-")) {
                                                        if (c.getRofu_op().indexOf("V") != -1) {%><i title="Ver" class="glyphicon glyphicon-zoom-in " style="color: #0088cc"></i><% }%>
                                                <%if (c.getRofu_op().indexOf("A") != -1) {%><i title="Agregar" class="glyphicon glyphicon-check " style="color: #00CE6F"></i><% }%>
                                                <%if (c.getRofu_op().indexOf("M") != -1) {%><i title="Modificar" class="glyphicon glyphicon-pencil " style="color: #ffcc33"></i><% }%>
                                                <%if (c.getRofu_op().indexOf("E") != -1) {%><i  title="Eliminar" class="glyphicon glyphicon-remove " style="color: #cc0000"></i><% }
                                                    }%>

                                            </td>                                            
                                            <td><button type="button" title="Remover" onclick="peticionAjax('../Roles', 'action=RemoverFuncionalidad&hash=<%=c.hashCode()%>')" class="btn-circle btn-danger bottom-right btn-outline"><i class="glyphicon glyphicon-remove"></i> </button></td>

                                        </tr>
                                        <% }%>
                                    </tbody>
                                </table>

                                <% }%>

                            </div>


                            <div class="form-group col-xs-4">
                                <label class="control-label" for="func_sel">Funcionalidad*</label>  
                                <select id="func_sel"  name="func_sel" onchange="$('#agrServicio').removeAttr('disabled')" class="form-control"   >
                                    <option value="">Seleccione..</option>
                                        <%  for (FuncionalidadesVO s : (ArrayList<FuncionalidadesVO>)new FuncionalidadesDAO(e).Listar()) {%>
                                    <option value="<%=s.getFunc_id()%>"><%=s.getFunc_descripcion()%>
                                  
                                    </option>
                                    <% }%>

                                </select>
                                <span class="help-block"></span>
                            </div>
                            <div class="form-group col-xs-1"> 
                                <i title="Agregar" class="glyphicon glyphicon-check " style="color: #00CE6F"></i>
                                <input id="a_ch"  value="A" name="op_check" placeholder="" class="checkbox" type="checkbox" onchange="calcularOp()">

                                <span class="help-block"></span>
                            </div>
                            <div class="form-group col-xs-1"> 
                                <i title="Modificar" class="glyphicon glyphicon-pencil " style="color: #ffcc33" ></i>
                                <input id="a_ch"  value="M" name="op_check" placeholder="" class="checkbox" type="checkbox" onchange="calcularOp()">

                                <span class="help-block"></span>
                            </div>
                            <div class="form-group col-xs-1"> 
                                <i title="Eliminar" class="glyphicon glyphicon-remove " style="color: #cc0000"></i>
                                <input id="a_ch"  value="E" name="op_check" placeholder="" class="checkbox" type="checkbox" onchange="calcularOp()" >

                                <span class="help-block"></span>
                            </div>
                            <script type="text/javascript">


                                function  calcularOp() {
                                    var op = ['V'];

                                    $.each($("input[name='op_check']:checked"), function () {

                                        op.push($(this).val());

                                    });

                                    return op.join();

                                }
                            </script>
                            <div class="alignRight col-xs-4">
                                <br/>
                                <button type="button" id="agrServicio"  class="btn btn-primary right" onclick=" peticionAjax('../Roles', 'action=AgregarFuncionalidad&func_sel='
                                                + $('#func_sel').val() + '&op_text=' + calcularOp())">Agregar</button>

                            </div>
                        </div>
                    </fieldset>  

                </div>
                <div class="modal-footer">
                    <input  name="action" value="newRol" type="hidden">
                    <button type="button" class="btn btn-default" data-dismiss="modal" onclick="$('#Form-Data').bootstrapValidator('resetForm', true);">Cancelar </button>
                    <button type="button" class="btn btn-primary" onclick="$('#Form-Data').submit()">Guardar</button>

                    <input  name="id" value="<%=pc.notEmpty(ent.getRoleId().toString())%>" type="hidden">

                </div>
                    </form>
                                    </div>

        <%
            }%>
    </div>
</div>


<script data-config>
    var filtersConfig = {
        base_path: '../tablefilter/',
        col_0: 'none',
        //        col_1: 'select',
        col_4: 'none',
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

    function tabla2() {

        var filtersConfig = {
            base_path: '../tablefilter/',
            col_0: 'none',
            //        col_1: 'select',
            col_4: 'none',
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

        var tf2 = new TableFilter('tabla2', filtersConfig);


        tf2.init();
    }

</script>
<%}else{%>
<script type="text/javascript">
    location.href='../logout.jsp';
</script>
<%}%>
