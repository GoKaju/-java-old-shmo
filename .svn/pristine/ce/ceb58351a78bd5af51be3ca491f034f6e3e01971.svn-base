<%-- 
    Document   : centroCostos
    Created on : 21-may-2016, 23:30:46
    Author     : Sebas
--%>

<%@page import="ocupacional.valueobjects.facturacion.ClientesVO"%>
<%@page import="ocupacional.valueobjects.facturacion.CentroCostosVO"%>
<%@page import="ocupacional.bdatos.facturacion.ClientesDAO"%>
<%@page import="ocupacional.bdatos.facturacion.CentroCostosDAO"%>
<%@page import="ocupacional.bdatos.RolFuncionalidadDAO"%>
<%@page import="ocupacional.valueobjects.RolFuncionalidadVOs"%>
<%@page import="valeria.metodos.ManejadorFechas"%>
<%@page import="valeria.metodos.Cadenas"%>
<%@page import="valeria.response.Mediador"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>




<%    Mediador e = (Mediador) session.getAttribute("Mediador");
    if (e != null) {

        Cadenas pc = new Cadenas();
        ManejadorFechas f = new ManejadorFechas();

        String idf = request.getParameter("idf");
        if (idf != null) {
            session.removeAttribute("idf");
            session.setAttribute("idf", idf);

        } else {
            idf = (String) session.getAttribute("idf");

        }
                            ClientesDAO cdao = new ClientesDAO(e);

        RolFuncionalidadVOs rf = new RolFuncionalidadDAO(e).Cargar(idf);
        
        ClientesVO clie  =  new ClientesVO();
        if(request.getParameter("clie_id")!=null){
        clie = cdao.Cargar(request.getParameter("clie_id"));
        
        }else{
        clie = cdao.Cargar(e.getUsuarioVO().getIdpersona());
        
        }
        


%>



<div class="panel panel-default">

    <div class="panel-heading">
        <div class="row">
            <div class="col-md-12">
                <h2 class="text-success">Sedes- <%=clie.getClie_nombre()%></h2>
            </div>
            <div class="form-group col-md-4">

                <label class="control-label" for="descripcion_txt">Descripcion</label>
                <input  id="descripcion_txt" name="descripcion_txt" placeholder=""  class="form-control " type="text" required  value="<%%>" >
                <span class="help-block"></span>
            </div>
            <div class="  col-md-4">
                <button  style="margin-top: 25px" type="button"   class="btn btn-success bottom-right btn-outline" onclick="peticionAjax('../CentroCostos', 'action=agregar&clie_id=<%=clie.getClie_id()%>&descripcion=' + $('#descripcion_txt').val())">Agregar</button>

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
                            <th>DESCRIPCIÓN</th>
                            <th>ESTADO</th>

                        </tr>
                    </thead>
                    <tbody>
                        <%//     
                            CentroCostosDAO ccdao = new CentroCostosDAO(e);

                            int count= 0;
                            
                            for (CentroCostosVO c : ccdao.ListarXcliente(clie.getClie_id())) {
count++;
                                 %>
                        <tr>
                            <td><%= count %></td>
                            <td><%= pc.notEmpty(c.getCeco_observacion())  %></td>
                            <td><%= pc.notEmpty(c.getCeco_estado())  %></td>
                            
                            <td>
                                <!--<button type="button" value="<%%>" data-toggle="modal" data-target=".modal-ver" class="btn-circle btn-success bottom-right btn-outline" onclick="RecargaForm('../panels/formularios/ticket/tickets.jsp?clie_ocu=' + this.value, 'cajaModalVer', 'modalContentVer')"><i class="glyphicon glyphicon-search"></i> </button>-->
                                <button type="button" value="<%%>" data-toggle="modal" data-target=".modal-nuevo" class="btn-circle btn-default bottom-right btn-outline" onclick="RecargaForm('../panels/formularios/factura/centroCostos.jsp?clie_id=<%=clie.getClie_id() %>&cc_ocu=<%=c.getCeco_id() %>', 'cajaModal', 'modalContent')"><i class="glyphicon glyphicon-pencil"></i> </button>
                                <button type="button" class="btn-circle btn-danger bottom-right btn-outline" onclick="peticionAjax('../CentroCostos','action=eliminarCentroCosto&clie_id=<%=clie.getClie_id()%>&id=<%= c.getCeco_id() %>')" ><i class="glyphicon glyphicon-remove"></i> </button></td>
                        </tr>
                        <%
                                
                            }
                        %>
                    </tbody>






                </table>




            </fieldset>

<!-- modal crear modifica-->

<div class="modal fade modal-nuevo" tabindex="-1" role="dialog" aria-lexample-modalabelledby="myLargeModalLabel">
    <div class="modal-dialog modal-sm" id="cajaModal">
        <% if (true) {
                String title = "Editar";
//            NaturalesVO natu = new NaturalesVO();
               CentroCostosVO cc = new CentroCostosVO();
                if (request.getParameter("cc_ocu") != null) {
             cc= ccdao.Cargar(request.getParameter("cc_ocu"));
                }
        %>
        <div class="modal-content" id="modalContent">
            <form   id="Form-Data" role="form" name="Form-Data"  method="POST" action="../CentroCostos" autocomplete="off">
                <div class="modal-header" >
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title"><%=title%></h4>
                </div>
                <div class="modal-body" id="modal_crear">

                    <fieldset>
                        <legend>Datos Basicos</legend>
                        <div class="row col-xs-12">

                            <div class=""> 
                                <label class="control-label" for="nombre">Nombre*</label>
                                <input id="nombre" name="nombre" placeholder=""  class="form-control " type="text" required  value="<%=pc.notNull(cc.getCeco_observacion())%>" >
                                <span class="help-block"></span>
                            </div>
                           
                                
                                <%if(cc.getCeco_id()!=null){%>
                            <div class="form-group ">

                                <label class="control-label" for="est">Estado*</label>
                                <select name="est" class="form-control" required >
                                    <option value="ACTIVO" <%if(cc.getCeco_estado().equals("ACTIVO")){%>selected<% }%>>ACTIVO</option>
                                    <option value="INACTIVO" <%if(cc.getCeco_estado().equals("INACTIVO")){%>selected<% }%>>INACTIVO</option>
                                </select>
                                <span class="help-block"></span>
                            </div>
                                <% }%>

                    </fieldset>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal" onclick="$('#Form-Data').bootstrapValidator('resetForm', true);">Cancelar</button>
                    <%  if (cc.getCeco_id()!= null) {%>
                    <input  name="cc_id" value="<%=cc.getCeco_id()%>" type="hidden">
                    <input  name="clie_id" value="<%=clie.getClie_id()%>" type="hidden">
                    <input  name="action" value="editarCC" type="hidden">
                    <%}%>
                    <button type="button" class="btn btn-primary" onclick="$('#Form-Data').submit()" >Guardar</button>
                </div> </form> </div>

        <%
            }%>
    </div>
</div>


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
                  <button  onclick="RecargaPanel('../panels/formularios/basicas/Clientes.jsp?idf=<%=(String) session.getAttribute("idf")%>', 'panelprincipal')" type="button" class="btn btn-default bottom-right btn-outline">Atras </button>

                    <!-- <button type="button" class="btn btn-default bottom-right btn-outline">Cancelar </button>
                     <button type="submit" class="btn btn-success bottom-right btn-outline">Registrar</button>
                    -->
                </div>
            </div>
        </div>


    </div>

</div>
<%}else{%>
<script type="text/javascript">
    location.href='../logout.jsp';
</script>
<%}%>