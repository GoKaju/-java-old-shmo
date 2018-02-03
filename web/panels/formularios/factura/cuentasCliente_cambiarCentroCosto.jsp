<%-- 
    Document   : cuentasCliente_cambiarCentroCosto
    Created on : 15/06/2016, 10:09:09 PM
    Author     : D4V3
--%>


<%@page import="ocupacional.JPA.controlers.ClientesJpaController"%>
<%@page import="ocupacional.JPA.valueobjects.Centrocostos"%>
<%@page import="javax.persistence.Persistence"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<%@page import="ocupacional.JPA.controlers.TicketJpaController"%>
<%@page import="ocupacional.JPA.valueobjects.Ticket"%>
<%@page import="ocupacional.JPA.valueobjects.Clientes"%>

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
      
       
       String id = request.getParameter("id");
       
if(id!=null){
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaP");
    Ticket t = new TicketJpaController(emf).findTicket(Integer.parseInt(id));
%>

<div class="panel panel-default">

    <div class="panel-heading">
        <div class="row">
            <div class="col-lg-9">
                <h2 class="text-success">Ordenes de servicio #<%=t.getTickId()%></h2>
            </div>

      
        </div>
    </div>
        <form   id="Form-Data" role="form" name="Form-Data"  method="POST" action="../OrdenServicio" autocomplete="off">
    <div class="panel-body">
        <div class="row">
  <fieldset id="caja">
                <legend>Datos:</legend>
                 <ul>
                     <li>Cliente: <%=ClienteVO.getClieNombre()%></li>
                     <li>Sede-Finca: <%=t.getCecoId().getCecoObservacion()%></li>
                 </ul>
                
  </fieldset>
            
                    <fieldset id="caja">
                <legend>cambiar</legend>
                <!-- Table -->
             <div class="form-group col-md-4">
                        <select  name="clientes" id="clientes" class="form-control" required onchange="peticionAjax('../OrdenServicio','action=listarCC&id='+this.value)">
                            <option value="">Seleccione..</option>
                            <%
                            
                            for(Clientes cc :new ClientesJpaController(emf).findClientesEntities()){
                            if(cc.getClieEstado().equals("ACTIVO")){
                            %>
                             <option value="<%=cc.getClieId()%>"><%=cc.getClieNombre()+" - "+cc.getClieDocumento() %></option>
                            
                            
                            <%
                            }
                            }
                            
                            %>
                        </select>
                        <span class="help-block"></span>
                    </div>
                             <div class="form-group col-md-4">
                                 <select  name="centro_costo" id="centro_costo" class="form-control" required >
                            <option value="">Seleccione..</option>
                            <%
                            
                            for(Centrocostos cc :ClienteVO.getCentrocostosList()){
                            if(cc.getCecoEstado().equals("ACTIVO")){

                            %>
                             <option value="<%=cc.getCecoId()%>"><%=cc.getCecoObservacion()%></option>
                            
                            
                            <%
                            }}
                            
                            
                            %>
                        </select>
                        <span class="help-block"></span>
                    </div>
            
                            <script type="text/javascript">
                                
                                $("#clientes").val(<%=ClienteVO.getClieId()%>);
                                $("#centro_costo").val(<%=t.getCecoId().getCecoId()%>);
                                
                            </script>
                
                
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
                    <input type="hidden" name="id" value="<%=t.getTickId()%>"> 
                    <input type="hidden" name="action" value="cambiarCC"> 
                        <button  onclick="RecargaPanel('../panels/formularios/factura/cuentasClienteVer.jsp', 'panelprincipal')" type="button" class="btn btn-default bottom-right btn-outline">Atras </button>
                        <button type="button" onclick="
                            alertify.confirm('¿Esta seguro de cambiar el Cliente o Centro de costo?',function(x){
                                if(x){
                                    
                                   $('#Form-Data').submit(); 
                                    
                                }
                                
                                
                            });
                            
                                " class="btn btn-success bottom-right ">Guardar </button>
                  
                </div>
            </div>
        </div>


    </div>
                 </form>
</div>
                                




<script>
    $(document).ready(function () {
        ValidarFormID();

        $('.datepicker').datepicker();


    });
    $(".close").click(function () {
        $('#Form-Data').bootstrapValidator('resetForm', true);
    });

</script>
<% }else{
%>Ninguna orden de servicio seleccionada<%
}}else{%>
<script type="text/javascript">
    location.href='../logout.jsp';
</script>
<%}%>
