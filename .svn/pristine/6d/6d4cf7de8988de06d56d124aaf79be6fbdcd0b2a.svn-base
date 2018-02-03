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
import ocupacional.valueobjects.EstadoCivilVO;
import valeria.conexion.ConexionAplicacion;
import valeria.metodos.Cadenas;
import valeria.response.Mediador;

/**
 *
 * @author VALERIA
 */
public class EstadoCivilDAO {

    Cadenas c = new Cadenas();
    Mediador e = null;
    ConexionAplicacion conn = null;
    private boolean closed = true;

    public EstadoCivilDAO(Mediador e) {
        this.e = e;
    }

    public EstadoCivilDAO(Mediador e, ConexionAplicacion conn) {
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
    
    public boolean Insertar(EstadoCivilVO EstadoCivilVO) throws Exception {
        PreparedStatement ps = null;
        ResultSet keyRS = null;
        boolean exito = false;
        this.getConexion();
        if (this.ConnEstado()) {
            try {
                this.conn.getCon().setAutoCommit(false);
                StringBuffer sql = new StringBuffer();
                sql.append("INSERT INTO javap.EstadoCivil(esci_descripcion, esci_fechacambio, esci_registradopor) VALUES (?,?,?) ");
                ps = this.conn.getCon().prepareStatement(sql.toString(), PreparedStatement.RETURN_GENERATED_KEYS);
                int a = 1;
                ps.setString(a++, EstadoCivilVO.getEsci_descripcion());
                ps.setTimestamp(a++, EstadoCivilVO.getEsci_fechacambio());
                ps.setString(a++, EstadoCivilVO.getEsci_registradopor());
                ps.executeUpdate();
                keyRS = ps.getGeneratedKeys();
                if (keyRS.next()) {
                    EstadoCivilVO.setEsci_id(keyRS.getString(1));
                }
                if (EstadoCivilVO.getEsci_id() != null) {
                    exito = true;
                }
            } catch (Exception ex) {
                e.ImprimirError("EstadoCivilDAO;ocupacional.bdatos.EstadoCivilDAO.Insertar();msg:" + ex.getMessage());
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

    public boolean Actualizar(EstadoCivilVO EstadoCivilVO) throws Exception {
        PreparedStatement ps = null;
        ResultSet keyRS = null;
        boolean exito = false;
        this.getConexion();
        if (this.ConnEstado()) {
            try {
                this.conn.getCon().setAutoCommit(false);
                StringBuffer sql = new StringBuffer();
                sql.append("UPDATE  javap.EstadoCivil SET  \n\t");
                sql.append("        esci_descripcion = ?,  \n\t");
                sql.append("        esci_fechacambio = ?,  \n\t");
                sql.append("        esci_registradopor = ?  \n\t");
                sql.append("WHERE   esci_id = ? \n\t");
                ps = this.conn.getCon().prepareStatement(sql.toString());
                int a = 1;
                ps.setString(a++, EstadoCivilVO.getEsci_descripcion());
                ps.setTimestamp(a++, EstadoCivilVO.getEsci_fechacambio());
                ps.setString(a++, EstadoCivilVO.getEsci_registradopor());
                ps.setString(a++, EstadoCivilVO.getEsci_id());
                ps.executeUpdate();
                exito = true;
            } catch (Exception ex) {
                e.ImprimirError("EstadoCivilDAO;ocupacional.bdatos.EstadoCivilDAO.Actualizar();msg:" + ex.getMessage());
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

    public boolean Eliminar(EstadoCivilVO EstadoCivilVO) throws Exception {
        PreparedStatement ps = null;
        ResultSet keyRS = null;
        boolean exito = false;
        this.getConexion();
        if (this.ConnEstado()) {
            try {
                this.conn.getCon().setAutoCommit(false);
                StringBuffer sql = new StringBuffer();
                sql.append("DELETE FROM javap.EstadoCivil   \n\t");
                sql.append("WHERE   acec_id = ? \n\t");
                ps = this.conn.getCon().prepareStatement(sql.toString());
                int a = 1;
                ps.setString(a++, EstadoCivilVO.getEsci_id());
                ps.executeUpdate();
                exito = true;
            } catch (Exception ex) {
                e.ImprimirError("EstadoCivilDAO;ocupacional.bdatos.EstadoCivilDAO.Eliminar();msg:" + ex.getMessage());
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
        ArrayList<EstadoCivilVO> lista = null;
        boolean exito = false;
        this.getConexion();
        if (this.ConnEstado()) {
            try {
                StringBuffer sql = new StringBuffer();
                sql.append("SELECT  esci_descripcion,  \n\t");
                sql.append("        esci_fechacambio,  \n\t");
                sql.append("        esci_registradopor  \n\t");
                sql.append("FROM    javap.EstadoCivil \n\t");
                sql.append("ORDER BY esci_id \n\t");
                ps = this.conn.getCon().prepareStatement(sql.toString());
                rs = ps.executeQuery();
                if (rs.isBeforeFirst()) {
                    lista = new ArrayList<EstadoCivilVO>();
                    while (rs.next()) {
                        EstadoCivilVO EstadoCivilVO = new EstadoCivilVO();
                        EstadoCivilVO.setEsci_id(rs.getString("esci_descripcion"));
                        EstadoCivilVO.setEsci_descripcion(rs.getString("esci_id"));
                        lista.add(EstadoCivilVO);
                    }
                }
            } catch (Exception ex) {
                e.ImprimirError("EstadoCivilDAO;ocupacional.bdatos.EstadoCivilDAO.Listar();msg:" + ex.getMessage());
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
