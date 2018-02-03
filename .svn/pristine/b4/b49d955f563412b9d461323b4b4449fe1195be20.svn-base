<%-- 
    Document   : form_ver
    Created on : 16/07/2016, 01:04:37 PM
    Author     : D4V3
--%>

<%@page import="formularios.controlers.PacientesJpaController"%>
<%@page import="ocupacional.JPA.controlers.CiudadesJpaController"%>
<%@page import="ocupacional.JPA.controlers.EntidadesJpaController"%>
<%@page import="ocupacional.JPA.controlers.EstadocivilJpaController"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="formularios.entidades.Huellafirma"%>
<%@page import="ocupacional.JPA.valueobjects.ServiciosExamenes"%>
<%@page import="valeria.metodos.ManejadorFechas"%>
<%@page import="formularios.entidades.Documentos"%>
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

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaP1PU");
        EntityManagerFactory emf2 = Persistence.createEntityManagerFactory("JavaP");
        EntityManager em = emf.createEntityManager();
        try {
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

            FormulariosJpaController fc = new FormulariosJpaController(emf);

            Pacientes paci = request.getParameter("codigoPaciente") == null ? (Pacientes) session.getAttribute("Paciente") : new PacientesJpaController(emf).findPacientes(Integer.parseInt(request.getParameter("codigoPaciente")));

            String tcls = o.notEmpty(request.getParameter("tics"));
//  determinar si el formulario ya fue llenado y cargar anotacion
//  cargar por tcls

            Anotaciones anot = new AnotacionesJpaController(emf).findAnotaciones(Integer.parseInt(request.getParameter("anot_ocu")));
            Formularios f = anot.getFormId();

            // recrear formulario con anotacion
            String anot_id = "";
            if (anot.getAnotId() != null) {
                anot_id = anot.getAnotId().toString();

            }


%>

<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>JAVAP (SALUD OCUPACIONAL)</title>
        <!-- Bootstrap Core CSS -->
        <link href="../../../css/bootstrap.css" rel="stylesheet">

        <!-- jQuery Version 1.11.0 -->
        <script src="../../../js/jquery-1.11.0.js"></script>
        <!-- Bootstrap Core JavaScript -->
        <script src="../../../js/bootstrap.js"></script>
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

    </head>
    <body>

        <script type="text/javascript">
            var genero = '<%=paci.getPaciGenero()%>';


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
                                            <td>Dirección: </td>
                                            <td><%=e.o.notEmpty(paci.getPaciDireccion())%><%if (paci.getCiudId() != null) {
                                                    out.print(" - " + new CiudadesJpaController(emf2).findCiudades(paci.getCiudId()).getCiudNombre() + ", " + new CiudadesJpaController(emf2).findCiudades(paci.getCiudId()).getDepaId().getDepaNombre());
                                                }%></td>
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
                <div class="row">
                    <div class="col-md-12">
                        <center><h3 class="text-success"><%=o.notEmpty(new ExamenesJpaController(emf2).findExamenes(f.getExamId()).getExamDescripcion())%></h3></center> 
                    </div>
                </div>
            </div>
            <form   id="Form-Data" role="form" name="Form-Data"  method="POST" action="../<%=o.notEmpty(action)%>" autocomplete="off">
                <div class="panel-body">

                    <div class="row">
                        <% for (Categorias cate : f.getCategoriasList()) {%>
                        <fieldset>
                            <div class="col-md-12 ">
                                <legend class="label label-info col-md-12" style="display: block;font-size: 16px ; width: 100%"><%=o.notEmpty(cate.getCateDescripcion())%></legend>

                            </div>

                            <%
                                for (Campos campo : cate.getCamposList()) {
//                                    System.out.println("Categoria::: "+ cate.getCateDescripcion());
//                                    System.out.println("tamaño " + cate.getCamposList().size());

                                    //                            Aplicar value a los campos
                                    String value = null;
                                    if (anot.getAnotId() != null) {

                                        for (Respuestas r : anot.getRespuestasList()) {
                                            if (r.getCampId().equals(campo)) {
                                                value = o.notEmpty(r.getRespDescripcion());
                                                break;
                                            }

                                        }
                                    }

                                    if (campo.getCampTipo().equals("text")) {
                            %>

                            <div class="form-group col-md-4" >



                                <dl class="dl-horizontal"  id="<%=o.notEmpty(campo.getCampIdf())%>">
                                    <dt><%=o.notEmpty(campo.getCampLabel())%></dt>
                                    <dd><%=o.notEmpty(value, o.notEmpty(campo.getCampValue(), ""))%></dd>

                                </dl>



                            </div>

                            <% } else if (campo.getCampTipo().equals("fecha")) {
                            %>
                            <div class="form-group col-md-4" >

                                <%
                                    String fecha = "";
                                    if (value != null && value.equals("")) {
                                        value = null;
                                    }
                                    fecha = o.notEmpty(value);

                                %>


                                <dl class="dl-horizontal" id="<%=o.notEmpty(campo.getCampIdf())%>" >
                                    <dt><%=o.notEmpty(campo.getCampLabel())%></dt>
                                    <dd><%=o.notEmpty(fecha)%></dd>

                                </dl>



                            </div>



                            <% } else if (campo.getCampTipo().equals("textarea")) {%>

                            <div class="form-group col-md-12">


                                <dl class="dl-horizontal" id="<%=o.notEmpty(campo.getCampIdf())%>">
                                    <dt><%=o.notEmpty(campo.getCampLabel())%></dt>
                                    <dd> <p><%=o.notEmpty(value, o.notEmpty(campo.getCampValue(), ""))%></p></dd>


                                </dl>
                            </div>


                            <% } else if (campo.getCampTipo().equals("selectmultiple")) {%>

                            <div class="form-group col-md-4">


                                <dl class="dl-horizontal" id="<%=o.notEmpty(campo.getCampIdf())%>">
                                    <dt title="<%=o.notEmpty(campo.getCampLabel())%>"><%=o.notEmpty(campo.getCampLabel())%></dt>
                                    <dd><%=o.notEmpty(value)%></dd>

                                </dl>
                            </div>

                            <% } else if (campo.getCampTipo().equals("select")) {%>

                            <div class="form-group col-md-4">


                                <dl class="dl-horizontal" id="<%=o.notEmpty(campo.getCampIdf())%>">
                                    <dt><%=o.notEmpty(campo.getCampLabel())%></dt>
                                    <dd><%=o.notEmpty(value, o.notEmpty(campo.getCampValue(), ""))%></dd>


                                </dl>
                            </div>

                            <%  } else if (campo.getCampTipo().equals("hr")) {%>
                            <legend></legend>
                            <%    } else if (campo.getCampTipo().equals("subTitle")) {%>

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
                                        Ticket t = new TicketClienteservicioJpaController(emf2).findTicketClienteservicio(anot.getTicsId()).getTickId();

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
                                        <td><%=se.getExamId().getExamDescripcion()%></td>
                                        <td><%
                                            if (an.getRespuestasList() != null) {
                                                for (Respuestas re : an.getRespuestasList()) {
                                                    if (re.getCampId().getCamptipoAux().equals("CONCEPTO")) {
                                            %>
                                            <%=o.notEmpty(re.getRespDescripcion())%><br />
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

                            <fieldset class="audiogram col-md-8">
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
                                <fieldset class="controls col-md-5" hidden="">
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
                            <fieldset class="col-md-4">
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

                            <script type="text/javascript" src="../../../js/media/js/ba-debug.min.js"></script>
                            <script type="text/javascript" src="../../../js/media/js/jquery.audiogram_1.js"></script>
                            <script type="text/javascript">
            $('#01496673').audiogram({
                editable: false,
                audiometricData: JSON.parse('<%=o.notEmpty(value)%>')
            });

                            </script>

                            <input  <%if (campo.getOpcionesList() != null) {%>list="<%=campo.getCampIdf()%>_list"<%}%> id="<%=o.notEmpty(campo.getCampIdf())%>" name="<%=o.notEmpty(campo.getCampName())%>"  class="form-control " type="hidden" <%=o.notEmpty(campo.getCampAtributos())%>  value="<%=o.notEmpty(value, o.notEmpty(campo.getCampValue()))%>" >                      

                            <%
//                                        System.out.println("value::: " + value);
                                    }
                                }%>


                            <script type="text/javascript">
                                <%=o.notEmpty(cate.getCateScript())%>



                            </script>
                        </fieldset>


                        <%}%>
                        <%
                            if (anot.getHuellafirmaList() != null && !anot.getHuellafirmaList().isEmpty()) {%>


                        <fieldset>
                            <legend>Huella y firma</legend>
                            <%for (Huellafirma hf : anot.getHuellafirmaList()) {%>
                            <div class="row">
                                <center>
                                    <div class="col-md-4" ></div>
                                    <div class="col-md-4"><img width="200" class="" src="<%=o.notEmpty(hf.getAnotFirma())%>" alt="No hay firma" />
                                        <img width="200" class="" src="<%=o.notEmpty(hf.getAnotHuella())%>" alt="No hay huella" />

                                    </div>
                                    <div class="col-md-4" ></div>

                                </center>
                            </div>
                            <% }%>
                        </fieldset>                






                        <%}%>
                        <fieldset>
                            <legend>Adjuntos</legend>

                            <table id="tablaDocumentos" class=" table table-hover">
                                <thead>
                                    <tr>
                                        <th>NOMBRE</th>
                                        <th>PESO</th>
                                        <th>FECHA</th>
                                        <th></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        int c = 0;
                                        for (Documentos d : anot.getDocumentosList()) {
                                    %>
                                    <tr>
                                        <td><%=d.getDocuNombre()%></td>  
                                        <td><%=d.getDocuPeso()%></td>  
                                        <td><%= new ManejadorFechas().DevuelveFormato(d.getDocFechacambio())%></td> 
                                        <td>
                                            <button type="button" data-toggle="collapse" data-target="#adjun<%=c%>" title="ver" class="btn btn-circle btn-primary "> <span class="glyphicon glyphicon-search"></span></button>
                                            <a href='../../../Adjuntos/<%=d.getDocuRuta()%>' download='<%=d.getDocuNombre()%>' title="Descargar" class="btn btn-circle btn-success "> <span class="glyphicon glyphicon-download"></span> </a>


                                        </td>


                                    </tr>
                                    <tr class="collapsing" id="adjun<%=c%>" >
                                        <td colspan="4">
                                            <embed src='../../../Adjuntos/<%=d.getDocuRuta()%>' style="width: 100%"  height='500' alt='pdf' pluginspage='http://www.adobe.com/products/acrobat/readstep2.html'>

                                        </td>
                                    </tr>
                                    <%
                                            c++;
                                        }%>

                                </tbody>
                            </table>
                        </fieldset>


                    </div>
                    <!-- /.row (nested) -->
                </div>
                <div class="panel-footer">
                    <div class="row">
                        <center>

                            <div class="form-group">
                                <button  onclick=" window.close()" type="button" class="btn btn-success bottom-right btn-outline">Cerrar</button>

                            </div>
                        </center>

                    </div>


                </div>
            </form>
        </div>
        <div id="cajaAyuda"   onclick="cerrarAyuda()" title="Click para cerrar" style="cursor: pointer;overflow-y: auto; overflow-x: hidden">


            <img id="imagenAyuda" src="../mostrarImg.jsp" width="400" alt="no se pudo mostrar imagen" />

            <div id="descripAyuda">

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

        </script>
        <%       } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                em.close();
            }
        } else {%>
        <script type="text/javascript">
            location.href = '../../../logout.jsp';
        </script>
        <%}%>
    </body>
</html>