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
public class RolesVO {
    private String role_id;
    private String role_descripcion;
    private Timestamp role_fechacambio;
    private String role_registradopor;
    private String role_estado;

    public String getRole_estado() {
        return role_estado;
    }

    public void setRole_estado(String role_estado) {
        this.role_estado = role_estado;
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

    public Timestamp getRole_fechacambio() {
        return role_fechacambio;
    }

    public void setRole_fechacambio(Timestamp role_fechacambio) {
        this.role_fechacambio = role_fechacambio;
    }

    public String getRole_registradopor() {
        return role_registradopor;
    }

    public void setRole_registradopor(String role_registradopor) {
        this.role_registradopor = role_registradopor;
    }
    
    
}
