<%-- 
    Document   : peticion_firma
    Created on : 22/07/2016, 02:29:43 AM
    Author     : D4V3
--%>

<%@page import="ocupacional.valueobjects.CieVO"%>
<%@page import="ocupacional.bdatos.CieDAO"%>
<%@page import="formularios.controlers.CieJpaController"%>
<%@page import="formularios.entidades.Cie"%>
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
        <div class="panel-title">
            <h3>
                Clasificación Internacional de Enfermedades - CIE
            </h3>
      </div>
       
    </div></div>
<div id="cajaImg" class="modal-body">
    <div class="row">
          <div class="form-group col-md-3">

                        <label class="control-label">
                            Opcion de busqueda
                        </label>
              <select class="form-control " oninput ="
                      opcionBusqueda(this.value)
                      " >
                            <option value="1">Descripción</option>
                            <option value="2">Codigo</option>
                            <option value="3">Capitulos </option>
                        </select>
                        <span class="help-block"></span>
                    </div>
        
        <div id="opDescripcion" class="col-md-9" >
                <div class="form-group col-md-11">

                        <label class="control-label">
                            Descripción
                        </label>
                    <input  type="text" class="form-control" placeholder="inserte un fragmento de la descripción" id="descripcion_opcionTA"/>
                        <span class="help-block"></span>
                    </div>
                <div class=" col-md-1">

                    <button onclick="buscarXdescripcion($('#descripcion_opcionTA').val())" type="button" class="btn btn-primary btn-circle" style="margin-top: 25px" title="buscar"> <i class="glyphicon glyphicon-search"></i></button>
                    </div>
        </div>
        <div id="opCodigo"  hidden="" class="col-md-9">
             <div class="form-group col-md-11">

                        <label class="control-label">
                            Codigo
                        </label>
                    <input  type="text" class="form-control" placeholder="inserte un fragmento del codigo minimo 2 caracteres" id="codigo_opcionTA"/>
                        <span class="help-block"></span>
                    </div>
                <div class=" col-md-1">

                    <button onclick="buscarXcodigo($('#codigo_opcionTA').val())" type="button" class="btn btn-primary btn-circle" style="margin-top: 25px" title="buscar"> <i class="glyphicon glyphicon-search"></i></button>
                    </div>
            
        </div>
        <div id="opCapitulos"  hidden="" class="col-md-9">
             <div class="form-group col-md-3">

                        <label class="control-label">
                            Capitulo
                        </label>
                 <select id="capi_opcionTA" class="form-control" onchange="peticionAjax('../Cie','action=listarVersiculo&codigo='+this.value)" >
                     <%  
                     CieDAO ciedao = new CieDAO(e);
                     for(CieVO cie: ciedao.ListarCapitulos()){
                     %>
                     <option value="<%=cie.getId() %>"><%=cie.getId()+ " - "+cie.getDescripcion()%></option>
                     <%}%>
                     
                 </select>
                        <span class="help-block"></span>
                    </div>
             <div class="form-group col-md-3">

                        <label class="control-label">
                            -
                        </label>
                 <select id="versi_opcionTA" class="form-control" oninput="peticionAjax('../Cie','action=listarLiteral&codigo='+this.value)" >
                   
                     
                 </select>
                        <span class="help-block"></span>
                    </div>
             <div class="form-group col-md-3">

                        <label class="control-label">
                            -
                        </label>
                 <select id="literal_opcionTA" class="form-control" oninput="peticionAjax('../Cie','action=listarSubliteral&codigo='+this.value)" >
                   
                     
                 </select>
                        <span class="help-block"></span>
                    </div>
            
        </div>
        
        
    </div>
    <script type="text/javascript">
function opcionBusqueda(x){
    if(x==='1'){
       $("#opCapitulos").hide(); 
       $("#opCodigo").hide(); 
       $("#opDescripcion").show(); 
    }else if(x==='2'){
       $("#opCapitulos").hide(); 
       $("#opDescripcion").hide(); 
       $("#opCodigo").show(); 
        
    
    }else if(x==='3'){
       $("#opCapitulos").show(); 
       $("#opDescripcion").hide(); 
       $("#opCodigo").hide(); 
        
    }
}
function buscarXdescripcion(x){
    if(x.length  >= 5){
       peticionAjax("../Cie","action=buscarPorDesc&codigo="+x);
            
    }else{
    alertify.error('Debe escribir minimo 5 caracteres');
        
    }
}
function buscarXcodigo(x){
    if(x.length  >= 2){
       peticionAjax("../Cie","action=buscarPorCodigo&codigo="+x);
            
    }else{
    alertify.error('Debe escribir minimo 2 caracteres');
        
    }
}
function llenarTabla(json){
    var x = JSON.parse(json);
    var caja = $("#cajaResultados");
    caja.html("");
    $.each(x, function(i, item) {
      caja.append('<tr><td>'+item.id+' - '+item.descripcion+'</td><td> <button onclick="agregarOpcionPre(\''+item.id+' - '+item.descripcion+'\')" type="button" class="btn btn-info btn-circle"  title="Agregar a la preselección"> <i class="glyphicon glyphicon-pencil"></i></button></td></tr>')  
    
});
  
}
function llenarVersiculo(json){
    var x = JSON.parse(json);
    var caja = $("#versi_opcionTA");
    caja.html("");
      caja.append('<option value="">Seleccione...</option>') ;
    $.each(x, function(i, item) {
      caja.append('<option value="'+item.id+'">'+item.id+' - '+item.descripcion+'</option>')  ;
    
});
  
}
function llenarLiteral(json){
    var x = JSON.parse(json);
    var caja = $("#literal_opcionTA");
    caja.html("");
      caja.append('<option value="">Seleccione...</option>') ;
    $.each(x, function(i, item) {
      caja.append('<option value="'+item.id+'">'+item.id+' - '+item.descripcion+'</option>')  
    
});
  
}

        
    </script>
    <div  class="row">
        

        <table id="tablaOpcionesTextarea" style="width: 100%" class="table table-hover">
            <thead>
                <tr>
                    <th>Opción</th>
                    <th></th>

                </tr>
            </thead>
            <tbody id="cajaResultados">
          
            </tbody>

        </table>

    </div>
    <div  class="row">
        <legend>Preselección</legend>
        <textarea id="preseleccion" rows="10" class="col-md-12 form-control"></textarea>
       

    </div>


</div>
<div class="modal-footer">


    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
    <button type="button" class="btn btn-success" onclick="agregarOpcionesTextArea()">Agregar</button>

</div>





<script data-config>
//    var filtersConfig = {
//        base_path: '../tablefilter/',
//        col_1: 'none',
//        alternate_rows: true,
//        btn_reset: true,
//        paging: true,
//        results_per_page: ['Resultados por Pag.', [10, 25, 50, 100]],
//        rows_counter: true,
//        loader: true,
//        status_bar: true,
//        mark_active_columns: true,
//        highlight_keywords: true,
//        extensions: [{name: 'sort'}]
//    };
//
//    var tf = new TableFilter('tablaOpcionesTextarea', filtersConfig);
//    tf.init();


function agregarOpcionPre(x){
    console.log("---> "+x);
       var l = '';
          l += '*'+x+' ';
    $("#preseleccion").append(l)
    
}
function agregarOpcionesTextArea(){
       var l =   $("#preseleccion").val();
    
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