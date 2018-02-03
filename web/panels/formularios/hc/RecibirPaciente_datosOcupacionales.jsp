<%-- 
    Document   : RecibirPaciente_datosOcupacionales
    Created on : 17/05/2016, 11:11:08 PM
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
                <h2 class="text-success">Datos Historia clinica</h2>
            </div>
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

<fieldset>
                        <legend>Datos del Paciente</legend>
                        <div class="row col-xs-12">



                        </div>
                    </fieldset>


                    <fieldset>
                        <legend>Riesgos segun Norma Tecnica Colombiana 45</legend>
                        <div class="row ">
                            <div class="list-group col-md-4">
                                <div href="#" class="list-group-item active">FISICOS</div>
                                <div class="list-group-item checkbox"><label > <input type="checkbox" id="ruido" name="" class="">Ruido</label></div>
                                <div class="list-group-item checkbox"><label > <input type="checkbox" id="" name="" class="">Vibraciones</label></div>
                                <div class="list-group-item checkbox"><label > <input type="checkbox" id="" name="" class="">Presion Barometrica</label></div>
                                <div class="list-group-item checkbox"><label > <input type="checkbox" id="" name="" class="">Calor</label></div>
                                <div class="list-group-item checkbox"><label > <input type="checkbox" id="" name="" class="">Frio</label></div>
                                <div class="list-group-item checkbox"><label > <input type="checkbox" id="" name="" class="">Radiacion no Ionizante</label></div>
                                <div class="list-group-item checkbox"><label > <input type="checkbox" id="" name="" class="">Radiacion Ionizante</label></div>
                            </div>

                            <div class="list-group col-md-4">
                                <div href="#" class="list-group-item active">ERGONOMICOS</div>
                                <div class="list-group-item checkbox"><label > <input type="checkbox" id="ruido" name="" class="">Carga Estatica</label></div>
                                <div class="list-group-item checkbox"><label > <input type="checkbox" id="" name="" class="">Bipedo</label></div>
                                <div class="list-group-item checkbox"><label > <input type="checkbox" id="" name="" class="">Sedente</label></div>
                                <div class="list-group-item checkbox"><label > <input type="checkbox" id="" name="" class="">Carga Dinamica </label></div>
                                <div class="list-group-item checkbox"><label > <input type="checkbox" id="" name="" class="">Ezfuerzos </label></div>
                                <div class="list-group-item checkbox"><label > <input type="checkbox" id="" name="" class="">Movimientos</label></div>
                                <div class="list-group-item checkbox"><label > <input type="checkbox" id="" name="" class="">Otros</label></div>
                            </div>
                            <div class="list-group col-md-4">
                                <div href="#" class="list-group-item active">QUIMICOS</div>
                                <div class="list-group-item checkbox"><label > <input type="checkbox" id="ruido" name="" class="">Aerosoles Solidos</label></div>
                                <div class="list-group-item checkbox"><label > <input type="checkbox" id="" name="" class="">Aerosoles Liquidos</label></div>
                                <div class="list-group-item checkbox"><label > <input type="checkbox" id="" name="" class="">Gases</label></div>
                                <div class="list-group-item checkbox"><label > <input type="checkbox" id="" name="" class="">Vapores</label></div>
                                <div class="list-group-item form-group "> <label class="" for="observacionesQuimic">Observaciones:</label>  
                                    <textarea id="observacionesQuimic" class="form-control" rows="2"></textarea>
                                    <span class="help-block"></span></div>
                            </div>
                        </div>
                        <div class="row ">
                            <div class="list-group col-md-4">
                                <div href="#" class="list-group-item active">PSICOSOCIALES</div>
                                <div class="list-group-item checkbox"><label > <input type="checkbox" id="ruido" name="" class="">Contenido de Tarea</label></div>
                                <div class="list-group-item checkbox"><label > <input type="checkbox" id="" name="" class="">Relaciones Humanas</label></div>
                                <div class="list-group-item checkbox"><label > <input type="checkbox" id="" name="" class="">Gestion</label></div>
                                <div class="list-group-item checkbox"><label > <input type="checkbox" id="" name="" class="">Aislamiento</label></div>
                                <div class="list-group-item checkbox"><label > <input type="checkbox" id="" name="" class="">Organizacion Tiempo de trabajo</label></div>
                            </div>
                            <div class="list-group col-md-4">
                                <div href="#" class="list-group-item active">BIOLOGICOS</div>
                                <div class="list-group-item checkbox"><label > <input type="checkbox" id="ruido" name="" class="">Animal</label></div>
                                <div class="list-group-item checkbox"><label > <input type="checkbox" id="" name="" class="">Vegetal</label></div>
                                <div class="list-group-item checkbox"><label > <input type="checkbox" id="" name="" class="">Hongos</label></div>
                                <div class="list-group-item checkbox"><label > <input type="checkbox" id="" name="" class="">Bacterias</label></div>
                                <div class="list-group-item checkbox"><label > <input type="checkbox" id="" name="" class="">Parasitos </label></div>
                                <div class="list-group-item checkbox"><label > <input type="checkbox" id="" name="" class="">Virus</label></div>
                            </div>
                            <div class="list-group col-md-4">
                                <div href="#" class="list-group-item active">CONDICIONES DE SEGURIDAD</div>
                                <div class="list-group-item checkbox"><label > <input type="checkbox" id="" name="" class="">Factor de Riesgo Mecanico</label></div>
                                <div class="list-group-item checkbox"><label > <input type="checkbox" id="" name="" class="">Factor de Riesgo Electrico</label></div>
                                <div class="list-group-item checkbox"><label > <input type="checkbox" id="" name="" class="">Factor de Riesgo Locativo </label></div>
                                <div class="list-group-item checkbox"><label > <input type="checkbox" id="" name="" class="">Factor de Riesgo Fisico</label></div>
                                <div class="list-group-item checkbox"><label > <input type="checkbox" id="" name="" class="">Factor de Riesgo Quimico</label></div>
                                <div class="list-group-item form-group "> <label class="" for="observacionesSeg">Observaciones:</label>  

                                    <textarea id="observacionesSeg" class="form-control" rows="2"></textarea>
                                    <span class="help-block"></span></div>
                            </div>
                        </div>
                        <div class="row " >
                            <div class="form-group col-xs-12">
                                <label class="control-label" for="rp_nombre">Tiempo de Exposición al Riesgo*</label>  
                                <input id="rp_nombre" name="rp_nombre" placeholder="" class="form-control" type="text"  required>
                                <span class="help-block"></span>
                            </div>
                            <div class="form-group col-xs-12">
                                <label class="control-label" for="rp_tel">E.P.P*</label>  
                                <input id="rp_tel" name="rp_tel" placeholder="" class="form-control" type="tel"  required>
                                <span class="help-block"></span> 
                            </div>


                        </div>
                    </fieldset>
<fieldset>
                        <legend>Antecedentes Ocupacionales</legend>
                       

                            
                                <table class=" table table-striped" style="width: 95%;">
                                <thead>
                                    <tr>
                                <th>Empresa</th>
                                <th>Cargo</th>
                                <th>Tiempo</th>
                                <th>Riesgos</th>
                           </tr>
                                </thead>
                                
                            <tbody>
                                <tr>
                                    <td colspan="4">No se encontraron registros...</td>
                                    
                                </tr>
                                
                            </tbody>
                                
                            </table>
                            <br/>
                            
                            <div class="row ">
                                <div class="form-group col-md-4">
                                    <label class="control-label" >Empresa</label> 
                                    <input id="anoe_empresa" name="anoe_empresa" placeholder="" class="form-control" type="text" required >
                                    <span class="help-block"></span>
                                </div>

                                <div class="form-group col-md-4">
                                    <label class="control-label" >Cargo</label>  
                                    <input id="anoe_cargo" name="anoe_cargo" placeholder="" class="form-control" type="text" required >
                                    <span class="help-block"></span>
                                </div>
                                <div class="form-group col-md-4">
                                    <label class="control-label">Tiempo</label>  
                                    <input id="anoe_tiempo" name="anoe_tiempo" placeholder="" class="form-control" type="text" required >
                                    <span class="help-block"></span>
                                </div>
                            </div>
                            <div class="row">
                                <div class="checkbox col-md-12">
                                    <div class="checkbox-inline"><label > <input type="checkbox" id="RFisico" name="RFisico" class="">Fisico</label> </div>
                                    <div class="checkbox-inline"><label > <input type="checkbox" id="RErgonomico" name="RErgonomico" class="">Ergonomico</label></div>
                                    <div class="checkbox-inline"><label > <input type="checkbox" id="RQuimico" name="RQuimico" class="">Quimico</label></div>
                                    <div class="checkbox-inline"><label > <input type="checkbox" id="RPsicologico" name="RPsicologico" class="">Psicologico</label></div>
                                    <div class="checkbox-inline"><label > <input type="checkbox" id="RBiologico" name="RBiologico" class="">Biologico</label></div>
                                    <div class="checkbox-inline"><label > <input type="checkbox" id="RConddeSegu" name="RConddeSegu" class="">Cond de Segu</label></div>
                                    <div class="checkbox-inline"><label > <input type="checkbox" id="RAT" name="RAT" class="">AT</label></div>
                                    <div class="checkbox-inline"><label > <input type="checkbox" id="REL" name="REL" class="">EL</label></div>
                                </div>
                            </div>
                            <div class="row">
                            <div class=" col-md-10 ">
                                    
                                   
                                </div>
                            <div class=" col-md-2 ">
                                    
                                <input   class="btn btn-primary right" type="button" value="AGREGAR" >
                                   
                                </div>
                            </div>
                            <hr>
                            <div class="row " >
                                <div class="form-group col-md-4">
                                    <label class="control-label" for="rp_nombre">Lesiones de Accidente Laboral*</label>  
                                    <input id="rp_nombre" name="rp_nombre" placeholder="" class="form-control" type="text"  required>
                                    <span class="help-block"></span>
                                </div>
                                <div class="form-group col-md-4">
                                    <label class="control-label" for="rp_tel">Secuelas*</label>  
                                    <input id="rp_tel" name="rp_tel" placeholder="" class="form-control" type="tel"  required>
                                    <span class="help-block"></span> 
                                </div>
                                <div class="form-group col-md-4">
                                    <label class="control-label" for="rp_tel">Enfermedad Laboral*</label>  
                                    <input id="rp_tel" name="rp_tel" placeholder="" class="form-control" type="tel"  required>
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
                    <button  onclick="RecargaPanel('../panels/formularios/hc/RecibirPaciente_datosbasicos.jsp', 'panelprincipal')" type="button" class="btn btn-default bottom-right btn-outline">Atras </button>
                    <button  onclick="" type="submit" class="btn btn-success bottom-right btn-outline">Continuar</button>
                    <button  onclick="RecargaPanel('../panels/formularios/hc/RecibirPaciente_datosOcupacionales2.jsp', 'panelprincipal')" type="button" class="btn btn-success bottom-right btn-outline">s</button>

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