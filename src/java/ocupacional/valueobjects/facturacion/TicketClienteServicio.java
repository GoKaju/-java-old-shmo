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
public class TicketClienteServicio {
private String tics_id;
private String tics_registradopor;
private String tick_id;
private String clse_id;
private Timestamp tics_fechacambio;

    public String getTics_id() {
        return tics_id;
    }

    public void setTics_id(String tics_id) {
        this.tics_id = tics_id;
    }

    public String getTics_registradopor() {
        return tics_registradopor;
    }

    public void setTics_registradopor(String tics_registradopor) {
        this.tics_registradopor = tics_registradopor;
    }

    public String getTick_id() {
        return tick_id;
    }

    public void setTick_id(String tick_id) {
        this.tick_id = tick_id;
    }

    public String getClse_id() {
        return clse_id;
    }

    public void setClse_id(String clse_id) {
        this.clse_id = clse_id;
    }

    public Timestamp getTics_fechacambio() {
        return tics_fechacambio;
    }

    public void setTics_fechacambio(Timestamp tics_fechacambio) {
        this.tics_fechacambio = tics_fechacambio;
    }
    
    
}
