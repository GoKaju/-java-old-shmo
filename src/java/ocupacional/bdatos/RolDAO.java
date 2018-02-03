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
import ocupacional.valueobjects.RolVO;
import valeria.conexion.ConexionAplicacion;
import valeria.metodos.Cadenas;
import valeria.response.Mediador;

/**
 *
 * @author D4V3
 */
public class RolDAO {

    Cadenas c = new Cadenas();
    Mediador e = null;
    ConexionAplicacion conn = null;
    private boolean closed = true;

    public RolDAO(Mediador e) {
        this.e = e;
    }

    public RolDAO(Mediador e, ConexionAplicacion conn) {
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

    public boolean Insertar(RolVO RolVO) throws Exception {
        PreparedStatement ps = null;
        ResultSet keyRS = null;
        boolean exito = false;
        this.getConexion();
        if (this.ConnEstado()) {
            try {
                this.conn.getCon().setAutoCommit(false);
                StringBuilder sql = new StringBuilder();

//                ***cambio dao**                ps.setTimestamp(a++, RolVO.getRole_fechacambio());
                sql.append("INSERT INTO javap.roles (role_descripcion, role_fechacambio, roleregistradopor) \n"
                        + "	VALUES (?,?,?)");
                ps = this.conn.getCon().prepareStatement(sql.toString(), PreparedStatement.RETURN_GENERATED_KEYS);
                int a = 1;
                ps.setString(a++, RolVO.getRole_descripcion());
                ps.setTimestamp(a++, RolVO.getRole_fechacambio());
                ps.setString(a++, RolVO.getRole_registradopor());
                ps.executeUpdate();
                keyRS = ps.getGeneratedKeys();
                if (keyRS.next()) {
                    RolVO.setRole_id(keyRS.getString(1));
                }
                if (RolVO.getRole_id() != null) {

//                ***cambio dao***
                    exito = true;
                }
            } catch (Exception ex) {
                e.ImprimirError("RolDAO;ocupacional.bdatos.RolDAO.Insertar();msg:" + ex.getMessage());
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

    public boolean Actualizar(RolVO RolVO) throws Exception {
        PreparedStatement ps = null;
        ResultSet keyRS = null;
        boolean exito = false;
        this.getConexion();
        if (this.ConnEstado()) {
            try {
                this.conn.getCon().setAutoCommit(false);
                StringBuilder sql = new StringBuilder();
//                ***cambio dao***
                sql.append("UPDATE javap.roles SET \n"
                        + "`role_descripcion` =?,\n"
                        + "`role_estado` =?,\n"
                        + "`role_fechacambio` =?,\n"
                        + "`roleregistradopor` =?\n"
                        + " WHERE role_id = ?");
                ps = this.conn.getCon().prepareStatement(sql.toString());
                int a = 1;
                ps.setString(a++, RolVO.getRole_descripcion());
                ps.setString(a++, RolVO.getRole_estado());
                ps.setTimestamp(a++, RolVO.getRole_fechacambio());
                ps.setString(a++, RolVO.getRole_registradopor());
                ps.setString(a++, RolVO.getRole_id());

//                ***cambio dao***
                ps.executeUpdate();
                exito = true;
            } catch (Exception ex) {
                e.ImprimirError("RolDAO;ocupacional.bdatos.RolDAO.Actualizar();msg:"
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

    public boolean Eliminar(RolVO RolVO) throws Exception {
        PreparedStatement ps = null;
        ResultSet keyRS = null;
        boolean exito = false;
        this.getConexion();
        if (this.ConnEstado()) {
            try {
                this.conn.getCon().setAutoCommit(false);
                StringBuilder sql = new StringBuilder();
                //                ***cambio dao***
//                sql.append("DELETE FROM javap.roles   \n\t");
//                sql.append("WHERE   role_id = ? \n\t");
                      sql.append("UPDATE javap.roles SET \n"
                        + "`role_estado` ='ELIMINADO'\n"
                        + " WHERE role_id = ?");
                ps = this.conn.getCon().prepareStatement(sql.toString());
                int a = 1;
                ps.setString(a++, RolVO.getRole_id());
                //                ***cambio dao***
                ps.executeUpdate();
                exito = true;
            } catch (Exception ex) {
                e.ImprimirError("RolDAO;ocupacional.bdatos.RolDAO.Eliminar();msg:" + ex.getMessage());
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

    public ArrayList<RolVO> Listar() throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<RolVO> lista = null;
        boolean exito = false;
        this.getConexion();
        if (this.ConnEstado()) {
            try {
                StringBuilder sql = new StringBuilder();
                this.conn.getCon().setAutoCommit(false);
                //                ***cambio dao***
                sql.append("SELECT * FROM `roles` r left join usuariorol u on u.role_id=r.role_id \n" +
"where  r.role_estado!='ELIMINADO' group by r.role_id");
                ps = this.conn.getCon().prepareStatement(sql.toString());
                rs = ps.executeQuery();
                if (rs.isBeforeFirst()) {
                    lista = new ArrayList<RolVO>();
                    while (rs.next()) {
                        RolVO RolVO = new RolVO();

                        RolVO.setRole_id(rs.getString("role_id"));
                        RolVO.setRole_descripcion(rs.getString("role_descripcion"));
                        RolVO.setRole_fechacambio(rs.getTimestamp("role_fechacambio"));
                        RolVO.setRole_registradopor(rs.getString("roleregistradopor"));
                        RolVO.setRole_estado(rs.getString("role_estado"));

//                ***cambio dao***
                        System.out.println("usua_id: "+ e.getUsuarioVO().getIdUsuario());
                        if(rs.getString("usua_id")==null || !rs.getString("usua_id").equals(e.getUsuarioVO().getIdUsuario())){
                        lista.add(RolVO);
                        }
                    }
                }
            } catch (Exception ex) {
                e.ImprimirError("RolDAO;ocupacional.bdatos.RolDAO.Listar();msg:" + ex.getMessage());
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
  
    public ArrayList<RolVO> ListarActivos() throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<RolVO> lista = null;
        boolean exito = false;
        this.getConexion();
        if (this.ConnEstado()) {
            try {
                StringBuilder sql = new StringBuilder();
                this.conn.getCon().setAutoCommit(false);
                //                ***cambio dao***
                sql.append("SELECT * FROM `roles` r  where  r.role_estado='ACTIVO' ");
                ps = this.conn.getCon().prepareStatement(sql.toString());
                rs = ps.executeQuery();
                if (rs.isBeforeFirst()) {
                    lista = new ArrayList<RolVO>();
                    while (rs.next()) {
                        RolVO RolVO = new RolVO();

                        RolVO.setRole_id(rs.getString("role_id"));
                        RolVO.setRole_descripcion(rs.getString("role_descripcion"));
                        RolVO.setRole_fechacambio(rs.getTimestamp("role_fechacambio"));
                        RolVO.setRole_registradopor(rs.getString("roleregistradopor"));
                        RolVO.setRole_estado(rs.getString("role_estado"));

//                ***cambio dao***
                        System.out.println("usua_id: "+ e.getUsuarioVO().getIdUsuario());
                        
                        lista.add(RolVO);
                        
                    }
                }
            } catch (Exception ex) {
                e.ImprimirError("RolDAO;ocupacional.bdatos.RolDAO.Listar();msg:" + ex.getMessage());
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
