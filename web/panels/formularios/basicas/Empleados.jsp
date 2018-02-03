<%-- 
    Document   : Clientes    Created on : 14/02/2016, 12:15:05 AM
    Author     : D4V3
--%>
<%@page import="formularios.controlers.EmpleadoexamenJpaController"%>
<%@page import="ocupacional.JPA.valueobjects.ClientesServicio"%>
<%@page import="ocupacional.JPA.valueobjects.Clientes"%>
<%@page import="ocupacional.bdatos.TipoDocumentoDAO"%>
<%@page import="ocupacional.valueobjects.TipoDocumentoVO"%>
<%@page import="javax.persistence.Persistence"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<%@page import="ocupacional.bdatos.RolFuncionalidadDAO"%>
<%@page import="ocupacional.valueobjects.RolFuncionalidadVOs"%>
<%@page import="valeria.metodos.Cadenas"%>
<%@page import="ocupacional.JPA.valueobjects.Empleadoexamen"%>
<%@page import="ocupacional.JPA.valueobjects.Empleados"%>
<%@page import="formularios.controlers.EmpleadosJpaController"%>

<%@page import="valeria.response.Mediador"%>
<%

    Mediador e = (Mediador) session.getAttribute("Mediador");
    Cadenas pc = new Cadenas();

    if (e != null) {

        String idf = request.getParameter("idf");
        if (idf != null) {
            session.removeAttribute("idf");
            session.setAttribute("idf", idf);

        } else {
            idf = (String) session.getAttribute("idf");

        }

        RolFuncionalidadVOs rf = new RolFuncionalidadDAO(e).Cargar(idf);

                            EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaP");
                            EmpleadosJpaController empleadosDAO = new EmpleadosJpaController(emf);



 
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div class="panel panel-default">

    <div class="panel-heading">
        <div class="row">
            <div class="col-lg-9">
                <h2 class="text-success">Empleados</h2>
            </div>

            <div class="col-lg-3">
                <div class="form-group">
                    <%if (rf.getRofu_op().indexOf("A") != -1) {%>
                    <button type="button" value="0" data-toggle="modal" data-target=".modal-nuevo" class="btn btn-success bottom-right btn-outline" >Nuevo</button>
                    <% }%>
                </div>
            </div>
        </div>
    </div>
    <div class="panel-body">
        <div class="row">
            <fieldset>
                <legend></legend>
                <!-- Table -->
                <table id="tabla" class="table table-hover ">
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>SEDE</th>
                            <th>CARGO </th>
                            <th>DOCUMENTO</th>
                            <th>NOMBRES</th>
                            <th>EXAMENES A CARGO</th>
                            <th>FIRMA/SELLO</th>
                           

                            <th>ACCIONES</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            EmpleadoexamenJpaController eedao= new EmpleadoexamenJpaController(emf);
                            int cont = 1;

                            for (Empleados clientesVO : empleadosDAO.findEmpleadosEntities()) {
if(!clientesVO.getEmplEstado().equals("ELIMINADO")){
                                
                        %>
                        <tr>
                            <td><%=cont++%></td>
                            <td><%=clientesVO.getSedeId().getSedeNombre() %></td>
                            <td><%=clientesVO.getEmplCargo()%></td>
                            <td><%=clientesVO.getEmplDocumento() %></td>
                            <td><%=clientesVO.getEmplNombres() %></td>
                            <td><% 
                                    
                                    for(Empleadoexamen exa: clientesVO.getEmpleadoexamenList()){
                                    
                                        out.println("* "+exa.getExamId().getExamDescripcion()+"<br>");
                                        
                                    
                                    }
//                                    
//                                    
//                                     %></td>
                            <td>
                                <img src="<%=pc.notEmpty( clientesVO.getEmplFirma()) %>" alt="Aqui va la firma." width="200" />
                                </td>
                            

                            

                            <td><button type="button" value="<%=clientesVO.getEmplId()%>" data-toggle="modal" data-target=".modal-ver" class="btn-circle btn-success bottom-right btn-outline" onclick=" RecargaForm('../panels/formularios/basicas/Empleados.jsp?clie_ocu=' + this.value, 'cajaModalVer', 'modalContentVer')"><i class="glyphicon glyphicon-search"></i> </button>
                                <%if (rf.getRofu_op().indexOf("M") != -1) {%><button type="button" value="<%=clientesVO.getEmplId()%>" class="btn-circle btn-default bottom-right btn-outline" onclick="RecargaPanel('../panels/formularios/basicas/Empleados_nuevo.jsp?id_ocu=' + this.value, 'panelprincipal')"><i class="glyphicon glyphicon-pencil"></i> </button><% }%>
                                <%if (rf.getRofu_op().indexOf("E") != -1) {%>   <button type="button" value="<%= clientesVO.getEmplId()%>"  onclick="eliminarRegistro('../Empleados', 'action=eliminarEmpleado&id=' + this.value)" class="btn-circle btn-danger bottom-right btn-outline"><i class="glyphicon glyphicon-remove"></i> </button><% }%></td>

                        </tr>
                        <%    }  

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

        <div class="modal-content" id="modalContent">
            <form   id="Form-Data" role="form" name="Form-Data"  method="POST" action="../Empleados" autocomplete="off">
                <div class="modal-header" >
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">Nuevo empleado</h4>
                </div>
                <div class="modal-body" id="modal_crear">

                    <fieldset>
                        <legend>Datos Basicos</legend>
                        <div class="row col-md-12">
                 
                            <div class="col-md-4"> 


                                <div class="form-group "> 
                                    <label class=" control-label" for="tido_id">Tipo doc.*</label> 
                                    <select id="tido_id" name="tido" class="form-control" required>
                                        <option value="">Seleccione..</option>
                                        <%
                                            for (TipoDocumentoVO t : new TipoDocumentoDAO(e).Listar()) {

                                        %>

                                        <option value="<%=t.getTipo_id()%>" ><%=t.getTipo_descripcion()%></option>

                                        <% }%>
                                    </select>

                                    <span class="help-block"></span>

                                </div>                    
                            </div>


                            <script type="text/javascript">
                                $("#tido_id").val(1);
                                function td(t) {

                                    if (t === 'J') {
                                        $("#tido_id").val(6);

                                    } else if (t === 'N') {

                                        $("#tido_id").val(1);

                                    }

                                }


                            </script>
                            <div class="form-group col-md-4">

                                <label class="control-label" for="pege_documento">No. doc.*</label>
                                <input id="pege_documento" name="doc" placeholder="" pattern="[0-9]{5,}" class="form-control" type="text" required   >
                                <span class="help-block"></span>
                            </div>

                        </div>
                    </fieldset>


                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal" onclick="$('#Form-Data').bootstrapValidator('resetForm', true);">Cancelar</button>
                    <input  name="action" value="validarEmpleado" type="hidden">
                    <button type="button" class="btn btn-primary" onclick="$('#Form-Data').submit()">Continuar</button>
                </div></form> </div> 

    </div>
</div>
                                

<!-- modal  fin -->
<!-- modal ver-->

<div class="modal fade modal-ver" tabindex="-1" role="dialog" aria-lexample-modalabelledby="myLargeModalLabel">
    <div class="modal-dialog modal-lg" id="cajaModalVer">
        <div class="modal-content" id="modalContentVer">
            <%
                if (request.getParameter("clie_ocu") != null) {

//                    Clientes clie = ClientesDAO.findClientes(Integer.parseInt(request.getParameter("clie_ocu")));
                    Empleados clie = new EmpleadosJpaController(emf).findEmpleados(Integer.parseInt(request.getParameter("clie_ocu")));
            %>
            <div class="modal-header" >
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><%=clie.getEmplNombres()%></h4>
            </div>
            <div class="modal-body">

                <fieldset>
                    <legend>Datos Basicos</legend>
                    <div class="row well well-lg panel-title">

                        <div class="col-xs-6">

                            <ul> 
                                <li><%=pc.notEmpty(clie.getTidoId().getTidoDescripcion() + ": " + clie.getEmplDocumento())%>
                                </li>
                                <li>
                                   Nombre: <%=pc.notEmpty(clie.getEmplNombres())%>
                                </li>
                                <li>
                                   Cargo: <%=pc.notEmpty(clie.getEmplCargo())%>
                                </li>
                             
                                <li>
                                   Estado: <%=pc.notEmpty(clie.getEmplEstado())%>
                                </li>
                             
                            </ul>
                        </div>
                    
                    </div>

                </fieldset>
                <fieldset>
                    <legend>Examenes Permitidos</legend>
                    <div class="row well-lg">

                        <table id="tablaFormServicios" class="table table-hover">
                            <thead>
                                <tr>
                                    <th>Examen</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                
                                
                                    for (Empleadoexamen ee : clie.getEmpleadoexamenList()) {
                                %>
                                <tr>
                                    <td><%=ee.getExamId().getExamDescripcion()%></td>                                            

                                </tr>
                                <% }%>
                            </tbody>
                        </table>





                    </div>
                </fieldset>

            </div>


            <div class="modal-footer">
                <!--<button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>-->
                <button type="button" class="btn btn-primary" data-dismiss="modal">OK</button>
            </div> 
            <% }%></div>
    </div>
</div>

<!-- modal  fin -->
<script data-config>
    var filtersConfig = {
        base_path: '../tablefilter/',
        col_1: 'select',
        col_6: 'none',
        col_7: 'none',
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
//        $("#ciudad").bselect();

    });

    $(".close").click(function () {
        $('#Form-Data').bootstrapValidator('resetForm', true);
    });


</script>
<% }else{%>
<script type="text/javascript">
    location.href='../logout.jsp';
</script>
<% }%>