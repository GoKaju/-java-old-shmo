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
import ocupacional.valueobjects.facturacion.Ticket;
import valeria.conexion.ConexionAplicacion;
import valeria.metodos.Cadenas;
import valeria.response.Mediador;

/**
 *
 * @author D4V3
 */
public class TicketsDAO {

    Cadenas c = new Cadenas();
    Mediador e = null;
    ConexionAplicacion conn = null;
    private boolean closed = true;

    public TicketsDAO(Mediador e) {
        this.e = e;
    }

    public TicketsDAO(Mediador e, ConexionAplicacion conn) {
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

    public boolean Insertar(Ticket Ticket) throws Exception {
        PreparedStatement ps = null;
        ResultSet keyRS = null;
        boolean exito = false;
        this.getConexion();
        if (this.ConnEstado()) {
            try {
                this.conn.getCon().setAutoCommit(false);
                StringBuilder sql = new StringBuilder();

//                ***cambio dao***
                sql.append("INSERT INTO `ticket`( `tick_estado`, `ceco_id`, `tick_registradopor`, `tick_fechacambio`,tick_paciente,sede_id,tick_clsede,teme_id,tick_brigada,tick_otroexamen) VALUES (?,?,?,?,?,?,?,?,?,?)");
                ps = this.conn.getCon().prepareStatement(sql.toString(), PreparedStatement.RETURN_GENERATED_KEYS);
                int a = 1;
                ps.setString(a++, Ticket.getTick_estado());
                ps.setString(a++, Ticket.getCeco_id());
                ps.setString(a++, Ticket.getTick_registradopor());
                ps.setTimestamp(a++, Ticket.getTick_fechacambio());
                ps.setString(a++, Ticket.getTick_paciente());
                ps.setString(a++, Ticket.getSede_id());
                ps.setString(a++, Ticket.getTick_clsede());
                ps.setString(a++, Ticket.getTeme_id());
                ps.setString(a++, Ticket.getTick_brigada());
                ps.setString(a++, Ticket.getTick_otroexamen());
                ps.executeUpdate();
                keyRS = ps.getGeneratedKeys();
                if (keyRS.next()) {
                    Ticket.setTick_id(keyRS.getString(1));
                }
                if (Ticket.getTick_id() != null) {

//                ***cambio dao***
                    exito = true;
                }
            } catch (Exception ex) {
                e.ImprimirError("TicketsDAO;ocupacional.bdatos.TicketsDAO.Insertar();msg:"
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

    public boolean Actualizar(Ticket Ticket) throws Exception {
        PreparedStatement ps = null;
        ResultSet keyRS = null;
        boolean exito = false;
        this.getConexion();
        if (this.ConnEstado()) {
            try {
                this.conn.getCon().setAutoCommit(false);
                StringBuilder sql = new StringBuilder();
//                ***cambio dao***
                sql.append("UPDATE `ticket` \n"
                        + "SET \n"
                        + " tick_estado=?,\n"
                        + " ceco_id=?,\n"
                        + " tick_registradopor=?,\n"
                        + " tick_fechacambio=?,\n"
                        + " tick_paciente=?,\n"
                        + " sede_id=?,\n"
                        + " tick_clsede=?,\n"
                        + " teme_id=?,\n"
                        + " tick_fecharegistro=?,\n"
                        + " tick_fecharecepcion=?,\n"
                        + " tick_fechaprocesado=?,\n"
                        + " tick_brigada=?,\n"
                        + " tick_otroexamen=?\n"
                        + " WHERE  tick_id=?");
                ps = this.conn.getCon().prepareStatement(sql.toString());
                int a = 1;
                ps.setString(a++, Ticket.getTick_estado());
                ps.setString(a++, Ticket.getCeco_id());
                ps.setString(a++, Ticket.getTick_registradopor());
                ps.setTimestamp(a++, Ticket.getTick_fechacambio());
                ps.setString(a++, Ticket.getTick_paciente());
                ps.setString(a++, Ticket.getSede_id());
                ps.setString(a++, Ticket.getTick_clsede());
                ps.setString(a++, Ticket.getTeme_id());
                ps.setTimestamp(a++, Ticket.getTick_fecharegistro());
                ps.setTimestamp(a++, Ticket.getTick_fecharecepcion());
                ps.setTimestamp(a++, Ticket.getTick_fechaprocesado());
                ps.setString(a++, Ticket.getTick_brigada());
                ps.setString(a++, Ticket.getTick_otroexamen());
                ps.setString(a++, Ticket.getTick_id());
//                ***cambio dao***
                ps.executeUpdate();
                exito = true;
            } catch (Exception ex) {
                ex.printStackTrace();
                e.ImprimirError("TicketsDAO;ocupacional.bdatos.TicketsDAO.Actualizar();msg:"
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

    public boolean Eliminar(String id) throws Exception {
        PreparedStatement ps = null;
        ResultSet keyRS = null;
        boolean exito = false;
        this.getConexion();
        if (this.ConnEstado()) {
            try {
                this.conn.getCon().setAutoCommit(false);
                StringBuilder sql = new StringBuilder();
                //                ***cambio dao***
                sql.append("DELETE FROM javap.ticket   \n\t");
                sql.append("WHERE    tick_id= ? \n\t");
                ps = this.conn.getCon().prepareStatement(sql.toString());
                int a = 1;
                ps.setString(a++, id);
                //                ***cambio dao***
                ps.executeUpdate();
                exito = true;
            } catch (Exception ex) {
                e.ImprimirError("TicketsDAO;ocupacional.bdatos.TicketsDAO.Eliminar();msg:"
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

    public ArrayList<Ticket> Listar(String id) throws Exception {
//        listar por cliente
        System.out.println("cliente id = " + id);
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Ticket> lista = null;
        boolean exito = false;
        this.getConexion();
        if (this.ConnEstado()) {
            try {
                StringBuilder sql = new StringBuilder();
                this.conn.getCon().setAutoCommit(false);
                //                ***cambio dao***
                sql.append("SELECT t.tick_fecharegistro,t.teme_id,t.tick_fecharecepcion,t.tick_fechaprocesado,t.tick_id,t.tick_estado,t.ceco_id,t.tick_registradopor,t.tick_fechacambio,t.tick_paciente,t.sede_id,tick_brigada,tick_otroexamen\n"
                        + " FROM `ticket` as t ,\n"
                        + "centrocostos cc\n"
                        + " where cc.ceco_id=t.ceco_id and\n"
                        + "  cc.clie_id=?");
                ps = this.conn.getCon().prepareStatement(sql.toString());
                ps.setString(1, id);
                rs = ps.executeQuery();
                if (rs.isBeforeFirst()) {
                    lista = new ArrayList<Ticket>();
                    while (rs.next()) {
                        Ticket Ticket = new Ticket();

                        Ticket.setTick_id(rs.getString("tick_id"));
                        Ticket.setTick_estado(rs.getString("tick_estado"));
                        Ticket.setCeco_id(rs.getString("ceco_id"));
                        Ticket.setTick_registradopor(rs.getString("tick_registradopor"));
                        Ticket.setTick_fechacambio(rs.getTimestamp("tick_fechacambio"));
                        Ticket.setTick_paciente(rs.getString("tick_paciente"));
                        Ticket.setTeme_id(rs.getString("teme_id"));
                        Ticket.setTick_brigada(rs.getString("tick_brigada"));
                        Ticket.setTick_otroexamen(rs.getString("tick_otroexamen"));

                        Ticket.setTick_fecharegistro(rs.getTimestamp("tick_fecharegistro"));
                        Ticket.setTick_fecharecepcion(rs.getTimestamp("tick_fecharecepcion"));
                        Ticket.setTick_fechaprocesado(rs.getTimestamp("tick_fechaprocesado"));

//                ***cambio dao***
                        lista.add(Ticket);
                    }
                }
            } catch (Exception ex) {
                e.ImprimirError("TicketsDAO;ocupacional.bdatos.TicketsDAO.Listar();msg:"
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

    public ArrayList<Ticket> ListarxPaciente(String doc, String nombre, String sede) throws Exception {

        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Ticket> lista = null;
        boolean exito = false;
        this.getConexion();
        if (this.ConnEstado()) {
            try {
                StringBuilder sql = new StringBuilder();
                this.conn.getCon().setAutoCommit(false);
                //                ***cambio dao***
                sql.append("select * from ticket t\n"
                        + "inner join javaphc.pacientes p on p.paci_id = t.tick_paciente\n"
                        + "WHERE \n"
                        + "p.paci_documento like '%"
                        +doc+ "%' and p.paci_nombres like '%"
                        +nombre+ "%' and (tick_estado ='POR PROCESAR' or tick_estado='PROCESANDO' ) and t.sede_id="+sede);
                ps = this.conn.getCon().prepareStatement(sql.toString());
              
                rs = ps.executeQuery();
                    lista = new ArrayList<Ticket>();
                if (rs.isBeforeFirst()) {
                    while (rs.next()) {
                        Ticket Ticket = new Ticket();

                        Ticket.setTick_id(rs.getString("tick_id"));
                        Ticket.setTick_estado(rs.getString("tick_estado"));
                        Ticket.setCeco_id(rs.getString("ceco_id"));
                        Ticket.setTick_registradopor(rs.getString("tick_registradopor"));
                        Ticket.setTick_fechacambio(rs.getTimestamp("tick_fechacambio"));
                        Ticket.setTick_paciente(rs.getString("tick_paciente"));
                        Ticket.setTeme_id(rs.getString("teme_id"));
                          Ticket.setTick_brigada(rs.getString("tick_brigada"));
                        Ticket.setTick_otroexamen(rs.getString("tick_otroexamen"));
                        Ticket.setTick_fecharegistro(rs.getTimestamp("tick_fecharegistro"));
                        Ticket.setTick_fecharecepcion(rs.getTimestamp("tick_fecharecepcion"));
                        Ticket.setTick_fechaprocesado(rs.getTimestamp("tick_fechaprocesado"));

//                ***cambio dao***
                        lista.add(Ticket);
                    }
                }
                    System.out.println(sql.toString());
                    System.out.println("entre lista size -->" +lista.size());
                
            } catch (Exception ex) {
                e.ImprimirError("TicketsDAO;ocupacional.bdatos.TicketsDAO.ListarxPaciente();msg:"
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

    public ArrayList<Ticket> ListarXestado(String id, String estado) throws Exception {
        System.out.println("cliente id = " + id);
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Ticket> lista = null;
        boolean exito = false;
        this.getConexion();
        if (this.ConnEstado()) {
            try {
                StringBuilder sql = new StringBuilder();
                this.conn.getCon().setAutoCommit(false);
                //                ***cambio dao***
                sql.append("SELECT t.tick_fecharegistro,t.teme_id,t.tick_fecharecepcion,t.tick_fechaprocesado,t.tick_id,t.tick_estado,t.ceco_id,t.tick_registradopor,t.tick_fechacambio,t.tick_paciente,t.sede_id,tick_brigada,tick_otroexamen\n"
                        + " FROM `ticket` as t ,\n"
                        + "centrocostos cc\n"
                        + " where cc.ceco_id=t.ceco_id and\n"
                        + "  cc.clie_id=? and t.tick_estado =?");
                ps = this.conn.getCon().prepareStatement(sql.toString());
                ps.setString(1, id);
                ps.setString(2, estado);
                rs = ps.executeQuery();
                if (rs.isBeforeFirst()) {
                    lista = new ArrayList<Ticket>();
                    while (rs.next()) {
                        Ticket Ticket = new Ticket();

                        Ticket.setTick_id(rs.getString("tick_id"));
                        Ticket.setTick_estado(rs.getString("tick_estado"));
                        Ticket.setCeco_id(rs.getString("ceco_id"));
                        Ticket.setTick_registradopor(rs.getString("tick_registradopor"));
                        Ticket.setTick_fechacambio(rs.getTimestamp("tick_fechacambio"));
                        Ticket.setTick_paciente(rs.getString("tick_paciente"));
                        Ticket.setTeme_id(rs.getString("teme_id"));
                          Ticket.setTick_brigada(rs.getString("tick_brigada"));
                        Ticket.setTick_otroexamen(rs.getString("tick_otroexamen"));

                        Ticket.setTick_fecharegistro(rs.getTimestamp("tick_fecharegistro"));
                        Ticket.setTick_fecharecepcion(rs.getTimestamp("tick_fecharecepcion"));
                        Ticket.setTick_fechaprocesado(rs.getTimestamp("tick_fechaprocesado"));

//                ***cambio dao***
                        lista.add(Ticket);
                    }
                }
            } catch (Exception ex) {
                e.ImprimirError("TicketsDAO;ocupacional.bdatos.TicketsDAO.Listar();msg:"
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

    public ArrayList<Ticket> ListarXcc(String id, String estado) throws Exception {
        System.out.println("cliente id = " + id);
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Ticket> lista = null;
        boolean exito = false;
        this.getConexion();
        if (this.ConnEstado()) {
            try {
                StringBuilder sql = new StringBuilder();
                this.conn.getCon().setAutoCommit(false);
                //                ***cambio dao***
                sql.append("SELECT t.tick_fecharegistro,t.teme_id,t.tick_fecharecepcion,t.tick_fechaprocesado,t.tick_id,t.tick_estado,t.ceco_id,t.tick_registradopor,t.tick_fechacambio,t.tick_paciente,t.sede_id,tick_brigada,tick_otroexamen \n"
                        + " FROM `ticket` as t ,\n"
                        + "centrocostos cc\n"
                        + " where cc.ceco_id=t.ceco_id and\n"
                        + "  cc.ceco_id=? and t.tick_estado=?");
                ps = this.conn.getCon().prepareStatement(sql.toString());
                ps.setString(1, id);
                ps.setString(2, estado);
                rs = ps.executeQuery();
                if (rs.isBeforeFirst()) {
                    lista = new ArrayList<Ticket>();
                    while (rs.next()) {
                        Ticket Ticket = new Ticket();

                        Ticket.setTick_id(rs.getString("tick_id"));
                        Ticket.setTick_estado(rs.getString("tick_estado"));
                        Ticket.setCeco_id(rs.getString("ceco_id"));
                        Ticket.setTick_registradopor(rs.getString("tick_registradopor"));
                        Ticket.setTick_fechacambio(rs.getTimestamp("tick_fechacambio"));
                        Ticket.setTick_paciente(rs.getString("tick_paciente"));
                        Ticket.setTeme_id(rs.getString("teme_id"));
                          Ticket.setTick_brigada(rs.getString("tick_brigada"));
                        Ticket.setTick_otroexamen(rs.getString("tick_otroexamen"));

                        Ticket.setTick_fecharegistro(rs.getTimestamp("tick_fecharegistro"));
                        Ticket.setTick_fecharecepcion(rs.getTimestamp("tick_fecharecepcion"));
                        Ticket.setTick_fechaprocesado(rs.getTimestamp("tick_fechaprocesado"));

//                ***cambio dao***
                        lista.add(Ticket);
                    }
                }
            } catch (Exception ex) {
                e.ImprimirError("TicketsDAO;ocupacional.bdatos.TicketsDAO.Listar();msg:"
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

    public ArrayList<Ticket> ListarxClienteEstado(String id, String Estado) throws Exception {
        System.out.println("cliente id = " + id);
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Ticket> lista = null;
        lista = new ArrayList<Ticket>();
        boolean exito = false;
        this.getConexion();
        if (this.ConnEstado()) {
            try {
                StringBuilder sql = new StringBuilder();
                this.conn.getCon().setAutoCommit(false);
                //                ***cambio dao***
                sql.append("SELECT t.tick_fecharegistro,t.teme_id,t.tick_fecharecepcion,t.tick_fechaprocesado,t.tick_id,t.tick_estado,t.ceco_id,t.tick_registradopor,t.tick_fechacambio,t.tick_paciente,t.sede_id,tick_brigada,tick_otroexamen \n"
                        + " FROM `ticket` as t ,\n"
                        + "centrocostos cc\n"
                        + " where cc.ceco_id=t.ceco_id and\n"
                        + "  cc.clie_id=? and t.tick_estado=?");
                ps = this.conn.getCon().prepareStatement(sql.toString());
                ps.setString(1, id);
                ps.setString(2, Estado);
                rs = ps.executeQuery();
                if (rs.isBeforeFirst()) {
                    while (rs.next()) {
                        Ticket Ticket = new Ticket();

                        Ticket.setTick_id(rs.getString("tick_id"));
                        Ticket.setTick_estado(rs.getString("tick_estado"));
                        Ticket.setCeco_id(rs.getString("ceco_id"));
                        Ticket.setTick_registradopor(rs.getString("tick_registradopor"));
                        Ticket.setTick_fechacambio(rs.getTimestamp("tick_fechacambio"));
                        Ticket.setTick_paciente(rs.getString("tick_paciente"));
                        Ticket.setTeme_id(rs.getString("teme_id"));
                          Ticket.setTick_brigada(rs.getString("tick_brigada"));
                        Ticket.setTick_otroexamen(rs.getString("tick_otroexamen"));

                        Ticket.setTick_fecharegistro(rs.getTimestamp("tick_fecharegistro"));
                        Ticket.setTick_fecharecepcion(rs.getTimestamp("tick_fecharecepcion"));
                        Ticket.setTick_fechaprocesado(rs.getTimestamp("tick_fechaprocesado"));

//                ***cambio dao***
                        lista.add(Ticket);
                    }
                }
            } catch (Exception ex) {
                e.ImprimirError("TicketsDAO;ocupacional.bdatos.TicketsDAO.Listar();msg:"
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

    public Ticket Cargar(String id) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean exito = false;
        Ticket Ticket = new Ticket();
        this.getConexion();
        if (this.ConnEstado()) {
            try {
                StringBuilder sql = new StringBuilder();
                this.conn.getCon().setAutoCommit(false);
                //                ***cambio dao***
                sql.append("SELECT t.teme_id,t.tick_fecharegistro,t.tick_fecharecepcion,t.tick_fechaprocesado,t.tick_id,t.tick_estado,t.ceco_id,t.tick_registradopor,t.tick_fechacambio,t.tick_paciente,t.sede_id,tick_brigada,tick_otroexamen FROM `ticket` as t  where  tick_id=?");
                ps = this.conn.getCon().prepareStatement(sql.toString());
                ps.setString(1, id);
                rs = ps.executeQuery();
                if (rs.next()) {

                    System.out.println("-->" + rs.getString("tick_id") + " <" + id);
                    Ticket.setTick_id(rs.getString("tick_id"));
                    Ticket.setTick_estado(rs.getString("tick_estado"));
                    Ticket.setCeco_id(rs.getString("ceco_id"));
                    Ticket.setTick_registradopor(rs.getString("tick_registradopor"));
                    Ticket.setTick_paciente(rs.getString("tick_paciente"));
                    Ticket.setTeme_id(rs.getString("teme_id"));
                    Ticket.setSede_id(rs.getString("sede_id"));
                      Ticket.setTick_brigada(rs.getString("tick_brigada"));
                        Ticket.setTick_otroexamen(rs.getString("tick_otroexamen"));

                    Ticket.setTick_fechacambio(rs.getTimestamp("tick_fechacambio"));
                    Ticket.setTick_fecharegistro(rs.getTimestamp("tick_fecharegistro"));
                    Ticket.setTick_fecharecepcion(rs.getTimestamp("tick_fecharecepcion"));
                    Ticket.setTick_fechaprocesado(rs.getTimestamp("tick_fechaprocesado"));

//                ***cambio dao***
                }

            } catch (Exception ex) {
                e.ImprimirError("TicketsDAO;ocupacional.bdatos.TicketsDAO.Listar();msg:"
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
        return Ticket;
    }
}
