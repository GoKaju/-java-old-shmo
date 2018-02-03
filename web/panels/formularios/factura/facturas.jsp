<%-- 
    Document   : facuras
    Created on : 2/04/2016, 12:01:18 PM
    Author     : D4V3
--%>
<%@page import="javax.persistence.TypedQuery"%>
<%@page import="javax.persistence.EntityManager"%>
<%@page import="ocupacional.JPA.valueobjects.Movimientosfacturacion"%>
<%@page import="ocupacional.JPA.controlers.MovimientosfacturacionJpaController"%>
<%@page import="ocupacional.JPA.valueobjects.Facturas"%>
<%@page import="ocupacional.JPA.controlers.FacturasJpaController"%>
<%-- 
    Document   : cuentasCliente
    Created on : 2/04/2016, 12:01:02 PM
    Author     : D4V3
--%>


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
  
try{
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


%>

<div class="panel panel-default">

    <div class="panel-heading">
        <div class="row">
            <div class="col-sm-12">
                <h2 class="text-success"> Movimientos Facturación </h2>
            </div>

        </div>
    </div>

    <div class="panel-body">
        <div class="row">


            <fieldset id="caja">
                <!-- Table -->
                <table id="tabla" class="table table-hover ">
                    <thead>
                        <tr>
                            <th title="consecutivo del movimiento">#</th>
                            <th style="table-layout: fixed; width: 400px" >FACTURAS</th>
                            <th>ORDENES SERVICIO</th>
                            <th>TOTAL</th>
                            <th>FECHA</th>
                            <th>OBSERVACION</th>
                            <th>ESTADO</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaP");
        
//        ClientesJpaController cdao = new ClientesJpaController(emf);
        MovimientosfacturacionJpaController mdao = new MovimientosfacturacionJpaController(emf);
        int cont = 1;
              
//        tiped query
          EntityManager em = emf.createEntityManager();
                    TypedQuery<Movimientosfacturacion> mofaList = em.createNamedQuery("Movimientosfacturacion.findAllDesc", Movimientosfacturacion.class);
          
                   
                   
        
        
        for (Movimientosfacturacion mov :  mofaList.getResultList()){
        int cuenta=0;
        
//       
        
                        %>
                        <tr>
                            <td><%=String.format("%05d", mov.getMofaId())%></td>
                            <td >
                                <div id="td<%=cont%>">
                                <div id="dvf<%=cont%>">
                                <%
                            int total =0;
                            Facturas fa = new Facturas();
                            for(Facturas fac : mov.getFacturasList()){
                                total+= fac.getFactTotal();
                                fa=fac;
                            %>
                            
                                       <%if (rf.getRofu_op().indexOf("M") != -1) {%>
                                
                                       <a data-toggle="modal" data-target="#myModal"  class="btn    <% if(f.compare(fac.getFactfechaCreacion(), fac.getFactFechacambio())==0){%> btn-success<%}else{%> btn-default<%}%>  " style="width: 100%" title="Ver" onclick="   $('#modalContent').load('../panels/formularios/factura/facturas_ver.jsp','fac_id=<%=fac.getFactId()%>&n=<%=cont%>');
                                          "><%=pc.notEmpty(String.valueOf(fac.getFactConsecutivo())+" - "+ pc.notEmpty(fac.getCecoId().getClieId().getClieNombre())+" ("+pc.notEmpty(fac.getCecoId().getCecoObservacion())+")" ) %> <i class="glyphicon glyphicon-search"></i> </a>   
           
                                <% }else{
       %>
       <%=pc.notEmpty(String.valueOf(fac.getFactConsecutivo())+" - "+ pc.notEmpty(fac.getCecoId().getClieId().getClieNombre())+" ("+pc.notEmpty(fac.getCecoId().getCecoObservacion())+")" ) %>                        
       <%
       }%>
                               
                                
                                <% }%>
                                </div></div>
                            </td>
                            <td><%
                            for(Ticket tick : mov.getTicketList()){
                            %>
                           <%=pc.notEmpty(tick.getTickId().toString()) %>, 
                           
                                <% }%></td>
                            <td style="font-weight: bold;">$<%=pc.getNumber(total)%></td>
                            <td><%=f.FechaLetrasHora(mov.getMofaFechacreacion()) %></td>
                            <td><%=mov.getMofaObservacion() %></td>
                            <td><%if(fa.getFactConsecutivo()>0 && !fa.getFactEstado().equals("ANULADA") ){%>IMPRESA<%}else if(fa.getFactEstado().equals("ANULADA")){%><b style="color:red"><%= pc.notEmpty(fa.getFactEstado()) %></b><%}else{ %><%= pc.notEmpty(mov.getMofaEstado()) %><% } %></td> </tr>
                        <%    
        }
                        %>
                    </tbody>






                </table>







            </fieldset>
    <!-- Modal -->
                <div class="modal fade" id="myModal" role="dialog">
                    <div class="modal-dialog modal-lg">
                        <div class="modal-content"  id="modalContent">
                           
                        </div>
                    </div>
                </div>
            <!--fin-->






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
    var filtersConfig = {
        base_path: '../tablefilter/',
       
        col_6: 'select',
//        col_8: 'none',
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


<script type="text/javascript">                           location.href='../logout.jsp';</script>

<% }
}catch(Exception ex){ex.printStackTrace();}
%>
