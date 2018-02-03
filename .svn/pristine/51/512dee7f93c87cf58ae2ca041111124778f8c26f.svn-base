<%-- 
    Document   : entidades
    Created on : 2/04/2016, 01:55:33 PM
    Author     : D4V3
--%>

<%@page import="ocupacional.bdatos.RolFuncionalidadDAO"%>
<%@page import="ocupacional.valueobjects.RolFuncionalidadVOs"%>
<%@page import="ocupacional.valueobjects.EntidadesVO"%>
<%@page import="ocupacional.bdatos.EntidadesDAO"%>
<%@page import="valeria.metodos.Cadenas"%>
<%@page import="java.util.ArrayList"%>
<%@page import="valeria.response.Mediador"%>
<%  
    try{
    
    Mediador e = (Mediador) session.getAttribute("Mediador");
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
                <h2 class="text-success">Entidades</h2>
            </div>

            <div class="col-lg-3">
                <div class="form-group">
 <%if(rf.getRofu_op().indexOf("A")!=-1){%>
                    <button type="button"  data-toggle="modal" data-target=".modal-nuevo" class="btn btn-success bottom-right btn-outline" onclick="RecargaForm('../panels/formularios/basicas/entidades.jsp', 'cajaModal', 'modalContent')">Nuevo</button>
<% }%>
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
                            <th>TIPO</th>
                            <th>NOMBRE</th>
                            <th>ESTADO</th>
                            <th>ACCIONES</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%

                            if (new EntidadesDAO(e).Listar() != null) {
                                int c = 1;
                                for (EntidadesVO enti : (ArrayList<EntidadesVO>) new EntidadesDAO(e).Listar()) {


                        %>
                        <tr>
                            <td><%=c++%></td>
                            <td><%=enti.getEnti_tipo()%></td>
                            <td><%=enti.getEnti_nombre()%></td>
                            <td><%=enti.getEnti_estado()%></td>
                            <td>
                                <%if(rf.getRofu_op().indexOf("M")!=-1){%> <button type="button" value="<%= enti.getEnti_id()%>" data-toggle="modal" data-target=".modal-nuevo" class="btn-circle btn-default bottom-right btn-outline" onclick="RecargaForm('../panels/formularios/basicas/entidades.jsp?clie_ocu=' + this.value, 'cajaModal', 'modalContent')"><i class="glyphicon glyphicon-pencil"></i> </button><% }%>
                                <%if(rf.getRofu_op().indexOf("E")!=-1){%> <button type="button" value="<%= enti.getEnti_id()%>"  onclick="eliminarRegistro('../Entidades','action=eliminarEntidad&id='+this.value)" class="btn-circle btn-danger bottom-right btn-outline"><i class="glyphicon glyphicon-remove"></i> </button><% }%></td>
                        </tr>
                        <%      }
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
    <div class="modal-dialog modal-sm" id="cajaModal">
        <% if (true) {
                String title = "Nueva Entidad";
//            NaturalesVO natu = new NaturalesVO();
                EntidadesVO ent = new EntidadesVO();
                if (request.getParameter("clie_ocu") != null) {
                    for (EntidadesVO c : (ArrayList<EntidadesVO>) new EntidadesDAO(e).Listar()) {
                        if (c.getEnti_id().equals(request.getParameter("clie_ocu"))) {
                            ent = c;
                        }
                    }
                    title = "Modificar Entidad";

                }
        %>
        <div class="modal-content" id="modalContent">
            <form   id="Form-Data" role="form" name="Form-Data"  method="POST" action="../Entidades" autocomplete="off">
                <div class="modal-header" >
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title"><%=title%></h4>
                </div>
                <div class="modal-body" id="modal_crear">

                    <fieldset>
                        <legend>Datos Basicos</legend>
                        <div class="row col-xs-12">

                            <div class=""> 


                                <div class="form-group "> 
                                    <label class=" control-label" for="tido_id">Tipo Entidad.*</label> 
                                    <select id="tido_id" name="te" class="form-control" required>
                                        <% if (ent.getEnti_tipo() != null) {%>

                                        <option value="<%=ent.getEnti_tipo()%>"><%=ent.getEnti_tipo()%></option>
                                        <% } else {%>

                                        <option value="">Seleccione..</option>

                                        <% }%>
                                        <option value="ARL">ARL</option>
                                        <option value="EPS">EPS</option>
                                    </select>

                                    <span class="help-block"></span>

                                </div>                    
                            </div>
                            <div class="form-group ">

                                <label class="control-label" for="pege_documento">Nombre*</label>
                                <input id="pege_documento" name="nd" placeholder=""  class="form-control " type="text" required  value="<%=pc.notNull(ent.getEnti_nombre())%>" >
                                <span class="help-block"></span>
                            </div>
                                      <%if(ent.getEnti_id()!=null){%>
                            <div class="form-group ">

                                <label class="control-label" for="est">Estado*</label>
                                <select name="est" class="form-control" required >
                                    <option value="ACTIVO" <%if(ent.getEnti_estado().equals("ACTIVO")){%>selected<% }%>>ACTIVO</option>
                                    <option value="INACTIVO" <%if(ent.getEnti_estado().equals("INACTIVO")){%>selected<% }%>>INACTIVO</option>
                                </select>
                                <span class="help-block"></span>
                            </div>
                                <% }%>

                    </fieldset>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal" onclick="$('#Form-Data').bootstrapValidator('resetForm', true);">Cancelar</button>
                    <%  if (ent.getEnti_id() != null) {%>
                    <input  name="id" value="<%=ent.getEnti_id()%>" type="hidden">
                    <input  name="action" value="editEntidad" type="hidden">
                    <%} else {%>
                    <input  name="action" value="newEntidad" type="hidden">
                    <% }%>
                    <button type="button" class="btn btn-primary" onclick="$('#Form-Data').submit()" >Guardar</button>
                </div> </form> </div>

        <%
            }%>
    </div>
</div>

<script data-config>
    var filtersConfig = {
        base_path: '../tablefilter/',
        col_0: 'none',
        col_1: 'select',
        col_3: 'select',
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
    

    
</script>
<%}else{%>
<script type="text/javascript">
    location.href='../logout.jsp';
</script>
<%}
    }catch(Exception ex ){ex.printStackTrace();}
%>
