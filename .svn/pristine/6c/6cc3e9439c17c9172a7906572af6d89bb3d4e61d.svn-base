<%-- 
    Document   : RecibirPaciente_datosOcupacionales2
    Created on : 17/05/2016, 11:35:05 PM
    Author     : D4V3
--%>



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
%>

<div class="panel panel-default">

    <div class="panel-heading">
        <div class="row">
            <div class="col-lg-9">
                <h2 class="text-success">Datos basicos</h2>
            </div>
        </div>
    </div>
    <form   id="Form-Data" role="form" name="Form-Data"  method="POST" action="../S" autocomplete="off">
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
                <fieldset >
                    <legend> Antecedentes Personales </legend>
                    <div class="row"  >
<div class="form-group col-md-4">
  <label class="control-label" for="prependedcheckbox">Antecedente</label>
  <div class="">
    <div class="input-group">
      <span class="input-group-addon">     
          <input type="checkbox">     
      </span>
      <input id="prependedcheckbox" name="prependedcheckbox" class="form-control" placeholder="Observaciones" type="text">
    </div>
    <p class="help-block"></p>
  </div>
</div>
<div class="form-group col-md-4">
  <label class="control-label" for="prependedcheckbox">Antecedente</label>
  <div class="">
    <div class="input-group">
      <span class="input-group-addon">     
          <input type="checkbox">     
      </span>
      <input id="prependedcheckbox" name="prependedcheckbox" class="form-control" placeholder="Observaciones" type="text">
    </div>
    <p class="help-block"></p>
  </div>
</div>
<div class="form-group col-md-4">
  <label class="control-label" for="prependedcheckbox">Antecedente</label>
  <div class="">
    <div class="input-group">
      <span class="input-group-addon">     
          <input type="checkbox">     
      </span>
      <input id="prependedcheckbox" name="prependedcheckbox" class="form-control" placeholder="Observaciones" type="text">
    </div>
    <p class="help-block"></p>
  </div>
</div>
                        <hr/>

                        
                    </div>
                </fieldset>
                    <fieldset id="caja">
                        <legend>Signos Vitales</legend>
                        <div class="row ">
                            <div class="form-group col-md-4">
                                <label class="control-label" >Tension Arterial</label> 
                                <input id="tensionArterial" name="tensionArterial"  class="form-control" type="text" required >
                                <span class="help-block"></span>
                            </div>

                            <div class="form-group col-md-4">
                                <label class="control-label" >Frecuencia Cardiaca</label>  
                                <input id="FrecuenciaCardiaca" name="FrecuenciaCardiaca"  class="form-control" type="text" required >
                                <span class="help-block"></span>
                            </div>
                            <div class="form-group col-md-4">
                                <label class="control-label" >Frecuencia Respiratoria</label>  
                                <input id="FrecuenciaResp" name="FrecuenciaResp"  class="form-control" type="text" required >
                                <span class="help-block"></span>
                            </div>
                        </div>
                        <div class="row ">
                            <div class="form-group col-md-4">
                                <label class="control-label" >Peso</label> 
                                <input id="peso" name="peso"  class="form-control" type="text" required >
                                <span class="help-block"></span>
                            </div>

                            <div class="form-group col-md-4">
                                <label class="control-label" >Talla</label>  
                                <input id="talla" name="talla" placeholder="Talla en cm" class="form-control" type="num" max="250" required >
                                <span class="help-block"></span>
                            </div>
                            <div class="form-group col-md-4">
                                <label class="control-label">IMC <small>Peso/Talla<sup>2</sup></small></label>  
                                <input id="imc" name="imc"  class="form-control" type="number" required >
                                <span class="help-block"></span>
                            </div>
                            <div class="form-group col-md-4">
                                <label class="control-label" >Temperatura <sup>o</sup>C</label>  
                                <input id="temperatura" name="temperatura"  class="form-control" type="number" required >
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
                        <button  onclick="RecargaPanel('../panels/formularios/hc/RecibirPaciente_datosOcupacionales.jsp', 'panelprincipal')" type="button" class="btn btn-default bottom-right btn-outline">Atras </button>
                        <button  onclick="" type="submit" class="btn btn-success bottom-right btn-outline">Continuar</button>

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
    Location.href='../logout.jsp';
</script>
<%}%>
