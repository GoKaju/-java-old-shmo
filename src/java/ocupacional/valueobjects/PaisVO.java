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
public class PaisVO {
    private String pais_id;
    private String pais_nombre;
    private Timestamp pais_fechacambio;
    private String pais_registradopor;
    private String pais_codigo;

    public String getPais_id() {
        return pais_id;
    }

    public void setPais_id(String pais_id) {
        this.pais_id = pais_id;
    }

    public String getPais_nombre() {
        return pais_nombre;
    }

    public void setPais_nombre(String pais_nombre) {
        this.pais_nombre = pais_nombre;
    }

    public Timestamp getPais_fechacambio() {
        return pais_fechacambio;
    }

    public void setPais_fechacambio(Timestamp pais_fechacambio) {
        this.pais_fechacambio = pais_fechacambio;
    }

    public String getPais_registradopor() {
        return pais_registradopor;
    }

    public void setPais_registradopor(String pais_registradopor) {
        this.pais_registradopor = pais_registradopor;
    }

    public String getPais_codigo() {
        return pais_codigo;
    }

    public void setPais_codigo(String pais_codigo) {
        this.pais_codigo = pais_codigo;
    }
    
    
            
}
