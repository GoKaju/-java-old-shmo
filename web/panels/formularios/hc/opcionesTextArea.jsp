<%-- 
    Document   : peticion_firma
    Created on : 22/07/2016, 02:29:43 AM
    Author     : D4V3
--%>

<%@page import="formularios.entidades.Opciones"%>
<%@page import="formularios.controlers.CamposJpaController"%>
<%@page import="valeria.response.Mediador"%>
<%@page import="valeria.metodos.Cadenas"%>
<%@page import="formularios.entidades.Anotaciones"%>
<%@page import="formularios.entidades.Huellafirma"%>
<%@page import="formularios.controlers.HuellafirmaJpaController"%>
<%@page import="formularios.controlers.AnotacionesJpaController"%>
<%@page import="javax.persistence.Persistence"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    Mediador e = (Mediador) session.getAttribute("Mediador");

    if (e != null) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaP1PU");

        Cadenas o = new Cadenas();


%>

<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal">&times;</button>
    <div class="row">
        <div class="col-md-6">
            <!--<div id="btn_firma" class="btn btn-danger" onclick="verificarDispositivos()" ><img width="25" src="../images/tablet_firma_icon.svg" alt="Firma icono" title="Verificar dispositivo de firma "/></div>-->
            <!--<div id="btn_huella" class="btn btn-danger" onclick="verificarDispositivos()" ><img width="25" src="../images/huella_icon.svg" alt="Huella icono" title="Verificar dispositivo de huella "/></div>-->
        </div>
        <div id="signature-pad" class="m-signature-pad right col-md-4">

            <!--<button type="button" id="btn_guardar" class="button save btn btn-danger " onclick="" >Pedir Datos</button>-->

        </div>
    </div></div>
<div id="cajaImg" class="modal-body">
    <div id="content_img" class="row">

        <table id="tablaOpcionesTextarea" style="width: 100%">
            <thead>
                <tr>
                    <th>Opción</th>
                    <th></th>

                </tr>
            </thead>
            <tbody>
                <%                                for (Opciones op : new CamposJpaController(emf).findCampos(Integer.parseInt(request.getParameter("camp_id"))).getOpcionesList()) {
                %>
                <tr>
                    <td>
                        <label style="font-weight: normal; cursor: pointer" for="op<%=op.hashCode()%>">
                            <%=op.getOpciNombre()%>
                        </label>
                    </td>
                    <td>
                        <input style=" 
                               " type="checkbox" id="op<%=op.hashCode()%>" name="opcionTextArea" value="<%=op.getOpciValue()%>" />

                    </td>
                </tr>
                <%}%>
            </tbody>

        </table>

    </div>


</div>
<div class="modal-footer">


    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
    <button type="button" class="btn btn-success" onclick="agregarOpcionesTextArea()">Agregar</button>

</div>





<script data-config>
    var filtersConfig = {
        base_path: '../tablefilter/',
        col_1: 'none',
        alternate_rows: true,
        btn_reset: true,
        paging: true,
        results_per_page: ['Resultados por Pag.', [25, 50, 100]],
        rows_counter: true,
        loader: true,
        status_bar: true,
        mark_active_columns: true,
        highlight_keywords: true,
        extensions: [{name: 'sort'}]
    };

    var tf = new TableFilter('tablaOpcionesTextarea', filtersConfig);
    tf.init();


function agregarOpcionesTextArea(){
       var l = '';
    $("input[name=opcionTextArea]").each(function (index) {  
       if($(this).is(':checked')){
          l += '*'+$(this).val()+' ';
       }
    });
    if(l!== ""){
        $("#<%=o.notEmpty(request.getParameter("camp_name")) %>").append(l);
                    $('#myModal').modal('toggle');
        
    }else{
        alertify.error("Seleccione mínimo una opción.");
    }
    
}
   //    anular enter
  $('form').keypress(function(e){   
    if(e == 13){
      return false;
    }
  });
  $('input').keypress(function(e){
    if(e.which == 13){
      return false;
    }
  });
</script>
<!--<script src="../../js/jquery-1.10.2.min.js"></script>-->



<%}else{%>
<script type="text/javascript">
    location.href='../logout.jsp';
</script>
<%}%>
