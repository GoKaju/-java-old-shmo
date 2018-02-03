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
public class ProveedoresExamenesVO {
    
    private String prex_id;
    private String prov_id;
    private String exam_id;
    private String prex_observacion;
    private String prex_registradopor;
    private Timestamp prex_fechacambio;
    private String prex_estado;

    public String getPrex_id() {
        return prex_id;
    }

    public void setPrex_id(String prex_id) {
        this.prex_id = prex_id;
    }

    public String getProv_id() {
        return prov_id;
    }

    public void setProv_id(String prov_id) {
        this.prov_id = prov_id;
    }

    public String getExam_id() {
        return exam_id;
    }

    public void setExam_id(String exam_id) {
        this.exam_id = exam_id;
    }

    public String getPrex_observacion() {
        return prex_observacion;
    }

    public void setPrex_observacion(String prex_observacion) {
        this.prex_observacion = prex_observacion;
    }

    public String getPrex_registradopor() {
        return prex_registradopor;
    }

    public void setPrex_registradopor(String prex_registradopor) {
        this.prex_registradopor = prex_registradopor;
    }

    public Timestamp getPrex_fechacambio() {
        return prex_fechacambio;
    }

    public void setPrex_fechacambio(Timestamp prex_fechacambio) {
        this.prex_fechacambio = prex_fechacambio;
    }

    public String getPrex_estado() {
        return prex_estado;
    }

    public void setPrex_estado(String prex_estado) {
        this.prex_estado = prex_estado;
    }
    
    
    
    
    
    
}
