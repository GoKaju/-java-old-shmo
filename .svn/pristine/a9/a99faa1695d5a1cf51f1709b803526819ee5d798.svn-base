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
import ocupacional.valueobjects.CieVO;
import valeria.conexion.ConexionAplicacion;
import valeria.metodos.Cadenas;
import valeria.response.Mediador;

/**
 *
 * @author VALERIA
 */
public class CieDAO {

    Cadenas c = new Cadenas();
    Mediador e = null;
    ConexionAplicacion conn = null;
    private boolean closed = true;

    public CieDAO(Mediador e) {
        this.e = e;
    }

    public CieDAO(Mediador e, ConexionAplicacion conn) {
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

    public ArrayList<CieVO> Listarxcodigo(String txt) throws Exception {
        txt = "%" + txt + "%";
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<CieVO> lista = null;
        this.getConexion();
        if (this.ConnEstado()) {
            try {
                StringBuilder sql = new StringBuilder();
                sql.append("select * FROM javaphc.cie c \n"
                        + "WHERE \n"
                        + "c.cie_id like ?\n"
                        + "ORDER BY c.cie_id+0 ASC");
                ps = this.conn.getCon().prepareStatement(sql.toString());
                ps.setString(1, txt);
                rs = ps.executeQuery();
                if (rs.isBeforeFirst()) {
                    lista = new ArrayList<CieVO>();
                    while (rs.next()) {
                        CieVO vo = new CieVO();
                        vo.setId(rs.getString("cie_id"));
                        vo.setDescripcion(rs.getString("cie_desc"));
                        vo.setGrupo(rs.getString("cie_grupo"));
                        lista.add(vo);
                    }
                }
            } catch (Exception ex) {
                e.ImprimirError(":::::::::::: " + this.getClass() + " msg:" + ex.getMessage());
            } finally {
                if (this.closed && this.conn.getCon() != null) {
//                    this.conn.getCon().commit();
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

    public ArrayList<CieVO> Listarxdescripcion(String txt) throws Exception {
        txt = "%" + txt + "%";
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<CieVO> lista = null;
        this.getConexion();
        if (this.ConnEstado()) {
            try {
                StringBuilder sql = new StringBuilder();
                sql.append("select * FROM javaphc.cie c \n"
                        + "WHERE \n"
                        + "c.cie_desc like ?\n"
                        + "ORDER BY c.cie_id+0 ASC");
                ps = this.conn.getCon().prepareStatement(sql.toString());
                ps.setString(1, txt);
                rs = ps.executeQuery();
                if (rs.isBeforeFirst()) {
                    lista = new ArrayList<CieVO>();
                    while (rs.next()) {
                        CieVO vo = new CieVO();
                        vo.setId(rs.getString("cie_id"));
                        vo.setDescripcion(rs.getString("cie_desc"));
                        vo.setGrupo(rs.getString("cie_grupo"));
                        lista.add(vo);
                    }
                }
            } catch (Exception ex) {
                e.ImprimirError(":::::::::::: " + this.getClass() + " msg:" + ex.getMessage());
            } finally {
                if (this.closed && this.conn.getCon() != null) {
//                    this.conn.getCon().commit();
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

    public ArrayList<CieVO> ListarCapitulos() throws Exception {

        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<CieVO> lista = null;
        this.getConexion();
        if (this.ConnEstado()) {
            try {
                StringBuilder sql = new StringBuilder();
                sql.append("SELECT * FROM javaphc.cie \n"
                        + "WHERE cie_id NOT REGEXP '[0-9]'\n"
                        + "AND cie_id not like '|%'\n"
                        + "ORDER BY cie_grupo+0 ASC");
                ps = this.conn.getCon().prepareStatement(sql.toString());
                rs = ps.executeQuery();
                if (rs.isBeforeFirst()) {
                    lista = new ArrayList<CieVO>();
                    while (rs.next()) {
                        CieVO vo = new CieVO();
                        vo.setId(rs.getString("cie_id"));
                        vo.setDescripcion(rs.getString("cie_desc"));
                        vo.setGrupo(rs.getString("cie_grupo"));
                        lista.add(vo);
                    }
                }
            } catch (Exception ex) {
                e.ImprimirError(":::::::::::: " + this.getClass() + " msg:" + ex.getMessage());
            } finally {
                if (this.closed && this.conn.getCon() != null) {
//                    this.conn.getCon().commit();
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

    public ArrayList<CieVO> listarVersiculo(String txt) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<CieVO> lista = null;
        this.getConexion();
        if (this.ConnEstado()) {
            try {
                StringBuilder sql = new StringBuilder();
                sql.append("SELECT * FROM javaphc.cie \n"
                        + "WHERE \n"
                        + " cie_id like '|" + txt + "%'\n"
                        + "AND\n"
                        + "cie_id REGEXP '" + txt + "[0-9]{1,2}'\n"
                        + "ORDER BY cie_id+0 ASC");
                ps = this.conn.getCon().prepareStatement(sql.toString());
                rs = ps.executeQuery();
                if (rs.isBeforeFirst()) {
                    lista = new ArrayList<CieVO>();
                    while (rs.next()) {
                        CieVO vo = new CieVO();
                        vo.setId(rs.getString("cie_id"));
                        vo.setDescripcion(rs.getString("cie_desc"));
                        vo.setGrupo(rs.getString("cie_grupo"));
                        lista.add(vo);
                    }
                }
            } catch (Exception ex) {
                e.ImprimirError(":::::::::::: " + this.getClass() + " msg:" + ex.getMessage());
            } finally {
                if (this.closed && this.conn.getCon() != null) {
//                    this.conn.getCon().commit();
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

    public ArrayList<CieVO> listarLiteral(String txt) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<CieVO> lista = null;
        this.getConexion();
        if (this.ConnEstado()) {
            try {
                StringBuilder sql = new StringBuilder();
                sql.append("SELECT * FROM javaphc.cie \n"
                        + "WHERE \n"
                        + " cie_grupo = '" + txt + "'\n"
                        + "ORDER BY cie_id+0 ASC");
                ps = this.conn.getCon().prepareStatement(sql.toString());
                rs = ps.executeQuery();
                if (rs.isBeforeFirst()) {
                    lista = new ArrayList<CieVO>();
                    while (rs.next()) {
                        CieVO vo = new CieVO();
                        vo.setId(rs.getString("cie_id"));
                        vo.setDescripcion(rs.getString("cie_desc"));
                        vo.setGrupo(rs.getString("cie_grupo"));
                        lista.add(vo);
                    }
                }
            } catch (Exception ex) {
                e.ImprimirError(":::::::::::: " + this.getClass() + " msg:" + ex.getMessage());
            } finally {
                if (this.closed && this.conn.getCon() != null) {
//                    this.conn.getCon().commit();
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

    public ArrayList<CieVO> listarSubLiteral(String txt) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<CieVO> lista = null;
        this.getConexion();
        if (this.ConnEstado()) {
            try {
                StringBuilder sql = new StringBuilder();
                sql.append("select * FROM javaphc.cie \n"
                        + "WHERE \n"
                        + " cie_id like '"+txt+"%'\n"
                        + "ORDER BY cie_id+0 ASC");
                ps = this.conn.getCon().prepareStatement(sql.toString());
                rs = ps.executeQuery();
                if (rs.isBeforeFirst()) {
                    lista = new ArrayList<CieVO>();
                    while (rs.next()) {
                        CieVO vo = new CieVO();
                        vo.setId(rs.getString("cie_id"));
                        vo.setDescripcion(rs.getString("cie_desc"));
                        vo.setGrupo(rs.getString("cie_grupo"));
                        lista.add(vo);
                    }
                }
            } catch (Exception ex) {
                e.ImprimirError(":::::::::::: " + this.getClass() + " msg:" + ex.getMessage());
            } finally {
                if (this.closed && this.conn.getCon() != null) {
//                    this.conn.getCon().commit();
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
