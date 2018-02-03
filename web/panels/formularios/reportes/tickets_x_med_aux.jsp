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
    

<script type="text/javascript" src="../assets/js/tableExport/tableExport.js"></script>
<script type="text/javascript" src="../assets/js/tableExport/jquery.base64.js"></script>

    <div class="panel-body">
        <div class="row">

            <div class="form-group col-md-2">
                <label class="control-label" for="tipo">Tipo*</label>
                <select name="tipo" id="tipo" class="form-control">
                    <option value="">TODOS</option>
                    <option value="MEDICO">MEDICO</option>
                    <option value="AUXILIAR">AUXILIAR</option>
                </select>
                <span class="help-block"></span>
            </div>
            <div class="form-group col-md-2">
                <label class="control-label" for="fini">Desde*</label>
                <input  id="fini" name="fini" placeholder="yyyy-mm-dd"  class="form-control  datepicker" type="text"   >
                <span class="help-block"></span>
            </div>
            <div class="form-group col-md-2">
                <label class="control-label" for="ffin">Hasta*</label>
                <input  id="ffin" name="ffin" placeholder="yyyy-mm-dd"  class="form-control datepicker" type="text"   >
                <span class="help-block"></span>
            </div>
            <div class="form-group col-md-2">
                <label class="control-label" for="sede">Sede</label>
                <input  id="sede" name="sede" placeholder=""  class="form-control  " type="text"   >
                <span class="help-block"></span>
            </div>
            <div class="form-group col-md-2">
                <label class="control-label" for="doc">Documento</label>
                <input  id="doc" name="doc" placeholder=""  class="form-control " type="text" >
                <span class="help-block"></span>
            </div>
            <div class="col-md-2">
                <button onclick="
                        if (validarRequiered('fini=' + $('#fini').val() + '&ffin=' + $('#ffin').val() + '')) {
                            $('#tablaOrdenes').load('../panels/formularios/reportes/tickets_x_med_aux_tabla.jsp?fini=' + $('#fini').val() + '&ffin=' + $('#ffin').val() + '&tipo=' + $('#tipo').val() + '&sede=' + $('#sede').val() + '&doc=' + $('#doc').val() + '');
                        }
                        " class="btn btn-circle btn-primary" type="button" style="margin-top:  25px" > <i class="glyphicon glyphicon-search"></i></button>
                <button onclick="
                     $('#tablaRpt').tableExport({type: 'excel', tableName: 'tablaRpt', escape: 'false'});
                        " class="btn btn-circle btn-success" type="button" style="margin-top:  25px" > <i class="glyphicon glyphicon glyphicon-save"></i></button>

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

                </div>
            </div>
        </div>


    </div>

</div>


<script>
                    $(document).ready(function () {
                        ValidarFormID();
                        $('.datepicker').datepicker({
                            format: 'yyyy-mm-dd'
                        });
//        $("#ciudad").bselect();

                    });

                    $(".close").click(function () {
                        $('#Form-Data').bootstrapValidator('resetForm', true);
                    });


</script>
<%} else {%>
<script type="text/javascript">
    location.href = '../logout.jsp';
</script>
<%}%>