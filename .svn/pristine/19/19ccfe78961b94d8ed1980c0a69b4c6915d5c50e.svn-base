<%-- 
    Document   : ordenServicio_nueva
    Created on : 17/05/2016, 09:17:41 PM
    Author     : D4V3
--%>

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

        Facturas fac = new Facturas();

        System.out.println("id_ocu = " + request.getParameter("id_ocu"));
        if (request.getParameter("id_ocu") != null) {
            fac = new FacturasJpaController(emf2).findFacturas(Integer.parseInt(request.getParameter("id_ocu")));
        }


%>

<div class="panel panel-default">


    <div class="panel-heading">
        <div class="row">
            <div class="col-md-12">
                <h2 class="text-success">Modificar factura - <%=fac.getFactConsecutivo()%>

                    <small> <label  >Estado</label>  
                        <a href="javascript:;" data-url="" id="estado" data-type="select" data-placement="top" data-title="Ingrese el nuevo estado" class="editable editable-click" data-original-title="" title="Modificar estado">
                            <%=fac.getFactEstado()%>
                        </a></small>
                </h2>
            </div>

            <script type="text/javascript">
                    $('#fac_estado').val('<%=fac.getFactEstado()%>');
            </script>
        </div>
    </div>
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
                            <li class="list-group-item"><strong>Documento:</strong> <%=o.notEmpty(fac.getCecoId().getClieId().getClieDocumento())%></li>
                            <li class="list-group-item"><strong>Cliente:</strong> <%=o.notEmpty(fac.getCecoId().getClieId().getClieNombre())%></li>
                            <li class="list-group-item"><strong>Sede/Finca:</strong> <%=o.notEmpty(fac.getCecoId().getCecoObservacion())%></li>
                            <li class="list-group-item"><strong>Fecha:</strong> 
                                <a href="javascript:;" data-url="" id="username" data-type="date" data-placement="top" data-title="Ingrese la nueva fecha" class="editable editable-click" data-original-title="" title="Modificar fecha">
                                    <%=o.notEmpty(f.DevuelveFormatoSlash(fac.getFactfechaCreacion()))%> 

                                </a>
                            </li>
                        </ul>
                        <ul class="list-group col-md-6">
                            <li class="list-group-item"><strong>Direccion:</strong> <%=o.notEmpty(fac.getCecoId().getClieId().getClieDireccion())%></li>
                            <li class="list-group-item"><strong>Telefonos:</strong> <%=o.notEmpty(fac.getCecoId().getClieId().getClieTelefonos())%></li>
                            <li class="list-group-item"><strong>Email:</strong> <%=o.notEmpty(fac.getCecoId().getClieId().getClieEmail())%></li>
                        </ul>
                    </div>
                </div>


            </fieldset>
            <script type="text/javascript">

            </script>
            <fieldset >
                <legend>  <a class="" data-toggle="collapse">Items</a></legend>
                <div class="row" id="cajaitems">
                    <table  class=" table table-striped table-hover center" > 
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
                                for (Itemfactura d : fac.getItemfacturaList()) {
                            %>
                            <tr>
                                <td><%=d.getItfaCantidad()%></td>  
                                <td><%=d.getIdfaNombre()%></td>  
                                <td>$<%=o.getNumber(d.getIdfaValor())%></td>  
                                <td>$<%=o.getNumber(d.getIdfaValor() * d.getItfaCantidad())%></td>  
                                <td>

                                    <button title="Remover este item" type="button" class="btn btn-circle btn-danger" onclick="
                                    eliminarRegistro('../Facturas', 'action=eliminarItem&id_ocu=<%=fac.getFactId()%>&id=<%=d.getItfaId()%>')

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



                <div class="row  "  >
                    <form   id="Form-Data" role="form" name="Form-Data"  method="POST" action="../OrdenServicio" autocomplete="off">
                        <div class="form-group col-md-3">
                            <label class="control-label" for="cantidad_txt">Cantidad</label>
                            <input type="number" id="cantidad_txt" name="cantidad_txt"  placeholder="Cantidad de items"  class="form-control "  required=""/>
                            <span class="help-block"></span>
                        </div>
                        <div class="form-group col-md-3">
                            <label class="control-label" for="descripcion_txt">Descripcion</label>
                            <input list="servicios" type="text" id="descripcion_txt" name="descripcion_txt"  placeholder="Descripcion de los items"  class="form-control "  required=""/>
                            <datalist id="servicios">
                                <%
                                   System.out.println("cantidad de servicios:::: " + fac.getCecoId().getClieId().getClientesServicioList().size());
                                   for (ClientesServicio cs : fac.getCecoId().getClieId().getClientesServicioList()) {%>
                                <option value="<%=cs.getServId().getServNombre()%>">

                                    <%}%>
                            </datalist>
                            <span class="help-block"></span>
                        </div>
                        <div class="form-group col-md-3">
                            <label class="control-label" for="valor_txt">Valor Unitario</label>
                            <input type="number" id="valor_txt" name="valor_txt"  placeholder="Valor de cada item"  class="form-control "  required=""/>
                            <span class="help-block"></span>
                        </div>

                        <div class="form-group col-md-3">
                            <input type="hidden" name="id_ocu" value="<%=fac.getFactId()%>">
                            <input type="hidden" name="action" value="AgregarItem">
                            <input   onclick="
                                    if (validarRequiered($('#Form-Data').serialize())) {
                                        peticionAjax('../Facturas', $('#Form-Data').serialize());
                                    }
                                    "  name=""  type="button"  value="Agregar" class="btn btn-outline btn-info" style="margin-top: 25px">

                            <span class="help-block"></span>
                        </div>
                    </form>
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

                    <button  onclick="RecargaPanel('../panels/formularios/factura/Facturamdf.jsp', 'panelprincipal')" type="button" class="btn btn-default bottom-right btn-outline">Atras </button>



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

</div>


<script>
    $(document).ready(function () {
        ValidarFormID();
        $('#username').editable({
            format: 'yyyy-mm-dd',
            viewformat: 'dd/mm/yyyy',
            datepicker: {
                weekStart: 1
            },
            success: function (response, newValue) {
                var x = newValue.toUTCString();
                peticionAjax('../Facturas', 'action=cambiarFecha&fecha=' + convertDate(x) + '&id=<%=fac.getFactId()%>');

            }


        });
        $('#estado').editable({
            value: '<%=fac.getFactEstado()%>',
            source: [
                {value: 'ACTIVA', text: 'ACTIVA'},
                {value: 'ANULADA', text: 'ANULADA'}
            ],
            success: function (response, newValue) {
                var x = newValue;
                peticionAjax('../Facturas', 'action=cambiarEstado&fac_estado=' + x + '&id=<%=fac.getFactId()%>');

            }


        });
        $('.datepicker').datepicker();
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