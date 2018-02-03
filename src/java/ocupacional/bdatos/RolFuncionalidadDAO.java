/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ocupacional.bdatos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import ocupacional.valueobjects.RolFuncionalidadVOs;
import valeria.conexion.ConexionAplicacion;
import valeria.metodos.Cadenas;
import valeria.response.Mediador;

/**
 *
 * @author D4V3
 */
public class RolFuncionalidadDAO {

    Cadenas c = new Cadenas();
    Mediador e = null;
    ConexionAplicacion conn = null;
    private boolean closed = true;

    public RolFuncionalidadDAO(Mediador e) {
        this.e = e;
    }

    public RolFuncionalidadDAO(Mediador e, ConexionAplicacion conn) {
        this.e = e;
        this.conn = conn;
        this.closed = false;
    }

    private void getConexion() throws Exception {
        if (this.conn == null) {
            this.conn = new ConexionAplicacion(this.e);
        } else if (!this.conn.getCon().isClosed()) {
            this.conn = new ConexionAplicacion(this.e);
        }
    }

    private boolean ConnEstado() throws SQLException {
        boolean estado = true;
        if (this.conn != null) {
            if (this.conn.getCon() != null) {
                if (this.conn.getCon().isClosed()) {
                    estado = false;
                }
            } else {
                estado = false;
            }
        } else {
            estado = false;
        }
        return estado;
    }

    public boolean Insertar(RolFuncionalidadVOs RolFuncionalidadVOs) throws Exception {
        PreparedStatement ps = null;
        ResultSet keyRS = null;
        boolean exito = false;
        this.getConexion();
        if (this.ConnEstado()) {
            try {
                this.conn.getCon().setAutoCommit(false);
                StringBuilder sql = new StringBuilder();

//                ***cambio dao***
                sql.append("INSERT INTO `rolfuncionalidad`( `role_id`, `func_id`, `rofu_fechacambio`, `role_registradopor`,rofu_operacion) VALUES (?,?,?,?,?)");
                ps = this.conn.getCon().prepareStatement(sql.toString(), PreparedStatement.RETURN_GENERATED_KEYS);
                int a = 1;
                ps.setString(a++, RolFuncionalidadVOs.getRole_id());
                ps.setString(a++, RolFuncionalidadVOs.getFunc_id());
                ps.setTimestamp(a++, RolFuncionalidadVOs.getRofu_fechacambio());
                ps.setString(a++, RolFuncionalidadVOs.getRofu_registradopor());
                ps.setString(a++, RolFuncionalidadVOs.getRofu_op());
                ps.executeUpdate();
                keyRS = ps.getGeneratedKeys();
                if (keyRS.next()) {
                    RolFuncionalidadVOs.setRofu_id(keyRS.getString(1));
                }
                if (RolFuncionalidadVOs.getRofu_id() != null) {

//                ***cambio dao***
                    exito = true;
                }
            } catch (Exception ex) {
                e.ImprimirError("RolFuncionalidadDAO;ocupacional.bdatos.RolFuncionalidadDAO.Insertar();msg:" + ex.getMessage());
            } finally {
                if (this.closed && this.conn.getCon() != null) {
                    this.conn.getCon().commit();
                    this.conn.getCon().close();
                    this.conn = null;
                }
                try {
                    if (keyRS != null) {
                        keyRS.close();
                    }
                    if (ps != null) {
                        ps.close();
                    }
                } catch (Exception ex) {
                }
            }

        }
        return exito;
    }

    public boolean Eliminar(RolFuncionalidadVOs RolFuncionalidadVOs) throws Exception {
        PreparedStatement ps = null;
        ResultSet keyRS = null;
        boolean exito = false;
        this.getConexion();
        if (this.ConnEstado()) {
            try {
                this.conn.getCon().setAutoCommit(false);
                StringBuilder sql = new StringBuilder();
                //                ***cambio dao***
                sql.append("DELETE FROM javap.rolfuncionalidad   \n\t");
                sql.append("WHERE   role_id = ? \n\t");
                ps = this.conn.getCon().prepareStatement(sql.toString());
                int a = 1;
                ps.setString(a++, RolFuncionalidadVOs.getRole_id());
                //                ***cambio dao***
                ps.executeUpdate();
                exito = true;
            } catch (Exception ex) {
                e.ImprimirError("RolFuncionalidadDAO;ocupacional.bdatos.RolFuncionalidadDAO.Eliminar();msg:" + ex.getMessage());
            } finally {
                if (this.closed && this.conn.getCon() != null) {
                    this.conn.getCon().commit();
                    this.conn.getCon().close();
                    this.conn = null;
                }
                try {
                    if (keyRS != null) {
                        keyRS.close();
                    }
                    if (ps != null) {
                        ps.close();
                    }
                } catch (Exception ex) {
                }
            }
        }
        return exito;
    }

    public ArrayList<RolFuncionalidadVOs> ListarXrol(String id) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<RolFuncionalidadVOs> lista = null;
        lista = new ArrayList<RolFuncionalidadVOs>();
        boolean exito = false;
        this.getConexion();
        if (this.ConnEstado()) {
            try {
                StringBuilder sql = new StringBuilder();
                this.conn.getCon().setAutoCommit(false);
                //                ***cambio dao***
                sql.append("SELECT * FROM rolfuncionalidad as r inner join funcionalidades f on f.func_id=r.func_id\n"
                        + " where role_id=? \n"
                        + "order by f.func_codigo");
                ps = this.conn.getCon().prepareStatement(sql.toString());
                ps.setString(1, id);
                rs = ps.executeQuery();
                if (rs.isBeforeFirst()) {
                    while (rs.next()) {
                        RolFuncionalidadVOs RolFuncionalidadVOs = new RolFuncionalidadVOs();

                        RolFuncionalidadVOs.setRofu_id(rs.getString("rofu_id"));
                        RolFuncionalidadVOs.setRole_id(rs.getString("role_id"));
                        RolFuncionalidadVOs.setFunc_id(rs.getString("func_id"));
                        RolFuncionalidadVOs.setRofu_op(rs.getString("rofu_operacion"));
                        System.out.println("entre>>>>>-----");
//                ***cambio dao***
                        lista.add(RolFuncionalidadVOs);
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                e.ImprimirError("RolFuncionalidadDAO;ocupacional.bdatos.RolFuncionalidadDAO.Listar();msg:" + ex.getMessage());
            } finally {
                if (this.closed && this.conn.getCon() != null) {
                    this.conn.getCon().commit();
                    this.conn.getCon().close();
                    this.conn = null;
                }
                try {
                    if (rs != null) {
                        rs.close();
                    }
                    if (ps != null) {
                        ps.close();
                    }
                } catch (Exception ex) {
                }
            }
        }
        return lista;
    }

    public RolFuncionalidadVOs Cargar(String id) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;

        RolFuncionalidadVOs RolFuncionalidadVOs = new RolFuncionalidadVOs();
        boolean exito = false;
        this.getConexion();
        if (this.ConnEstado()) {
            try {
                StringBuilder sql = new StringBuilder();
                this.conn.getCon().setAutoCommit(false);
                //                ***cambio dao***
                sql.append("SELECT * FROM `rolfuncionalidad` where rofu_id=?");
                ps = this.conn.getCon().prepareStatement(sql.toString());
                ps.setString(1, id);
                rs = ps.executeQuery();
                if (rs.isBeforeFirst()) {
                    while (rs.next()) {

                        RolFuncionalidadVOs.setRofu_id(rs.getString("rofu_id"));
                        RolFuncionalidadVOs.setRofu_id(rs.getString("role_id"));
                        RolFuncionalidadVOs.setRofu_id(rs.getString("func_id"));
                        RolFuncionalidadVOs.setRofu_op(rs.getString("rofu_operacion"));

//                ***cambio dao***
                    }
                }
            } catch (Exception ex) {
                e.ImprimirError("RolFuncionalidadDAO;ocupacional.bdatos.RolFuncionalidadDAO.Listar();msg:" + ex.getMessage());
            } finally {
                if (this.closed && this.conn.getCon() != null) {
                    this.conn.getCon().commit();
                    this.conn.getCon().close();
                    this.conn = null;
                }
                try {
                    if (rs != null) {
                        rs.close();
                    }
                    if (ps != null) {
                        ps.close();
                    }
                } catch (Exception ex) {
                }
            }
        }
        return RolFuncionalidadVOs;
    }

}
