/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ocupacional.bdatos.facturacion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import ocupacional.valueobjects.facturacion.CentroCostosVO;
import valeria.conexion.ConexionAplicacion;
import valeria.response.Mediador;

/**
 *
 * @author Sebas
 */
public class CentroCostosDAO {

    Mediador e = null;
    ConexionAplicacion conn = null;
    private boolean closed = true;

    public CentroCostosDAO(Mediador e) {
        this.e = e;
    }

    public CentroCostosDAO(Mediador e, ConexionAplicacion conn) {
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

    public boolean Insertar(CentroCostosVO CentroCostosVO) throws Exception {
        PreparedStatement ps = null;
        ResultSet keyRS = null;
        boolean exito = false;
        this.getConexion();
        if (this.ConnEstado()) {
            try {
                this.conn.getCon().setAutoCommit(false);
                StringBuilder sql = new StringBuilder();

//                ***cambio dao***
                sql.append("INSERT INTO `centrocostos`( `clie_id`, `ceco_observacion`, `ceco_estado`, `ceco_fechacambio`, `ceco_registradopor`) "
                        + "VALUES (?,?,?,?,?)");
                ps = this.conn.getCon().prepareStatement(sql.toString(), PreparedStatement.RETURN_GENERATED_KEYS);
                int a = 1;
                ps.setString(a++, CentroCostosVO.getClie_id());
                ps.setString(a++, CentroCostosVO.getCeco_observacion());
                ps.setString(a++, CentroCostosVO.getCeco_estado());
                ps.setTimestamp(a++, CentroCostosVO.getCeco_fecaCambio());
                ps.setString(a++, CentroCostosVO.getCeco_registradoPor());
                ps.executeUpdate();
                keyRS = ps.getGeneratedKeys();
                if (keyRS.next()) {
                    CentroCostosVO.setClie_id(keyRS.getString(1));
                }
                if (CentroCostosVO.getClie_id() != null) {

//                ***cambio dao***
                    exito = true;
                }
            } catch (Exception ex) {
                e.ImprimirError("CentroCostosDAO;ocupacional.bdatos.CentroCostosDAO.Insertar();msg:" + ex.getMessage());
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

    public boolean Actualizar(CentroCostosVO CentroCostosVO) throws Exception {
        PreparedStatement ps = null;
        ResultSet keyRS = null;
        boolean exito = false;
        this.getConexion();
        if (this.ConnEstado()) {
            try {
                this.conn.getCon().setAutoCommit(false);
                StringBuilder sql = new StringBuilder();
//                ***cambio dao***
                sql.append("UPDATE `centrocostos` SET `clie_id`=?,"
                        + "`ceco_observacion`=?,"
                        + "`ceco_estado`=?,"
                        + "`ceco_fechacambio`=?,"
                        + "`ceco_registradopor`=?"
                        + " WHERE `ceco_id`=?");
                ps = this.conn.getCon().prepareStatement(sql.toString());
                int a = 1;
                ps.setString(a++, CentroCostosVO.getClie_id());
                ps.setString(a++, CentroCostosVO.getCeco_observacion());
                ps.setString(a++, CentroCostosVO.getCeco_estado());
                ps.setTimestamp(a++, CentroCostosVO.getCeco_fecaCambio());
                ps.setString(a++, CentroCostosVO.getCeco_registradoPor());
                ps.setString(a++, CentroCostosVO.getCeco_id());

//                ***cambio dao***
                ps.executeUpdate();
                exito = true;
            } catch (Exception ex) {
                e.ImprimirError("CentroCostosDAO;ocupacional.bdatos.CentroCostosDAO.Actualizar();msg:" + ex.getMessage());
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
                sql.append("DELETE FROM `centrocostos` WHERE `ceco_id`=? \n\t");
                ps = this.conn.getCon().prepareStatement(sql.toString());
                int a = 1;
                ps.setString(1, id);
                //                ***cambio dao***
                ps.executeUpdate();
                exito = true;
            } catch (Exception ex) {
                ex.printStackTrace();
                e.ImprimirError("CentroCostosDAO;ocupacional.bdatos.CentroCostosDAO.Eliminar();msg:" + ex.getMessage());
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

    public ArrayList<CentroCostosVO> Listar() throws Exception {

        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<CentroCostosVO> lista = new ArrayList<CentroCostosVO>();
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
                        CentroCostosVO CentroCostosVO = new CentroCostosVO();

                        CentroCostosVO.setClie_id(rs.getString("clie_id"));
                        CentroCostosVO.setCeco_estado(rs.getString("ceco_estado"));
                        CentroCostosVO.setCeco_fecaCambio(rs.getTimestamp("ceco_fechacambio"));
                        CentroCostosVO.setCeco_observacion(rs.getString("ceco_observacion"));
                        CentroCostosVO.setCeco_registradoPor(rs.getString("ceco_registradopor"));
                        CentroCostosVO.setCeco_id(rs.getString("ceco_id"));

//                ***cambio dao***
                        lista.add(CentroCostosVO);
                    }

                }

                System.out.println("3");

            } catch (Exception ex) {
                ex.printStackTrace();
                e.ImprimirError("CentroCostosDAO;ocupacional.bdatos.CentroCostosDAO.Listar();msg:" + ex.getMessage());
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

public ArrayList<CentroCostosVO> ListarXcliente(String clie_id) throws Exception {

        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<CentroCostosVO> lista = new ArrayList<CentroCostosVO>();
        boolean exito = false;
        this.getConexion();
        if (this.ConnEstado()) {

            try {
                StringBuilder sql = new StringBuilder();
                this.conn.getCon().setAutoCommit(false);
                //                ***cambio dao***
                sql.append("SELECT * FROM `centrocostos` WHERE clie_id=?");
                ps = this.conn.getCon().prepareStatement(sql.toString());

                ps.setString(1, clie_id);

                rs = ps.executeQuery();
                if (rs.isBeforeFirst()) {

                    while (rs.next()) {
                        CentroCostosVO CentroCostosVO = new CentroCostosVO();

                        CentroCostosVO.setClie_id(rs.getString("clie_id"));
                        CentroCostosVO.setCeco_estado(rs.getString("ceco_estado"));
                        CentroCostosVO.setCeco_fecaCambio(rs.getTimestamp("ceco_fechacambio"));
                        CentroCostosVO.setCeco_observacion(rs.getString("ceco_observacion"));
                        CentroCostosVO.setCeco_registradoPor(rs.getString("ceco_registradopor"));
                        CentroCostosVO.setCeco_id(rs.getString("ceco_id"));

//                ***cambio dao***
                        lista.add(CentroCostosVO);
                    }

                }

                System.out.println("3");

            } catch (Exception ex) {
                ex.printStackTrace();
                e.ImprimirError("CentroCostosDAO;ocupacional.bdatos.CentroCostosDAO.Listar();msg:" + ex.getMessage());
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

    public CentroCostosVO Cargar(String ceco_id) throws Exception {

        PreparedStatement ps = null;
        ResultSet rs = null;
        CentroCostosVO CentroCostosVO = new CentroCostosVO();
        boolean exito = false;
        this.getConexion();
        if (this.ConnEstado()) {

            try {
                StringBuilder sql = new StringBuilder();
                this.conn.getCon().setAutoCommit(false);
                //                ***cambio dao***
                sql.append("SELECT * FROM `centrocostos` WHERE `ceco_id`=?");
                ps = this.conn.getCon().prepareStatement(sql.toString());
                ps.setString(1, ceco_id);
                rs = ps.executeQuery();
                if (rs.isBeforeFirst()) {

                    while (rs.next()) {

                        CentroCostosVO.setClie_id(rs.getString("clie_id"));
                        CentroCostosVO.setCeco_estado(rs.getString("ceco_estado"));
                        CentroCostosVO.setCeco_fecaCambio(rs.getTimestamp("ceco_fechacambio"));
                        CentroCostosVO.setCeco_observacion(rs.getString("ceco_observacion"));
                        CentroCostosVO.setCeco_registradoPor(rs.getString("ceco_registradopor"));
                        CentroCostosVO.setCeco_id(rs.getString("ceco_id"));

//                ***cambio dao***
                    }

                }

                System.out.println("3");

            } catch (Exception ex) {
                ex.printStackTrace();
                e.ImprimirError("CentroCostosDAO;ocupacional.bdatos.CentroCostosDAO.Listar();msg:" + ex.getMessage());
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
        return CentroCostosVO;
    }

}
