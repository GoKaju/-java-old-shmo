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
import ocupacional.valueobjects.facturacion.ClientesVO;
import valeria.conexion.ConexionAplicacion;
import valeria.metodos.Cadenas;
import valeria.response.Mediador;

/**
 *
 * @author D4V3
 */
public class ClientesDAO {

    Cadenas c = new Cadenas();
    Mediador e = null;
    ConexionAplicacion conn = null;
    private boolean closed = true;

    public ClientesDAO(Mediador e) {
        this.e = e;
    }

    public ClientesDAO(Mediador e, ConexionAplicacion conn) {
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

    public boolean Insertar(ClientesVO ClientesVO) throws Exception {
        PreparedStatement ps = null;
        ResultSet keyRS = null;
        boolean exito = false;
        this.getConexion();
        if (this.ConnEstado()) {
            try {
                this.conn.getCon().setAutoCommit(false);
                StringBuilder sql = new StringBuilder();

//                ***cambio dao***
                sql.append("INSERT INTO `clientes`\n"
                        + "( `pege_id`, `clie_registradopor`, `clie_fechacambio`, `clie_observacion`, `clie_tipo`) \n"
                        + "VALUES (?,?,?,?,?)");
                ps = this.conn.getCon().prepareStatement(sql.toString(), PreparedStatement.RETURN_GENERATED_KEYS);
                int a = 1;
                ps.setString(a++, ClientesVO.getPege_id());
                ps.setString(a++, ClientesVO.getClie_registradopor());
                ps.setTimestamp(a++, ClientesVO.getClie_fechacambio());
                ps.setString(a++, ClientesVO.getClie_observacion());
                ps.setString(a++, ClientesVO.getClie_tipo());
                ps.executeUpdate();
                keyRS = ps.getGeneratedKeys();
                if (keyRS.next()) {
                    ClientesVO.setClie_id(keyRS.getString(1));
                }
                if (ClientesVO.getClie_id() != null) {

//                ***cambio dao***
                    exito = true;
                }
            } catch (Exception ex) {
                e.ImprimirError("ClientesDAO;ocupacional.bdatos.ClientesDAO.Insertar();msg:" + ex.getMessage());
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

    public boolean Actualizar(ClientesVO ClientesVO) throws Exception {
        PreparedStatement ps = null;
        ResultSet keyRS = null;
        boolean exito = false;
        this.getConexion();
        if (this.ConnEstado()) {
            try {
                this.conn.getCon().setAutoCommit(false);
                StringBuilder sql = new StringBuilder();
//                ***cambio dao***
                sql.append("UPDATE `clientes` \n"
                        + "SET \n"
                        + "`pege_id`=? ,\n"
                        + "`clie_registradopor`=? ,\n"
                        + "`clie_fechacambio`=? ,\n"
                        + "`clie_observacion`=? ,\n"
                        + "`clie_tipo`=? ,\n"
                        + "`clie_estado`=?\n"
                        + "WHERE `clie_id`=?");
                ps = this.conn.getCon().prepareStatement(sql.toString());
                int a = 1;
                ps.setString(a++, ClientesVO.getPege_id());
                ps.setString(a++, ClientesVO.getClie_registradopor());
                ps.setTimestamp(a++, ClientesVO.getClie_fechacambio());
                ps.setString(a++, ClientesVO.getClie_observacion());
                ps.setString(a++, ClientesVO.getClie_tipo());
                ps.setString(a++, ClientesVO.getClie_estado());
                ps.setString(a++, ClientesVO.getClie_id());

//                ***cambio dao***
                ps.executeUpdate();
                exito = true;
            } catch (Exception ex) {
                e.ImprimirError("ClientesDAO;ocupacional.bdatos.ClientesDAO.Actualizar();msg:" + ex.getMessage());
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

    public boolean Eliminar(ClientesVO ClientesVO) throws Exception {
        PreparedStatement ps = null;
        ResultSet keyRS = null;
        boolean exito = false;
        this.getConexion();
        if (this.ConnEstado()) {
            try {
                this.conn.getCon().setAutoCommit(false);
                StringBuilder sql = new StringBuilder();
                //                ***cambio dao***
                sql.append("UPDATE  javap.clientes c SET c.clie_estado = 'ELIMINADO', c.clie_registradopor = ?,  c.clie_fechacambio=? \n\t");
                sql.append("WHERE   clie_id = ? \n\t");
                ps = this.conn.getCon().prepareStatement(sql.toString());
                int a = 1;
                ps.setString(a++, ClientesVO.getClie_registradopor());
                ps.setTimestamp(a++, ClientesVO.getClie_fechacambio());
                ps.setString(a++, ClientesVO.getClie_id());
                //                ***cambio dao***
                ps.executeUpdate();
                exito = true;
            } catch (Exception ex) {
                ex.printStackTrace();
                e.ImprimirError("ClientesDAO;ocupacional.bdatos.ClientesDAO.Eliminar();msg:" + ex.getMessage());
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

    public ArrayList<ClientesVO> Listar() throws Exception {

        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<ClientesVO> lista = new ArrayList<ClientesVO>();
        boolean exito = false;
        this.getConexion();
        if (this.ConnEstado()) {

           

            try {
                StringBuilder sql = new StringBuilder();
                this.conn.getCon().setAutoCommit(false);
                //                ***cambio dao***
                sql.append("SELECT * FROM clientes where clie_estado != 'ELIMINADO'");
                ps = this.conn.getCon().prepareStatement(sql.toString());
                rs = ps.executeQuery();
                if (rs.isBeforeFirst()) {

                    

                    
                    while (rs.next()) {
                        ClientesVO ClientesVO = new ClientesVO();

                        ClientesVO.setClie_id(rs.getString("clie_id"));
                        ClientesVO.setPege_id(rs.getString("pege_id"));
                        ClientesVO.setClie_observacion(rs.getString("clie_observacion"));
                        ClientesVO.setClie_tipo(rs.getString("clie_tipo"));
                        ClientesVO.setClie_estado(rs.getString("clie_estado"));

//                ***cambio dao***
                        lista.add(ClientesVO);
                    }

                }

                System.out.println("3");

            } catch (Exception ex) {
                ex.printStackTrace();
                e.ImprimirError("ClientesDAO;ocupacional.bdatos.ClientesDAO.Listar();msg:" + ex.getMessage());
            } finally {
                if (this.closed && this.conn.getCon() != null) {
                    this.conn.getCon().commit();
                    this.conn.getCon().close();
                    this.conn = null;
                    System.out.println("entre");
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
     public ArrayList<ClientesVO> Buscar( String documento) throws Exception {

        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<ClientesVO> lista = new ArrayList<ClientesVO>();
        boolean exito = false;
        this.getConexion();
        if (this.ConnEstado()) {

           

            try {
                StringBuilder sql = new StringBuilder();
                this.conn.getCon().setAutoCommit(false);
                //                ***cambio dao***
                sql.append("select * from personageneral p, clientes c where p.pege_id=c.pege_id and p.pege_documento like '%?%'");
                ps = this.conn.getCon().prepareStatement(sql.toString());
                ps.setString(1, documento);
                rs = ps.executeQuery();
                if (rs.isBeforeFirst()) {

                    

                    
                    while (rs.next()) {
                        ClientesVO ClientesVO = new ClientesVO();

                        ClientesVO.setClie_id(rs.getString("clie_id"));
                        ClientesVO.setPege_id(rs.getString("pege_id"));
                        ClientesVO.setClie_observacion(rs.getString("clie_observacion"));
                        ClientesVO.setClie_tipo(rs.getString("clie_tipo"));
                        ClientesVO.setClie_estado(rs.getString("clie_estado"));

//                ***cambio dao***
                        lista.add(ClientesVO);
                    }

                }

                System.out.println("3");

            } catch (Exception ex) {
                ex.printStackTrace();
                e.ImprimirError("ClientesDAO;ocupacional.bdatos.ClientesDAO.Listar();msg:" + ex.getMessage());
            } finally {
                if (this.closed && this.conn.getCon() != null) {
                    this.conn.getCon().commit();
                    this.conn.getCon().close();
                    this.conn = null;
                    System.out.println("entre");
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
    public ClientesVO Cargar(String Pegeid) throws Exception {

        PreparedStatement ps = null;
        ResultSet rs = null;
                        ClientesVO ClientesVO = new ClientesVO();
        boolean exito = false;
        this.getConexion();
        if (this.ConnEstado()) {

           

            try {
                StringBuilder sql = new StringBuilder();
                this.conn.getCon().setAutoCommit(false);
                //                ***cambio dao***
                sql.append("SELECT * FROM clientes WHERE clie_id= ?");
                ps = this.conn.getCon().prepareStatement(sql.toString());
                ps.setString(1, Pegeid);
                rs = ps.executeQuery();
                if (rs.isBeforeFirst()) {
                    
                    while (rs.next()) {

                  
                        ClientesVO.setClie_id(rs.getString("clie_id"));
//                        ClientesVO.setPege_id(rs.getString("pege_id"));
                        ClientesVO.setClie_nombre(rs.getString("clie_nombre"));
                        ClientesVO.setClie_observacion(rs.getString("clie_observacion"));
                        ClientesVO.setClie_tipo(rs.getString("clie_tipo"));
                        ClientesVO.setClie_estado(rs.getString("clie_estado"));

//                ***cambio dao***
                    }

                }

                System.out.println("3");

            } catch (Exception ex) {
                ex.printStackTrace();
                e.ImprimirError("ClientesDAO;ocupacional.bdatos.ClientesDAO.Listar();msg:" + ex.getMessage());
            } finally {
                if (this.closed && this.conn.getCon() != null) {
                    this.conn.getCon().commit();
                    this.conn.getCon().close();
                    this.conn = null;
                    System.out.println("entre");
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
        return ClientesVO;
    }
    public ClientesVO Cargarxid(String id) throws Exception {

        PreparedStatement ps = null;
        ResultSet rs = null;
                        ClientesVO ClientesVO = new ClientesVO();
        boolean exito = false;
        this.getConexion();
        if (this.ConnEstado()) {

           

            try {
                StringBuilder sql = new StringBuilder();
                this.conn.getCon().setAutoCommit(false);
                //                ***cambio dao***
                sql.append("SELECT * FROM clientes WHERE clie_id= ?");
                ps = this.conn.getCon().prepareStatement(sql.toString());
                ps.setString(1, id);
                rs = ps.executeQuery();
                if (rs.isBeforeFirst()) {
                    
                    while (rs.next()) {

                  
                        ClientesVO.setClie_id(rs.getString("clie_id"));
                        ClientesVO.setPege_id(rs.getString("pege_id"));
                        ClientesVO.setClie_observacion(rs.getString("clie_observacion"));
                        ClientesVO.setClie_tipo(rs.getString("clie_tipo"));
                        ClientesVO.setClie_estado(rs.getString("clie_estado"));

//                ***cambio dao***
                    }

                }

                System.out.println("3");

            } catch (Exception ex) {
                ex.printStackTrace();
                e.ImprimirError("ClientesDAO;ocupacional.bdatos.ClientesDAO.Listar();msg:" + ex.getMessage());
            } finally {
                if (this.closed && this.conn.getCon() != null) {
                    this.conn.getCon().commit();
                    this.conn.getCon().close();
                    this.conn = null;
                    System.out.println("entre");
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
        return ClientesVO;
    }

}
