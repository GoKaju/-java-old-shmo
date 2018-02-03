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
public class PersonaGeneralVO {

    private String pege_id;
    private String pege_documento;
    private String tido_id;
    private Timestamp pege_fechacambio;
    private String pege_registradopor;
    private String pege_direcciondomicilio;
    private String pege_numerotelefono;
    private String pege_numerocelular;
    private String ciud_id;
     private String pege_mail;

    public String getPege_mail() {
        return pege_mail;
    }

    public void setPege_mail(String pege_mail) {
        this.pege_mail = pege_mail;
    }

    public String getPege_id() {
        return pege_id;
    }

    public void setPege_id(String pege_id) {
        this.pege_id = pege_id;
    }

    public String getPege_documento() {
        return pege_documento;
    }

    public void setPege_documento(String pege_documento) {
        this.pege_documento = pege_documento;
    }

    public String getTido_id() {
        return tido_id;
    }

    public void setTido_id(String tido_id) {
        this.tido_id = tido_id;
    }

    public Timestamp getPege_fechacambio() {
        return pege_fechacambio;
    }

    public void setPege_fechacambio(Timestamp pege_fechacambio) {
        this.pege_fechacambio = pege_fechacambio;
    }

    public String getPege_registradopor() {
        return pege_registradopor;
    }

    public void setPege_registradopor(String pege_registradopor) {
        this.pege_registradopor = pege_registradopor;
    }

    public String getPege_direcciondomicilio() {
        return pege_direcciondomicilio;
    }

    public void setPege_direcciondomicilio(String pege_direcciondomicilio) {
        this.pege_direcciondomicilio = pege_direcciondomicilio;
    }

    public String getPege_numerotelefono() {
        return pege_numerotelefono;
    }

    public void setPege_numerotelefono(String pege_numerotelefono) {
        this.pege_numerotelefono = pege_numerotelefono;
    }

    public String getPege_numerocelular() {
        return pege_numerocelular;
    }

    public void setPege_numerocelular(String pege_numerocelular) {
        this.pege_numerocelular = pege_numerocelular;
    }

    public String getCiud_id() {
        return ciud_id;
    }

    public void setCiud_id(String ciud_id) {
        this.ciud_id = ciud_id;
    }

}
