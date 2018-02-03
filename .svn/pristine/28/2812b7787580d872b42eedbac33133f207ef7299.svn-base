/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ocupacional.bdatos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import ocupacional.valueobjects.RolesVO;

public class RolesDAO {

    ControladorBD c = new ControladorBD();
    String sql = "";
    ResultSet r;

    public ArrayList<RolesVO> listar() {

        sql = "select * from javap.roles";

        ArrayList<RolesVO> array = new ArrayList<RolesVO>();

        c.conexion();
        r = c.devuelveDatos(sql);
        int i = 0;
        try {
            while (r.next()) {
//                i++;
//                System.out.println(i);

                RolesVO rol = new RolesVO();
                rol.setRole_id(r.getString("role_id"));
                rol.setRole_descripcion(r.getString("role_descripcion"));
                rol.setRole_fechacambio(Timestamp.valueOf(r.getString("role_fechacambio")));
                rol.setRole_registradopor(r.getString("roleregistradopor"));

                array.add(rol);

            }
        } catch (SQLException ex) {
            Logger.getLogger(JuridicasDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        c.cerrarConexion();

        return array;

    }

    public ArrayList<RolesVO> listarRolesUsuario(String idUsuario) {

        sql = "SELECT * from javap.roles as r inner join javap.usuariorol as ur inner join javap.usuarios as u WHERE ur.usua_id="+ idUsuario +" and u.usua_id=ur.usua_id and r.role_id=ur.role_id ;";

        ArrayList<RolesVO> array = new ArrayList<RolesVO>();

        c.conexion();
        r = c.devuelveDatos(sql);
        int i = 0;
        try {
            while (r.next()) {
//                i++;
//                System.out.println(i);

                RolesVO rol = new RolesVO();
                rol.setRole_id(r.getString("role_id"));
                rol.setRole_descripcion(r.getString("role_descripcion"));
                rol.setRole_fechacambio(Timestamp.valueOf(r.getString("role_fechacambio")));
                rol.setRole_registradopor(r.getString("roleregistradopor"));

                array.add(rol);

            }
        } catch (SQLException ex) {
            Logger.getLogger(JuridicasDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        c.cerrarConexion();

        return array;

    }
}
