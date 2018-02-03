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
public class UsuarioVO {
  private String usua_tipo;
  private String usua_estado;
  private String pege_nombre;
  private String usua_id; 
   private String pege_id;
   private String usua_usuario;
   private String usua_contrasena;
   private Timestamp usua_fechacambio;
   private String usua_registradopor;
   private String sede_id;
   private Timestamp usua_ultimoacceso;

    public String getUsua_tipo() {
        return usua_tipo;
    }

    public void setUsua_tipo(String usua_tipo) {
        this.usua_tipo = usua_tipo;
    }

    public String getSede_id() {
        return sede_id;
    }

    public void setSede_id(String sede_id) {
        this.sede_id = sede_id;
    }

    public String getUsua_estado() {
        return usua_estado;
    }

    public void setUsua_estado(String usua_estado) {
        this.usua_estado = usua_estado;
    }

    public String getPege_nombre() {
        return pege_nombre;
    }

    public void setPege_nombre(String pege_nombre) {
        this.pege_nombre = pege_nombre;
    }

    public String getUsua_id() {
        return usua_id;
    }

    public void setUsua_id(String usua_id) {
        this.usua_id = usua_id;
    }

    public String getPege_id() {
        return pege_id;
    }

    public void setPege_id(String pege_id) {
        this.pege_id = pege_id;
    }

    public String getUsua_usuario() {
        return usua_usuario;
    }

    public void setUsua_usuario(String usua_usuario) {
        this.usua_usuario = usua_usuario;
    }

    public String getUsua_contrasena() {
        return usua_contrasena;
    }

    public void setUsua_contrasena(String usua_contrasena) {
        this.usua_contrasena = usua_contrasena;
    }

    public Timestamp getUsua_fechacambio() {
        return usua_fechacambio;
    }

    public void setUsua_fechacambio(Timestamp usua_fechacambio) {
        this.usua_fechacambio = usua_fechacambio;
    }

    public String getUsua_registradopor() {
        return usua_registradopor;
    }

    public void setUsua_registradopor(String usua_registradopor) {
        this.usua_registradopor = usua_registradopor;
    }

    public Timestamp getUsua_ultimoacceso() {
        return usua_ultimoacceso;
    }

    public void setUsua_ultimoacceso(Timestamp usua_ultimoacceso) {
        this.usua_ultimoacceso = usua_ultimoacceso;
    }
   
   
}
