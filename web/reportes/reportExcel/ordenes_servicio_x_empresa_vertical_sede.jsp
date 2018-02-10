<%-- 
    Document   : ordenes_servicio_x_empresa
    Created on : 2/02/2017, 07:40:18 PM
    Author     : Sebas
--%>

<%@page import="ocupacional.JPA.valueobjects.Centrocostos"%>
<%@page import="ocupacional.JPA.controlers.CentrocostosJpaController"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="ocupacional.JPA.controlers.SedeJpaController"%>
<%@page import="ocupacional.JPA.valueobjects.Sede"%>
<%@page import="java.util.AbstractList"%>
<%@page import="java.util.Iterator"%>
<%@page import="ocupacional.JPA.valueobjects.ClientesServicio"%>
<%@page import="valeria.metodos.Cadenas"%>
<%@page import="ocupacional.valueobjects.facturacion.FechasCuentasCliente"%>
<%@page import="ocupacional.JPA.controlers.EstadocivilJpaController"%>
<%@page import="valeria.metodos.Constantes"%>
<%@page import="java.util.ArrayList"%>
<%@page import="ocupacional.JPA.valueobjects.ServiciosExamenes"%>
<%@page import="formularios.controlers.CamposJpaController"%>
<%@page import="formularios.entidades.Respuestas"%>
<%@page import="ocupacional.servlets.historiaclinica.ConstantesReportes"%>
<%@page import="ocupacional.JPA.valueobjects.TicketClienteservicio"%>
<%@page import="ocupacional.valueobjects.facturacion.TicketClienteServicio"%>
<%@page import="javax.persistence.TypedQuery"%>
<%@page import="formularios.entidades.Anotaciones"%>
<%@page import="formularios.entidades.Pacientes"%>
<%@page import="formularios.controlers.PacientesJpaController"%>
<%@page import="valeria.metodos.ManejadorFechas"%>
<%@page import="ocupacional.JPA.valueobjects.Ticket"%>
<%@page import="java.util.List"%>
<%@page import="javax.persistence.EntityManager"%>
<%@page import="javax.persistence.Query"%>
<%@page import="javax.persistence.Persistence"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<%@page import="ocupacional.JPA.controlers.ClientesJpaController"%>
<%@page import="ocupacional.JPA.valueobjects.Clientes"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%!
    class conteo {

        int servicio;
        String servicioNom;
        int cont = 0;

    }


%>
<%
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaP");
    EntityManager em = emf.createEntityManager();
    ClientesJpaController clientesJpaController = new ClientesJpaController(emf);
    ManejadorFechas manejadorFechas = new ManejadorFechas();
    Cadenas o = new Cadenas();
    String fini = request.getParameter("fini");
    String ffin = request.getParameter("ffin");
    String cc = request.getParameter("cc");
    Sede sede = new SedeJpaController(emf).findSede(Integer.parseInt(request.getParameter("sede")));
//    String fini = "2018-01-02 00:00:00";
//    String ffin = "2018-01-31 23:59:59";
//    String clie = "111";
//    String clie = "102";
//    Sede sede = new SedeJpaController(emf).findSede(2);
//    String tipoReporte = request.getParameter("tipo");

    Centrocostos ccvo = new CentrocostosJpaController(emf).findCentrocostos(Integer.parseInt(cc));
    Query cons = em.createNativeQuery(
            "select "
            + "distinct(serv.serv_id),"
            + "serv.serv_nombre from "
            + "ticket tick "
            + "inner join centrocostos cc on cc.ceco_id = tick.ceco_id "
            + "inner join ticket_clienteservicio tcs on tcs.tick_id = tick.tick_id "
            + "inner join clientes_servicio cls on cls.clse_id = tcs.clse_id "
            + "inner join servicios serv on serv.serv_id = cls.serv_id "
            + "where tick.tick_estado = 'PROCESADO' "
            + "and cc.ceco_id = ? "
            + "and tick.sede_id = ? "
            + "and tick.tick_fechaprocesado BETWEEN ? AND ? "
            + "order by serv.serv_id ");

    cons.setParameter(1, ccvo.getCecoId());
    cons.setParameter(2, sede.getSedeId());
    cons.setParameter(3, manejadorFechas.DevuelveFormato(manejadorFechas.retornaTimestamp(fini, "dd-MM-yyyy"), "yyyy-MM-dd"));
    cons.setParameter(4, manejadorFechas.DevuelveFormato(manejadorFechas.retornaTimestamp(ffin + " 23:59:59", "dd-MM-yyyy HH:mm:ss"), "yyyy-MM-dd HH:mm:ss"));

    List<Object[]> lisExamenes = cons.getResultList();

    Query cons2 = em.createNativeQuery(
            "select "
            + " clie.clie_id,"
            + " clie.clie_nombre,"
            + " tick.tick_id, "
            + " tick.tick_fechaprocesado,"
            + " CONCAT(paci.paci_nombres,' ',paci.paci_apellidos) nombre,"
            + " paci.paci_documento,"
            + " TIMESTAMPDIFF(YEAR,paci.paci_fechanacimiento,CURDATE()) edad,"
            + " esci.esci_descripcion,"
            + " cc.ceco_observacion,"
            + " ("
            + " select resp.resp_descripcion "
            + " from ticket tt "
            + " inner join ticket_clienteservicio tcs on tcs.tick_id = tt.tick_id  "
            + " inner join javaphc.anotaciones anot on tcs.tics_id = anot.tics_id and anot.form_id =1 and anot.anot_estado ='FINALIZADO' "
            + " inner join javaphc.respuestas resp on anot.anot_id = resp.anot_id and resp.camp_id = 1 "
            + " where  tt.tick_id = tick.tick_id "
            + " limit 1 "
            + " ) cargo, "
            + " teme.teme_descripcion, "
            + " ( "
            + " select GROUP_CONCAT(cls.serv_id) "
            + " from ticket tt "
            + " inner join ticket_clienteservicio tcs on tcs.tick_id = tt.tick_id  "
            + " inner join clientes_servicio cls on cls.clse_id = tcs.clse_id "
            + " where  tt.tick_id = tick.tick_id "
            + " group by tt.tick_id "
            + " ) servicios, "
            + " ( "
            + " select resp.resp_descripcion "
            + " from ticket tt "
            + " inner join ticket_clienteservicio tcs on tcs.tick_id = tt.tick_id  "
            + " inner join javaphc.anotaciones anot on tcs.tics_id = anot.tics_id and anot.form_id =10 and anot.anot_estado ='FINALIZADO' "
            + " inner join javaphc.respuestas resp on anot.anot_id = resp.anot_id and resp.camp_id = 149 "
            + " where  tt.tick_id = tick.tick_id "
            + " limit 1 "
            + " ) restricciones, "
            + " ( "
            + " select resp.resp_descripcion "
            + " from ticket tt "
            + " inner join ticket_clienteservicio tcs on tcs.tick_id = tt.tick_id  "
            + " inner join javaphc.anotaciones anot on tcs.tics_id = anot.tics_id and anot.form_id =10 and anot.anot_estado ='FINALIZADO' "
            + " inner join javaphc.respuestas resp on anot.anot_id = resp.anot_id and resp.camp_id = 147 "
            + " where  tt.tick_id = tick.tick_id "
            + " limit 1 "
            + " ) recomendaciones, "
            + " ( "
            + " select resp.resp_descripcion "
            + " from ticket tt "
            + " inner join ticket_clienteservicio tcs on tcs.tick_id = tt.tick_id  "
            + " inner join javaphc.anotaciones anot on tcs.tics_id = anot.tics_id and anot.form_id =10 and anot.anot_estado ='FINALIZADO' "
            + " inner join javaphc.respuestas resp on anot.anot_id = resp.anot_id and resp.camp_id = 145 "
            + " where  tt.tick_id = tick.tick_id "
            + " limit 1 "
            + " ) diagnostico, "
            + " sede.sede_id, "
            + " sede.sede_nombre "
            + " from  "
            + " ticket tick "
            + " inner join centrocostos cc on cc.ceco_id = tick.ceco_id  "
            + " inner join clientes clie on clie.clie_id = cc.clie_id "
            + " inner join javaphc.pacientes paci on paci.paci_id = tick.tick_paciente "
            + " inner join estadocivil esci on paci.paci_ecivil = esci.esci_id "
            + " inner join tipoexamen_medico teme on teme.teme_id = tick.teme_id "
            + " inner join sede sede on sede.sede_id = tick.sede_id "
            + " where "
            + " tick.tick_estado = 'PROCESADO' "
            + " and cc.ceco_id = ? "
            + " and tick.sede_id = ? "
            + " and "
            + " tick.tick_fechaprocesado BETWEEN ? AND ?");

    cons2.setParameter(1, ccvo.getCecoId());
    cons2.setParameter(2, sede.getSedeId());
    cons2.setParameter(3, manejadorFechas.DevuelveFormato(manejadorFechas.retornaTimestamp(fini, "dd-MM-yyyy"), "yyyy-MM-dd"));
    cons2.setParameter(4, manejadorFechas.DevuelveFormato(manejadorFechas.retornaTimestamp(ffin + " 23:59:59", "dd-MM-yyyy HH:mm:ss"), "yyyy-MM-dd HH:mm:ss"));
    List<Object[]> lisTickets = cons2.getResultList();

    String filename = ccvo.getClieId().getClieNombre() + "-" + ccvo.getCecoObservacion() + "" + fini + " - " + ffin;
//    String filename = String.valueOf(lisTickets.size());
//    response.setContentType("application/vnd.ms-excel");
//    response.setHeader("Content-Disposition", "inline; filename=" + filename + ".xls");
    List<conteo> examenesConteo = new ArrayList();

    Map<String, conteo> mapaConteo = new HashMap();


%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=filename%></title>
        <style type="text/css">
            body{

                font-size: 9px; 
            }
            table
            {
                font-size: 9px; 
                table-layout: fixed;

            }

            td
            {
                border: 1px solid black;
                word-wrap: break-word;

            }
            th
            {

                border: 2px solid black; 
                word-wrap: break-word;

            }
            a{
                font-size: 16px;
            }
        </style>
    </head>
    <body>

        <a href="#" id="test" onClick="javascript:fnExcelReport();">DESCARGAR</a>
        <br /><br />
        <table id="myTable"  border="0" style="table-layout: fixed;" >
            <thead>
                <tr>
                    <th style="text-align: center" colspan="<%=14 + lisExamenes.size()%>">

                        REPORTE DE ORDENES PROCESADAS PARA  <%= ccvo.getClieId().getClieNombre() + "-" + ccvo.getCecoObservacion()%> ENTRE <%=fini%> y <%=ffin%>

                    </th>
                </tr>
                <tr>
                    <th style="text-align: center" colspan="<%=9%>">

                    </th>
                    <th style="text-align: center ; width: 100px" colspan="<%=lisExamenes.size()%>">
                        PARACLINICOS
                    </th>
                    <th style="text-align: center" colspan="<%=5%>">

                    </th>

                </tr>
                <tr>
                    <th style="width: 40px">CODIGO</th>
                    <th style="width: 50px">FECHA</th>
                    <th style="width: 80px">NOMBRE</th>
                    <th style="width: 100px">CEDULA</th>
                    <th style="width: 20px">EDAD</th>
                    <th style="width: 40px">E. CIVIL</th>
                    <th style="width: 40px">SEDE/FINCA</th>
                    <th style="width: 40px">CARGO</th>
                    <th style="width: 40px">TIPO EXAMEN</th>
                        <%
                            if (lisExamenes != null) {
                                for (Object[] exa : lisExamenes) {
                        %>
                    <th style="width: 20px"><%=exa[1] != null ? exa[1] : ""%></th>

                    <%}
                        }
                    %>

                    <th style="width: 30px">CONDICIONADOS</th>
                    <th style="width: 80px">RESTRICCIONES</th>
                    <th style="width: 100px">RECOMENDACIONES</th>
                    <th style="width: 100px">DIAGNOSTICO</th>
                    <th style="width: 30px">LUGAR</th>
                </tr>

            </thead>
            <tbody>
                <%
                    if (lisTickets != null)
                        for (Object[] tick : lisTickets) {%>

                <tr>
                    <td><%=tick[2] != null ? tick[2] : ""%></td>
                    <td><%=tick[3] != null ? tick[3] : ""%></td>
                    <td><%=tick[4] != null ? tick[4] : ""%></td>
                    <td><%=tick[5] != null ? tick[5] : ""%></td>
                    <td><%=tick[6] != null ? tick[6] : ""%></td>
                    <td><%=tick[7] != null ? tick[7] : ""%></td>
                    <td><%=tick[8] != null ? tick[8] : ""%></td>
                    <td><%=tick[9] != null ? tick[9] : ""%></td>
                    <td><%=tick[10] != null ? tick[10] : ""%></td>
                    <!--paraclinicos-->
                    <%
                        if (lisExamenes != null) {
                            String examenes = tick[11] != null ? (String) tick[11] : "";
                            String[] lexa = examenes.split(",");
                            for (Object[] exa : lisExamenes) {
                                String esta = "";
                                for (String e : lexa) {
                                    if (e.equals(exa[0].toString())) {
                                        esta = "1";
                                        if (!mapaConteo.containsKey(e)) {
                                            conteo c = new conteo();
                                            c.servicioNom = exa[1].toString();
                                            c.cont = 0;
                                            mapaConteo.put(e, c);
                                        }
                                        mapaConteo.get(e).cont++;
                                    }
                                }
                    %>
                    <td style="width: 20px"><%=esta%></td>

                    <%}
                        }
                    %>
                    <!--condicionados-->
                    <td> 
                        <%  if (tick[12] == null || tick[12].equals("")) {

                                out.println("No");

                            } else {
                                String restric = (String) tick[12];
                                if (restric.trim().equalsIgnoreCase("NO") || restric.trim().equalsIgnoreCase("ninguna") || restric.trim().equalsIgnoreCase("ninguno")) {

                                    out.println("No");
                                } else {
                                    out.println("Si");
                                }

                            }%>
                    </td>
                    <td><%=tick[12] != null ? tick[12] : ""%></td>
                    <td><%=tick[13] != null ? tick[13] : ""%></td>
                    <td><%=tick[14] != null ? tick[14] : ""%></td>
                    <td><%=tick[16] != null ? tick[16] : ""%></td>
                </tr>


                <%}
                %>
                <tr></tr>
            </tbody>
            <tfoot>
                <tr>
                    <th colspan="6">DESCRIPCION</th>
                    <th colspan="3">CANTIDAD</th>
                    <!--                    <th colspan="3">VALOR U.</th>
                                        <th colspan="3">TOTAL</th></tr>-->

                    <%  for (Map.Entry<String, conteo> entry : mapaConteo.entrySet()) {
                            conteo cs = entry.getValue();
                    %> 
                <tr>
                    <td colspan="6"><%=cs.servicioNom%></td>
                    <td colspan="3"><%=cs.cont%></td>

                </tr>
                <%}%>
            </tfoot>
        </table>


    </body>

    <script src="../../../js/jquery-2.1.4.js" ></script>
    <script type="text/javascript">
            function fnExcelReport() {
                var tab_text = '<html xmlns:x="urn:schemas-microsoft-com:office:excel">';
                tab_text = tab_text + '<head><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet>';

                tab_text = tab_text + '<x:Name>Test Sheet</x:Name>';

                tab_text = tab_text + '<x:WorksheetOptions><x:Panes></x:Panes></x:WorksheetOptions></x:ExcelWorksheet>';
                tab_text = tab_text + '</x:ExcelWorksheets></x:ExcelWorkbook></xml></head><body>';

                tab_text = tab_text + "<table border='1px'>";
                tab_text = tab_text + $('#myTable').html();
                tab_text = tab_text + '</table></body></html>';

                var data_type = 'data:application/vnd.ms-excel';

                var ua = window.navigator.userAgent;
                var msie = ua.indexOf("MSIE ");

                if (msie > 0 || !!navigator.userAgent.match(/Trident.*rv\:11\./)) {
                    if (window.navigator.msSaveBlob) {
                        var blob = new Blob([tab_text], {
                            type: "application/csv;charset=utf-8;"
                        });
                        navigator.msSaveBlob(blob, 'Test file.xls');
                    }
                } else {
                    $('#test').attr('href', data_type + ', ' + encodeURIComponent(tab_text));
                    $('#test').attr('download', '<%=filename%>.xls');
                }

            }
            $(document).ready(function () {
                fnExcelReport();
            });
    </script>
</html>


