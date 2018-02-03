<%-- 
    Document   : cam_pass
    Created on : 18/08/2016, 08:43:45 AM
    Author     : D4V3
--%>


<%@page import="ocupacional.JPA.valueobjects.Usuarios"%>
<%@page import="valeria.session.UsuarioVO"%>
<%@page import="valeria.metodos.Encriptacion"%>
<%@page import="valeria.response.Mediador"%>
<%@page import="valeria.conexion.ConexionAplicacion"%>
<%
    Mediador e = (Mediador) session.getAttribute("Mediador");
%>
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
            <!--<link href="css/plugins/metisMenu/metisMenu.min.css" rel="stylesheet">-->
            <!-- Timeline CSS -->
            <!--<link href="css/plugins/timeline.css" rel="stylesheet">-->
            <!-- Custom CSS -->
            <!--<link href="css/sb-admin-2.css" rel="stylesheet">-->
            <!-- Morris Charts CSS -->
            <!--<link href="css/plugins/morris.css" rel="stylesheet">-->
            <!--<link href="css/jquery.toastmessage.css" rel="stylesheet">-->
            <link href="js/alertify/alertify.core.css" rel="stylesheet">
            <link href="js/alertify/alertify.default.css" rel="stylesheet">
            <!-- Custom Fonts -->
            <!--            <link href="font-awesome-4.1.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">-->
            <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
            <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
            <!--[if lt IE 9]>
                <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
                <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
            <![endif]-->

            <!-- jQuery Version 1.11.0 -->
            <script src="js/jquery-2.1.4.js"></script>
            <!--<script src="js/jquery.toastmessage.js"></script>-->
            <script src="js/alertify.js"></script>

            <!-- Bootstrap Core JavaScript -->
            <script src="js/bootstrap.js"></script>
            <script src="js/bootstrapValidator.js"></script>

            <!--<script src="js/plugins/metisMenu/metisMenu.min.js"></script>-->

            <script src="js/sb-admin-2.js"></script>
            <script src="js/Manejador.js"></script>
        </head>
        <body style="background-image: url('images/bg.gif');">
            <div id="login-overlay" class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header" style="padding: 5px auto;">
                        <h4 class="modal-title" id="myModalLabel">Javap Salud Ocupacional - Cambio de contraseña</h4>
                    </div>
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-md-6">
                                <div class="well">
                                    <%
                                        Usuarios user = (Usuarios) session.getAttribute("usercampass");
                                        if (user != null) {
                                    %>
                                    <form role="form" name="Form-Data" id="Form-Data" method="POST" action="Pass" autocomplete="off">
                                        <div class="form-group">
                                            <label for="usuario" class="control-label">Usuario</label>
                                            <input type="text" class="form-control" id="usuario" name="usuario" minlength="4" maxlength="20" value="<%=user.getUsuaUsuario()%>"  readonly required>
                                            <span class="help-block"></span>
                                        </div>
                                        <div class="form-group">
                                            <label for="password" class="control-label">Contrase&ntilde;a actual</label>
                                            <input type="password" class="form-control" id="password" minlength="6" maxlength="20" readonly name="password_ant" value="<%=new Encriptacion().decrypt(user.getUsuaContrasena())%>" required>
                                            <span class="help-block"></span>
                                        </div>
                                        <div class="form-group">
                                            <label for="password" class="control-label">Contrase&ntilde;a nueva</label>
                                            <input type="password" class="form-control" id="password" minlength="6" maxlength="20" placeholder="Contrase&ntilde;a nueva" name="password_new" value="" required>
                                            <span class="help-block"></span>
                                        </div>
                                        <div class="form-group">
                                            <label for="password" class="control-label">Confirmar contrase&ntilde;a </label>
                                            <input type="password" class="form-control" id="password" minlength="6" maxlength="20" placeholder="Contrase&ntilde;a confirme" name="password_new_confirm" value="" required>
                                            <span class="help-block"></span>
                                        </div>

                                        <button type="submit" class="btn btn-success btn-block">Continuar</button>
                                    </form>
                                    <%
                                    } else {%>
                                    Está ingresando de manera no autorizada...
                                    <%}%>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="panel-heading">
                                    <div style=" margin: auto; width: 200px; height: auto;">
                                        <img src="images/photo.png" style="width: 100%; margin: auto;"/>                                

                                    </div>
                                        <br />
                                        <h3 style="text-align: justify;" class="panel-title well">
                                    <strong>
                                            NOTA: <br />
                                            La contraseña que le fue suministrada es de tipo temporal, debe cambiarla para activar su usuario. <br />
                                            Recuerde que en el apartado datos usuario puede modificarla las veces que sea necesario.
                                    </strong>
                                        </h3>
                                </div>
                            </div>

                </div>
                </div>
            </div>    
            </div>    
            <script type="text/javascript">


            </script>
        </body>
    </html>
