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
import ocupacional.valueobjects.CiudadesVO;
import valeria.conexion.ConexionAplicacion;
import valeria.metodos.Cadenas;
import valeria.response.Mediador;

/**
 *
 * @author VALERIA
 */
public class CiudadesDAO {

    Cadenas c = new Cadenas();
    Mediador e = null;
    ConexionAplicacion conn = null;
    private boolean closed = true;

    public CiudadesDAO(Mediador e) {
        this.e = e;
    }

    public CiudadesDAO(Mediador e, ConexionAplicacion conn) {
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

    public boolean Insertar(CiudadesVO CiudadesVO) throws Exception {
        PreparedStatement ps = null;
        ResultSet keyRS = null;
        boolean exito = false;
        this.getConexion();
        if (this.ConnEstado()) {
            try {
                this.conn.getCon().setAutoCommit(false);
                StringBuffer sql = new StringBuffer();
                sql.append("INSERT INTO javap.ciudades(depa_id, ciud_nombre, ciud_fechacambio, ciud_registradopor) VALUES (?,?,?,?) ");
                ps = this.conn.getCon().prepareStatement(sql.toString(), PreparedStatement.RETURN_GENERATED_KEYS);
                int a = 1;
                ps.setString(a++, CiudadesVO.getDepa_id());
                ps.setString(a++, CiudadesVO.getCiud_nombre());
                ps.setTimestamp(a++, CiudadesVO.getCiud_fechacambio());
                ps.setString(a++, CiudadesVO.getCiud_registradopor());
                ps.executeUpdate();
                keyRS = ps.getGeneratedKeys();
                if (keyRS.next()) {
                    CiudadesVO.setCiud_id(keyRS.getString(1));
                }
                if (CiudadesVO.getCiud_id() != null) {
                    exito = true;
                }
            } catch (Exception ex) {
                e.ImprimirError("CiudadesDAO;ocupacional.bdatos.CiudadesDAO.Insertar();msg:" + ex.getMessage());
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

    public boolean Actualizar(CiudadesVO CiudadesVO) throws Exception {
        PreparedStatement ps = null;
        ResultSet keyRS = null;
        boolean exito = false;
        this.getConexion();
        if (this.ConnEstado()) {
            try {
                this.conn.getCon().setAutoCommit(false);
                StringBuffer sql = new StringBuffer();
                sql.append("UPDATE  javap.ciudades SET  \n\t");
                sql.append("        depa_id = ?,  \n\t");
                sql.append("        ciud_nombre = ?,  \n\t");
                sql.append("        ciud_fechacambio = ?,  \n\t");
                sql.append("        ciud_registradopor = ?  \n\t");
                sql.append("WHERE   ciud_id = ? \n\t");
                ps = this.conn.getCon().prepareStatement(sql.toString());
                int a = 1;
                ps.setString(a++, CiudadesVO.getDepa_id());
                ps.setString(a++, CiudadesVO.getCiud_nombre());
                ps.setTimestamp(a++, CiudadesVO.getCiud_fechacambio());
                ps.setString(a++, CiudadesVO.getCiud_registradopor());
                ps.setString(a++, CiudadesVO.getCiud_id());
                ps.executeUpdate();
                exito = true;
            } catch (Exception ex) {
                e.ImprimirError("CiudadesDAO;ocupacional.bdatos.CiudadesDAO.Actualizar();msg:" + ex.getMessage());
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

    public boolean Eliminar(CiudadesVO CiudadesVO) throws Exception {
        PreparedStatement ps = null;
        ResultSet keyRS = null;
        boolean exito = false;
        this.getConexion();
        if (this.ConnEstado()) {
            try {
                this.conn.getCon().setAutoCommit(false);
                StringBuffer sql = new StringBuffer();
                sql.append("DELETE FROM javap.ciudades   \n\t");
                sql.append("WHERE   ciud_id = ? \n\t");
                ps = this.conn.getCon().prepareStatement(sql.toString());
                int a = 1;
                ps.setString(a++, CiudadesVO.getCiud_id());
                ps.executeUpdate();
                exito = true;
            } catch (Exception ex) {
                e.ImprimirError("CiudadesDAO;ocupacional.bdatos.CiudadesDAO.Eliminar();msg:" + ex.getMessage());
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

    public Object Listar(String id) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<CiudadesVO> lista = null;
        boolean exito = false;
        this.getConexion();
        if (this.ConnEstado()) {
            try {
                this.conn.getCon().setAutoCommit(false);

                StringBuffer sql = new StringBuffer();
                sql.append("SELECT  ciud_id,  \n\t");
                sql.append("        depa_id,  \n\t");
                sql.append("        ciud_nombre,  \n\t");
                sql.append("        ciud_fechacambio,  \n\t");
                sql.append("        ciud_registradopor  \n\t");
                sql.append("FROM    javap.ciudades \n\t");
                sql.append("WHERE    depa_id=? \n\t");
                sql.append("ORDER BY ciud_nombre \n\t");
                ps = this.conn.getCon().prepareStatement(sql.toString());
                ps.setString(1, id);
                rs = ps.executeQuery();
                if (rs.isBeforeFirst()) {
                    lista = new ArrayList<CiudadesVO>();
                    while (rs.next()) {
                        CiudadesVO CiudadesVO = new CiudadesVO();
                        CiudadesVO.setCiud_id(rs.getString("ciud_id"));
                        CiudadesVO.setCiud_nombre(rs.getString("ciud_nombre"));
                        CiudadesVO.setDepa_id(rs.getString("depa_id"));
                        lista.add(CiudadesVO);
                    }
                }
            } catch (Exception ex) {
                e.ImprimirError("CiudadesDAO;ocupacional.bdatos.CiudadesDAO.Listar();msg:" + ex.getMessage());
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
