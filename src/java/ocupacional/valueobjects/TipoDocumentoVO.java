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
public class TipoDocumentoVO {
    private String tipo_id;
    private String tipo_descripcion;
    private Timestamp tipo_fechacambio;
    private String tipo_registradopor;

    public String getTipo_id() {
        return tipo_id;
    }

    public void setTipo_id(String tipo_id) {
        this.tipo_id = tipo_id;
    }

    public String getTipo_descripcion() {
        return tipo_descripcion;
    }

    public void setTipo_descripcion(String tipo_descripcion) {
        this.tipo_descripcion = tipo_descripcion;
    }

    public Timestamp getTipo_fechacambio() {
        return tipo_fechacambio;
    }

    public void setTipo_fechacambio(Timestamp tipo_fechacambio) {
        this.tipo_fechacambio = tipo_fechacambio;
    }

    public String getTipo_registradopor() {
        return tipo_registradopor;
    }

    public void setTipo_registradopor(String tipo_registradopor) {
        this.tipo_registradopor = tipo_registradopor;
    }
    
    
}
