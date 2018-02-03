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
public class Ticket {
    
    private String tick_id;
    private String teme_id;
    private String tick_estado;
    private String tick_clsede;
    private String ceco_id;
    private String sede_id;
    private String tick_paciente;
    private String tick_paciente_nombre;
    private String tick_paciente_documento;
    private String tick_registradopor;
    private String tick_otroexamen;
    private String tick_brigada;
    private Timestamp tick_fechacambio;
    private Timestamp tick_fecharegistro;
    private Timestamp tick_fecharecepcion;
    private Timestamp tick_fechaprocesado;

    public String getTick_otroexamen() {
        return tick_otroexamen;
    }

    public void setTick_otroexamen(String tick_otroexamen) {
        this.tick_otroexamen = tick_otroexamen;
    }

    public String getTick_brigada() {
        return tick_brigada;
    }

    public void setTick_brigada(String tick_brigada) {
        this.tick_brigada = tick_brigada;
    }
    

    public String getTick_clsede() {
        return tick_clsede;
    }

    public void setTick_clsede(String tick_clsede) {
        this.tick_clsede = tick_clsede;
    }

    public String getTeme_id() {
        return teme_id;
    }

    public void setTeme_id(String teme_id) {
        this.teme_id = teme_id;
    }
    

    public Timestamp getTick_fecharegistro() {
        return tick_fecharegistro;
    }

    public void setTick_fecharegistro(Timestamp tick_fecharegistro) {
        this.tick_fecharegistro = tick_fecharegistro;
    }

    public Timestamp getTick_fecharecepcion() {
        return tick_fecharecepcion;
    }

    public void setTick_fecharecepcion(Timestamp tick_fecharecepcion) {
        this.tick_fecharecepcion = tick_fecharecepcion;
    }

    public Timestamp getTick_fechaprocesado() {
        return tick_fechaprocesado;
    }

    public void setTick_fechaprocesado(Timestamp tick_fechaprocesado) {
        this.tick_fechaprocesado = tick_fechaprocesado;
    }

    public String getTick_paciente_nombre() {
        return tick_paciente_nombre;
    }

    public void setTick_paciente_nombre(String tick_paciente_nombre) {
        this.tick_paciente_nombre = tick_paciente_nombre;
    }

    public String getTick_paciente_documento() {
        return tick_paciente_documento;
    }

    public void setTick_paciente_documento(String tick_paciente_documento) {
        this.tick_paciente_documento = tick_paciente_documento;
    }

    public String getTick_id() {
        return tick_id;
    }

    public void setTick_id(String tick_id) {
        this.tick_id = tick_id;
    }

    public String getTick_estado() {
        return tick_estado;
    }

    public void setTick_estado(String tick_estado) {
        this.tick_estado = tick_estado;
    }

    public String getCeco_id() {
        return ceco_id;
    }

    public void setCeco_id(String ceco_id) {
        this.ceco_id = ceco_id;
    }

    public String getSede_id() {
        return sede_id;
    }

    public void setSede_id(String sede_id) {
        this.sede_id = sede_id;
    }

    public String getTick_paciente() {
        return tick_paciente;
    }

    public void setTick_paciente(String tick_paciente) {
        this.tick_paciente = tick_paciente;
    }

    public String getTick_registradopor() {
        return tick_registradopor;
    }

    public void setTick_registradopor(String tick_registradopor) {
        this.tick_registradopor = tick_registradopor;
    }

    public Timestamp getTick_fechacambio() {
        return tick_fechacambio;
    }

    public void setTick_fechacambio(Timestamp tick_fechacambio) {
        this.tick_fechacambio = tick_fechacambio;
    }
    
    
    
}
