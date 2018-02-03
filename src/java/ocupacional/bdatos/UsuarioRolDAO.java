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
import ocupacional.valueobjects.UsuarioRolVO;
import valeria.conexion.ConexionAplicacion;
import valeria.metodos.Cadenas;
import valeria.response.Mediador;

/**
 *
 * @author D4V3
 */
public class UsuarioRolDAO {

    Cadenas c = new Cadenas();
    Mediador e = null;
    ConexionAplicacion conn = null;
    private boolean closed = true;

    public UsuarioRolDAO(Mediador e) {
        this.e = e;
    }

    public UsuarioRolDAO(Mediador e, ConexionAplicacion conn) {
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

    public boolean Insertar(UsuarioRolVO UsuarioRolVO) throws Exception {
        PreparedStatement ps = null;
        ResultSet keyRS = null;
        boolean exito = false;
        this.getConexion();
        if (this.ConnEstado()) {
            try {
                this.conn.getCon().setAutoCommit(false);
                StringBuilder sql = new StringBuilder();

//                ***cambio dao***
                sql.append("INSERT INTO `usuariorol`( usua_id,role_id,usro_registradopor,usro_fechacambio) VALUES (?,?,?,?)");
                ps = this.conn.getCon().prepareStatement(sql.toString(), PreparedStatement.RETURN_GENERATED_KEYS);
                int a = 1;
                ps.setString(a++, UsuarioRolVO.getUsua_id());
                ps.setString(a++, UsuarioRolVO.getRole_id());
                ps.setString(a++, UsuarioRolVO.getUsro_registradopor());
                ps.setTimestamp(a++, UsuarioRolVO.getUsro_fechacambio());
                ps.executeUpdate();
                keyRS = ps.getGeneratedKeys();
                if (keyRS.next()) {
                    UsuarioRolVO.setUsro_id(keyRS.getString(1));
                }
                if (UsuarioRolVO.getUsro_id() != null) {

//                ***cambio dao***
                    exito = true;
                }
            } catch (Exception ex) {
                e.ImprimirError("UsuarioRolDAO;ocupacional.bdatos.UsuarioRolDAO.Insertar();msg:" + ex.getMessage());
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

//    public boolean Actualizar(UsuarioRolVO UsuarioRolVO) throws Exception {
//        PreparedStatement ps = null;
//        ResultSet keyRS = null;
//        boolean exito = false;
//        this.getConexion();
//        if (this.ConnEstado()) {
//            try {
//                this.conn.getCon().setAutoCommit(false);
//                StringBuilder sql = new StringBuilder();
////                ***cambio dao***
//                sql.append("");
//                ps = this.conn.getCon().prepareStatement(sql.toString());
//                int a = 1;
//                ps.setString(a++, UsuarioRolVO.());
//
////                ***cambio dao***
//                ps.executeUpdate();
//                exito = true;
//            } catch (Exception ex) {
//                e.ImprimirError("UsuarioRolDAO;ocupacional.bdatos.UsuarioRolDAO.Actualizar();msg:" + 
//ex.getMessage());
//            } finally {
//                if (this.closed && this.conn.getCon() != null) {
//                    this.conn.getCon().commit();
//                    this.conn.getCon().close();
//                    this.conn = null;
//                }
//                try {
//                    if (keyRS != null) {
//                        keyRS.close();
//                    }
//                    if (ps != null) {
//                        ps.close();
//                    }
//                } catch (Exception ex) {
//                }
//            }
//        }
//        return exito;
//    }
    public boolean EliminarTodos(String idUsuario) throws Exception {
        PreparedStatement ps = null;
        ResultSet keyRS = null;
        boolean exito = false;
        this.getConexion();
        if (this.ConnEstado()) {
            try {
                this.conn.getCon().setAutoCommit(false);
                StringBuilder sql = new StringBuilder();
                //                ***cambio dao***
                sql.append("DELETE FROM javap.usuariorol   \n\t");
                sql.append("WHERE   usua_id = ? \n\t");
                ps = this.conn.getCon().prepareStatement(sql.toString());
                int a = 1;
                ps.setString(a++,idUsuario);
                //                ***cambio dao***
                ps.executeUpdate();
                exito = true;
            } catch (Exception ex) {
                e.ImprimirError("UsuarioRolDAO;ocupacional.bdatos.UsuarioRolDAO.EliminarTodos();msg:" + ex.getMessage());
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

    public ArrayList<UsuarioRolVO> ListarXUsuario(String id) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<UsuarioRolVO> lista = null;
        lista = new ArrayList<UsuarioRolVO>();
        boolean exito = false;
        this.getConexion();
        if (this.ConnEstado()) {
            try {
                StringBuilder sql = new StringBuilder();
                this.conn.getCon().setAutoCommit(false);
                //                ***cambio dao***
                sql.append("SELECT * FROM usuariorol as r inner join roles f on f.role_id=r.role_id\n"
                        + " where usua_id=? \n"
                        + "order by f.role_descripcion");
                ps = this.conn.getCon().prepareStatement(sql.toString());
                ps.setString(1, id);
                rs = ps.executeQuery();
                if (rs.isBeforeFirst()) {
                    while (rs.next()) {
                        UsuarioRolVO UsuarioRolVO = new UsuarioRolVO();

                        UsuarioRolVO.setUsro_id(rs.getString("usro_id"));
                        UsuarioRolVO.setUsua_id(rs.getString("usua_id"));
                        UsuarioRolVO.setRole_id(rs.getString("role_id"));
                        UsuarioRolVO.setRole_descripcion(rs.getString("role_descripcion"));
                        UsuarioRolVO.setUsro_registradopor(rs.getString("usro_registradopor"));
                        UsuarioRolVO.setUsro_fechacambio(rs.getTimestamp("usro_fechacambio"));
//                ***cambio dao***
                        lista.add(UsuarioRolVO);
                    }
                }
            } catch (Exception ex) {
                e.ImprimirError("UsuarioRolDAO;ocupacional.bdatos.UsuarioRolDAO.Listar();msg:" + ex.getMessage());
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

    public UsuarioRolVO Cargar(String id) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;

        UsuarioRolVO UsuarioRolVO = new UsuarioRolVO();
        boolean exito = false;
        this.getConexion();
        if (this.ConnEstado()) {
            try {
                StringBuilder sql = new StringBuilder();
                this.conn.getCon().setAutoCommit(false);
                //                ***cambio dao***
                sql.append("SELECT * FROM `usuariorol` where usro_id=?");
                ps = this.conn.getCon().prepareStatement(sql.toString());
                ps.setString(1, id);
                rs = ps.executeQuery();
                if (rs.isBeforeFirst()) {
                    while (rs.next()) {
                        UsuarioRolVO.setUsro_id(rs.getString("usro_id"));
                        UsuarioRolVO.setUsua_id(rs.getString("usua_id"));
                        UsuarioRolVO.setRole_id(rs.getString("role_id"));
                        UsuarioRolVO.setUsro_registradopor(rs.getString("usro_registradopor"));
                        UsuarioRolVO.setUsro_fechacambio(rs.getTimestamp("usro_fechacambio"));

//                ***cambio dao***
                    }
                }
            } catch (Exception ex) {
                e.ImprimirError("UsuarioRolDAO;ocupacional.bdatos.UsuarioRolDAO.Listar();msg:" + ex.getMessage());
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
        return UsuarioRolVO;
    }

}
