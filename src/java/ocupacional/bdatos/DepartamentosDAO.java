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
import ocupacional.valueobjects.DepartamentosVO;
import valeria.conexion.ConexionAplicacion;
import valeria.metodos.Cadenas;
import valeria.response.Mediador;

/**
 *
 * @author VALERIA
 */
public class DepartamentosDAO {

    Cadenas c = new Cadenas();
    Mediador e = null;
    ConexionAplicacion conn = null;
    private boolean closed = true;

    public DepartamentosDAO(Mediador e) {
        this.e = e;
    }

    public DepartamentosDAO(Mediador e, ConexionAplicacion conn) {
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

    public boolean Insertar(DepartamentosVO DepartamentosVO) throws Exception {
        PreparedStatement ps = null;
        ResultSet keyRS = null;
        boolean exito = false;
        this.getConexion();
        if (this.ConnEstado()) {
            try {
                this.conn.getCon().setAutoCommit(false);
                StringBuffer sql = new StringBuffer();
                sql.append("INSERT INTO javap.departamentos(pais_id, depa_nombre, depa_fechacambio, depa_registradopor) VALUES (?,?,?,?) ");
                ps = this.conn.getCon().prepareStatement(sql.toString(), PreparedStatement.RETURN_GENERATED_KEYS);
                int a = 1;
                ps.setString(a++, DepartamentosVO.getPais_id());
                ps.setString(a++, DepartamentosVO.getDepa_nombre());
                ps.setTimestamp(a++, DepartamentosVO.getDepa_fechacambio());
                ps.setString(a++, DepartamentosVO.getDepa_registradopor());
                ps.executeUpdate();
                keyRS = ps.getGeneratedKeys();
                if (keyRS.next()) {
                    DepartamentosVO.setDepa_id(keyRS.getString(1));
                }
                if (DepartamentosVO.getDepa_id() != null) {
                    exito = true;
                }
            } catch (Exception ex) {
                e.ImprimirError("DepartamentosDAO;ocupacional.bdatos.DepartamentosDAO.Insertar();msg:" + ex.getMessage());
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

    public boolean Actualizar(DepartamentosVO DepartamentosVO) throws Exception {
        PreparedStatement ps = null;
        ResultSet keyRS = null;
        boolean exito = false;
        this.getConexion();
        if (this.ConnEstado()) {
            try {
                this.conn.getCon().setAutoCommit(false);
                StringBuffer sql = new StringBuffer();
                sql.append("UPDATE  javap.departamentos SET  \n\t");
                sql.append("        pais_id = ?,  \n\t");
                sql.append("        depa_nombre = ?,  \n\t");
                sql.append("        depa_fechacambio = ?,  \n\t");
                sql.append("        depa_registradopor = ?  \n\t");
                sql.append("WHERE   depa_id = ? \n\t");
                ps = this.conn.getCon().prepareStatement(sql.toString());
                int a = 1;
                ps.setString(a++, DepartamentosVO.getPais_id());
                ps.setString(a++, DepartamentosVO.getDepa_nombre());
                ps.setTimestamp(a++, DepartamentosVO.getDepa_fechacambio());
                ps.setString(a++, DepartamentosVO.getDepa_registradopor());
                ps.setString(a++, DepartamentosVO.getDepa_id());
                ps.executeUpdate();
                exito = true;
            } catch (Exception ex) {
                e.ImprimirError("DepartamentosDAO;ocupacional.bdatos.DepartamentosDAO.Actualizar();msg:" + ex.getMessage());
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

    public boolean Eliminar(DepartamentosVO DepartamentosVO) throws Exception {
        PreparedStatement ps = null;
        ResultSet keyRS = null;
        boolean exito = false;
        this.getConexion();
        if (this.ConnEstado()) {
            try {
                this.conn.getCon().setAutoCommit(false);
                StringBuffer sql = new StringBuffer();
                sql.append("DELETE FROM javap.departamentos   \n\t");
                sql.append("WHERE   depa_id = ? \n\t");
                ps = this.conn.getCon().prepareStatement(sql.toString());
                int a = 1;
                ps.setString(a++, DepartamentosVO.getDepa_id());
                ps.executeUpdate();
                exito = true;
            } catch (Exception ex) {
                e.ImprimirError("DepartamentosDAO;ocupacional.bdatos.DepartamentosDAO.Eliminar();msg:" + ex.getMessage());
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

    public Object Listar() throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<DepartamentosVO> lista = null;
        boolean exito = false;
        this.getConexion();
        if (this.ConnEstado()) {
            try {
                this.conn.getCon().setAutoCommit(false);
                StringBuffer sql = new StringBuffer();
                sql.append("SELECT  depa_id,  \n\t");
                sql.append("        pais_id,  \n\t");
                sql.append("        depa_nombre,  \n\t");
                sql.append("        depa_fechacambio,  \n\t");
                sql.append("        depa_registradopor  \n\t");
                sql.append("FROM    javap.departamentos \n\t");
                sql.append("ORDER BY depa_nombre \n\t");
                ps = this.conn.getCon().prepareStatement(sql.toString());
                rs = ps.executeQuery();
                if (rs.isBeforeFirst()) {
                    lista = new ArrayList<DepartamentosVO>();
                    while (rs.next()) {
                        DepartamentosVO DepartamentosVO = new DepartamentosVO();
                        DepartamentosVO.setDepa_id(rs.getString("depa_id"));
                        DepartamentosVO.setDepa_nombre(rs.getString("depa_nombre"));
                        DepartamentosVO.setPais_id(rs.getString("pais_id"));
                        lista.add(DepartamentosVO);
                    }
                }
            } catch (Exception ex) {
                e.ImprimirError("DepartamentosDAO;ocupacional.bdatos.DepartamentosDAO.Listar();msg:" + ex.getMessage());
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

}
