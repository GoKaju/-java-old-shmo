

<%@page import="valeria.metodos.ManejadorFechas"%>
<%@page import="ocupacional.JPA.valueobjects.Sede"%>
<%@page import="ocupacional.JPA.controlers.SedeJpaController"%>
<%@page import="javax.persistence.EntityManager"%>
<%@page import="javax.persistence.Persistence"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<%@page import="ocupacional.JPA.valueobjects.Ticket"%>
<%@page import="javax.persistence.TypedQuery"%>
<%@page import="ocupacional.JPA.valueobjects.Roles"%>
<%-- 
        DESAROLLADOR : Alejandro 
        NOMBRE DEL DOCUMENTO   : Inicio
        FECHA DE CREACION : 05-nov-2015, 12:53:04  
        HISTORIA DE USUARIO :
        DESCRIPCION FUNCIONAL DEL DOCUMENTO : 
--%>
<%@page import="ocupacional.valueobjects.ActividadEconomicaVO"%>
<%@page import="ocupacional.bdatos.ActividadEconomicaDAO"%>
<%@page import="ocupacional.valueobjects.UsuarioRolVO"%>
<%@page import="valeria.response.Mediador"%>
<%
    Mediador e = (Mediador) session.getAttribute("Mediador");
    System.out.println("mediador " + e);
    try {
        if (e != null) {
            System.out.println("mediador no nulo " + e.getRuta());
            if (e.getUsuarioVO() != null) {
                Roles UsuarioRolVOv = null;
                if (e.getUsuarioVO().getRol() != null) {
                    UsuarioRolVOv = (Roles) e.getUsuarioVO().getRol();
                }
                ManejadorFechas f = new ManejadorFechas();
                EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaP");

%>
<jsp:include page="includes/header.jsp"/>


<div id="page-wrapper" >
    <div id="Contenedor" >
        <!-- /.row -->
        <div class="row">
            <div class="col-lg-12" id="panelprincipal" >
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h2 class="text-success">Inicio</h2>
                    </div>
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-lg-12">
                                Bienvenido al sistema de gestion de examenes medicos ocupacionales JAVAP.
                                Por favor seleccione un ROL..

                            </div>
                            <br />
                            <br />
                            <!-- /.col-lg-6 (nested) -->
                            <%if (e.getUsuarioVO().getUsua_tipo().equals("E")) {
                           EntityManager em = emf.createEntityManager();
                                Sede sede = new SedeJpaController(emf).findSede(Integer.parseInt(e.getUsuarioVO().getSede_id()));
                                TypedQuery<Ticket> cp1 = em.createNamedQuery("Ticket.findBySedeyEstado", Ticket.class);
                                        cp1.setParameter("sede",sede );
                                        cp1.setParameter("estado","POR PROCESAR");
                                  int p1 = cp1.getResultList().size();
                                TypedQuery<Ticket> cp2 = em.createNamedQuery("Ticket.findBySedeyEstado", Ticket.class);
                                        cp2.setParameter("sede",sede );
                                        cp2.setParameter("estado","PROCESANDO");
                                  int p2 = cp2.getResultList().size();
                                String hoy = f.DevuelveFormato(f.getFechaHoraTimeStamp());
                                  
                                  TypedQuery<Ticket> cp3 = em.createNamedQuery("Ticket.findBySedeyEstadoFechas", Ticket.class);
                                        cp3.setParameter("sede",sede );
                                        cp3.setParameter("estado","PROCESADO");
                                        cp3.setParameter("inicio",f.StringToTimeStampHora(hoy+" 00:00:00"));
                                        cp3.setParameter("fin",f.StringToTimeStampHora(hoy+" 23:59:59"));
                                  int p3 = cp3.getResultList().size();
//                                  int p3 = 0;
                                
                            
                            
                            
                            %>
                            <div class="col-md-12">
                                <div class="col-md-4">
                                    <div class="panel panel-warning">
                                        <div class="panel-heading">
                                            <div class="row">
                                                <div class="col-xs-3">
                                                    <i class="fa fa-shopping-cart fa-5x"></i>
                                                </div>
                                                <div class="col-xs-9 text-center">
                                                        <div class="huge"><%=p1%></div>
                                                    <div></div>
                                                </div>
                                            </div>
                                        </div>
                                        <a href="#">
                                            <div class="panel-footer">
                                                <span class="pull-left">POR PROCESAR</span>
                                                <!--<span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>-->
                                                <div class="clearfix"></div>
                                            </div>
                                        </a>
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <div class="panel panel-info">
                                        <div class="panel-heading">
                                            <div class="row">
                                                <div class="col-xs-3">
                                                    <i class="fa fa-gears fa-5x "></i>
                                                </div>
                                                <div class="col-xs-9 text-center">
                                                    <div class="huge"><%=p2%></div>
                                                    <div></div>
                                                </div>
                                            </div>
                                        </div>
                                        <a href="#">
                                            <div class="panel-footer">
                                                <span class="pull-left">PROCESANDO</span>
                                                <!--<span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>-->
                                                <div class="clearfix"></div>
                                            </div>
                                        </a>
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <div class="panel panel-success">
                                        <div class="panel-heading">
                                            <div class="row">
                                                <div class="col-xs-3">
                                                    <i class="fa fa-check fa-5x"></i>
                                                </div>
                                                <div class="col-xs-9 text-center">
                                                    <div class="huge"><%=p3%></div>
                                                    <div></div>
                                                </div>
                                            </div>
                                        </div>
                                        <a href="#">
                                            <div class="panel-footer">
                                                <span class="pull-left">PROCESADO HOY</span>
                                                <!--<span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>-->
                                                <div class="clearfix"></div>
                                            </div>
                                        </a>
                                    </div>
                                </div>


                            </div>
                            <%}%>
                            <!-- /.col-lg-6 (nested) -->
                        </div>
                        <!-- /.row (nested) -->
                    </div>
                    <!-- /.panel-body -->
                </div>
                <!-- /.panel -->
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->

    </div>
</div>
<!-- /#page-wrapper -->

<jsp:include page="includes/footer.jsp"/>

<% if (UsuarioRolVOv == null) { %>
<script>VentanaModal("Debe Seleccionar un rol de la lista de roles");</script>
<%  }  %>

<%  } else {
                response.sendRedirect("../logout.jsp");
            }
        } else {
            response.sendRedirect("../logout.jsp");
        }
    } catch (Exception ex) {
        ex.printStackTrace();
    }
%>