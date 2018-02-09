<%-- 
    Document   : cuentasClienteVer
    Created on : 2/06/2016, 09:14:20 PM
    Author     : D4V3
--%>
<%@page import="ocupacional.JPA.valueobjects.Ticket"%>
<%@page import="ocupacional.valueobjects.facturacion.FechasCuentasCliente"%>
<%@page import="javax.persistence.EntityManager"%>
<%@page import="javax.persistence.TypedQuery"%>
<%@page import="ocupacional.JPA.valueobjects.TicketClienteservicio"%>
<%@page import="ocupacional.JPA.valueobjects.Centrocostos"%>
<%@page import="ocupacional.JPA.valueobjects.Clientes"%>
<%@page import="formularios.entidades.Pacientes"%>
<%@page import="formularios.controlers.PacientesJpaController"%>
<%@page import="javax.persistence.Persistence"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<%-- 
    Document   : ordenServicio
    Created on : 11/05/2016, 09:38:53 PM
    Author     : D4V3
--%>


<%@page import="ocupacional.bdatos.historiaclinica.tipoExamenMedicoDAO"%>
<%@page import="ocupacional.bdatos.facturacion.CentroCostosDAO"%>
<%@page import="ocupacional.valueobjects.facturacion.ClientesServiciosVO"%>
<%@page import="valeria.metodos.ManejadorFechas"%>
<%@page import="valeria.metodos.ProcesaCaracEspeciales"%>
<%@page import="ocupacional.bdatos.facturacion.ClientesServiciosDAO"%>
<%@page import="java.util.List"%>
<%@page import="ocupacional.bdatos.facturacion.TicketsDAO"%>
<%@page import="ocupacional.bdatos.facturacion.ClientesDAO"%>
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


        Clientes ClienteVO = (Clientes) session.getAttribute("ClienteVO");

        if (ClienteVO.getClieId() != null) {
            FechasCuentasCliente fcu = (FechasCuentasCliente) session.getAttribute("fechasCuentasClienteFacturacion");

%>

<div class="panel panel-default">

    <div class="panel-heading">
        <div class="row">
            <div class="col-lg-9">
                <h3 class="text-success">Ordenes de servicio procesadas: <br /><%=ClienteVO.getClieNombre().toUpperCase()%></h3>
            </div>


        </div>
    </div>
    <div class="panel-body">
        <div class="row">
        
          
            


            <fieldset id="caja">
                <legend> <small>Desde:<%=f.DevuelveFormato(fcu.getFechaInicio()) %>
          Hasta: <%=f.DevuelveFormato(fcu.getFechaFin()) %></small> </legend>
                <!-- Table -->
                <div class="row">
                    <div class="col-md-4 ">
                        <label for="tipoReporte_sel" class="control-label"> Tipo de reporte</label> 
                        <select name="tipoReporte_sel" id="tipoReporte_sel" class="form-control">
                           <option value="">Cuadro Ordenes procesadas</option>
                           <option value="1">Cuadro Ordenes tipo TK7</option>
                           <option value="2">Cuadro Ordenes tipo Adecco</option>
                       </select>
                    </div>
                    <div class="col-md-2 " style="margin-top: 25px">
                        
                        <button type="button" class="btn btn-success" onclick="
                            if($('#tipoReporte_sel').val()==='2'){
                                window.open('reportes/reportExcel/ordenes_servicio_x_empresa_vertical?sede=<%=fcu.getSede().getSedeId() %>&clie=<%=ClienteVO.getClieId()%>&fini=<%=f.DevuelveFormato(fcu.getFechaInicio())%>&ffin=<%=f.DevuelveFormato(fcu.getFechaFin()) %>');
                                
                            }else{
                                window.open('reportes/reportExcel/ordenes_servicio_x_empresa?sede=<%=fcu.getSede().getSedeId() %>&clie=<%=ClienteVO.getClieId()%>&fini=<%=f.DevuelveFormato(fcu.getFechaInicio())%>&ffin=<%=f.DevuelveFormato(fcu.getFechaFin()) %>&tipo='+$('#tipoReporte_sel').val());
                        }
                                ">Generar Excel</button>
                        
                    </div>
                    <div class="col-md-2 " style="margin-top: 25px">
                        
                        <button type="button" class="btn btn-success" onclick="
                        RecargaPanel('../panels/formularios/factura/cuentasClienteGenerarxSede.jsp', 'panelprincipal')
                                ">Generar Excel x Sede/finca</button>
                        
                    </div>
                   
                    
                </div>
                <br />
                
                
                <table id="tabla1" class="table table-hover ">
                    <thead>
                        <tr>
                            <th>#</th>
                           <th>SEDE-FINCA</th>
                            <th>DOCUMENTO</th>
                            <th>PACIENTE</th>
                            <th>TIPO</th>
                            <th>SERVICIOS</th>
                            <th>F. DE REGISTRO</th>
                            <th>F. DE FINALIZACION</th>
                            <th>ACCIONES</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            int total = 0;
                            
                              EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaP");
        
//        tiped query
          EntityManager em = emf.createEntityManager();
                    TypedQuery<Ticket> contickProcesados = em.createNamedQuery("Ticket.findByFechasSedeJavap", Ticket.class);
                    contickProcesados.setParameter("clieId",ClienteVO.getClieId());
                    contickProcesados.setParameter("sede", fcu.getSede());
                    contickProcesados.setParameter("inicio", fcu.getFechaInicio());
                    contickProcesados.setParameter("fin", fcu.getFechaFin());
                    List<Ticket> lista = contickProcesados.getResultList();
                   
                    for (Ticket t : lista) {
                       
        
                
                            
                       
                                        EntityManagerFactory emf2 = Persistence.createEntityManagerFactory("JavaP1PU");
                                        PacientesJpaController pacic = new PacientesJpaController(emf2);

                                        Pacientes paci = pacic.findPacientes(t.getTickPaciente());
                                        if (paci == null) {
                                            paci = new Pacientes();
                                        }


                        %>
                        <tr>
                         <td><%=t.getTickId()%></td>
                            <td><%=t.getCecoId().getCecoObservacion()%></td>
                            <td><%=paci.getPaciDocumento()%></td>
                            <td><%=pc.notEmpty(paci.getPaciNombres() + " " + paci.getPaciApellidos())%></td>

                            <td><%=t.getTemeId().getTemeDescripcion()%></td>
                            <td>
                                <%

                                    for (TicketClienteservicio tcs : t.getTicketClienteservicioList()) {

                                        total += tcs.getClseId().getClseValor();
                                %>
                                *<%=tcs.getClseId().getServId().getServNombre().toUpperCase() + "  $" + pc.getNumber(tcs.getClseId().getClseValor()).toUpperCase()%><br/>
                                <% }%> 
                            </td>
                            <td><%=f.FechaLetrasHora(t.getTickFecharegistro())%></td>
                            <td><%=f.FechaLetrasHora(t.getTickFechaprocesado())%></td>                            
                            <td>
                                <button type="button" value="<%= t.getTickId()%>" class="btn-circle btn-primary  bottom-right btn-outline" title="Editar sede-cliente" onclick="RecargaPanel('../panels/formularios/factura/cuentasCliente_cambiarCentroCosto.jsp?id=<%= t.getTickId()%>', 'panelprincipal')"><i class="glyphicon glyphicon-edit"></i> </button>
                            </td>
                        </tr>
                        <%   }%>
                    </tbody>

               


                </table>
                    <legend style="font-size: 14px">
                        
                                <span style="float: left ; font-weight: bold">TOTAL PROCESADO:</span>
                                <span style="float: right; font-weight: bold">
                                    $<%=pc.getNumber(total)%>
                                </span>
                    </legend>
                        




            </fieldset>







            <!-- /.col-lg-6 (nested) -->

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
                    <!--<button type="button" class="btn btn-default bottom-right btn-outline">Cancelar </button>-->
                    <button  onclick="RecargaPanel('../panels/formularios/factura/cuentasCliente.jsp', 'panelprincipal')" type="button" class="btn btn-default bottom-right btn-outline">Atras </button>
                    <button  onclick="RecargaPanel('../panels/formularios/factura/cuentasCliente_facturar.jsp', 'panelprincipal')" type="button" class="btn btn-success bottom-right ">Facturar cliente </button>

                </div>
            </div>
        </div>


    </div>

</div>



<script data-config>
    var filtersConfig = {
        base_path: '../tablefilter/',
        col_0: 'none',
//        //        col_1: 'select',
        col_8: 'text',
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

    var tf = new TableFilter('tabla1', filtersConfig);


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
<% em.close(); } else {
%>Usted no es cliente<%
    }
}else{%>
<script type="text/javascript">
    location.href='../logout.jsp';
</script>
<%}%>