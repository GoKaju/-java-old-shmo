<%-- 
    Document   : RecibirPaciente_Medico
    Created on : 2/04/2016, 11:43:50 AM
    Author     : D4V3
--%>

<%@page import="formularios.entidades.Anotaciones"%>
<%@page import="formularios.entidades.Formularios"%>
<%@page import="javax.persistence.TypedQuery"%>
<%@page import="javax.persistence.EntityManager"%>
<%@page import="ocupacional.JPA.valueobjects.ServiciosExamenes"%>
<%@page import="ocupacional.JPA.valueobjects.ClientesServicio"%>
<%@page import="ocupacional.JPA.valueobjects.TicketClienteservicio"%>
<%@page import="ocupacional.valueobjects.facturacion.TicketClienteServicio"%>
<%@page import="ocupacional.JPA.valueobjects.Ticket"%>
<%@page import="ocupacional.JPA.controlers.TicketJpaController"%>
<%@page import="formularios.entidades.Pacientes"%>
<%@page import="formularios.controlers.PacientesJpaController"%>
<%@page import="javax.persistence.Persistence"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<%@page import="ocupacional.bdatos.historiaclinica.tipoExamenMedicoDAO"%>
<%@page import="java.util.AbstractList"%>
<%@page import="java.util.List"%>


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


%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div class="panel panel-default">

    <div class="panel-heading">
        <div class="row">
            <div class="col-lg-9">
                <h2 class="text-success">Recepcion de Pacientes - Medico</h2>
            </div>


        </div>
    </div>
    <div class="panel-body">
        <div class="row">
          
          

            <!--</form>-->
            <div id="cajaTicketsPaciente" class="col-md-12">
                <div id="tablaTicketsPaciente">
                    <%       
          EntityManagerFactory emf2 = Persistence.createEntityManagerFactory("JavaP");
             
        
        List<Ticket> l = new TicketJpaController(emf2).findTicketEntities();

                        if (l != null || l.isEmpty()) {
                    %>

                    <table class="table table-hover" id="tabla" >
                        <thead>
                            <tr>
                                
                        <th title="Numero de ticket">NO.</th> 
<!--                        <th>EMPRESA</th> -->
                        <th>ESTADO</th> 
                        <th>TIPO</th> 
                        <th>PACIENTE DOC.</th> 
                        <th>PACIENTE NOMBRE</th> 
                        <th>EXAMENES</th>
                        <th>OBSERVACIONES</th>
                        <th></th> 

                            </tr>
                        </thead>
                        <tbody>
<%
for(Ticket t : l){
    if(t.getTickEstado().equals("PROCESANDO")&& t.getSedeId().getSedeId()==Integer.parseInt("4")){
           EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaP1PU");
                    PacientesJpaController   pacic = new PacientesJpaController(emf);
                    
Pacientes paci = pacic.findPacientes(t.getTickPaciente());
if(paci== null)paci= new Pacientes();
ClientesVO c = new ClientesDAO(e).Cargar(t.getCecoId().getCecoId().toString());
                    
%>
<tr>
    <td><%=pc.notEmpty(t.getTickId().toString()) %></td>
    <!--<td></td>-->
    <td><%=pc.notEmpty(t.getTickEstado()) %></td>
    <td><%=pc.notEmpty(t.getTemeId().getTemeDescripcion() )%></td>
    <td><%=pc.notEmpty(paci.getPaciDocumento()) %></td>
    <td><%=pc.notEmpty(paci.getPaciNombres()+" "+paci.getPaciApellidos() ).toUpperCase() %></td>
    <td>
        
        <%
        for(TicketClienteservicio tcl:t.getTicketClienteservicioList()){
        for(ServiciosExamenes se :tcl.getClseId().getServId().getServiciosExamenesList()){
            boolean procesado = false;
            Formularios form = new Formularios();
              EntityManager em = emf.createEntityManager();
        TypedQuery<Formularios> consultaform = em.createNamedQuery("Formularios.findByExamId", Formularios.class);
        consultaform.setParameter("examId", se.getExamId().getExamId());
        List<Formularios> lista = consultaform.getResultList();
                   if (!lista.isEmpty()) {
            for (Formularios f : lista) {
                form = f;
            }}
       TypedQuery<Anotaciones> consultaAnot = em.createNamedQuery("Anotaciones.findByTCLS", Anotaciones.class);
        consultaAnot.setParameter("ticsId", tcl.getTicsId());
        consultaAnot.setParameter("formId", form);
        List<Anotaciones> listaa = consultaAnot.getResultList();
                   if (!listaa.isEmpty()) {
            for (Anotaciones a : listaa) {
                           procesado=a.getAnotEstado().equals("PRE EDICION")? false : true;

            }}
            
//            aqui cargar form y luego listar form y tcs
            
            
        %>
        <span class="glyphicon glyphicon-circle-arrow-right " style=" <%if(!procesado){%>color: #006dcc<%}else{%>color: #00CE6F<%}%>"></span><%=se.getExamId().getExamDescripcion() %> <br />
    <% }}
    %>
    </td>
    
    <td><p><%=pc.notEmpty(paci.getPaciObservaciones()) %></p></td>
    <td>
        <button title="editar" type="button" value="<%= t.getTickId().toString()%>"  onclick="peticionAjax('../Pacientes','action=timelineMedico&tick_id='+this.value)" class="btn-circle btn-primary bottom-right btn-outline"><i class="glyphicon glyphicon-edit"></i> </button>
    </td>
</tr>          


      <% }}%>                      
                        </tbody>
                    </table>

                    <%}  else {%>
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
                           
if(doc/doc === 1 || doc===""){
    

                            if (doc !== '' || nom !== '')
                            {
                                var datos = 'action=buscarTiketPaciente&';
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
<script data-config>
    var filtersConfig = {
        base_path: '../tablefilter/',
        col_0: 'none',
        col_2: 'select',
        col_7: 'none',
        alternate_rows: true,
        btn_reset: true,
        paging: true,
        results_per_page: ['Resultados por Pag.', [5,10, 25, 50, 100]],
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
<%}else{%>
<script type="text/javascript">
    Location.href='../logout.jsp';
</script>
<%}%>

