<%-- 
    Document   : form
    Created on : 18/06/2016, 08:26:51 PM
    Author     : D4V3
--%>
<%@page import="formularios.entidades.Documentos"%>
<%@page import="ocupacional.JPA.controlers.EntidadesJpaController"%>
<%@page import="ocupacional.JPA.controlers.EstadocivilJpaController"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="ocupacional.JPA.valueobjects.ServiciosExamenes"%>
<%@page import="ocupacional.JPA.valueobjects.Servicios"%>
<%@page import="ocupacional.JPA.valueobjects.Examenes"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="valeria.metodos.ManejadorFechas"%>
<%@page import="formularios.controlers.AnotacionesJpaController"%>
<%@page import="formularios.entidades.Respuestas"%>
<%@page import="java.util.List"%>
<%@page import="javax.persistence.TypedQuery"%>
<%@page import="javax.persistence.EntityManager"%>
<%@page import="ocupacional.JPA.controlers.TicketClienteservicioJpaController"%>
<%@page import="ocupacional.JPA.valueobjects.TicketClienteservicio"%>
<%@page import="formularios.entidades.Anotaciones"%>
<%@page import="formularios.entidades.Pacientes"%>
<%@page import="ocupacional.JPA.valueobjects.Ticket"%>
<%@page import="ocupacional.JPA.controlers.ExamenesJpaController"%>
<%@page import="formularios.entidades.Opciones"%>
<%@page import="formularios.entidades.Ayudas"%>
<%@page import="formularios.entidades.Campos"%>
<%@page import="formularios.entidades.Categorias"%>
<%@page import="formularios.entidades.Formularios"%>
<%@page import="formularios.controlers.FormulariosJpaController"%>
<%@page import="javax.persistence.Persistence"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<%@page import="valeria.metodos.Cadenas"%>
<%@page import="java.util.ArrayList"%>
<%@page import="valeria.response.Mediador"%>
<%    Mediador e = (Mediador) session.getAttribute("Mediador");
    Cadenas o = new Cadenas();
    ManejadorFechas fechas = new ManejadorFechas();
    if (e != null) {
//   variables necesarias 
        String idform = request.getParameter("idform");
        if (idform == null) {
            idform = "1";
        }
        String atras = request.getParameter("atras");
        if (atras == null) {
            atras = "404.html";
        }
        String action = request.getParameter("action");
        if (action == null) {
            action = "Pacientes";
        }
        String modulo = request.getParameter("modulo");
        if (modulo == null) {
            modulo = "procesaForm";
        }
//variables de session necesarias 

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaP1PU");
        EntityManagerFactory emf2 = Persistence.createEntityManagerFactory("JavaP");
        FormulariosJpaController fc = new FormulariosJpaController(emf);
        Formularios f = fc.findFormularios(Integer.parseInt(idform));


  


%>
<style type="text/css">

    #cajaAyuda{

        z-index: 999;         
        position: absolute;
        display: none;
        height: 300px;
        width: 400px;
        box-shadow: 0 0 10px rgba(0,0,0,0.5);   
        background: white;  


    }


</style>

<script type="text/javascript">
    var genero = '';
    var tipoexamen = ''


</script>
<div class="panel panel-default">
    <div class="panel-heading">
        <div class="row">
            <div class="col-md-9">
            <h2 class="text-success"><%if(f.getExamId()!=null){%><%=new ExamenesJpaController(emf2).findExamenes(f.getExamId()).getExamDescripcion() %><%}%></h2>
            </div>
            <div class="col-md-3">
                <button style="margin-top: 25px" type="button" title="actualizar" onclick="RecargaPanel('../panels/formularios/basicas/form_visualizar.jsp?idform=<%=f.getFormId() %>', 'panelprincipal')" class="btn btn-success btn-circle"> <i class="glyphicon glyphicon-repeat"></i></button>
            </div>
        </div>
    </div>
                                   <form   id="Form-Data" role="form" name="Form-Data"  method="POST" action="../<%=o.notEmpty(action)%>" autocomplete="off">

        <div class="panel-body">

            <div class="row">
                <% for (Categorias cate : f.getCategoriasList()) {%>
                <fieldset>
                    <legend><%=o.notEmpty(cate.getCateDescripcion())%></legend>

                    <%
                        for (Campos campo : cate.getCamposList()) {

//                            System.out.println("tamaño " + cate.getCamposList().size());
//                            Aplicar value a los campos
                            String value = null;
                        

                            if (campo.getCampTipo().equals("text")) {
                    %>

                    <div class="form-group col-md-4">

                        <label class="control-label" for="<%=o.notEmpty(campo.getCampIdf())%>"><%=o.notEmpty(campo.getCampLabel())%>
                            <%for (Ayudas ayuda : campo.getAyudasList()) {%><span onclick="mostrarAyuda(<%=ayuda.getAyudId()%>, '<%=o.notEmpty(ayuda.getAyudDescripcion())%>', event)" style="cursor: pointer;font-weight: bold " class=" label label-info" title="Mostrar ayuda">?</span><%}%>
                        </label>
                        <input <%if (campo.getOpcionesList() != null) {%>list="<%=campo.getCampIdf()%>_list"<%}%> id="<%=o.notEmpty(campo.getCampIdf())%>" name="<%=o.notEmpty(campo.getCampName())%>"  class="form-control " type="text" <%=o.notEmpty(campo.getCampAtributos())%>  value="<%=o.notEmpty(value, o.notEmpty(campo.getCampValue()))%>" >
                        <span class="help-block"></span>
                        <%if (campo.getOpcionesList() != null) {%>
                        <datalist id="<%=campo.getCampIdf()%>_list">
                            <%for (Opciones op : campo.getOpcionesList()) {%>
                            <option>
                                <%=o.notEmpty(op.getOpciValue())%> 
                            </option>
                            <%}%>
                        </datalist>
                        <%}%>
                    </div>


                    <% } else if (campo.getCampTipo().equals("fecha")) {
                    %>

                    <div class="form-group col-md-4">

                        <label class="control-label" for="<%=o.notEmpty(campo.getCampIdf())%>"><%=o.notEmpty(campo.getCampLabel())%>
                            <%for (Ayudas ayuda : campo.getAyudasList()) {%><span onclick="mostrarAyuda(<%=ayuda.getAyudId()%>, '<%=o.notEmpty(ayuda.getAyudDescripcion())%>', event)" style="cursor: pointer;font-weight: bold " class=" label label-info" title="Mostrar ayuda">?</span><%}%>
                        </label>

                        <%
                            String fecha = "";
                            if (value != null && value.equals("")) {
                                value = null;
                            }
                            fecha = o.notEmpty(value, fechas.DevuelveFormato(fechas.getFechaHoraTimeStamp()));

                        %>
                        <input  id="<%=o.notEmpty(campo.getCampIdf())%>" name="<%=o.notEmpty(campo.getCampName())%>"  class="form-control datepicker " type="text"  <%=o.notEmpty(campo.getCampAtributos())%>  value="<%=fecha%>" >
                        <span class="help-block"></span>
                    </div>


                    <% } else if (campo.getCampTipo().equals("textarea")) {
                        if (campo.getOpcionesList().isEmpty() && !campo.getCamptipoAux().equalsIgnoreCase("CIE")) {
                    %>
                    <div class="form-group col-md-12">
                        <label class="control-label" for="<%=o.notEmpty(campo.getCampIdf())%>"><%=o.notEmpty(campo.getCampLabel())%>
                            <%for (Ayudas ayuda : campo.getAyudasList()) {%><span onclick="mostrarAyuda(<%=ayuda.getAyudId()%>, '<%=o.notEmpty(ayuda.getAyudDescripcion())%>', event)" style="cursor: pointer;font-weight: bold " class=" label label-info" title="Mostrar ayuda">?</span><%}%>
                        </label>
                        <textarea maxlength="2990"   class="form-control col-md-12"  id="<%=o.notEmpty(campo.getCampIdf())%>" name="<%=o.notEmpty(campo.getCampName())%>"  <%=o.notEmpty(campo.getCampAtributos())%>><%=o.notEmpty(value, o.notEmpty(campo.getCampValue()))%></textarea>
                        <span class="help-block"></span>
                    </div>
                    <% } else {
                    %> 
                    <!--aqui debo definir si trae opciones se debe crear un boton que abra una modal para seleccionar las opciones-->  
                    <div class="form-group col-md-11">

                        <label class="control-label" for="<%=o.notEmpty(campo.getCampIdf())%>"><%=o.notEmpty(campo.getCampLabel())%>
                            <%for (Ayudas ayuda : campo.getAyudasList()) {%><span onclick="mostrarAyuda(<%=ayuda.getAyudId()%>, '<%=o.notEmpty(ayuda.getAyudDescripcion())%>', event)" style="cursor: pointer;font-weight: bold " class=" label label-info" title="Mostrar ayuda">?</span><%}%>
                        </label>
                        <textarea maxlength="2990"   class="form-control col-md-12"  id="<%=o.notEmpty(campo.getCampIdf())%>" name="<%=o.notEmpty(campo.getCampName())%>"  <%=o.notEmpty(campo.getCampAtributos())%>><%=o.notEmpty(value, o.notEmpty(campo.getCampValue()))%></textarea>
                        <span class="help-block"></span>
                    </div>
                    <div class="col-md-1">
                        <button  data-toggle="modal" data-target="#myModal"  onclick="
                            <% if(campo.getCamptipoAux().equalsIgnoreCase("CIE")){%>
                                    $('#modalContent').load('../panels/formularios/hc/opcionesTextAreaCie10.jsp?camp_name=<%=o.notEmpty(campo.getCampIdf())%>&camp_id=<%=campo.getCampId()%>');
                            <% }else{%>
                                    $('#modalContent').load('../panels/formularios/hc/opcionesTextArea.jsp?camp_name=<%=o.notEmpty(campo.getCampIdf())%>&camp_id=<%=campo.getCampId()%>');
                            <% }%>
                                 "  style="margin-top: 30px" type="button" class="btn btn-circle btn-info" title="Opciones peestablecidas"> <i class="glyphicon glyphicon-pencil"></i> </button>
                    </div>


                    <%
                        }

                    } else if (campo.getCampTipo().equals("selectmultiple")) {
                        String[] values = new String[0];
                        if (value != null) {
                            values = value.split(",");
                        }
                    %>

                    <div class="form-group col-md-4">

                        <label class="control-label" for="<%=o.notEmpty(campo.getCampIdf())%>"><%=o.notEmpty(campo.getCampLabel())%>
                            <%for (Ayudas ayuda : campo.getAyudasList()) {%><span onclick="mostrarAyuda(<%=ayuda.getAyudId()%>, '<%=o.notEmpty(ayuda.getAyudDescripcion())%>', event)" style="cursor: pointer;font-weight: bold " class=" label label-info" title="Mostrar ayuda">?</span><%}%>
                        </label>

                        <select class="form-control col-md-4" id="<%=o.notEmpty(campo.getCampIdf())%>" name="<%=o.notEmpty(campo.getCampName())%>" multiple  <%=o.notEmpty(campo.getCampAtributos())%>>
                            <%  for (Opciones op : campo.getOpcionesList()) {%>
                            <option value="<%=o.notEmpty(op.getOpciValue())%>"  
                                    <%

                                        for (String v : values) {
                                            if (op.getOpciValue().equals(v)) {
                                    %>
                                    selected
                                    <%}
                                        }

                                    %>
                                    ><%=o.notEmpty(op.getOpciNombre())%></option>
                            <% }%>

                        </select>


                        <span class="help-block"></span>
                    </div>

                    <% } else if (campo.getCampTipo().equals("select")) {%>

                    <div class="form-group col-md-4">

                        <label class="control-label" for="<%=o.notEmpty(campo.getCampIdf())%>"><%=o.notEmpty(campo.getCampLabel())%>
                            <%for (Ayudas ayuda : campo.getAyudasList()) {%><span onclick="mostrarAyuda(<%=ayuda.getAyudId()%>, '<%=o.notEmpty(ayuda.getAyudDescripcion())%>', event)" style="cursor: pointer;font-weight: bold " class=" label label-info" title="Mostrar ayuda">?</span><%}%>
                        </label>

                        <select class="form-control col-md-4" id="<%=o.notEmpty(campo.getCampIdf())%>" name="<%=o.notEmpty(campo.getCampName())%>"   <%=o.notEmpty(campo.getCampAtributos())%>>
                            <%  for (Opciones op : campo.getOpcionesList()) {%>
                            <option value="<%=o.notEmpty(op.getOpciValue())%>" <%
                                if (op.getOpciValue().equals(value)) {

                                    %> selected <%}%>><%=o.notEmpty(op.getOpciNombre())%></option>
                            <% }%>

                        </select>


                        <span class="help-block"></span>
                    </div>

                    <%  } else if (campo.getCampTipo().equals("hr")) {%>
                    <legend></legend>
                    <%  } else if (campo.getCampTipo().equals("huellaFirma")) {%>
                    <!--tipo huella firma--> 
                    <div class="col-md-4">
                        <button type="button" data-toggle="modal" data-target="#myModal" class="btn btn-success"  onclick="
                                $('#modalContent').load('../panels/HF/peticion_firma.jsp ');
                                " >Huella y Firma</button>
                    </div>
              
                    <!--fin-->
                    <%} else if (campo.getCampTipo().equals("adjunto")) {%>

                    <!--adjunto--> 
                    <div class="col-md-4">
                        <button  style="margin-top: 30px" type="button" data-toggle="modal" data-target="#modalAdjunto" class="btn btn-success"  onclick="
                                $('#modalAdjuntoContent').load('../panels/formularios/hc/car_arc_dial.jsp?anot_id= ');
                                 " >Adjunto</button>
                    </div>

                    <%  } else if (campo.getCampTipo().equals("subTitle")) {%>

                    <!--adjunto--> 
                    <div class="col-md-12">
                        <h5><strong><%=campo.getCampName()%></strong></h5>
                    </div>

                    <%  } else if (campo.getCampTipo().equals("laboratorios")) {
                    %>

                    <table class="table table-hover" ><thead>
                            <tr>
                                <th>TIPO</th>
                                <th>NOMBRE</th>
                                <th>RESULTADO</th>
                            </tr>
                        </thead>
                        <tbody>                      
                         
                        </tbody>

                    </table>






                    <%
                    } else if (campo.getCampTipo().equals("audiometria")) {
                    %>
                    <fieldset class="audiogram">
                        <legend class="audiogram">
                            Entrada umbral audiométrico</legend>
                        <table class="col-md-6"  id="01496673" data-appointment="10041362"  data-audiogram="new">
                            <thead>
                                <tr>
                                    <th></th>
                                    <th></th>
                                    <th></th>
                                    <th></th>
                                    <th></th>
                                    <th></th>
                                    <th></th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                </tr>
                                <tr>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                </tr>
                                <tr>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                </tr>
                                <tr>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                </tr>
                                <tr>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                </tr>
                                <tr>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                </tr>
                                <tr>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                </tr>
                                <tr>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                </tr>
                                <tr>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                </tr>
                                <tr>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                </tr>
                                <tr>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                </tr>
                                <tr>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                </tr>
                                <tr>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                </tr>
                                <tr>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                </tr>
                            </tbody>
                        </table>
                        <fieldset class="controls col-md-5">
                            <legend class = "controls">Selectores</legend>
                            <section id="buttons">
                                <section id="buttons_controller">
                                    <fieldset class="radioSetSelector col-md-8">
                                        <input type="radio" name="ear_specific.soundfield" id="button_ear_specific" />
                                        <label class="btn btn-primary"  for="button_ear_specific">
                                            Específicos del oído</label> <br />
                                        <input type="radio" name="ear_specific.soundfield" id="button_soundfield" />
                                        <label class="btn btn-primary" for="button_soundfield">Campo sonoro</label>
                                    </fieldset>


                                    <fieldset class="radioSetSelector col-md-4">
                                        <input type="radio" name="addRemove" id="button_add" />
                                        <label class="btn btn-success" for="button_add"> <i class="glyphicon glyphicon-pencil"></i>  </label> <br />
                                        <input type="radio" name="addRemove" id="button_remove" />
                                        <label class="btn btn-danger" for="button_remove"><i class="glyphicon glyphicon-trash"></i></label>
                                    </fieldset>

                                    <!--<button id="button_save" type="button">Save</button>-->
                                </section>

                                <section id="buttons_ear_specific">
                                    <fieldset class="radioSetSelector col-md-4">
                                        <input type="radio" name="leftRight"  id="button_left" />
                                        <label class="btn btn-info" for="button_left">Izq</label> <br />
                                        <input type="radio" name="leftRight" id="button_right" />
                                        <label class="btn btn-info" for="button_right">Der</label>
                                    </fieldset>

                                    <fieldset class="radioSetSelector col-md-4">
                                        <input type="radio" name="airBone" id="button_air" />
                                        <label class="btn btn-info" for="button_air">Aire</label>  <br />
                                        <input type="radio" name="airBone" id="button_bone" />
                                        <label class="btn btn-info" for="button_bone">Hueso</label>
                                    </fieldset>

                                    <fieldset class="radioSetSelector col-md-4">
                                        <input type="radio" name="masking" id="button_unmasked" />
                                        <label class="btn btn-info" for="button_unmasked">
                                            Desenmascarado</label> <br />
                                        <input type="radio" name="masking" id="button_masked" />
                                        <label class="btn btn-info" for="button_masked">
                                            Enmascarado</label>
                                    </fieldset>
                                </section>

                                <section id="buttons_soundfield">
                                    <fieldset class="radioSetSelector">
                                        <input type="radio" name="sft" id="button_unaided" />
                                        <label class="btn btn-info" for="button_unaided">Sin ayuda</label>
                                        <input type="radio" name="sft" id="button_aided" />
                                        <label class="btn btn-info" for="button_aided">Ayudado</label>
                                        <input type="radio" name="sft" id="button_ci" />
                                        <label class="btn btn-info" for="button_ci">
                                            Implante coclear</label>
                                    </fieldset>
                                </section>
                            </section>
                        </fieldset>
                    </fieldset>                        

                    <script type="text/javascript" src="../js/media/js/ba-debug.min.js"></script>
                    <script type="text/javascript" src="../js/media/js/jquery.audiogram.js"></script>
                    <script type="text/javascript">
                                        $('#01496673').audiogram({
                                            editable: true,
                                         
        <%if (value!= null){ %>audiometricData: JSON.parse('<%=o.notEmpty(value)%>')<%}%>
                                        });

                    </script>

                    <input  <%if (campo.getOpcionesList() != null) {%>list="<%=campo.getCampIdf()%>_list"<%}%> id="<%=o.notEmpty(campo.getCampIdf())%>" name="<%=o.notEmpty(campo.getCampName())%>"  class="form-control " type="hidden" <%=o.notEmpty(campo.getCampAtributos())%>  value="<%=o.notEmpty(value, o.notEmpty(campo.getCampValue()))%>" >                      

                    <%
                                System.out.println("value::: " + value);
                            }
                        }%>



                    <script type="text/javascript">
                        <%=o.notEmpty(cate.getCateScript())%>



                    </script>
                </fieldset>

                <% }%>



            </div>
            <!-- /.row (nested) -->
        </div>
        <div class="panel-footer">
            <div class="row">


                <div class="col-lg-9 right">

                </div>
                <div class="col-lg-3 right">
                    <div class="form-group">
                        <button  onclick="RecargaPanel('../panels/formularios/basicas/form_list.jsp', 'panelprincipal')" type="button" class="btn btn-default bottom-right btn-outline">Atras </button>

                    </div>
                </div>
            </div>


        </div>
    </form>
</div>
<div id="cajaAyuda"   onclick="cerrarAyuda()" title="Click para cerrar" style="cursor: pointer;overflow-y: auto; overflow-x: hidden">


    <img id="imagenAyuda" src="../mostrarImg.jsp" width="400" alt="no se pudo mostrar imagen" />

    <div id="descripAyuda">

    </div>
</div>
<!-- Modal -->
<div class="modal fade" id="modalAdjunto" role="dialog">
    <div class="modal-dialog modal-lg ">
        <div class="modal-content"  id="modalAdjuntoContent">

        </div>
    </div>
</div>
<!--fin-->
      <!-- Modal -->
                    <div class="modal fade" id="myModal" role="dialog">
                        <div class="modal-dialog modal-lg">
                            <div class="modal-content"  id="modalContent">

                            </div>
                        </div>
                    </div>

<script type="text/javascript">
    function mostrarAyuda(id, desc, event) {

        $("#imagenAyuda").attr("src", "../mostrarImg.jsp?id=" + id);
        $("#descripAyuda").html(desc);



        //determina un margen de pixels del div al raton

        marginx = -220;
        marginy = -50;


        //La variable IE determina si estamos utilizando IE

        var IE = document.all ? true : false;

        //Si no utilizamos IE capturamos el evento del mouse

        if (!IE)
            document.captureEvents(Event.MOUSEMOVE)


        var tempX = 0;

        var tempY = 0;


        if (IE)

        { //para IE

            tempX = event.clientX + document.body.scrollLeft;

            tempY = event.clientY + document.body.scrollTop;

        } else { //para netscape

            tempX = event.pageX;

            tempY = event.pageY;

        }

        if (tempX < 0) {
            tempX = 0;
        }

        if (tempY < 0) {
            tempY = 0;
        }


        //modificamos el valor del id posicion para indicar la posicion del mouse

//	document.getElementById('posicion').innerHTML="PosX = "+tempX+" | PosY = "+tempY;

//alert(tempX+" "+tempY)
        document.getElementById('cajaAyuda').style.top = (tempY + marginy + "px");

        document.getElementById('cajaAyuda').style.left = (tempX + marginx + "px");

        document.getElementById('cajaAyuda').style.display = 'block';
//       $("#cajaAyuda").show();

        return;

    }
    function cerrarAyuda() {

//       $("#cajaAyuda").hide()
        document.getElementById('cajaAyuda').style.display = 'none';

    }

    $(document).ready(function () {
        ValidarFormID();
        $('.datepicker').datepicker(
                {format: 'dd-mm-yyyy'
                });


    });
    $(".close").click(function () {
        $('#Form-Data').bootstrapValidator('resetForm', true);
    });
    
   //    anular enter
  $('form').keypress(function(e){   
    if(e == 13){
      return false;
    }
  });
  $('input').keypress(function(e){
    if(e.which == 13){
      return false;
    }
  });
</script>
<%}else{%>
<script type="text/javascript">
    location.href='../logout.jsp';
</script>
<%}%>