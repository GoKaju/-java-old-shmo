<%-- 
        DESAROLLADOR : VALERIA
        NOMBRE DEL DOCUMENTO   : formulario
        FECHA DE CREACION : 12/12/2015, 08:47:35 AM
        HISTORIA DE USUARIO :
        DESCRIPCION FUNCIONAL DEL DOCUMENTO : 
--%>

<%@page import="ocupacional.bdatos.UsuariosDAO"%>
<%@page import="ocupacional.valueobjects.UsuarioRolVO"%>
<%@page import="valeria.response.Mediador"%>
<%
    Mediador e = (Mediador) session.getAttribute("Mediador");
    if (e != null) {
        if (e.getUsuarioVO() != null) {
            e.o.setRequest(request);
            String modulo = e.o.getvariable("modulo");
            if (e.o.Compara(modulo, "nuevo")) {
%>
<form>
    <input type="text" name="">
</form>
<%
            }
        }
    }
%>