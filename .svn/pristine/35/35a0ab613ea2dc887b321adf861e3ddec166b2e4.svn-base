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
import ocupacional.valueobjects.EmpleadosVO;
import valeria.conexion.ConexionAplicacion;
import valeria.response.Mediador;

/**
 *
 * @author Sebas
 */
public class EmpleadosDAO {
    
       Mediador e = null;
    ConexionAplicacion conn = null;
    private boolean closed = true;

    public EmpleadosDAO(Mediador e) {
        this.e = e;
    }

    public EmpleadosDAO(Mediador e, ConexionAplicacion conn) {
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

    public boolean Insertar(EmpleadosVO EmpleadosVO) throws Exception {
        PreparedStatement ps = null;
        ResultSet keyRS = null;
        boolean exito = false;
        this.getConexion();
        if (this.ConnEstado()) {
            try {
                this.conn.getCon().setAutoCommit(false);
                StringBuilder sql = new StringBuilder();

//                ***cambio dao***
                sql.append("INSERT INTO `empleados`(`pege_id`, `sede_id`, `usua_id`, `empl_fechaIngreso`, `empl_area`, `empl_fechacambio`, `empl_resgistradopor`)"
                        + " VALUES (?,?,?,?,?,?,?)");
                ps = this.conn.getCon().prepareStatement(sql.toString(), PreparedStatement.RETURN_GENERATED_KEYS);
                int a = 1;
                ps.setString(a++, EmpleadosVO.getPege_id());
                ps.setString(a++, EmpleadosVO.getSede_id());
                ps.setString(a++, EmpleadosVO.getUsua_id());
                ps.setTimestamp(a++, EmpleadosVO.getEmpl_fechaingreso());
                ps.setString(a++, EmpleadosVO.getEmpl_area());
                ps.setTimestamp(a++, EmpleadosVO.getEmpl_fechaCambio());
                ps.setString(a++, EmpleadosVO.getEmpl_registradoPor());
                ps.executeUpdate();
                keyRS = ps.getGeneratedKeys();
                
                exito= true;
            } catch (Exception ex) {
                e.ImprimirError("EmpleadosDAO;ocupacional.bdatos.EmpleadosDAO.Insertar();msg:" + ex.getMessage());
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

    public boolean Actualizar(EmpleadosVO EmpleadosVO) throws Exception {
        PreparedStatement ps = null;
        ResultSet keyRS = null;
        boolean exito = false;
        this.getConexion();
        if (this.ConnEstado()) {
            try {
                this.conn.getCon().setAutoCommit(false);
                StringBuilder sql = new StringBuilder();
//                ***cambio dao***
                sql.append("UPDATE `empleados` SET "
                        + "`sede_id`=?,"
//                        + "`usua_id`=?,"
//                        + "`empl_fechaIngreso`=?,"
                        + "`empl_area`=?,"
                        + "`empl_fechacambio`=?,"
                        + "`empl_resgistradopor`=?"
                        + " WHERE `pege_id`=?");
                ps = this.conn.getCon().prepareStatement(sql.toString());
                int a = 1;
                ps.setString(a++, EmpleadosVO.getSede_id());
//                ps.setString(a++, EmpleadosVO.getUsua_id());
//                ps.setTimestamp(a++, EmpleadosVO.getEmpl_fechaingreso());
                ps.setString(a++, EmpleadosVO.getEmpl_area());
                ps.setTimestamp(a++, EmpleadosVO.getEmpl_fechaCambio());
                ps.setString(a++, EmpleadosVO.getEmpl_registradoPor());
                  ps.setString(a++, EmpleadosVO.getPege_id());

//                ***cambio dao***
                ps.executeUpdate();
                exito = true;
            } catch (Exception ex) {
                e.ImprimirError("EmpleadosDAO;ocupacional.bdatos.EmpleadosDAO.Actualizar();msg:" + ex.getMessage());
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

    public boolean Eliminar(String id) throws Exception {
        PreparedStatement ps = null;
        ResultSet keyRS = null;
        boolean exito = false;
        this.getConexion();
        if (this.ConnEstado()) {
            try {
                this.conn.getCon().setAutoCommit(false);
                StringBuilder sql = new StringBuilder();
                //                ***cambio dao***
                sql.append("DELETE FROM `empleados` WHERE `pege_id`=? \n\t");
                ps = this.conn.getCon().prepareStatement(sql.toString());
                int a = 1;
                ps.setString(1, id);
                //                ***cambio dao***
                ps.executeUpdate();
                exito = true;
            } catch (Exception ex) {
                ex.printStackTrace();
                e.ImprimirError("EmpleadosDAO;ocupacional.bdatos.EmpleadosDAO.Eliminar();msg:" + ex.getMessage());
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

    public ArrayList<EmpleadosVO> Listar() throws Exception {

        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<EmpleadosVO> lista = new ArrayList<EmpleadosVO>();
        boolean exito = false;
        this.getConexion();
        if (this.ConnEstado()) {

            try {
                StringBuilder sql = new StringBuilder();
                this.conn.getCon().setAutoCommit(false);
                //                ***cambio dao***
                sql.append("SELECT * FROM `centrocostos`");
                ps = this.conn.getCon().prepareStatement(sql.toString());
                rs = ps.executeQuery();
                if (rs.isBeforeFirst()) {

                    while (rs.next()) {
                        EmpleadosVO EmpleadosVO = new EmpleadosVO();

                        EmpleadosVO.setPege_id(rs.getString("pege_id"));
                        EmpleadosVO.setSede_id(rs.getString("sede_id"));
                        EmpleadosVO.setUsua_id(rs.getString("usua_id"));
                        EmpleadosVO.setEmpl_fechaingreso(rs.getTimestamp("empl_fechaIngreso"));
                        EmpleadosVO.setEmpl_area(rs.getString("empl_area"));
                        EmpleadosVO.setEmpl_fechaCambio(rs.getTimestamp("empl_fechacambio"));
                        EmpleadosVO.setEmpl_registradoPor(rs.getString("empl_resgistradopor"));

//                ***cambio dao***
                        lista.add(EmpleadosVO);
                    }

                }

                System.out.println("3");

            } catch (Exception ex) {
                ex.printStackTrace();
                e.ImprimirError("EmpleadosDAO;ocupacional.bdatos.EmpleadosDAO.Listar();msg:" + ex.getMessage());
            } finally {
                if (this.closed && this.conn.getCon() != null) {
                    this.conn.getCon().commit();
                    this.conn.getCon().close();
                    this.conn = null;
                    System.out.println("entre");
                }
                try {
                    if (rs != null) {
                        rs.close();
                    }
                    if (ps != null) {
                        ps.close();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        return lista;
    }

   
    public EmpleadosVO Cargar(String ceco_id) throws Exception {

        PreparedStatement ps = null;
        ResultSet rs = null;
        EmpleadosVO EmpleadosVO = new EmpleadosVO();
        boolean exito = false;
        this.getConexion();
        if (this.ConnEstado()) {

            try {
                StringBuilder sql = new StringBuilder();
                this.conn.getCon().setAutoCommit(false);
                //                ***cambio dao***
                sql.append("SELECT * FROM `empleados` WHERE `pege_id`=?");
                ps = this.conn.getCon().prepareStatement(sql.toString());
                ps.setString(1, ceco_id);
                rs = ps.executeQuery();
                if (rs.isBeforeFirst()) {

                    while (rs.next()) {

                        EmpleadosVO.setPege_id(rs.getString("pege_id"));
                        EmpleadosVO.setSede_id(rs.getString("sede_id"));
                        EmpleadosVO.setUsua_id(rs.getString("usua_id"));
                        EmpleadosVO.setEmpl_fechaingreso(rs.getTimestamp("empl_fechaIngreso"));
                        EmpleadosVO.setEmpl_area(rs.getString("empl_area"));
                        EmpleadosVO.setEmpl_fechaCambio(rs.getTimestamp("empl_fechacambio"));
                        EmpleadosVO.setEmpl_registradoPor(rs.getString("empl_resgistradopor"));
//                ***cambio dao***
                    }

                }

                System.out.println("3");

            } catch (Exception ex) {
                ex.printStackTrace();
                e.ImprimirError("EmpleadosDAO;ocupacional.bdatos.EmpleadosDAO.Listar();msg:" + ex.getMessage());
            } finally {
                if (this.closed && this.conn.getCon() != null) {
                    this.conn.getCon().commit();
                    this.conn.getCon().close();
                    this.conn = null;
                    System.out.println("entre");
                }
                try {
                    if (rs != null) {
                        rs.close();
                    }
                    if (ps != null) {
                        ps.close();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        return EmpleadosVO;
    }

    
}
