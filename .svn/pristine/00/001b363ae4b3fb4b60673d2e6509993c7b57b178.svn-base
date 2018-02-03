<%-- 
    Document   : cuentasCliente_facturar
    Created on : 15/06/2016, 11:35:50 PM
    Author     : D4V3
--%>

<%@page import="ocupacional.JPA.valueobjects.Ticket"%>
<%@page import="javax.persistence.TypedQuery"%>
<%@page import="javax.persistence.EntityManager"%>
<%@page import="javax.persistence.Persistence"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<%@page import="ocupacional.valueobjects.facturacion.FechasCuentasCliente"%>
<%@page import="ocupacional.JPA.valueobjects.TicketClienteservicio"%>
<%@page import="ocupacional.JPA.valueobjects.Centrocostos"%>
<%@page import="ocupacional.JPA.valueobjects.Clientes"%>
<%@page import="ocupacional.valueobjects.facturacion.CentroCostosVO"%>
<%@page import="ocupacional.valueobjects.facturacion.ClientesVO"%>
<%@page import="ocupacional.bdatos.historiaclinica.tipoExamenMedicoDAO"%>
<%@page import="ocupacional.bdatos.facturacion.CentroCostosDAO"%>
<%@page import="ocupacional.valueobjects.facturacion.ClientesServiciosVO"%>
<%@page import="valeria.metodos.ManejadorFechas"%>
<%@page import="valeria.metodos.ProcesaCaracEspeciales"%>
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
        ManejadorFechas f= new ManejadorFechas();

        String idf = request.getParameter("idf");
        if (idf != null) {
            session.removeAttribute("idf");
            session.setAttribute("idf", idf);

        } else {
            idf = (String) session.getAttribute("idf");

        }

       RolFuncionalidadVOs rf = new RolFuncionalidadDAO(e).Cargar(idf);

       Clientes ClienteVO = (Clientes)session.getAttribute("ClienteVO");
  
       
if(ClienteVO.getClieId()!=null){
                FechasCuentasCliente fcu = (FechasCuentasCliente) session.getAttribute("fechasCuentasClienteFacturacion");

%>

<div class="panel panel-default">

    <div class="panel-heading">
        <div class="row">
            <div class="col-lg-9">
                <h2 class="text-success">Cuentas por Sede-Finca
                
                </h2>
            </div>

      
        </div>
    </div>
    <div class="panel-body">
        <div class="row">

            
                    <fieldset id="caja">
                <legend> Desde:<%=f.DevuelveFormato(fcu.getFechaInicio()) %>
          Hasta: <%=f.DevuelveFormato(fcu.getFechaFin()) %></legend>
                <!-- Table -->
                <table id="tabla" class="table table-hover ">
                    <thead>
                        <tr>
                            <th title="Sede-Finca">Sede-Finca</th>
                            <th>TOTAL</th>
                            <th>ACCIONES</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%

           int total= 0;
      
      for(Centrocostos cc : ClienteVO.getCentrocostosList()){
         
      if(cc.getTicketList().size()>0){
      
      %>
      <tr>
                         
                            <td><%=cc.getCecoObservacion()%></td>
                        <%
      
                                int c = 1;
                                int totalcc=0;
                                
                                
                                  EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaP");
        
//        tiped query
          EntityManager em = emf.createEntityManager();
                    TypedQuery<Ticket> contickProcesados = em.createNamedQuery("Ticket.findByFechasYsede", Ticket.class);
                    contickProcesados.setParameter("sede",cc);
                    contickProcesados.setParameter("inicio", fcu.getFechaInicio());
                    contickProcesados.setParameter("fin", fcu.getFechaFin());
                    List<Ticket> lista = contickProcesados.getResultList();
                   
                    for (Ticket t : lista) {
                       
           
           
                                if(t.getTicketClienteservicioList()!=null){
                                for(TicketClienteservicio tcs: t.getTicketClienteservicioList()) {
                                        
                               total+=tcs.getClseId().getClseValor();
                               totalcc+=tcs.getClseId().getClseValor();
                                } }
                                }    
                                
                                
                        %> 
                            <td  style="text-align: right">
                                $<%=pc.getNumber(totalcc) %>
                            </td>                          
                           <td>
          <%if(totalcc>0){%>
          <a  class="btn btn-circle btn-success  bottom-right btn-outline" title="Facturar sede" onclick="peticionAjax('../Cuentas', 'action=GenerarFac&cc=<%=cc.getCecoId()%>');
            "><i class="glyphicon glyphicon-edit"></i> </a>
<%}%>
</td>
      </tr>
                           <%
                               em.close();
      }}
      

                        %>
                    </tbody>

                    <tfoot>
                        <tr>
                            <td colspan="2">
                                <span style="float: left ; font-weight: bold">TOTAL:</span>
                                <span style="float: right; font-weight: bold">
                                    $<%=pc.getNumber(total)%>
                                </span>
                            </td>
                          <td></td>
                        </tr>
                        
                    </tfoot>



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
   
        //        col_1: 'select',

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


    });
    $(".close").click(function () {
        $('#Form-Data').bootstrapValidator('resetForm', true);
    });

</script>
<%   }else{
%>Usted no es cliente<%
}}else{%>
<script type="text/javascript">
    location.href='../logout.jsp';
</script>
<%}%>