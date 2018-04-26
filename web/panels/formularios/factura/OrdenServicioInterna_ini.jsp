<%-- 
    Document   : OrdenServicioInterna_ini
    Created on : 22/05/2016, 03:53:38 PM
    Author     : D4V3
--%>

<%@page import="ocupacional.JPA.valueobjects.ClientesServicio"%>
<%@page import="ocupacional.JPA.valueobjects.Clientes"%>
<%@page import="ocupacional.JPA.controlers.ClientesJpaController"%>
<%@page import="formularios.controlers.PacientesJpaController"%>
<%@page import="javax.persistence.Persistence"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<%@page import="ocupacional.bdatos.RolFuncionalidadDAO"%>
<%@page import="ocupacional.valueobjects.RolFuncionalidadVOs"%>
<%@page import="ocupacional.valueobjects.facturacion.ClientesServiciosVO"%>
<%@page import="ocupacional.bdatos.facturacion.ServiciosDAO"%>
<%@page import="ocupacional.valueobjects.facturacion.ServiciosVO"%>
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
<%   

session.removeAttribute("listaTiketEmpresa");
 Mediador e = (Mediador) session.getAttribute("Mediador");
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
<div class="panel panel-default">

    <div class="panel-heading">
        <div class="row">
            <div class="col-lg-9">
                <h2 class="text-success">Orden Servicio</h2>
            </div>

   
        </div>
    </div>
    <div class="panel-body">
        <div class="row">
            <fieldset>
                <legend>Seleccione un cliente...</legend>
                <!-- Table -->
                <table id="tabla" class="table table-hover ">
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>TIPO </th>
                            <th>TIPO DOCUMENTO</th>
                            <th>No. DOCUMENTO</th>
                            <th>NOMBRE</th>
                            <th>TELEFONO</th>
                  
                            <th>ACCIONES</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                    
                          EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaP");
                    ClientesJpaController   ClientesDAO = new ClientesJpaController(emf);
                                int cont = 1;
                    
                                for (Clientes clientesVO : ClientesDAO.findClientesEntities()) {
                                   
                                   if(!clientesVO.getClieEstado().equals("ELIMINADO")){
                        %>
                        <tr>
                            <td><%=cont++%></td>
                            <td><%=clientesVO.getClieTipo() %></td>
                            <td><%=clientesVO.getTidoId().getTidoDescripcion() %></td>
                            <td><%=clientesVO.getClieDocumento()  %></td>
                            <td><%=clientesVO.getClieNombre() %></td>
                            <td><%=clientesVO.getClieTelefonos()%></td>
                      
                            <td><button type="button" value="<%=clientesVO.getClieId()%>" data-toggle="modal" data-target=".modal-ver" class="btn-circle btn-success bottom-right btn-outline" onclick="peticionAjax('../Clientes', 'action=CargarServicios&id=' + this.value),RecargaForm('../panels/formularios/basicas/Clientes.jsp?clie_ocu=' + this.value, 'cajaModalVer', 'modalContentVer')"><i class="glyphicon glyphicon-search"></i> </button>
                                 <%if(rf.getRofu_op().indexOf("M")!= -1){%><button type="button" value="<%=clientesVO.getClieId()%>" class="btn-circle btn-default bottom-right btn-outline" onclick="RecargaPanel('../panels/formularios/factura/ordenServicio.jsp?idf=<%=idf%>&clie_ocu='+ this.value, 'panelprincipal')"><i class="glyphicon glyphicon-pencil"></i> </button><% }%>

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

        <div class="modal-content" id="modalContent">
            <form   id="Form-Data" role="form" name="Form-Data"  method="POST" action="../Clientes" autocomplete="off">
                <div class="modal-header" >
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">Nuevo cliente</h4>
                </div>
                <div class="modal-body" id="modal_crear">

                    <fieldset>
                        <legend>Datos Basicos</legend>
                        <div class="row col-md-12">
                            <div class="col-md-4"> 


                                <div class="form-group "> 
                                    <label class=" control-label" for="tipocliente">Tipo cliente*</label> 
                                    <select id="tipocliente" name="tipocliente" class="form-control"  onchange="td(this.value)">
                                        <option value="J">JURIDICO</option>
                                        <option value="N">NATURAL</option>
                                    
                                    </select>

                                    
                                    
                                    <span class="help-block"></span>

                                </div>                    
                            </div>
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
                                              $("#tido_id").val(6);
                                        function td (t){
                                       
                                            if(t==='J'){
                                                $("#tido_id").val(6);
                                                
                                            }else if (t==='N'){
                                                
                                                $("#tido_id").val(1);
                                                
                                            }
                                            
                                        }
                                        
                                        
                                    </script>
                            <div class="form-group col-md-4">

                                <label class="control-label" for="pege_documento">No. doc.*</label>
                                <input id="pege_documento" name="doc" placeholder="" pattern="[0-9]{5,}" class="form-control " type="text" required  " >
                                <span class="help-block"></span>
                            </div>
                    
                        </div>
                    </fieldset>

                 
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal" onclick="$('#Form-Data').bootstrapValidator('resetForm', true);">Cancelar</button>
                    <input  name="action" value="validarCliente" type="hidden">
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
              
                   Clientes clie= ClientesDAO.findClientes(Integer.parseInt(request.getParameter("clie_ocu")));
            %>
            <div class="modal-header" >
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><%=clie.getClieNombre()%></h4>
            </div>
            <div class="modal-body">

                <fieldset>
                    <legend>Datos Basicos</legend>
                    <div class="row well well-lg panel-title">

                        <div class="col-xs-6">
                            
                        <ul> 
                            <li><%=pc.notEmpty(clie.getTidoId().getTidoDescripcion()+": "+clie.getClieDocumento())%>
                            </li>
                            <li>
                                Razon Social/Nombre: <%=pc.notEmpty(clie.getClieNombre())%>
                            </li>
                        <%
                        if(clie.getClieTipo().equals("J")){
                        
                        %>
                            <li>
                                Representante Legal: <%=pc.notEmpty(clie.getClierepLegal())%>
                            </li>
                          <%}%>
                        </ul>
                        </div>
                        <div class="col-xs-6">
                            
                        <ul>
                            <li> Ciudad: <%=pc.notEmpty(clie.getCiudId().getCiudNombre())%>
                            </li>
                            <li>
                                Dirección: <%=pc.notEmpty(clie.getClieDireccion())%>
                            </li>
                            <li>
                                Teléfonos: <%=pc.notEmpty(clie.getClieTelefonos())%>
                            </li>
                        
                            <li>
                                Email: <%=pc.notEmpty(clie.getClieEmail())%>
                            </li>
                        </ul>
                        </div>
                        

                    </div>

                </fieldset>
                <fieldset>
                    <legend>Servicios Permitidos</legend>
                    <div class="row well-lg">
                                          
                                <table id="tablaFormServicios" class="table table-hover">
                                    <thead>
                                        <tr>
                                            <th>Servicio</th>
                                            <th>Precio(COP)</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <%
                                            for (ClientesServicio c : clie.getClientesServicioList()) {
                                                                               if(c.getClseEstado().equals("ACTIVO")){ 

                                                %>
                                        <tr>
                                            <td><%=c.getServId().getServNombre() %></td>                                            
                                            <td>$<%=pc.getNumber(c.getClseValor())%></td>                                            

                                    </tr>
                                    <% }}%>
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