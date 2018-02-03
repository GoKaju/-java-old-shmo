<%-- 
    Document   : ordenServicio_nueva
    Created on : 17/05/2016, 09:17:41 PM
    Author     : D4V3
--%>

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

        Cadenas pc = new Cadenas();
        Clientes c = new Clientes();
        System.out.println("id_ocu = " + request.getParameter("id_ocu"));
        if (request.getParameter("id_ocu") != null) {
            c = new ClientesJpaController(emf2).findClientes(Integer.parseInt(request.getParameter("id_ocu")));
        }

        PacientesJpaController pacic = new PacientesJpaController(emf);
        TicketJpaController ttc = new TicketJpaController(emf2);

        Ticket tt = new Ticket();
        ocupacional.JPA.valueobjects.Ticket tk = new ocupacional.JPA.valueobjects.Ticket();

        Pacientes paci = new Pacientes();
        if (request.getParameter("id") != null) {
            tt = new TicketsDAO(e).Cargar(request.getParameter("id"));
            tk = ttc.findTicket(Integer.parseInt(request.getParameter("id")));

            paci = pacic.findPacientes(tk.getTickPaciente());
        }
        if (tk == null) {
            tk = new ocupacional.JPA.valueobjects.Ticket();
        }
        if (paci == null) {
            paci = new Pacientes();
        }

%>

<div class="panel panel-default">
    <form   id="Form-Data" role="form" name="Form-Data"  method="POST" action="../OrdenServicio" autocomplete="off">

        <div class="panel-heading">
            <div class="row">
                <div class="col-md-6">
                    <h2 class="text-success">Modificar orden de servicio</h2>
                </div>
                <div class="form-group col-md-4">
                    <label class="control-label" >Estado</label>  
                    <select  name="tick_estado" id="tick_estado" class="form-control" >
                        <option value="POR PROCESAR">POR PROCESAR</option>
                        <option value="PROCESANDO">PROCESANDO</option>
                        <option value="PROCESADO">PROCESADO</option>
                        <option value="FACTURADO">FACTURADO</option>
                        <option value="ANULADO">ANULADO</option>
                    </select>
                    <span class="help-block"></span>
                </div>
                <script type="text/javascript">
                    $('#tick_estado').val('<%=tk.getTickEstado()%>');
                </script>
            </div>
        </div>
        <div class="panel-body">
            <div class="row">


                <%                if (true) {

                %>

                <fieldset  style="padding: 10px">
                    <legend>Datos Basicos</legend>

                    <div class="row" id="pacientes_container"> 

                        <div id="p1">

                            <div class="form-group col-md-3">

                                <label class="control-label" for="paci_documento1">No. doc.*</label>
                                <input oninput="peticionAjax('../OrdenServicio','action=valPaciente&doc='+this.value +'&caja_id=f1');" id="paci_documento1" name="paci_documento" placeholder=""  class="form-control " type="number" required  value="<%=pc.notEmpty(paci.getPaciDocumento())%>" >
                                <span class="help-block"></span>
                            </div>
                            <div class="form-group col-md-4">

                                <label class="control-label" for="paci_nombres1">Nombres *</label>
                                <input  id="paci_nombres1" name="paci_nombres" placeholder=""  class="form-control " type="text" required  value="<%=pc.notEmpty(paci.getPaciNombres())%>" >
                                <span class="help-block"></span>
                            </div>
                            <div class="form-group col-md-4">

                                <label class="control-label" for="paci_apellidos1">Apellidos *</label>
                                <input  id="paci_apellidos1" name="paci_apellidos" placeholder=""  class="form-control " type="text" required  value="<%=pc.notEmpty(paci.getPaciApellidos())%>" >
                                <span class="help-block"></span>
                            </div>
                            <div class="form-group col-md-1">
                                <div  style="margin-top: 25px">
                                    <%if (tk.getTickId() == null) {%>
                                    <button title="Agregar persona(se creara una orden de servicio identica para cada persona)" onclick="addPersona()" type="button" class="btn btn-circle btn-success"><i class="glyphicon glyphicon-user"></i></button>
                                        <%}%>
                                </div>
                            </div>
                        </div>


                    </div>


                    <div class="row ">

                        <div class="form-group col-md-4">
                            <label class="control-label" >Tipo de Examen Medico*</label>  
                            <select  name="tipo_examen" id="tipo_examen" class="form-control" required onchange="tipoExaOtro(this.value)">
                                <option value="">Seleccione..</option>
                                <%
                                    System.out.println("--- 1");
                                    for (tipoExamenMedicoVO tem : new tipoExamenMedicoDAO(e).Listar()) {
                                %>
                                <option value="<%=pc.notEmpty(tem.getTeme_id())%>"><%=pc.notEmpty(tem.getTeme_descripcion())%></option>

                                <%
                                    }
                                %>
                            </select>
                            <span class="help-block"></span>
                        </div>
                            <div id="tipoExamenotroContainer">
                                 <%if(tk.getTemeId()!= null && tk.getTemeId().getTemeId().equals(7)){%>
                             <div class="form-group col-md-4">
                                            <label class="control-label" for="tipoExamenotro">Tipo examen otro*</label>
                                            <input  id="tipoExamenotro" name="tipoExamenotro" placeholder=""  class="form-control " type="text" required value="<%=pc.notEmpty(tk.getTickOtroexamen()) %>" >
                                            <span class="help-block"></span>
                                            </div>
                            <%}%>
                        </div>
                        <script type="text/javascript">
                            function tipoExaOtro(val) {
                                if (val === '7') {
                                    var x = ' <div class="form-group col-md-4">' +
                                            '<label class="control-label" for="tipoExamenotro">Tipo examen otro*</label>' +
                                            '<input  id="tipoExamenotro" name="tipoExamenotro" placeholder=""  class="form-control " type="text" required >' +
                                            '<span class="help-block"></span>' +
                                            '</div>';
                                    $('#tipoExamenotroContainer').html(x);

//    $('').bootstrapValidator('resetForm', true);
                                    $f = $("#Form-Data");
                                    $f.bootstrapValidator('destroy');
                                    ValidarFormID();

                                } else {
                                    $('#tipoExamenotroContainer').html("");
                                    $f = $("#Form-Data");
                                    $f.bootstrapValidator('destroy');
                                    ValidarFormID();
//                                            $('#Form-Data').bootstrapValidator('resetForm', true);

                                }


                            }

                        </script>
                        <div class="form-group col-md-4">
                            <label class="control-label" >Sede/finca*</label>  
                            <select  name="centro_costo" id="centro_costo" class="form-control" required onchange="">
                                <option value="">Seleccione..</option>
                                <%
                                    System.out.println("--- 2");
                                    for (Centrocostos cc : c.getCentrocostosList()) {
                                        if (cc.getCecoEstado().equals("ACTIVO")) {
                                %>
                                <option value="<%=cc.getCecoId()%>"><%=cc.getCecoObservacion()%></option>


                                <%}
                                    }
                                %>
                            </select>
                            <span class="help-block"></span>
                        </div>
                        <div class="form-group col-md-4">
                            <label class="control-label" for="sedefinca_sel" >Centro de costo*</label>  
                            <select  name="sedefinca_sel" id="sedefinca_sel" class="form-control" required onchange="">
                                <option value="">Seleccione..</option>
                                <%
                                    System.out.println("--- 3");
                                    for (ClienteSedes s : c.getClienteSedesList()) {

                                %>
                                <option value="<%=s.getClsedDescripcion()%>" ><%=s.getClsedDescripcion()%></option>


                                <% }
                                %>
                            </select>
                            <span class="help-block"></span>
                        </div>
                        <div class="form-group col-md-4">
                            <label class="control-label" for="sede_sel" >Sede Javap*</label>  
                            <select  name="sede_sel" id="sede_sel" class="form-control" required onchange="tipoBrigada(this.value)">
                                <option value="">Seleccione..</option>
                                <%
                                    System.out.println("--- 3");
                                    for (Sede s : new SedeJpaController(emf2).findSedeEntities()) {

                                %>
                                <option value="<%=s.getSedeId()%>" <%if (s.getSedeId() == Integer.parseInt(e.getUsuarioVO().getSede_id())) {%>selected="" <%}%>  ><%=s.getSedeNombre()%></option>


                                <%}
                                %>
                            </select>
                            <span class="help-block"></span>
                        </div>
                                 <div id="nombreBrigadaContainer">
                                 <%if(tk.getSedeId()!=null && tk.getSedeId().getSedeId().equals(4)){%>
                             <div class="form-group col-md-4">
                                            <label class="control-label" for="nombreBrigada">Nombre brigada*</label>
                                            <input  id="nombreBrigada" name="nombreBrigada" placeholder=""  class="form-control " type="text" required value="<%=pc.notEmpty(tk.getTickBrigada()) %>" >
                                            <span class="help-block"></span>
                                            </div>
                            <%}%>
                        </div>
                        <script type="text/javascript">
                            function tipoBrigada(val) {
                                if (val === '4') {
                                    var x = ' <div class="form-group col-md-4">' +
                                            '<label class="control-label" for="nombreBrigada">Nombre brigada*</label>' +
                                            '<input  id="nombreBrigada" name="nombreBrigada" placeholder=""  class="form-control " type="text" required >' +
                                            '<span class="help-block"></span>' +
                                            '</div>';
                                    $('#nombreBrigadaContainer').html(x);

//    $('').bootstrapValidator('resetForm', true);
                                    $f = $("#Form-Data");
                                    $f.bootstrapValidator('destroy');
                                    ValidarFormID();

                                } else {
                                    $('#nombreBrigadaContainer').html("");
                                    $f = $("#Form-Data");
                                    $f.bootstrapValidator('destroy');
                                    ValidarFormID();
//                                            $('#Form-Data').bootstrapValidator('resetForm', true);

                                }


                            }

                        </script>
                    </div>
                </fieldset>
                <script type="text/javascript">

                    $("#centro_costo").val(<%=pc.notEmpty(tk.getCecoId().getCecoId().toString())%>);
                    $("#tipo_examen").val(<%=pc.notEmpty(tk.getTemeId().getTemeId().toString())%>);
                    $("#sede_sel").val(<%=pc.notEmpty(tk.getSedeId().getSedeId().toString())%>);
                    $("#sedefinca_sel").val('<%=pc.notEmpty(tk.getTickClsede())%>');</script>
                <fieldset >
                    <legend>  <a class="" data-toggle="collapse">Servicios a prestar</a></legend>
                    <div class="row" id="cajaServicios">
                        <%
                            System.out.println("aqui");
                            List<ClientesServicio> listaClientesServiciosVO = c.getClientesServicioList();
                            System.out.println("aqui 2");

                        %>
                        <table class="table table-hover" id="tablaServicios"> 
                            <thead >
                                <tr>
                                    <th>Servicio</th>
                                    <th></th>

                                </tr>


                            </thead>
                            <tbody>
                                <%     ArrayList<TicketClienteServicio> listaTCServicio = (ArrayList<TicketClienteServicio>) session.getAttribute("listaTCServicio");
                                    if (tk.getTickId().toString() != null) {
                                        listaTCServicio = new TicketClienteServicioDAO(e).ListarxTicket(tk.getTickId().toString());
                                        session.setAttribute("listaTCServicio", listaTCServicio);
                                    }
                                    if (listaTCServicio != null) {
                                        ClientesServicioJpaController csdao = new ClientesServicioJpaController(emf2);
                                        for (TicketClienteServicio tcs : listaTCServicio) {
                                            String s = csdao.findClientesServicio(Integer.parseInt(tcs.getClse_id())).getServId().getServNombre();
    //                                for(ClientesServicio cs: listaClientesServiciosVO){
                                            //                                if(cs.getClseId().toString().equals(tcs.getClse_id()))s=cs.getServId().getServNombre();}

                                %>
                                <tr>
                                    <th><%=s%></th>
                                    <th>
                                        <button type="button" value="<%= tcs.hashCode()%>"  onclick="eliminarRegistro('../OrdenServicio', 'action=eliminartcs&id_ocu=<%=c.getClieId()%>&id=' + this.value)" class="btn-circle btn-danger bottom-right btn-outline"><i class="glyphicon glyphicon-remove"></i> </button>

                                    </th>
                                </tr>
                                <%
                                        }
                                    }
                                %>


                            </tbody>

                        </table>


                    </div>



                    <div class="row  "  >
                        <div class="form-group col-md-8">
                            <label class="control-label" for="Servicio_sel">Servicio</label>  
                            <select id="Servicio_sel" name="Servicio_sel" class="form-control" >
                                <option value="">Seleccione..</option>
                                <%
                                    for (ClientesServicio cs : listaClientesServiciosVO) {
                                        if (cs.getClseEstado().equals("ACTIVO")) {
                                %>
                                <option value="<%=cs.getClseId()%>"><%=cs.getServId().getServNombre()%></option>

                                <%

                                        }
                                    }
                                %>
                            </select>
                            <span class="help-block"></span>
                        </div>

                        <div class="form-group col-md-4">
                            <input   onclick="peticionAjax('../OrdenServicio', 'id_ocu=<%=c.getClieId()%>&action=addServicio&Servicio_sel=' + $('#Servicio_sel').val());
                                    $f = $('#Form-Data');
                                    $f.bootstrapValidator('destroy');
                                    ValidarFormID();"  name=""  type="button"  value="Agregar" class="btn btn-outline btn-info" style="margin-top: 25px">

                            <span class="help-block"></span>
                        </div>
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
                        <% if (e.getUsuarioVO().getUsua_tipo().equals("C")) {%>
                        <button  onclick="RecargaPanel('../panels/formularios/factura/OrdenServiciomdf.jsp', 'panelprincipal')" type="button" class="btn btn-default bottom-right btn-outline">Atras </button>
                        <%} else {%>
                        <button  onclick="RecargaPanel('../panels/formularios/factura/OrdenServiciomdf.jsp', 'panelprincipal')" type="button" class="btn btn-default bottom-right btn-outline">Atras </button>
                        <%}%>
                        <input type="hidden" name="id" value="<%=pc.notEmpty(tk.getTickId().toString())%>">
                        <input type="hidden" name="id_ocu" value="<%=c.getClieId()%>">
                        <input type="hidden" name="action" value="ModificarOrdenServicio">
                        <button  onclick="
                                alertify.confirm('¿Está seguro de realizar esta acción?', function (e) {
                                    if (e) {
                                        $('#Form-Data').submit();

                                    }


                                })
                                 " type="button" class="btn btn-success bottom-right btn-outline">Guardar</button>

                    </div>
                </div>
            </div>


        </div>
    </form>
</div>


<script>
    $(document).ready(function () {
        ValidarFormID();
//        $('.datepicker').datepicker();
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