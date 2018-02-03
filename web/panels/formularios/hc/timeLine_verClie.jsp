<%-- 
    Document   : timeLine
    Created on : 14/07/2016, 10:00:41 PM
    Author     : D4V3
--%>
<%@page import="ocupacional.JPA.controlers.CiudadesJpaController"%>
<%@page import="java.util.List"%>
<%@page import="javax.persistence.TypedQuery"%>
<%@page import="ocupacional.JPA.valueobjects.ProveedoresExamenes"%>
<%@page import="ocupacional.JPA.controlers.ProveedoresExamenesJpaController"%>
<%@page import="formularios.controlers.EmpleadosJpaController"%>
<%@page import="javax.persistence.Query"%>
<%@page import="javax.persistence.EntityManager"%>
<%@page import="ocupacional.JPA.valueobjects.Empleadoexamen"%>
<%@page import="ocupacional.JPA.controlers.ExamenesJpaController"%>
<%@page import="formularios.entidades.Formularios"%>
<%@page import="formularios.controlers.FormulariosJpaController"%>
<%@page import="ocupacional.JPA.valueobjects.ServiciosExamenes"%>
<%@page import="ocupacional.JPA.valueobjects.ClientesServicio"%>
<%@page import="ocupacional.JPA.valueobjects.Servicios"%>
<%@page import="ocupacional.JPA.valueobjects.TicketClienteservicio"%>
<%@page import="ocupacional.JPA.valueobjects.Examenes"%>
<%@page import="ocupacional.JPA.valueobjects.Ticket"%>
<%@page import="formularios.entidades.Anotaciones"%>
<%@page import="ocupacional.JPA.controlers.EntidadesJpaController"%>
<%@page import="ocupacional.JPA.controlers.EstadocivilJpaController"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="ocupacional.JPA.controlers.TicketJpaController"%>
<%@page import="formularios.controlers.PacientesJpaController"%>
<%@page import="javax.persistence.Persistence"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<%@page import="valeria.metodos.ManejadorFechas"%>
<%@page import="valeria.response.Mediador"%>
<%@page import="formularios.entidades.Pacientes"%>
<%
    try {

        Mediador e = (Mediador) session.getAttribute("Mediador");
        ManejadorFechas f = new ManejadorFechas();

        if (e != null) {

            EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaP1PU");
            EntityManagerFactory emf2 = Persistence.createEntityManagerFactory("JavaP");
            EntityManager em = emf.createEntityManager();
            try{

            PacientesJpaController pacic = new PacientesJpaController(emf);
            TicketJpaController tDAO = new TicketJpaController(emf2);
            Ticket tick = tDAO.findTicket(Integer.parseInt(request.getParameter("id")));
            Pacientes paci = pacic.findPacientes(tick.getTickPaciente());
%>



<div class="panel panel-default">

    <div class="panel-heading">
        <div class="row">
            <div class="col-md-12">
                <a href="#c0" data-toggle="collapse">
                    <h4 class="text-success"><%=e.o.notEmpty(paci.getPaciNombres().toUpperCase() + " " + paci.getPaciApellidos().toUpperCase()) + "   Doc." + e.o.notEmpty(paci.getPaciDocumento())%><br/>  <small><%=f.getDiffDates(paci.getPaciFechanacimiento(), f.getFechaHora())%> </small> </h4></a>
                <div class="" id="c0" style="">
                    <div class="col-md-2">
                        <%
                            if (paci.getPaciFoto() == null) {
                                if (paci.getPaciGenero().equals("Masculino")) {%>
                        <img src="../images/hombre.PNG" alt="foto" />
                        <%} else {%>
                        <img src="../images/mujer.PNG" alt="foto" />
                        <%}
                       } else {%>
                        <img src="<%=paci.getPaciFoto()%>" alt="foto" />
                        <%}%>
                    </div>
                    <div class="col-md-5">
                        <table class="table table-hover">
                            <tbody>
                                <tr>
                                    <td>Fecha de nacimiento:</td>
                                    <td><%=e.o.notEmpty(f.FechaLetras(new Timestamp(paci.getPaciFechanacimiento().getTime())))%></td>
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
    </div>

    <style type="text/css">


        *, *:after, *:before {
            -webkit-box-sizing: border-box;
            -moz-box-sizing: border-box;
            box-sizing: border-box;
        }

        h2{ margin: 0}

        img {
            max-width: 100%;
        }

        .cd-container {
            /* this class is used to give a max-width to the element it is applied to, and center it horizontally when it reaches that max-width */
            width: 95%;
            max-width: 1170px;
            margin: 0 auto;
        }
        .cd-container::after {
            /* clearfix */
            content: '';
            display: table;
            clear: both;
        }



        #cd-timeline {
            position: relative;
            padding: 2em 0;
            margin-top: 2em;
            margin-bottom: 2em;
        }
        #cd-timeline::before {
            /* this is the vertical line */
            content: '';
            position: absolute;
            top: 0;
            left: 18px;
            height: 100%;
            width: 4px;
            background: #d7e4ed;
        }
        @media only screen and (min-width: 1170px) {
            #cd-timeline {
                margin-top: 3em;
                margin-bottom: 3em;
            }
            #cd-timeline::before {
                left: 50%;
                margin-left: -2px;
            }
        }

        .cd-timeline-block {
            position: relative;
            margin: 1em 0;
        }
        .cd-timeline-block::after {
            clear: both;
            content: "";
            display: table;
        }
        .cd-timeline-block:first-child {
            margin-top: 0;
        }
        .cd-timeline-block:last-child {
            margin-bottom: 0;
        }
        @media only screen and (min-width: 1170px) {
            .cd-timeline-block {
                margin: 2em 0;
            }
            .cd-timeline-block:first-child {
                margin-top: 0;
            }
            .cd-timeline-block:last-child {
                margin-bottom: 0;
            }
        }

        .cd-timeline-img {
            position: absolute;
            top: 0;
            left: 0;
            width: 40px;
            height: 40px;
            border-radius: 50%;
            box-shadow: 0 0 0 2px #ffffff, inset 0 2px 0 rgba(0, 0, 0, 0.08), 0 3px 0 2px rgba(0, 0, 0, 0.05);
        }
        #img_head {
            position: absolute;
            top: 15px;
            left: 20px;
            width: 40px;
            height: 40px;
            border-radius: 50%;
            box-shadow: 0 0 0 2px #ffffff, inset 0 2px 0 rgba(0, 0, 0, 0.08), 0 3px 0 2px rgba(0, 0, 0, 0.05);
            /*    width: 40px;
                height: 40px;*/
            background: #75ce66;

            /* Force Hardware Acceleration in WebKit */
            -webkit-transform: translateZ(0);
            -webkit-backface-visibility: hidden;
        }
        .cd-timeline-img img, #img_head img {
            display: block;
            width: 40px;
            height: 40px;
            position: relative;
            left: 50%;
            top: 50%;
            margin-left: -20px;
            margin-top: -20px;
        }
        .cd-timeline-img.cd-picture {
            background: #75ce66;
        }
        .cd-timeline-img.cd-movie {
            background: #c03b44;
        }
        .cd-timeline-img.cd-location {
            background: #f0ca45;
        }
        @media only screen and (min-width: 1170px) {
            .cd-timeline-img {
                width: 30px;
                height: 30px;
                left: 50%;
                margin-left: -15px;
                /* Force Hardware Acceleration in WebKit */
                -webkit-transform: translateZ(0);
                -webkit-backface-visibility: hidden;
            }

            .cssanimations .cd-timeline-img.is-hidden {
                visibility: hidden;
            }
            .cssanimations .cd-timeline-img.bounce-in {
                visibility: visible;
                -webkit-animation: cd-bounce-1 0.6s;
                -moz-animation: cd-bounce-1 0.6s;
                animation: cd-bounce-1 0.6s;
            }
        }

        @-webkit-keyframes cd-bounce-1 {
            0% {
                opacity: 0;
                -webkit-transform: scale(0.5);
            }
            60% {
                opacity: 1;
                -webkit-transform: scale(1.2);
            }
            100% {
                -webkit-transform: scale(1);
            }
        }
        @-moz-keyframes cd-bounce-1 {
            0% {
                opacity: 0;
                -moz-transform: scale(0.5);
            }
            60% {
                opacity: 1;
                -moz-transform: scale(1.2);
            }
            100% {
                -moz-transform: scale(1);
            }
        }
        @keyframes cd-bounce-1 {
            0% {
                opacity: 0;
                -webkit-transform: scale(0.5);
                -moz-transform: scale(0.5);
                -ms-transform: scale(0.5);
                -o-transform: scale(0.5);
                transform: scale(0.5);
            }
            60% {
                opacity: 1;
                -webkit-transform: scale(1.2);
                -moz-transform: scale(1.2);
                -ms-transform: scale(1.2);
                -o-transform: scale(1.2);
                transform: scale(1.2);
            }
            100% {
                -webkit-transform: scale(1);
                -moz-transform: scale(1);
                -ms-transform: scale(1);
                -o-transform: scale(1);
                transform: scale(1);
            }
        }
        .cd-timeline-content {
            position: relative;
            margin-left: 60px;
            background: #ffffff;
            border-radius: 0.5em;
            padding: 0.3em;

            border: 1px solid #d7e4ed ;
            box-shadow: 0 3px 0 #d7e4ed;
        }
        .cd-timeline-content::after {
            clear: both;
            content: "";
            display: table;
        }
        .cd-timeline-content h2 {
            color: #303e49;
        }
        .cd-timeline-content p, .cd-timeline-content .cd-read-more, .cd-timeline-content .cd-date {
            font-size: 12px;
            /*font-size: 0.8125rem;*/
        }
        .cd-timeline-content .cd-read-more, .cd-timeline-content .cd-date {
            display: inline-block;
        }
        .cd-timeline-content p {
            margin: 1em 0;
            line-height: 1.6;
        }
        .cd-timeline-content .cd-read-more {
            float: right;
            padding: .8em 1em;
            background: #acb7c0;
            color: #ffffff;
            border-radius: 0.25em;
        }
        .no-touch .cd-timeline-content .cd-read-more:hover {
            background-color: #bac4cb;
        }
        .cd-timeline-content .cd-date {
            float: left;
            padding: .8em 0;
            opacity: .7;
        }
        .cd-timeline-content::before {
            content: '';
            position: absolute;
            top: 16px;
            right: 100%;
            height: 0;
            width: 0;
            border: 7px solid transparent;
            border-right: 7px solid #ffffff;
        }
        @media only screen and (min-width: 768px) {
            .cd-timeline-content h2 {
                font-size: 16px;
                /*font-size: 1.25rem;*/
            }
            .cd-timeline-content p {
                font-size: 14px;
                /*font-size: 1rem;*/
            }
            .cd-timeline-content .cd-read-more, .cd-timeline-content .cd-date {
                font-size: 12px;
                /*font-size: 0.875rem;*/
            }
        }
        @media only screen and (min-width: 1170px) {
            .cd-timeline-content {
                margin-left: 0;
                padding: 0.5em;
                width: 45%;
            }
            .cd-timeline-content::before {
                top: 24px;
                left: 100%;
                border-color: transparent;
                border-left-color: #ffffff;
            }
            .cd-timeline-content .cd-read-more {
                float: left;
            }
            .cd-timeline-content .cd-date {
                position: absolute;
                width: 100%;
                left: 122%;
                top: 6px;
                font-size: 12px;
                /*font-size: 1rem;*/
            }
            .cd-timeline-block:nth-child(even) .cd-timeline-content {
                float: right;
            }
            .cd-timeline-block:nth-child(even) .cd-timeline-content::before {
                top: 24px;
                left: auto;
                right: 100%;
                border-color: transparent;
                border-right-color: #ffffff;
            }
            .cd-timeline-block:nth-child(even) .cd-timeline-content .cd-read-more {
                float: right;
            }
            .cd-timeline-block:nth-child(even) .cd-timeline-content .cd-date {
                left: auto;
                right: 122%;
                text-align: right;
            }
            .cssanimations .cd-timeline-content.is-hidden {
                visibility: hidden;
            }
            .cssanimations .cd-timeline-content.bounce-in {
                visibility: visible;
                -webkit-animation: cd-bounce-2 0.6s;
                -moz-animation: cd-bounce-2 0.6s;
                animation: cd-bounce-2 0.6s;
            }
        }

        @media only screen and (min-width: 1170px) {
            /* inverse bounce effect on even content blocks */
            .cssanimations .cd-timeline-block:nth-child(even) .cd-timeline-content.bounce-in {
                -webkit-animation: cd-bounce-2-inverse 0.6s;
                -moz-animation: cd-bounce-2-inverse 0.6s;
                animation: cd-bounce-2-inverse 0.6s;
            }
        }
        @-webkit-keyframes cd-bounce-2 {
            0% {
                opacity: 0;
                -webkit-transform: translateX(-100px);
            }
            60% {
                opacity: 1;
                -webkit-transform: translateX(20px);
            }
            100% {
                -webkit-transform: translateX(0);
            }
        }
        @-moz-keyframes cd-bounce-2 {
            0% {
                opacity: 0;
                -moz-transform: translateX(-100px);
            }
            60% {
                opacity: 1;
                -moz-transform: translateX(20px);
            }
            100% {
                -moz-transform: translateX(0);
            }
        }
        @keyframes cd-bounce-2 {
            0% {
                opacity: 0;
                -webkit-transform: translateX(-100px);
                -moz-transform: translateX(-100px);
                -ms-transform: translateX(-100px);
                -o-transform: translateX(-100px);
                transform: translateX(-100px);
            }
            60% {
                opacity: 1;
                -webkit-transform: translateX(20px);
                -moz-transform: translateX(20px);
                -ms-transform: translateX(20px);
                -o-transform: translateX(20px);
                transform: translateX(20px);
            }
            100% {
                -webkit-transform: translateX(0);
                -moz-transform: translateX(0);
                -ms-transform: translateX(0);
                -o-transform: translateX(0);
                transform: translateX(0);
            }
        }
        @-webkit-keyframes cd-bounce-2-inverse {
            0% {
                opacity: 0;
                -webkit-transform: translateX(100px);
            }
            60% {
                opacity: 1;
                -webkit-transform: translateX(-20px);
            }
            100% {
                -webkit-transform: translateX(0);
            }
        }
        @-moz-keyframes cd-bounce-2-inverse {
            0% {
                opacity: 0;
                -moz-transform: translateX(100px);
            }
            60% {
                opacity: 1;
                -moz-transform: translateX(-20px);
            }
            100% {
                -moz-transform: translateX(0);
            }
        }
        @keyframes cd-bounce-2-inverse {
            0% {
                opacity: 0;
                -webkit-transform: translateX(100px);
                -moz-transform: translateX(100px);
                -ms-transform: translateX(100px);
                -o-transform: translateX(100px);
                transform: translateX(100px);
            }
            60% {
                opacity: 1;
                -webkit-transform: translateX(-20px);
                -moz-transform: translateX(-20px);
                -ms-transform: translateX(-20px);
                -o-transform: translateX(-20px);
                transform: translateX(-20px);
            }
            100% {
                -webkit-transform: translateX(0);
                -moz-transform: translateX(0);
                -ms-transform: translateX(0);
                -o-transform: translateX(0);
                transform: translateX(0);
            }
        }




    </style>



    <!--    </head>
    <body>-->

    <div class="panel-body ">  

        <a href="#cd-timeline" data-toggle="collapse"><legend>Resultados</legend></a>
        <section id="cd-timeline" class="cd-container" >

            <%
                int i = 0;
                System.out.println("paso 1");

                System.out.println("paso 2");

                for (TicketClienteservicio tcs : tick.getTicketClienteservicioList()) {

                    TypedQuery<Anotaciones> consulta = em.createNamedQuery("Anotaciones.tics", Anotaciones.class);
                    consulta.setParameter("estado", "FINALIZADO");
                    consulta.setParameter("id", tcs.getTicsId());
                    List<Anotaciones> listaClienteServicio = consulta.getResultList();

                    for (Anotaciones a : listaClienteServicio) {
                        if (a.getAnotvisiblePorClie() == 1) {
                            i++;
            %>
            <div class="cd-timeline-block">
                <%
                    if (new ExamenesJpaController(emf2).findExamenes(a.getFormId().getExamId()).getExamTipo().equals("EXAMEN")) {
                %>
                <div class="cd-timeline-img cd-picture">
                    <img src="../images/examen_icon.svg" style="max-width: 24px; margin-left: -12px;" alt="Picture">
                </div> <!-- cd-timeline-img -->
                <%
                        } else if (new ExamenesJpaController(emf2).findExamenes(a.getFormId().getExamId()).getExamTipo().equals("LABORATORIO")) { %>
                <div class="cd-timeline-img cd-movie">
                    <img src="../images/laboratorio_icon.svg" style="max-width: 24px; margin-left: -12px;" alt="Picture">
                </div>  
                <%}%>
                <div class="cd-timeline-content">

                    <h2 ><a style="color: #009900" href="#" 
                            <% if (!new ExamenesJpaController(emf2).findExamenes(a.getFormId().getExamId()).getExamDescripcion().equals("Certificado medico ocupacional")) {%>  onclick="window.open('../panels/formularios/hc/form_ver.jsp?codigoPaciente=<%=paci.getPaciId()%>&anot_ocu=<%=a.getAnotId()%>', '_blank', 'toolbar=no,scrollbars=yes,resizable=yes,top=50,left=50,width=800,height=600')"
                            <%} else {%> onclick=" window.open('../Reportes?action=certificadoMedico&anot=<%=a.getAnotId()%>', '_blank', 'toolbar=no,scrollbars=yes,resizable=yes,top=50,left=50,width=800,height=600')"
                            <%}%>  ><%=new ExamenesJpaController(emf2).findExamenes(a.getFormId().getExamId()).getExamDescripcion()%></a>
                        <a style="color: #009900"  href="#demo<%=i%>" data-toggle="collapse" ><span class="glyphicon glyphicon-check"></span></a></h2>

                    <div  class="collapse" id="demo<%=i%>">
                        <p>Contenido</p>
                    </div>
                    <span class="cd-date"><%=e.o.notEmpty(f.FechaLetrasHora(new Timestamp(a.getAnotFechacambio().getTime())))%></span>
                </div> <!-- cd-timeline-content -->
            </div> <!-- cd-timeline-block -->

            <% }
                    }
                }
                if (i == 0) {

            %> 
            No posee anotaciones.
            <script type="text/javascript">
                $('#cd-timeline').removeClass('cd-container');
                $('#cd-timeline').removeAttr('id');

            </script>
            <%}%>

        </section> <!-- cd-timeline -->





        <script type="text/javascript">
            jQuery(document).ready(function ($) {
                var $timeline_block = $('.cd-timeline-block');

                //hide timeline blocks which are outside the viewport
                $timeline_block.each(function () {
                    if ($(this).offset().top > $(window).scrollTop() + $(window).height() * 0.75) {
                        $(this).find('.cd-timeline-img, .cd-timeline-content').addClass('is-hidden');
                    }
                });

                //on scolling, show/animate timeline blocks when enter the viewport
                $(window).on('scroll', function () {
                    $timeline_block.each(function () {
                        if ($(this).offset().top <= $(window).scrollTop() + $(window).height() * 0.75 && $(this).find('.cd-timeline-img').hasClass('is-hidden')) {
                            $(this).find('.cd-timeline-img, .cd-timeline-content').removeClass('is-hidden').addClass('bounce-in');

                        }
                    });
                });
            });
        </script>
    </div>    
    <div class="panel-footer">

        <div class="row">

            <div class="col-lg-9 right">

            </div>
            <div class="col-lg-3 right">
                <div class="form-group">

                    <button  onclick="RecargaPanel('../panels/formularios/factura/ordenServicio_ver.jsp?id=<%=tick.getTickId()%>', 'panelprincipal')" type="button" class="btn btn-default bottom-right btn-outline">Atras </button>

                    <!--<button  onclick="" type="submit" class="btn btn-success bottom-right btn-outline">Guardar</button>-->

                </div>
            </div>
        </div>
    </div>
</div>
<%     }catch (Exception ex) {
                ex.printStackTrace();
            }finally {
                em.close();
            }
    }else{%>
<script type="text/javascript">
    location.href = '../logout.jsp';
</script>
<%}
    }
    catch(Exception ex

    
        ){ex.printStackTrace();
    }%>