<%-- 
    Document   : historiaBuscar
    Created on : 3/08/2017, 10:43:34 AM
    Author     : djgomez
--%>


<%@page import="ocupacional.bdatos.TipoDocumentoDAO"%>
<%@page import="ocupacional.valueobjects.TipoDocumentoVO"%>
<%@page import="ocupacional.JPA.valueobjects.ClientesServicio"%>
<%@page import="ocupacional.JPA.valueobjects.Clientes"%>
<%@page import="ocupacional.JPA.controlers.ClientesJpaController"%>
<%@page import="formularios.controlers.PacientesJpaController"%>
<%@page import="javax.persistence.Persistence"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<%@page import="ocupacional.bdatos.RolFuncionalidadDAO"%>
<%@page import="ocupacional.valueobjects.RolFuncionalidadVOs"%>
<%@page import="valeria.metodos.Cadenas"%>
<%@page import="ocupacional.bdatos.CiudadesDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="valeria.response.Mediador"%>
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
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div class="panel panel-default">

    <div class="panel-heading">
        <div class="row">
            <div class="col-lg-9">
                <h2 class="text-success">Buscar - Historia paciente</h2>
            </div>


        </div>
    </div>
    <div class="panel-body">
        <div class="row">
            <div class="form-group col-md-3">

                <label class="control-label" for="doc"># Documento*</label>
                <input  id="doc" name="doc" placeholder="Numero de documento"  class="form-control " type="number" required  >
                <span class="help-block"></span>
            </div>
            <div class="col-md-3">
                <button onclick="peticionAjax('../Pacientes','action=busPaciente&doc='+$('#doc').val())" class="btn btn-circle btn-primary" type="button" style="margin-top:  25px" > <i class="glyphicon glyphicon-search"></i></button>
                
            </div>


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
        $('.datepicker').datepicker();
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