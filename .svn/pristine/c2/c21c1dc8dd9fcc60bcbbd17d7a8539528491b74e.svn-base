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
import java.util.logging.Level;
import java.util.logging.Logger;
import ocupacional.valueobjects.facturacion.ServiciosExamenesVO;
import valeria.conexion.ConexionAplicacion;
import valeria.metodos.Cadenas;
import valeria.response.Mediador;

/**
 *
 * @author D4V3
 */
public class ServiciosExamenesDAO {

    Cadenas c = new Cadenas();
    Mediador e = null;
    ConexionAplicacion conn = null;
    private boolean closed = true;

    public ServiciosExamenesDAO(Mediador e) {
        this.e = e;
    }

    public ServiciosExamenesDAO(Mediador e, ConexionAplicacion conn) {
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

    public boolean Insertar(ServiciosExamenesVO ServiciosExamenesVO) throws Exception {
        PreparedStatement ps = null;
        ResultSet keyRS = null;
        boolean exito = false;
        this.getConexion();
        if (this.ConnEstado()) {
            try {
                this.conn.getCon().setAutoCommit(false);
                StringBuilder sql = new StringBuilder();

//                ***cambio dao***
                sql.append("INSERT INTO `servicios_examenes`( `exam_id`, `serv_id`, `seex_registradopor`, `seex_fechacambio`) VALUES (?,?,?,?)");
                ps = this.conn.getCon().prepareStatement(sql.toString(), PreparedStatement.RETURN_GENERATED_KEYS);
                int a = 1;
                ps.setString(a++, ServiciosExamenesVO.getExam_id());
                ps.setString(a++, ServiciosExamenesVO.getServ_id());
                ps.setString(a++, ServiciosExamenesVO.getSeex_registradopor());
                ps.setTimestamp(a++, ServiciosExamenesVO.getSeex_fechacambio());
                ps.executeUpdate();
                keyRS = ps.getGeneratedKeys();
                if (keyRS.next()) {
                    ServiciosExamenesVO.setSeex_id(keyRS.getString(1));
                }
                if (ServiciosExamenesVO.getSeex_id() != null) {

//                ***cambio dao***
                    exito = true;
                }
            } catch (Exception ex) {
                e.ImprimirError("ServiciosExamenesDAO;ocupacional.bdatos.ServiciosExamenesDAO.Insertar();msg:" + ex.getMessage());
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

    public boolean Actualizar(ServiciosExamenesVO ServiciosExamenesVO) throws Exception {
        PreparedStatement ps = null;
        ResultSet keyRS = null;
        boolean exito = false;
        this.getConexion();
        if (this.ConnEstado()) {
            try {
                this.conn.getCon().setAutoCommit(false);
                StringBuilder sql = new StringBuilder();
//                ***cambio dao***
                sql.append("UPDATE `servicios_examenes` \n"
                        + "SET\n"
                        + "`exam_id`=?,\n"
                        + "`serv_id`=?,\n"
                        + "`seex_registradopor`=?,\n"
                        + "`seex_fechacambio`=?\n"
                        + " WHERE  `seex_id`=?");
                ps = this.conn.getCon().prepareStatement(sql.toString());
                int a = 1;
                ps.setString(a++, ServiciosExamenesVO.getExam_id());
                ps.setString(a++, ServiciosExamenesVO.getServ_id());
                ps.setString(a++, ServiciosExamenesVO.getSeex_registradopor());
                ps.setTimestamp(a++, ServiciosExamenesVO.getSeex_fechacambio());
                ps.setString(a++, ServiciosExamenesVO.getSeex_id());

//                ***cambio dao***
                ps.executeUpdate();
                exito = true;
            } catch (Exception ex) {
                e.ImprimirError("ServiciosExamenesDAO;ocupacional.bdatos.ServiciosExamenesDAO.Actualizar();msg:"
                        + ex.getMessage());
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

    public boolean Eliminar(ServiciosExamenesVO ServiciosExamenesVO) throws Exception {
        PreparedStatement ps = null;
        ResultSet keyRS = null;
        boolean exito = false;
        this.getConexion();
        if (this.ConnEstado()) {
            try {
                this.conn.getCon().setAutoCommit(false);
                StringBuilder sql = new StringBuilder();
                //                ***cambio dao***
                sql.append("DELETE FROM javap.servicios_examenes   \n\t");
                sql.append("WHERE   seex_id = ? \n\t");
                ps = this.conn.getCon().prepareStatement(sql.toString());
                int a = 1;
                ps.setString(a++, ServiciosExamenesVO.getSeex_id());
                //                ***cambio dao***
                ps.executeUpdate();
                exito = true;
            } catch (Exception ex) {
                e.ImprimirError("ServiciosExamenesDAO;ocupacional.bdatos.ServiciosExamenesDAO.Eliminar();msg:" + ex.getMessage());
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
    public boolean EliminarTodos(String id){
    boolean exito= false;
        try {
            ArrayList<ServiciosExamenesVO> lista= this.Listar(id);
            for (ServiciosExamenesVO s : lista) {
                this.Eliminar(s);
                
            }
            exito= true;        
            
        } catch (Exception ex) {
            Logger.getLogger(ServiciosExamenesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    return exito;
    
    }

    public ArrayList<ServiciosExamenesVO> Listar(String idS) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<ServiciosExamenesVO> lista = null;
                    lista = new ArrayList<ServiciosExamenesVO>();
        boolean exito = false;
        this.getConexion();
        if (this.ConnEstado()) {
            try {
                StringBuilder sql = new StringBuilder();
                this.conn.getCon().setAutoCommit(false);
                //                ***cambio dao***
                sql.append("SELECT * FROM `servicios_examenes` WHERE serv_id="+idS);
                ps = this.conn.getCon().prepareStatement(sql.toString());
                rs = ps.executeQuery();
                if (rs.isBeforeFirst()) {
                    while (rs.next()) {
                        ServiciosExamenesVO ServiciosExamenesVO = new ServiciosExamenesVO();

                        ServiciosExamenesVO.setSeex_id(rs.getString("seex_id"));
                        ServiciosExamenesVO.setExam_id(rs.getString("exam_id"));
                        ServiciosExamenesVO.setServ_id(rs.getString("serv_id"));

//                ***cambio dao***
                        lista.add(ServiciosExamenesVO);
                    }
                }
            } catch (Exception ex) {
                e.ImprimirError("ServiciosExamenesDAO;ocupacional.bdatos.ServiciosExamenesDAO.Listar();msg:" + ex.getMessage());
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
