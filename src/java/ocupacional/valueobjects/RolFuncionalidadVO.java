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
public class RolFuncionalidadVO {

    
    
    private int func_id;
    private int pege_id;
    private String fuus_registradopor;
    private Timestamp fuus_fechacambio;

    private String func_codigo;
    private String func_titulo;
    private String func_descripcion;
    private String func_ruta;
    private String rofu_id;
    private String func_class;
    private int can_hijos;
    private int nivel;

    public String getRofu_id() {
        return rofu_id;
    }

    public void setRofu_id(String rofu_id) {
        this.rofu_id = rofu_id;
    }

    public String getFunc_class() {
        return func_class;
    }

    public void setFunc_class(String func_class) {
        this.func_class = func_class;
    }

    public int getFunc_id() {
        return func_id;
    }

    public void setFunc_id(int func_id) {
        this.func_id = func_id;
    }

    public int getPege_id() {
        return pege_id;
    }

    public void setPege_id(int pege_id) {
        this.pege_id = pege_id;
    }

    public String getFuus_registradopor() {
        return fuus_registradopor;
    }

    public void setFuus_registradopor(String fuus_registradopor) {
        this.fuus_registradopor = fuus_registradopor;
    }

    public Timestamp getFuus_fechacambio() {
        return fuus_fechacambio;
    }

    public void setFuus_fechacambio(Timestamp fuus_fechacambio) {
        this.fuus_fechacambio = fuus_fechacambio;
    }

    public String getFunc_codigo() {
        return func_codigo;
    }

    public void setFunc_codigo(String func_codigo) {
        this.func_codigo = func_codigo;
    }

    public String getFunc_titulo() {
        return func_titulo;
    }

    public void setFunc_titulo(String func_titulo) {
        this.func_titulo = func_titulo;
    }

    public String getFunc_descripcion() {
        return func_descripcion;
    }

    public void setFunc_descripcion(String func_descripcion) {
        this.func_descripcion = func_descripcion;
    }

    public String getFunc_ruta() {
        return func_ruta;
    }

    public void setFunc_ruta(String func_ruta) {
        this.func_ruta = func_ruta;
    }

    public int getCan_hijos() {
        return can_hijos;
    }

    public void setCan_hijos(int can_hijos) {
        this.can_hijos = can_hijos;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

}
