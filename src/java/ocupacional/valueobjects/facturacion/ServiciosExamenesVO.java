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
public class ServiciosExamenesVO {
    
    private String  seex_id;
    private String  exam_id;
    private String  serv_id;
    private String  seex_registradopor;
    private Timestamp  seex_fechacambio;

    public String getSeex_id() {
        return seex_id;
    }

    public void setSeex_id(String seex_id) {
        this.seex_id = seex_id;
    }

    public String getExam_id() {
        return exam_id;
    }

    public void setExam_id(String exam_id) {
        this.exam_id = exam_id;
    }

    public String getServ_id() {
        return serv_id;
    }

    public void setServ_id(String serv_id) {
        this.serv_id = serv_id;
    }

    public String getSeex_registradopor() {
        return seex_registradopor;
    }

    public void setSeex_registradopor(String seex_registradopor) {
        this.seex_registradopor = seex_registradopor;
    }

    public Timestamp getSeex_fechacambio() {
        return seex_fechacambio;
    }

    public void setSeex_fechacambio(Timestamp seex_fechacambio) {
        this.seex_fechacambio = seex_fechacambio;
    }
    
}
