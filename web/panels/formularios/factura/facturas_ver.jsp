<%-- 
    Document   : facturas_ver
    Created on : 8/09/2016, 01:23:52 AM
    Author     : D4V3
--%>

<%@page import="valeria.metodos.Cadenas"%>
<%@page import="ocupacional.JPA.valueobjects.Itemfactura"%>
<%@page import="ocupacional.JPA.controlers.FacturasJpaController"%>
<%@page import="ocupacional.JPA.valueobjects.Facturas"%>
<%@page import="javax.persistence.Persistence"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<%@page import="valeria.metodos.ManejadorFechas"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
String anot= request.getParameter("fac_id");
    ManejadorFechas  f = new ManejadorFechas();
    Cadenas o = new Cadenas();

EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaP");
Facturas a  = new FacturasJpaController(emf).findFacturas(Integer.parseInt(anot));

%>
    <form   id="Form-Data" role="form" name="Form-Data"  method="POST" action="../Cuentas" autocomplete="off">
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal">&times;</button>
    <h3 class="modal-title " style="color: darkred">Factura # <%=a.getFactConsecutivo()%> <small style="color: darkgray">(<%=f.FechaLetrasHora(a.getFactfechaCreacion()) %>)</small></h3>
        
        </div>
        
    <div  class="modal-body">
        <div class="row" >
            <ul class="list-group col-md-6">
                <li class="list-group-item"><strong>Documento:</strong> <%=o.notEmpty(a.getCecoId().getClieId().getClieDocumento()) %></li>
                <li class="list-group-item"><strong>Cliente:</strong> <%=o.notEmpty(a.getCecoId().getClieId().getClieNombre()) %></li>
                <li class="list-group-item"><strong>Centro de costo:</strong> <%=o.notEmpty(a.getCecoId().getCecoObservacion()) %></li>
            </ul>
            <ul class="list-group col-md-6">
                <li class="list-group-item"><strong>Direccion:</strong> <%=o.notEmpty(a.getCecoId().getClieId().getClieDireccion()) %></li>
                <li class="list-group-item"><strong>Telefonos:</strong> <%=o.notEmpty(a.getCecoId().getClieId().getClieTelefonos()) %></li>
                <li class="list-group-item"><strong>Email:</strong> <%=o.notEmpty(a.getCecoId().getClieId().getClieEmail()) %></li>
            </ul>
            

            <table  class=" table table-striped table-hover center" > 
                <thead>
                    <tr>
                        <th>CANTIDAD</th>
                        <th>DESCRIPCION</th>
                        <th>VALOR UNITARIO</th>
                        <th>VALOR TOTAL</th>
                       
                    </tr>
                </thead>
                <tbody>
                    <% 
                    
                    for (Itemfactura d : a.getItemfacturaList()){
                    %>
                    <tr>
                      <td><%=d.getItfaCantidad()%></td>  
                      <td><%=d.getIdfaNombre()%></td>  
                      <td>$<%=o.getNumber(d.getIdfaValor())%></td>  
                      <td>$<%=o.getNumber(d.getIdfaValor()*d.getItfaCantidad())%></td>  
                    </tr>
                    <%}%>
                    
                </tbody>
                <tfoot>
                    
                    <tr>
                        <td colspan="3" style=" text-align:  right; font-weight: bold">TOTAL:</td>
                        <td> <strong>$<%=o.getNumber(a.getFactTotal()) %></strong></td>
                    </tr>
                    
                </tfoot>
            </table>
                    <%
                    if(a.getFactConsecutivo()==0){
                    %>
                 <div class="form-group col-md-4">
                        <label class="control-label" for="numeroFac_txt" style="" >
                            Numero de factura
                        </label>
                     <input  id="numeroFac_txt" name="numeroFac_txt"  required="" class="form-control " type="number"   value="" >
                        <span class="help-block"></span>
                    </div>
<%}%>
        </div>
    </div>
    <div class="modal-footer">
        <input type="hidden" name="action" value="GenerarFacVer" />
        <input type="hidden" name="fac_ocu" value="<%=a.getFactId()%>" />
        <button type="button" class="left btn btn-default" data-dismiss="modal">Cerrar</button>
        <button type="button" onclick="
            if(validarRequiered($('#Form-Data').serialize())){
     $('#myModal').modal('toggle');
            window.open('../Cuentas?'+$('#Form-Data').serialize(), '_blank', 'toolbar=no,scrollbars=yes,resizable=yes,top=50,left=100,width=800,height=600')
    }       
                " class="left btn btn-primary" >Imprimir <i class="glyphicon glyphicon-print"></i></button>

    </div>
        <script type="text/javascript">
      ValidarFormID();
        </script>
    </form>

