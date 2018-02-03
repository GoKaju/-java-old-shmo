package ocupacional.bdatos ;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControladorBD {

    private Statement estado;//variable para hacer peticion a SQL
    private Connection connection;//variable para hacer la conexion a SQL

    //conexion a la base de datos
    public Connection conexion() {
        try {

            Class.forName("com.mysql.jdbc.Driver");//se carga el driver
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javap", "root", "/$V$p.soig");//se realiza la conexion (ubicacion,usuario,contraseña)
            estado = connection.createStatement(); //se realiza la inicializacion de la peticion a la BD conectada
            System.out.println("me pude conectar a la base de datos");
        } catch (SQLException e) {

            System.out.println("error en mysql al conectarse: " + e);

        } catch (ClassNotFoundException ec) {

            ec.printStackTrace();

        } catch (Exception error) {

            System.out.println("se ha encontrado el siguien error: " + error);

        }
        return connection;
    }

    public boolean operacionBD(String sql) {

        boolean resultado = false;

        try {

            estado.executeUpdate(sql);
            resultado = true;
        } catch (SQLException ex) {

            System.out.println("excepcion de sql al reralizar operacion en la base de datos: " + ex);

            resultado = false;

        }

        return resultado;
    }

    public ResultSet devuelveDatos(String sql) {

        ResultSet resultado = null;
        try {
            System.out.println("esta es la consulta= " + sql);
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
            estado.close();
            System.out.println("Cerre la conexion");
        } catch (SQLException ex) {
            Logger.getLogger(ControladorBD.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
