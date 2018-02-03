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
import ocupacional.valueobjects.PersonaGeneralVO;
import valeria.conexion.ConexionAplicacion;
import valeria.metodos.Cadenas;
import valeria.response.Mediador;

/**
 *
 * @author D4V3
 */
public class PersonaGeneralDAO {

    Cadenas c = new Cadenas();
    Mediador e = null;
    ConexionAplicacion conn = null;
    private boolean closed = true;

    public PersonaGeneralDAO(Mediador e) {
        this.e = e;
    }

    public PersonaGeneralDAO(Mediador e, ConexionAplicacion conn) {
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

    public boolean Insertar(PersonaGeneralVO PersonaGeneralVO) throws Exception {
        PreparedStatement ps = null;
        ResultSet keyRS = null;
        boolean exito = false;
        this.getConexion();
        if (this.ConnEstado()) {
            try {
                this.conn.getCon().setAutoCommit(false);
                StringBuilder sql = new StringBuilder();

//                ***cambio dao***
                sql.append("INSERT INTO `personageneral`( `pege_documento`, `tido_id`, `ciud_id`, `pege_telefono`, `pege_email`, `pege_direccion`, `pege_fechacambio`, `pege_registradopor`, `pege_celular`) VALUES (?,?,?,?,?,?,?,?,?)");
                ps = this.conn.getCon().prepareStatement(sql.toString(), PreparedStatement.RETURN_GENERATED_KEYS);
                int a = 1;
                ps.setString(a++, PersonaGeneralVO.getPege_documento());
                ps.setString(a++, PersonaGeneralVO.getTido_id());
                ps.setString(a++, PersonaGeneralVO.getCiud_id());
                ps.setString(a++, PersonaGeneralVO.getPege_numerotelefono());
                ps.setString(a++, PersonaGeneralVO.getPege_mail());
                ps.setString(a++, PersonaGeneralVO.getPege_direcciondomicilio());
                ps.setTimestamp(a++, PersonaGeneralVO.getPege_fechacambio());
                ps.setString(a++, PersonaGeneralVO.getPege_registradopor());
                ps.setString(a++, PersonaGeneralVO.getPege_numerocelular());
                ps.executeUpdate();
                keyRS = ps.getGeneratedKeys();
                if (keyRS.next()) {
                    PersonaGeneralVO.setPege_id(keyRS.getString(1));
                }
                if (PersonaGeneralVO.getPege_id() != null) {

//                ***cambio dao***
                    exito = true;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                e.ImprimirError("PersonaGeneralDAO;ocupacional.bdatos.PersonaGeneralDAO.Insertar();msg:" + ex.getMessage());
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

    public boolean Actualizar(PersonaGeneralVO PersonaGeneralVO) throws Exception {
        PreparedStatement ps = null;
        ResultSet keyRS = null;
        boolean exito = false;
        this.getConexion();
        if (this.ConnEstado()) {
            try {
                this.conn.getCon().setAutoCommit(false);
                StringBuilder sql = new StringBuilder();
//                ***cambio dao***
                sql.append("UPDATE `personageneral` SET\n"
                        + " `pege_documento`=?,\n"
                        + "`tido_id`=?,\n"
                        + "`ciud_id`=?,\n"
                        + "`pege_telefono`=?,\n"
                        + "`pege_email`=?,\n"
                        + "`pege_direccion`=?,\n"
                        + "`pege_fechacambio`=?,\n"
                        + "`pege_registradopor`=?,\n"
                        + "`pege_celular`=?\n"
                        + " WHERE pege_id=?");
                ps = this.conn.getCon().prepareStatement(sql.toString());
                int a = 1;
                ps.setString(a++, PersonaGeneralVO.getPege_documento());
                ps.setString(a++, PersonaGeneralVO.getTido_id());
                ps.setString(a++, PersonaGeneralVO.getCiud_id());
                ps.setString(a++, PersonaGeneralVO.getPege_numerotelefono());
                ps.setString(a++, PersonaGeneralVO.getPege_mail());
                ps.setString(a++, PersonaGeneralVO.getPege_direcciondomicilio());
                ps.setTimestamp(a++, PersonaGeneralVO.getPege_fechacambio());
                ps.setString(a++, PersonaGeneralVO.getPege_registradopor());
                ps.setString(a++, PersonaGeneralVO.getPege_numerocelular());
                ps.setString(a++, PersonaGeneralVO.getPege_id());

//                ***cambio dao***
                ps.executeUpdate();
                exito = true;
            } catch (Exception ex) {
                e.ImprimirError("PersonaGeneralDAO;ocupacional.bdatos.PersonaGeneralDAO.Actualizar();msg:" + ex.getMessage());
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

    public boolean Eliminar(PersonaGeneralVO PersonaGeneralVO) throws Exception {
        PreparedStatement ps = null;
        ResultSet keyRS = null;
        boolean exito = false;
        this.getConexion();
        if (this.ConnEstado()) {
            try {
                this.conn.getCon().setAutoCommit(false);
                StringBuilder sql = new StringBuilder();
                //                ***cambio dao***
                sql.append("DELETE FROM javap.personageneral   \n\t");
                sql.append("WHERE   pege_id = ? \n\t");
                ps = this.conn.getCon().prepareStatement(sql.toString());
                int a = 1;
                ps.setString(a++, PersonaGeneralVO.getPege_id());
                //                ***cambio dao***
                ps.executeUpdate();
                exito = true;
            } catch (Exception ex) {
                e.ImprimirError("PersonaGeneralDAO;ocupacional.bdatos.PersonaGeneralDAO.Eliminar();msg:" + ex.getMessage());
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

    public ArrayList<PersonaGeneralVO> Listar() throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<PersonaGeneralVO> lista = null;
        boolean exito = false;
        this.getConexion();
        if (this.ConnEstado()) {
            try {
                StringBuilder sql = new StringBuilder();
                this.conn.getCon().setAutoCommit(false);
                //                ***cambio dao***
                sql.append("SELECT * FROM `personageneral`");
                ps = this.conn.getCon().prepareStatement(sql.toString());
                rs = ps.executeQuery();
                if (rs.isBeforeFirst()) {
                    lista = new ArrayList<PersonaGeneralVO>();
                    while (rs.next()) {
                        PersonaGeneralVO PersonaGeneralVO = new PersonaGeneralVO();
                        
                        PersonaGeneralVO.setPege_id(rs.getString("pege_id"));
                        PersonaGeneralVO.setCiud_id(rs.getString("ciud_id"));
                        PersonaGeneralVO.setPege_direcciondomicilio(rs.getString("pege_direccion"));
                        PersonaGeneralVO.setPege_documento(rs.getString("pege_documento"));
                        PersonaGeneralVO.setPege_mail(rs.getString("pege_email"));
                        PersonaGeneralVO.setPege_numerocelular(rs.getString("pege_celular"));
                        PersonaGeneralVO.setPege_numerotelefono(rs.getString("pege_telefono"));
                        PersonaGeneralVO.setTido_id(rs.getString("tido_id"));

//                ***cambio dao***
                        lista.add(PersonaGeneralVO);
                    }
                }
            } catch (Exception ex) {
                e.ImprimirError("PersonaGeneralDAO;ocupacional.bdatos.PersonaGeneralDAO.Listar();msg:" + ex.getMessage());
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

        public PersonaGeneralVO Cargar(String id ) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean exito = false;
        PersonaGeneralVO PersonaGeneralVO = new PersonaGeneralVO();
        this.getConexion();
        if (this.ConnEstado()) {
            try {
                StringBuilder sql = new StringBuilder();
                this.conn.getCon().setAutoCommit(false);
                //                ***cambio dao***
                sql.append("SELECT * FROM `personageneral`   WHERE pege_id= ?");
                ps = this.conn.getCon().prepareStatement(sql.toString());
                ps.setString(1,id );
                
                rs = ps.executeQuery();
                if (rs.isBeforeFirst()) {
                    
                    while (rs.next()) {
                        
                        
                        PersonaGeneralVO.setPege_id(rs.getString("pege_id"));
                        PersonaGeneralVO.setCiud_id(rs.getString("ciud_id"));
                        PersonaGeneralVO.setPege_direcciondomicilio(rs.getString("pege_direccion"));
                        PersonaGeneralVO.setPege_documento(rs.getString("pege_documento"));
                        PersonaGeneralVO.setPege_mail(rs.getString("pege_email"));
                        PersonaGeneralVO.setPege_numerocelular(rs.getString("pege_celular"));
                        PersonaGeneralVO.setPege_numerotelefono(rs.getString("pege_telefono"));
                        PersonaGeneralVO.setTido_id(rs.getString("tido_id"));

//                ***cambio dao***
                    }
                }
            } catch (Exception ex) {
                e.ImprimirError("PersonaGeneralDAO;ocupacional.bdatos.PersonaGeneralDAO.Cargar();msg:" + ex.getMessage());
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
        return PersonaGeneralVO;
    }
        public PersonaGeneralVO Verificar(String td, String nd ) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean exito = false;
        PersonaGeneralVO PersonaGeneralVO = new PersonaGeneralVO();
        this.getConexion();
        if (this.ConnEstado()) {
            try {
                StringBuilder sql = new StringBuilder();
                this.conn.getCon().setAutoCommit(false);
                //                ***cambio dao***
                sql.append("SELECT * FROM `personageneral`   WHERE tido_id= ?  AND  pege_documento=? ");
                ps = this.conn.getCon().prepareStatement(sql.toString());
                ps.setString(1,td );
                ps.setString(2,nd );
                
                rs = ps.executeQuery();
                if (rs.isBeforeFirst()) {
                    
                    while (rs.next()) {
                        PersonaGeneralVO.setPege_id(rs.getString("pege_id"));
                        PersonaGeneralVO.setCiud_id(rs.getString("ciud_id"));
                        PersonaGeneralVO.setPege_direcciondomicilio(rs.getString("pege_direccion"));
                        PersonaGeneralVO.setPege_documento(rs.getString("pege_documento"));
                        PersonaGeneralVO.setPege_mail(rs.getString("pege_email"));
                        PersonaGeneralVO.setPege_numerocelular(rs.getString("pege_celular"));
                        PersonaGeneralVO.setPege_numerotelefono(rs.getString("pege_telefono"));
                        PersonaGeneralVO.setTido_id(rs.getString("tido_id"));
//                ***cambio dao***
                    }
                }
            } catch (Exception ex) {
                e.ImprimirError("PersonaGeneralDAO;ocupacional.bdatos.PersonaGeneralDAO.Validar();msg:" + ex.getMessage());
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
        return PersonaGeneralVO;
    }

}
    



