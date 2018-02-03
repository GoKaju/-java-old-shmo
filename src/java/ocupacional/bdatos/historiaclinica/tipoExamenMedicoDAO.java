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
import java.util.logging.Level;
import java.util.logging.Logger;
import ocupacional.bdatos.PaisDAO;
import ocupacional.valueobjects.historiaclinica.tipoExamenMedicoVO;
import valeria.conexion.ConexionAplicacion;
import valeria.metodos.Cadenas;
import valeria.response.Mediador;

/**
 *
 * @author D4V3
 */
public class tipoExamenMedicoDAO {
   
    
    
    
    
      Cadenas c = new Cadenas();
    Mediador e = null;
    ConexionAplicacion conn = null;
    private boolean closed = true;

    public tipoExamenMedicoDAO(Mediador e) {
        try {
            this.e = e;
           
        } catch (Exception ex) {
            Logger.getLogger(tipoExamenMedicoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public tipoExamenMedicoDAO(Mediador e, ConexionAplicacion conn) {
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

    public ArrayList<tipoExamenMedicoVO> Listar() throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<tipoExamenMedicoVO> lista = null;
        boolean exito = false;
        this.getConexion();
        if (this.ConnEstado()) {
            try {
                 this.conn.getCon().setAutoCommit(false);
                StringBuilder sql = new StringBuilder();
                sql.append("SELECT  teme_id,  \n\t");
                sql.append("        teme_descripcion  \n\t");
                sql.append("FROM    tipoexamen_medico \n\t");
                sql.append("WHERE    teme_estado='ACTIVO' \n\t");
                sql.append("ORDER BY teme_descripcion \n\t");
                ps = this.conn.getCon().prepareStatement(sql.toString());
                rs = ps.executeQuery();
                if (rs.isBeforeFirst()) {
                    lista = new ArrayList<tipoExamenMedicoVO>();
            
                    while (rs.next()) {
                        tipoExamenMedicoVO x = new tipoExamenMedicoVO();
                        x.setTeme_id(rs.getString("teme_id"));
                        x.setTeme_descripcion(rs.getString("teme_descripcion"));
        
                        
                        lista.add(x);
                       
                    }
                }
            } catch (Exception ex) {
                e.ImprimirError("PaisDAO;ocupacional.bdatos.tipoExamenMedicoDAO.Listar();msg:" + ex.getMessage());
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
        public  tipoExamenMedicoVO Cargar( String id) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
           tipoExamenMedicoVO x = new tipoExamenMedicoVO();

        boolean exito = false;
        this.getConexion();
        if (this.ConnEstado()) {
            try {
                 this.conn.getCon().setAutoCommit(false);
                StringBuilder sql = new StringBuilder();
                sql.append("SELECT  teme_id,  \n\t");
                sql.append("        teme_descripcion  \n\t");
                sql.append("FROM    tipoexamen_medico \n\t");
                sql.append("WHERE    teme_estado='ACTIVO' \n\t");
                sql.append("and    teme_id=? \n\t");
                sql.append("ORDER BY teme_descripcion \n\t");
                ps = this.conn.getCon().prepareStatement(sql.toString());
                ps.setString(1, id);
                       
                rs = ps.executeQuery();
                if (rs.isBeforeFirst()) {
            
                    if (rs.next()) {
                               x.setTeme_id(rs.getString("teme_id"));
                        x.setTeme_descripcion(rs.getString("teme_descripcion"));
                       
                    }
                }
            } catch (Exception ex) {
                e.ImprimirError("PaisDAO;ocupacional.bdatos.tipoExamenMedicoDAO.Listar();msg:" + ex.getMessage());
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
        return x;
    }
    
}
