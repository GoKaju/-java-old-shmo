<%-- 
    Document   : cuentasCliente
    Created on : 2/04/2016, 12:01:02 PM
    Author     : D4V3
--%>


<%@page import="ocupacional.JPA.valueobjects.Sede"%>
<%@page import="ocupacional.JPA.controlers.SedeJpaController"%>
<%@page import="javax.persistence.TypedQuery"%>
<%@page import="javax.persistence.EntityManager"%>
<%@page import="ocupacional.valueobjects.facturacion.FechasCuentasCliente"%>
<%@page import="ocupacional.JPA.valueobjects.Centrocostos"%>
<%@page import="ocupacional.JPA.valueobjects.TicketClienteservicio"%>
<%@page import="ocupacional.JPA.controlers.ClientesJpaController"%>
<%@page import="ocupacional.JPA.valueobjects.Clientes"%>
<%@page import="ocupacional.JPA.controlers.TicketJpaController"%>
<%@page import="ocupacional.JPA.valueobjects.Ticket"%>
<%@page import="javax.persistence.Persistence"%>
<%@page import="javax.persistence.EntityManagerFactory"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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

       // RolFuncionalidadVOs rf = new RolFuncionalidadDAO(e).Cargar(idf);

        FechasCuentasCliente fcu = (FechasCuentasCliente) session.getAttribute("fechasCuentasClienteFacturacion");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaP");
        EntityManager em = emf.createEntityManager();
         
        SedeJpaController sededao = new SedeJpaController(emf);
        if (fcu == null) {
            fcu = new FechasCuentasCliente();
            fcu.setFechaInicio(f.StringToTimeStampHora(f.DevuelveFormato(f.getFechaHoraTimeStamp()) + " 00:00:00"));
//       fcu.setFechaInicio(f.StringToTimeStamp("24-12-2016"));
            fcu.setFechaFin(f.StringToTimeStampHora(f.DevuelveFormato(f.getFechaHoraTimeStamp()) + " 23:59:59"));

            fcu.setSede(sededao.findSede(Integer.parseInt(e.getUsuarioVO().getSede_id())));
            session.setAttribute("fechasCuentasClienteFacturacion", fcu);
        }


%>

<div class="panel panel-default">

    <div class="panel-heading">
        <div class="row">
            <div class="col-sm-12">
                <h2 class="text-success">Cuentas Clientes </h2>
            </div>

        </div>
    </div>

    <div class="panel-body">
        <div class="row">
            <div class="col-md-4">Desde: <a href="javascript:;" data-url="" id="fechaInicio" data-type="date" data-placement="rigth" data-title="Ingrese la nueva fecha" class="editable editable-click" data-original-title="" title="Modificar fecha">

                    <%=f.DevuelveFormato(fcu.getFechaInicio())%> 

                </a></div>
            <div class="col-md-4">      Hasta: 
                <a href="javascript:;" data-url="" id="fechaFin" data-type="date" data-placement="rigth" data-title="Ingrese la nueva fecha" class="editable editable-click" data-original-title="" title="Modificar fecha">
                    <%=f.DevuelveFormato(fcu.getFechaFin())%> 

                </a></div>
            <div class="col-md-4">
                Sede: <a href="javascript:;" data-url="" id="sede" data-type="select" data-placement="rigth" data-title="Ingrese la sede" class="editable editable-click" data-original-title="" title="Modificar sede">
                    <%=fcu.getSede().getSedeNombre() %> 

                </a>

            </div>



            <fieldset id="caja">
                <legend>Seleccione un cliente...</legend>
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
                            <th>CUENTA</th>
                            <th>ACCIONES</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%

                            ClientesJpaController cdao = new ClientesJpaController(emf);
                            int cont = 1;
                            for (Clientes c : cdao.findClientesEntities()) {
                                if (c.getClieEstado().equals("ACTIVO")) {
                                    int cuenta = 0;
                                    System.out.println(fcu.toString());
//        tiped query
                                   
                                    TypedQuery<Ticket> contickProcesados = em.createNamedQuery("Ticket.findByFechasSedeJavap", Ticket.class);
                                    contickProcesados.setParameter("clieId", c.getClieId());
                                    contickProcesados.setParameter("sede", fcu.getSede());
                                    contickProcesados.setParameter("inicio", fcu.getFechaInicio());
                                    contickProcesados.setParameter("fin", fcu.getFechaFin());
                                    List<Ticket> lista = contickProcesados.getResultList();

                                    for (Ticket t : lista) {

                                        for (TicketClienteservicio tcl : t.getTicketClienteservicioList()) {
                                            cuenta += tcl.getClseId().getClseValor();

                                        }
                                    }

//        for(Centrocostos cc : c.getCentrocostosList()){
//        for (Ticket t : cc.getTicketList()){
//            if(t.getTickEstado().equals("PROCESADO")){
//            for(TicketClienteservicio tcl: t.getTicketClienteservicioList()){
//            
//            cuenta += tcl.getClseId().getClseValor();
//            }
//        }}}
                                    if (cuenta > 0) {
                        %>
                        <tr>
                            <td><%=cont++%></td>
                            <td><%=c.getTidoId().getTidoDescripcion()%></td>
                            <td><%=c.getClieDocumento()%></td>
                            <td><%=c.getClieNombre()%></td>
                            <td><%=c.getClieTelefonos()%></td>
                            <td><%if (cuenta > 0) {%>DEUDA<%} else {%><%=c.getClieEstado()%><%}%></td>
                            <td style="font-weight: bold">$<%=pc.getNumber(cuenta)%></td>
                            <td>
                               <button type="button" value="<%=c.getClieId()%>" title="Ver cuenta" class="btn-circle btn-success bottom-right " onclick="peticionAjax('../Cuentas', 'action=cuentaVer&id=' + this.value)"><i class="glyphicon glyphicon-search"></i> </button>

                        </tr>
                        <%
                                    }
                                }
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


            <div class="col-lg-9 right">

            </div>
            <div class="col-lg-3 right">
                <!--                <div class="form-group">
                                    <button type="button" class="btn btn-default bottom-right btn-outline">Cancelar </button>
                                    <button  onclick="peticionAjax('../OrdenServicio', 'action=eliminarSSession');" type="button" class="btn btn-success bottom-right btn-outline">Nueva orden</button>
                
                                </div>-->
            </div>
        </div>


    </div>

</div>


<script data-config>
    $('#fechaInicio').editable({
        format: 'yyyy-mm-dd',
        viewformat: 'dd-mm-yyyy',
        datepicker: {
            weekStart: 1
        },
        success: function (response, newValue) {
            var x = newValue.toUTCString();
            peticionAjax('../Cuentas', 'action=cuentaCambiarInicio&fecha=' + convertDate(x));

        }});
    $('#fechaFin').editable({
        format: 'yyyy-mm-dd',
        viewformat: 'dd-mm-yyyy',
        datepicker: {
            weekStart: 1
        },
        success: function (response, newValue) {
            var x = newValue.toUTCString();
            peticionAjax('../Cuentas', 'action=cuentaCambiarFin&fecha=' + convertDate(x));

        }});
    $('#sede').editable({
        value: <%=fcu.getSede().getSedeId()%>,
        source: [
    <%
                for (Sede s : sededao.findSedeEntities()) {
    %>

            {value: <%=s.getSedeId()%>, text: '<%=s.getSedeNombre()%>'},
    <%
                }
    %>
        ],
        success: function (response, newValue) {
//            var x = newValue.toUTCString();
            peticionAjax('../Cuentas', 'action=cuentaCambiarSede&sede=' + newValue);

        }
    });

    var filtersConfig = {
        base_path: '../tablefilter/',
        col_1: 'select',
        col_5: 'select',
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
<% em.close(); } else {%>
<script type="text/javascript">
    location.href = '../logout.jsp';
</script>
<%}%>
