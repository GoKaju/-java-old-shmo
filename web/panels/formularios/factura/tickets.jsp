
<%-- 
    Document   : tickes
    Created on : 24/01/2016, 04:56:16 PM
    Author     : D4V3
--%>
<%@page import="valeria.metodos.Cadenas"%>
<%@page import="ocupacional.bdatos.DepartamentosDAO"%>
<%@page import="ocupacional.valueobjects.DepartamentosVO"%>
<%@page import="ocupacional.bdatos.CiudadesDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="ocupacional.valueobjects.CiudadesVO"%>
<%@page import="ocupacional.bdatos.PaisDAO"%>
<%@page import="ocupacional.valueobjects.PaisVO"%>
<%@page import="ocupacional.bdatos.ActividadEconomicaDAO"%>
<%@page import="ocupacional.valueobjects.ActividadEconomicaVO"%>
<%@page import="ocupacional.bdatos.JuridicasDAO"%>
<%@page import="ocupacional.valueobjects.JuridicasVO"%>
<%@page import="ocupacional.bdatos.NaturalesDAO"%>
<%@page import="ocupacional.valueobjects.NaturalesVO"%>
<%@page import="ocupacional.bdatos.PersonaGeneralDAO"%>
<%@page import="ocupacional.valueobjects.PersonaGeneralVO"%>
<%@page import="ocupacional.bdatos.TipoDocumentoDAO"%>
<%@page import="ocupacional.valueobjects.TipoDocumentoVO"%>
<%@page import="ocupacional.bdatos.facturacion.ClientesDAO"%>
<%@page import="ocupacional.valueobjects.facturacion.ClientesVO"%>
<%@page import="valeria.response.Mediador"%>
<%    Mediador e = (Mediador) session.getAttribute("Mediador");
if(e!=null){
    Cadenas pc = new Cadenas(); %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div class="panel panel-default">

    <div class="panel-heading">
        <div class="row">
            <div class="col-lg-9">
                <h2 class="text-success">Ordenes de servicio</h2>
            </div>

            <div class="col-lg-3">
                <div class="form-group">

                    <button type="button"  data-toggle="modal" data-target=".modal-nuevo" class="btn btn-success bottom-right btn-outline" onclick="RecargaForm('../panels/formularios/ticket/tickets.jsp', 'cajaModal', 'modalContent')">Nuevo</button>

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
                            <th>TIPO DOCUMENTO</th>
                            <th>No. DOCUMENTO</th>
                            <th>NOMBRE</th>
                            <th>TELEFONO</th>
                            <th>ESTADO</th>
                            <th>ACCIONES</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%%>
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
                PersonaGeneralVO pege = new PersonaGeneralVO();
                TipoDocumentoVO td = new TipoDocumentoVO();
                ClientesVO clie = new ClientesVO();
                String title = "Nuevo Ticket";
//            NaturalesVO natu = new NaturalesVO();
                JuridicasVO juri = new JuridicasVO();
                if (request.getParameter("clie_ocu") != null) {
                    for (ClientesVO c : new ClientesDAO(e).Listar()) {
                        if (c.getClie_id().equals(request.getParameter("clie_ocu"))) {
                            clie = c;
                        }
                    }
                    title = "Modificar Ticket";
                    pege = new PersonaGeneralDAO(e).Cargar(clie.getPege_id());
                    td = new TipoDocumentoDAO(e).Cargar(pege.getTido_id());
//                natu = new NaturalesDAO(e).Cargar(pege.getPege_id());
                    juri = new JuridicasDAO(e).Cargar(pege.getPege_id());
                }
        %>
        <div class="modal-content" id="modalContent">
            <form   id="Form-Data" role="form" name="Form-Data"  method="POST" action="../Tickets" autocomplete="off">
                <div class="modal-header" >
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title"><%=title%></h4>
                </div>
                <div class="modal-body" id="modal_crear">

                    <fieldset>
                        <legend>Datos Basicos</legend>
                        <div class="row col-xs-12">

                            <div class="col-xs-4"> 


                                <div class="form-group "> 
                                    <label class=" control-label" for="tido_id">Tipo doc.</label> 
                                    <select id="tido_id" name="td" class="form-control" required>
                                        <option value="">Seleccione..</option>
                                        <%
                                            for (TipoDocumentoVO t : new TipoDocumentoDAO(e).Listar()) {

                                        %>

                                        <option value="<%=t.getTipo_id()%>"><%=t.getTipo_descripcion()%></option>

                                        <% }%>
                                    </select>

                                    <span class="help-block"></span>

                                </div>                    
                            </div>
                            <div class="form-group col-xs-4">

                                <label class="control-label" for="pege_documento">No. doc.</label>
                                <input id="pege_documento" name="nd" placeholder="" pattern="[0-9]{10}" class="form-control " type="text" required="true"  value="<%=pc.notNull(pege.getPege_documento())%>" >
                                <span class="help-block"></span>
                            </div>
                        </div>
                    </fieldset>
                    <fieldset id="caja">
                        <legend></legend>
                        <div class="row col-xs-12">
                            <div class="form-group col-xs-4">
                                <label class="control-label" for="nombre1">Primer Nombre*</label> 
                                <input id="nombre1" name="nombre1" placeholder="" class="form-control" type="text" required="true" >
                                <span class="help-block"></span>
                            </div>
                            <div class="form-group col-xs-4">
                                <label class="control-label" for="">Segundo Nombre</label> 
                                <input id="" name="" placeholder="" class="form-control" type="text" required="true" >
                                <span class="help-block"></span>
                            </div>
                            <div class="form-group col-xs-4">
                                <label class="control-label" for="">Primer Apellido*</label>  
                                <input id="" name="" placeholder="" class="form-control" type="text" required="true" >
                                <span class="help-block"></span>
                            </div>
                        </div>
                                                <div class="row col-xs-12">
                            <div class="form-group col-xs-4">
                                <label class="control-label" for="">Segundo Apellido*</label> 
                                <input id="" name="" placeholder="" class="form-control" type="text" required="true" >
                                <span class="help-block"></span>
                            </div>
                            <div class="form-group col-xs-4">
                                <label class="control-label" for="">Fecha de Nacimiento*</label> 
                                <input id="" name="" placeholder="" class="form-control" type="text" required="true" >
                                <span class="help-block"></span>
                            </div>
                            <div class="form-group col-xs-4">
                                <label class="control-label" for="">Estado Civil*</label>  
                                <select id="" name="" class="form-control" required="true" onchange="">
                                    <option value="">Seleccione..</option>
                                    <option value="">Soltero</option>
                                    <option value="">Casado</option>
                                    <option value="">Viudo</option>
                                    <option value="">Divorciado</option>
                                    <option value="">Union Marital de Hecho</option>
                                </select>
                                <span class="help-block"></span>
                            </div>
                        </div>
                                                          <div class="row col-xs-12">
                            <div class="form-group col-xs-4">
                                <label class="control-label" for="">Grupo Sanguineo*</label> 
                                <input id="" name="" placeholder="" class="form-control" type="text" required="true" >
                                <span class="help-block"></span>
                            </div>
                            <div class="form-group col-xs-4">
                                <label class="control-label" for="">EPS*</label> 
    <select id="" name="" class="form-control" required="true" onchange="">
                                    <option value="">Seleccione..</option>
           
                                </select>
                                <span class="help-block"></span>
                            </div>
                            <div class="form-group col-xs-4">
                                <label class="control-label" for="">ARL*</label>  
                                <select id="" name="" class="form-control" required="true" onchange="">
                                    <option value="">Seleccione..</option>
                              
                                </select>
                                <span class="help-block"></span>
                            </div>
                        </div>
                        </fieldset>
                        <fieldset>
                            <legend>Datos de Ubicacion</legend>
                        
                        <div class="row col-xs-12">
                            <div class="form-group col-xs-4">
                                <label class="control-label" for="pais">Pais</label>  
                                <select id="pais" name="pais" class="form-control" required="true" onchange="RecargaSelect('../panels/formularios/ticket/tickets.jsp?p_ocu=' + this.value, 'departamentocaja', 'departamento')">
                                    <option value="">Seleccione..</option>
                                    <%  for (PaisVO p : new PaisDAO(e).Listar()) {
                                    %>
                                    <option value="<%=p.getPais_id()%>"><%=p.getPais_nombre()%>
                                    </option>
                                    <% }%>
                                </select>
                                <span class="help-block"></span>
                            </div>
                            <div class="form-group col-xs-4">
                                <label class=" control-label" for="departamento">Departamento</label>  
                                <div  id="departamentocaja">
                                    <select id="departamento" name="departamento" class="form-control" required="true" onchange="RecargaSelect('../panels/formularios/ticket/tickets.jsp?d_ocu=' + this.value, 'ciudadcaja', 'ciudad')">
                                        <option value="">Seleccione..</option>
                                        <%
                                            if (request.getParameter("p_ocu") != null) {
                                                for (DepartamentosVO p : (ArrayList<DepartamentosVO>) new DepartamentosDAO(e).Listar()) {

                                                    if (p.getPais_id().equals(request.getParameter("p_ocu"))) {

                                        %>
                                        <option value="<%=p.getDepa_id()%>"><%=p.getDepa_nombre()%>
                                        </option>
                                        <% }
                                                }
                                            }%>
                                    </select>
                                </div>
                                <span class="help-block"></span>
                            </div>
                            <div class="form-group col-xs-4">
                                <label class="control-label" for="ciudad">Ciudad</label>  
                                <div  id="ciudadcaja">
                                    <select id="ciudad" name="ciudad" class="form-control" required="true">
                                        <option value="">Seleccione..</option>
                                        <%
                                            if (request.getParameter("d_ocu") != null) {
                                                for (CiudadesVO p : (ArrayList<CiudadesVO>) new CiudadesDAO(e).Listar()) {
                                                    if (p.getDepa_id().equals(request.getParameter("d_ocu"))) {
                                        %>
                                        <option value="<%=p.getCiud_id()%>"><%=p.getCiud_nombre()%>
                                        </option>
                                        <% }
                                                }
                                            }%>
                                    </select>
                                </div>
                                <span class="help-block"></span>
                            </div>
                        </div>
                        <div class="row col-xs-12">
                            <div class="form-group col-xs-4">
                                <label class="control-label" for="pege_direccion">Direccion*</label>  
                                <input id="pege_direccion"  name="pege_direccion" placeholder="" class="form-control" type="text" required="true"  >
                                <span class="help-block"></span>
                            </div>
                            <div class="form-group col-xs-4">
                                <label class="control-label" for="pege_telefono">Telefono</label>  
                                <input id="pege_telefono" name="pege_telefono" placeholder="" class="form-control " type="tel" >
                                <span class="help-block"></span>
                            </div>
                            <div class="form-group col-xs-4">
                                <label class="control-label" for="pege_celular">Celular*</label>  
                                <input id="pege_celular"  name="pege_celular" placeholder="" maxlength="10" pattern="[0-9]{10}" class="form-control" type="text" required="true" >
                                <span class="help-block"></span>
                            </div>
                        </div>
                                    
                    </fieldset>
                    <fieldset>
                        <legend>Informacion Ocupacional Actual</legend>
                        <div class="row col-xs-12">
                            <div class="form-group col-xs-4">
                                <label class="control-label" for="rp_nombre">Cargo a Desempeñar*</label>  
                                <input id="rp_nombre" name="rp_nombre" placeholder="" class="form-control" type="text"  required="true">
                                <span class="help-block"></span>
                            </div>
                            <div class="form-group col-xs-4">
                                <label class="control-label" for="rp_tel">Antiguedad*</label>  
                                <input id="rp_tel" name="rp_tel" placeholder="" class="form-control" type="tel"  required="true" >
                                <span class="help-block"></span>
                            </div>
                       
                               <div class="form-group col-xs-4">
                                <label class="control-label" for="">Turnos*</label>  
                                <select id="" name="" class="form-control" required="true" onchange="">
                                    <option value="">Seleccione..</option>
                                </select>
                                <span class="help-block"></span>
                            </div>
                        </div> <div class="row col-xs-12">
                            <div class="form-group col-xs-12">
                                <label class="control-label" for="rp_nombre">Equipos que Maneja*</label>  
                                <input id="rp_nombre" name="rp_nombre" placeholder="" class="form-control" type="text"  required="true">
                                <span class="help-block"></span>
                            </div>
                            <div class="form-group col-xs-12">
                                <label class="control-label" for="rp_tel">Materias Primas*</label>  
                                <input id="rp_tel" name="rp_tel" placeholder="" class="form-control" type="tel"  required="true">
                                <span class="help-block"></span>
                            </div>
                       
                               <div class="form-group col-xs-4">
                                <label class="control-label" for="">Actividad Realizada*</label>  
                                <select id="" name="" class="form-control" required="true" onchange="">
                                    <option value="">Seleccione..</option>
                                </select>
                                <span class="help-block"></span>
                            </div>
                             <div class="form-group col-xs-4">
                                <label class="control-label" for="">Tipo de Examen Medico*</label>  
                                <select id="" name="" class="form-control" required="true" onchange="">
                                    <option value="">Seleccione..</option>
                                </select>
                                <span class="help-block"></span>
                            </div>
                        </div>
                    </fieldset>
                    <fieldset >
                        <legend>  <a href="#demo" class="" data-toggle="collapse">Servicios Permitidos</a></legend>
                        <div class="row col-xs-12 collapse" id="demo" >
                            <div class="form-group col-xs-4">
                                <label class="control-label" for="Servicio_sel">Servicio</label>  
                                <select id="Servicio_sel" name="Servicio_sel" class="form-control" required="true"  >
                                    <option value="">Seleccione..</option>
                                    <%  for (ActividadEconomicaVO p : new ActividadEconomicaDAO(e).Listar()) {%>
                                    <option value="<%=p.getAcec_id()%>"><%=p.getAcec_descripcion()%>
                                    </option>
                                    <% }%>
                                </select>
                                <span class="help-block"></span>
                            </div>
                            <div class="form-group col-xs-4">
                                <label class="control-label" for="precio_text">Precio</label>  
                                <input id="precio_text" name="precio_text" placeholder="" class="form-control" type="number"  required="true">

                                <span class="help-block"></span>
                            </div>
                            <div class="form-group col-xs-4">
                                <label class="control-label">  </label>
                                <input id="" name="" placeholder="" class="form-control" type="button"  value="Agregar" class="btn btn-outline btn-info">

                                <span class="help-block"></span>
                            </div>
                        </div>
                    </fieldset>
                                </div>
                <!--</div>-->
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal" onclick="$('#Form-Data').bootstrapValidator('resetForm', true);">Cancelar</button>
                    <input  name="action" value="valDoc" type="hidden">
                    <button type="button" class="btn btn-primary" onclick="$('#Form-Data').submit()">Guardar</button>
                </div> </form> </div>

        <%
            }%>
    </div>
</div>

<!-- modal  fin -->
<!-- modal ver-->

<div class="modal fade modal-ver" tabindex="-1" role="dialog" aria-lexample-modalabelledby="myLargeModalLabel">
    <div class="modal-dialog modal-lg" id="cajaModalVer">
        <div class="modal-content" id="modalContentVer">
            <%
                if (request.getParameter("clie_ocu") != null) {
                    PersonaGeneralVO pege = new PersonaGeneralVO();
                    TipoDocumentoVO td = new TipoDocumentoVO();
                    ClientesVO clie = new ClientesVO();
                    JuridicasVO juri = new JuridicasVO();
                    for (ClientesVO c : new ClientesDAO(e).Listar()) {
                        if (c.getClie_id().equals(request.getParameter("clie_ocu"))) {
                            clie = c;
                        }
                    }
                    pege = new PersonaGeneralDAO(e).Cargar(clie.getPege_id());
                    td = new TipoDocumentoDAO(e).Cargar(pege.getTido_id());
                    juri = new JuridicasDAO(e).Cargar(pege.getPege_id());
            %>
            <div class="modal-header" >
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><%=juri.getJuri_razonsocial()%></h4>
            </div>
            <div class="modal-body">

                <fieldset>
                    <legend>Datos Basicos</legend>
                    <div class="row well well-lg panel-title">

                        <ul> 
                            <li> Nit:<%=pege.getPege_documento()%>
                            </li>
                            <li>
                                Razon Social:<%=juri.getJuri_razonsocial()%>
                            </li>

                        </ul>
                    </div>

                </fieldset>
                <fieldset>
                    <legend>Servicios Permitidos</legend>
                    <div class="row well-lg">




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
</div>
<!-- modal  fin -->
<script data-config>
    var filtersConfig = {
        base_path: '../tablefilter/',
        col_1: 'select',
        col_6: 'select',
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