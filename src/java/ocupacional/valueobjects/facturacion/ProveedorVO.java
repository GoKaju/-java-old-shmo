/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ocupacional.valueobjects.facturacion;

import java.sql.Timestamp;

/**
 *
 * @author D4V3
 */
public class ProveedorVO {
    
    private String prov_id;
    private String pege_id;
    private String prov_registradopor;
    private String prov_observacion;
    private String prov_tipo;
    private String prov_estado;
    private Timestamp prov_fechacambio;

    public String getProv_id() {
        return prov_id;
    }

    public void setProv_id(String prov_id) {
        this.prov_id = prov_id;
    }

    public String getPege_id() {
        return pege_id;
    }

    public void setPege_id(String pege_id) {
        this.pege_id = pege_id;
    }

    public String getProv_registradopor() {
        return prov_registradopor;
    }

    public void setProv_registradopor(String prov_registradopor) {
        this.prov_registradopor = prov_registradopor;
    }

    public String getProv_observacion() {
        return prov_observacion;
    }

    public void setProv_observacion(String prov_observacion) {
        this.prov_observacion = prov_observacion;
    }

    public String getProv_tipo() {
        return prov_tipo;
    }

    public void setProv_tipo(String prov_tipo) {
        this.prov_tipo = prov_tipo;
    }

    public String getProv_estado() {
        return prov_estado;
    }

    public void setProv_estado(String prov_estado) {
        this.prov_estado = prov_estado;
    }

    public Timestamp getProv_fechacambio() {
        return prov_fechacambio;
    }

    public void setProv_fechacambio(Timestamp prov_fechacambio) {
        this.prov_fechacambio = prov_fechacambio;
    }
    
    
    
}
