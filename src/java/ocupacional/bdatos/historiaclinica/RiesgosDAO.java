/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ocupacional.bdatos.historiaclinica;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import ocupacional.valueobjects.historiaclinica.RiesgosVO;
import valeria.conexion.ConexionAplicacion;
import valeria.metodos.Cadenas;
import valeria.response.Mediador;

/**
 *
 * @author Sebas
 */
public class RiesgosDAO {

    Cadenas c = new Cadenas();
    Mediador e = null;
    ConexionAplicacion conn = null;
    private boolean closed = true;

    public RiesgosDAO(Mediador e) {
        this.e = e;
    }

    public RiesgosDAO(Mediador e, ConexionAplicacion conn) {
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

    public boolean Insertar(RiesgosVO RiesgosVO) throws Exception {
        PreparedStatement ps = null;
        ResultSet keyRS = null;
        boolean exito = false;
        this.getConexion();
        if (this.ConnEstado()) {
            try {
                this.conn.getCon().setAutoCommit(false);
                StringBuilder sql = new StringBuilder();

//                ***cambio dao***
                sql.append("INSERT INTO javaphistoriaclinica.`riesgos`( `ries_nombre`, `fari_id`, `ries_fechacambio`, `ries_registradopor`)"
                        + " VALUES (?,?,?,?)");
                ps = this.conn.getCon().prepareStatement(sql.toString(), PreparedStatement.RETURN_GENERATED_KEYS);
                int a = 1;
                ps.setString(a++, RiesgosVO.getRies_nombre());
                ps.setString(a++, RiesgosVO.getFari_id());
                ps.setTimestamp(a++, RiesgosVO.getRies_fechacambio());
                ps.setString(a++, RiesgosVO.getRies_registradopor());
                ps.executeUpdate();
                keyRS = ps.getGeneratedKeys();
                if (keyRS.next()) {
                    RiesgosVO.setRies_id(keyRS.getString(1));
                }
                if (RiesgosVO.getRies_id() != null) {

//                ***cambio dao***
                    exito = true;
                }
            } catch (Exception ex) {
                e.ImprimirError("RiesgosDAO;ocupacional.bdatos.RiesgosDAO.Insertar();msg:" + ex.getMessage());
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

    public boolean Actualizar(RiesgosVO RiesgosVO) throws Exception {
        PreparedStatement ps = null;
        ResultSet keyRS = null;
        boolean exito = false;
        this.getConexion();
        if (this.ConnEstado()) {
            try {
                this.conn.getCon().setAutoCommit(false);
                StringBuilder sql = new StringBuilder();
//                ***cambio dao***
                sql.append("UPDATE javaphistoriaclinica.`riesgos` SET "
                        + "`ries_nombre`=?,"
                        + "`fari_id`=?,"
                        + "`ries_fechacambio`=?,"
                        + "`ries_registradopor`=?"
                        + " WHERE `ries_id`=?");
                ps = this.conn.getCon().prepareStatement(sql.toString());
                int a = 1;
                ps.setString(a++, RiesgosVO.getRies_nombre());
                ps.setString(a++, RiesgosVO.getFari_id());
                ps.setTimestamp(a++, RiesgosVO.getRies_fechacambio());
                ps.setString(a++, RiesgosVO.getRies_registradopor());
                ps.setString(a++, RiesgosVO.getRies_id());

//                ***cambio dao***
                ps.executeUpdate();
                exito = true;
            } catch (Exception ex) {
                e.ImprimirError("RiesgosDAO;ocupacional.bdatos.RiesgosDAO.Actualizar();msg:" + ex.getMessage());
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

    public boolean Eliminar(RiesgosVO RiesgosVO) throws Exception {
        PreparedStatement ps = null;
        ResultSet keyRS = null;
        boolean exito = false;
        this.getConexion();
        if (this.ConnEstado()) {
            try {
                this.conn.getCon().setAutoCommit(false);
                StringBuilder sql = new StringBuilder();
                //                ***cambio dao***
                sql.append("DELETE FROM javaphistoriaclinica.riesgos   \n\t");
                sql.append("WHERE   ries_id = ? \n\t");
                ps = this.conn.getCon().prepareStatement(sql.toString());
                int a = 1;
                ps.setString(1, RiesgosVO.getRies_id());
                //                ***cambio dao***
                ps.executeUpdate();
                exito = true;
            } catch (Exception ex) {
                ex.printStackTrace();
                e.ImprimirError("RiesgosDAO;ocupacional.bdatos.RiesgosDAO.Eliminar();msg:" + ex.getMessage());
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

    public ArrayList<RiesgosVO> Listar() throws Exception {

        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<RiesgosVO> lista = new ArrayList<RiesgosVO>();
        boolean exito = false;
        this.getConexion();
        if (this.ConnEstado()) {

            try {
                StringBuilder sql = new StringBuilder();
                this.conn.getCon().setAutoCommit(false);
                //                ***cambio dao***
                sql.append("SELECT * FROM javaphistoriaclinica.riesgos");
                ps = this.conn.getCon().prepareStatement(sql.toString());
                rs = ps.executeQuery();
                if (rs.isBeforeFirst()) {

                    while (rs.next()) {
                        RiesgosVO RiesgosVO = new RiesgosVO();

                        RiesgosVO.setRies_id(rs.getString("ries_id"));
                        RiesgosVO.setRies_nombre(rs.getString("ries_nombre"));
                        RiesgosVO.setFari_id(rs.getString("fari_id"));
                        RiesgosVO.setRies_fechacambio(rs.getTimestamp("ries_fechacambio"));
                        RiesgosVO.setRies_registradopor(rs.getString("ries_registradopor"));

//                ***cambio dao***
                        lista.add(RiesgosVO);
                    }

                }

                System.out.println("3");

            } catch (Exception ex) {
                ex.printStackTrace();
                e.ImprimirError("RiesgosDAO;ocupacional.bdatos.RiesgosDAO.Listar();msg:" + ex.getMessage());
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

    public RiesgosVO Cargar(String Pegeid) throws Exception {

        PreparedStatement ps = null;
        ResultSet rs = null;
        RiesgosVO RiesgosVO = null;
        boolean exito = false;
        this.getConexion();
        if (this.ConnEstado()) {

            try {
                StringBuilder sql = new StringBuilder();
                this.conn.getCon().setAutoCommit(false);
                //                ***cambio dao***
                sql.append("SELECT * FROM javaphistoriaclinica.riesgos WHERE ries_id= ?");
                ps = this.conn.getCon().prepareStatement(sql.toString());
                ps.setString(1, Pegeid);
                rs = ps.executeQuery();
                if (rs.isBeforeFirst()) {

                    while (rs.next()) {
                        RiesgosVO = new RiesgosVO();
                        RiesgosVO.setRies_id(rs.getString("ries_id"));
                        RiesgosVO.setRies_nombre(rs.getString("ries_nombre"));
                        RiesgosVO.setFari_id(rs.getString("fari_id"));
                        RiesgosVO.setRies_fechacambio(rs.getTimestamp("ries_fechacambio"));
                        RiesgosVO.setRies_registradopor(rs.getString("ries_registradopor"));

//                ***cambio dao***
                    }

                }

                System.out.println("3");

            } catch (Exception ex) {
                ex.printStackTrace();
                e.ImprimirError("ClientesDAO;ocupacional.bdatos.ClientesDAO.Listar();msg:" + ex.getMessage());
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
        return RiesgosVO;
    }

}
