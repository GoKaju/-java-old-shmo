<%-- 
        DESAROLLADOR : Alejandro 
        NOMBRE DEL DOCUMENTO   : log_ctr
        FECHA DE CREACION : 02-nov-2015, 20:28:05  
        HISTORIA DE USUARIO :
        DESCRIPCION FUNCIONAL DEL DOCUMENTO : 
--%>

<%@page import="valeria.response.ObjetoRespuestaVO"%>
<%@page import="valeria.response.Mediador"%>
<%!
    ObjetoRespuestaVO ObjetoRespuestaVO = new ObjetoRespuestaVO();
    Mediador e = null;
    ServletRequest _request = null;
    ServletResponse _response = null;
%>
<%
    this.e = (Mediador) session.getAttribute("Mediador");
    if (this.e != null) {
        if (this.e.getUsuarioVO() != null) {
            session.invalidate();
            this.e = new Mediador(this.ObjetoRespuestaVO);
        }
    } else {
        this.e = new Mediador(this.ObjetoRespuestaVO);
    }
    session.isNew();
    this._request = request;
    this._response = response;
    session.setAttribute("Mediador", this.e);
    ServletContext context = getServletContext();
    RequestDispatcher rd = context.getRequestDispatcher("/login.jsp");
    rd.forward(request, response);
%>