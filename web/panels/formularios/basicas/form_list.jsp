<%-- 
    Document   : Clientes    Created on : 14/02/2016, 12:15:05 AM
    Author     : D4V3
--%>
<%@page import="ocupacional.JPA.controlers.ExamenesJpaController"%>
<%@page import="formularios.controlers.FormulariosJpaController"%>
<%@page import="formularios.entidades.Formularios"%>
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
                                            session.removeAttribute("listaClienteServicio");




 
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div class="panel panel-default">

    <div class="panel-heading">
        <div class="row">
            <div class="col-lg-9">
                <h2 class="text-success">Visualizar formularios</h2>
            </div>

           
        </div>
    </div>
    <div class="panel-body">
        <div class="row">
            <fieldset>
                <legend></legend>
                <!-- Table -->
                <table id="tabla" class="table table-hover ">
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>ID </th>
                            <th>EXAMEN</th>
                            <th>TITULO</th>
                            <th>DESCRIPCION</th>
                            <th>ACCIONES</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%

                            EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaP");
                            EntityManagerFactory emf2 = Persistence.createEntityManagerFactory("JavaP1PU");
                            int cont = 1;

                            for (Formularios f : new FormulariosJpaController(emf2).findFormulariosEntities()) {

                        %>
                        <tr>
                            <td><%=cont++%></td>
                            <td><%=f.getFormId()%></td>
                          <% if(f.getExamId()!=null){%>  <td><%=new ExamenesJpaController(emf).findExamenes(f.getExamId()).getExamDescripcion() %></td>
                          <%}else{%> 
                          <td>No definido</td>
                          <%}%> 
                          <td><%=f.getFormTitulo()%></td>
                            <td><%=f.getFormDescripcion()%></td>
                            <td><button title="Ver" type="button" value="<%=f.getFormId()%>"  class="btn-circle btn-success bottom-right btn-outline" onclick="RecargaPanel('../panels/formularios/basicas/form_visualizar.jsp?idform='+this.value, 'panelprincipal')"><i class="glyphicon glyphicon-search"></i> </button>
                        </tr>
                        <%      

                            }
                        %>
                    </tbody>
                </table>
            </fieldset>
            <!-- /.col-lg-6 (nested) -->
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

<!-- modal  fin -->
<script data-config>
    var filtersConfig = {
        base_path: '../tablefilter/',
   
        col_5: 'none',
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

</script>

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