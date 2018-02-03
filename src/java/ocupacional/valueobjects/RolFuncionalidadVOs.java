/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ocupacional.valueobjects;

import java.sql.Timestamp;

/**
 *
 * @author D4V3
 */
public class RolFuncionalidadVOs {
    
    private String Rofu_id;
    private String Rofu_registradopor;
    private Timestamp Rofu_fechacambio;
    private String Role_id;
    private String func_id;
    private String rofu_op;

    

    public String getRofu_op() {
        return rofu_op;
    }

    public void setRofu_op(String rofu_op) {
        this.rofu_op = rofu_op;
    }

    public String getRofu_id() {
        return Rofu_id;
    }

    public void setRofu_id(String Rofu_id) {
        this.Rofu_id = Rofu_id;
    }

    public String getRofu_registradopor() {
        return Rofu_registradopor;
    }

    public void setRofu_registradopor(String Rofu_registradopor) {
        this.Rofu_registradopor = Rofu_registradopor;
    }

    public Timestamp getRofu_fechacambio() {
        return Rofu_fechacambio;
    }

    public void setRofu_fechacambio(Timestamp Rofu_fechacambio) {
        this.Rofu_fechacambio = Rofu_fechacambio;
    }

    public String getRole_id() {
        return Role_id;
    }

    public void setRole_id(String Role_id) {
        this.Role_id = Role_id;
    }

    public String getFunc_id() {
        return func_id;
    }

    public void setFunc_id(String func_id) {
        this.func_id = func_id;
    }
    
}
