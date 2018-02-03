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
public class SexoVO {
    private String sexo_id;
    private String sexo_descripcion;
    private Timestamp sexo_fechacambio;
    private String sexo_registradopor;

    public String getSexo_id() {
        return sexo_id;
    }

    public void setSexo_id(String sexo_id) {
        this.sexo_id = sexo_id;
    }

    public String getSexo_descripcion() {
        return sexo_descripcion;
    }

    public void setSexo_descripcion(String sexo_descripcion) {
        this.sexo_descripcion = sexo_descripcion;
    }

    public Timestamp getSexo_fechacambio() {
        return sexo_fechacambio;
    }

    public void setSexo_fechacambio(Timestamp sexo_fechacambio) {
        this.sexo_fechacambio = sexo_fechacambio;
    }

    public String getSexo_registradopor() {
        return sexo_registradopor;
    }

    public void setSexo_registradopor(String sexo_registradopor) {
        this.sexo_registradopor = sexo_registradopor;
    }
    
    
}
