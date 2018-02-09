<%-- 
    Document   : cuentasClienteVer
    Created on : 2/06/2016, 09:14:20 PM
    Author     : D4V3
--%>
<%@page import="javax.persistence.Query"%>
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
                <h3 class="text-success">Sedes/finca de: <br /><%=ClienteVO.getClieNombre().toUpperCase()%></h3>
            </div>


        </div>
    </div>
    <div class="panel-body">
        <div class="row">





            <fieldset id="caja">
                <legend> <small>Desde:<%=f.DevuelveFormato(fcu.getFechaInicio())%>
                        Hasta: <%=f.DevuelveFormato(fcu.getFechaFin())%></small> </legend>
                <!-- Table -->

                <br />


                <table id="tabla1" class="table table-hover ">
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>SEDE-FINCA</th>
                            <th>TICKETS</th>
                            <th>ACCIONES</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            int total = 0;
                            int count = 0;

                            EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaP");
//        tiped query
                            EntityManager em = emf.createEntityManager();

                            Query cons = em.createNativeQuery(
                                    "select cc.ceco_id, "
                                    + "cc.ceco_observacion, "
                                    + "count(tick.tick_id) "
                                    + " from "
                                    + "ticket tick "
                                    + "inner join centrocostos cc on cc.ceco_id = tick.ceco_id "
                                    + "where tick.tick_estado = 'PROCESADO' "
                                    + "and cc.clie_id =? "
                                    + "and tick.sede_id = ? "
                                    + "and tick.tick_fechaprocesado BETWEEN ? AND ? "
                                    + "group by cc.ceco_id "
                                    + "order by 2");

                            cons.setParameter(1, ClienteVO.getClieId());
                            cons.setParameter(2, fcu.getSede().getSedeId());
                            cons.setParameter(3, f.DevuelveFormato(fcu.getFechaInicio(), "yyyy-MM-dd"));
                            cons.setParameter(4, f.DevuelveFormato(fcu.getFechaFin(), "yyyy-MM-dd HH:mm:ss"));
                            List<Object[]> lisSedes = cons.getResultList();
                            if (lisSedes != null) {
                                for (Object[] sede : lisSedes) {
                                       total += Integer.parseInt(sede[2].toString());

                        %>
                        <tr>
                            <td><%=++count%></td>
                            <td><%=sede[1]%></td>
                            <td><%=sede[2]%></td>
                            <td>
                                   <button type="button" class="btn btn-success" onclick="
                                window.open('reportes/reportExcel/ordenes_servicio_x_empresa_vertical_sede?sede=<%=fcu.getSede().getSedeId() %>&cc=<%=sede[0]%>&fini=<%=f.DevuelveFormato(fcu.getFechaInicio())%>&ffin=<%=f.DevuelveFormato(fcu.getFechaFin()) %>');
                                ">Generar Excel</button>
                            </td>
                        </tr>
                        <%   }
                            }%>
                    </tbody>




                </table>
                <legend style="font-size: 14px">

                    <span style="float: left ; font-weight: bold">TOTAL ORDENES:</span>
                    <span style="float: right; font-weight: bold">
                        <%=pc.getNumber(total)%>
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
                    <button  onclick="RecargaPanel('../panels/formularios/factura/cuentasClienteVer.jsp', 'panelprincipal')" type="button" class="btn btn-default bottom-right btn-outline">Atras </button>

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
<% em.close();
} else {
%>Usted no es cliente<%
    }
} else {%>
<script type="text/javascript">
    location.href = '../logout.jsp';
</script>
<%}%>