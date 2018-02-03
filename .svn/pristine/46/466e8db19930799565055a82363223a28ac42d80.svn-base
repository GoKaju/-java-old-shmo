<%-- 
        DESAROLLADOR : Alejandro 
        NOMBRE DEL DOCUMENTO   : header
        FECHA DE CREACION : 05-nov-2015, 12:53:04  
        HISTORIA DE USUARIO :
        DESCRIPCION FUNCIONAL DEL DOCUMENTO : 
--%>

<%@page import="ocupacional.JPA.controlers.SedeJpaController"%>
<%@page import="javax.persistence.Persistence"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<%@page import="valeria.response.Mediador"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <% response.addHeader("Expires:", "Mon, 01 Jan 2001 00:00:00 GMT");
            response.addHeader("Pragma", "no-cache");
            response.addHeader("Cache-Control", "no-cache, must-revalidate");
            response.addHeader("Cache-Control", "post-check=0, pre-check=0");
            response.addHeader("Cache-Control", "private");%>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>JAVAP (SALUD OCUPACIONAL)</title>
        <!-- Bootstrap Core CSS -->
        <link href="../css/bootstrap.css" rel="stylesheet">
        <link href="../css/gistfile1.css" rel="stylesheet">
        <link href="../css/Loader.css" rel="stylesheet">
        <!-- MetisMenu CSS -->
        <link href="../css/plugins/metisMenu/metisMenu.min.css" rel="stylesheet">
        <!-- Timeline CSS -->
        <link href="../css/plugins/timeline.css" rel="stylesheet">
        <!-- Custom CSS -->
        <link href="../css/sb-admin-2.css" rel="stylesheet">
        <!--<link href="../css/chat-css.css" rel="stylesheet">-->
        <!-- Morris Charts CSS -->
        <link href="../css/plugins/morris.css" rel="stylesheet">
        <link href="../css/plugins/bootstrap-combobox.css" rel="stylesheet">
        <link href="../css/plugins/bootstrap-select.min.css" rel="stylesheet">
        <!--<link href="../css/perfect-scrollbar.css" rel="stylesheet">-->
        <link href="../js/bootstrap-datepicker-1.5.1-dist/css/bootstrap-datepicker.css" rel="stylesheet">
        <!-- Custom Fonts -->
        <link href="../font-awesome-4.1.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
        <link href="../js/alertify/alertify.core.css" rel="stylesheet" type="text/css">
        <link href="../js/alertify/alertify.default.css" rel="stylesheet" type="text/css">
        <link href="../js/parsley.css" rel="stylesheet" type="text/css">
        <!--<link href="../css/plugins/bselect.css" rel="stylesheet" type="text/css">-->
        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
            <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->

        <!-- jQuery Version 1.11.0 -->
        <script src="../js/jquery-1.11.0.js"></script>
        <!-- Bootstrap Core JavaScript -->
        <script src="../js/bootstrap.js"></script>
        <script src="../js/bootstrapValidator.js"></script>
        <script src="../js/bootstrap-datepicker-1.5.1-dist/js/bootstrap-datepicker.js"></script>
        <script src="../js/bootstrap-datepicker-1.5.1-dist/locales/bootstrap-datepicker.es.min.js" charset="UTF-8"></script>
        <script src="../js/plugins/metisMenu/metisMenu.min.js"></script>
        <script src="../js/plugins/bootstrap-combobox.js"></script>
        <script src="../js/plugins/bootstrap-select.min.js"></script>
        <!--<script src="../js/perfect-scrollbar.jquery.js"></script>-->

        <!-- Metis Menu Plugin JavaScript -->
        <!-- 
        Morris Charts JavaScript 
        -->
        <script src="../js/plugins/morris/raphael.min.js"></script>
        <!--<script src="../js/plugins/morris/morris.min.js"></script>
        <script src="../js/plugins/morris/morris-data.js"></script>-->
        <!-- Custom Theme JavaScript -->
        <script src="../js/sb-admin-2.js"></script>
        <script src="../js/alertify.js"></script>
        <script src="../js/Manejador.js"></script>
        <script src="../tablefilter/tablefilter.js"></script>
        <!--<script src="../js/plugins/bselect.min.js">-->
        
        <!--x-editable-->
        <link href="//cdnjs.cloudflare.com/ajax/libs/x-editable/1.5.0/bootstrap3-editable/css/bootstrap-editable.css" rel="stylesheet"/>
<script src="//cdnjs.cloudflare.com/ajax/libs/x-editable/1.5.0/bootstrap3-editable/js/bootstrap-editable.min.js"></script>

        <!--        </script>-->
        <style>
            fieldset{
                padding: 15px;

            }
        </style>
    </head>

    <body  >
        <div class="modalLoader" hidden="">

            <div class="spinner">
                <span class="ball-1"></span>
                <span class="ball-2"></span>
                <span class="ball-3"></span>
                <span class="ball-4"></span>
                <span class="ball-5"></span>
                <span class="ball-6"></span>
                <span class="ball-7"></span>
                <span class="ball-8"></span>
            </div>
        </div>
        <!-- Modal chat -->
<!--        <div id="chatModal"  hidden="">
            <div class="modal-dialog modal-lg">

                 Modal content
                <div class="modal-content">
                          <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                            <h4 class="modal-title">Javap - Chat</h4>
                          </div>
                    <div class="modal-body">
                        <div id="chat-container" class="row" >

                            <div id="chat-side" class="panel panel-primary ">
                                <div class="panel-heading">
                                    Usuarios conectados

                                </div>
                                <div class="panel-body" >
                                    <div class="row" id="chat-users">
                                        
                                    </div>


                                </div>
                                <div class="panel-footer">
                                    <div class="row">

                                        <div class="col-md-12 ">
                                            <div class="form-group ">
                                                <input id="txt_buscar_usuario" placeholder="Buscar usuario"  class="form-control" type="text"    >
                                            </div>
                                        </div>

                                    </div>
                                </div>

                            </div>
                            <div id="chat-conversations" class="panel panel-primary ">
                                <ul class="nav nav-tabs">
                                    <li class=""><a data-toggle="tab" href="#home">Home</a></li>
                                    <li><a data-toggle="tab" href="#menu1">Menu 1</a></li>
                                </ul>

                                <div class="tab-content chat-msj-container ">
                                    <div id="home" class="tab-pane fade in active">
                                        <h3>HOME</h3>
                                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
                                    </div>
                                    <div id="menu1" class="tab-pane fade">
                                        <div class="alert alert-info mensajeOtros">
                                            <strong>Info!</strong> Indicates
                                        </div>                     
                                        <div class="alert alert-success mensajeYo">
                                            <strong>Info!</strong> Indicates
                                        </div>
                                        <div class="alert alert-info mensajeOtros">
                                            <strong>Info!</strong> Indicates
                                        </div>                     
                                        <div class="alert alert-success mensajeYo">
                                            <strong>Info!</strong> Indicates
                                        </div>
                                        <div class="alert alert-info mensajeOtros">
                                            <strong>Info!</strong> Indicates
                                        </div>                     
                                        <div class="alert alert-success mensajeYo">
                                            <strong>Info!</strong> Indicates
                                        </div>
                                        <div class="alert alert-info mensajeOtros">
                                            <strong>Info!</strong> Indicates
                                        </div>                     
                                        <div class="alert alert-success mensajeYo">
                                            <strong>Info!</strong> Indicates
                                        </div>
                                        <div class="alert alert-info mensajeOtros">
                                            <strong>Info!</strong> Indicates
                                        </div>                     
                                        <div class="alert alert-success mensajeYo">
                                            <strong>Info!</strong> Indicates
                                        </div>
                                        <div class="alert alert-info mensajeOtros">
                                            <strong>Info!</strong> Indicates
                                        </div>                     
                                        <div class="alert alert-success mensajeYo">
                                            <strong>Info!</strong> Indicates
                                        </div>
                                    </div>

                                </div>




                                <div class="panel-footer">
                                    <div class="row">

                                        <div class="col-md-11 ">
                                            <div class="form-group ">
                                                <input id="txt_messaje" placeholder="Mensage"  class="form-control" type="text"    >

                                            </div>
                                        </div>
                                        <div class="col-md-1 ">
                                            <div class="form-group ">
                                                <button id="btn_enviarmje"  class="btn btn-success btn-circle" type="button" ><i class="fa fa-send"></i></button>

                                            </div>
                                        </div>

                                    </div> 
                                </div>

                            </div>


                        </div>
                        <script type="text/javascript">
//    $('#chat-msj-container').perfectScrollbar();
                        </script>

                    </div>
                          <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                          </div>
                </div>

            </div>
        </div>-->


        <script>
//            $("#bodyp").css("height",($(window).height()-10)+"px");

        </script>
        <div id="wrapper" >
            <!-- Navigation -->
            <nav class="navbar navbar-default navbar-static-top" role="navigation" style="">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <%
                        Mediador e = (Mediador) session.getAttribute("Mediador");
                        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaP");
                    %>
                    <a class="navbar-brand" href="Inicio">Javap Salud Ocupacional - <%=new SedeJpaController(emf).findSede(Integer.parseInt(e.getUsuarioVO().getSede_id())).getSedeNombre().toUpperCase()%></a>
                </div>
                <!-- /.navbar-header -->
                <jsp:include page="top_menu.jsp"/>
                <jsp:include page="left_menu.jsp"/>
            </nav>
