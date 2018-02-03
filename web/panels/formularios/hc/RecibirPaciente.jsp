
<%@page import="ocupacional.JPA.controlers.SedeJpaController"%>
<%@page import="ocupacional.JPA.valueobjects.Sede"%>
<%@page import="ocupacional.JPA.valueobjects.TicketClienteservicio"%>
<%@page import="ocupacional.JPA.controlers.TicketJpaController"%>
<%@page import="ocupacional.valueobjects.facturacion.TicketClienteServicio"%>
<%@page import="ocupacional.JPA.controlers.CentrocostosJpaController"%>
<%@page import="formularios.entidades.Pacientes"%>
<%@page import="formularios.controlers.PacientesJpaController"%>
<%@page import="javax.persistence.Persistence"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<%@page import="ocupacional.bdatos.historiaclinica.tipoExamenMedicoDAO"%>
<%@page import="java.util.AbstractList"%>
<%@page import="java.util.List"%>
<%@page import="ocupacional.valueobjects.facturacion.Ticket"%>
<%-- 
    Document   : 
    Created on : 24/01/2016, 04:56:16 PM
    Author     : D4V3
--%>

<%@page import="valeria.metodos.Cadenas"%>
<%@page import="ocupacional.bdatos.DepartamentosDAO"%>
<%@page import="ocupacional.valueobjects.DepartamentosVO"%>
<%@page import="ocupacional.bdatos.CiudadesDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="ocupacional.valueobjects.CiudadesVO"%>
<%@page import="ocupacional.bdatos.PaisDAO"%>
<%@page import="ocupacional.valueobjects.PaisVO"%>
<%@page import="ocupacional.bdatos.ActividadEconomicaDAO"%>
<%@page import="ocupacional.valueobjects.ActividadEconomicaVO"%>
<%@page import="ocupacional.bdatos.JuridicasDAO"%>
<%@page import="ocupacional.valueobjects.JuridicasVO"%>
<%@page import="ocupacional.bdatos.NaturalesDAO"%>
<%@page import="ocupacional.valueobjects.NaturalesVO"%>
<%@page import="ocupacional.bdatos.PersonaGeneralDAO"%>
<%@page import="ocupacional.valueobjects.PersonaGeneralVO"%>
<%@page import="ocupacional.bdatos.TipoDocumentoDAO"%>
<%@page import="ocupacional.valueobjects.TipoDocumentoVO"%>
<%@page import="ocupacional.bdatos.facturacion.ClientesDAO"%>
<%@page import="ocupacional.valueobjects.facturacion.ClientesVO"%>
<%@page import="valeria.response.Mediador"%>
<%    Mediador e = (Mediador) session.getAttribute("Mediador");
    Cadenas pc = new Cadenas();

    if (e != null) {

EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaP1PU");
           EntityManagerFactory emf2 = Persistence.createEntityManagerFactory("JavaP");
%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div class="panel panel-default">

    <div class="panel-heading">
        <div class="row">
            <div class="col-lg-9">
                <h2 class="text-success">Recepcion de Pacientes</h2>
            </div>


        </div>
    </div>
    <div class="panel-body">
        <div class="row">
            <!--<form   id="Form-Data" role="form" name="Form-Data"  method="POST" action="" autocomplete="off">-->
            <div class="form-group col-md-2">

                <label class="control-label" for="documento_txt">
                    Sede:
                </label>
                <select name="sede" id="sede"  class="form-control ">
                    <% for(Sede s: new SedeJpaController(emf2).findSedeEntities()){%>
                    <option value="<%=s.getSedeId() %>"><%=s.getSedeNombre()%></option>
                    <% }%>
                    
                </select>
                    <script type="text/javascript"> $("#sede").val(<%=e.getUsuarioVO().getSede_id()%>)</script>
                <span class="help-block"></span>
            </div>
            <div class="form-group col-md-4">

                <label class="control-label" for="documento_txt">
                    No. Documento:
                </label>
                <input  id="documento_txt" name="documento_txt" class="form-control " type="number"   onkeydown="funcionEnter(event);"  >
                <span class="help-block"></span>
            </div>

            <div class="form-group col-md-4">

                <label class="control-label" for="nombre_txt">
                    Nombre:
                </label>
                <input  id="nombre_txt" name="nombre_txt"  class="form-control " type="text"  onkeydown="funcionEnter(event);" >
                <span class="help-block"></span>
            </div>
            <div class="form-group col-md-2">
                <button  onclick="enviar()" class="btn btn-primary" style="margin-top: 25px">BUSCAR</button>

            </div>

            <!--</form>-->
            <div id="cajaTicketsPaciente" class="col-md-12">
                <div id="tablaTicketsPaciente">
                    <%                    List<Ticket> l = (AbstractList<Ticket>) session.getAttribute("listaTicketPaciente");

                        if (l != null ) {
                            if(!l.isEmpty()){
                    %>

                    <table class="table table-hover">
                        <thead>
                            <tr>
                                
                        <th title="Numero de ticket">NO.</th> 
                        <th>EMPRESA</th> 
                        <th>ESTADO</th> 
                        <th>TIPO</th> 
                        <th>PACIENTE DOC.</th> 
                        <th>PACIENTE NOMBRE</th> 
                        <th>SERVICIOS</th> 
                        <th>OBSERVACIONES</th> 
                        <th></th> 

                            </tr>
                        </thead>
                        <tbody>
<%
for(Ticket t : l){
    
           
                    PacientesJpaController   pacic = new PacientesJpaController(emf);
                    ocupacional.JPA.valueobjects.Ticket tick = new TicketJpaController(emf2).findTicket(Integer.parseInt(t.getTick_id()));
Pacientes paci = pacic.findPacientes(Integer.parseInt(t.getTick_paciente()));
if(paci== null)paci= new Pacientes();
ClientesVO c = new ClientesDAO(e).Cargar(t.getCeco_id());
                    
%>
<tr>
    <td><%=pc.notEmpty(t.getTick_id()) %></td>
    <td><%= new CentrocostosJpaController(emf2).findCentrocostos(Integer.parseInt(t.getCeco_id())).getClieId().getClieNombre()%></td>
    <td><%=pc.notEmpty(t.getTick_estado()) %></td>
    <td><%=pc.notEmpty(new tipoExamenMedicoDAO(e).Cargar(t.getTeme_id()).getTeme_descripcion()) %></td>
    <td><%=pc.notEmpty(paci.getPaciDocumento()) %></td>
    <td><%=pc.notEmpty(paci.getPaciNombres()+" "+paci.getPaciApellidos() ) %></td>
    <td>
        <%
            if( tick != null &&tick.getTicketClienteservicioList()!=null){
            for(TicketClienteservicio tcs: tick.getTicketClienteservicioList() ){%>
        -> <%=tcs.getClseId().getServId().getServNombre()%> <br />
        <%}}%>
    </td>
    <td><p><%=pc.notEmpty(paci.getPaciObservaciones()) %></p></td>
    <td>
        <%if(t.getTick_estado().equals("POR PROCESAR")||t.getTick_estado().equals("PROCESANDO")){%>
        <button title="editar" type="button" value="<%= t.getTick_id()%>"  onclick="RecargaPanel('../panels/formularios/hc/RecibirPaciente_datosbasicos.jsp?tick_id=<%= t.getTick_id()%>','panelprincipal')" class="btn-circle btn-primary bottom-right btn-outline"><i class="glyphicon glyphicon-edit"></i> </button>
   <%}%>
        
    </td>
    
</tr>          


      <%}%>                      
                        </tbody>
                    </table>

                    <% 
                            
                               } else {%>
                    No se encontro coincidencia...
                    <%}
        } else {%>
                    No se encontro coincidencia...
                    <%}%>
                </div>
            </div>

        </div>
    </div>
    <div class="panel-footer"></div>
</div>

<!-- modal  fin -->
<!--<script data-config>
    var filtersConfig = {
        base_path: '../tablefilter/',
        col_1: 'select',
        col_6: 'select',
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

</script>-->

<script>
    $(document).ready(function () {
        ValidarFormID();
        $('.datepicker').datepicker();


    });


    $(".close").click(function () {
        $('#Form-Data').bootstrapValidator('resetForm', true);
    });
function funcionEnter(evento)
{
//para IE
if (window.event)
{
if (window.event.keyCode===13)
{
enviar();
}
}
else
{
//Firefox y otros navegadores
if (evento)
{
if(evento.which===13)
{
enviar();
}
}
}
}

function enviar(){
             var doc = $('#documento_txt').val();
                            var nom = $('#nombre_txt').val();
                            var sede = $('#sede').val();
                           
if(doc/doc === 1 || doc===""){
    

                            if (doc !== '' || nom !== '')
                            {
                                var datos = 'action=buscarTiketPaciente&sede='+sede+"&";
                                if (doc !== '' )         {
                                 datos += 'doc='+doc+'&';
                                }
                                if (nom !== '' )         {
                                 datos += 'nombre='+nom+'&';
                                }


                                peticionAjax('../OrdenServicio',datos)

                            } else {

                                alertify.error('Debe llenar minimo un campo.');
                            }}else{
                            
                                alertify.error('El campo Documento debe ser numerico.');
                            }

}

</script>
<%}else{%>
<script type="text/javascript">
    location.href='../logout.jsp';
</script>
<%}%>
