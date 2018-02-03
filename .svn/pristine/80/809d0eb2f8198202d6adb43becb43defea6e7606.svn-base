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
import ocupacional.valueobjects.facturacion.ClientesServiciosVO;
import valeria.conexion.ConexionAplicacion;
import valeria.metodos.Cadenas;
import valeria.response.Mediador;

/**
 *
 * @author D4V3
 */
public class ClientesServiciosDAO {

    Cadenas c = new Cadenas();
    Mediador e = null;
    ConexionAplicacion conn = null;
    private boolean closed = true;

    public ClientesServiciosDAO(Mediador e) {
        this.e = e;
    }

    public ClientesServiciosDAO(Mediador e, ConexionAplicacion conn) {
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

    public boolean Insertar(ClientesServiciosVO ClientesServiciosVO) throws Exception {
        PreparedStatement ps = null;
        ResultSet keyRS = null;
        boolean exito = false;
        this.getConexion();
        if (this.ConnEstado()) {
            try {
                this.conn.getCon().setAutoCommit(false);
                StringBuilder sql = new StringBuilder();

//                ***cambio dao***
                sql.append("INSERT INTO `clientes_servicio`( `clie_id`, `serv_id`, `clse_valor`, `clse_observacion`, `clse_registradopor`, `clse_fechacambio`) VALUES (?,?,?,?,?,?)");
                ps = this.conn.getCon().prepareStatement(sql.toString(), PreparedStatement.RETURN_GENERATED_KEYS);
                int a = 1;
                ps.setString(a++, ClientesServiciosVO.getClie_id());
                ps.setString(a++, ClientesServiciosVO.getServ_id());
                ps.setString(a++, ClientesServiciosVO.getClse_valor());
                ps.setString(a++, ClientesServiciosVO.getClse_observacion());
                ps.setString(a++, ClientesServiciosVO.getClse_registradopor());
                ps.setTimestamp(a++, ClientesServiciosVO.getClse_fechacambio());

                ps.executeUpdate();
                keyRS = ps.getGeneratedKeys();
                if (keyRS.next()) {
                    ClientesServiciosVO.setClse_id(keyRS.getString(1));
                }
                if (ClientesServiciosVO.getClse_id() != null) {

//                ***cambio dao***
                    exito = true;
                }
            } catch (Exception ex) {
                e.ImprimirError("ClientesServiciosDAO;ocupacional.bdatos.ClientesServiciosDAO.Insertar();msg:"
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

    public boolean Actualizar(ClientesServiciosVO ClientesServiciosVO) throws Exception {
        PreparedStatement ps = null;
        ResultSet keyRS = null;
        boolean exito = false;
        this.getConexion();
        if (this.ConnEstado()) {
            try {
                this.conn.getCon().setAutoCommit(false);
                StringBuilder sql = new StringBuilder();
//                ***cambio dao***
                sql.append("UPDATE `clientes_servicio` \n"
                        + "SET \n"
                        + "`clie_id`=?,\n"
                        + "`serv_id`=?,\n"
                        + "`clse_valor`=?,\n"
                        + "`clse_observacion`=?,\n"
                        + "`clse_registradopor`=?,\n"
                        + "`clse_fechacambio`=?\n"
                        + " WHERE `clse_id`=?");
                ps = this.conn.getCon().prepareStatement(sql.toString());
                int a = 1;
                ps.setString(a++, ClientesServiciosVO.getClie_id());
                ps.setString(a++, ClientesServiciosVO.getServ_id());
                ps.setString(a++, ClientesServiciosVO.getClse_valor());
                ps.setString(a++, ClientesServiciosVO.getClse_observacion());
                ps.setString(a++, ClientesServiciosVO.getClse_registradopor());
                ps.setTimestamp(a++, ClientesServiciosVO.getClse_fechacambio());
                ps.setString(a++, ClientesServiciosVO.getClse_id());

//                ***cambio dao***
                ps.executeUpdate();
                exito = true;
            } catch (Exception ex) {
                e.ImprimirError("ClientesServiciosDAO;ocupacional.bdatos.ClientesServiciosDAO.Actualizar();msg:"
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

    public boolean Eliminar(ClientesServiciosVO ClientesServiciosVO) throws Exception {
        PreparedStatement ps = null;
        ResultSet keyRS = null;
        boolean exito = false;
        this.getConexion();
        if (this.ConnEstado()) {
            try {
                this.conn.getCon().setAutoCommit(false);
                StringBuilder sql = new StringBuilder();
                //                ***cambio dao***
                sql.append("DELETE FROM javap.clientes_servicio   \n\t");
                sql.append("WHERE   clse_id = ? \n\t");
                ps = this.conn.getCon().prepareStatement(sql.toString());
                int a = 1;
                ps.setString(a++, ClientesServiciosVO.getClse_id());
                //                ***cambio dao***
                ps.executeUpdate();
                exito = true;
            } catch (Exception ex) {
                e.ImprimirError("ClientesServiciosDAO;ocupacional.bdatos.ClientesServiciosDAO.Eliminar();msg:"
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

    public ArrayList<ClientesServiciosVO> Listar( String id) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<ClientesServiciosVO> lista = null;
                    lista = new ArrayList<ClientesServiciosVO>();
        boolean exito = false;
        this.getConexion();
        if (this.ConnEstado()) {
            try {
                StringBuilder sql = new StringBuilder();
                this.conn.getCon().setAutoCommit(false);
                //                ***cambio dao***
                sql.append("SELECT c.clse_id,c.clie_id,c.serv_id,c.clse_valor,c.clse_observacion,s.serv_nombre FROM `clientes_servicio` c, servicios s Where c.serv_id= s.serv_id And c.clse_estado='ACTIVO' and clie_id= ?");
                ps = this.conn.getCon().prepareStatement(sql.toString());
                ps.setString(1, id);
                rs = ps.executeQuery();
                if (rs.isBeforeFirst()) {
                    while (rs.next()) {

                        ClientesServiciosVO ClientesServiciosVO = new ClientesServiciosVO();
                        ClientesServiciosVO.setClse_id(rs.getString("clse_id"));
                        ClientesServiciosVO.setClie_id(rs.getString("clie_id"));
                        ClientesServiciosVO.setServ_descripcion(rs.getString("serv_nombre"));
                        ClientesServiciosVO.setServ_id(rs.getString("serv_id"));
                        ClientesServiciosVO.setClse_valor(rs.getString("clse_valor"));
                        ClientesServiciosVO.setClse_observacion(rs.getString("clse_observacion"));

//                ***cambio dao***
                        lista.add(ClientesServiciosVO);
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                e.ImprimirError("ClientesServiciosDAO;ocupacional.bdatos.ClientesServiciosDAO.Listar();msg:"
                        + ex.getMessage());
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
    public ClientesServiciosVO Cargar( String id) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean exito = false;
                        ClientesServiciosVO ClientesServiciosVO = new ClientesServiciosVO();
        this.getConexion();
        if (this.ConnEstado()) {
            try {
                StringBuilder sql = new StringBuilder();
                this.conn.getCon().setAutoCommit(false);
                //                ***cambio dao***
                sql.append("SELECT c.clse_id,c.clie_id,c.serv_id,c.clse_valor,c.clse_observacion,s.serv_nombre FROM `clientes_servicio` c, servicios s Where c.serv_id= s.serv_id And  c.clse_id= ?");
                ps = this.conn.getCon().prepareStatement(sql.toString());
                ps.setString(1, id);
                rs = ps.executeQuery();
                if (rs.isBeforeFirst()) {
                    if (rs.next()) {

                        ClientesServiciosVO.setClse_id(rs.getString("clse_id"));
                        ClientesServiciosVO.setClie_id(rs.getString("clie_id"));
                        ClientesServiciosVO.setServ_descripcion(rs.getString("serv_nombre"));
                        ClientesServiciosVO.setServ_id(rs.getString("serv_id"));
                        ClientesServiciosVO.setClse_valor(rs.getString("clse_valor"));
                        ClientesServiciosVO.setClse_observacion(rs.getString("clse_observacion"));

//                ***cambio dao***
                    }
                }
            } catch (Exception ex) {
                e.ImprimirError("ClientesServiciosDAO;ocupacional.bdatos.ClientesServiciosDAO.Listar();msg:"
                        + ex.getMessage());
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
        return ClientesServiciosVO;
    }

}
