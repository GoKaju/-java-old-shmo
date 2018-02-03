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
import ocupacional.valueobjects.ActividadEconomicaVO;
import valeria.conexion.ConexionAplicacion;
import valeria.metodos.Cadenas;
import valeria.response.Mediador;

/**
 *
 * @author VALERIA
 */
public class ActividadEconomicaDAO {

    Cadenas c = new Cadenas();
    Mediador e = null;
    ConexionAplicacion conn = null;
    private boolean closed = true;

    public ActividadEconomicaDAO(Mediador e) {
        this.e = e;
    }

    public ActividadEconomicaDAO(Mediador e, ConexionAplicacion conn) {
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

    public boolean Insertar(ActividadEconomicaVO ActividadEconomicaVO) throws Exception {
        PreparedStatement ps = null;
        ResultSet keyRS = null;
        boolean exito = false;
        this.getConexion();
        if (this.ConnEstado()) {
            try {
                this.conn.getCon().setAutoCommit(false);
                StringBuffer sql = new StringBuffer();
                sql.append("INSERT INTO javap.actividadeconomica(acec_descripcion, acec_codigo, acec_fechacambio, acec_registradopor) VALUES (?,?,?,?) ");
                ps = this.conn.getCon().prepareStatement(sql.toString(), PreparedStatement.RETURN_GENERATED_KEYS);
                int a = 1;
                ps.setString(a++, ActividadEconomicaVO.getAcec_descripcion());
                ps.setString(a++, ActividadEconomicaVO.getAcec_codigo());
                ps.setTimestamp(a++, ActividadEconomicaVO.getAcec_fechacambio());
                ps.setString(a++, ActividadEconomicaVO.getAcec_registradopor());
                ps.executeUpdate();
                keyRS = ps.getGeneratedKeys();
                if (keyRS.next()) {
                    ActividadEconomicaVO.setAcec_id(keyRS.getString(1));
                }
                if (ActividadEconomicaVO.getAcec_id() != null) {
                    exito = true;
                }
            } catch (Exception ex) {
                e.ImprimirError("ActividadEconomicaDAO;ocupacional.bdatos.ActividadEconomicaDAO.Insertar();msg:" + ex.getMessage());
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

    public boolean Actualizar(ActividadEconomicaVO ActividadEconomicaVO) throws Exception {
        PreparedStatement ps = null;
        ResultSet keyRS = null;
        boolean exito = false;
        this.getConexion();
        if (this.ConnEstado()) {
            try {
                this.conn.getCon().setAutoCommit(false);
                StringBuffer sql = new StringBuffer();
                sql.append("UPDATE  javap.actividadeconomica SET  \n\t");
                sql.append("        acec_descripcion = ?,  \n\t");
                sql.append("        acec_codigo = ?,  \n\t");
                sql.append("        acec_fechacambio = ?,  \n\t");
                sql.append("        acec_registradopor = ?  \n\t");
                sql.append("WHERE   acec_id = ? \n\t");
                ps = this.conn.getCon().prepareStatement(sql.toString());
                int a = 1;
                ps.setString(a++, ActividadEconomicaVO.getAcec_descripcion());
                ps.setString(a++, ActividadEconomicaVO.getAcec_codigo());
                ps.setTimestamp(a++, ActividadEconomicaVO.getAcec_fechacambio());
                ps.setString(a++, ActividadEconomicaVO.getAcec_registradopor());
                ps.setString(a++, ActividadEconomicaVO.getAcec_id());
                ps.executeUpdate();
                exito = true;
            } catch (Exception ex) {
                e.ImprimirError("ActividadEconomicaDAO;ocupacional.bdatos.ActividadEconomicaDAO.Actualizar();msg:" + ex.getMessage());
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

    public boolean Eliminar(ActividadEconomicaVO ActividadEconomicaVO) throws Exception {
        PreparedStatement ps = null;
        ResultSet keyRS = null;
        boolean exito = false;
        this.getConexion();
        if (this.ConnEstado()) {
            try {
                this.conn.getCon().setAutoCommit(false);
                StringBuffer sql = new StringBuffer();
                sql.append("DELETE FROM javap.actividadeconomica   \n\t");
                sql.append("WHERE   acec_id = ? \n\t");
                ps = this.conn.getCon().prepareStatement(sql.toString());
                int a = 1;
                ps.setString(a++, ActividadEconomicaVO.getAcec_id());
                ps.executeUpdate();
                exito = true;
            } catch (Exception ex) {
                e.ImprimirError("ActividadEconomicaDAO;ocupacional.bdatos.ActividadEconomicaDAO.Eliminar();msg:" + ex.getMessage());
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

    public ArrayList<ActividadEconomicaVO> Listar() throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<ActividadEconomicaVO> Lista = null;
        boolean exito = false;
        this.getConexion();
        if (this.ConnEstado()) {
            try {
                
                this.conn.getCon().setAutoCommit(false);
                StringBuffer sql = new StringBuffer();
                sql.append("SELECT  acec_descripcion,  \n\t");
                sql.append("        acec_id,  \n\t");
                sql.append("        acec_codigo,  \n\t");
                sql.append("        acec_fechacambio,  \n\t");
                sql.append("        acec_registradopor  \n\t");
                sql.append("FROM    javap.actividadeconomica \n\t");
                sql.append("ORDER BY acec_descripcion \n\t");
                ps = this.conn.getCon().prepareStatement(sql.toString());
                rs = ps.executeQuery();
                Lista = new ArrayList<ActividadEconomicaVO>();
                if (rs.isBeforeFirst()) {
                    while (rs.next()) {
                        ActividadEconomicaVO ActividadEconomicaVO = new ActividadEconomicaVO();
                        ActividadEconomicaVO.setAcec_id(rs.getString("acec_id"));
                        ActividadEconomicaVO.setAcec_codigo(rs.getString("acec_codigo"));
                        ActividadEconomicaVO.setAcec_descripcion(rs.getString("acec_descripcion"));
                        ActividadEconomicaVO.setAcec_fechacambio(rs.getTimestamp("acec_fechacambio"));
                        ActividadEconomicaVO.setAcec_registradopor(rs.getString("acec_registradopor"));
                        Lista.add(ActividadEconomicaVO);
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                e.ImprimirError("ActividadEconomicaDAO;ocupacional.bdatos.ActividadEconomicaDAO.Listar();msg:" + ex.getMessage());
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
        return Lista;
    }

}
