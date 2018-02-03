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
import java.util.logging.Level;
import java.util.logging.Logger;
import ocupacional.valueobjects.PaisVO;
import valeria.conexion.ConexionAplicacion;
import valeria.metodos.Cadenas;
import valeria.response.Mediador;

/**
 *
 * @author VALERIA
 */
public class PaisDAO {

    Cadenas c = new Cadenas();
    Mediador e = null;
    ConexionAplicacion conn = null;
    private boolean closed = true;

    public PaisDAO(Mediador e) {
        try {
            this.e = e;
           
        } catch (Exception ex) {
            Logger.getLogger(PaisDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public PaisDAO(Mediador e, ConexionAplicacion conn) {
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

    public boolean Insertar(PaisVO PaisVO) throws Exception {
        PreparedStatement ps = null;
        ResultSet keyRS = null;
        boolean exito = false;
        this.getConexion();
        if (this.ConnEstado()) {
            try {
                this.conn.getCon().setAutoCommit(false);
                StringBuffer sql = new StringBuffer();
                sql.append("INSERT INTO javap.PaisVO(pais_nombre, , pais_fechacambio, pais_registradopor, pais_codigo) VALUES (?,?,?,?) ");
                ps = this.conn.getCon().prepareStatement(sql.toString(), PreparedStatement.RETURN_GENERATED_KEYS);
                int a = 1;
                ps.setString(a++, PaisVO.getPais_nombre());
                ps.setTimestamp(a++, PaisVO.getPais_fechacambio());
                ps.setString(a++, PaisVO.getPais_registradopor());
                ps.setString(a++, PaisVO.getPais_codigo());
                
                ps.executeUpdate();
                keyRS = ps.getGeneratedKeys();
                if (keyRS.next()) {
                    PaisVO.setPais_id(keyRS.getString(1));
                }
                if (PaisVO.getPais_id() != null) {
                    exito = true;
                }
            } catch (Exception ex) {
                e.ImprimirError("PaisDAO;ocupacional.bdatos.PaisDAO.Insertar();msg:" + ex.getMessage());
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

    public boolean Actualizar(PaisVO PaisVO) throws Exception {
        PreparedStatement ps = null;
        ResultSet keyRS = null;
        boolean exito = false;
        this.getConexion();
        if (this.ConnEstado()) {
            try {
                this.conn.getCon().setAutoCommit(false);
                StringBuffer sql = new StringBuffer();
                sql.append("UPDATE  javap.Pais SET  \n\t");
                sql.append("        Pais_nombre = ?,  \n\t");
                sql.append("        Pais_fechacambio = ?,  \n\t");
                sql.append("        Pais_registradopor = ?  \n\t");
                sql.append("        Pais_codigo = ?,  \n\t");
                sql.append("WHERE   Pais_id = ? \n\t");
                ps = this.conn.getCon().prepareStatement(sql.toString());
                int a = 1;
                ps.setString(a++, PaisVO.getPais_nombre());
                ps.setTimestamp(a++, PaisVO.getPais_fechacambio());
                ps.setString(a++, PaisVO.getPais_registradopor());
                ps.setString(a++, PaisVO.getPais_codigo());
                ps.setString(a++, PaisVO.getPais_id());
                ps.executeUpdate();
                exito = true;
            } catch (Exception ex) {
                e.ImprimirError("PaisDAO;ocupacional.bdatos.PaisDAO.Actualizar();msg:" + ex.getMessage());
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

    public boolean Eliminar(PaisVO PaisVO) throws Exception {
        PreparedStatement ps = null;
        ResultSet keyRS = null;
        boolean exito = false;
        this.getConexion();
        if (this.ConnEstado()) {
            try {
                this.conn.getCon().setAutoCommit(false);
                StringBuffer sql = new StringBuffer();
                sql.append("DELETE FROM javap.paisVO   \n\t");
                sql.append("WHERE   Pais_id = ? \n\t");
                ps = this.conn.getCon().prepareStatement(sql.toString());
                int a = 1;
                ps.setString(a++, PaisVO.getPais_id());
                ps.executeUpdate();
                exito = true;
            } catch (Exception ex) {
                e.ImprimirError("PaisDAO;ocupacional.bdatos.PaisDAO.Eliminar();msg:" + ex.getMessage());
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

    public ArrayList<PaisVO> Listar() throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<PaisVO> lista = null;
        boolean exito = false;
        this.getConexion();
        if (this.ConnEstado()) {
            try {
                 this.conn.getCon().setAutoCommit(false);
                StringBuilder sql = new StringBuilder();
                sql.append("SELECT  id,  \n\t");
                sql.append("        nombre,  \n\t");
                sql.append("       iso  \n\t");
                sql.append("FROM    paises \n\t");
                sql.append("ORDER BY nombre \n\t");
                ps = this.conn.getCon().prepareStatement(sql.toString());
                rs = ps.executeQuery();
                if (rs.isBeforeFirst()) {
                    lista = new ArrayList<PaisVO>();
            
                    while (rs.next()) {
                        PaisVO PaisVO = new PaisVO();
                        PaisVO.setPais_id(rs.getString("id"));
                        PaisVO.setPais_nombre(rs.getString("nombre"));
                        PaisVO.setPais_codigo(rs.getString("iso"));
                        lista.add(PaisVO);
                       
                    }
                }
            } catch (Exception ex) {
                e.ImprimirError("PaisDAO;ocupacional.bdatos.PaisDAO.Listar();msg:" + ex.getMessage());
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
                    ex.printStackTrace();
                }
            }
        }
        return lista;
    }

}
