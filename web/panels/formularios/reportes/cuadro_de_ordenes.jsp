<%-- 
    Document   : cuadro_de_ordenes
    Created on : 16/03/2017, 01:25:14 PM
    Author     : D4V3
--%>

<%@page import="ocupacional.bdatos.RolFuncionalidadDAO"%>
<%@page import="ocupacional.valueobjects.RolFuncionalidadVOs"%>
<%@page import="valeria.metodos.Cadenas"%>
<%@page import="valeria.response.Mediador"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%

    Mediador e = (Mediador) session.getAttribute("Mediador");
    Cadenas pc = new Cadenas();

    if (e != null) {

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
            <div class="col-lg-9">
                <h2 class="text-success">Reportes - Ordenes de servicio</h2>
            </div>


        </div>
    </div>
    <div class="panel-body">
        <div class="row">
              
              <div class="form-group col-md-3">
                 <label class="control-label" for="cliente_text">Estado*</label>
                 <select name="estado" id="estado" class="form-control" required="">
                     <option value="POR+PROCESAR">POR PROCESAR</option>
                     <option value="PROCESANDO">PROCESANDO</option>
                     <option value="PROCESADO">PROCESADO</option>
                     <option value="FACTURADO">FACTURADO</option>
                     <option value="ANULADO">ANULADO</option>
                 </select>
                <span class="help-block"></span>
            </div>
           
            
            <div class="form-group col-md-3">
                <label class="control-label" for="fini">Desde*</label>
                <input  id="fini" name="fini" placeholder=""  class="form-control  datepicker" type="text"   >
                <span class="help-block"></span>
            </div>
            <div class="form-group col-md-3">
                <label class="control-label" for="ffin">Hasta*</label>
                <input  id="ffin" name="ffin" placeholder=""  class="form-control datepicker" type="text"   >
                <span class="help-block"></span>
            </div>
            <div class="col-md-3">
                <button onclick="
                   if(validarRequiered('fini='+$('#fini').val()+'&ffin='+$('#ffin').val()+'&estado='+$('#estado').val()+'')){
    $('#tablaOrdenes').load('../panels/formularios/reportes/cuadro_de_ordenes_tabla.jsp?fini='+$('#fini').val()+'&ffin='+$('#ffin').val()+'&estado='+$('#estado').val()+'');
                   } 
                                        " class="btn btn-circle btn-primary" type="button" style="margin-top:  25px" > <i class="glyphicon glyphicon-search"></i></button>
                
            </div>


        </div>
        
        <div class="row" id="tablaOrdenes">
              
        </div>
       
       
        <!-- /.row (nested) -->
    </div>
    <!-- /.panel-body -->
    <div class="panel-footer">
        <div class="row">
            <div class="col-lg-9">
            </div>

            <div class="col-lg-3">
                <div class="form-group">
                    <!-- <button type="button" class="btn btn-default bottom-right btn-outline">Cancelar </button>
                     <button type="submit" class="btn btn-success bottom-right btn-outline">Registrar</button>
                    -->
                </div>
            </div>
        </div>


    </div>

</div>



<script>
    $(document).ready(function () {
        ValidarFormID();
        $('.datepicker').datepicker({
            format: 'dd-mm-yyyy'
        });
//        $("#ciudad").bselect();

    });

    $(".close").click(function () {
        $('#Form-Data').bootstrapValidator('resetForm', true);
    });


</script>
<%}else{%>
<script type="text/javascript">
    location.href='../logout.jsp';
</script>
<%}%>