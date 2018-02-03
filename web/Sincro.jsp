<%-- 
    Document   : pruebaSincro
    Created on : 21/09/2016, 12:35:19 PM
    Author     : D4V3
--%>
<%@page import="formularios.controlers.SincroPacientesJpaController"%>
<%@page import="ocupacional.JPA.controlers.SincroTicketJpaController"%>
<%@page import="javax.persistence.Persistence"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<%@page import="ocupacional.bdatos.RolFuncionalidadDAO"%>
<%@page import="ocupacional.valueobjects.RolFuncionalidadVOs"%>
<%@page import="valeria.metodos.ManejadorFechas"%>
<%@page import="valeria.metodos.Cadenas"%>
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
                <h2 class="text-success"> Sincronización</h2>
            </div>

        </div>
    </div>

    <div class="panel-body">
        <div class="row">

<div class="alert alert-danger"> No es posible realisar la sincronizacion desde el mismo servidor</div>
            <fieldset id="caja">
                <!-- Table -->
                <table id="tabla" class="table table-hover ">
                    <thead>
                        <tr>
                           
                            <th>TIPO</th>
                            <th># REGISTOS</th>
                           
                        </tr>
                    </thead>
                    <tbody>
                        <%
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaP");
        EntityManagerFactory emf2 = Persistence.createEntityManagerFactory("JavaP1PU");
        
       
 
 
                            %>
                            <tr>
                            <td>Ordenes de servicio</td> 
                                <td><%=new SincroTicketJpaController(emf).getSincroTicketCount()%></td>
                            </tr>
                            <tr>
                            <td>Pacientes </td> 
                            <td><%=new SincroPacientesJpaController(emf2).getSincroPacientesCount()%></td>
                            </tr>
                        <%    
        
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
                <div class="form-group">
                    <!--<button type="button" class="btn btn-default bottom-right btn-outline">Cancelar </button>-->
<!--                    <button  onclick="
                        alertify.confirm('¿Está seguro de realizar esta acción?',function(e){
                         if(e){
                             
                        peticionAjax('../Sincronizacion', 'action=sincronizar');
                             
                         }   
                            
                            
                        })
                                            " type="button" class="btn btn-primary bottom-right ">Sincronizar</button>-->

                </div>
            </div>
        </div>


    </div>

</div>


<script data-config>
    var filtersConfig = {
        base_path: '../tablefilter/',
       
        col_8: 'none',
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
<% } else {%>


<script type="text/javascript">                           location.href='../logout.jsp';</script>

<% }
}catch(Exception ex){ex.printStackTrace();}
%>
