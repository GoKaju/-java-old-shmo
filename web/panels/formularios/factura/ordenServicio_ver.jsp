<%-- 
    Document   : ordenServicio_ver
    Created on : 19/05/2016, 11:25:01 PM
    Author     : D4V3
--%>

<%@page import="ocupacional.JPA.valueobjects.TicketClienteservicio"%>
<%@page import="ocupacional.JPA.controlers.TicketJpaController"%>
<%@page import="ocupacional.JPA.valueobjects.Ticket"%>
<%@page import="java.util.List"%>
<%@page import="ocupacional.JPA.controlers.ClientesJpaController"%>
<%@page import="ocupacional.JPA.valueobjects.Clientes"%>
<%@page import="ocupacional.JPA.controlers.ClientesServicioJpaController"%>
<%@page import="ocupacional.JPA.valueobjects.ClientesServicio"%>
<%@page import="javax.persistence.Persistence"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<%@page import="formularios.entidades.Pacientes"%>
<%@page import="formularios.controlers.PacientesJpaController"%>
<%@page import="ocupacional.bdatos.historiaclinica.tipoExamenMedicoDAO"%>
<%@page import="ocupacional.bdatos.facturacion.CentroCostosDAO"%>
<%@page import="ocupacional.bdatos.NaturalesDAO"%>
<%@page import="ocupacional.valueobjects.NaturalesVO"%>
<%@page import="valeria.metodos.ManejadorFechas"%>
<%@page import="ocupacional.bdatos.PersonaGeneralDAO"%>
<%@page import="ocupacional.bdatos.facturacion.TicketClienteServicioDAO"%>
<%--<%@page import="ocupacional.valueobjects.facturacion.Ticket"%>--%>
<%@page import="ocupacional.bdatos.facturacion.TicketsDAO"%>


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
<%    
    try{
    Mediador e = (Mediador) session.getAttribute("Mediador");
    if (e != null) {
                                                 EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaP1PU");
                                                 EntityManagerFactory emf2 = Persistence.createEntityManagerFactory("JavaP");

//               ClientesVO c = (ClientesVO)session.getAttribute("ClienteVO");
        Cadenas pc = new Cadenas();
        Ticket tt = new TicketJpaController(emf2).findTicket(Integer.parseInt(request.getParameter("id")));
     
       
               Clientes clie = tt.getCecoId().getClieId();
                                            System.out.println("#############"+clie.getClieNombre());
PacientesJpaController pacic = new PacientesJpaController(emf);
                                    
Pacientes paci = pacic.findPacientes(tt.getTickPaciente());
if(paci==null)paci= new  Pacientes();

%>

<div class="panel panel-default">

    <div class="panel-heading">
        <div class="row">
            <div class="col-lg-9">
                <h2 class="text-success">Orden de servicio N° <%=tt.getTickId()%>  Estado(<small><%=tt.getTickEstado()%> </small> ) </h2>
            </div>
        </div>
    </div>
    <form   id="Form-Data" role="form" name="Form-Data"  method="POST" action="../OrdenServicio" autocomplete="off">
        <div class="panel-body">
            <div class="row ">



                <fieldset  style="padding: 10px" >
                    <legend>Datos Basicos</legend>

                    <div class="row ">

                        <ul>
                            <li>Sede JAVAP: <%=pc.notEmpty(tt.getSedeId().getSedeNombre())%>
                                 <%if(tt.getSedeId()!=null && tt.getSedeId().getSedeId().equals(4)){%>
                                  - <%=pc.notEmpty(tt.getTickBrigada()) %>
                                <%}%>
                            
                            </li>
                            <li>Documento: <%=pc.notEmpty(paci.getPaciDocumento())%></li>
                            <li>Nombre:  <%=pc.notEmpty(paci.getPaciNombres()+" "+paci.getPaciApellidos())%></li>
                            <li>Sede:  <%=pc.notEmpty(tt.getCecoId().getCecoObservacion())%></li>
                            <li>Centro de costo: <%=pc.notEmpty(tt.getTickClsede())%></li>
                            <li>Tipo de Examen:  <%=pc.notEmpty(tt.getTemeId().getTemeDescripcion())%>
                                 <%if(tt.getTemeId()!= null && tt.getTemeId().getTemeId().equals(7)){%>
                             - <%=pc.notEmpty(tt.getTickOtroexamen()) %>
                             <%}%>
                            </li>
                            <li>Fecha de Registro: <%=new ManejadorFechas().FechaLetrasHora(tt.getTickFecharegistro())%></li>
                            <li>Fecha de inicio atencion <%=new ManejadorFechas().FechaLetrasHora(tt.getTickFecharecepcion())%></li>
                            <li>Fecha de fin atencion <%=new ManejadorFechas().FechaLetrasHora(tt.getTickFechaprocesado())%></li>
                        </ul>

                    </div>

                </fieldset>

                <fieldset >
                    <legend> Servicios </legend>
                    <div class="row" id="cajaServicios">
                        <%                      
                        List<ClientesServicio> listaClientesServiciosVO = clie.getClientesServicioList();
                                            System.out.println("2#############");

                        %>
                        <table class="table table-hover" id="tablaServicios"> 
                            <thead >
                                <tr>
                                    <th>Servicio</th>
                                    

                                </tr>


                            </thead>
                            <tbody>
                          <%                             
                    List<TicketClienteservicio> listaTCServicio = tt.getTicketClienteservicioList();
                                    if (listaTCServicio != null) {
                                        for (TicketClienteservicio tcs : listaTCServicio) {
                                          

                                %>
                                <tr>
                                    <td><%=tcs.getClseId().getServId().getServNombre()%></td>

                                </tr>
                                <%
                                        }
                                    }
                                %>


                            </tbody>

                        </table>


                    </div>
                </fieldset>
                <fieldset hidden="">


                    <legend>Archivos</legend>
                    <table class="table table-hover">
                        <thead></thead>
                        <tbody>
                            <tr>
                                <td>Certificado</td>
                                <td>Fecha</td>
                                <td>ver-descargar</td>
                            </tr>


                        </tbody>
                    </table>
                </fieldset>

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
                        
                        <% if(e.getUsuarioVO().getUsua_tipo().equals("C")){%>
                        <button  onclick="RecargaPanel('../panels/formularios/factura/ordenServicio.jsp', 'panelprincipal')" type="button" class="btn btn-default bottom-right btn-outline">Atras </button>
                        <%}else{%>
                        <button  onclick="RecargaPanel('../panels/formularios/factura/ordenServicio.jsp?clie_ocu=<%=clie.getClieId()%>', 'panelprincipal')" type="button" class="btn btn-default bottom-right btn-outline">Atras </button>
                        <%}%>
                        <% if(tt.getTickEstado().equals("PROCESADO")|| tt.getTickEstado().equals("FACTURADO")){%>
                        <button  onclick="RecargaPanel('../panels/formularios/hc/timeLine_verClie.jsp?id=<%=tt.getTickId()%>', 'panelprincipal')" type="button" class="btn btn-success bottom-right ">Ver resultados </button>
                        <%}%>
                        
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
<%}else{%>
<script type="text/javascript">
    location.href='../logout.jsp';
</script>


<% }}catch(Exception ex){ex.printStackTrace(); }%>