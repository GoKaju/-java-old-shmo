<%-- 
    Document   : ph
    Created on : 30/04/2016, 01:42:34 PM
    Author     : D4V3
--%>

<%@page import="valeria.response.ObjetoRespuestaVO"%>
<%@page import="valeria.response.Mediador"%>
<%@page import="valeria.conexion.ConexionAplicacion"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%!    Mediador e; %>

<%
this.e= (Mediador) session.getAttribute("Mediador");
this.crearConexion();

this.actualizar("INSERT INTO huellafirma.peticionhuella (dispositivo) VALUES ('d');");
this.cerrarConexion();



%>


<%!
  public Connection connection;
    private Statement estado;
  public Mediador _e = this.e ;

   
    
    
    public void crearConexion() {

        try {

            Class.forName("com.mysql.jdbc.Driver");//se carga el driver

            connection = new ConexionAplicacion(e).getCon();
            estado = connection.createStatement(); //se realiza la inicializacion de la peticion a la BD conectada
           
            
            System.out.println("Cree la conexion");

        } catch (Exception error) {

            System.out.println("se ha encontrado el siguien error: " + error);

        }

    }

    public boolean actualizar(String sql) {
boolean b = false;
        try {
            estado.executeUpdate(sql);
            b=true;
            System.out.println("pude realizar la operacion en la base de datos");
        } catch (SQLException ex) {

            System.out.println("excepcion de sql al reralizar operacion en la base de datos: " + ex);

        }
        return b;
    }
    
    public boolean guardarImagen(byte [] huella , String id){
    this.crearConexion();
	String sql = "UPDATE huellafirma.peticionhuella SET `estado` = 'PROCESADO', huella =?  WHERE id = ?";
	
	   PreparedStatement ps = null;
	try {
		ps = connection.prepareStatement(sql);
		ps.setBytes(1, huella);
		ps.setString(2, id);
		ps.executeUpdate();
		return true;
	} catch (Exception ex) {
		ex.printStackTrace();
	}finally{
		try {
			ps.close();
                    this.cerrarConexion();
		} catch (Exception ex) {
			ex.printStackTrace();}
	}        
	return false;
}

    public ResultSet consultar(String sql) {

        ResultSet resultado = null;
        try {
            resultado = estado.executeQuery(sql);
            System.out.println("pude devolver los datos");

        } catch (SQLException e) {

            System.out.println("excepcion de sql al devolver datos: " + e);

        }

        return resultado;
    }

    public void cerrarConexion() {

        try {
            connection.close();
            System.out.println("Cerre la conexion");
        } catch (SQLException ex) {
            System.out.println("Error de SQL: " + ex);
        }

    }





%>