<%-- 
        DESAROLLADOR : VALERIA
        NOMBRE DEL DOCUMENTO   : ctr
        FECHA DE CREACION : 4/12/2015, 06:49:09 AM
        HISTORIA DE USUARIO :
        DESCRIPCION FUNCIONAL DEL DOCUMENTO : 
--%>

<%@page import="ocupacional.bdatos.UsuariosDAO"%>
<%@page import="ocupacional.valueobjects.UsuarioRolVO"%>
<%@page import="valeria.response.Mediador"%>
<%!
    String _url = "";
    boolean _recarga = false;
%>
<%
    Mediador e = (Mediador) session.getAttribute("Mediador");
    if (e != null) {
        if (e.getUsuarioVO() != null) {
            e.o.setRequest(request);
            String modulo = e.o.getvariable("modulo");
            if (e.o.Compara(modulo, "nuevo")) {
                this._url = "formulario.jsp?modulo=nuevo";
            }
            ServletContext context = getServletContext();
            RequestDispatcher rd = context.getRequestDispatcher("/" + this._url);
            rd.forward(request, response);
        }
    }
%>

<%!
    public void NuevoUsuario() {

    }
%>