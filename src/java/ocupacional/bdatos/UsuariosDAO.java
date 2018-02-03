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
import ocupacional.JPA.valueobjects.Roles;
import ocupacional.JPA.valueobjects.Rolfuncionalidad;
import ocupacional.JPA.valueobjects.Usuariorol;
import ocupacional.valueobjects.RolFuncionalidadVO;
import ocupacional.valueobjects.UsuarioRolVO;
import valeria.conexion.ConexionAplicacion;
import valeria.metodos.Cadenas;
import valeria.metodos.ManejadorFechas;
import valeria.response.Mediador;
import valeria.session.UsuarioVO;

/**
 *
 * DESAROLLADOR Alejandro NOMBRE DEL DOCUMENTO : UsuariosDAO FECHA DE CREACION :
 * 02-nov-2015, 11:25:44 HISTORIA DE USUARIO : DESCRIPCION FUNCIONAL DEL
 * DOCUMENTO :
 *
 */
public class UsuariosDAO {

    Cadenas c = new Cadenas();
    Mediador e = null;
    ConexionAplicacion conn = null;
    private boolean closed = true;

    public UsuariosDAO(Mediador e) {
        this.e = e;
    }

    public UsuariosDAO(Mediador e, ConexionAplicacion conn) {
        this.e = e;
        this.conn = conn;
        this.closed = false;
    }

    private void getConexion() throws Exception {
        System.out.println("ocupacional.bdatos.UsuariosDAO.getConexion() \n\t");
        if (this.conn == null) {
            System.out.println("ocupacional.bdatos.UsuariosDAO.getConexion() --- la conexion es null \n\t");
            this.conn = new ConexionAplicacion(this.e);
        } else if (!this.conn.getCon().isClosed()) {
            System.out.println("ocupacional.bdatos.UsuariosDAO.getConexion() --- la conexion esta cerrada \n\t");
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

    public Object Logueo(String usuario) throws Exception {
        System.out.println("ocupacional.bdatos.UsuariosDAO.Logueo() \n\t");
        UsuarioVO UsuarioVO = null;
        StringBuffer sql = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        this.getConexion();
        if (this.ConnEstado()) {
            try {
                sql = new StringBuffer();
                sql.append("select * from javap.usuarios u \n"
                        + "where u.usua_usuario = ?");
                ps = this.conn.getCon().prepareStatement(sql.toString());
                ps.setString(1, usuario);
                rs = ps.executeQuery();
                if (rs.isBeforeFirst()) {
                    if (rs.next()) {
                        UsuarioVO = new UsuarioVO();
                        UsuarioVO.setIdUsuario(rs.getString("usua_id"));
                        UsuarioVO.setUsua_tipo(rs.getString("usua_tipo"));
                        UsuarioVO.setIdpersona(rs.getString("usua_idpersona"));
                        UsuarioVO.setNombre_usuario(rs.getString("usua_usuario"));
                        UsuarioVO.setPassword_usuario(rs.getString("usua_contrasena"));
                        UsuarioVO.setHora_acceso(new ManejadorFechas().getFechaHoraTimeStamp());
                        UsuarioVO.setSede_id(rs.getString("sede_id"));
                        UsuarioVO.setUsua_estado(rs.getString("usua_estado"));

                        sql = new StringBuffer();
                        sql.append("update javap.usuarios set usua_ultimoacceso=? where usua_id=?");
                        ps = this.conn.getCon().prepareStatement(sql.toString());
                        ps.setTimestamp(1, UsuarioVO.getHora_acceso());
                        ps.setString(2, UsuarioVO.getIdUsuario());
                        ps.executeUpdate();

                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                e.ImprimirError("UsuariosDAO;Logueo;msg:" + ex.getMessage());
            } finally {
                if (this.conn.getCon() != null) {
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
                    ex.printStackTrace();

                }
            }
        }
        return UsuarioVO;
    }

    public Object LogueoCam_pass(String usuario) throws Exception {
        System.out.println("ocupacional.bdatos.UsuariosDAO.Logueo() \n\t");
        UsuarioVO UsuarioVO = null;
        StringBuffer sql = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        this.getConexion();
        if (this.ConnEstado()) {
            try {
                sql = new StringBuffer();
                sql.append("select * from javap.usuarios u \n"
                        + "where u.usua_usuario = ?");
                ps = this.conn.getCon().prepareStatement(sql.toString());
                ps.setString(1, usuario);
                rs = ps.executeQuery();
                if (rs.isBeforeFirst()) {
                    if (rs.next()) {
                        UsuarioVO = new UsuarioVO();
                        UsuarioVO.setIdUsuario(rs.getString("usua_id"));
                        UsuarioVO.setUsua_tipo(rs.getString("usua_tipo"));
                        UsuarioVO.setIdpersona(rs.getString("usua_idpersona"));
                        UsuarioVO.setNombre_usuario(rs.getString("usua_usuario"));
                        UsuarioVO.setPassword_usuario(rs.getString("usua_contrasena"));
                        UsuarioVO.setHora_acceso(new ManejadorFechas().getFechaHoraTimeStamp());
                        UsuarioVO.setSede_id(rs.getString("sede_id"));
                        UsuarioVO.setUsua_estado(rs.getString("usua_estado"));

                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                e.ImprimirError("UsuariosDAO;Logueo;msg:" + ex.getMessage());
            } finally {
                if (this.conn.getCon() != null) {
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
                    ex.printStackTrace();

                }
            }
        }
        return UsuarioVO;
    }

    public Object ListarRolesUsuario(String usuario) throws Exception {
        System.out.println("lista roles:::" + usuario);
        UsuarioRolVO UsuarioRolVO = null;
        StringBuffer sql = null;
        ResultSet rs = null;
        ArrayList Lista = null;
        PreparedStatement ps = null;
        this.getConexion();
        if (this.ConnEstado()) {
            try {

//                System.out.println("ENTRE");
                sql = new StringBuffer();
                sql.append("SELECT  rol.role_id, \n\t \n\t");
                sql.append("        rol.role_descripcion \n\t \n\t");
                sql.append("FROM    roles rol inner join usuariorol usro on rol.role_id = usro.role_id \n\t \n\t");
                sql.append("where   rol.role_estado='ACTIVO' and usro.usua_id = ? \n\t \n\t");
                ps = this.conn.getCon().prepareStatement(sql.toString());
                ps.setString(1, usuario);
                rs = ps.executeQuery();
                if (rs.isBeforeFirst()) {
                    Lista = new ArrayList();
                    while (rs.next()) {
                        UsuarioRolVO = new UsuarioRolVO();
                        UsuarioRolVO.setRole_id(rs.getString("role_id"));
                        UsuarioRolVO.setRole_descripcion(rs.getString("role_descripcion"));
                        Lista.add(UsuarioRolVO);
                        e.ImprimirError("ListarRolesUsuario:" + UsuarioRolVO.getRole_descripcion());
                    }

//                    System.out.println("tamaño: "+ Lista.size());
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                e.ImprimirError("UsuariosDAO;ListarRolesUsuario;msg:" + ex.getMessage());
            } finally {
                if (this.closed && this.conn.getCon() != null) {
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
        return Lista;
    }

    public ArrayList<ocupacional.valueobjects.UsuarioVO> Listar() throws Exception {
        ArrayList<ocupacional.valueobjects.UsuarioVO> lista = new ArrayList<ocupacional.valueobjects.UsuarioVO>();
        ocupacional.valueobjects.UsuarioVO UsuarioVO = null;
        StringBuffer sql = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        this.getConexion();
        if (this.ConnEstado()) {
            try {
                sql = new StringBuffer();
                sql.append("select * from javap.usuarios order by usua_usuario");
                ps = this.conn.getCon().prepareStatement(sql.toString());
                rs = ps.executeQuery();
                if (rs.isBeforeFirst()) {
                    System.out.println(rs.toString());
                    while (rs.next()) {
                        UsuarioVO = new ocupacional.valueobjects.UsuarioVO();
                        UsuarioVO.setUsua_id(rs.getString("usua_id"));
                        UsuarioVO.setUsua_tipo(rs.getString("usua_tipo"));
                        UsuarioVO.setPege_id(rs.getString("usua_idpersona"));
                        UsuarioVO.setUsua_usuario(rs.getString("usua_usuario"));
                        UsuarioVO.setUsua_contrasena(rs.getString("usua_contrasena"));
                        UsuarioVO.setUsua_ultimoacceso(rs.getTimestamp("usua_ultimoacceso"));
                        UsuarioVO.setSede_id(rs.getString("sede_id"));
                        UsuarioVO.setUsua_estado(rs.getString("usua_estado"));
                        lista.add(UsuarioVO);

                    }
                }
            } catch (Exception ex) {
                e.ImprimirError("UsuariosDAO;ListarRolesUsuario;msg:" + ex.getMessage());
            } finally {
                if (this.closed && this.conn.getCon() != null) {
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

    public ocupacional.valueobjects.UsuarioVO Cargar(String id) throws Exception {
        ocupacional.valueobjects.UsuarioVO UsuarioVO = null;
        StringBuffer sql = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        this.getConexion();
        if (this.ConnEstado()) {
            try {
                sql = new StringBuffer();
                sql.append("SELECT  pege.pege_id, \n\t \n\t");
                sql.append("        usua.usua_id, \n\t \n\t");
                sql.append("        usua.usua_usuario, \n\t \n\t");
                sql.append("        usua.usua_ultimoacceso, \n\t \n\t");
                sql.append("        usua.usua_contrasena, \n\t \n\t");
                sql.append("        usua.usua_estado, \n\t \n\t");
                sql.append("        pege.pege_documento, \n\t \n\t");
                sql.append("        concat(IFNULL(natu.natu_primernombre, ''), ' ', IFNULL(natu.natu_segundonombre, ''), ' ', IFNULL(natu.natu_primerapellido, ''), ' ', IFNULL(natu.natu_segundoapellido, ''), IFNULL(juri.juri_razonsocial, '')) nombre, \n\t \n\t");
                sql.append("        usua.usua_ultimoacceso \n\t \n\t");
                sql.append("FROM    javap.usuarios usua inner join javap.personageneral pege on usua.pege_id = pege.pege_id \n\t \n\t");
                sql.append("        left join javap.naturales natu on pege.pege_id = natu.pege_id \n\t \n\t");
                sql.append("        left join javap.juridicas juri on juri.pege_id = pege.pege_id \n\t \n\t");
                sql.append("       where pege.pege_id=? \n\t \n\t");

                ps = this.conn.getCon().prepareStatement(sql.toString());

                ps.setString(1, id);

                rs = ps.executeQuery();
                if (rs.isBeforeFirst()) {
                    if (rs.next()) {
                        UsuarioVO = new ocupacional.valueobjects.UsuarioVO();
                        UsuarioVO.setUsua_id(rs.getString("usua_id"));
                        UsuarioVO.setPege_id(rs.getString("pege_id"));
                        UsuarioVO.setUsua_usuario(rs.getString("usua_usuario"));
                        UsuarioVO.setPege_nombre(rs.getString("nombre"));
                        UsuarioVO.setUsua_estado(rs.getString("usua_estado"));
                        UsuarioVO.setUsua_contrasena(rs.getString("usua_contrasena"));
                        UsuarioVO.setUsua_ultimoacceso(rs.getTimestamp("usua_ultimoacceso"));

                    }
                }
            } catch (Exception ex) {
                e.ImprimirError("UsuariosDAO;ListarRolesUsuario;msg:" + ex.getMessage());
            } finally {
                if (this.closed && this.conn.getCon() != null) {
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
        return UsuarioVO;
    }

    //public String Dibujar_Arbol(int apli_id, int pege_id, int nivel, String raiz) throws SQLException, Exception {
    public String Dibujar_Arbol(String usua_id, String role_id, int nivel, String raiz, int level) throws SQLException, Exception {
        StringBuffer dato = new StringBuffer();
        ArrayList<RolFuncionalidadVO> lista = null;
        Cadenas o = this.e.o;
        StringBuilder cadena = new StringBuilder();
        try {
            lista = (ArrayList<RolFuncionalidadVO>) this.Funcionalidades(usua_id, role_id, nivel, raiz);
//            System.out.println("tamaño" + lista.size());
            if (lista != null) {
                for (RolFuncionalidadVO RolFuncionalidadVO : lista) {
                    if (e.o.Compara(raiz, "")) {
                        level = 0;
                    }

                    if (RolFuncionalidadVO.getCan_hijos() == 0) {
                        cadena.append("<li> \n\t");
                        //cadena.append("     <a target='panelprincipal' href=\"" + o.writter(RolFuncionalidadVO.getFunc_ruta()) + "\"> " + o.writter(RolFuncionalidadVO.getFunc_descripcion()) + "</a> \n\t");
                        cadena.append("     <a onclick=\"RecargaPanel('../" + o.writter(RolFuncionalidadVO.getFunc_ruta()) + ".jsp?idf=" + RolFuncionalidadVO.getRofu_id() + "','panelprincipal');\">" + o.writter(RolFuncionalidadVO.getFunc_descripcion()) + "</a> \n\t");
                        cadena.append("</li> \n\t");
                    } else {
                        cadena.append("<li> \n\t");
                        cadena.append("     <a href=\"#\"> <strong><i class=\"fa " + o.writter(RolFuncionalidadVO.getFunc_class()) + " fa-fw\"></i> " + o.writter(RolFuncionalidadVO.getFunc_descripcion()) + "</strong><span class=\"fa arrow\"></span> </a> \n\t");
                        if (level == 0) {
                            cadena.append("         <ul class=\"nav nav-second-level\"> \n\t");
                        } else {
                            cadena.append("         <ul class=\"nav nav-third-level\"> \n\t");
                        }
                        cadena.append(Dibujar_Arbol(usua_id, role_id, RolFuncionalidadVO.getNivel() + 1, RolFuncionalidadVO.getFunc_codigo(), ++level));
                        cadena.append("         </ul> \n\t");
                        cadena.append("</li> \n\t");
                    }
                }
            }
//            System.out.println("FINALIZA \n\t");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
        }
        return (cadena.toString());
    }

    public String Dibujar_Arbol2(Roles rVO) throws SQLException, Exception {

        Cadenas o = this.e.o;
        StringBuilder cadena = new StringBuilder();
        try {
            System.out.println("llegue aqui 1");
            if (rVO.getRolfuncionalidadList() != null) {
                System.out.println("llegue aqui :;: " + rVO.getUsuariorolList().size());
                for (Rolfuncionalidad rf : rVO.getRolfuncionalidadList()) {
                    System.out.println("llegue aqui " + rf.getRofuId());

                    if (!rf.getFuncId().getFuncCodigo().contains("_")) {
                        cadena.append("<li> \n\t");
                        cadena.append("  <a href=\"#\"> <strong><i class=\"fa " + o.writter(rf.getFuncId().getClass().toString()) + " fa-fw\"></i> " + o.writter(rf.getFuncId().getFuncDescripcion()) + "</strong><span class=\"fa arrow\"></span> </a> \n\t");
                        cadena.append("<ul class=\"nav nav-second-level\"> \n\t");

                        for (Rolfuncionalidad rf2 : rVO.getRolfuncionalidadList()) {
                            if (rf2.getFuncId().getFuncCodigo().indexOf(rf.getFuncId().getFuncCodigo() + "_") != -1 && rf2.getFuncId().getFuncCodigo().length() == 5) {
                                cadena.append("<li> \n\t");
                                cadena.append("     <a href=\"#\"> <strong><i class=\"fa " + o.writter(rf2.getFuncId().getClass().toString()) + " fa-fw\"></i> " + o.writter(rf2.getFuncId().getFuncDescripcion()) + "</strong><span class=\"fa arrow\"></span> </a> \n\t");
                                cadena.append("         <ul class=\"nav nav-third-level\"> \n\t");

                                for (Rolfuncionalidad rf3 : rVO.getRolfuncionalidadList()) {
                                   if (rf3.getFuncId().getFuncCodigo().indexOf(rf2.getFuncId().getFuncCodigo() + "_") != -1 && rf3.getFuncId().getFuncCodigo().length() == 8) {
                                  
                                cadena.append("<li> \n\t");
                                cadena.append("     <a onclick=\"RecargaPanel('../" + o.writter(rf3.getFuncId().getFuncUrl()) + ".jsp?idf=" + rf3.getRofuId() + "','panelprincipal');\">" + o.writter(rf3.getFuncId().getFuncDescripcion()) + "</a> \n\t");
                                cadena.append("</li> \n\t");
                                   
                                   }   
                                }

                            
                                cadena.append("         </ul> \n\t");
                        cadena.append("</li> \n\t");
                            }
                        }
                        cadena.append("         </ul> \n\t");
                        cadena.append("</li> \n\t");

                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        } finally {
        }
        return (cadena.toString());
    }

    public Object Funcionalidades(String usua_id, String role_id, int nivel, String raiz) throws SQLException, Exception {
        PreparedStatement ps;
        ResultSet rs;
        StringBuilder sql = new StringBuilder();
        ArrayList lista = null;
        ps = null;
        rs = null;
        this.getConexion();
        if (this.ConnEstado()) {
            try {
                sql.append("SELECT  func.func_codigo,  \n\t");
                sql.append("        func.func_descripcion,  \n\t");
                sql.append("        func.func_url,  \n\t");
                sql.append("        rofu.rofu_id,  \n\t");
                //sql.append("        func.func_class,  \n\t");
                sql.append("        func.func_id,  \n\t");
                sql.append("        (SELECT COUNT(func_id)  \n\t");
                sql.append("        FROM    funcionalidades fc  \n\t");
                sql.append("        WHERE   fc.func_codigo LIKE CONCAT(func.func_codigo,'%')  \n\t");
                sql.append("                AND LENGTH(fc.func_codigo) = LENGTH(func.func_codigo)+3) CANTIDAD,  \n\t");
                sql.append("        ((LENGTH(func.func_codigo)-2)/3) NIVEL  \n\t");
                sql.append("FROM    funcionalidades func,  \n\t");
                sql.append("        rolfuncionalidad rofu,  \n\t");
                sql.append("        roles role,  \n\t");
                sql.append("        usuariorol usro  \n\t");
                sql.append("WHERE   func.func_id = rofu.func_id AND  \n\t");
                sql.append("        rofu.role_id = role.role_id AND  \n\t");
                sql.append("        role.role_id = usro.role_id AND  \n\t");
                sql.append("        usro.usua_id = " + usua_id + " AND  \n\t");
                sql.append("        role.role_id = " + role_id + " AND  \n\t");
                sql.append("        func.func_codigo LIKE '").append(raiz).append("%' AND  \n\t");
                sql.append("        LENGTH(func.func_codigo) = " + ((nivel * 3) + 2) + "  \n\t");

                sql.append("ORDER BY func.func_codigo \n\t");

                //System.out.println("ocupacional.bdatos.UsuariosDAO.Funcionalidades()" + sql.toString());
                ps = conn.getCon().prepareStatement(sql.toString());

                //ps.setString(1, usua_id);
                //ps.setString(2, role_id);
                //ps.setInt(3, ((nivel * 3) + 2));
//                System.out.println(" :::: " + sql.toString());
                rs = ps.executeQuery();
                if (rs.isBeforeFirst()) {
//                    System.out.println("ocupacional.bdatos.UsuariosDAO.Funcionalidades()>>>1 \n\t");
                    lista = new ArrayList();
                    RolFuncionalidadVO datos;
                    for (; rs.next(); lista.add(datos)) {
//                        System.out.println("ocupacional.bdatos.UsuariosDAO.Funcionalidades()>>>2 \n\t");
                        datos = new RolFuncionalidadVO();
                        datos.setFunc_id(rs.getInt("func_id"));
                        datos.setFunc_codigo(rs.getString("func_codigo"));
                        datos.setFunc_descripcion(rs.getString("func_descripcion"));
                        datos.setFunc_ruta(rs.getString("func_url"));
                        datos.setRofu_id(rs.getString("rofu_id"));
                        datos.setCan_hijos(rs.getInt("CANTIDAD"));
                        // datos.setFunc_class(rs.getString("func_class"));
                        datos.setNivel(rs.getInt("NIVEL"));
                    }
                }

            } catch (Exception ex) {
                e.ImprimirError("UsuariosDAO;Funcionalidades;msg:" + ex.getMessage());
            } finally {
                if (this.closed && this.conn.getCon() != null) {
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

    public boolean Actualizar(ocupacional.valueobjects.UsuarioVO UsuarioVO) throws Exception {
        PreparedStatement ps = null;
        ResultSet keyRS = null;
        boolean exito = false;
        this.getConexion();
        if (this.ConnEstado()) {
            try {
                this.conn.getCon().setAutoCommit(false);
                StringBuilder sql = new StringBuilder();
//                ***cambio dao***
                sql.append("UPDATE javap.usuarios SET \n"
                        + " usua_usuario=?,\n"
                        + " usua_contrasena=?,\n"
                        + " usua_registradopor=?,\n"
                        + " usua_ultimoacceso=?,\n"
                        + " usua_estado=?\n"
                        + " WHERE usua_id = ?");
                ps = this.conn.getCon().prepareStatement(sql.toString());
                int a = 1;
                ps.setString(a++, UsuarioVO.getUsua_usuario());
                ps.setString(a++, UsuarioVO.getUsua_contrasena());
                ps.setString(a++, UsuarioVO.getUsua_registradopor());
                ps.setTimestamp(a++, UsuarioVO.getUsua_ultimoacceso());
                ps.setString(a++, UsuarioVO.getUsua_estado());
                ps.setString(a++, UsuarioVO.getUsua_id());
                System.out.println(">>>> usua ->" + UsuarioVO.getUsua_id());

//                ***cambio dao***
                ps.executeUpdate();
                exito = true;
            } catch (Exception ex) {
                e.ImprimirError("UsuarioRolDAO;ocupacional.bdatos.UsuarioRolDAO.Actualizar();msg:"
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

    public boolean Eliminar(ocupacional.valueobjects.UsuarioVO UsuarioVO) throws Exception {
        PreparedStatement ps = null;
        ResultSet keyRS = null;
        boolean exito = false;
        this.getConexion();
        if (this.ConnEstado()) {
            try {
                this.conn.getCon().setAutoCommit(false);
                StringBuilder sql = new StringBuilder();
//                ***cambio dao***
                sql.append("UPDATE javap.usuarios SET \n"
                        + " usua_estado=?\n"
                        + " WHERE usua_id = ?");
                ps = this.conn.getCon().prepareStatement(sql.toString());
                int a = 1;
                ps.setString(a++, UsuarioVO.getUsua_estado());
                ps.setString(a++, UsuarioVO.getUsua_id());
                System.out.println(">>>> usua ->" + UsuarioVO.getUsua_id());

//                ***cambio dao***
                ps.executeUpdate();
                exito = true;
            } catch (Exception ex) {
                e.ImprimirError("UsuarioRolDAO;ocupacional.bdatos.UsuarioRolDAO.Actualizar();msg:"
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

    public boolean Insertar(ocupacional.valueobjects.UsuarioVO UsuarioVO) throws Exception {
        PreparedStatement ps = null;
        ResultSet keyRS = null;
        boolean exito = false;
        this.getConexion();
        if (this.ConnEstado()) {
            try {
                this.conn.getCon().setAutoCommit(false);
                StringBuilder sql = new StringBuilder();
//                ***cambio dao***
                sql.append("INSERT INTO javap.usuarios (pege_id, usua_usuario, usua_contrasena, usua_fechacambio, usua_registradopor, usua_ultimoacceso, sede_id, usua_estado) \n"
                        + "	VALUES (?,?,?,?,?,?,?,?)");
//                ps = this.conn.getCon().prepareStatement(sql.toString());
                ps = this.conn.getCon().prepareStatement(sql.toString(), PreparedStatement.RETURN_GENERATED_KEYS);
                int a = 1;
                ps.setString(a++, UsuarioVO.getPege_id());
                ps.setString(a++, UsuarioVO.getUsua_usuario());
                ps.setString(a++, UsuarioVO.getUsua_contrasena());
                ps.setTimestamp(a++, UsuarioVO.getUsua_fechacambio());
                ps.setString(a++, UsuarioVO.getUsua_registradopor());
                ps.setTimestamp(a++, UsuarioVO.getUsua_ultimoacceso());
                ps.setString(a++, UsuarioVO.getSede_id());
                ps.setString(a++, UsuarioVO.getUsua_estado());

//                ***cambio dao***
                ps.executeUpdate();

                keyRS = ps.getGeneratedKeys();
                if (keyRS.next()) {
                    UsuarioVO.setUsua_id(keyRS.getString(1));
                }
                if (UsuarioVO.getUsua_id() != null) {
                    exito = true;
                }
            } catch (Exception ex) {
                e.ImprimirError("UsuarioRolDAO;ocupacional.bdatos.UsuarioRolDAO.Actualizar();msg:"
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
}
