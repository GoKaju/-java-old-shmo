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
import ocupacional.valueobjects.TipoDocumentoVO;
import valeria.conexion.ConexionAplicacion;
import valeria.metodos.Cadenas;
import valeria.response.Mediador;

/**
 *
 * @author D4V3
 */
public class TipoDocumentoDAO {
    
    
    
      Cadenas c = new Cadenas();
    Mediador e = null;
    ConexionAplicacion conn = null;
    private boolean closed = true;

    public TipoDocumentoDAO(Mediador e) {
        try {
            this.e = e;
           
        } catch (Exception ex) {
            Logger.getLogger(PaisDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public TipoDocumentoDAO(Mediador e, ConexionAplicacion conn) {
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

    public ArrayList<TipoDocumentoVO> Listar() throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<TipoDocumentoVO> lista = null;
        boolean exito = false;
        this.getConexion();
        if (this.ConnEstado()) {
            try {
                 this.conn.getCon().setAutoCommit(false);
                StringBuilder sql = new StringBuilder();
                sql.append("SELECT  tido_id,  \n\t");
                sql.append("        tido_descripcion  \n\t");
                sql.append("FROM   tipodocumento  \n\t");
                sql.append("ORDER BY tido_descripcion \n\t");
                ps = this.conn.getCon().prepareStatement(sql.toString());
                rs = ps.executeQuery();
                if (rs.isBeforeFirst()) {
                    lista = new ArrayList<TipoDocumentoVO>();
            
                    while (rs.next()) {
                        TipoDocumentoVO x = new TipoDocumentoVO();
                        x.setTipo_id(rs.getString("tido_id"));
                        x.setTipo_descripcion(rs.getString("tido_descripcion"));
                        
                        lista.add(x);
                       
                    }
                }
            } catch (Exception ex) {
                e.ImprimirError("PaisDAO;ocupacional.bdatos.TipoDocumentoDAO.Listar();msg:" + ex.getMessage());
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
        public  TipoDocumentoVO Cargar( String id) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
           TipoDocumentoVO x = new TipoDocumentoVO();

        boolean exito = false;
        this.getConexion();
        if (this.ConnEstado()) {
            try {
                 this.conn.getCon().setAutoCommit(false);
                StringBuilder sql = new StringBuilder();
                sql.append("SELECT  tido_id,  \n\t");
                sql.append("        tido_descripcion  \n\t");
                sql.append("FROM   tipodocumento  \n\t");
                sql.append("WHERE   tido_id=?  \n\t");
                
                sql.append(" ORDER BY tido_descripcion \n\t");
                ps = this.conn.getCon().prepareStatement(sql.toString());
                ps.setString(1, id);
                       
                rs = ps.executeQuery();
                if (rs.isBeforeFirst()) {
            
                    while (rs.next()) {
                        x.setTipo_id(rs.getString("tido_id"));
                        x.setTipo_descripcion(rs.getString("tido_descripcion"));
                        System.out.println(x.getTipo_descripcion());
                      
                       
                    }
                }
            } catch (Exception ex) {
                e.ImprimirError("PaisDAO;ocupacional.bdatos.TipoDocumentoDAO.Listar();msg:" + ex.getMessage());
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
