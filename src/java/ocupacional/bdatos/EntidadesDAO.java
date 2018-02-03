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
import ocupacional.valueobjects.EntidadesVO;
import valeria.conexion.ConexionAplicacion;
import valeria.metodos.Cadenas;
import valeria.response.Mediador;

/**
 *
 * @author VALERIA
 */
public class EntidadesDAO {

    Cadenas c = new Cadenas();
    Mediador e = null;
    ConexionAplicacion conn = null;
    private boolean closed = true;

    public EntidadesDAO(Mediador e) {
        this.e = e;
    }

    public EntidadesDAO(Mediador e, ConexionAplicacion conn) {
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

    public boolean Insertar(EntidadesVO EntidadesVO) throws Exception {
        PreparedStatement ps = null;
        ResultSet keyRS = null;
        boolean exito = false;
        this.getConexion();
        if (this.ConnEstado()) {
            try {
                this.conn.getCon().setAutoCommit(false);
                StringBuffer sql = new StringBuffer();
                sql.append("INSERT INTO javap.Entidades(enti_tipo, enti_nombre, enti_fechacambio, enti_registradopor) VALUES (?,?,?,?) ");
                ps = this.conn.getCon().prepareStatement(sql.toString(), PreparedStatement.RETURN_GENERATED_KEYS);
                int a = 1;
                ps.setString(a++, EntidadesVO.getEnti_tipo());
                ps.setString(a++, EntidadesVO.getEnti_nombre());
                ps.setTimestamp(a++, EntidadesVO.getEnti_fechacambio());
                ps.setString(a++, EntidadesVO.getEnti_registradopor());
                ps.executeUpdate();
                keyRS = ps.getGeneratedKeys();
                if (keyRS.next()) {
                    EntidadesVO.setEnti_id(keyRS.getString(1));
                }
                if (EntidadesVO.getEnti_id() != null) {
                    exito = true;
                }
            } catch (Exception ex) {
                e.ImprimirError("EntidadesDAO;ocupacional.bdatos.EntidadesDAO.Insertar();msg:" + ex.getMessage());
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

    public boolean Actualizar(EntidadesVO EntidadesVO) throws Exception {
        PreparedStatement ps = null;
        ResultSet keyRS = null;
        boolean exito = false;
        this.getConexion();
        if (this.ConnEstado()) {
            try {
                this.conn.getCon().setAutoCommit(false);
                StringBuffer sql = new StringBuffer();
                sql.append("UPDATE  javap.Entidades SET  \n\t");
                sql.append("        enti_Tipo = ?,  \n\t");
                sql.append("        enti_nombre = ?,  \n\t");
                sql.append("        enti_estado = ?,  \n\t");
                
                sql.append("        enti_fechacambio = ?,  \n\t");
                sql.append("        enti_registradopor = ?  \n\t");
                sql.append("WHERE   enti_id = ? \n\t");
                ps = this.conn.getCon().prepareStatement(sql.toString());
                int a = 1;
                ps.setString(a++, EntidadesVO.getEnti_tipo());
                ps.setString(a++, EntidadesVO.getEnti_nombre());
                ps.setString(a++, EntidadesVO.getEnti_estado());
                ps.setTimestamp(a++, EntidadesVO.getEnti_fechacambio());
                ps.setString(a++, EntidadesVO.getEnti_registradopor());
                ps.setString(a++, EntidadesVO.getEnti_id());
                ps.executeUpdate();
                exito = true;
                
                System.out.println("entidades actualizar");
            } catch (Exception ex) {
                ex.printStackTrace();
                e.ImprimirError("EntidadesDAO;ocupacional.bdatos.EntidadesDAO.Actualizar();msg:" + ex.getMessage());
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

    public boolean Eliminar(EntidadesVO EntidadesVO) throws Exception {
        PreparedStatement ps = null;
        ResultSet keyRS = null;
        boolean exito = false;
        this.getConexion();
        if (this.ConnEstado()) {
            try {
                this.conn.getCon().setAutoCommit(false);
                StringBuffer sql = new StringBuffer();
                sql.append("DELETE FROM javap.Entidades   \n\t");
                sql.append("WHERE   Enti_id = ? \n\t");
                ps = this.conn.getCon().prepareStatement(sql.toString());
                int a = 1;
                ps.setString(a++, EntidadesVO.getEnti_id());
                ps.executeUpdate();
                exito = true;
            } catch (Exception ex) {
                e.ImprimirError("EntidadesDAO;ocupacional.bdatos.EntidadesDAO.Eliminar();msg:" + ex.getMessage());
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
        ArrayList<EntidadesVO> lista = null;
        boolean exito = false;
        this.getConexion();
        if (this.ConnEstado()) {
            try {
                this.conn.getCon().setAutoCommit(false);
                StringBuilder sql = new StringBuilder();
                sql.append("SELECT  *  \n\t");
                sql.append("FROM    javap.entidades \n\t");
                sql.append("ORDER BY enti_nombre \n\t");
                ps = this.conn.getCon().prepareStatement(sql.toString());
                System.out.println("sql:::: "+sql.toString());
                rs = ps.executeQuery();
                if (rs.isBeforeFirst()) {
                System.out.println("rs:::: "+rs.toString());
                    lista = new ArrayList<EntidadesVO>();
                    while (rs.next()) {
                        EntidadesVO EntidadesVO = new EntidadesVO();
                        EntidadesVO.setEnti_id(rs.getString("enti_id"));
                        EntidadesVO.setEnti_tipo(rs.getString("enti_tipo"));
                        EntidadesVO.setEnti_nombre(rs.getString("enti_nombre"));
                        EntidadesVO.setEnti_estado(rs.getString("enti_estado"));
                        lista.add(EntidadesVO);
                    }
                }
            } catch (Exception ex) {
                
                ex.printStackTrace();
                e.ImprimirError("EntidadesDAO;ocupacional.bdatos.EntidadesDAO.Listar();msg:" + ex.getMessage());
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
