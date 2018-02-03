/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ocupacional.valueobjects.historiaclinica;

/**
 *
 * @author D4V3
 */
public class tipoExamenMedicoVO {
    
    private String teme_id;
    
        private String teme_descripcion;
        private String teme_estado;
        private String teme_fechacambio;
        private String teme_registradopor;

    public String getTeme_id() {
        return teme_id;
    }

    public void setTeme_id(String teme_id) {
        this.teme_id = teme_id;
    }

    public String getTeme_descripcion() {
        return teme_descripcion;
    }

    public void setTeme_descripcion(String teme_descripcion) {
        this.teme_descripcion = teme_descripcion;
    }

    public String getTeme_estado() {
        return teme_estado;
    }

    public void setTeme_estado(String teme_estado) {
        this.teme_estado = teme_estado;
    }

    public String getTeme_fechacambio() {
        return teme_fechacambio;
    }

    public void setTeme_fechacambio(String teme_fechacambio) {
        this.teme_fechacambio = teme_fechacambio;
    }

    public String getTeme_registradopor() {
        return teme_registradopor;
    }

    public void setTeme_registradopor(String teme_registradopor) {
        this.teme_registradopor = teme_registradopor;
    }
    
}
