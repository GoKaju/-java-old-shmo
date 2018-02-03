/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ocupacional.valueobjects;

import java.sql.Timestamp;

/**
 *
 * @author VALERIA
 */
public class UsuarioRolVO {

    private String usro_id;
    private String usua_id;
    private String role_id;
    private String role_descripcion;
    private String usro_registradopor;
    private Timestamp usro_fechacambio;

    public String getUsro_id() {
        return usro_id;
    }

    public void setUsro_id(String usro_id) {
        this.usro_id = usro_id;
    }

    public String getUsua_id() {
        return usua_id;
    }

    public void setUsua_id(String usua_id) {
        this.usua_id = usua_id;
    }

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    public String getRole_descripcion() {
        return role_descripcion;
    }

    public void setRole_descripcion(String role_descripcion) {
        this.role_descripcion = role_descripcion;
    }

    public String getUsro_registradopor() {
        return usro_registradopor;
    }

    public void setUsro_registradopor(String usro_registradopor) {
        this.usro_registradopor = usro_registradopor;
    }

    public Timestamp getUsro_fechacambio() {
        return usro_fechacambio;
    }

    public void setUsro_fechacambio(Timestamp usro_fechacambio) {
        this.usro_fechacambio = usro_fechacambio;
    }

}
