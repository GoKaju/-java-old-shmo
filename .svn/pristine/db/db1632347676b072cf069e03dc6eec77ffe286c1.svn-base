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
import ocupacional.valueobjects.facturacion.ProveedorVO;
import valeria.conexion.ConexionAplicacion;
import valeria.metodos.Cadenas;
import valeria.response.Mediador;

/**
 *
 * @author D4V3
 */
public class ProveedorDAO {
    
    

    Cadenas c = new Cadenas();
    Mediador e = null;
    ConexionAplicacion conn = null;
    private boolean closed = true;

    public ProveedorDAO(Mediador e) {
        this.e = e;
    }

    public ProveedorDAO(Mediador e, ConexionAplicacion conn) {
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

    public boolean Insertar(ProveedorVO ProveedorVO) throws Exception {
        PreparedStatement ps = null;
        ResultSet keyRS = null;
        boolean exito = false;
        this.getConexion();
        if (this.ConnEstado()) {
            try {
                this.conn.getCon().setAutoCommit(false);
                StringBuilder sql = new StringBuilder();

//                ***cambio dao***
                sql.append("INSERT INTO `proveedores`\n"
                        + "( `pege_id`, `prov_registradopor`, `prov_fechacambio`, `prov_observacion`, `prov_tipo`) \n"
                        + "VALUES (?,?,?,?,?)");
                ps = this.conn.getCon().prepareStatement(sql.toString(), PreparedStatement.RETURN_GENERATED_KEYS);
                int a = 1;
                ps.setString(a++, ProveedorVO.getPege_id());
                ps.setString(a++, ProveedorVO.getProv_registradopor());
                ps.setTimestamp(a++, ProveedorVO.getProv_fechacambio());
                ps.setString(a++, ProveedorVO.getProv_observacion());
                ps.setString(a++, ProveedorVO.getProv_tipo());
                ps.executeUpdate();
                keyRS = ps.getGeneratedKeys();
                if (keyRS.next()) {
                    ProveedorVO.setProv_id(keyRS.getString(1));
                }
                if (ProveedorVO.getProv_id()!= null) {

//                ***cambio dao***
                    exito = true;
                }
            } catch (Exception ex) {
                e.ImprimirError("ProveedorDAO;ocupacional.bdatos.ProveedorDAO.Insertar();msg:" + ex.getMessage());
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

    public boolean Actualizar(ProveedorVO ProveedorVO) throws Exception {
        PreparedStatement ps = null;
        ResultSet keyRS = null;
        boolean exito = false;
        this.getConexion();
        if (this.ConnEstado()) {
            try {
                this.conn.getCon().setAutoCommit(false);
                StringBuilder sql = new StringBuilder();
//                ***cambio dao***
                sql.append("UPDATE `proveedores` \n"
                        + "SET \n"
                        + "`pege_id`=? ,\n"
                        + "`prov_registradopor`=? ,\n"
                        + "`prov_fechacambio`=? ,\n"
                        + "`prov_observacion`=? ,\n"
                        + "`prov_tipo`=? ,\n"
                        + "`prov_estado`=?\n"
                        + "WHERE `prov_id`=?");
                ps = this.conn.getCon().prepareStatement(sql.toString());
                int a = 1;
                ps.setString(a++, ProveedorVO.getPege_id());
                ps.setString(a++, ProveedorVO.getProv_registradopor());
                ps.setTimestamp(a++, ProveedorVO.getProv_fechacambio());
                ps.setString(a++, ProveedorVO.getProv_observacion());
                ps.setString(a++, ProveedorVO.getProv_tipo());
                ps.setString(a++, ProveedorVO.getProv_estado());
                ps.setString(a++, ProveedorVO.getProv_id());

//                ***cambio dao***
                ps.executeUpdate();
                exito = true;
            } catch (Exception ex) {
                e.ImprimirError("ProveedorDAO;ocupacional.bdatos.ProveedorDAO.Actualizar();msg:" + ex.getMessage());
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

    public boolean Eliminar(ProveedorVO ProveedorVO) throws Exception {
        PreparedStatement ps = null;
        ResultSet keyRS = null;
        boolean exito = false;
        this.getConexion();
        if (this.ConnEstado()) {
            try {
                this.conn.getCon().setAutoCommit(false);
                StringBuilder sql = new StringBuilder();
                //                ***cambio dao***
                sql.append("DELETE FROM javap.proveedores   \n\t");
                sql.append("WHERE   prov_id = ? \n\t");
                ps = this.conn.getCon().prepareStatement(sql.toString());
                int a = 1;
                ps.setString(1, ProveedorVO.getProv_id());
                //                ***cambio dao***
                ps.executeUpdate();
                exito = true;
            } catch (Exception ex) {
                ex.printStackTrace();
                e.ImprimirError("ProveedorDAO;ocupacional.bdatos.ProveedorDAO.Eliminar();msg:" + ex.getMessage());
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

    public ArrayList<ProveedorVO> Listar() throws Exception {

        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<ProveedorVO> lista = new ArrayList<ProveedorVO>();
        boolean exito = false;
        this.getConexion();
        if (this.ConnEstado()) {

           

            try {
                StringBuilder sql = new StringBuilder();
                this.conn.getCon().setAutoCommit(false);
                //                ***cambio dao***
                sql.append("SELECT * FROM proveedores");
                ps = this.conn.getCon().prepareStatement(sql.toString());
                rs = ps.executeQuery();
                if (rs.isBeforeFirst()) {

                    

                    
                    while (rs.next()) {
                        ProveedorVO ProveedorVO = new ProveedorVO();

                        ProveedorVO.setProv_id(rs.getString("prov_id"));
                        ProveedorVO.setPege_id(rs.getString("pege_id"));
                        ProveedorVO.setProv_registradopor(rs.getString("prov_registradopor"));
                        ProveedorVO.setProv_fechacambio(rs.getTimestamp("prov_fechacambio"));
                        ProveedorVO.setProv_observacion(rs.getString("prov_observacion"));
                        ProveedorVO.setProv_tipo(rs.getString("prov_tipo"));
                        ProveedorVO.setProv_estado(rs.getString("prov_estado"));

//                ***cambio dao***
                        lista.add(ProveedorVO);
                    }

                }

                System.out.println("3");

            } catch (Exception ex) {
                ex.printStackTrace();
                e.ImprimirError("ProveedorDAO;ocupacional.bdatos.ProveedorDAO.Listar();msg:" + ex.getMessage());
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
    public ProveedorVO Cargar(String Pegeid) throws Exception {

        PreparedStatement ps = null;
        ResultSet rs = null;
                        ProveedorVO ProveedorVO = new ProveedorVO();
        boolean exito = false;
        this.getConexion();
        if (this.ConnEstado()) {

           

            try {
                StringBuilder sql = new StringBuilder();
                this.conn.getCon().setAutoCommit(false);
                //                ***cambio dao***
                sql.append("SELECT * FROM proveedores WHERE pege_id= ?");
                ps = this.conn.getCon().prepareStatement(sql.toString());
                ps.setString(1, Pegeid);
                rs = ps.executeQuery();
                if (rs.isBeforeFirst()) {
                    
                    while (rs.next()) {

                  
                            ProveedorVO.setProv_id(rs.getString("prov_id"));
                        ProveedorVO.setPege_id(rs.getString("pege_id"));
                        ProveedorVO.setProv_registradopor(rs.getString("prov_registradopor"));
                        ProveedorVO.setProv_fechacambio(rs.getTimestamp("prov_fechacambio"));
                        ProveedorVO.setProv_observacion(rs.getString("prov_observacion"));
                        ProveedorVO.setProv_tipo(rs.getString("prov_tipo"));
                        ProveedorVO.setProv_estado(rs.getString("prov_estado"));

//                ***cambio dao***
                    }

                }

                System.out.println("3");

            } catch (Exception ex) {
                ex.printStackTrace();
                e.ImprimirError("ProveedorDAO;ocupacional.bdatos.ProveedorDAO.Listar();msg:" + ex.getMessage());
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
        return ProveedorVO;
    }

    
}
