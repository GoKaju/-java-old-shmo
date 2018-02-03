/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ocupacional.bdatos.historiaclinica;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import ocupacional.valueobjects.historiaclinica.AntecedentesOcupacionalesEspecificosVO;
import valeria.conexion.ConexionAplicacion;
import valeria.metodos.Cadenas;
import valeria.response.Mediador;

/**
 *
 * @author Sebas
 */
public class AntecedentesOcupacionalesEspecificosDAO {

    Cadenas c = new Cadenas();
    Mediador e = null;
    ConexionAplicacion conn = null;
    private boolean closed = true;

    public AntecedentesOcupacionalesEspecificosDAO(Mediador e) {
        this.e = e;
    }

    public AntecedentesOcupacionalesEspecificosDAO(Mediador e, ConexionAplicacion conn) {
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

    public boolean Insertar(AntecedentesOcupacionalesEspecificosVO AntecedentesOcupacionalesEspecificosVO) throws Exception {
        PreparedStatement ps = null;
        ResultSet keyRS = null;
        boolean exito = false;
        this.getConexion();
        if (this.ConnEstado()) {
            try {
                this.conn.getCon().setAutoCommit(false);
                StringBuilder sql = new StringBuilder();

//                ***cambio dao***
                sql.append("INSERT INTO `antecedentesocupacionalesespecificos`(`anoc_id`, `anoe_empresa`, `anoe_cargo`, `anoe_tiempo`, `anoe_registradopor`, `anoe_fechacambio`)");
                sql.append(" VALUES");
                sql.append(" (?,?,?,?,?,?)");
                ps = this.conn.getCon().prepareStatement(sql.toString(), PreparedStatement.RETURN_GENERATED_KEYS);
                int a = 1;
                ps.setString(a++, AntecedentesOcupacionalesEspecificosVO.getAnoc_id());
                ps.setString(a++, AntecedentesOcupacionalesEspecificosVO.getAnoe_empresa());
                ps.setString(a++, AntecedentesOcupacionalesEspecificosVO.getAnoe_cargo());
                ps.setString(a++, AntecedentesOcupacionalesEspecificosVO.getAnoe_tiempo());
                ps.setString(a++, AntecedentesOcupacionalesEspecificosVO.getAnoe_registradopor());
                ps.setTimestamp(a++, AntecedentesOcupacionalesEspecificosVO.getAnoe_fechacambio());
                ps.executeUpdate();
                keyRS = ps.getGeneratedKeys();
                if (keyRS.next()) {
                    AntecedentesOcupacionalesEspecificosVO.setAnoe_id(keyRS.getString(1));
                }
                if (AntecedentesOcupacionalesEspecificosVO.getAnoe_id() != null) {

//                ***cambio dao***
                    exito = true;
                }
            } catch (Exception ex) {
                e.ImprimirError("AntecedentesOucpacionalesEspecificosDAO;ocupacional.bdatos.AntecedentesOucpacionalesEspecificosDAO.Insertar();msg:" + ex.getMessage());
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

    public boolean Actualizar(AntecedentesOcupacionalesEspecificosVO AntecedentesOcupacionalesEspecificosVO) throws Exception {
        PreparedStatement ps = null;
        ResultSet keyRS = null;
        boolean exito = false;
        this.getConexion();
        if (this.ConnEstado()) {
            try {
                this.conn.getCon().setAutoCommit(false);
                StringBuilder sql = new StringBuilder();
//                ***cambio dao***
                sql.append("UPDATE `antecedentesocupacionalesespecificos` SET "
                        + "`anoc_id`=?,"
                        + "`anoe_empresa`=?,"
                        + "`anoe_cargo`=?,"
                        + "`anoe_tiempo`=?,"
                        + "`anoe_registradopor`=?,"
                        + "`anoe_fechacambio`=? WHERE `anoe_id`=?");
                ps = this.conn.getCon().prepareStatement(sql.toString());
                int a = 1;
                ps.setString(a++, AntecedentesOcupacionalesEspecificosVO.getAnoc_id());
                ps.setString(a++, AntecedentesOcupacionalesEspecificosVO.getAnoe_empresa());
                ps.setString(a++, AntecedentesOcupacionalesEspecificosVO.getAnoe_tiempo());
                ps.setString(a++, AntecedentesOcupacionalesEspecificosVO.getAnoe_registradopor());
                ps.setTimestamp(a++, AntecedentesOcupacionalesEspecificosVO.getAnoe_fechacambio());
                ps.setString(a++, AntecedentesOcupacionalesEspecificosVO.getAnoe_id());

//                ***cambio dao***
                ps.executeUpdate();
                exito = true;
            } catch (Exception ex) {
                e.ImprimirError("AntecedentesOucpacionalesEspecificosDAO;ocupacional.bdatos.AntecedentesOucpacionalesEspecificosDAO.Actualizar();msg:" + ex.getMessage());
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

    public boolean Eliminar(AntecedentesOcupacionalesEspecificosVO AntecedentesOcupacionalesEspecificosVO) throws Exception {
        PreparedStatement ps = null;
        ResultSet keyRS = null;
        boolean exito = false;
        this.getConexion();
        if (this.ConnEstado()) {
            try {
                this.conn.getCon().setAutoCommit(false);
                StringBuilder sql = new StringBuilder();
                //                ***cambio dao***
                sql.append("DELETE FROM `antecedentesocupacionalesespecificos` WHERE `anoc_id`=?");
                ps = this.conn.getCon().prepareStatement(sql.toString());
                int a = 1;
                ps.setString(1, AntecedentesOcupacionalesEspecificosVO.getAnoc_id());
                //                ***cambio dao***
                ps.executeUpdate();
                exito = true;
            } catch (Exception ex) {
                ex.printStackTrace();
                e.ImprimirError("AntecedentesOucpacionalesEspecificosDAO;ocupacional.bdatos.AntecedentesOucpacionalesEspecificosDAO.Eliminar();msg:" + ex.getMessage());
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

    public ArrayList<AntecedentesOcupacionalesEspecificosVO> Listar(String anoc_id) throws Exception {

        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<AntecedentesOcupacionalesEspecificosVO> lista = new ArrayList<AntecedentesOcupacionalesEspecificosVO>();
        boolean exito = false;
        this.getConexion();
        if (this.ConnEstado()) {

            try {
                StringBuilder sql = new StringBuilder();
                this.conn.getCon().setAutoCommit(false);
                //                ***cambio dao***
                sql.append("SELECT `antecedentesocupacionalesespecificos` FROM clientes WHERE anoc_id=?");
                ps = this.conn.getCon().prepareStatement(sql.toString());

                ps.setString(1, anoc_id);

                rs = ps.executeQuery();
                if (rs.isBeforeFirst()) {

                    while (rs.next()) {
                        AntecedentesOcupacionalesEspecificosVO AntecedentesOcupacionalesEspecificosVO = new AntecedentesOcupacionalesEspecificosVO();

                        AntecedentesOcupacionalesEspecificosVO.setAnoe_id(rs.getString("anoe_id"));
                        AntecedentesOcupacionalesEspecificosVO.setAnoc_id(rs.getString("anoc_id"));
                        AntecedentesOcupacionalesEspecificosVO.setAnoe_empresa(rs.getString("anoe_empresa"));
                        AntecedentesOcupacionalesEspecificosVO.setAnoe_cargo(rs.getString("anoe_cargo"));
                        AntecedentesOcupacionalesEspecificosVO.setAnoe_tiempo(rs.getString("anoe_tiempo"));
                        AntecedentesOcupacionalesEspecificosVO.setAnoe_registradopor(rs.getString("anoe_registradopor"));
                        AntecedentesOcupacionalesEspecificosVO.setAnoe_fechacambio(rs.getTimestamp("anoe_fechacambio"));

//                ***cambio dao***
                        lista.add(AntecedentesOcupacionalesEspecificosVO);
                    }

                }

                System.out.println("3");

            } catch (Exception ex) {
                ex.printStackTrace();
                e.ImprimirError("AntecedentesOucpacionalesEspecificosDAO;ocupacional.bdatos.AntecedentesOucpacionalesEspecificosDAO.Listar();msg:" + ex.getMessage());
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

    public AntecedentesOcupacionalesEspecificosVO Cargar(String anoe_id) throws Exception {

        PreparedStatement ps = null;
        ResultSet rs = null;
        AntecedentesOcupacionalesEspecificosVO AntecedentesOcupacionalesEspecificosVO = new AntecedentesOcupacionalesEspecificosVO();
        boolean exito = false;
        this.getConexion();
        if (this.ConnEstado()) {

            try {
                StringBuilder sql = new StringBuilder();
                this.conn.getCon().setAutoCommit(false);
                //                ***cambio dao***
                sql.append("SELECT * antecedentesocupacionalesespecificos FROM  WHERE anoe_id= ?");
                ps = this.conn.getCon().prepareStatement(sql.toString());
                ps.setString(1, anoe_id);
                rs = ps.executeQuery();
                if (rs.isBeforeFirst()) {

                    while (rs.next()) {

                          AntecedentesOcupacionalesEspecificosVO = new AntecedentesOcupacionalesEspecificosVO();

                        AntecedentesOcupacionalesEspecificosVO.setAnoe_id(rs.getString("anoe_id"));
                        AntecedentesOcupacionalesEspecificosVO.setAnoc_id(rs.getString("anoc_id"));
                        AntecedentesOcupacionalesEspecificosVO.setAnoe_empresa(rs.getString("anoe_empresa"));
                        AntecedentesOcupacionalesEspecificosVO.setAnoe_cargo(rs.getString("anoe_cargo"));
                        AntecedentesOcupacionalesEspecificosVO.setAnoe_tiempo(rs.getString("anoe_tiempo"));
                        AntecedentesOcupacionalesEspecificosVO.setAnoe_registradopor(rs.getString("anoe_registradopor"));
                        AntecedentesOcupacionalesEspecificosVO.setAnoe_fechacambio(rs.getTimestamp("anoe_fechacambio"));

//                ***cambio dao***
                    }

                }

                System.out.println("3");

            } catch (Exception ex) {
                ex.printStackTrace();
                e.ImprimirError("AntecedentesOucpacionalesEspecificosDAO;ocupacional.bdatos.AntecedentesOucpacionalesEspecificosDAO.Listar();msg:" + ex.getMessage());
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
        return AntecedentesOcupacionalesEspecificosVO;
    }

}
