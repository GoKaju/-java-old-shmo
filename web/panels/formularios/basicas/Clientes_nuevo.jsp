<%-- 
    Document   : Clientes_nuevo
    Created on : 3/07/2016, 02:31:26 PM
    Author     : D4V3
--%>
<%@page import="ocupacional.JPA.valueobjects.ClientesServicio"%>
<%@page import="ocupacional.bdatos.facturacion.ServiciosDAO"%>
<%@page import="ocupacional.valueobjects.facturacion.ServiciosVO"%>
<%@page import="ocupacional.valueobjects.facturacion.ClientesServiciosVO"%>
<%@page import="ocupacional.JPA.valueobjects.Ciudades"%>
<%@page import="ocupacional.JPA.controlers.CiudadesJpaController"%>
<%@page import="ocupacional.JPA.controlers.TipodocumentoJpaController"%>
<%@page import="ocupacional.JPA.valueobjects.Tipodocumento"%>
<%@page import="java.util.List"%>
<%@page import="javax.persistence.TypedQuery"%>
<%@page import="javax.persistence.EntityManager"%>
<%@page import="ocupacional.JPA.controlers.ClientesJpaController"%>
<%@page import="ocupacional.JPA.valueobjects.Clientes"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<%@page import="javax.persistence.Persistence"%>
<%@page import="ocupacional.valueobjects.RolFuncionalidadVOs"%>
<%@page import="valeria.metodos.Cadenas"%>

<%@page import="java.util.ArrayList"%>
<%@page import="valeria.response.Mediador"%>
<%    Mediador e = (Mediador) session.getAttribute("Mediador");
    if (e != null) {
        Cadenas pc = new Cadenas();
        pc.setRequest(request);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaP");
        ClientesJpaController clieDAO = new ClientesJpaController(emf);
        TipodocumentoJpaController tidoDAO = new TipodocumentoJpaController(emf);
        Clientes clie = new Clientes();
        String tipo = pc.getvariable("tipocliente");
        String tido = pc.getvariable("tido");
        String doc = pc.getvariable("doc");
        String id_ocu = pc.getvariable("id_ocu");
        System.out.println("consulta>1");
        if (!id_ocu.equals("")) {
            clie = clieDAO.findClientes(Integer.parseInt(id_ocu));
            tipo = clie.getClieTipo();

            session.removeAttribute("listaClienteServicio");
            session.setAttribute("listaClienteServicio", clie.getClientesServicioList());
        }
        System.out.println("consulta>2");

        if (!tipo.equals("") && !tido.equals("") && !doc.equals("")) {

            clie.setTidoId(tidoDAO.findTipodocumento(Integer.parseInt(tido)));
            clie.setClieDocumento(doc);

            EntityManager em = emf.createEntityManager();
            TypedQuery<Clientes> consultaDocumento = em.createNamedQuery("Clientes.findByClieDocumento", Clientes.class);

            consultaDocumento.setParameter("clieDocumento", doc);
            System.out.println("consulta>" + consultaDocumento.toString());
            List<Clientes> lista = consultaDocumento.getResultList();

            for (Clientes p : lista) {
                if (p.getTidoId().equals(tidoDAO.findTipodocumento(Integer.parseInt(tido)))) {
                    clie = p;
                    tipo = p.getClieTipo();
                }
            }
        }

        System.out.println("consulta>3");

        
%>

<div class="panel panel-default">

    <div class="panel-heading">
        <div class="row">
            <div class="col-lg-9">
                <h2 class="text-success">Nuevo Cliente</h2>
            </div>
        </div>
    </div>
    <form   id="Form-Data" role="form" name="Form-Data"  method="POST" action="../Clientes" autocomplete="off">
        <div class="panel-body">
            <div class="row">





                <fieldset  style="padding: 10px">
                    <legend>Datos Basicos</legend>

                    <div class="row"> 
                        <div class="form-group col-md-4">

                            <label class="control-label" for="paci_tipodocumento">tipo doc.*</label>
                            <input  id="paci_tipodocumento" name="" readonly placeholder=""  class="form-control " type="text" required  value="<%if (clie.getTidoId() != null) {%><%=pc.notEmpty(clie.getTidoId().getTidoDescripcion())%>" >
                            <input type="hidden" name="tipo_documento" value="<%=pc.notEmpty(clie.getTidoId().getTidoId().toString())%>">
                            <span class="help-block"></span>
                        </div> 
                        <% }%>
                        <div class="form-group col-md-4">

                            <label class="control-label" for="documento">No. doc.*</label>
                            <input  id="paci_documento" name="documento" placeholder=""  readonly="" pattern="[0-9]{4}" class="form-control " type="text" required  value="<%=pc.notEmpty(clie.getClieDocumento())%>" >
                            <span class="help-block"></span>
                        </div>
                        <div class="form-group col-md-4">

                            <label class="control-label" for="nombre">
                                <%
                                    System.out.println("consulta>3.5");
                                    if (tipo.equals("J")) {
                                %>
                                Razon social*
                                <%} else {%>
                                Nombre*
                                <%}
                                    System.out.println("consulta>4");

                                %>

                            </label>
                            <input  id="nombre" name="nombre" placeholder=""    class="form-control " type="text" required  value="<%=pc.notEmpty(clie.getClieNombre())%>" >
                            <span class="help-block"></span>
                        </div>
                        <div class="form-group col-md-4">

                            <label class="control-label" for="ciudad">
                                Ciudad*

                            </label>
                            <select name="ciudad" id="ciudad" class="form-control " required="" >
                                <option value="">Seleccione...</option>
                                <%
                                    CiudadesJpaController ciudDAO = new CiudadesJpaController(emf);
                                    for (Ciudades c : ciudDAO.findCiudadesEntities()) {
                                %>
                                <option value="<%=c.getCiudId()%>"><%=c.getCiudNombre()%></option>

                                <%}%>
                            </select>
                            <script type="text/javascript">
                                $("#ciudad").val(<% if (clie.getCiudId() != null) {%>
                                <%=clie.getCiudId().getCiudId()%>
                                <% }%>
                                );</script>
                            <span class="help-block"></span>
                        </div>
                        <div class="form-group col-md-4">

                            <label class="control-label" for="direccion">
                                Direccion*

                            </label>
                            <input  id="direccion" name="direccion" placeholder=""    class="form-control " type="text" required  value="<%=pc.notEmpty(clie.getClieDireccion())%>" >
                            <span class="help-block"></span>
                        </div>
                        <div class="form-group col-md-4">

                            <label class="control-label" for="telefonos">
                                Telefonos*

                            </label>
                            <input  id="telefonos" name="telefonos" placeholder=""    class="form-control " type="tel" required  value="<%=pc.notEmpty(clie.getClieTelefonos())%>" >
                            <span class="help-block"></span>
                        </div>
                        <div class="form-group col-md-4">

                            <label class="control-label" for="email">
                                Email

                            </label>
                            <input  id="email" name="email" placeholder=""    class="form-control " type="email"   value="<%=pc.notEmpty(clie.getClieEmail())%>" >
                            <span class="help-block"></span>
                        </div>

                        <%
                            if (tipo.equals("J")) {
                        %>
                        <div class="form-group col-md-8">

                            <label class="control-label" for="repLegal">
                                Representante legal*

                            </label>
                            <input  id="repLegal" name="repLegal" placeholder=""    class="form-control " type="text" required  value="<%=pc.notEmpty(clie.getClierepLegal())%>" >
                            <span class="help-block"></span>
                        </div>
                        <%}
                            System.out.println("consulta>5");

                        %>
                        <div class="form-group col-md-12">

                            <label class="control-label" for="observaciones">
                                Observaciones

                            </label>
                            <textarea class="col-md-12 form-control" name="observaciones" id="observaciones" ><%=pc.notEmpty(clie.getClieObservacion())%></textarea>

                            <span class="help-block"></span>
                        </div>

                    </div>   
                </fieldset>

                <fieldset >
                    <legend>  <a href="#Servicios" class="" data-toggle="collapse">Servicios Permitidos</a></legend>
                    <div class="row col-xs-12 " id="Servicios" >


                        <%

                            List<ClientesServicio> listaClienteServicio = (List<ClientesServicio>) session.getAttribute("listaClienteServicio");

                            if (listaClienteServicio == null) {
                                System.out.println("codigo 6");
                                listaClienteServicio = (List<ClientesServicio>) new ArrayList<ClientesServicio>();
                                session.setAttribute("listaClienteServicio", listaClienteServicio);

                            }
                            System.out.println("codigo 7" + listaClienteServicio.size());
                            if (listaClienteServicio != null) {

                        %>
                        <div id="cajaFormServicios">
                            <table id="tablaFormServicios" class="table table-hover">
                                <thead>
                                    <tr>
                                        <th>Servicio</th>
                                        <th>Precio(COP)</th>
                                        <th></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%                                        int cont = 0;
                                        for (ClientesServicio c : listaClienteServicio) {
                                            if (c.getClseEstado().equals("ACTIVO")) {
                                    %>
                                    <tr>
                                        <td><%=c.getServId().getServNombre()%></td>                                            
                                        <td>$<%=pc.getNumber(c.getClseValor())%></td>                                            
                                        <td><button type="button" title="Remover" onclick="peticionAjax('../Clientes', '<%if (!id_ocu.equals("")) {%>id_ocu=<%=pc.notEmpty(id_ocu)%>&<%}%>action=RemoverServicio&hash=<%=cont%>')" class="btn-circle btn-danger bottom-right btn-outline"><i class="glyphicon glyphicon-remove"></i> </button></td>

                                    </tr>
                                    <% }
                                            cont++;
                                        }%>
                                </tbody>
                            </table>

                            <% }%>

                        </div>


                        <div class="form-group col-md-4">
                            <label class="control-label" for="Servicio_sel">Servicio</label>  
                            <select id="Servicio_sel"  name="Servicio_sel" onchange="$('#agrServicio').removeAttr('disabled')" class="form-control"   >
                                <option value="">Seleccione..</option>
                                <%  for (ServiciosVO s : new ServiciosDAO(e).Listar()) {
                                        if (!s.getServ_estado().equals("ELIMINADO")) {
                                %>
                                <option value="<%=s.getServ_id()%>"><%=s.getServ_nombre()%>
                                </option>
                                <% }
                                    }%>
                            </select>
                            <span class="help-block"></span>
                        </div>
                        <div class="form-group col-md-4">
                            <label class="control-label" for="precio_text">Precio</label>  
                            <input id="precio_text" max="999999999" maxlength="10" oninput="$('#agrServicio').removeAttr('disabled')" name="precio_text" placeholder="" class="form-control" type="number" >

                            <span class="help-block"></span>
                        </div>
                        <div class="alignRight col-md-4">
                            <br/>
                            <button type="button" id="agrServicio"  class="btn btn-primary right" onclick="peticionAjax('../Clientes', '<%if (!id_ocu.equals("")) {%>id_ocu=<%=pc.notEmpty(id_ocu)%>&<%}%>action=AgregarServicio&Servicio_sel=' + $('#Servicio_sel').val() + '&precio_text=' + $('#precio_text').val()), this.disabled = true">Agregar</button>

                        </div>
                    </div>
                </fieldset>

                <!-- /.row (nested) -->
            </div>
        </div>
        <!-- /.panel-body -->
        <div class="panel-footer">
            <div class="row">


                <div class="col-lg-9 right">

                </div>
                <div class="col-lg-3 right">
                    <div class="form-group">
                        <button  onclick="RecargaPanel('../panels/formularios/basicas/Clientes.jsp?idf=<%=  (String) session.getAttribute("idf")%>', 'panelprincipal')" type="button" class="btn btn-default bottom-right btn-outline">Atras </button>

                        <input type="hidden" name="id" value="<% if (clie.getClieId() != null) {%><%=clie.getClieId()%><% }%>">
                        <input type="hidden" name="tipo" value="<%=tipo%>">
                        <input type="hidden" name="action" value="newCliente">
                        <button  onclick="" type="submit" class="btn btn-success bottom-right btn-outline">Guardar</button>

                    </div>
                </div>
            </div>


        </div>
    </form>
</div>


<script>
    $(document).ready(function () {
        ValidarFormID();
//        $('.datepicker').datepicker();


    });


</script>
<% } else {%>
<script type="text/javascript">
    location.href = '../logout.jsp';
</script>
<% }%>