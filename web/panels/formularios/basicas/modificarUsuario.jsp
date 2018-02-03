<%-- 
    Document   : modificarUsuario
    Created on : 06-feb-2016, 23:20:44
    Author     : Sebas
--%>

<%@page import="java.awt.SystemTray"%>
<%@page import="java.util.ArrayList"%>
<%@page import="ocupacional.valueobjects.PersonaGeneralVO"%>
<%@page import="ocupacional.bdatos.PersonaGeneralDAO"%>
<%@page import="ocupacional.valueobjects.NaturalesVO"%>
<%@page import="ocupacional.valueobjects.JuridicasVO"%>
<%@page import="ocupacional.bdatos.JuridicasDAO"%>
<%@page import="ocupacional.bdatos.NaturalesDAO"%>
<%@page import="ocupacional.valueobjects.UsuarioVOO"%>
<%@page import="valeria.response.Mediador"%>
<%@page import="ocupacional.bdatos.UsuariosDAO"%>
<%@page import="ocupacional.valueobjects.RolesVO"%>
<%@page import="ocupacional.bdatos.RolesDAO"%>
<%@page import="ocupacional.valueobjects.TipoDocumentoVO"%>
<%@page import="ocupacional.bdatos.TipoDocumentoDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

        <%
            Mediador mediador = (Mediador) session.getAttribute("Mediador");

            UsuariosDAO udao = new UsuariosDAO(mediador);
            NaturalesDAO ndao = new NaturalesDAO(mediador);
            JuridicasDAO jdao = new JuridicasDAO(mediador);
            PersonaGeneralDAO pdao = new PersonaGeneralDAO();
            RolesDAO rd = new RolesDAO();

            UsuarioVOO uvoo = udao.cargar(request.getParameter("idUsuario"));
            JuridicasVO jvo = jdao.cargar(uvoo.getPege_id());
            NaturalesVO nvo = ndao.cargar(uvoo.getPege_id());
            PersonaGeneralVO pvo = pdao.cargar(uvoo.getPege_id());

            String tipo = pvo.getTido_id();
            String documento = pvo.getPege_documento();
            String nombres = null;

            if (jvo != null) {

                nombres = jvo.getJuri_razonsocial();

            } else if (nvo != null) {

                nombres = nvo.getNatu_primernombre() + " " + nvo.getNatu_segundonombre() + " " + nvo.getNatu_primerapellido() + " " + nvo.getNatu_segundoapellido();

            }


        %>





        <!-- Modal -->



        <div class="modal-body">

            <fieldset>
                <legend>Datos del usuario</legend>
                <div class="row">
                    <!--<form   id="Form-Data" role="form" name="Form-Data"  method="POST" action="" autocomplete="off">-->
                    <div class="col-lg-6"> <div class="form-group ">
                            <label class="col-md-4 control-label" for="tido_id">Tipo doc.</label>  
                            <div class="col-md-6">
                                <select disabled="" id="tipoDocumentoUsuario" name="tido_id" class="form-control input-sm" required>


                                    <%                                            TipoDocumentoDAO td = new TipoDocumentoDAO();

                                        td.listar();

                                        for (TipoDocumentoVO t : td.listar()) {

                                            if (t.getTipo_id().equals(pvo.getTido_id())) {


                                    %>

                                    <option value="<%= t.getTipo_id()%>" ><%= t.getTipo_descripcion()%></option>

                                    <%
                                            }
                                        }


                                    %>

                                </select>

                                <span class="help-block"></span>
                            </div>
                        </div>                    
                    </div>
                    <div class="col-lg-6">
                        <div class="form-group ">
                            <label class="col-md-4 control-label" for="pege_documento">No. doc.</label>  
                            <div class="col-md-6">
                                <input id="numeroDocumentoUsuario" name="pege_documento" disabled="" value="<%= documento%>"  class="form-control input-sm" type="text" required >

                                <span class="help-block"></span>
                            </div>
                        </div>                    
                    </div>
                    <div class="row">

                        <div class="col-lg-12">

                            <div class="form-group">
                                <label class="col-md-10 control-label" for="singlebutton"></label>
                                <div class="col-md-2">
                                    <button hidden="" id="singlebutton" name="singlebutton" class="btn btn-primary" onclick="verificaExistenciaUsuario()" >Buscar</button>
                                </div>
                            </div>
                        </div>

                    </div>
                    <br>
                    <div class="col-lg-12">
                        <div class="form-group ">
                            <label class="col-md-3 control-label" for="nombres"  >Nombres y apellidos</label> 

                            <div class="col-md-8">
                                <input id="nombresPersona" value="<%= nombres%>" name="nombres" disabled=""  class="form-control input-sm" type="text" required >

                                <span class="help-block"></span>
                            </div>
                        </div>                    
                    </div>

                    <!--</form>-->
            </fieldset>

            <fieldset>
                <legend></legend>
                <br>






            </fieldset>
            <form  id="formNuevoUsuario" role="form" name="Form-Data"  method="POST" action="../ControladorUsuarios" autocomplete="off">
                <fieldset>

                    <div class="col-lg-6"> <div class="form-group ">
                            <label class="col-md-4 control-label" for="usuario">Usuario</label> 

                            <input type="text" hidden="" value="" id="pege_idusuario" >

                            <div class="col-md-6">
                                <input id="nombreUsuario" name="nombreUsuario" value="<%= uvoo.getUsua_usuario()%>"  class="form-control input-sm" type="text" required >

                                <span class="help-block"></span>
                            </div>
                        </div>                    
                    </div>
                    <div class="col-lg-6"> <div class="form-group ">
                            <label class="col-md-4 control-label" for="contrasena">Contraseña</label> 

                            <div class="col-md-6">
                                <input id="contrasenaUsuario" name="contrasenaUsuario" value="<%= uvoo.getUsua_contrasena()%>" class="form-control input-sm" type="password" required >

                                <span class="help-block"></span>
                            </div>
                        </div>                    
                    </div>




                </fieldset>

                <fieldset>
                    <legend>Roles</legend>
                    <div class="row">

                        <%

                            ArrayList<RolesVO> rlista = rd.listar();
                            ArrayList<RolesVO> rulista = rd.listarRolesUsuario(uvoo.getUsua_id());

                            int numroles = rlista.size();
                            int escritos = 0;

                            String ids = "";

                            //System.out.println("Rol id " + r.getRole_descripcion());
                            for (RolesVO ru : rulista) {

                                escritos++;

                                //System.out.println("Rol de usuarios " + ru.getRole_descripcion());
                                ids += ru.getRole_id() + ",";


                        %>

                        <div class="col-lg-4"> 
                            <div class="form-group ">
                                <label class="col-md-4 control-label" for="usuario"><%= ru.getRole_descripcion()%></label> 

                                <div class=" checkbox col-md-6">
                                    <input id="rolUsuario" name="rolUsuario" checked="checked"   type="checkbox" required="" value="<%= ru.getRole_id()%>">

                                    <span class="help-block"></span>
                                </div>
                            </div>                 
                        </div>



                        <%

                            }

                            String[] array = ids.split(",");

                            for (RolesVO rol : rlista) {

                                for (String s : array) {

                                    System.out.println("rol: " + rol.getRole_id());

                                    System.out.println("s: " + s);

                                    System.out.println("ddd: " + rol.getRole_id().equals(s));

                                    if (rol.getRole_id().equals(s)) {

                                        break;

                                    } else {
                        %>




                        <div class="col-lg-4"> 
                            <div class="form-group ">
                                <label class="col-md-4 control-label" for="usuario"><%= rol.getRole_descripcion()%></label> 

                                <div class=" checkbox col-md-6">
                                    <input id="rolUsuario" name="rolUsuario"    type="checkbox" required="" value="<%= rol.getRole_id()%>">

                                    <span class="help-block"></span>
                                </div>
                            </div>                 
                        </div>



                        <%

                                        escritos++;

                                        break;
                                    }
                                }

                            }

                        %>

                        <%//                            int i = 0;
//                            int y = 0;
//
//                            for (RolesVO ru : rulista) {
//                                System.out.println("x" + (++i));
//
//                                for (RolesVO r : rlista) {
//                                    System.out.println("y" + (++y));
//                                    //System.out.println("Rol de usuarios " + ru.getRole_descripcion());
//                                    if (r.getRole_id().equals(ru.getRole_id())) {
//
//                                    } else {
//
//                                        System.out.println("entre al else");
                        %>

                        <!--                        <div class="col-lg-4"> 
                                                    <div class="form-group ">
                                                        <label class="col-md-4 control-label" for="usuario"></label> 
                        
                                                        <div class=" checkbox col-md-6">
                                                            <input id="rolUsuario" name="rolUsuario"   type="checkbox" required="" value="">
                        
                                                            <span class="help-block"></span>
                                                        </div>
                                                    </div>                 
                                                </div>-->



                        <%//                                    }
//
//                                }
//
//                            }
                        %>


                        <!--                        <div class="col-lg-4"> 
                                                    <div class="form-group ">
                                                        <label class="col-md-4 control-label" for="usuario"></label> 
                        
                                                        <div class=" checkbox col-md-6">
                                                            <input id="rolUsuario" name="rolUsuario"    type="checkbox" required="" value="">
                        
                                                            <span class="help-block"></span>
                                                        </div>
                                                    </div>                 
                                                </div>-->





                </fieldset>



            </form>

            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                <button type="submit"   onclick="" class="btn btn-primary">Registrar</button>
            </div> 
        </div>

        <!-- modal  fin -->

    </body>
</html>
