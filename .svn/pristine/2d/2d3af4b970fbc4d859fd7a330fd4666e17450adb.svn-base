package ocupacional.bdatos.historiaclinica;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import ocupacional.valueobjects.historiaclinica.AntecedentesOcupacionalesEspecificosFamiliaRiesgosVO;
import valeria.conexion.ConexionAplicacion;
import valeria.metodos.Cadenas;
import valeria.response.Mediador;

public class AntecedentesOcupacionalesEspecificosFamiliaRiesgosDAO {

    Cadenas c = new Cadenas();
    Mediador e = null;
    ConexionAplicacion conn = null;
    private boolean closed = true;

    public AntecedentesOcupacionalesEspecificosFamiliaRiesgosDAO(Mediador e) {
        this.e = e;
    }

    public AntecedentesOcupacionalesEspecificosFamiliaRiesgosDAO(Mediador e, ConexionAplicacion conn) {
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

    public boolean Insertar(AntecedentesOcupacionalesEspecificosFamiliaRiesgosVO object) throws Exception {
        PreparedStatement ps = null;
        ResultSet keyRS = null;
        boolean exito = false;
        this.getConexion();
        if (this.ConnEstado()) {
            try {
                this.conn.getCon().setAutoCommit(false);
                StringBuilder sql = new StringBuilder();

//                ***cambio dao***
                sql.append("INSERT INTO `antecedentesocupacionalesespicificos_familiariesgos`(`fari_id`, `anoe_id`, `aoef_registradopor`, `aoef_fechacambio`) VALUES (?,?,?,?)");

                ps = this.conn.getCon().prepareStatement(sql.toString(), PreparedStatement.RETURN_GENERATED_KEYS);
                int a = 1;
                ps.setString(a++, object.getFari_id());
                ps.setString(a++, object.getAnoe_id());
                ps.setTimestamp(a++, object.getAoef_fechacambio());
                ps.setString(a++, object.getAoef_registradopor());

                ps.executeUpdate();
                keyRS = ps.getGeneratedKeys();
                if (keyRS.next()) {
                    object.setAoef_id(keyRS.getString(1));
                }
                if (object.getAoef_id() != null) {

//                ***cambio dao***
                    exito = true;
                }
            } catch (Exception ex) {
                e.ImprimirError("AntecedentesOcupacionalesEspecificosFamiliaRiesgosDAO;ocupacional.bdatos.AntecedentesOcupacionalesEspecificosFamiliaRiesgosDAO.Insertar();msg:" + ex.getMessage());
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

    public boolean Actualizar(AntecedentesOcupacionalesEspecificosFamiliaRiesgosVO AntecedentesOcupacionalesEspecificosFamiliaRiesgosVO) throws Exception {
        PreparedStatement ps = null;
        ResultSet keyRS = null;
        boolean exito = false;
        this.getConexion();
        if (this.ConnEstado()) {
            try {
                this.conn.getCon().setAutoCommit(false);
                StringBuilder sql = new StringBuilder();
//                ***cambio dao***
                sql.append("UPDATE `antecedentesocupacionalesespicificos_familiariesgos` SET `fari_id`=?,"
                        + "`anoe_id`=?,`aoef_registradopor`=?,`aoef_fechacambio`=? WHERE `aoef_id`=?");
                ps = this.conn.getCon().prepareStatement(sql.toString());
                int a = 1;
                ps.setString(a++, AntecedentesOcupacionalesEspecificosFamiliaRiesgosVO.getFari_id());
                ps.setString(a++, AntecedentesOcupacionalesEspecificosFamiliaRiesgosVO.getAnoe_id());
                ps.setString(a++, AntecedentesOcupacionalesEspecificosFamiliaRiesgosVO.getAoef_registradopor());
                ps.setTimestamp(a++, AntecedentesOcupacionalesEspecificosFamiliaRiesgosVO.getAoef_fechacambio());
                ps.setString(a++, AntecedentesOcupacionalesEspecificosFamiliaRiesgosVO.getAoef_id());

//                ***cambio dao***
                ps.executeUpdate();
                exito = true;
            } catch (Exception ex) {
                e.ImprimirError("AntecedentesOcupacionalesEspecificosFamiliaRiesgosDAO;ocupacional.bdatos.AntecedentesOcupacionalesEspecificosFamiliaRiesgosDAO.Actualizar();msg:" + ex.getMessage());
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

    public boolean Eliminar(AntecedentesOcupacionalesEspecificosFamiliaRiesgosVO AntecedentesOcupacionalesEspecificosFamiliaRiesgosVO) throws Exception {
        PreparedStatement ps = null;
        ResultSet keyRS = null;
        boolean exito = false;
        this.getConexion();
        if (this.ConnEstado()) {
            try {
                this.conn.getCon().setAutoCommit(false);
                StringBuilder sql = new StringBuilder();
                //                ***cambio dao***
                sql.append("DELETE FROM javaphistoriaclinica.antecedentesocupacionalesespicificos_familiariesgos WHERE aoef_id=?   \n\t");

                ps = this.conn.getCon().prepareStatement(sql.toString());
                int a = 1;
                ps.setString(1, AntecedentesOcupacionalesEspecificosFamiliaRiesgosVO.getAoef_id());
                //                ***cambio dao***
                ps.executeUpdate();
                exito = true;
            } catch (Exception ex) {
                ex.printStackTrace();
                e.ImprimirError("AntecedentesOcupacionalesEspecificosFamiliaRiesgosDAO;ocupacional.bdatos.AntecedentesOcupacionalesEspecificosFamiliaRiesgosDAO.Eliminar();msg:" + ex.getMessage());
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

    public ArrayList<AntecedentesOcupacionalesEspecificosFamiliaRiesgosVO> Listarxantecedente() throws Exception {

        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<AntecedentesOcupacionalesEspecificosFamiliaRiesgosVO> lista = new ArrayList<AntecedentesOcupacionalesEspecificosFamiliaRiesgosVO>();
        boolean exito = false;
        this.getConexion();
        if (this.ConnEstado()) {

            try {
                StringBuilder sql = new StringBuilder();
                this.conn.getCon().setAutoCommit(false);
                //                ***cambio dao***
                sql.append("SELECT * FROM javaphistoriaclinica.antecedentesocupacionalesespicificos_familiariesgos where anoe_id=?");
                ps = this.conn.getCon().prepareStatement(sql.toString());
                rs = ps.executeQuery();
                if (rs.isBeforeFirst()) {

                    while (rs.next()) {
                        AntecedentesOcupacionalesEspecificosFamiliaRiesgosVO AntecedentesOcupacionalesEspecificosFamiliaRiesgosVO = new AntecedentesOcupacionalesEspecificosFamiliaRiesgosVO();

                        AntecedentesOcupacionalesEspecificosFamiliaRiesgosVO.setAoef_id(rs.getString("aoef_id"));
                        AntecedentesOcupacionalesEspecificosFamiliaRiesgosVO.setFari_id(rs.getString("fari_id"));
                        AntecedentesOcupacionalesEspecificosFamiliaRiesgosVO.setAnoe_id(rs.getString("anoe_id"));
                        AntecedentesOcupacionalesEspecificosFamiliaRiesgosVO.setAoef_registradopor(rs.getString("aoef_registradopor"));
                        AntecedentesOcupacionalesEspecificosFamiliaRiesgosVO.setAoef_fechacambio(rs.getTimestamp("aoef_fechacambio"));

//                ***cambio dao***
                        lista.add(AntecedentesOcupacionalesEspecificosFamiliaRiesgosVO);
                    }

                }

                System.out.println("3");

            } catch (Exception ex) {
                ex.printStackTrace();
                e.ImprimirError("AntecedentesOcupacionalesEspecificosFamiliaRiesgosDAO;ocupacional.bdatos.AntecedentesOcupacionalesEspecificosFamiliaRiesgosDAO.Listar();msg:" + ex.getMessage());
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

    public AntecedentesOcupacionalesEspecificosFamiliaRiesgosVO Cargar(String Pegeid) throws Exception {

        PreparedStatement ps = null;
        ResultSet rs = null;
        AntecedentesOcupacionalesEspecificosFamiliaRiesgosVO AntecedentesOcupacionalesEspecificosFamiliaRiesgosVO = new AntecedentesOcupacionalesEspecificosFamiliaRiesgosVO();
        boolean exito = false;
        this.getConexion();
        if (this.ConnEstado()) {

            try {
                StringBuilder sql = new StringBuilder();
                this.conn.getCon().setAutoCommit(false);
                //                ***cambio dao***
                sql.append("SELECT * FROM javaphistoriaclinica.antecedentesocupacionalesespicificos_familiariesgos WHERE anoe_id= ?");
                ps = this.conn.getCon().prepareStatement(sql.toString());
                ps.setString(1, Pegeid);
                rs = ps.executeQuery();
                if (rs.isBeforeFirst()) {

                    while (rs.next()) {

                        AntecedentesOcupacionalesEspecificosFamiliaRiesgosVO.setAoef_id(rs.getString("aoef_id"));
                        AntecedentesOcupacionalesEspecificosFamiliaRiesgosVO.setFari_id(rs.getString("fari_id"));
                        AntecedentesOcupacionalesEspecificosFamiliaRiesgosVO.setAnoe_id(rs.getString("anoe_id"));
                        AntecedentesOcupacionalesEspecificosFamiliaRiesgosVO.setAoef_registradopor(rs.getString("aoef_registradopor"));
                        AntecedentesOcupacionalesEspecificosFamiliaRiesgosVO.setAoef_fechacambio(rs.getTimestamp("aoef_fechacambio"));

//                ***cambio dao***
                    }

                }

                System.out.println("3");

            } catch (Exception ex) {
                ex.printStackTrace();
                e.ImprimirError("AntecedentesOcupacionalesEspecificosFamiliaRiesgosDAO;ocupacional.bdatos.AntecedentesOcupacionalesEspecificosFamiliaRiesgosDAO.Listar();msg:" + ex.getMessage());
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
        return AntecedentesOcupacionalesEspecificosFamiliaRiesgosVO;
    }

}
