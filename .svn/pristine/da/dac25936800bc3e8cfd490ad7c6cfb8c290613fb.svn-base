<%-- 
    Document   : inv_pro_car_arc
    Created on : 24/05/2016, 10:23:17 AM
    Author     : DJGOMEZ
--%>


<%@page import="formularios.controlers.AnotacionesJpaController"%>
<%@page import="formularios.entidades.Anotaciones"%>
<%@page import="formularios.entidades.Documentos"%>
<%@page import="formularios.controlers.DocumentosJpaController"%>
<%@page import="javax.persistence.Persistence"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<%@page import="valeria.metodos.Cadenas"%>
<%@page import="valeria.response.Mediador"%>
<%@page import="valeria.metodos.ManejadorFechas"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.io.File"%>
<%@page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
<%@page import="org.apache.commons.fileupload.FileItem"%>
<%@page import="java.util.List"%>
<%@page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>
<%@page import="org.apache.commons.io.*"%>
<%@page import="org.apache.commons.fileupload.FileItemFactory"%>
<%@page import="org.apache.commons.fileupload.FileUploadException"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    
    Mediador e = (Mediador) session.getAttribute("Mediador");
    Cadenas o = new Cadenas();
    ManejadorFechas  f = new ManejadorFechas();
    if (e != null) {
EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaP1PU");
DocumentosJpaController ddao = new DocumentosJpaController(emf);
Documentos d = new Documentos();
d.setDocFechacambio(f.getFechaHoraTimeStamp());
d.setDocuRegistradopor(e.getUsuarioVO().getIdUsuario());
d.setDocuRuta(f.getCadena());
    
     String hash= null;
    File file;
    /* hala el separador del sistema operativo*/
   String separador = System.getProperty("file.separator");
    /* se obtiene la ruta de la aplicacion mas el separador mas la carpeta dentro de la aplicacion ***tiene que estar creada*** mas el separador nuevamente  */
  String ruta = application.getRealPath("") + separador + "Adjuntos" + separador;
   int i=1;
    try {
        FileItemFactory factory = new DiskFileItemFactory();
        ServletContext servletContext = this.getServletConfig().getServletContext();
        /*se obtiene la ruta de los temporales del servlet o el el servidor*/
        File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
        ServletFileUpload upload = new ServletFileUpload(factory);
        /*se obtiene la lista de archivos que hay en el request recuerde que se debe encriptar como multipart/form-data */
        List <FileItem> items = upload.parseRequest(request);
        for (FileItem item : items) {
            /*aqui evaluamos si no es un campo de un formulario no es necesario*/
            if (!item.isFormField()) {
                // Get the uploaded file parameters
              d.setDocuNombre(item.getName());
              d.setDocuPeso(String.valueOf(item.getSize()/1024)+" KB");
                /*aqui se crea un archivo vacio con la ruta y el nombre q queramos y luego sobrescribimos ese archivo con el contenido de el enviado */
                file = new File(ruta +d.getDocuRuta());
                item.write(file);

            } else {
                String fieldName = item.getFieldName();
                if(fieldName.equals("anot_id")){
                d.setAnotId( new AnotacionesJpaController(emf).findAnotaciones(Integer.parseInt(item.getString())));

                }
            }
        }
        ddao.create(d);
                                     response.getWriter().write("1");

  
    } catch (Exception ex) {
        ex.printStackTrace();
      

    }
    } else {%>
<script type="text/javascript">
    location.href='../logout.jsp';
</script>
<%}%>