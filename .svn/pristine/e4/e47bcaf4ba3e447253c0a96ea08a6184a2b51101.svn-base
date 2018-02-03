<%-- 
    Document   : Clientes_nuevo
    Created on : 3/07/2016, 02:31:26 PM
    Author     : D4V3
--%>
<%@page import="formularios.controlers.EmpleadosJpaController"%>
<%@page import="ocupacional.JPA.controlers.SedeJpaController"%>
<%@page import="ocupacional.JPA.valueobjects.Sede"%>
<%@page import="ocupacional.JPA.valueobjects.Empleadoexamen"%>
<%@page import="ocupacional.JPA.controlers.ExamenesJpaController"%>
<%@page import="ocupacional.JPA.valueobjects.Examenes"%>
<%@page import="ocupacional.JPA.valueobjects.Empleados"%>
<%@page import="ocupacional.JPA.controlers.TipodocumentoJpaController"%>
<%@page import="ocupacional.JPA.valueobjects.Tipodocumento"%>
<%@page import="java.util.List"%>
<%@page import="javax.persistence.TypedQuery"%>
<%@page import="javax.persistence.EntityManager"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<%@page import="javax.persistence.Persistence"%>
<%@page import="ocupacional.valueobjects.RolFuncionalidadVOs"%>
<%@page import="valeria.metodos.Cadenas"%>

<%@page import="java.util.ArrayList"%>
<%@page import="valeria.response.Mediador"%>
<%    Mediador e = (Mediador) session.getAttribute("Mediador");
    if (e != null) {
        Cadenas pc = new Cadenas();
        pc.setRequest(request);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaP");
//        ClientesJpaController clieDAO = new ClientesJpaController(emf);
        TipodocumentoJpaController tidoDAO = new TipodocumentoJpaController(emf);
        EmpleadosJpaController edao = new EmpleadosJpaController(emf);
        Empleados evo = new Empleados();
//        String tipo = pc.getvariable("tipocliente");
        String tido = pc.getvariable("tido");
        String doc = pc.getvariable("doc");
//        String id_ocu = pc.getvariable("id_ocu");
        System.out.println("consulta>1");

        System.out.println("consulta>2");

        if (tido != null && doc != null && !tido.equals("") && !doc.equals("")) {

            evo.setTidoId(tidoDAO.findTipodocumento(Integer.parseInt(tido)));
            evo.setEmplDocumento(doc);

        } else if (request.getParameter("id_ocu") != null) {

            evo = edao.findEmpleados(Integer.parseInt(request.getParameter("id_ocu")));

        }


%>

<div class="panel panel-default">

    <div class="panel-heading">
        <div class="row">
            <div class="col-lg-9">
                <h2 class="text-success">Nuevo Empleado</h2>
            </div>
        </div>
    </div>
    <form   id="Form-Data" role="form" name="Form-Data"  method="POST" action="../Empleados" autocomplete="off">
        <div class="panel-body">
            <div class="row">





                <fieldset  style="padding: 10px">
                    <legend>Datos Basicos</legend>

                    <div class="row"> 
                        <div class="form-group col-md-4">

                            <label class="control-label" for="paci_tipodocumento">tipo doc.*</label>
                            <input  id="paci_tipodocumento" name="" readonly placeholder=""  class="form-control " type="text" required  value="<%                                if (evo.getTidoId() != null) {

                                    out.println(evo.getTidoId().getTidoDescripcion());
                                }

                                System.out.println("4");

                                    %>" >
                            <input type="hidden" name="tipo_documento" value="<%if (evo.getTidoId() != null) {
                                    out.println(evo.getTidoId().getTidoId().toString());
                                }%>">
                            <span class="help-block"></span>

                        </div> 
                        <div class="form-group col-md-4">

                            <label class="control-label" for="documento">No. doc.*</label>
                            <input  id="documento" name="documento" placeholder=""  readonly="" pattern="[0-9]{4}" class="form-control " type="text" required  value="<%=pc.notEmpty(evo.getEmplDocumento())%>" >
                            <span class="help-block"></span>
                        </div>


                        <div class="form-group col-md-4">

                            <label class="control-label" for="nombres">
                                Nombres*

                            </label>
                            <input  id="nombres" name="nombres" placeholder=""    class="form-control " type="text" required  value="<%=pc.notEmpty(evo.getEmplNombres())%>" >
                            <span class="help-block"></span>
                        </div>
                        <div class="form-group col-md-4">

                            <label class="control-label" for="cargo">
                                Cargo*

                            </label>
                            <select id="cargo" name="cargo" class="form-control" required>
                                <option value="">Seleccione..</option>
                                <option value="ADMINISTRADOR" <%if(pc.notEmpty(evo.getEmplCargo()).equals("ADMINISTRADOR")){%>selected=""<%}%>>ADMINISTRADOR</option>
                                <option value="AUXILIAR ADMINISTRATIVO" <%if(pc.notEmpty(evo.getEmplCargo()).equals("AUXILIAR ADMINISTRATIVO")){%>selected=""<%}%>>AUXILIAR ADMINISTRATIVO</option>
                                <option value="AUXILIAR DE ENFERMERIA" <%if(pc.notEmpty(evo.getEmplCargo()).equals("AUXILIAR DE ENFERMERIA")){%>selected=""<%}%>>AUXILIAR DE ENFERMERIA</option>
                                <option value="AUXILIAR DE LABORATORIO" <%if(pc.notEmpty(evo.getEmplCargo()).equals("AUXILIAR DE LABORATORIO")){%>selected=""<%}%>>AUXILIAR DE LABORATORIO</option>
                                <option value="BACTERIOLOGO" <%if(pc.notEmpty(evo.getEmplCargo()).equals("BACTERIOLOGO")){%>selected=""<%}%>>BACTERIOLOGO</option>
                                <option value="FONOAUDIOLOGO" <%if(pc.notEmpty(evo.getEmplCargo()).equals("FONOAUDIOLOGO")){%>selected=""<%}%>>FONOAUDIOLOGO</option>
                                <option value="MEDICO E.S.O" <%if(pc.notEmpty(evo.getEmplCargo()).equals("MEDICO E.S.O")){%>selected="" <%}%>>MEDICO E.S.O</option>
                                <option value="OPTOMETRA" <%if(pc.notEmpty(evo.getEmplCargo()).equals("OPTOMETRA")){%>selected=""<%}%>>OPTOMETRA</option>
                                <option value="PSICOLOGO E.S.O" <%if(pc.notEmpty(evo.getEmplCargo()).equals("PSICOLOGO E.S.O")){%>selected=""<%}%>>PSICOLOGO E.S.O</option>
                                <option value="PSICOLOGO ORGANIZACIONAL" <%if(pc.notEmpty(evo.getEmplCargo()).equals("PSICOLOGO ORGANIZACIONAL")){%>selected=""<%}%>>PSICOLOGO ORGANIZACIONAL</option>
                                <option value="TERAPISTA RESPIRATORIO" <%if(pc.notEmpty(evo.getEmplCargo()).equals("TERAPISTA RESPIRATORIO")){%>selected=""<%}%>>TERAPISTA RESPIRATORIO</option>

                            </select>
                            
                            <span class="help-block"></span>
                        </div>

                        <div class="form-group col-md-4"> 
                            <label class=" control-label" for="sede">Sede*</label> 
                            <select id="sede" name="sede" class="form-control" required>
                                <option value="">Seleccione..</option>
                                <%
                                    for (Sede t : new SedeJpaController(emf).findSedeEntities()) {
                                        System.out.println("5");

                                %>

                                <option value="<%=t.getSedeId()%>" ><%=t.getSedeNombre()%></option>

                                <% }%>
                            </select>
                            <%if (evo.getSedeId() != null) {%>
                            <script>
                                $("#sede").val(<%=pc.notEmpty(String.valueOf(evo.getSedeId().getSedeId()))%>);
                            </script>
                            <% }%> 




                            <span class="help-block"></span>

                        </div> 
                              <div class="form-group col-md-4">

                            <label class="control-label" for="firma_input">
                              Firma:

                            </label>
                                  <input  id="firma_input" name="firma_input" accept="image/*"  class="" type="file"  onchange="encodeImageFileAsURL();" >
                                  <input  id="firma_txt" name="firma_txt"   class="form-control " type="hidden" value="<%=pc.notEmpty(evo.getEmplFirma())%>">
                            <span class="help-block"></span>
                        </div>
                          

<script type='text/javascript'>
  function encodeImageFileAsURL() {

    var filesSelected = document.getElementById("firma_input").files;
    if (filesSelected.length > 0) {
      var fileToLoad = filesSelected[0];

      var fileReader = new FileReader();

      fileReader.onload = function(fileLoadedEvent) {
        var srcData = fileLoadedEvent.target.result; // <--- data: base64

$("#firma_txt").val(srcData);
        var newImage = document.createElement('img');
        newImage.src = srcData;
        newImage.width = '400';

        document.getElementById("img_firma").innerHTML = newImage.outerHTML;
      }
      fileReader.readAsDataURL(fileToLoad);
    }
  }
</script>
                    </div>   

                            <div class="row ">
                            <div class="form-group col-md-4" id="img_firma">
                                
                                <% if(evo.getEmplFirma()!=null){%>
                                <img src="<%=pc.notEmpty(evo.getEmplFirma())%>" alt=" Aqui va la firma..." width="400" />
                                <%}else{%>
                              Aqui va la firma...
                                <%}%>
                        </div>  
                            </div>
                </fieldset>

                <fieldset >
                    <legend>  <a href="#Servicios" class="" data-toggle="collapse">Examenes Asignados</a></legend>
                    <div class="row col-xs-12 " id="Servicios" >

                        <div id="cajaFormExamenes">

                            <%

                                List<Empleadoexamen> listaClienteServicio = (List<Empleadoexamen>) session.getAttribute("listaEmpleadoExamen");

                                if (request.getParameter("id_ocu") != null) {

                                    listaClienteServicio = evo.getEmpleadoexamenList();
                                    session.setAttribute("listaEmpleadoExamen", listaClienteServicio);
                                }
                                System.out.println("consulta>6");

                                if (listaClienteServicio != null) {
                                    System.out.println("entre");
                            %>
                            <table id="tablaFormExamenes" class="table table-hover">
                                <thead>
                                    <tr>
                                        <th>Servicio</th>

                                        <th></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        int cont = 0;
                                        for (Empleadoexamen c : listaClienteServicio) {

                                    %>
                                    <tr>
                                        <td><%= c.getExamId().getExamDescripcion()%></td>                                            

                                        <td><button type="button" title="Remover" onclick="peticionAjax('../Empleados', 'action=removerExamen&hash=<%= c.hashCode()%>')" class="btn-circle btn-danger bottom-right btn-outline"><i class="glyphicon glyphicon-remove"></i> </button></td>

                                    </tr>
                                    <% }%>
                                </tbody>
                            </table>

                            <% }%>

                        </div>


                        <div class="form-group col-md-4">
                            <label class="control-label" for="Servicio_sel">Examen</label>  
                            <select id="exam_sel"  name="Servicio_sel" onchange="$('#agrServicio').removeAttr('disabled')" class="form-control"   >
                                <option value="">Seleccione..</option>
                                <%  for (Examenes s : new ExamenesJpaController(emf).findExamenesEntities()) {

                                        if (s.getExamEstado().equals("ACTIVO")) {

                                %>

                                <option value="<%=s.getExamId()%>"><%=s.getExamDescripcion()%>
                                </option>


                                <%                                        }

                                %>




                                <% }%>
                            </select>
                            <span class="help-block"></span>
                        </div>

                        <div class="alignRight col-md-4">
                            <br/>
                            <button type="button" id="agrServicio"  class="btn btn-primary right" onclick="peticionAjax('../Empleados', 'action=AgregarExamen&Exam_sel=' + $('#exam_sel').val()), this.disabled = true">Agregar</button>

                        </div>
                    </div>
                </fieldset>

                <!-- /.row (nested) -->
            </div>
        </div>
        <!-- /.panel-body -->
        <div class="panel-footer">
            <div class="row">


                <div class="col-lg-9 right">

                </div>
                <div class="col-lg-3 right">
                    <div class="form-group">
                        <button  onclick="RecargaPanel('../panels/formularios/basicas/Empleados.jsp', 'panelprincipal')" type="button" class="btn btn-default bottom-right btn-outline">Atras </button>


                        <%
                            if (request.getParameter("id_ocu") != null) {
                        %>
                        <input type="hidden" name="action" value="editarEmpleado">
                        <input type="hidden" name="id_ocu" value="<%=request.getParameter("id_ocu")%>">

                        <%
                        } else {
                        %>
                        <input type="hidden" name="action" value="crearEmpleado">

                        <%
                            }
                        %>
                        <button  onclick="" type="submit" class="btn btn-success bottom-right btn-outline">Guardar</button>

                    </div>
                </div>
            </div>


        </div>
    </form>
</div>


<script>
    $(document).ready(function () {
        ValidarFormID();
//        $('.datepicker').datepicker();


    });


</script>
<% }else{%>
<script type="text/javascript">
    location.href='../logout.jsp';
</script>
<% }%>