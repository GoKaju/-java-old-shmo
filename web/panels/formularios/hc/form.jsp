<%-- 
    Document   : form
    Created on : 18/06/2016, 08:26:51 PM
    Author     : D4V3
--%>
<%@page import="ocupacional.JPA.controlers.CiudadesJpaController"%>
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
    ManejadorFechas fechas = new ManejadorFechas();
    Cadenas o = new Cadenas();
System.out.println("TESTE DEMORA FORM::: 1 -> "+ fechas.getFechaHoraTimeStamp());
    if (e != null) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaP1PU");
        EntityManagerFactory emf2 = Persistence.createEntityManagerFactory("JavaP");
        EntityManager em = emf.createEntityManager();
        try{
        System.out.println("TESTE DEMORA FORM::: 2 -> "+ fechas.getFechaHoraTimeStamp());

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
            action = "Formularios";
//            action = "Pacientes";
        }
        String modulo = request.getParameter("modulo");
        if (modulo == null) {
            modulo = "procesaForm";
        }
//variables de session necesarias 

System.out.println("TESTE DEMORA FORM::: 3 -> "+ fechas.getFechaHoraTimeStamp());

        
        FormulariosJpaController fc = new FormulariosJpaController(emf);
        Formularios f = fc.findFormularios(Integer.parseInt(idform));

        session.removeAttribute("FormularioActual");
        session.setAttribute("FormularioActual", f);
        Ticket t = (Ticket) session.getAttribute("Ticket");
        Pacientes paci = (Pacientes) session.getAttribute("Paciente");

        String tcls = o.notEmpty(request.getParameter("tics"));
//  determinar si el formulario ya fue llenado y cargar anotacion
//  cargar por tcls

System.out.println("TESTE DEMORA FORM::: 4 -> "+ fechas.getFechaHoraTimeStamp());

        System.out.println("entre a form " + tcls);
        Anotaciones anot = new Anotaciones();
        AnotacionesJpaController anotdao = new AnotacionesJpaController(emf);
        session.removeAttribute("Anotacion");
System.out.println("TESTE DEMORA FORM::: 5 -> "+ fechas.getFechaHoraTimeStamp());

        TicketClienteservicio tclsVO = new TicketClienteservicioJpaController(emf2).findTicketClienteservicio(Integer.parseInt(tcls));
        TypedQuery<Anotaciones> consultaxtcls = em.createNamedQuery("Anotaciones.findByTCLS", Anotaciones.class);
        consultaxtcls.setParameter("ticsId", tclsVO.getTicsId());
        consultaxtcls.setParameter("formId", f);
        List<Anotaciones> lista = consultaxtcls.getResultList();
        System.out.println("entre a lista " + lista.isEmpty());
System.out.println("TESTE DEMORA FORM::: 6 -> "+ fechas.getFechaHoraTimeStamp());

         if (!lista.isEmpty()) {
            for (Anotaciones a : lista) {
                anot = a;
            }
        }
System.out.println("TESTE DEMORA FORM::: 7 -> "+ fechas.getFechaHoraTimeStamp());

        // recrear formulario con anotacion
        String anot_id = "";
        if (anot.getAnotId() != null) {
            anot_id = anot.getAnotId().toString();
            session.setAttribute("Anotacion", anot);
        } else {
            System.out.println("TESTE DEMORA FORM::: 8 -> "+ fechas.getFechaHoraTimeStamp());

            anot.setAnotEstado("PRE EDICION");
            anot.setAnotRegistradopor(e.getUsuarioVO().getIdUsuario());
            anot.setAnotFechacambio(fechas.getFechaHoraTimeStamp());
            anot.setFormId(f);
            anot.setPaciId(paci);
            anot.setTicsId(Integer.parseInt(tcls));
            System.out.println("TESTE DEMORA FORM::: 9 -> "+ fechas.getFechaHoraTimeStamp());
//            anotdao.create(anot);

            em.getTransaction().begin();
            em.persist(anot);
            em.getTransaction().commit();
          
            System.out.println("TESTE DEMORA FORM::: 10 -> "+ fechas.getFechaHoraTimeStamp());
            anot_id = anot.getAnotId().toString();
            session.setAttribute("Anotacion", anot);
            System.out.println("TESTE DEMORA FORM::: 11 -> "+ fechas.getFechaHoraTimeStamp());

        }


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
    var genero = '<%=paci.getPaciGenero()%>';
    var tipoexamen = '<%=o.notEmpty(tclsVO.getTickId().getTemeId().getTemeDescripcion())%>'


</script>
<div class="panel panel-default">
    <div class="panel-heading">
        <div class="row">
            <div class="col-md-12">
                <a href="#c0" data-toggle="collapse">
                    <h4 class="text-success"><%=e.o.notEmpty(paci.getPaciNombres().toUpperCase() + " " + paci.getPaciApellidos().toUpperCase()) + "   Doc." + e.o.notEmpty(paci.getPaciDocumento())%><br/>  <small><%=fechas.getDiffDates(paci.getPaciFechanacimiento(), fechas.getFechaHora())%> </small> </h4></a>
                <div class="collapse" id="c0" style="">
                    <div class="col-md-2">

                        <%
                            if (paci.getPaciFoto() == null) {
                                if (paci.getPaciGenero().equals("Masculino")) {%>
                        <img class="img-responsive" src="../images/hombre.PNG" alt="foto" />
                        <%} else {%>
                        <img class="img-responsive" src="../images/mujer.PNG" alt="foto" />
                        <%}
                       } else {%>
                        <img class="img-responsive" src="<%=paci.getPaciFoto()%>" alt="foto" />
                        <%}%>

                    </div>
                    <div class="col-md-5">
                        <table class="table table-hover">
                            <tbody>
                                <tr>
                                    <td>Fecha de nacimiento:</td>
                                    <td><%=e.o.notEmpty(fechas.FechaLetras(new Timestamp(paci.getPaciFechanacimiento().getTime())))%></td>
                                </tr>
                                <tr>
                                    <td>Estado civil:</td>
                                    <td><%=e.o.notEmpty(new EstadocivilJpaController(emf2).findEstadocivil(Integer.parseInt(paci.getPaciEcivil())).getEsciDescripcion())%></td>
                                </tr>
                                <tr>
                                    <td>Grupo sanguineo:</td>
                                    <td><%=e.o.notEmpty(paci.getPaciRh())%></td>
                                </tr>
                                <tr>
                                    <td>EPS: </td>
                                    <td><%=e.o.notEmpty(new EntidadesJpaController(emf2).findEntidades(Integer.parseInt(paci.getPaciEps())).getEntiNombre())%></td>
                                </tr>
                                <tr>
                                    <td>ARL:</td>
                                    <td><%=e.o.notEmpty(new EntidadesJpaController(emf2).findEntidades(Integer.parseInt(paci.getPaciArl())).getEntiNombre())%></td>
                                </tr>

                            </tbody>

                        </table>   
                    </div>
                    <div class="col-md-5">
                        <table class="table table-hover">
                            <tbody>
                                <tr>
                                    <td>Escolaridad:</td>
                                    <td><%=e.o.notEmpty(paci.getPaciEscolaridad())%></td>
                                </tr>
                                <tr>
                                    <td>Dominancia:</td>
                                    <td><%=e.o.notEmpty(paci.getPaciDominancia())%></td>
                                </tr>
                                <tr>
                                    <td>Genero:</td>
                                    <td><%=e.o.notEmpty(paci.getPaciGenero())%></td>
                                </tr>
                                <tr>
                                    <td>Direcci�n: </td>
                                    <td><%=e.o.notEmpty(paci.getPaciDireccion())%><%if(paci.getCiudId()!=null){out.print(" - "+new CiudadesJpaController(emf2).findCiudades(paci.getCiudId()).getCiudNombre()+", "+new CiudadesJpaController(emf2).findCiudades(paci.getCiudId()).getDepaId().getDepaNombre());}%></td>
                                </tr>
                                <tr>
                                    <td>Telefono:</td>
                                    <td><%=e.o.notEmpty(paci.getPaciTelefono())%></td>
                                </tr>

                            </tbody>

                        </table>   
                    </div>


                </div>
            </div>
        </div>
    </div>
    <form   id="Form-Data" role="form" name="Form-Data"  method="POST" action="../<%=o.notEmpty(action)%>" autocomplete="off">

        <div class="panel-body">

            <div class="row">
                <%
                    System.out.println("TESTE DEMORA FORM::: 12 -> "+ fechas.getFechaHoraTimeStamp());

                    for (Categorias cate : f.getCategoriasList()) {%>
                <fieldset>
                    <legend><%=o.notEmpty(cate.getCateDescripcion())%></legend>

                    <%
                        for (Campos campo : cate.getCamposList()) {

//                            System.out.println("tama�o " + cate.getCamposList().size());
//                            Aplicar value a los campos
                            String value = null;
                            if (anot.getAnotId() != null) {

                                for (Respuestas r : anot.getRespuestasList()) {
                                    if (r.getCampId().equals(campo)) {
                                        value = o.notEmpty(r.getRespDescripcion());
                                    }

                                }
                            }

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
                            <textarea maxlength="2990" rows="10"  class="form-control col-md-12"  id="<%=o.notEmpty(campo.getCampIdf())%>" name="<%=o.notEmpty(campo.getCampName())%>"  <%=o.notEmpty(campo.getCampAtributos())%>><%=o.notEmpty(value, o.notEmpty(campo.getCampValue()))%></textarea>
                        <span class="help-block"></span>
                    </div>
                    <% } else {
                    %> 
                    <!--aqui debo definir si trae opciones se debe crear un boton que abra una modal para seleccionar las opciones-->  
                    <div class="form-group col-md-11">

                        <label class="control-label" for="<%=o.notEmpty(campo.getCampIdf())%>"><%=o.notEmpty(campo.getCampLabel())%>
                            <%for (Ayudas ayuda : campo.getAyudasList()) {%><span onclick="mostrarAyuda(<%=ayuda.getAyudId()%>, '<%=o.notEmpty(ayuda.getAyudDescripcion())%>', event)" style="cursor: pointer;font-weight: bold " class=" label label-info" title="Mostrar ayuda">?</span><%}%>
                        </label>
                        <textarea maxlength="2990" rows="10"  class="form-control col-md-12"  id="<%=o.notEmpty(campo.getCampIdf())%>" name="<%=o.notEmpty(campo.getCampName())%>"  <%=o.notEmpty(campo.getCampAtributos())%>><%=o.notEmpty(value, o.notEmpty(campo.getCampValue()))%></textarea>
                        <span class="help-block"></span>
                    </div>
                    <div class="col-md-1">
                        <button  data-toggle="modal" data-target="#myModal"  onclick="
                                 <% if (campo.getCamptipoAux().equalsIgnoreCase("CIE")) {%>
                                $('#modalContent').load('../panels/formularios/hc/opcionesTextAreaCie10.jsp?camp_name=<%=o.notEmpty(campo.getCampIdf())%>&camp_id=<%=campo.getCampId()%>');
                                 <% } else {%>
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

                    <%} else if (campo.getCampTipo().equals("adjunto")) {%>

                    <!--adjunto--> 
                    <div class="col-md-4">
                        <button  style="margin-top: 30px" type="button" data-toggle="modal" data-target="#modalAdjunto" class="btn btn-success"  onclick="
                                $('#modalAdjuntoContent').load('../panels/formularios/hc/car_arc_dial.jsp?anot_id=<%=anot_id%> ');
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
                            <%
                                for (TicketClienteservicio tcs : t.getTicketClienteservicioList()) {
                                    for (ServiciosExamenes se : tcs.getClseId().getServId().getServiciosExamenesList()) {
                                        if (se.getExamId().getExamTipo().equals("LABORATORIO") || se.getExamId().getExamTipo().equals("PARACLINICO")) {
//                 buscar form
                                            TypedQuery<Formularios> conxExam = em.createNamedQuery("Formularios.findByExamId", Formularios.class);
                                            conxExam.setParameter("examId", se.getExamId().getExamId());
                                            List<Formularios> lf = conxExam.getResultList();

                                            Formularios formul = new Formularios();
                                            if (!lf.isEmpty()) {
                                                for (Formularios a : lf) {
                                                    formul = a;
                                                }
                                            }

                                            TypedQuery<Anotaciones> conxtcls = em.createNamedQuery("Anotaciones.findByTCLS", Anotaciones.class);
                                            conxtcls.setParameter("ticsId", tcs.getTicsId());
                                            conxtcls.setParameter("formId", formul);
                                            List<Anotaciones> li = conxtcls.getResultList();
                                            Anotaciones an = new Anotaciones();
                                            if (!li.isEmpty()) {
                                                for (Anotaciones a : li) {
                                                    an = a;
                                                }
                                            }


                            %>

                            <tr>
                                <td><%=se.getExamId().getExamTipo()%></td>
                                <td><%=se.getExamId().getExamDescripcion()%>
                                    <%
                                        if (an.getAnotId() != null) {
                                    %>
                                    <button type="button" class="btn btn-primary btn-circle" onclick="window.open('../panels/formularios/hc/form_ver.jsp?anot_ocu=<%=an.getAnotId()%>', '_blank', 'toolbar=no,scrollbars=yes,resizable=yes,top=50,left=50,width=800,height=600')"><i class="glyphicon glyphicon-search"></i></button>

                                    <%
                                        }
                                    %>

                                </td>
                                <td><%
                                    if (an.getRespuestasList() != null) {
                                        for (Respuestas re : an.getRespuestasList()) {
                                            if (re.getCampId().getCamptipoAux().equals("CONCEPTO")) {
                                    %>
                                    <input type="text" class="form-control"  value="<%=o.notEmpty(re.getRespDescripcion())%>" onchange="peticionAjax('../Pacientes', 'action=actualizarConceptoRespuesta&id=<%=re.getRespId()%>&val=' + this.value)"/>  
                                    <%
                                                }
                                            }
                                        }

                                    %>  </td>
                            </tr>
                            <% }
                                    }
                                }%>
                        </tbody>

                    </table>






                    <%
                    } else if (campo.getCampTipo().equals("audiometria")) {
                    %>
                    <fieldset class="audiogram " >
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
                    
                    <div class="col-md-5" >
                        <fieldset>
                            <table class="table table-bordered " style="text-align: center">
                                <thead ><tr><th colspan="2" style="text-align: center">Promedio Tonal</th></tr>
                                    <tr>
                                        <th style="text-align: center">Oido Izq</th>
                                        <th style="text-align: center">Oido Der</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td style="text-align: center"><span id="promOI">0</span></td>
                                        <td style="text-align: center"><span id="promOD">0</span></td>
                                    </tr>
                                </tbody>
                            </table>
                        </fieldset>
                        <fieldset class="controls " >
                            <legend class = "controls">Selectores</legend>
                            <section id="buttons">
                                <section id="buttons_controller">
                                    <fieldset class="radioSetSelector col-md-8">
                                        <input class="col-md-1" type="radio" name="ear_specific.soundfield" id="button_ear_specific" />
                                        <label class="btn btn-primary col-md-11"  for="button_ear_specific">
                                            Espec�ficos del o�do</label> <br />
                                        <input class="col-md-1" type="radio" name="ear_specific.soundfield" id="button_soundfield" />
                                        <label class="btn btn-primary col-md-11" for="button_soundfield">Campo sonoro</label>
                                    </fieldset>


                                    <fieldset class="radioSetSelector col-md-4">
                                        <input  type="radio" name="addRemove" id="button_add" />
                                        <label class="btn btn-success " for="button_add"> <i class="glyphicon glyphicon-pencil"></i>  </label> <br />
                                        <input  type="radio" name="addRemove" id="button_remove" />
                                        <label class="btn btn-danger " for="button_remove"><i class="glyphicon glyphicon-trash"></i></label>
                                    </fieldset>

                                    <!--<button id="button_save" type="button">Save</button>-->
                                </section>

                                <section id="buttons_ear_specific">
                                    <fieldset class="radioSetSelector col-md-4">
                                        <input class="col-md-1" type="radio" name="leftRight"  id="button_left" />
                                        <label class="btn btn-info col-md-10" for="button_left">Izq</label> <br />
                                        <input class="col-md-1" type="radio" name="leftRight" id="button_right" />
                                        <label class="btn btn-info col-md-10" for="button_right">Der</label>
                                    </fieldset>

                                    <fieldset class="radioSetSelector col-md-4">
                                        <input class="col-md-1" type="radio" name="airBone" id="button_air" />
                                        <label class="btn btn-info col-md-10" for="button_air" title="Aire">Aire</label>  <br />
                                        <input class="col-md-1" type="radio" name="airBone" id="button_bone" />
                                        <label class="btn btn-info col-md-10" for="button_bone" title="Hueso">Hueso</label>
                                    </fieldset>

                                    <fieldset class="radioSetSelector col-md-4">
                                        <input class="col-md-1" type="radio" name="masking" id="button_unmasked" />
                                        <label class="btn btn-info col-md-10" for="button_unmasked" title="Desenmascarado">
                                            Desen.</label> <br />
                                        <input class="col-md-1" type="radio" name="masking" id="button_masked" />
                                        <label class="btn btn-info col-md-10" for="button_masked" title="Enmascarado">
                                            Enma.</label>
                                    </fieldset>
                                </section>

                                <section id="buttons_soundfield">
                                    <fieldset class="radioSetSelector col-md-8">
                                        <input class="col-md-1" type="radio" name="sft" id="button_unaided" />
                                        <label class="btn btn-info col-md-11" for="button_unaided">Sin ayuda</label>
                                        <input class="col-md-1" type="radio" name="sft" id="button_aided" />
                                        <label class="btn btn-info col-md-11" for="button_aided">Ayudado</label>
                                        <input class="col-md-1" type="radio" name="sft" id="button_ci" />
                                        <label class="btn btn-info col-md-11" for="button_ci">
                                            Implante coclear</label>
                                    </fieldset>
                                </section>
                            </section>
                        </fieldset>
                    </div>
                        </fieldset>
                                          

                    <script type="text/javascript" src="../js/media/js/ba-debug.min.js"></script>
                    <script type="text/javascript" src="../js/media/js/jquery.audiogram.js"></script>
                    <script type="text/javascript">
                                        $('#01496673').audiogram({
                                            editable: true
                        <%if (value != null && !value.isEmpty()) {%>, audiometricData: JSON.parse('<%=o.notEmpty(value)%>')<%}%>
                                        });

                    </script>

                    <input  <%if (campo.getOpcionesList() != null) {%>list="<%=campo.getCampIdf()%>_list"<%}%> id="<%=o.notEmpty(campo.getCampIdf())%>" name="<%=o.notEmpty(campo.getCampName())%>"  class="form-control " type="hidden" <%=o.notEmpty(campo.getCampAtributos())%>  value="<%=o.notEmpty(value, o.notEmpty(campo.getCampValue()))%>" >                      

                    <%
//                                System.out.println("value::: " + value);
                            }
                        }%>



                    <script type="text/javascript">
                        <%=o.notEmpty(cate.getCateScript())%>



                    </script>
                </fieldset>

                <% }
                System.out.println("TESTE DEMORA FORM::: 13 -> "+ fechas.getFechaHoraTimeStamp());

                %>



            </div>
            <!-- /.row (nested) -->
        </div>
        <div class="panel-footer">
            <div class="row">


                <div class="col-lg-9 right">

                </div>
                <div class="col-lg-3 right">
                    <div class="form-group">
                        <button  onclick="RecargaPanel('../panels/formularios/hc/timeLine.jsp?tick=<%=t.getTickId()%>&tipo=<%=request.getParameter("tipo")%>', 'panelprincipal')" type="button" class="btn btn-default bottom-right btn-outline">Atras </button>
                        <input type="hidden" name="anot_id" value="<%=anot_id%>">
                        <input type="hidden" name="tipo" value="<%=request.getParameter("tipo")%>">
                        <input type="hidden" name="tics" value="<%=o.notEmpty(request.getParameter("tics"))%>">
                        <input type="hidden" name="action" value="<%=o.notEmpty(modulo)%>">
                        <button  onclick="$('#Form-Data').submit()" type="button" class="btn btn-success bottom-right btn-outline">Guardar</button>
                        <label for="visiblexCliente" title="Ya no sera modificable"> �Visible por empresa?</label>
                        <input  id="visiblexCliente" type="checkbox" name="visiblexCliente" value="1">

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
<!--fin-->

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
    $('form').keypress(function (e) {
        if (e == 13) {
            return false;
        }
    });
    $('input').keypress(function (e) {
        if (e.which == 13) {
            return false;
        }
    });
</script>
<%       }catch(Exception ex ){
                ex.printStackTrace();
            }
            finally{
            em.close();
            }
         }else{%>
<script type="text/javascript">
    location.href='../logout.jsp';
</script>
<%}%>