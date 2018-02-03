<%-- 
    Document   : con_pro_car_arc_dial
    Created on : 7/04/2016, 03:09:17 PM
    Author     : DJGOMEZ
--%>
<%@page import="valeria.metodos.ManejadorFechas"%>
<%@page import="formularios.entidades.Documentos"%>
<%@page import="formularios.controlers.AnotacionesJpaController"%>
<%@page import="formularios.entidades.Anotaciones"%>
<%@page import="javax.persistence.Persistence"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<%
String anot= request.getParameter("anot_id");
    ManejadorFechas  f = new ManejadorFechas();

EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaP1PU");
Anotaciones a  = new AnotacionesJpaController(emf).findAnotaciones(Integer.parseInt(anot));

%>

<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal">&times;</button>
    <form class="form"  encype="multipart/form-data" method="post" action="../panels/formularios/hc/car_arc.jsp" autocomplete="off" id="FormCarga" name="FormCarga">
        <div class="row">
            
        <div class="form-group col-md-6">

            <label class="control-label" for="Archivo_txt"> Seleccione...</label>
            <input class="" accept=".pdf"  required onchange="validarExtencion(this, 'pdf')" type="file" id="Archivo_txt" name="Archivo_txt" size="40"  />
            <span class="help-block"></span>
        </div>
        <div class="form-group col-md-4">
            <input type="hidden" name="anot_id" id="accion" value="<%=anot%>"/>
            <button type="button"    onclick="$('#FormCarga').submit()" class="btn btn-success" style="margin-top: 30px"><span class="glyphicon glyphicon-upload"></span></button>
        </div>


    </form>
        </div>
        </div>
        
    <div  class="modal-body">
        <div class="row" id="cajaDocumentos">

            <table id="tablaDocumentos" class=" table table-hover">
                <thead>
                    <tr>
                        <th>NOMBRE</th>
                        <th>PESO</th>
                        <th>FECHA</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <% 
                    for (Documentos d : a.getDocumentosList()){
                    %>
                    <tr>
                      <td><%=d.getDocuNombre() %></td>  
                      <td><%=d.getDocuPeso() %></td>  
                      <td><%=f.DevuelveFormato(d.getDocFechacambio()) %></td> 
                      <td>
                                       <a href='../Adjuntos/<%=d.getDocuRuta() %>' onclick="window.open(this.href, '', 'resizable=yes,location=no,menubar=no,scrollbars=yes,status=yes,toolbar=no,fullscreen=no,dependent=no,width=800,height=600,status');
                                           return false" title="ver" class="btn btn-circle btn-primary "> <span class="glyphicon glyphicon-search"></span></a>
                                             <a href='../Adjuntos/<%=d.getDocuRuta()%>' download='<%=d.getDocuNombre()%>' title="Descargar" class="btn btn-circle btn-success "> <span class="glyphicon glyphicon-download"></span> </a>
                                             <a href='#' onclick="
                                                 
                                                 alertify.confirm('¿Está seguro de remover este archivo?',function(e){
                                                     if(e){
                                                         removerArchivo(<%=d.getDocuId()%>)
                                             }
                                                 });
                                                 
                                                                                              " download='<%=d.getDocuNombre()%>' title="Eliminar" class="btn btn-circle btn-danger "> <span class="glyphicon glyphicon-remove"></span> </a>


                          
                      </td>
                        
                        
                    </tr>
                    <%}%>
                    
                </tbody>
            </table>


        </div>
    </div>
    <div class="modal-footer">

        <div class="left col-md-9" style="color: darkred">
            Nota:solo se admiten archivos tipo (PDF) y con tamaño inferior a <span id="tamanio"></span>.

        </div>

        <button type="button" class="left btn btn-default" data-dismiss="modal">Cerrar</button>

    </div>



    <!---------------------------->

    <script type="text/javascript">
        var max = 3072;
        $("#tamanio").html(max + " Kb");
        $('#Archivo_txt').bind('change', function () {
//                                 var max = 102400;
            if ((this.files[0].size) / 1024 > max) {
                alertify.error("El peso del archivo no puede superar los " + max + " Kb.")
                $(this).val("");
                $("#upload-file-info").html("");


            }
        });

    </script>



    <script>

        $("#FormCarga").bootstrapValidator({
            message: "This value is not valid",
            feedbackIcons: {
                valid: "glyphicon glyphicon-ok",
                invalid: "glyphicon glyphicon-remove",
                validating: "glyphicon glyphicon-refresh"
            },
            fields: {}
        }).on("success.form.bv", function (e) {
            e.preventDefault();
//            var $form = $(e.target);
//            var bv = $form.data("bootstrapValidator");
//    alert($form.serialize())    alert($form.attr("action"))
//
     var formData = new FormData(document.getElementById('FormCarga'));
                        var request = $('#FormCarga').attr("action");
                        $.ajax({
                            async: true,
                            type: 'POST',
                            dataType: 'html',
                            contentType: false,
                            processData: false,
                            url: request,
                            data: formData,
                            success: function (r) {
                              
                                if (r.trim() === '1') {
                                    $("#cajaDocumentos").load("../panels/formularios/hc/car_arc_dial.jsp?anot_id=<%=anot%> #tablaDocumentos");
                             alertify.success("Agregado correctamente ...");
                            
                              $('#FormCarga').bootstrapValidator('resetForm', true);
                                } else {
                                    alertify.error("Error en el procesamiento intente nuevamente...");
                                }
                            },
                            timeout: 100000,
                            error: function (jqXHR, textStatus, errorThrown) {
                                alert("error" + textStatus + "    " + errorThrown + "  " + jqXHR);
                            }
                        });

        });



function removerArchivo(id){

  $.post('../panels/formularios/hc/del_arc.jsp','docu_id='+id,function (e){
      
      if(e.trim()==='1'){
          
          
$('#cajaDocumentos').load('../panels/formularios/hc/car_arc_dial.jsp?anot_id=<%=anot%> #tablaDocumentos',function(){alertify.success('Removido correctamente ...');});
          
          
      }else{
          
          alertify.error("Error al procesar!");
          
      }
      
      
      
  });






}

    </script>
