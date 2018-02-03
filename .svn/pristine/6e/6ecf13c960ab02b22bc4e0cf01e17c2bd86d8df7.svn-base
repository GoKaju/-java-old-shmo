<%-- 
    Document   : CrearNatural
    Created on : 23/01/2016, 11:40:06 PM
    Author     : D4V3
--%>

<%@page import="ocupacional.bdatos.DepartamentosDAO"%>
<%@page import="ocupacional.valueobjects.DepartamentosVO"%>
<%@page import="ocupacional.bdatos.CiudadesDAO"%>
<%@page import="ocupacional.valueobjects.CiudadesVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="valeria.response.Mediador"%>
<%@page import="ocupacional.bdatos.PaisDAO"%>
<%@page import="ocupacional.valueobjects.PaisVO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%    Mediador e = (Mediador) session.getAttribute("Mediador");%>

<form   id="Form-Data" role="form" name="Form-Data"  method="POST" action="sistema" autocomplete="off">
    <div class="panel panel-default">

        <div class="panel-heading">
            <h2 class="text-success">Registrar persona </h2>
        </div>
        <div class="panel-body">
            <div class="row">


                <fieldset>

                    <!-- Form Name -->

                    <div class="col-lg-6">
                        <!-- Text input-->
                        <div class="form-group">
                            <label class="col-md-4 control-label" for="natu_primernombre">Primer nombre</label>  
                            <div class="col-md-6">
                                <input id="natu_primernombre" name="natu_primernombre" placeholder="" class="form-control input-sm" type="text" required="true" tabindex="1">
                                <span class="help-block"></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4 control-label" for="natu_primerapellido">Primer Apellido</label>  
                            <div class="col-md-6">
                                <input id="natu_primerapellido" name="natu_primerapellido" placeholder="" class="form-control input-sm" type="text" required="true" tabindex="3" >
                                <span class="help-block"></span>
                            </div>
                        </div>



                    </div>
                    <div class="col-lg-6">
                        <!-- Text input-->
                        <div class="form-group">
                            <label class="col-md-4 control-label" for="natu_segundonombre">Segundo nombre</label>  
                            <div class="col-md-6">
                                <input id="natu_segundonombre" minlength="1" name="natu_segundonombre" placeholder="" class="form-control input-sm" type="text" required="true" accesskey="" tabindex="2" >
                                <span class="help-block"></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4 control-label" for="natu_segundoapellido">Segundo Apellido</label>  
                            <div class="col-md-6">
                                <input id="natu_segundoapellido" minlength="1" name="natu_segundoapellido" placeholder="" class="form-control input-sm" type="text" required="true" tabindex="4" >
                                <span class="help-block"></span>
                            </div>
                        </div>

                    </div>
                </fieldset>
                <fieldset>
                    <legend></legend>

                    <!-- Form Name -->

                    <div class="col-lg-6">
                        <!-- Text input-->
                        <div class="form-group">
                            <label class="col-md-4 control-label" for="pais">Pais</label>  
                            <div class="col-md-6">
                                <select id="pais" name="pais" class="form-control" required onchange="RecargaSelect('../panels/formularios/CrearJuridica.jsp?p_ocu=' + this.value, 'departamentocaja', 'departamento')">

                                    <option value="">Seleccione..</option>
                                    <%
                                        for (PaisVO p : (ArrayList<PaisVO>) new PaisDAO(e).Listar()) {

                                    %>
                                    <option value="<%=p.getPais_id()%>"><%=p.getPais_nombre()%></option>
                                    <%

                                        }
                                    %>

                                </select>
                                <span class="help-block"></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4 control-label" for="ciud_id">Ciudad</label>  
                            <div class="col-md-6">
                                <div  id="ciudadcaja">
                                    <select id="ciudad" name="ciudad" class="form-control" required="">
                                        <option value="">Seleccione..</option>
                                        <%
                                            if (request.getParameter("d_ocu") != null) {
                                                for (CiudadesVO p : (ArrayList<CiudadesVO>) new CiudadesDAO(e).Listar()) {

                                                    if (p.getDepa_id().equals(request.getParameter("d_ocu"))) {

                                        %>

                                        <option value="<%=p.getCiud_id()%>"><%=p.getCiud_nombre()%>
                                        </option>


                                        <% }
                                                }
                                            }%>

                                    </select>
                                </div>
                                <span class="help-block"></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4 control-label" for="pege_telefono">Telefono</label>  
                            <div class="col-md-6">
                                <input id="pege_telefono" name="pege_telefono" placeholder="" class="form-control input-sm" type="number"  >
                                <span class="help-block"></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4 control-label" for="pege_email">Email</label>  
                            <div class="col-md-6">
                                <input id="pege_email" name="pege_email" placeholder="" class="form-control input-sm" type="email"  required="">
                                <span class="help-block"></span>
                            </div>
                        </div>



                    </div>
                    <div class="col-lg-6">
                        <!-- Text input-->
                        <div class="form-group">
                            <label class="col-md-4 control-label" for="departamento">Departamento</label>  
                            <div class="col-md-6">
                                <div  id="departamentocaja">
                                    <select id="departamento" name="departamento" class="form-control" required onchange="RecargaSelect('../panels/formularios/CrearJuridica.jsp?d_ocu=' + this.value, 'ciudadcaja', 'ciudad')">
                                        <option value="">Seleccione..</option>
                                        <%
                                            if (request.getParameter("p_ocu") != null) {
                                                for (DepartamentosVO p : (ArrayList<DepartamentosVO>) new DepartamentosDAO(e).Listar()) {

                                                    if (p.getPais_id().equals(request.getParameter("p_ocu"))) {

                                        %>

                                        <option value="<%=p.getDepa_id()%>"><%=p.getDepa_nombre()%>
                                        </option>


                                        <% }
                                                }
                                            }%>

                                    </select>
                                </div>
                                <span class="help-block"></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4 control-label" for="pege_direccion">Direccion</label>  
                            <div class="col-md-6">
                                <input id="pege_direccion" minlength="1" name="pege_direccion" placeholder="" class="form-control input-sm" type="text" required="true" accesskey="" tabindex="2" >
                                <span class="help-block"></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4 control-label" for="pege_celular">Celular</label>  
                            <div class="col-md-6">
                                <input id="pege_celular" minlength="1" name="pege_celular" placeholder="" class="form-control input-sm" type="number" required="true" tabindex="4" >
                                <span class="help-block"></span>
                            </div>
                        </div>

                    </div>
                </fieldset>
                <fieldset>
                    <legend></legend>
                    <div class="col-lg-6">
                        <!-- Text input-->
                        <div class="form-group">
                            <label class="col-md-4 control-label" for="natu_fechanacimiento">Fecha de Nac.</label>  
                            <div class="col-md-6">
                                <input id="natu_fechanacimiento" name="natu_fechanacimiento" placeholder="" class="form-control input-sm " type="text" required="true" >
                                <span class="help-block"></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4 control-label" for="natu_gruposanguineo">Grupo sanguineo</label>  
                            <div class="col-md-6">
                                <input id="natu_gruposanguineo" name="natu_gruposanguineo" placeholder="" class="form-control input-sm" type="text" required >
                                <span class="help-block"></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4 control-label" for="natu_estrato">Estrato</label>  
                            <div class="col-md-6">
                                <input id="natu_estrato" name="natu_estrato" placeholder="" class="form-control input-sm" type="number" required   >
                                <span class="help-block"></span>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-6">
                        <!-- Text input-->
                        <div class="form-group">
                            <label class="col-md-4 control-label" for="sexo_id">Sexo</label>  
                            <div class="col-md-6">
                                <select id="sexo_id" name="sexo_id" class="form-control" required>
                                    <option value="">Seleccione..</option>
                                </select>
                                <span class="help-block"></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4 control-label" for="esci_id">Estado civil</label>  
                            <div class="col-md-6">
                                <select id="esci_id" name="esci_id" class="form-control" required>
                                    <option value="">Seleccione..</option>

                                </select>
                                <span class="help-block"></span>
                            </div>
                        </div>


                    </div>
                </fieldset>
                <fieldset>

                    <legend></legend>
                    <div class="col-lg-6">
                        <div class="form-group">
                            <label class="col-md-4 control-label" for="enti_ideps">Eps</label>  
                            <div class="col-md-6">
                                <select id="enti_ideps" name="enti_ideps" class="form-control" required>
                                    <option value="">Seleccione..</option>

                                </select>
                                <span class="help-block"></span>
                            </div>
                        </div></div>
                    <div class="col-lg-6">
                        <div class="form-group">
                            <label class="col-md-4 control-label" for="enti_idarl">Arl</label>  
                            <div class="col-md-6">
                                <select id="enti_idarl" name="enti_idarl" class="form-control" required>
                                    <option value="">Seleccione..</option>

                                </select>
                                <span class="help-block"></span>
                            </div>
                        </div>
                    </div>


                </fieldset>





                <!-- /.col-lg-6 (nested) -->

            </div>   
            <!-- /.row (nested) -->
        </div>
        <!-- /.panel-body -->
        <div class="panel-footer">
            <div class="form-group">
                <button type="button" class="btn btn-default bottom-right btn-outline">Cancelar </button>

                <button type="submit" class="btn btn-success bottom-right btn-outline">Registrar</button>
            </div>
        </div>

    </div>
</form>
<script>
    $(document).ready(function () {
        ValidarFormID();

    });

</script>
