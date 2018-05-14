<%-- 
    Document   : RecibirPaciente_datosbasicos
    Created on : 17/05/2016, 10:57:06 PM
    Author     : D4V3
--%>


<%@page import="java.util.List"%>
<%@page import="formularios.entidades.ResponsablesPaciente"%>
<%@page import="javax.persistence.TypedQuery"%>
<%@page import="javax.persistence.EntityManager"%>
<%@page import="formularios.controlers.TipoVinculacionEpsJpaController"%>
<%@page import="formularios.entidades.TipoVinculacionEps"%>
<%@page import="ocupacional.JPA.controlers.SexoJpaController"%>
<%@page import="ocupacional.JPA.valueobjects.Sexo"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="ocupacional.JPA.valueobjects.Ciudades"%>
<%@page import="ocupacional.JPA.controlers.DepartamentosJpaController"%>
<%@page import="ocupacional.JPA.valueobjects.Departamentos"%>
<%@page import="ocupacional.JPA.controlers.EntidadesJpaController"%>
<%@page import="ocupacional.JPA.valueobjects.Entidades"%>
<%@page import="java.text.DateFormat"%>
<%@page import="valeria.metodos.ManejadorFechas"%>
<%@page import="ocupacional.JPA.controlers.EstadocivilJpaController"%>
<%@page import="ocupacional.JPA.valueobjects.Estadocivil"%>
<%@page import="ocupacional.bdatos.EstadoCivilDAO"%>
<%@page import="ocupacional.valueobjects.EstadoCivilVO"%>
<%@page import="ocupacional.JPA.valueobjects.Ticket"%>
<%@page import="ocupacional.JPA.controlers.TicketJpaController"%>
<%@page import="formularios.entidades.Pacientes"%>
<%@page import="formularios.controlers.PacientesJpaController"%>
<%@page import="javax.persistence.Persistence"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<%@page import="ocupacional.bdatos.TipoDocumentoDAO"%>
<%@page import="ocupacional.valueobjects.TipoDocumentoVO"%>
<%@page import="ocupacional.valueobjects.PersonaGeneralVO"%>
<%@page import="ocupacional.bdatos.RolFuncionalidadDAO"%>
<%@page import="ocupacional.valueobjects.RolFuncionalidadVOs"%>
<%@page import="valeria.metodos.Cadenas"%>

<%@page import="java.util.ArrayList"%>
<%@page import="valeria.response.Mediador"%>
<%
    try {
        Mediador e = (Mediador) session.getAttribute("Mediador");
        if (e != null) {
            session.removeAttribute("listaTicketPaciente");
            Cadenas pc = new Cadenas();
            ManejadorFechas f = new ManejadorFechas();
            System.out.println("entre 1");
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaP1PU");
            EntityManagerFactory emf2 = Persistence.createEntityManagerFactory("JavaP");
            PacientesJpaController pacic = new PacientesJpaController(emf);
            TicketJpaController tDAO = new TicketJpaController(emf2);
            if (request.getParameter("tick_id") != null) {

                System.out.println("entre 2");

                Ticket t = tDAO.findTicket(Integer.parseInt(request.getParameter("tick_id")));

                Pacientes paci = pacic.findPacientes(t.getTickPaciente());
                if (paci == null) {
                    paci = new Pacientes();
                }
                System.out.println("entre 3");

%>

<div class="panel panel-default">

    <div class="panel-heading">
        <div class="row">
            <div class="col-lg-9">
                <h2 class="text-success">Datos basicos</h2>
            </div>
        </div>
    </div>
    <form   id="Form-Data" role="form" name="Form-Data"  method="POST" action="../Pacientes" autocomplete="off">
        <div class="panel-body">
            <div class="row">


                <%                if (true) {
                        System.out.println("entre 4");

                %>


                <fieldset id="caja">
                    <div class="row well ">
                        <div class="col-md-6 img-thumbnail">
                            <script src="../js/webcam.min.js"></script>

                            <center>  
                                <div   id="my_camera" style="width:320px; height:240px;"></div>
                                <!--<img src=""  id="my_camera" style="width:320px; height:240px;"/>-->

                                <script language="JavaScript">
                                    Webcam.attach('#my_camera');

                                    function take_snapshot() {
                                        Webcam.snap(function (data_uri) {
                                            document.getElementById('my_result').innerHTML = '<img src="' + data_uri + '"/>';
                                            document.getElementById('paci_foto').value = data_uri;
                                            //                Webcam.reset();
                                            $('#Form-Data').bootstrapValidator.disableSubmitButtons(false);
                                        });


                                    }

                                </script>

                                <button type="button" class="btn btn-success" onclick="javascript:void(take_snapshot())">Tomar foto</button>
                            </center>   </div>
                        <div class=" col-md-6 img-thumbnail">
                            <center>

                                <div id="my_result" style="margin-bottom: 30px">
                                    <img src="<%=pc.notEmpty(paci.getPaciFoto())%>" alt="Aqui va la fotografía." />.
                                </div>
                                <input type="hidden" name="paci_foto"  id="paci_foto" value="<%=pc.notEmpty(paci.getPaciFoto())%>"/>
                            </center>

                        </div>

                    </div>
                    <div class="row">
                        <div class="form-group col-md-4">
                            <label class="control-label" for="documento">Documento*</label> 
                            <input id="documento" name="documento" placeholder="" class="form-control" type="text" required readonly value="<%=pc.notEmpty(paci.getPaciDocumento())%>">
                            <span class="help-block"></span>
                        </div>
                        <div class="form-group col-md-4">
                            <label class="control-label" for="nombres" >Nombres*</label> 
                            <input  name="nombres" id="nombres" placeholder="" required="" class="form-control" type="text" value="<%=pc.notEmpty(paci.getPaciNombres())%>" >
                            <span class="help-block"></span>
                        </div>
                        <div class="form-group col-md-4">
                            <label class="control-label" for="apellidos">Apellidos*</label>  
                            <input  name="apellidos" id="apellidos" placeholder="" class="form-control" type="text" required value="<%=pc.notEmpty(paci.getPaciApellidos())%>">
                            <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group col-md-4">
                            <label class="control-label" for="fechaNacimiento" >Fecha de Nacimiento*</label> 
                            <%
                                String fecha = "";
                                if (paci.getPaciFechanacimiento() != null) {
                                    DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                                    fecha = df.format(paci.getPaciFechanacimiento());
                                }
                            %>
                            <input  name="fechaNacimiento"  id="fechaNacimiento" placeholder="" class="form-control datepicker" type="text" required value="<%=fecha%>"  >
                            <span class="help-block"></span>
                        </div>
                        <div class="form-group col-md-4">
                            <label class="control-label" for="estadoCivil">Estado Civil*</label>  
                            <select  name="estadoCivil" id="estadoCivil" class="form-control" required >
                                <option value="">Seleccione..</option>
                                <%
                                    for (Estadocivil ec : new EstadocivilJpaController(emf2).findEstadocivilEntities()) {%>
                                <option value="<%=ec.getEsciId()%>"><%=pc.notEmpty(ec.getEsciDescripcion())%></option>
                                <%}
                                %>
                            </select>
                            <span class="help-block"></span>
                        </div>

                        <div class="form-group col-md-4">
                            <label class="control-label" for="rh" >Grupo Sanguineo*</label> 
                            <select name="rh" id="rh" required="" class="form-control">
                                <option value="O+">O+</option>
                                <option value="O-">O-</option>
                                <option value="A+">A+</option>
                                <option value="A-">A-</option>
                                <option value="B+">B+</option>
                                <option value="B-">B-</option>
                                <option value="AB+">AB+</option>
                                <option value="AB-">AB-</option>
                            </select>
                            <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group col-md-4">
                            <label class="control-label" for="eps">EPS*</label> 
                            <select  name="eps" id="eps" class="form-control" required onchange="">
                                <option value="">Seleccione..</option>
                                <%
                                    for (Entidades ec : new EntidadesJpaController(emf2).findEntidadesEntities()) {
                                        if (ec.getEntiTipo().equals("EPS")) {%>
                                <option value="<%=ec.getEntiId()%>"><%=pc.notEmpty(ec.getEntiNombre())%></option>
                                <%}
                                    }
                                %>
                            </select>
                            <span class="help-block"></span>
                        </div>
                        <div class="form-group col-md-4">
                            <label class="control-label" for="Vinculacion" >Vinculacion*</label>  
                            <select  name="Vinculacion" id="Vinculacion" class="form-control" required onchange="">
                             <option value="">Seleccione..</option>
                                <%
                                    for (TipoVinculacionEps ec : new TipoVinculacionEpsJpaController(emf).findTipoVinculacionEpsEntities()) {%>
                                <option value="<%=ec.getTvepDescripcion()%>"><%=pc.notEmpty(ec.getTvepDescripcion())%></option>
                                <%}
                                %>

                            </select>
                            <span class="help-block"></span>
                        </div>

                        <div class="form-group col-md-4">
                            <label class="control-label" for="arl" >ARL*</label>  
                            <select  name="arl" id="arl" class="form-control" required onchange="">
                                <option value="">Seleccione..</option>
                                <%
                                    for (Entidades ec : new EntidadesJpaController(emf2).findEntidadesEntities()) {
                                        if (ec.getEntiTipo().equals("ARL")) {%>
                                <option value="<%=ec.getEntiId()%>"><%=pc.notEmpty(ec.getEntiNombre())%></option>
                                <%}
                                    }

                                %>
                            </select>
                            <span class="help-block"></span>
                        </div>

                    </div>
                    <div class="row">
                        <div class="form-group col-md-4">
                            <label class="control-label" for="Escolaridad" >Escolaridad*</label>  
                            <select  name="Escolaridad" id="Escolaridad" class="form-control" required onchange="">
                                <option value="">Seleccione..</option>
                                <option value="Sin escolaridad">Sin escolaridad</option>
                                <option value="Preescolar">Preescolar</option>
                                <option value="Primaria incompleta">Primaria incompleta</option>
                                <option value="Primaria completa">Primaria completa</option>
                                <option value="Secundaria Incompleta">Secundaria Incompleta</option>
                                <option value="Secundaria completa">Secundaria completa</option>
                                <option value="Tecnica">Tecnica</option>
                                <option value="Tecnologica">Tecnologica</option>
                                <option value="Profesional">Profesional</option>
                                <option value="Maestría y Doctorado">Maestría y Doctorado</option>
                                <option value="No especificado" selected>No especificado</option>

                            </select>
                            <span class="help-block"></span>
                        </div>

                        <div class="form-group col-md-4">
                            <label class="control-label" for="Dominancia" >Dominancia*</label>  
                            <select  name="Dominancia" id="Dominancia" class="form-control" required onchange="">
                                <option value="">Seleccione..</option>
                                <option value="Diestro" selected>Diestro</option>
                                <option value="Zurdo">Zurdo</option>
                                <option value="Ambidiestro">Ambidiestro</option>

                            </select>
                            <span class="help-block"></span>
                        </div>
                        <div class="form-group col-md-4">
                            <label class="control-label" for="Genero" >Genero*</label>  
                            <select  name="Genero" id="Genero" class="form-control" required onchange="">
                                <option value="">Seleccione..</option>
                                <%  for (Sexo ec : new SexoJpaController(emf2).findSexoEntities()) {
                                %>
                                <option value="<%=ec.getSexoDescripcion()%>"><%=pc.notEmpty(ec.getSexoDescripcion())%></option>
                                <%}

                                %>
                            </select>
                            <span class="help-block"></span>
                        </div>

                    </div>
                </fieldset>
                <fieldset>
                    <legend>Datos Demograficos</legend>

                    <div class="row" >

                        <div class="form-group col-md-3">
                            <label class="control-label ">Lugar de procedencia*</label>
                            <select required name="ciudad_procedencia" id="ciudad_procedencia" class="form-control selectpicker" data-size="10" data-live-search="true" data-style="btn-white">
                                <option value="">Seleccione...</option>
                                <% for (Departamentos d : new DepartamentosJpaController(emf2).findDepartamentosEntities()) {%>
                                <optgroup label="<%=d.getDepaNombre()%>">
                                    <%for (Ciudades c : d.getCiudadesList()) {%>

                                    <option value="<%=c.getCiudId()%>"<%if (paci.getCiudId() != null && c.getCiudId().equals(paci.getCiudId())) {%> selected<%}%>><%=c.getCiudNombre() + " (" + d.getDepaNombre() + ")"%></option>
                                    <%}%>
                                </optgroup>    
                                <%}%>

                            </select>
                            <span class="help-block"></span>
                        </div>
                        <div class="form-group col-md-3">
                            <label class="control-label ">Ciudad residencia*</label>
                            <select required name="ciudad" id="ciudad" class="form-control selectpicker" data-size="10" data-live-search="true" data-style="btn-white">
                                <option value="">Seleccione...</option>
                                <% for (Departamentos d : new DepartamentosJpaController(emf2).findDepartamentosEntities()) {%>
                                <optgroup label="<%=d.getDepaNombre()%>">
                                    <%for (Ciudades c : d.getCiudadesList()) {%>

                                    <option value="<%=c.getCiudId()%>"<%if (paci.getCiudId() != null && c.getCiudId().equals(paci.getCiudId())) {%> selected<%}%>><%=c.getCiudNombre() + " (" + d.getDepaNombre() + ")"%></option>
                                    <%}%>
                                </optgroup>    
                                <%}%>

                            </select>
                            <span class="help-block"></span>
                        </div>

                        <div class="form-group col-md-3">
                            <label class="control-label" for="pege_direccion">Lugar de Recidencia*</label>  
                            <input id="pege_direccion"  name="pege_direccion" placeholder="" class="form-control" type="text" required value="<%= pc.notEmpty(paci.getPaciDireccion())%>" >
                            <span class="help-block"></span>
                        </div>
                        <div class="form-group col-md-3">
                            <label class="control-label" for="pege_telefono">Telefono</label>  
                            <input id="pege_telefono" name="pege_telefono" placeholder="" class="form-control " type="text"  value="<%= pc.notEmpty(paci.getPaciTelefono())%>">
                            <span class="help-block"></span>
                        </div>
                    </div>


                </fieldset>

                            <%
                            // acompañante y responsable
                    EntityManager em = emf.createEntityManager();
                    TypedQuery<ResponsablesPaciente> consulta = em.createNamedQuery("ResponsablesPaciente.findByTickId", ResponsablesPaciente.class);
                    consulta.setParameter("tickId", t.getTickId());
                    
                        List<ResponsablesPaciente> lista = consulta.getResultList();
                        em.close();
                        ResponsablesPaciente responsable = new ResponsablesPaciente();
                        ResponsablesPaciente acompanante = new ResponsablesPaciente();
                        for (ResponsablesPaciente p : lista) {
                            if(p.getRepaTipo().equals("RESPONSABLE")){
                            responsable = p;
                            }else if(p.getRepaTipo().equals("ACOMPANANTE")){
                            acompanante = p;
                            }
                           
                        }  
                            %>
                            
                            
                <fieldset>
                    <legend>Acompañante:</legend>
                    <div class="form-group col-md-3">
                        <label class="control-label" for="nombre_Acom">Nombre</label>  
                        <input id="nombre_Acom" name="nombre_Acom" placeholder="" class="form-control " type="text"  value="<%= pc.notEmpty(acompanante.getRepaNombre())%>">
                        <span class="help-block"></span>
                    </div>
                    <div class="form-group col-md-3">
                        <label class="control-label" for="parentesco_Acom">Parentesco</label>  
                        <input id="parentesco_Acom" name="parentesco_Acom" placeholder="" class="form-control " type="text"  value="<%= pc.notEmpty(acompanante.getRepaParentesco())%>">
                        <span class="help-block"></span>
                    </div>

                    <div class="form-group col-md-3">
                        <label class="control-label" for="direccion_Acom">Lugar de Recidencia</label>  
                        <input id="direccion_Acom"  name="direccion_Acom" placeholder="" class="form-control" type="text"  value="<%= pc.notEmpty(acompanante.getRepaDireccion())%>" >
                        <span class="help-block"></span>
                    </div>
                    <div class="form-group col-md-3">
                        <label class="control-label" for="telefono_Acom">Telefono</label>  
                        <input id="telefono_Acom" name="telefono_Acom" placeholder="" class="form-control " type="text"  value="<%= pc.notEmpty(acompanante.getRepaTelefono())%>">
                        <span class="help-block"></span>
                    </div>

                </fieldset>
                <fieldset>
                    <legend>Responsable:</legend>
                    <div class="form-group col-md-3">
                        <label class="control-label" for="nombre_Resp">Nombre</label>  
                        <input id="nombre_Resp" name="nombre_Resp" placeholder="" class="form-control " type="text"  value="<%= pc.notEmpty(responsable.getRepaNombre())%>">
                        <span class="help-block"></span>
                    </div>
                    <div class="form-group col-md-3">
                        <label class="control-label" for="parentesco_Resp">Parentesco</label>  
                        <input id="parentesco_Resp" name="parentesco_Resp" placeholder="" class="form-control " type="text"  value="<%= pc.notEmpty(responsable.getRepaParentesco())%>">
                        <span class="help-block"></span>
                    </div>

                    <div class="form-group col-md-3">
                        <label class="control-label" for="direccion_Resp">Lugar de Recidencia</label>  
                        <input id="direccion_Resp"  name="direccion_Resp" placeholder="" class="form-control" type="text"  value="<%= pc.notEmpty(responsable.getRepaDireccion())%>" >
                        <span class="help-block"></span>
                    </div>
                    <div class="form-group col-md-3">
                        <label class="control-label" for="telefono_Resp">Telefono</label>  
                        <input id="telefono_Resp" name="telefono_Resp" placeholder="" class="form-control " type="text"  value="<%= pc.notEmpty(responsable.getRepaTelefono())%>">
                        <span class="help-block"></span>
                    </div>
                </fieldset>

                <fieldset>
                    <legend>Observaciones:</legend>

                    <div class="row" >


                        <div class="form-group col-md-12">
                            <textarea id="pege_observacion" maxlength="4000" name="pege_observacion" placeholder="" class="form-control"   ><%= pc.notEmpty(paci.getPaciObservaciones())%></textarea>
                            <span class="help-block"></span>
                        </div>

                    </div>


                </fieldset>
                <script type="text/javascript">


                </script>



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
                        <input type="hidden" name="tick_id" value="<%=t.getTickId()%>"/>
                        <input type="hidden" name="action" value="actualizarPaciente"/>
                        <button  onclick="RecargaPanel('../panels/formularios/hc/RecibirPaciente.jsp', 'panelprincipal')" type="button" class="btn btn-default bottom-right btn-outline">Atras </button>
                        <label  onclick="
                                console.log($('#Form-Data').serialize());
                                $('#Form-Data').submit();
                                "  class="btn btn-success bottom-right btn-outline">Continuar</label>
                        <!--<button  onclick="RecargaPanel('../panels/formularios/hc/RecibirPaciente_datosOcupacionales.jsp', 'panelprincipal')" type="button" class="btn btn-success bottom-right btn-outline">s</button>-->

                    </div>
                </div>
            </div>


        </div>
    </form>
</div>



<script>
    $('.selectpicker').selectpicker('render');

    ValidarFormID();

    $('.datepicker').datepicker(
            {format: 'dd-mm-yyyy'
            });
    $("#arl").val('<%=pc.notEmpty(paci.getPaciArl())%>');
    $("#eps").val('<%=pc.notEmpty(paci.getPaciEps())%>');
    $("#Vinculacion").val('<%=pc.notEmpty(paci.getPaciVinculacionEps())%>');
    $("#estadoCivil").val('<%=pc.notEmpty(paci.getPaciEcivil())%>');
    $("#rh").val('<%=pc.notEmpty(paci.getPaciRh())%>');
    $("#Genero").val('<%=pc.notEmpty(paci.getPaciGenero())%>');
    $("#Dominancia").val('<%=pc.notEmpty(paci.getPaciDominancia())%>');
    $("#Escolaridad").val('<%=pc.notEmpty(paci.getPaciEscolaridad())%>');




    $(".close").click(function () {
        $('#Form-Data').bootstrapValidator('resetForm', true);
    });

</script>
<% }
} else {%>
<script type="text/javascript">
    Location.href = '../logout.jsp';
</script>
<%}
    } catch (Exception ex) {
        ex.printStackTrace();
    }%>