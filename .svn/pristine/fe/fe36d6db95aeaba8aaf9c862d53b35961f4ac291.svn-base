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
import ocupacional.valueobjects.JuridicasVO;
import valeria.conexion.ConexionAplicacion;
import valeria.metodos.Cadenas;
import valeria.response.Mediador;

/**
 *
 * @author VALERIA
 */
public class JuridicasDAO {

    Cadenas c = new Cadenas();
    Mediador e = null;
    ConexionAplicacion conn = null;
    private boolean closed = true;

    public JuridicasDAO(Mediador e) {
        this.e = e;
    }

    public JuridicasDAO(Mediador e, ConexionAplicacion conn) {
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

    public boolean Insertar(JuridicasVO JuridicasVO) throws Exception {
        PreparedStatement ps = null;
        ResultSet keyRS = null;
        boolean exito = false;
        this.getConexion();
        if (this.ConnEstado()) {
            try {
                this.conn.getCon().setAutoCommit(false);
                StringBuilder sql = new StringBuilder();
                sql.append("INSERT INTO javap.juridicas(pege_id,juri_razonsocial, juri_representante, juri_fechacambio, juri_registradopor, acec_id) VALUES (?,?,?,?,?,?) ");
                ps = this.conn.getCon().prepareStatement(sql.toString());
                int a = 1;
                ps.setString(a++, JuridicasVO.getPege_id());
                ps.setString(a++, JuridicasVO.getJuri_razonsocial());
                ps.setString(a++, JuridicasVO.getJuri_representante());
                ps.setTimestamp(a++, JuridicasVO.getJuri_fechacambio());
                ps.setString(a++, JuridicasVO.getJuri_registradopor());
                ps.setString(a++, JuridicasVO.getAcec_id());
                ps.executeUpdate();
//                keyRS = ps.getGeneratedKeys();
//                if (keyRS.next()) {
//                    JuridicasVO.setPege_id(keyRS.getString(1));
//                }
                if (JuridicasVO.getPege_id() != null) {
                    exito = true;
                }
            } catch (Exception ex) {
                e.ImprimirError("JuridicasDAO;ocupacional.bdatos.JuridicasDAO.Insertar();msg:" + ex.getMessage());
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

    public boolean Actualizar(JuridicasVO JuridicasVO) throws Exception {
        PreparedStatement ps = null;
        ResultSet keyRS = null;
        boolean exito = false;
        this.getConexion();
        if (this.ConnEstado()) {
            try {
                this.conn.getCon().setAutoCommit(false);
                StringBuilder sql = new StringBuilder();
                sql.append("UPDATE  javap.Juridicas SET  \n\t");
                sql.append("        juri_razonsocial = ?,  \n\t");
                sql.append("        juri_representante = ?,  \n\t");
                sql.append("        acec_id = ?,  \n\t");
                sql.append("        juri_fechacambio = ?,  \n\t");
                sql.append("        juri_registradopor = ?  \n\t");
                sql.append("WHERE   pege_id = ? \n\t");
                ps = this.conn.getCon().prepareStatement(sql.toString());
                int a = 1;
                ps.setString(a++, JuridicasVO.getJuri_razonsocial());
                ps.setString(a++, JuridicasVO.getJuri_representante());
                ps.setString(a++, JuridicasVO.getAcec_id());
                ps.setTimestamp(a++, JuridicasVO.getJuri_fechacambio());
                ps.setString(a++, JuridicasVO.getJuri_registradopor());
                ps.setString(a++, JuridicasVO.getPege_id());
                ps.executeUpdate();
                exito = true;
            } catch (Exception ex) {
                e.ImprimirError("JuridicasDAO;ocupacional.bdatos.JuridicasDAO.Actualizar();msg:" + ex.getMessage());
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

    public boolean Eliminar(JuridicasVO JuridicasVO) throws Exception {
        PreparedStatement ps = null;
        ResultSet keyRS = null;
        boolean exito = false;
        this.getConexion();
        if (this.ConnEstado()) {
            try {
                this.conn.getCon().setAutoCommit(false);
                StringBuilder sql = new StringBuilder();
                sql.append("DELETE FROM javap.Juridicas   \n\t");
                sql.append("WHERE   pege_id = ? \n\t");
                ps = this.conn.getCon().prepareStatement(sql.toString());
                int a = 1;
                ps.setString(a++, JuridicasVO.getPege_id());
                ps.executeUpdate();
                exito = true;
            } catch (Exception ex) {
                e.ImprimirError("JuridicasDAO;ocupacional.bdatos.JuridicasDAO.Eliminar();msg:" + ex.getMessage());
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

        ArrayList<JuridicasVO> lista = null;
        boolean exito = false;
        this.getConexion();
        if (this.ConnEstado()) {
            try {
                this.conn.getCon().setAutoCommit(false);
                StringBuilder sql = new StringBuilder();
                sql.append("SELECT  pege_id,  \n\t");
                sql.append("        juri_razonsocial,  \n\t");
                sql.append("        juri_representante,  \n\t");
                sql.append("        juri_fechacambio,  \n\t");
                sql.append("        juri_registradopor  \n\t");
                sql.append("        acec_id  \n\t");
                sql.append("FROM    javap.Juridicas \n\t");
                sql.append("ORDER BY juri_razonsocial \n\t");
                ps = this.conn.getCon().prepareStatement(sql.toString());
                rs = ps.executeQuery();
                if (rs.isBeforeFirst()) {
                    lista = new ArrayList<JuridicasVO>();
                    while (rs.next()) {
                        JuridicasVO JuridicasVO = new JuridicasVO();
                        JuridicasVO.setPege_id(rs.getString("pege_id"));
                        JuridicasVO.setJuri_razonsocial(rs.getString("juri_razonsocial"));
                        JuridicasVO.setJuri_representante(rs.getString("juri_representante"));
                        JuridicasVO.setAcec_id(rs.getString("acec_id"));
                        lista.add(JuridicasVO);
                    }
                }
            } catch (Exception ex) {
                e.ImprimirError("JuridicasDAO;ocupacional.bdatos.JuridicasDAO.Listar();msg:" + ex.getMessage());
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

    public JuridicasVO Cargar(String id) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        JuridicasVO JuridicasVO = new JuridicasVO();

        boolean exito = false;
        this.getConexion();
        this.conn.getCon().setAutoCommit(false);
        if (this.ConnEstado()) {
            try {
                this.conn.getCon().setAutoCommit(false);
                StringBuilder sql = new StringBuilder();
                sql.append("SELECT  * \n\t");
                sql.append("FROM    javap.Juridicas \n\t");
                sql.append("WHERE pege_id=? \n\t");
                ps = this.conn.getCon().prepareStatement(sql.toString());
                ps.setString(1, id);
                rs = ps.executeQuery();
                if (rs.isBeforeFirst()) {
                    while (rs.next()) {
                        JuridicasVO.setPege_id(rs.getString("pege_id"));
                        JuridicasVO.setJuri_razonsocial(rs.getString("juri_razonsocial"));
                        JuridicasVO.setJuri_representante(rs.getString("juri_representante"));
                        JuridicasVO.setAcec_id(rs.getString("acec_id"));
                    }
                }
            } catch (Exception ex) {
                e.ImprimirError("JuridicasDAO;ocupacional.bdatos.JuridicasDAO.Cargar();msg:" + ex.getMessage());
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
        return JuridicasVO;
    }
}
