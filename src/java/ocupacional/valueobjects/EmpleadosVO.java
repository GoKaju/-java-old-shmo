/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ocupacional.valueobjects;

import java.sql.Timestamp;

/**
 *
 * @author Sebas
 */
public class EmpleadosVO {
    
    private String pege_id;
    private String sede_id;
    private String usua_id;
    private Timestamp empl_fechaingreso;
    private String empl_area;
    private Timestamp empl_fechaCambio;
    private String empl_registradoPor;

    public String getPege_id() {
        return pege_id;
    }

    public void setPege_id(String pege_id) {
        this.pege_id = pege_id;
    }

    public String getSede_id() {
        return sede_id;
    }

    public void setSede_id(String sede_id) {
        this.sede_id = sede_id;
    }

    public String getUsua_id() {
        return usua_id;
    }

    public void setUsua_id(String usua_id) {
        this.usua_id = usua_id;
    }

    public Timestamp getEmpl_fechaingreso() {
        return empl_fechaingreso;
    }

    public void setEmpl_fechaingreso(Timestamp empl_id) {
        this.empl_fechaingreso = empl_id;
    }

    public String getEmpl_area() {
        return empl_area;
    }

    public void setEmpl_area(String empl_area) {
        this.empl_area = empl_area;
    }

    public Timestamp getEmpl_fechaCambio() {
        return empl_fechaCambio;
    }

    public void setEmpl_fechaCambio(Timestamp empl_fechaCambio) {
        this.empl_fechaCambio = empl_fechaCambio;
    }

    public String getEmpl_registradoPor() {
        return empl_registradoPor;
    }

    public void setEmpl_registradoPor(String empl_registradoPor) {
        this.empl_registradoPor = empl_registradoPor;
    }
    
    
    
}
