<%-- 
    Document   : ordenServicio
    Created on : 11/05/2016, 09:38:53 PM
    Author     : D4V3
--%>

<%@page import="java.util.Collections"%>
<%@page import="ocupacional.JPA.valueobjects.ClientesServicio"%>
<%@page import="ocupacional.JPA.valueobjects.TicketClienteservicio"%>
<%@page import="ocupacional.JPA.valueobjects.Ticket"%>
<%@page import="ocupacional.JPA.valueobjects.Centrocostos"%>
<%@page import="ocupacional.JPA.valueobjects.Clientes"%>
<%@page import="ocupacional.JPA.controlers.ClientesJpaController"%>
<%@page import="formularios.entidades.Pacientes"%>
<%@page import="javax.persistence.Persistence"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<%@page import="formularios.controlers.PacientesJpaController"%>
<%@page import="ocupacional.valueobjects.facturacion.ClientesVO"%>
<%@page import="ocupacional.bdatos.historiaclinica.tipoExamenMedicoDAO"%>
<%@page import="ocupacional.bdatos.facturacion.CentroCostosDAO"%>
<%@page import="ocupacional.valueobjects.facturacion.ClientesServiciosVO"%>
<%@page import="valeria.metodos.ManejadorFechas"%>
<%@page import="valeria.metodos.ProcesaCaracEspeciales"%>
<%@page import="java.util.List"%>
<%@page import="ocupacional.bdatos.RolFuncionalidadDAO"%>
<%@page import="ocupacional.valueobjects.RolFuncionalidadVOs"%>
<%@page import="valeria.metodos.Cadenas"%>

<%@page import="java.util.ArrayList"%>
<%@page import="valeria.response.Mediador"%>
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

        RolFuncionalidadVOs rf = new RolFuncionalidadDAO(e).Cargar(idf);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaP1PU");
        EntityManagerFactory emf2 = Persistence.createEntityManagerFactory("JavaP");

        ClientesJpaController ClientesDAO = new ClientesJpaController(emf2);
        Clientes ClienteVO = new Clientes();
        if (request.getParameter("clie_ocu") != null) {

            ClienteVO = ClientesDAO.findClientes(Integer.parseInt(request.getParameter("clie_ocu")));

        } else {
            if (e.getUsuarioVO().getUsua_tipo().equals("C")) {
                ClienteVO = ClientesDAO.findClientes(Integer.parseInt(e.getUsuarioVO().getIdpersona()));
//      session.setAttribute("ClienteVO", ClienteVO);
            } else {

                System.out.println("entre aqui");
            }
        }

        if (ClienteVO.getClieId() != null) {
%>

<div class="panel panel-default">

    <div class="panel-heading">
        <div class="row">
            <div class="col-lg-9">
                <h2 class="text-success">Ordenes de servicio</h2>
            </div>


        </div>
    </div>
    <div class="panel-body">
        <div class="row">

            <fieldset>
                <legend>Cuadro de busqueda</legend>
                <div class="form-group col-md-3">
                    <label class="control-label" for="doc"># Documento*</label>
                    <input  id="doc" name="doc" placeholder="Numero de documento"  class="form-control " type="number" required  >
                    <span class="help-block"></span>
                </div>
                 <div class="form-group col-md-3">
                    <label class="control-label" for="doc"> Nombres*</label>
                    <input  id="nombre" name="nombre" placeholder="Nombre"  class="form-control " type="text" required  >
                    <span class="help-block"></span>
                </div>
                <div class="col-md-3">
                    <button onclick="peticionAjaxSinValidar('../OrdenServicio', 'action=buscarTiketEmpresa&clie_ocu=<%=request.getParameter("clie_ocu")%>&doc=' + $('#doc').val()+'&nombre=' + $('#nombre').val())" class="btn btn-circle btn-primary" type="button" style="margin-top:  25px" > <i class="glyphicon glyphicon-search"></i></button>

                </div>


            </fieldset>


            <fieldset id="cajaTicketsEmpresa">
                <legend></legend>
                <!-- Table -->
                <table id="tablaTicketsEmpresa" class="table table-hover ">
                    <thead>
                        <tr>
                            <th>#</th>
                            <th title="Sede de la empresa cliente ">SEDE/FINCA</th>
                            <th>N° DOCUMENTO</th>
                            <th>NOMBRES</th>
                            <th>TIPO</th>
<!--                            <th>SERVICIOS</th>-->
                            <th>F. DE REGISTRO</th>
                            <th>ESTADO</th>
                            <th>ACCIONES</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            
                            
                                List<Object[]> listaTickets = (List)session.getAttribute("listaTiketEmpresa");

                                
                                if(listaTickets != null && !listaTickets.isEmpty()){
                                for (Object[] t : listaTickets) {
                            

                        %>
                        <tr>
                            <td><%=String.format("%06d", t[0])%></td>
                            <td><%=t[1].toString() %></td>
                            <td><%=t[2].toString()%></td>
                            <td><%=t[3].toString()%></td>
                            <td><%=t[4].toString()%></td>
                            <td><%=t[5].toString()%></td>
                            <td><%=t[6].toString()%></td>
                            <td>
                                <button type="button" value="<%=t[0].toString() %>" class="btn-circle btn-success  bottom-right btn-outline" onclick="RecargaPanel('../panels/formularios/factura/ordenServicio_ver.jsp?id=<%= t[0].toString()%>', 'panelprincipal')"><i class="glyphicon glyphicon-search"></i> </button>


                                <%if (t[6].toString().equalsIgnoreCase("POR PROCESAR")) {%>    <button type="button" class="btn-circle btn-default  bottom-right btn-outline"  value="<%= t[0].toString()%>" onclick="peticionAjax('../OrdenServicio', 'action=eliminarSSession&id_ocu=<%=ClienteVO.getClieId()%>&id=<%= t[0].toString()%>');"><i class="glyphicon glyphicon-pencil"></i> </button><% }%>
                                <%if (t[6].toString().equalsIgnoreCase("POR PROCESAR")) {%>   <button type="button" value="<%= t[0].toString()%>"  onclick="eliminarRegistro('../OrdenServicio', 'action=eliminarTicket&id_ocu=<%=ClienteVO.getClieId()%>&id=' + this.value)" class="btn-circle btn-danger bottom-right btn-outline"><i class="glyphicon glyphicon-remove"></i> </button><% }%></td>
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


            <div class="col-md-6 right">

            </div>
            <div class="col-md-6 right">
                <% if (e.getUsuarioVO().getUsua_tipo().equals("C")) {%>
                <%} else {%>
                <button  onclick="RecargaPanel('../panels/formularios/factura/OrdenServicioInterna_ini.jsp?idf=<%=idf%>', 'panelprincipal')" type="button" class="btn btn-default bottom-right btn-outline">Atras </button>
                <%}%>

                <button  onclick="peticionAjax('../OrdenServicio', 'action=eliminarSSession&id_ocu=<%=ClienteVO.getClieId()%>');" type="button" class="btn btn-success bottom-right btn-outline">Nueva orden</button>


            </div>
        </div>


    </div>

</div>

<script data-config>
//    var filtersConfig = {
//        base_path: '../tablefilter/',
//        col_1: 'select',
//        col_4: 'select',
//        col_7: 'select',
//        col_8: 'none',
//        alternate_rows: true,
//        btn_reset: true,
//        paging: true,
//        results_per_page: ['Resultados por Pag.', [10, 25, 50, 100]],
//        rows_counter: true,
//        loader: true,
//        status_bar: true,
//        mark_active_columns: true,
//        highlight_keywords: true,
//        extensions: [{name: 'sort'}]
//    };
//
//    var tf = new TableFilter('tabla', filtersConfig);
//    tf.emitter.on(['after-filtering'], function () {
//        console.log("hola voy a filtrar");
//    });
//
//
//    tf.init();


function recargarBusqueda(){
    
    $('#cajaTicketsEmpresa').load('../panels/formularios/factura/ordenServicio.jsp?clie_ocu=<%=request.getParameter("clie_ocu")%>  #tablaTicketsEmpresa',function(){alertify.success('Procesados con exito')})
}

</script>

<script>
    $(document).ready(function () {
//        ValidarFormID();
//        $('.datepicker').datepicker();


    });
//    $(".close").click(function () {
//        $('#Form-Data').bootstrapValidator('resetForm', true);
//    });

</script>
<% } else {
%>Usted no es cliente<%
    }
} else {%>
<script type="text/javascript">
    location.href = '../logout.jsp';
</script>
<%}%>