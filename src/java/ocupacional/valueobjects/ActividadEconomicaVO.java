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
public class ActividadEconomicaVO {

    private String acec_id;
    private String acec_descripcion;
    private String acec_codigo;
    private Timestamp acec_fechacambio;
    private String acec_registradopor;

    public String getAcec_id() {
        return acec_id;
    }

    public void setAcec_id(String acec_id) {
        this.acec_id = acec_id;
    }

    public String getAcec_descripcion() {
        return acec_descripcion;
    }

    public void setAcec_descripcion(String acec_descripcion) {
        this.acec_descripcion = acec_descripcion;
    }

    public String getAcec_codigo() {
        return acec_codigo;
    }

    public void setAcec_codigo(String acec_codigo) {
        this.acec_codigo = acec_codigo;
    }

    public Timestamp getAcec_fechacambio() {
        return acec_fechacambio;
    }

    public void setAcec_fechacambio(Timestamp acec_fechacambio) {
        this.acec_fechacambio = acec_fechacambio;
    }

    public String getAcec_registradopor() {
        return acec_registradopor;
    }

    public void setAcec_registradopor(String acec_registradopor) {
        this.acec_registradopor = acec_registradopor;
    }

}
