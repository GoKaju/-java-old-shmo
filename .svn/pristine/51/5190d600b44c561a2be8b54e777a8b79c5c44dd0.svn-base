<%-- 
    Document   : RecibirPaciente_datosbasicos
    Created on : 17/05/2016, 10:57:06 PM
    Author     : D4V3
--%>


<%@page import="ocupacional.bdatos.EstadoCivilDAO"%>
<%@page import="ocupacional.bdatos.EntidadesDAO"%>
<%@page import="ocupacional.bdatos.TipoDocumentoDAO"%>
<%@page import="ocupacional.valueobjects.TipoDocumentoVO"%>
<%@page import="ocupacional.valueobjects.PersonaGeneralVO"%>
<%@page import="ocupacional.bdatos.RolFuncionalidadDAO"%>
<%@page import="ocupacional.valueobjects.RolFuncionalidadVOs"%>
<%@page import="valeria.metodos.Cadenas"%>

<%@page import="java.util.ArrayList"%>
<%@page import="valeria.response.Mediador"%>
<%    Mediador e = (Mediador) session.getAttribute("Mediador");
    if (e != null) {

        Cadenas pc = new Cadenas();
        
                                    EntidadesDAO edao= new EntidadesDAO(e);
                                    EstadoCivilDAO cvildao= new EstadoCivilDAO(e);
                                    

%>

<div class="panel panel-default">

    <div class="panel-heading">
        <div class="row">
             <center>
            
            <div class="col-lg-9">
                <h2 class="text-success">Datos basicos</h2> 
            </div>
                 
                 </center>
        </div>
    </div>
                <form   id="Form-Data" role="form" name="Form-Data"  method="POST" action="../" autocomplete="off">
    <div class="panel-body">
        <div class="row">


            <%
                if (true) {
//                PersonaGeneralVO pege = new PersonaGeneralVO();
//                TipoDocumentoVO td = new TipoDocumentoVO();
//              
//                String title = "Nuevo Ticket";
////            NaturalesVO natu = new NaturalesVO();
//                JuridicasVO juri = new JuridicasVO();
//                if (request.getParameter("clie_ocu") != null) {
//                    for (ClientesVO c : new ClientesDAO(e).Listar()) {
//                        if (c.getClie_id().equals(request.getParameter("clie_ocu"))) {
//                            clie = c;
//                        }
//                    }
//                    title = "Modificar Ticket";
//                    pege = new PersonaGeneralDAO(e).Cargar(clie.getPege_id());
//                    td = new TipoDocumentoDAO(e).Cargar(pege.getTido_id());
////                natu = new NaturalesDAO(e).Cargar(pege.getPege_id());
//                    juri = new JuridicasDAO(e).Cargar(pege.getPege_id());
//                }
            %>


            <fieldset id="caja">
                <div class="row">
                    <div class="form-group col-md-4">
                        <label class="control-label" for="nombre1">Primer Nombre*</label> 
                        <input id="nombre1" name="nombre1" placeholder="" class="form-control" type="text" required >
                        <span class="help-block"></span>
                    </div>
                    <div class="form-group col-md-4">
                        <label class="control-label" >Segundo Nombre</label> 
                        <input  name="nombre2" placeholder="" class="form-control" type="text"  >
                        <span class="help-block"></span>
                    </div>
                    <div class="form-group col-md-4">
                        <label class="control-label" >Primer Apellido*</label>  
                        <input  name="apellido1" placeholder="" class="form-control" type="text" required >
                        <span class="help-block"></span>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-md-4">
                        <label class="control-label" >Segundo Apellido</label> 
                        <input  name="apellido2" placeholder="" class="form-control" type="text"  >
                        <span class="help-block"></span>
                    </div>
                    <div class="form-group col-md-4">
                        <label class="control-label" >Fecha de Nacimiento*</label> 
                        <input  name="fechaNacimiento" placeholder="" class="form-control datepicker" type="text" required >
                        <span class="help-block"></span>
                    </div>
                    <div class="form-group col-md-4">
                        <label class="control-label" >Estado Civil*</label>  
                        <select  name="estadoCivil" class="form-control" required >
                            <option value="">Seleccione..</option>
                            <option value="">Soltero</option>
                            <option value="">Casado</option>
                            <option value="">Viudo</option>
                            <option value="">Divorciado</option>
                            <option value="">Union Marital de Hecho</option>
                        </select>
                        <span class="help-block"></span>
                    </div>
                </div>
                <div class="row ">
                    <div class="form-group col-md-4">
                        <label class="control-label" >Grupo Sanguineo*</label> 
                        <input  name="GrupoSanguineo" placeholder="" class="form-control" type="text" required >
                        <span class="help-block"></span>
                    </div>
                    <div class="form-group col-md-4">
                        <label class="control-label" >EPS*</label> 
                        <select  name="eps" class="form-control" required onchange="">
                            <option value="">Seleccione..</option>

                        </select>
                        <span class="help-block"></span>
                    </div>
                    <div class="form-group col-md-4">
                        <label class="control-label" >ARL*</label>  
                        <select  name="arl" class="form-control" required onchange="">
                            <option value="">Seleccione..</option>
                            
                            <%
                            
                            
                            %>
                            

                        </select>
                        <span class="help-block"></span>
                    </div>
                </div>
            </fieldset>
            <fieldset>
                <legend>Datos de Ubicacion</legend>

                <div class="row" >
                   
                    <div class="form-group col-md-4">
                        <label class="control-label" for="ciudad">Ciudad</label>  
                        <div  id="ciudadcaja">
                            <select id="ciudad" name="ciudad" class="form-control" required>
                                <option value="">Seleccione..</option>

                            </select>
                        </div>
                        <span class="help-block"></span>
                    </div>
                    <div class="form-group col-md-4">
                        <label class="control-label" for="pege_direccion">Direccion*</label>  
                        <input id="pege_direccion"  name="pege_direccion" placeholder="" class="form-control" type="text" required  >
                        <span class="help-block"></span>
                    </div>
                    <div class="form-group col-md-4">
                        <label class="control-label" for="pege_telefono">Telefono</label>  
                        <input id="pege_telefono" name="pege_telefono" placeholder="" class="form-control " type="tel" >
                        <span class="help-block"></span>
                    </div>
                </div>
                <div class="row ">
                    <div class="form-group col-md-4">
                        <label class="control-label" for="pege_celular">Celular*</label>  
                        <input id="pege_celular"  name="pege_celular" placeholder="" maxlength="10" pattern="[0-9]{10}" class="form-control" type="text" required >
                        <span class="help-block"></span>
                    </div>
                </div>

            </fieldset>
          


            <% }%>
        </div>   
        <!-- /.row (nested) -->
    </div>
    <!-- /.panel-body -->
    <div class="panel-footer">
        <div class="row">


            <div class="col-lg-9 right">

            </div>
            <div class="col-lg-3 right">
                <div class="form-group">
                    <button  onclick="RecargaPanel('../panels/formularios/basicas/empleados.jsp', 'panelprincipal')" type="button" class="btn btn-default bottom-right btn-outline">Atras </button>
                    <button  onclick="" type="submit" class="btn btn-success bottom-right btn-outline">Continuar</button>
                    <button  onclick="//RecargaPanel('../panels/formularios/hc/RecibirPaciente_datosOcupacionales.jsp', 'panelprincipal')" type="button" class="btn btn-success bottom-right btn-outline">s</button>

                </div>
            </div>
        </div>


    </div>
</form>
</div>


<script>
    $(document).ready(function () {
        ValidarFormID();
        $('.datepicker').datepicker();


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