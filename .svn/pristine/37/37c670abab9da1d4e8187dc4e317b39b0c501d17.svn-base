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
import ocupacional.valueobjects.FuncionalidadesVO;
import valeria.conexion.ConexionAplicacion;
import valeria.metodos.Cadenas;
import valeria.response.Mediador;

/**
 *
 * @author VALERIA
 */
public class FuncionalidadesDAO {

    Cadenas c = new Cadenas();
    Mediador e = null;
    ConexionAplicacion conn = null;
    private boolean closed = true;

    public FuncionalidadesDAO(Mediador e) {
        this.e = e;
    }

    public FuncionalidadesDAO(Mediador e, ConexionAplicacion conn) {
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

    public boolean Insertar(FuncionalidadesVO FuncionalidadesVO) throws Exception {
        PreparedStatement ps = null;
        ResultSet keyRS = null;
        boolean exito = false;
        this.getConexion();
        if (this.ConnEstado()) {
            try {
                this.conn.getCon().setAutoCommit(false);
                StringBuffer sql = new StringBuffer();
                sql.append("INSERT INTO javap.FuncionalidadesVO(func_descripcion, func_url, func_codigo, func_registradopor, func_fechacambio, func_class) VALUES (?,?,?,?,?,?) ");
                ps = this.conn.getCon().prepareStatement(sql.toString(), PreparedStatement.RETURN_GENERATED_KEYS);
                int a = 1;
                ps.setString(a++, FuncionalidadesVO.getFunc_descripcion());
                ps.setString(a++, FuncionalidadesVO.getFunc_url());
                ps.setString(a++, FuncionalidadesVO.getFunc_codigo());
                ps.setString(a++, FuncionalidadesVO.getFunc_registradopor());
                ps.setTimestamp(a++, FuncionalidadesVO.getFunc_fechacambio());
                ps.setString(a++, FuncionalidadesVO.getFunc_class());
                ps.executeUpdate(); 
                keyRS = ps.getGeneratedKeys();
                if (keyRS.next()) {
                    FuncionalidadesVO.setFunc_id(keyRS.getString(1));
                }
                if (FuncionalidadesVO.getFunc_id() != null) {
                    exito = true;
                }
            } catch (Exception ex) {
                e.ImprimirError("FuncionalidadesDAO;ocupacional.bdatos.FuncionalidadesDAO.Insertar();msg:" + ex.getMessage());
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

    public boolean Actualizar(FuncionalidadesVO FuncionalidadesVO) throws Exception {
        PreparedStatement ps = null;
        ResultSet keyRS = null;
        boolean exito = false;
        this.getConexion();
        if (this.ConnEstado()) {
            try {
                this.conn.getCon().setAutoCommit(false);
                StringBuffer sql = new StringBuffer();
                sql.append("UPDATE  javap.Funcionalidades SET  \n\t");
                sql.append("        func_descripcion = ?,  \n\t");
                sql.append("        func_url = ?,  \n\t");
                sql.append("        func_codigo = ?,  \n\t");
                sql.append("        func_registradopor = ?  \n\t");
                sql.append("        func_fechacambio = ?,  \n\t");
                sql.append("        func_class = ?,  \n\t");
                sql.append("WHERE   acec_id = ? \n\t");
                ps = this.conn.getCon().prepareStatement(sql.toString());
                int a = 1;
                ps.setString(a++, FuncionalidadesVO.getFunc_descripcion());
                ps.setString(a++, FuncionalidadesVO.getFunc_url());
                ps.setString(a++, FuncionalidadesVO.getFunc_codigo());
                ps.setString(a++, FuncionalidadesVO.getFunc_registradopor());
                ps.setTimestamp(a++, FuncionalidadesVO.getFunc_fechacambio());
                ps.setString(a++, FuncionalidadesVO.getFunc_id());
                ps.executeUpdate();
                exito = true;
            } catch (Exception ex) {
                e.ImprimirError("FuncionalidadesDAO;ocupacional.bdatos.FuncionalidadesDAO.Actualizar();msg:" + ex.getMessage());
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

    public boolean Eliminar(FuncionalidadesVO FuncionalidadesVO) throws Exception {
        PreparedStatement ps = null;
        ResultSet keyRS = null;
        boolean exito = false;
        this.getConexion();
        if (this.ConnEstado()) {
            try {
                this.conn.getCon().setAutoCommit(false);
                StringBuffer sql = new StringBuffer();
                sql.append("DELETE FROM javap.Funcionalidades  \n\t");
                sql.append("WHERE   func_id = ? \n\t");
                ps = this.conn.getCon().prepareStatement(sql.toString());
                int a = 1;
                ps.setString(a++, FuncionalidadesVO.getFunc_id());
                ps.executeUpdate();
                exito = true;
            } catch (Exception ex) {
                e.ImprimirError("FuncionalidadesDAO;ocupacional.bdatos.FuncionalidadesDAO.Eliminar();msg:" + ex.getMessage());
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

    public Object Listar() throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<FuncionalidadesVO> lista = null;
        boolean exito = false;
        this.getConexion();
        if (this.ConnEstado()) {

            try {
                   this.conn.getCon().setAutoCommit(false);
                StringBuilder sql = new StringBuilder();
                sql.append("SELECT  *  \n\t");
                sql.append("FROM    javap.funcionalidades \n\t");
                sql.append("ORDER BY func_codigo \n\t");
                ps = this.conn.getCon().prepareStatement(sql.toString());
                rs = ps.executeQuery();
                if (rs.isBeforeFirst()) {
                    lista = new ArrayList<FuncionalidadesVO>();
                    while (rs.next()) {
                        FuncionalidadesVO FuncionalidadesVO = new FuncionalidadesVO();
                        FuncionalidadesVO.setFunc_id(rs.getString("func_id"));
                        FuncionalidadesVO.setFunc_descripcion(rs.getString("func_descripcion"));
                        FuncionalidadesVO.setFunc_url(rs.getString("func_url"));
                        FuncionalidadesVO.setFunc_codigo(rs.getString("func_codigo"));
                        lista.add(FuncionalidadesVO);
                    }
                }
            } catch (Exception ex) {
                e.ImprimirError("FuncionalidadesDAO;ocupacional.bdatos.FuncionalidadesDAO.Listar();msg:" + ex.getMessage());
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
    public FuncionalidadesVO Cargar(String id) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
                        FuncionalidadesVO FuncionalidadesVO = new FuncionalidadesVO();
        boolean exito = false;
        this.getConexion();
        if (this.ConnEstado()) {
            try {
                                this.conn.getCon().setAutoCommit(false);

                StringBuilder sql = new StringBuilder();
                sql.append("SELECT  *  \n\t");
                sql.append("FROM    javap.funcionalidades \n\t");
                sql.append("Where func_id=? \n\t");
                ps = this.conn.getCon().prepareStatement(sql.toString());
                ps.setString(1, id);
                System.out.println("id--"+id);
                rs = ps.executeQuery();
                if (rs.isBeforeFirst()) {
                    if (rs.next()) {
                        FuncionalidadesVO.setFunc_id(rs.getString("func_id"));
                        FuncionalidadesVO.setFunc_descripcion(rs.getString("func_descripcion"));
                        FuncionalidadesVO.setFunc_url(rs.getString("func_url"));
                        FuncionalidadesVO.setFunc_codigo(rs.getString("func_codigo"));
//                        System.out.println("des--"+FuncionalidadesVO.getFunc_descripcion());
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                e.ImprimirError("FuncionalidadesDAO;ocupacional.bdatos.FuncionalidadesDAO.Listar();msg:" + ex.getMessage());
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
        System.out.println("bien");
        return FuncionalidadesVO;
    }

}
