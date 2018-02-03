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
import ocupacional.valueobjects.NaturalesVO;
import valeria.conexion.ConexionAplicacion;
import valeria.metodos.Cadenas;
import valeria.response.Mediador;

/**
 *
 * @author VALERIA
 */
public class NaturalesDAO {

    Cadenas c = new Cadenas();
    Mediador e = null;
    ConexionAplicacion conn = null;
    private boolean closed = true;

    public NaturalesDAO(Mediador e) {
        this.e = e;
    }

    public NaturalesDAO(Mediador e, ConexionAplicacion conn) {
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

    public boolean Insertarvacio(NaturalesVO NaturalesVO) throws Exception {
        PreparedStatement ps = null;
        ResultSet keyRS = null;
        boolean exito = false;
        this.getConexion();
        if (this.ConnEstado()) {
            try {
                this.conn.getCon().setAutoCommit(false);
                StringBuilder sql = new StringBuilder();
                sql.append("INSERT INTO javap.Naturales(pege_id,natu_primernombre, natu_segundonombre, natu_primerapellido, natu_segundoapellido, natu_fechanacimiento, natu_gruposanguineo, natu_estrato, esci_id, natu_fechacambio, natu_registradopor, enti_ideps, enti_idarl, sexo_id) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");
                ps = this.conn.getCon().prepareStatement(sql.toString(), PreparedStatement.RETURN_GENERATED_KEYS);
                int a = 1;
                ps.setString(a++, NaturalesVO.getPege_id());
                ps.setString(a++, NaturalesVO.getNatu_primernombre());
                ps.setString(a++, NaturalesVO.getNatu_segundonombre());
                ps.setString(a++, NaturalesVO.getNatu_primerapellido());
                ps.setString(a++, NaturalesVO.getNatu_segundoapellido());
                ps.setTimestamp(a++, NaturalesVO.getNatu_fechanacimiento());
                ps.setString(a++, NaturalesVO.getNatu_gruposanguineo());
                ps.setString(a++, NaturalesVO.getNatu_estrato());
                ps.setString(a++, NaturalesVO.getEsci_id());
                ps.setTimestamp(a++, NaturalesVO.getNatu_fechacambio());
                ps.setString(a++, NaturalesVO.getNatu_registradopor());
                ps.setString(a++, NaturalesVO.getEnti_ideps());
                ps.setString(a++, NaturalesVO.getEnti_idarl());
                ps.setString(a++, NaturalesVO.getSexo_id());
                ps.executeUpdate();
//                keyRS = ps.getGeneratedKeys();
//                if (keyRS.next()) {
//                    NaturalesVO.setPege_id(keyRS.getString(1));
//                }
//                if (NaturalesVO.getPege_id() != null) {
//                    exito = true;
//                }
            } catch (Exception ex) {
                e.ImprimirError("NaturalesDAO;ocupacional.bdatos.NaturalesDAO.Insertar();msg:" + ex.getMessage());
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
    public boolean Insertar(NaturalesVO NaturalesVO) throws Exception {
        PreparedStatement ps = null;
        ResultSet keyRS = null;
        boolean exito = false;
        this.getConexion();
        if (this.ConnEstado()) {
            try {
                this.conn.getCon().setAutoCommit(false);
                StringBuilder sql = new StringBuilder();
                sql.append("INSERT INTO javap.Naturales(natu_primernombre, natu_segundonombre, natu_primerapellido, natu_segundoapellido, natu_fechanacimiento, natu_gruposanguineo, natu_estrato, esci_id, natu_fechacambio, natu_registradopor, enti_ideps, enti_idarl, sexo_id) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?) ");
                ps = this.conn.getCon().prepareStatement(sql.toString(), PreparedStatement.RETURN_GENERATED_KEYS);
                int a = 1;
                ps.setString(a++, NaturalesVO.getNatu_primernombre());
                ps.setString(a++, NaturalesVO.getNatu_segundonombre());
                ps.setString(a++, NaturalesVO.getNatu_primerapellido());
                ps.setString(a++, NaturalesVO.getNatu_segundoapellido());
                ps.setTimestamp(a++, NaturalesVO.getNatu_fechanacimiento());
                ps.setString(a++, NaturalesVO.getNatu_gruposanguineo());
                ps.setString(a++, NaturalesVO.getNatu_estrato());
                ps.setString(a++, NaturalesVO.getEsci_id());
                ps.setTimestamp(a++, NaturalesVO.getNatu_fechacambio());
                ps.setString(a++, NaturalesVO.getNatu_registradopor());
                ps.setString(a++, NaturalesVO.getEnti_ideps());
                ps.setString(a++, NaturalesVO.getEnti_idarl());
                ps.setString(a++, NaturalesVO.getSexo_id());
                ps.executeUpdate();
                keyRS = ps.getGeneratedKeys();
                if (keyRS.next()) {
                    NaturalesVO.setPege_id(keyRS.getString(1));
                }
                if (NaturalesVO.getPege_id() != null) {
                    exito = true;
                }
            } catch (Exception ex) {
                e.ImprimirError("NaturalesDAO;ocupacional.bdatos.NaturalesDAO.Insertar();msg:" + ex.getMessage());
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

    public boolean Actualizar(NaturalesVO NaturalesVO) throws Exception {
        PreparedStatement ps = null;
        ResultSet keyRS = null;
        boolean exito = false;
        this.getConexion();
        if (this.ConnEstado()) {
            try {
                this.conn.getCon().setAutoCommit(false);
                StringBuilder sql = new StringBuilder();
                sql.append("UPDATE  javap.Naturales SET  \n\t");
                sql.append("        natu_primernombre = ?,  \n\t");
                sql.append("        natu_segundonombre = ?,  \n\t");
                sql.append("        natu_primerapellido = ?,  \n\t");
                sql.append("        natu_segundoapellido = ?,  \n\t");
                sql.append("        natu_fechanacimiento = ?,  \n\t");
                sql.append("        natu_gruposanguineo = ?,  \n\t");
                sql.append("        natu_estrato = ?,  \n\t");
                sql.append("        esci_id = ?,  \n\t");
                sql.append("        natu_fechacambio = ?,  \n\t");
                sql.append("        natu_registradopor = ?  \n\t");
                sql.append("WHERE   pege_id = ? \n\t");
                ps = this.conn.getCon().prepareStatement(sql.toString());
                int a = 1;
                ps.setString(a++, NaturalesVO.getNatu_primernombre());
                ps.setString(a++, NaturalesVO.getNatu_segundonombre());
                ps.setString(a++, NaturalesVO.getNatu_primerapellido());
                ps.setString(a++, NaturalesVO.getNatu_segundoapellido());
                ps.setTimestamp(a++, NaturalesVO.getNatu_fechanacimiento());
                ps.setString(a++, NaturalesVO.getNatu_gruposanguineo());
                ps.setString(a++, NaturalesVO.getNatu_estrato());
                ps.setString(a++, NaturalesVO.getEsci_id());
                ps.setTimestamp(a++, NaturalesVO.getNatu_fechacambio());
                ps.setString(a++, NaturalesVO.getNatu_registradopor());
                ps.setString(a++, NaturalesVO.getEnti_ideps());
                ps.setString(a++, NaturalesVO.getEnti_idarl());
                ps.setString(a++, NaturalesVO.getSexo_id());
                ps.executeUpdate();
                exito = true;
            } catch (Exception ex) {
                e.ImprimirError("NaturalesDAO;ocupacional.bdatos.NaturalesDAO.Actualizar();msg:" + ex.getMessage());
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

    public boolean Eliminar(NaturalesVO NaturalesVO) throws Exception {
        PreparedStatement ps = null;
        ResultSet keyRS = null;
        boolean exito = false;
        this.getConexion();
        if (this.ConnEstado()) {
            try {
                this.conn.getCon().setAutoCommit(false);
                StringBuilder sql = new StringBuilder();
                sql.append("DELETE FROM javap.Naturales   \n\t");
                sql.append("WHERE   pege_id = ? \n\t");
                ps = this.conn.getCon().prepareStatement(sql.toString());
                int a = 1;
                ps.setString(a++, NaturalesVO.getPege_id());
                ps.executeUpdate();
                exito = true;
            } catch (Exception ex) {
                e.ImprimirError("NaturalesDAO;ocupacional.bdatos.NaturalesDAO.Eliminar();msg:" + ex.getMessage());
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

        ArrayList<NaturalesVO> lista = null;
        boolean exito = false;
        this.getConexion();
                this.conn.getCon().setAutoCommit(false);

        if (this.ConnEstado()) {
            try {
                StringBuilder sql = new StringBuilder();
                sql.append("SELECT  natu_primernombre,  \n\t");
                sql.append("        natu_segundonombre,  \n\t");
                sql.append("        natu_primerapellido,  \n\t");
                sql.append("        natu_segundoapellido,  \n\t");
                sql.append("        natu_fechanacimiento,  \n\t");
                sql.append("        natu_gruposanguineo,  \n\t");
                sql.append("        natu_estrato,  \n\t");
                sql.append("        esci_id,  \n\t");
                sql.append("        natu_fechacambio,  \n\t");
                sql.append("        natu_registradopor  \n\t");
                sql.append("        enti_ideps,  \n\t");
                sql.append("        enti_idarl,  \n\t");
                sql.append("FROM    javap.Naturales \n\t");
                sql.append("ORDER BY pege_id \n\t");
                ps = this.conn.getCon().prepareStatement(sql.toString());
                rs = ps.executeQuery();
                if (rs.isBeforeFirst()) {
                    lista = new ArrayList<NaturalesVO>();
                    while (rs.next()) {
                        NaturalesVO NaturalesVO = new NaturalesVO();
                        NaturalesVO.setNatu_primernombre(rs.getString("natu_primernombre"));
                        NaturalesVO.setNatu_segundonombre(rs.getString("natu_segundonombre"));
                        NaturalesVO.setNatu_primerapellido(rs.getString("natu_primerapellido"));
                        NaturalesVO.setNatu_segundoapellido(rs.getString("natu_segundo4eapellido"));
                        NaturalesVO.setPege_id(rs.getString("pege_id"));
                        lista.add(NaturalesVO);
                    }
                }
            } catch (Exception ex) {
                e.ImprimirError("NaturalesDAO;ocupacional.bdatos.NaturalesDAO.Listar();msg:" + ex.getMessage());
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

    public NaturalesVO Cargar(String id) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;

      
        NaturalesVO NaturalesVO = new NaturalesVO();
        boolean exito = false;
        this.getConexion(); 
        this.conn.getCon().setAutoCommit(false);
        if (this.ConnEstado()) {
            try {
                StringBuilder sql = new StringBuilder();
                sql.append("SELECT  natu_primernombre,  \n\t");
                sql.append("        natu_segundonombre,  \n\t");
                sql.append("        natu_primerapellido,  \n\t");
                sql.append("        natu_segundoapellido,  \n\t");
                sql.append("        natu_fechanacimiento,  \n\t");
                sql.append("        natu_gruposanguineo,  \n\t");
                sql.append("        natu_estrato,  \n\t");
                sql.append("        esci_id,  \n\t");
                sql.append("        natu_fechacambio,  \n\t");
                sql.append("        natu_registradopor  \n\t");
                sql.append("        enti_ideps,  \n\t");
                sql.append("        enti_idarl,  \n\t");
                sql.append("FROM    javap.Naturales \n\t");
                sql.append("WHERE pege_id=? \n\t");
                sql.append("ORDER BY pege_id \n\t");
                ps = this.conn.getCon().prepareStatement(sql.toString());
                ps.setString(1, id);
                rs = ps.executeQuery();
                if (rs.isBeforeFirst()) {

                    while (rs.next()) {

                        NaturalesVO.setNatu_primernombre(rs.getString("natu_primernombre"));
                        NaturalesVO.setNatu_segundonombre(rs.getString("natu_segundonombre"));
                        NaturalesVO.setNatu_primerapellido(rs.getString("natu_primerapellido"));
                        NaturalesVO.setNatu_segundoapellido(rs.getString("natu_segundo4eapellido"));
                        NaturalesVO.setPege_id(rs.getString("pege_id"));

                    }
                }
            } catch (Exception ex) {
                e.ImprimirError("NaturalesDAO;ocupacional.bdatos.NaturalesDAO.Listar();msg:" + ex.getMessage());
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
        return NaturalesVO;
    }

}
