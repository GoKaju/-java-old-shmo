<%-- 
    Document   : proveedores
    Created on : 2/04/2016, 11:59:55 AM
    Author     : D4V3
--%>

<%@page import="ocupacional.bdatos.ActividadEconomicaDAO"%>
<%@page import="ocupacional.valueobjects.ActividadEconomicaVO"%>
<%@page import="ocupacional.bdatos.CiudadesDAO"%>
<%@page import="ocupacional.valueobjects.CiudadesVO"%>
<%@page import="ocupacional.bdatos.DepartamentosDAO"%>
<%@page import="ocupacional.valueobjects.DepartamentosVO"%>
<%@page import="ocupacional.bdatos.JuridicasDAO"%>
<%@page import="ocupacional.bdatos.NaturalesDAO"%>
<%@page import="ocupacional.valueobjects.JuridicasVO"%>
<%@page import="ocupacional.valueobjects.NaturalesVO"%>
<%@page import="ocupacional.bdatos.TipoDocumentoDAO"%>
<%@page import="ocupacional.valueobjects.TipoDocumentoVO"%>
<%@page import="ocupacional.bdatos.PersonaGeneralDAO"%>
<%@page import="ocupacional.valueobjects.PersonaGeneralVO"%>
<%@page import="ocupacional.bdatos.RolFuncionalidadDAO"%>
<%@page import="ocupacional.valueobjects.RolFuncionalidadVOs"%>
<%@page import="ocupacional.bdatos.facturacion.ExamenesDAO"%>
<%@page import="ocupacional.valueobjects.facturacion.ExamenesVO"%>
<%@page import="ocupacional.valueobjects.facturacion.ProveedoresExamenesVO"%>
<%@page import="ocupacional.valueobjects.facturacion.ProveedorVO"%>
<%@page import="ocupacional.bdatos.facturacion.ProveedorDAO"%>
<%@page import="ocupacional.valueobjects.facturacion.ClientesServiciosVO"%>
<%@page import="ocupacional.bdatos.facturacion.ServiciosDAO"%>
<%@page import="ocupacional.valueobjects.facturacion.ServiciosVO"%>
<%@page import="valeria.metodos.Cadenas"%>
<%@page import="java.util.ArrayList"%>
<%@page import="valeria.response.Mediador"%>
<%    Mediador e = (Mediador) session.getAttribute("Mediador");
    Cadenas pc = new Cadenas();
  if( e != null ){
      
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
                <h2 class="text-success">Laboratorios</h2>
            </div>

            <div class="col-lg-3">
                <div class="form-group">
 <%if(rf.getRofu_op().indexOf("A")!=-1){%>
                    <button type="button" value="0" data-toggle="modal" data-target=".modal-nuevo" class="btn btn-success bottom-right btn-outline" onclick="peticionAjax('../Proveedores', 'action=CargarExamenes&id=' + this.value),RecargaForm('../panels/formularios/basicas/proveedores.jsp', 'cajaModal', 'modalContent')">Nuevo</button>
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
                            <th>TIPO DOCUMENTO</th>
                            <th>No. DOCUMENTO</th>
                            <th>NOMBRE</th>
                            <th>TELEFONO</th>
                            <th>ESTADO</th>
                            <th>ACCIONES</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%

                            if (new ProveedorDAO(e).Listar() != null) {
                                int c = 1;
                                for (ProveedorVO p : new ProveedorDAO(e).Listar()) {
                                    PersonaGeneralVO pege = new PersonaGeneralDAO(e).Cargar(p.getPege_id());
                                    System.out.println(pege.getTido_id());
                                    TipoDocumentoVO td = new TipoDocumentoDAO(e).Cargar(pege.getTido_id());
                                    System.out.println(td.getTipo_id());

                                    NaturalesVO natu = new NaturalesDAO(e).Cargar(pege.getPege_id());
                                    JuridicasVO juri = new JuridicasDAO(e).Cargar(pege.getPege_id());


                        %>
                        <tr>
                            <td><%=c++%></td>
                            <td><%=td.getTipo_descripcion()%></td>
                            <td><%=pege.getPege_documento()%></td>
                            <td><%=juri.getJuri_razonsocial()%></td>
                            <td><%= pege.getPege_numerotelefono()%></td>
                            <td><%=p.getProv_estado()%></td>
                            <td><button type="button" value="<%=p.getProv_id()%>" data-toggle="modal" data-target=".modal-ver" class="btn-circle btn-success bottom-right btn-outline" onclick="peticionAjax('../Proveedores', 'action=CargarExamenes&id=' + this.value),RecargaForm('../panels/formularios/basicas/proveedores.jsp?clie_ocu=' + this.value, 'cajaModalVer', 'modalContentVer')"><i class="glyphicon glyphicon-search"></i> </button>
                                <%if(rf.getRofu_op().indexOf("M")!=-1){%> <button type="button" value="<%=p.getProv_id()%>" data-toggle="modal" data-target=".modal-nuevo" class="btn-circle btn-default bottom-right btn-outline" onclick="peticionAjax('../Proveedores', 'action=CargarExamenes&id=' + this.value), RecargaForm('../panels/formularios/basicas/proveedores.jsp?clie_ocu=' + this.value, 'cajaModal', 'modalContent')"><i class="glyphicon glyphicon-pencil"></i> </button><% }%>
                                <%if(rf.getRofu_op().indexOf("E")!=-1){%> <button type="button" value="<%= p.getProv_id()%>"  onclick="eliminarRegistro('../Proveedores', 'action=eliminarProveedor&id=' + this.value)" class="btn-circle btn-danger bottom-right btn-outline"><i class="glyphicon glyphicon-remove"></i> </button><% }%></td>

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
    <div class="modal-dialog modal-lg" id="cajaModal">
        <% if (true) {
                PersonaGeneralVO pege = new PersonaGeneralVO();
                TipoDocumentoVO td = new TipoDocumentoVO();
                ProveedorVO clie = new ProveedorVO();
                String title = "Nuevo Proveedor";
                System.out.println("----->"+request.getParameter("clie_ocu"));
//            NaturalesVO natu = new NaturalesVO();
                JuridicasVO juri = new JuridicasVO();
                if (request.getParameter("clie_ocu") != null) {
                    
                    for (ProveedorVO c : new ProveedorDAO(e).Listar()) {
                        if (c.getProv_id().equals(request.getParameter("clie_ocu"))) {
                            clie = c;
                        }
                    }
                    title = "Modificar Proveedor";
                    pege = new PersonaGeneralDAO(e).Cargar(clie.getPege_id());
                    td = new TipoDocumentoDAO(e).Cargar(pege.getTido_id());
//                natu = new NaturalesDAO(e).Cargar(pege.getPege_id());
                    juri = new JuridicasDAO(e).Cargar(pege.getPege_id());
                }
        %>
        <div class="modal-content" id="modalContent">
            <form   id="Form-Data" role="form" name="Form-Data"  method="POST" action="../Proveedores" autocomplete="off">
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
                                    <label class=" control-label" for="tido_id">Tipo doc.*</label> 
                                    <select <% if(clie.getProv_id()!=null){%>onchange="this.value=<%=pc.notEmpty(pege.getTido_id())%>"<%}%> id="tido_id" name="td" class="form-control" required>
                                        <option value="">Seleccione..</option>
                                        <%
                                            for (TipoDocumentoVO t : new TipoDocumentoDAO(e).Listar()) {
                                                String ch = "";
                                                if (pege.getTido_id() != null && pege.getTido_id().equals(t.getTipo_id())) {
                                                    ch = "selected";
                                                }

                                        %>

                                        <option value="<%=t.getTipo_id()%>" <%=ch%>><%=t.getTipo_descripcion()%></option>

                                        <% }%>
                                    </select>

                                    <span class="help-block"></span>

                                </div>                    
                            </div>
                            <div class="form-group col-xs-4">

                                <label class="control-label" for="pege_documento">No. doc.*</label>
                                <input <% if(clie.getProv_id()!=null){%> readonly<%}%> id="pege_documento" name="nd" placeholder="" pattern="[0-9]{5,}" class="form-control " type="text" required  value="<%=pc.notNull(pege.getPege_documento())%>" >
                                <span class="help-block"></span>
                            </div>
                            <div class="alignRight col-xs-4">
                                <br/>
                                <button <% if(clie.getProv_id()!=null){%>disabled=""<%}%>  type="button" id="validar" class="btn btn-primary right" onclick="peticionAjax('../Proveedores', 'action=validarProveedor&td=' + $('#tido_id').val() + '&nd=' + $('#pege_documento').val()), this.disabled = true">Validar</button>

                            </div>
                        </div>
                    </fieldset>

                    <div id="content" <%if (juri.getPege_id() == null) {%>hidden<% }%>>

                        <fieldset id="caja">
                            <legend></legend>
                            <div class="row col-xs-12">
                                <div class="form-group col-xs-4">
                                    <label class="control-label" for="juri_razonsocial">Razon Social*</label> 
                                    <input id="juri_razonsocial" name="juri_razonsocial" placeholder="" class="form-control" type="text" required="true" value="<%=pc.notEmpty(juri.getJuri_razonsocial())%>">
                                    <span class="help-block"></span>
                                </div>
                                <div class="form-group col-xs-4">
                                    <label class=" control-label" for="acec_id"> Actividad Economica*</label>  
                                    <select id="acec_id" name="acec_id" class="form-control" required="true"  >
                                        <option value="">Seleccione..</option>
                                        <%  for (ActividadEconomicaVO p : new ActividadEconomicaDAO(e).Listar()) {
                                           String ch = "";
                                                if (juri.getAcec_id()!= null && juri.getAcec_id().equals(p.getAcec_id())) {
                                                    ch = "selected";
                                                }
                                        %>
                                        <option value="<%=p.getAcec_id()%>" <%=ch%>><%=p.getAcec_descripcion()%>
                                        </option>
                                        <% }%>
                                    </select>
                                    <span class="help-block"></span>
                                </div>
                                <!--                            <div class="form-group col-xs-4">
                                                                <label class="control-label" for="juri_sede">Sede</label>
                                                                <input id="juri_sede" name="juri_sede" placeholder="" class="form-control" type="text" required="true" >
                                                                <span class="help-block"></span>
                                                            </div>-->
                                <div class="form-group col-xs-4">
                                    <label class="control-label" for="ciudad">Ciudad*</label>  
                                    <select id="ciudad" name="ciudad" class="form-control" required >
                                        <option value="">Seleccione..</option>

                                        <% for (DepartamentosVO d : (ArrayList<DepartamentosVO>) new DepartamentosDAO(e).Listar()) {

                                        %>
                                        <optgroup label="<%=d.getDepa_nombre()%>">
                                            <% for (CiudadesVO c : (ArrayList<CiudadesVO>) new CiudadesDAO(e).Listar(d.getDepa_id())) {
                                                  String ch = "";
                                                if (pege.getCiud_id()!= null && pege.getCiud_id().equals(c.getCiud_id())) {
                                                    ch = "selected";
                                                }

                                            %>
                                            <option value="<%=c.getCiud_id()%>" <%=ch%>><%=c.getCiud_nombre()%></option>
                                            <% }%>
                                        </optgroup>
                                        <% }%>
                                    </select>
                                    <span class="help-block"></span>
                                </div>
                            </div>

                            <div class="row col-xs-12">
                                <div class="form-group col-xs-4">
                                    <label class="control-label" for="pege_direccion">Dirección*</label>  
                                    <input id="pege_direccion"  name="pege_direccion" placeholder="" value="<%=pc.notEmpty(pege.getPege_direcciondomicilio())%>" class="form-control" type="text" required >
                                    <span class="help-block"></span>
                                </div>
                                <div class="form-group col-xs-4">
                                    <label class="control-label" for="pege_telefono">Telefono*</label>  
                                    <input id="pege_telefono" name="pege_telefono" placeholder="" class="form-control " value="<%=pc.notEmpty(pege.getPege_numerotelefono())%>" type="tel" required>
                                    <span class="help-block"></span>
                                </div>
                                <div class="form-group col-xs-4">
                                    <label class="control-label" for="pege_celular">Celular</label>  
                                    <input id="pege_celular"  name="pege_celular" placeholder="" maxlength="10" value="<%=pc.notEmpty(pege.getPege_numerocelular())%>" pattern="[0-9]{10}" class="form-control" type="text"  >
                                    <span class="help-block"></span>
                                </div>
                            </div>
                            <div class="row col-xs-12">
                                <div class="form-group col-xs-4">
                                    <label class="control-label" for="pege_email">Email</label>  
                                    <input id="pege_email" name="pege_email" placeholder="" class="form-control" type="email" value="<%=pc.notEmpty(pege.getPege_mail())%>" >
                                    <span class="help-block"></span>
                                </div>
                                    <%
                                    if(clie.getProv_id()!=null){
                                    %>
                                      <div class="form-group col-xs-4">
                                     <label class="control-label" for="est">Estado*</label>
                                <select name="est" class="form-control" required >
                                    <option value="ACTIVO" <%if(clie.getProv_estado().equals("ACTIVO")){%>selected<% }%>>ACTIVO</option>
                                    <option value="INACTIVO" <%if(clie.getProv_estado().equals("INACTIVO")){%>selected<% }%>>INACTIVO</option>
                                </select>
                                <span class="help-block"></span>
                                </div>
                                    <% } %>
                            </div>
                        </fieldset>
                        <fieldset>
                            <legend>Representante Legal</legend>
                            <div class="row col-xs-12">
                                <div class="form-group ">
                                    <label class="control-label" for="rp_nombre">Nombre*</label>  
                                    <input id="rp_nombre" name="rp_nombre" placeholder="" class="form-control" type="text"  required="true" value="<%=pc.notEmpty(juri.getJuri_representante())%>">
                                    <span class="help-block"></span>
                                </div>

                            </div>
                        </fieldset>
                    </div>
                    <fieldset >
                        <legend>  <a href="#Servicios" class="" data-toggle="collapse">Examenes Permitidos</a></legend>
                        <div class="row col-xs-12 collapse" id="Servicios" >

                            <div id="cajaFormServicios">

                                <%
                                    ArrayList<ProveedoresExamenesVO> listaProveedorExamenes = (ArrayList<ProveedoresExamenesVO>) session.getAttribute("listaProveedorExamenes");

                                    if (listaProveedorExamenes != null) {
                                        System.out.println("rntre");
                                %>
                                <table id="tablaFormServicios" class="table table-hover">
                                    <thead>
                                        <tr>
                                            <th>Examen</th>
                                            <th>Observación</th>
                                            <th></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <%
                                            for (ProveedoresExamenesVO c : listaProveedorExamenes) {
                                                String serv = "";
                                                for (ExamenesVO s : (ArrayList<ExamenesVO>) new ExamenesDAO(e).Listar()) {
                                                    if (c.getExam_id().equals(s.getExam_id())) {
                                                        serv = s.getExam_descripcion();
                                                        break;
                                                    }
                                                }

                                        %>
                                        <tr>
                                            <td><%=serv%></td>                                            
                                            <td><%=pc.notEmpty(pc.notEmpty(c.getPrex_observacion()))%></td>                                            
                                            <td><button type="button" title="Remover" onclick="peticionAjax('../Proveedores', 'action=RemoverExamen&hash=<%=c.hashCode()%>')" class="btn-circle btn-danger bottom-right btn-outline"><i class="glyphicon glyphicon-remove"></i> </button></td>

                                    </tr>
                                    <% }%>
                                    </tbody>
                                </table>

                                <% }%>

                            </div>


                            <div class="form-group col-xs-4">
                                <label class="control-label" for="Servicio_sel">Examen*</label>  
                                <select id="Servicio_sel"  name="Servicio_sel" onchange="$('#agrExamen').removeAttr('disabled')" class="form-control"   >
                                    <option value="">Seleccione..</option>
                                    <%  for (ExamenesVO s :(ArrayList<ExamenesVO>) new ExamenesDAO(e).Listar()) {%>
                                    <option value="<%=s.getExam_id()%>"><%=s.getExam_descripcion()%>
                                    </option>
                                    <% }%>
                                </select>
                                <span class="help-block"></span>
                            </div>
                            <div class="form-group col-xs-4">
                                <label class="control-label" for="precio_text">Observacion*</label>  
                                <input id="precio_text"  onchange="$('#agrExamen').removeAttr('disabled')" name="precio_text" placeholder="" class="form-control" type="text" >

                                <span class="help-block"></span>
                            </div>
                            <div class="alignRight col-xs-4">
                                <br/>
                                <button type="button" id="agrExamen"  class="btn btn-primary right" onclick="peticionAjax('../Proveedores', 'action=AgregarExamen&Servicio_sel=' + $('#Servicio_sel').val() + '&precio_text=' + $('#precio_text').val()), this.disabled = true">Agregar</button>

                            </div>
                        </div>
                    </fieldset>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal" onclick="$('#Form-Data').bootstrapValidator('resetForm', true);">Cancelar</button>
                    <input  name="action" value="newProveedor" type="hidden">
                    <button type="button" class="btn btn-primary" onclick="$('#Form-Data').submit()">Guardar</button>
                </div></form> </div> 

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
                    ProveedorVO clie = new ProveedorVO();
                    JuridicasVO juri = new JuridicasVO();
                    for (ProveedorVO c : new ProveedorDAO(e).Listar()) {
                        if (c.getProv_id().equals(request.getParameter("clie_ocu"))) {
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

                        <div class="col-xs-6">
                            
                        <ul> 
                            <li> Nit: <%=pc.notEmpty(pege.getPege_documento())%>
                            </li>
                            <li>
                                Razon Social: <%=pc.notEmpty(juri.getJuri_razonsocial())%>
                            </li>
                            <li>
                                  <%   String ch = "";
                                  for (ActividadEconomicaVO p : new ActividadEconomicaDAO(e).Listar()) {
                                          
                                                if (juri.getAcec_id()!= null && juri.getAcec_id().equals(p.getAcec_id())) {
                                                    ch = p.getAcec_codigo()+" - " +p.getAcec_descripcion();
                                                    break;
                                                }}
                                        %>
                                Actividad Economica: <%=ch%>
                            </li>
                            <li>
                                Representante Legal: <%=pc.notEmpty(juri.getJuri_representante())%>
                            </li>
                          
                        </ul>
                        </div>
                        <div class="col-xs-6">
                            
                        <ul> 
                                  <% 
                                  String ciudad="";
                                  for (DepartamentosVO d : (ArrayList<DepartamentosVO>) new DepartamentosDAO(e).Listar()) {

                                            if(ciudad.equals("")){ for (CiudadesVO c : (ArrayList<CiudadesVO>) new CiudadesDAO(e).Listar(d.getDepa_id())) {
                                                
                                                if (pege.getCiud_id()!= null && pege.getCiud_id().equals(c.getCiud_id())) {
                                                   ciudad= c.getCiud_nombre()+", "+d.getDepa_nombre();
                                                   break;
                                                }

                                           }}else{
                                            break;}
                                  }%>
                            <li> Ciudad: <%=pc.notEmpty(ciudad)%>
                            </li>
                            <li>
                                Dirección: <%=pc.notEmpty(pege.getPege_direcciondomicilio())%>
                            </li>
                            <li>
                                Teléfono: <%=pc.notEmpty(pege.getPege_numerotelefono())%>
                            </li>
                            <li>
                                Celular: <%=pc.notEmpty(pege.getPege_numerocelular())%>
                            </li>
                            <li>
                                Email: <%=pc.notEmpty(pege.getPege_mail())%>
                            </li>
                        </ul>
                        </div>
                        

                    </div>

                </fieldset>
                <fieldset>
                    <legend>Examenes Permitidos</legend>
                    <div class="row well-lg">
                                        <%
                                    ArrayList<ProveedoresExamenesVO> listaProveedorExamenes = (ArrayList<ProveedoresExamenesVO>) session.getAttribute("listaProveedorExamenes");

                                    if (listaProveedorExamenes != null) {
                                        System.out.println("rntre");
                                %>
                                <table id="tablaFormServicios" class="table table-hover">
                                    <thead>
                                        <tr>
                                            <th>Examen</th>
                                            <th>Observación</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <%
                                            for (ProveedoresExamenesVO c : listaProveedorExamenes) {
                                                String serv = "";
                                                for (ExamenesVO s : (ArrayList<ExamenesVO>) new ExamenesDAO(e).Listar()) {
                                                    if (c.getExam_id().equals(s.getExam_id())) {
                                                        serv = s.getExam_descripcion();
                                                        break;
                                                    }
                                                }

                                        %>
                                        <tr>
                                            <td><%=serv%></td>                                            
                                            <td><%=pc.notEmpty(pc.notEmpty(c.getPrex_observacion()))%></td>                                            

                                    </tr>
                                    <% }%>
                                    </tbody>
                                </table>

                                <% }%>


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
//        $("#ciudad").bselect();

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