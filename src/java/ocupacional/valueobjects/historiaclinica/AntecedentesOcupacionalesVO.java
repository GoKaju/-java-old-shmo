/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ocupacional.valueobjects.historiaclinica;

import java.sql.Timestamp;

/**
 *
 * @author Sebas
 */
public class AntecedentesOcupacionalesVO {

    private String anoc_id;
    private String hicl_id;
    private String anoc_leciones;
    private String anoc_enfermedadP;
    private String anoc_secuelas;
    private String anoc_registradopor;
    private Timestamp anoc_fechacambio;

    public String getAnoc_id() {
        return anoc_id;
    }

    public void setAnoc_id(String anoc_id) {
        this.anoc_id = anoc_id;
    }

    public String getHicl_id() {
        return hicl_id;
    }

    public void setHicl_id(String hicl_id) {
        this.hicl_id = hicl_id;
    }

    public String getAnoc_leciones() {
        return anoc_leciones;
    }

    public void setAnoc_leciones(String anoc_leciones) {
        this.anoc_leciones = anoc_leciones;
    }

    public String getAnoc_enfermedadP() {
        return anoc_enfermedadP;
    }

    public void setAnoc_enfermedadP(String anoc_enfermedadP) {
        this.anoc_enfermedadP = anoc_enfermedadP;
    }

    public String getAnoc_secuelas() {
        return anoc_secuelas;
    }

    public void setAnoc_secuelas(String anoc_secuelas) {
        this.anoc_secuelas = anoc_secuelas;
    }

    public String getAnoc_registradopor() {
        return anoc_registradopor;
    }

    public void setAnoc_registradopor(String anoc_registradopor) {
        this.anoc_registradopor = anoc_registradopor;
    }

    public Timestamp getAnoc_fechacambio() {
        return anoc_fechacambio;
    }

    public void setAnoc_fechacambio(Timestamp anoc_fechacambio) {
        this.anoc_fechacambio = anoc_fechacambio;
    }

}
