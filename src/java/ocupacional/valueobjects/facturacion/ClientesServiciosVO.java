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
public class ClientesServiciosVO {
    private String  clse_id;
    private String  clie_id;
    private String  serv_id;
    private String  clse_valor;
    private String  clse_observacion;
    private String  clse_registradopor;
    private String  serv_descripcion;
    private Timestamp  clse_fechacambio;

    public String getServ_descripcion() {
        return serv_descripcion;
    }

    public void setServ_descripcion(String serv_descripcion) {
        this.serv_descripcion = serv_descripcion;
    }

    public String getClse_id() {
        return clse_id;
    }

    public void setClse_id(String clse_id) {
        this.clse_id = clse_id;
    }

    public String getClie_id() {
        return clie_id;
    }

    public void setClie_id(String clie_id) {
        this.clie_id = clie_id;
    }

    public String getServ_id() {
        return serv_id;
    }

    public void setServ_id(String serv_id) {
        this.serv_id = serv_id;
    }

    public String getClse_valor() {
        return clse_valor;
    }

    public void setClse_valor(String clse_valor) {
        this.clse_valor = clse_valor;
    }

    public String getClse_observacion() {
        return clse_observacion;
    }

    public void setClse_observacion(String clse_observacion) {
        this.clse_observacion = clse_observacion;
    }

    public String getClse_registradopor() {
        return clse_registradopor;
    }

    public void setClse_registradopor(String clse_registradopor) {
        this.clse_registradopor = clse_registradopor;
    }

    public Timestamp getClse_fechacambio() {
        return clse_fechacambio;
    }

    public void setClse_fechacambio(Timestamp clse_fechacambio) {
        this.clse_fechacambio = clse_fechacambio;
    }
    
    
}
