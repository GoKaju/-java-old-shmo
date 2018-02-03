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
import ocupacional.valueobjects.facturacion.ProveedoresExamenesVO;
import valeria.conexion.ConexionAplicacion;
import valeria.metodos.Cadenas;
import valeria.response.Mediador;

/**
 *
 * @author D4V3
 */
public class ProveedoresExamenesDAO {
    
    
    

    Cadenas c = new Cadenas();
    Mediador e = null;
    ConexionAplicacion conn = null;
    private boolean closed = true;

    public ProveedoresExamenesDAO(Mediador e) {
        this.e = e;
    }

    public ProveedoresExamenesDAO(Mediador e, ConexionAplicacion conn) {
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

    public boolean Insertar(ProveedoresExamenesVO ProveedoresExamenesVO) throws Exception {
        PreparedStatement ps = null;
        ResultSet keyRS = null;
        boolean exito = false;
        this.getConexion();
        if (this.ConnEstado()) {
            try {
                this.conn.getCon().setAutoCommit(false);
                StringBuilder sql = new StringBuilder();

//                ***cambio dao***
                sql.append("INSERT INTO `proveedores_examenes`\n"
                        + "( `prov_id`, `exam_id`, `prex_observacion`, `prex_registradopor`) \n"
                        + "VALUES (?,?,?,?)");
                ps = this.conn.getCon().prepareStatement(sql.toString(), PreparedStatement.RETURN_GENERATED_KEYS);
                int a = 1;
                ps.setString(a++, ProveedoresExamenesVO.getProv_id());
                ps.setString(a++, ProveedoresExamenesVO.getExam_id());
                ps.setString(a++, ProveedoresExamenesVO.getPrex_observacion());
                ps.setString(a++, ProveedoresExamenesVO.getPrex_registradopor());
                ps.executeUpdate();
                keyRS = ps.getGeneratedKeys();
                if (keyRS.next()) {
                    ProveedoresExamenesVO.setPrex_id(keyRS.getString(1));
                }
                if (ProveedoresExamenesVO.getPrex_id()!= null) {

//                ***cambio dao***
                    exito = true;
                }
            } catch (Exception ex) {
                e.ImprimirError("ProveedoresExamenesDAO;ocupacional.bdatos.ProveedoresExamenesDAO.Insertar();msg:" + ex.getMessage());
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

//    public boolean Actualizar(ProveedoresExamenesVO ProveedoresExamenesVO) throws Exception {
//        PreparedStatement ps = null;
//        ResultSet keyRS = null;
//        boolean exito = false;
//        this.getConexion();
//        if (this.ConnEstado()) {
//            try {
//                this.conn.getCon().setAutoCommit(false);
//                StringBuilder sql = new StringBuilder();
////                ***cambio dao***
//                sql.append("UPDATE `clientes` \n"
//                        + "SET \n"
//                        + "`pege_id`=? ,\n"
//                        + "`clie_registradopor`=? ,\n"
//                        + "`clie_fechacambio`=? ,\n"
//                        + "`clie_observacion`=? ,\n"
//                        + "`clie_tipo`=? ,\n"
//                        + "`clie_estado`=?\n"
//                        + "WHERE `clie_id`=?");
//                ps = this.conn.getCon().prepareStatement(sql.toString());
//                int a = 1;
//                ps.setString(a++, ProveedoresExamenesVO.getPege_id());
//                ps.setString(a++, ProveedoresExamenesVO.getClie_registradopor());
//                ps.setTimestamp(a++, ProveedoresExamenesVO.getClie_fechacambio());
//                ps.setString(a++, ProveedoresExamenesVO.getClie_observacion());
//                ps.setString(a++, ProveedoresExamenesVO.getClie_tipo());
//                ps.setString(a++, ProveedoresExamenesVO.getClie_estado());
//                ps.setString(a++, ProveedoresExamenesVO.getClie_id());
//
////                ***cambio dao***
//                ps.executeUpdate();
//                exito = true;
//            } catch (Exception ex) {
//                e.ImprimirError("ProveedoresExamenesDAO;ocupacional.bdatos.ProveedoresExamenesDAO.Actualizar();msg:" + ex.getMessage());
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

    public boolean Eliminar(ProveedoresExamenesVO ProveedoresExamenesVO) throws Exception {
        PreparedStatement ps = null;
        ResultSet keyRS = null;
        boolean exito = false;
        this.getConexion();
        if (this.ConnEstado()) {
            try {
                this.conn.getCon().setAutoCommit(false);
                StringBuilder sql = new StringBuilder();
                //                ***cambio dao***
                sql.append("DELETE FROM javap.proveedores_examenes   \n\t");
                sql.append("WHERE   prex_id = ? \n\t");
                ps = this.conn.getCon().prepareStatement(sql.toString());
                int a = 1;
                ps.setString(1, ProveedoresExamenesVO.getPrex_id());
                //                ***cambio dao***
                ps.executeUpdate();
                exito = true;
            } catch (Exception ex) {
                ex.printStackTrace();
                e.ImprimirError("ProveedoresExamenesDAO;ocupacional.bdatos.ProveedoresExamenesDAO.Eliminar();msg:" + ex.getMessage());
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

    public ArrayList<ProveedoresExamenesVO> Listar(String  id) throws Exception {

        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<ProveedoresExamenesVO> lista = new ArrayList<ProveedoresExamenesVO>();
        boolean exito = false;
        this.getConexion();
        if (this.ConnEstado()) {

           

            try {
                StringBuilder sql = new StringBuilder();
                this.conn.getCon().setAutoCommit(false);
                //                ***cambio dao***
                sql.append("SELECT * FROM proveedores_examenes where prov_id=?");
                ps = this.conn.getCon().prepareStatement(sql.toString());
                ps.setString(1, id);
                rs = ps.executeQuery();
                if (rs.isBeforeFirst()) {

                    

                    
                    while (rs.next()) {
                        ProveedoresExamenesVO ProveedoresExamenesVO = new ProveedoresExamenesVO();

                        ProveedoresExamenesVO.setPrex_id(rs.getString("prex_id"));
                        ProveedoresExamenesVO.setProv_id(rs.getString("prov_id"));
                        ProveedoresExamenesVO.setExam_id(rs.getString("exam_id"));
                        ProveedoresExamenesVO.setPrex_observacion(rs.getString("prex_observacion"));
                        ProveedoresExamenesVO.setPrex_registradopor(rs.getString("prex_registradopor"));
                        ProveedoresExamenesVO.setPrex_fechacambio(rs.getTimestamp("prex_fechacambio"));
                        ProveedoresExamenesVO.setPrex_estado(rs.getString("prex_estado"));

//                ***cambio dao***
                        lista.add(ProveedoresExamenesVO);
                    }

                }

                System.out.println("3");

            } catch (Exception ex) {
                ex.printStackTrace();
                e.ImprimirError("ProveedoresExamenesDAO;ocupacional.bdatos.ProveedoresExamenesDAO.Listar();msg:" + ex.getMessage());
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
//    public ProveedoresExamenesVO Cargar(String Pegeid) throws Exception {
//
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//                        ProveedoresExamenesVO ProveedoresExamenesVO = new ProveedoresExamenesVO();
//        boolean exito = false;
//        this.getConexion();
//        if (this.ConnEstado()) {
//
//           
//
//            try {
//                StringBuilder sql = new StringBuilder();
//                this.conn.getCon().setAutoCommit(false);
//                //                ***cambio dao***
//                sql.append("SELECT * FROM clientes WHERE pege_id= ?");
//                ps = this.conn.getCon().prepareStatement(sql.toString());
//                ps.setString(1, Pegeid);
//                rs = ps.executeQuery();
//                if (rs.isBeforeFirst()) {
//                    
//                    while (rs.next()) {
//
//                  
//                        ProveedoresExamenesVO.setClie_id(rs.getString("clie_id"));
//                        ProveedoresExamenesVO.setPege_id(rs.getString("pege_id"));
//                        ProveedoresExamenesVO.setClie_observacion(rs.getString("clie_observacion"));
//                        ProveedoresExamenesVO.setClie_tipo(rs.getString("clie_tipo"));
//                        ProveedoresExamenesVO.setClie_estado(rs.getString("clie_estado"));
//
////                ***cambio dao***
//                    }
//
//                }
//
//                System.out.println("3");
//
//            } catch (Exception ex) {
//                ex.printStackTrace();
//                e.ImprimirError("ProveedoresExamenesDAO;ocupacional.bdatos.ProveedoresExamenesDAO.Listar();msg:" + ex.getMessage());
//            } finally {
//                if (this.closed && this.conn.getCon() != null) {
//                    this.conn.getCon().commit();
//                    this.conn.getCon().close();
//                    this.conn = null;
//                    System.out.println("entre");
//                }
//                try {
//                    if (rs != null) {
//                        rs.close();
//                    }
//                    if (ps != null) {
//                        ps.close();
//                    }
//                } catch (Exception ex) {
//                    ex.printStackTrace();
//                }
//            }
//        }
//        return ProveedoresExamenesVO;
//    }

}
