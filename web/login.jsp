<%-- 
        DESAROLLADOR : Alejandro 
        NOMBRE DEL DOCUMENTO   : login
        FECHA DE CREACION : 30-oct-2015, 21:38:20  
        HISTORIA DE USUARIO :
        DESCRIPCION FUNCIONAL DEL DOCUMENTO : 
--%>

<%@page import="ocupacional.servlets.historiaclinica.variables"%>
<%@page import="valeria.metodos.Encriptacion"%>
<%@page import="valeria.response.Mediador"%>
<%@page import="valeria.conexion.ConexionAplicacion"%>
<%
    Mediador e = (Mediador) session.getAttribute("Mediador");
%>
<!DOCTYPE html>
<html lang="es">
    <!DOCTYPE html>
    <html lang="es">
        <head>
            <meta charset="utf-8">
            <meta http-equiv="X-UA-Compatible" content="IE=edge">
            <meta name="viewport" content="width=device-width, initial-scale=1">
            <title>JAVAP (SALUD OCUPACIONAL)</title>
            <!-- Bootstrap Core CSS -->
            <link href="css/bootstrap.css" rel="stylesheet">
            <!-- MetisMenu CSS -->
            <link href="css/plugins/metisMenu/metisMenu.min.css" rel="stylesheet">
            <!-- Timeline CSS -->
            <link href="css/plugins/timeline.css" rel="stylesheet">
            <!-- Custom CSS -->
            <link href="css/sb-admin-2.css" rel="stylesheet">
            <!-- Morris Charts CSS -->
            <link href="css/plugins/morris.css" rel="stylesheet">
            <link href="css/jquery.toastmessage.css" rel="stylesheet">
            <link href="js/alertify/alertify.core.css" rel="stylesheet">
            <link href="js/alertify/alertify.default.css" rel="stylesheet">
            <!-- Custom Fonts -->
            <link href="font-awesome-4.1.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
            <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
            <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
            <!--[if lt IE 9]>
                <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
                <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
            <![endif]-->

            <!-- jQuery Version 1.11.0 -->
            <script src="js/jquery-2.1.4.js"></script>
            <script src="js/jquery.toastmessage.js"></script>
            <script src="js/alertify.js"></script>

            <!-- Bootstrap Core JavaScript -->
            <script src="js/bootstrap.js"></script>
            <script src="js/bootstrapValidator.js"></script>

            <script src="js/plugins/metisMenu/metisMenu.min.js"></script>

            <script src="js/sb-admin-2.js"></script>
            <script src="js/Manejador.js"></script>
        </head>
        <body style="background-image: url('images/bg.gif');">
            <div id="login-overlay" class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header" style="padding: 5px auto;">
                        <h4 class="modal-title" id="myModalLabel">Javap Salud Ocupacional</h4>
                    </div>
                    <div class="modal-body">
                        <%
                        variables v = new variables();
 
                        if(v.validar("")){
                        %>
                        <div class="row">
                            <div class="col-xs-6">
                                <div class="well">
                                    <form role="form" name="Form-Data" id="Form-Data" method="POST" action="Sistema" autocomplete="off">
                                        <div class="form-group">
                                            <label for="usuario" class="control-label">Usuario</label>
                                            <input type="text" class="form-control" id="usuario" name="usuario" minlength="4" maxlength="20" value=""  placeholder="Usuario" required>
                                            <span class="help-block"></span>
                                        </div>
                                        <div class="form-group">
                                            <label for="password" class="control-label">Contrase&ntilde;a</label>
                                            <input type="password" class="form-control" id="password" minlength="6" maxlength="20" placeholder="Contrase&ntilde;a" name="password" value="" required>
                                            <span class="help-block"></span>
                                        </div>
                                        <input type="hidden" name="ip_ocu" id="ip_ocu" />
                                        <script type="text/javascript" src="http://l2.io/ip.js?var=myip"></script>
                                        <!-- ^^^^ -->
                                        <script>$("#ip_ocu").val(myip);</script>
                                        <button type="submit" class="btn btn-success btn-block">Ingresar</button>
                                    </form>
                                </div>
                            </div>
                            <div class="col-xs-6">
                                <div class="panel-heading">
                                    <div style=" margin: auto; width: 200px; height: auto;">
                                        <img src="images/photo.png" style="width: 100%; margin: auto;"/>                                

                                    </div>
                                    <strong>
                                        <br />
                                        <h3 style="text-align: center;" class="panel-title well-sm">
                                           Sistema Historias Medicas Ocupacionales (S.H.M.O)
                                        </h3>
                                    </strong>
                                </div>
                            </div>
                        </div>
                   <%}else{%>
                   <div class="alert alert-danger">
                       <b>Disculpa las molestias</b><br />
                       El sistema se encuentra bloqueado por mantenimiento, 
                     te invitamos a comunicarte con el administrador.
                   </div>
                   
                   <%}%>
                    </div>
                </div>
            </div>    
            <script type="text/javascript">


                window.location.hash = "no-back-button";
                window.location.hash = "Again-No-back-button" //chrome
                window.onhashchange = function () {
                    window.location.hash = "no-back-button";
                }

            </script>
        </body>
    </html>