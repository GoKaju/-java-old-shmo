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
public class NaturalesVO {

    private String natu_primernombre;
    private String natu_segundonombre;
    private String natu_primerapellido;
    private String natu_segundoapellido;
    private String Nombre;
    private String pege_id;
    private Timestamp natu_fechanacimiento;
    private String natu_gruposanguineo;
    private String natu_estrato;
    private String esci_id;
    private Timestamp natu_fechacambio;
    private String natu_registradopor;
    private String enti_ideps;
    private String enti_idarl;
    private String sexo_id;

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getNatu_primernombre() {
        return natu_primernombre;
    }

    public void setNatu_primernombre(String natu_primernombre) {
        this.natu_primernombre = natu_primernombre;
    }

    public String getNatu_segundonombre() {
        return natu_segundonombre;
    }

    public void setNatu_segundonombre(String natu_segundonombre) {
        this.natu_segundonombre = natu_segundonombre;
    }

    public String getNatu_primerapellido() {
        return natu_primerapellido;
    }

    public void setNatu_primerapellido(String natu_primerapellido) {
        this.natu_primerapellido = natu_primerapellido;
    }

    public String getNatu_segundoapellido() {
        return natu_segundoapellido;
    }

    public void setNatu_segundoapellido(String natu_segundoapellido) {
        this.natu_segundoapellido = natu_segundoapellido;
    }

    public String getPege_id() {
        return pege_id;
    }

    public void setPege_id(String pege_id) {
        this.pege_id = pege_id;
    }

    public Timestamp getNatu_fechanacimiento() {
        return natu_fechanacimiento;
    }

    public void setNatu_fechanacimiento(Timestamp natu_fechanacimiento) {
        this.natu_fechanacimiento = natu_fechanacimiento;
    }

    public String getNatu_gruposanguineo() {
        return natu_gruposanguineo;
    }

    public void setNatu_gruposanguineo(String natu_gruposanguineo) {
        this.natu_gruposanguineo = natu_gruposanguineo;
    }

    public String getNatu_estrato() {
        return natu_estrato;
    }

    public void setNatu_estrato(String natu_estrato) {
        this.natu_estrato = natu_estrato;
    }

    public String getEsci_id() {
        return esci_id;
    }

    public void setEsci_id(String esci_id) {
        this.esci_id = esci_id;
    }

    public Timestamp getNatu_fechacambio() {
        return natu_fechacambio;
    }

    public void setNatu_fechacambio(Timestamp natu_fechacambio) {
        this.natu_fechacambio = natu_fechacambio;
    }

    public String getNatu_registradopor() {
        return natu_registradopor;
    }

    public void setNatu_registradopor(String natu_registradopor) {
        this.natu_registradopor = natu_registradopor;
    }

    public String getEnti_ideps() {
        return enti_ideps;
    }

    public void setEnti_ideps(String enti_ideps) {
        this.enti_ideps = enti_ideps;
    }

    public String getEnti_idarl() {
        return enti_idarl;
    }

    public void setEnti_idarl(String enti_idarl) {
        this.enti_idarl = enti_idarl;
    }

    public String getSexo_id() {
        return sexo_id;
    }

    public void setSexo_id(String sexo_id) {
        this.sexo_id = sexo_id;
    }

}
