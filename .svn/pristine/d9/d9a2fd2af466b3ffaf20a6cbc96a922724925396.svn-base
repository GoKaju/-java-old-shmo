<%-- 
    Document   : ordenServicio_nueva
    Created on : 17/05/2016, 09:17:41 PM
    Author     : D4V3
--%>

<%@page import="java.util.AbstractList"%>
<%@page import="valeria.metodos.ManejadorFechas"%>
<%@page import="ocupacional.JPA.valueobjects.Itemfactura"%>
<%@page import="ocupacional.JPA.controlers.FacturasJpaController"%>
<%@page import="ocupacional.JPA.valueobjects.Facturas"%>
<%@page import="ocupacional.JPA.controlers.TicketJpaController"%>
<%@page import="formularios.entidades.ClienteSedes"%>
<%@page import="ocupacional.JPA.controlers.SedeJpaController"%>
<%@page import="ocupacional.JPA.valueobjects.Sede"%>
<%@page import="ocupacional.JPA.controlers.ClientesServicioJpaController"%>
<%@page import="java.util.List"%>
<%@page import="ocupacional.JPA.valueobjects.ClientesServicio"%>
<%@page import="ocupacional.JPA.valueobjects.Centrocostos"%>
<%@page import="ocupacional.JPA.controlers.ClientesJpaController"%>
<%@page import="ocupacional.JPA.valueobjects.Clientes"%>
<%@page import="formularios.entidades.Pacientes"%>
<%@page import="formularios.controlers.PacientesJpaController"%>
<%@page import="formularios.controlers.FormulariosJpaController"%>
<%@page import="javax.persistence.Persistence"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<%@page import="ocupacional.bdatos.historiaclinica.tipoExamenMedicoDAO"%>
<%@page import="ocupacional.valueobjects.historiaclinica.tipoExamenMedicoVO"%>
<%@page import="ocupacional.bdatos.facturacion.CentroCostosDAO"%>
<%@page import="ocupacional.valueobjects.facturacion.CentroCostosVO"%>
<%@page import="ocupacional.bdatos.facturacion.TicketClienteServicioDAO"%>
<%@page import="ocupacional.bdatos.PersonaGeneralDAO"%>
<%@page import="ocupacional.bdatos.facturacion.TicketsDAO"%>
<%@page import="ocupacional.valueobjects.facturacion.Ticket"%>
<%@page import="ocupacional.valueobjects.facturacion.TicketClienteServicio"%>
<%@page import="ocupacional.bdatos.facturacion.ClientesDAO"%>
<%@page import="ocupacional.valueobjects.facturacion.ClientesVO"%>
<%@page import="ocupacional.bdatos.facturacion.ClientesServiciosDAO"%>
<%@page import="ocupacional.valueobjects.facturacion.ClientesServiciosVO"%>
<%@page import="ocupacional.bdatos.TipoDocumentoDAO"%>
<%@page import="ocupacional.valueobjects.TipoDocumentoVO"%>
<%@page import="ocupacional.valueobjects.PersonaGeneralVO"%>
<%@page import="ocupacional.bdatos.RolFuncionalidadDAO"%>
<%@page import="ocupacional.valueobjects.RolFuncionalidadVOs"%>
<%@page import="valeria.metodos.Cadenas"%>

<%@page import="java.util.ArrayList"%>
<%@page import="valeria.response.Mediador"%>
<%    Mediador e = (Mediador) session.getAttribute("Mediador");
    if (e != null) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaP1PU");
        EntityManagerFactory emf2 = Persistence.createEntityManagerFactory("JavaP");

        Cadenas o = new Cadenas();
        ManejadorFechas f = new ManejadorFechas();

        
       
//        Facturas fac = new Facturas();
Clientes clie = new Clientes();
        System.out.println("clie_ocu = " + request.getParameter("clie_ocu"));
        if (request.getParameter("clie_ocu") != null) {
            clie = new ClientesJpaController(emf2).findClientes(Integer.parseInt(request.getParameter("clie_ocu")));
        }


%>

<div class="panel panel-default">


    <div class="panel-heading">
        <div class="row">
            <div class="col-md-12">
                <h2 class="text-success">Crear factura

                   
                </h2>
            </div>

            
        </div>
    </div>
                    <form   id="Form-Data" role="form" name="Form-Data"  method="POST" action="../Facturas" autocomplete="off">
    <div class="panel-body">
        <div class="row">


            <%                if (true) {

            %>

            <div class=" alert alert-warning">
                <b>ALERTA:</b> Los cambios realizados en esta sección seran reflejados inmediatamente en la factura y no son revertibles, usted es el responsable de estos, tenga cuidado al realizarlos. 

            </div>

            <fieldset  style="padding: 10px">
                <legend>Datos Basicos</legend>

                <div  id="pacientes_container"> 

                    <div id="p1">
                        <ul class="list-group col-md-6">
                            <li class="list-group-item"><strong>Documento:</strong> <%=o.notEmpty(clie.getClieDocumento())%></li>
                            <li class="list-group-item"><strong>Cliente:</strong> <%=o.notEmpty(clie.getClieNombre())%></li>
                              <li class="list-group-item"><strong>Direccion:</strong> <%=o.notEmpty(clie.getClieDireccion())%></li>
                            <li class="list-group-item"><strong>Telefonos:</strong> <%=o.notEmpty(clie.getClieTelefonos())%></li>
                            <li class="list-group-item"><strong>Email:</strong> <%=o.notEmpty(clie.getClieEmail())%></li>
                        </ul>
                        <ul class="list-group col-md-6">
                           <li class="list-group-item">
                                <div class="form-group ">
                            <label class="control-label" for="sede">Sede/Finca:</label>
                            <select name="sede" id="sede" class="form-control" required="">
                                <%for(Centrocostos cc : clie.getCentrocostosList()){%>   
                                <option value="<%=cc.getCecoId() %>"><%=cc.getCecoObservacion()%></option>
                                <%}%>
                                </select>
                            <span class="help-block"></span>
                        </div>
                            
                            </li>
                            <li class="list-group-item">
                        <div class="form-group ">
                            <label class="control-label" for="fecha">Fecha:</label>
                            <input type="text" class="datepicker form-control" name="fecha" id="fecha" required="" value="<%=f.DevuelveFormato(f.getFechaHoraTimeStamp()) %>" />
                            <span class="help-block"></span>
                        </div>
                            </li>
                        </ul>
                    </div>
                </div>


            </fieldset>
            <script type="text/javascript">

            </script>
            <fieldset >
                <legend>  <a class="" data-toggle="collapse">Items</a></legend>
                <div class="row" id="cajaitems">
                    <table  id="tablaItems" class=" table table-striped table-hover center" > 
                        <thead>
                            <tr>
                                <th>CANTIDAD</th>
                                <th>DESCRIPCION</th>
                                <th>VALOR UNITARIO</th>
                                <th>VALOR TOTAL</th>
                                <th></th>

                            </tr>
                        </thead>
                        <tbody>
                            <%
                                int total = 0;
                                
                                List<Itemfactura> listaItem =(AbstractList<Itemfactura>) session.getAttribute("listaItemCrearFactura");
                                if(listaItem == null){listaItem = new  ArrayList<Itemfactura>();
                                session.setAttribute("listaItemCrearFactura", listaItem);
                                }
                                for (Itemfactura d :listaItem) {
                            %>
                            <tr>
                                <td><%=d.getItfaCantidad()%></td>  
                                <td><%=d.getIdfaNombre()%></td>  
                                <td>$<%=o.getNumber(d.getIdfaValor())%></td>  
                                <td>$<%=o.getNumber(d.getIdfaValor() * d.getItfaCantidad())%></td>  
                                <td>

                                    <button title="Remover este item" type="button" class="btn btn-circle btn-danger" onclick="
                                    eliminarRegistro('../Facturas', 'action=eliminarItemNuevaFactura&hash=<%=d.hashCode()%>&clie_ocu=<%=clie.getClieId()%>')

                                            ">
                                        <i class="glyphicon glyphicon-remove"></i>
                                    </button>
                                </td>
                            </tr>
                            <% total += (d.getIdfaValor() * d.getItfaCantidad());
                        }%>

                        </tbody>
                        <tfoot>

                            <tr>
                                <td colspan="3" style=" text-align:  right; font-weight: bold">TOTAL:</td>
                                <td> <strong>$<%=o.getNumber(total)%></strong></td>
                            </tr>

                        </tfoot>
                    </table>

                </div>


<hr />
                <div class="row  "  >
                        <div class="form-group col-md-3">
                            <label class="control-label" for="cantidad_txt">Cantidad</label>
                            <input form="x" type="number" id="cantidad_txt" name="cantidad_txt"  placeholder="Cantidad de items"  class="form-control "  />
                            <span class="help-block"></span>
                        </div>
                        <div class="form-group col-md-3">
                            <label class="control-label" for="descripcion_txt">Descripcion</label>
                            <input form="x" list="servicios" type="text" id="descripcion_txt" name="descripcion_txt"  placeholder="Descripcion de los items"  class="form-control "  />
                            <datalist id="servicios">
                                <%
                                   System.out.println("cantidad de servicios:::: " + clie.getClientesServicioList().size());
                                   for (ClientesServicio cs :clie.getClientesServicioList()) {%>
                                <option value="<%=cs.getServId().getServNombre()%>">

                                    <%}%>
                            </datalist>
                            <span class="help-block"></span>
                        </div>
                        <div class="form-group col-md-3">
                            <label class="control-label" for="valor_txt">Valor Unitario</label>
                            <input form="x" type="number" id="valor_txt" name="valor_txt"  placeholder="Valor de cada item"  class="form-control " />
                            <span class="help-block"></span>
                        </div>

                        <div class="form-group col-md-3">
                            <input form="x" type="hidden" name="action" value="AgregarItemNuevaFac">
                            <input form="x" type="hidden" name="clie_ocu" value="<%=clie.getClieId() %>">


                            <input   onclick="
                                    if (validarRequiered($('#x').serialize())) {
                                        peticionAjax('../Facturas', $('#x').serialize());
                                   document.getElementById('x').reset();
                                        
                                    }
                                    "  name=""  type="button"  value="Agregar" class="btn btn-outline btn-info" style="margin-top: 25px">

                            <span class="help-block"></span>
                        </div>
                </div>
                            <hr />
                     <div class="form-group ">
                            <label class="control-label" for="valor_txt">Observación/Justificación:</label>
                            <textarea required=""  name="observacion" id="observacion" class="form-control" placeholder="Justifique la creacion de esta factura."></textarea>
                            <span class="help-block"></span>
                        </div>          
                            
            </fieldset>


            <% }%>
        </div>   
        <!-- /.row (nested) -->
    </div>
    <!-- /.panel-body -->
    <div class="panel-footer">
        <div class="row">


            <div class="col-lg-9 right">

            </div>
            <div class="col-lg-3 right">
                <div class="form-group">
                            <input type="hidden" name="clie_ocu" value="<%=clie.getClieId() %>">
                            <input type="hidden" name="action" value="GuardarNuevaFactura">

                    <button  onclick="RecargaPanel('../panels/formularios/factura/FacturaNueva.jsp', 'panelprincipal')" type="button" class="btn btn-default bottom-right btn-outline">Atras </button>
                    <button onclick="
                            alertify.confirm('¿Esta seguro de guardar esta factura?',function (e){
                                if(e){
                                     $('#Form-Data').submit();
                                }
                                
                            })
                            "  type="button" class="btn btn-success bottom-right btn-outline">Guardar </button>



                    <!--                        <button  onclick="
                                                    alertify.confirm('¿Está seguro de realizar esta acción?', function (e) {
                                                        if (e) {
                                                            $('#Form-Data').submit();
                    
                                                        }
                    
                    
                                                    })
                                                     " type="button" class="btn btn-success bottom-right btn-outline">Guardar</button>-->

                </div>
            </div>
        </div>


    </div>

                    </form>
                            <form id="x" name="x" action="" ></form>
</div>


<script>
    $(document).ready(function () {
        ValidarFormID();
   
   
        $('.datepicker').datepicker({
            format: 'dd-mm-yyyy'
            
        }
                );
    });
    $(".close").click(function () {
        $('#Form-Data').bootstrapValidator('resetForm', true);
    });

</script>
<%} else {%>
<script type="text/javascript">
    location.href = '../logout.jsp';
</script>
<%}%>