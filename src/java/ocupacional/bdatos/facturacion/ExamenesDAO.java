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
import ocupacional.valueobjects.facturacion.ExamenesVO;
import valeria.conexion.ConexionAplicacion;
import valeria.metodos.Cadenas;
import valeria.response.Mediador;

/**
 *
 * @author D4V3
 */
public class ExamenesDAO {

    Cadenas c = new Cadenas();
    Mediador e = null;
    ConexionAplicacion conn = null;
    private boolean closed = true;

    public ExamenesDAO(Mediador e) {
        this.e = e;
    }

    public ExamenesDAO(Mediador e, ConexionAplicacion conn) {
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

    public boolean Insertar(ExamenesVO ExamenesVO) throws Exception {
        PreparedStatement ps = null;
        ResultSet keyRS = null;
        boolean exito = false;
        this.getConexion();
        if (this.ConnEstado()) {
            try {
                this.conn.getCon().setAutoCommit(false);
                StringBuilder sql = new StringBuilder();

//                ***cambio dao***
                sql.append("INSERT INTO `examenes`( `exam_descripcion`, `exam_registradopor`, `exam_fechacambio`, `exam_observacion`, `exam_tipo`)"
                        + " VALUES (?,?,?,?,?)");
                ps = this.conn.getCon().prepareStatement(sql.toString(), PreparedStatement.RETURN_GENERATED_KEYS);
                int a = 1;
                ps.setString(a++, ExamenesVO.getExam_descripcion());
                ps.setString(a++, ExamenesVO.getExam_registradopor());
                ps.setTimestamp(a++, ExamenesVO.getExam_fechacambio());
                ps.setString(a++, ExamenesVO.getExam_observacion());
                ps.setString(a++, ExamenesVO.getExam_tipo());
                ps.executeUpdate();
                keyRS = ps.getGeneratedKeys();
                if (keyRS.next()) {
                    ExamenesVO.setExam_id(keyRS.getString(1));
                }
                if (ExamenesVO.getExam_id() != null) {

//                ***cambio dao***
                    exito = true;
                }
            } catch (Exception ex) {
                e.ImprimirError("ExamenesDAO;ocupacional.bdatos.ExamenesDAO.Insertar();msg:" + ex.getMessage());
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

    public boolean Actualizar(ExamenesVO ExamenesVO) throws Exception {
        PreparedStatement ps = null;
        ResultSet keyRS = null;
        boolean exito = false;
        this.getConexion();
        if (this.ConnEstado()) {
            try {
                this.conn.getCon().setAutoCommit(false);
                StringBuilder sql = new StringBuilder();
//                ***cambio dao***
                sql.append("UPDATE `examenes` \n"
                        + "SET\n"
                        + "`exam_descripcion`=?,\n"
                        + "`exam_registradopor`=?,\n"
                        + "`exam_fechacambio`=?,\n"
                        + "`exam_observacion`=?,\n"
                        + "`exam_estado`=?,\n"
                        + "`exam_tipo`=?\n"
                        + " WHERE  `exam_id`=?");
                ps = this.conn.getCon().prepareStatement(sql.toString());
                int a = 1;
                ps.setString(a++, ExamenesVO.getExam_descripcion());
                ps.setString(a++, ExamenesVO.getExam_registradopor());
                ps.setTimestamp(a++, ExamenesVO.getExam_fechacambio());
                ps.setString(a++, ExamenesVO.getExam_observacion());
                ps.setString(a++, ExamenesVO.getExam_estado());
                ps.setString(a++, ExamenesVO.getExam_tipo());
                ps.setString(a++, ExamenesVO.getExam_id());

//                ***cambio dao***
                ps.executeUpdate();
                exito = true;
            } catch (Exception ex) {
                e.ImprimirError("ExamenesDAO;ocupacional.bdatos.ExamenesDAO.Actualizar();msg:"
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

    public boolean Eliminar(ExamenesVO ExamenesVO) throws Exception {
        PreparedStatement ps = null;
        ResultSet keyRS = null;
        boolean exito = false;
        this.getConexion();
        if (this.ConnEstado()) {
            try {
                this.conn.getCon().setAutoCommit(false);
                StringBuilder sql = new StringBuilder();
                //                ***cambio dao***
                sql.append("DELETE FROM javap.examenes   \n\t");
                sql.append("WHERE   exam_id = ? \n\t");
                ps = this.conn.getCon().prepareStatement(sql.toString());
                int a = 1;
                ps.setString(a++, ExamenesVO.getExam_id());
                //                ***cambio dao***
                ps.executeUpdate();
                exito = true;
            } catch (Exception ex) {
                e.ImprimirError("ExamenesDAO;ocupacional.bdatos.ExamenesDAO.Eliminar();msg:" + ex.getMessage());
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
        ArrayList<ExamenesVO> lista = null;
        boolean exito = false;
        this.getConexion();
        if (this.ConnEstado()) {
            try {
                StringBuilder sql = new StringBuilder();
                this.conn.getCon().setAutoCommit(false);
                //                ***cambio dao***
                sql.append("SELECT * FROM `examenes`");
                ps = this.conn.getCon().prepareStatement(sql.toString());
                rs = ps.executeQuery();
                if (rs.isBeforeFirst()) {
                    lista = new ArrayList<ExamenesVO>();
                    while (rs.next()) {
                        ExamenesVO ExamenesVO = new ExamenesVO();

                        ExamenesVO.setExam_id(rs.getString("exam_id"));
                        ExamenesVO.setExam_descripcion(rs.getString("exam_descripcion"));
                        ExamenesVO.setExam_observacion(rs.getString("exam_observacion"));
                        ExamenesVO.setExam_tipo(rs.getString("exam_tipo"));
                        ExamenesVO.setExam_estado(rs.getString("exam_estado"));

//                ***cambio dao***
                        lista.add(ExamenesVO);
                    }
                }
            } catch (Exception ex) {
                e.ImprimirError("ExamenesDAO;ocupacional.bdatos.ExamenesDAO.Listar();msg:" + ex.getMessage());
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
