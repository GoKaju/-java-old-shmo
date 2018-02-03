<%-- 
    Document   : centroCostos
    Created on : 21-may-2016, 23:30:46
    Author     : Sebas
--%>

<%@page import="formularios.entidades.ClienteSedes"%>
<%@page import="ocupacional.JPA.valueobjects.Clientes"%>
<%@page import="ocupacional.JPA.controlers.ClientesJpaController"%>
<%@page import="javax.persistence.Persistence"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<%@page import="ocupacional.valueobjects.facturacion.ClientesVO"%>
<%@page import="ocupacional.valueobjects.facturacion.CentroCostosVO"%>
<%@page import="ocupacional.bdatos.facturacion.ClientesDAO"%>
<%@page import="ocupacional.bdatos.facturacion.CentroCostosDAO"%>
<%@page import="ocupacional.bdatos.RolFuncionalidadDAO"%>
<%@page import="ocupacional.valueobjects.RolFuncionalidadVOs"%>
<%@page import="valeria.metodos.ManejadorFechas"%>
<%@page import="valeria.metodos.Cadenas"%>
<%@page import="valeria.response.Mediador"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>




<%    Mediador e = (Mediador) session.getAttribute("Mediador");
    if (e != null) {

        Cadenas pc = new Cadenas();
        ManejadorFechas f = new ManejadorFechas();

        String idf = request.getParameter("idf");
        if (idf != null) {
            session.removeAttribute("idf");
            session.setAttribute("idf", idf);

        } else {
            idf = (String) session.getAttribute("idf");

        }
//                            ClientesDAO cdao = new ClientesDAO(e);

        RolFuncionalidadVOs rf = new RolFuncionalidadDAO(e).Cargar(idf);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaP");
        ClientesJpaController  cdao = new ClientesJpaController(emf);
        Clientes clie  =  new Clientes();
        if(request.getParameter("clie_id")!=null){
        clie = cdao.findClientes(Integer.parseInt(request.getParameter("clie_id")));
        
        }
        else if (e.getUsuarioVO().getUsua_tipo().equalsIgnoreCase("c"))
        {
        clie = cdao.findClientes(Integer.parseInt(e.getUsuarioVO().getIdpersona()));
        
        }
        
        
        if(clie.getClieId()!=null){
        


%>



<div class="panel panel-default">

    <div class="panel-heading">
        <div class="row">
            <div class="col-md-12">
                <h2 class="text-success">Centros de costo - <%=clie.getClieNombre()%></h2>
            </div>
            <div class="form-group col-md-4">

                <label class="control-label" for="descripcion_txt">Descripcion</label>
                <input  id="descripcion_txt" name="descripcion_txt" placeholder=""  class="form-control " type="text" required  value="<%%>" >
                <span class="help-block"></span>
            </div>
            <div class="  col-md-4">
                <button  style="margin-top: 25px" type="button"   class="btn btn-success bottom-right btn-outline" onclick="peticionAjax('../Sedesfincas', 'action=agregar&clie_id=<%=clie.getClieId()%>&descripcion=' + $('#descripcion_txt').val())">Agregar</button>

            </div>
        </div>
    </div>
    <div class="panel-body">
        <div class="row">
            <fieldset>
                <legend></legend>
                <!-- Table -->
                <table id="tabla" class="table table-hover ">
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>DESCRIPCIÓN</th>

                        </tr>
                    </thead>
                    <tbody>
                        <%//     

                            int count= 0;
                            
                            for (ClienteSedes c : clie.getClienteSedesList()) {
count++;
                                 %>
                        <tr>
                            <td><%= count %></td>
                            <td><%= pc.notEmpty(c.getClsedDescripcion())  %></td>
                            
                            <td>
                                <!--<button type="button" value="<%%>" data-toggle="modal" data-target=".modal-ver" class="btn-circle btn-success bottom-right btn-outline" onclick="RecargaForm('../panels/formularios/ticket/tickets.jsp?clie_ocu=' + this.value, 'cajaModalVer', 'modalContentVer')"><i class="glyphicon glyphicon-search"></i> </button>-->

                                <button type="button" class="btn-circle btn-danger bottom-right btn-outline" onclick="peticionAjax('../Sedesfincas','action=eliminar&clie_id=<%=clie.getClieId()%>&id=<%= c.getClsedId()%>')" ><i class="glyphicon glyphicon-remove"></i> </button></td>
                        </tr>
                        <%
                                
                            }
                        %>
                    </tbody>






                </table>




            </fieldset>



            <!-- /.col-lg-6 (nested) -->

        </div>   
        <!-- /.row (nested) -->
    </div>
    <!-- /.panel-body -->
    <div class="panel-footer">
        <div class="row">
            <div class="col-lg-9">
            </div>

            <div class="col-lg-3">
                <div class="form-group">
                                      <button  onclick="RecargaPanel('../panels/formularios/basicas/Clientes.jsp?idf=<%=(String) session.getAttribute("idf")%>', 'panelprincipal')" type="button" class="btn btn-default bottom-right btn-outline">Atras </button>

                    <!-- <button type="button" class="btn btn-default bottom-right btn-outline">Cancelar </button>
                     <button type="submit" class="btn btn-success bottom-right btn-outline">Registrar</button>
                    -->
                </div>
            </div>
        </div>


    </div>

</div>
<%}else{%>
Usted no es cliente...
<%}%>
<%}else{%>
<script type="text/javascript">
    location.href='../logout.jsp';
</script>
<%}%>