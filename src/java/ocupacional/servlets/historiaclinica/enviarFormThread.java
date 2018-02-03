/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ocupacional.servlets.historiaclinica;

import com.google.gson.Gson;
import formularios.controlers.RespuestasJpaController;
import formularios.entidades.Respuestas;
import java.util.List;
import valeria.metodos.ManejadorFechas;

/**
 *
 * @author DJGOMEZ
 */
public class enviarFormThread extends Thread{
    private final List<Respuestas> lista;
    private final RespuestasJpaController rdao;
    private final int anotacion;
    ManejadorFechas f = new ManejadorFechas();

    public enviarFormThread(List<Respuestas> lista, RespuestasJpaController rdao,int anotacion) {
      
        this.lista = lista;
        this.rdao = rdao;
        this.anotacion = anotacion;
    }
    public boolean enviarForm(){
        System.out.println("::::::::::::::::::::"+f.getFechaHoraTimeStamp());
        System.out.println("Enviar Formulario anotacion:: "+anotacion);
       
    try
    {   
//        Gson gson = new Gson();
//        System.out.println(gson.toJson(lista));
        for (Respuestas r : lista) {
            System.out.println("campo: "+ r.getCampId().getCampLabel());
            System.out.println("resp : "+ r.getRespDescripcion());
            rdao.create(r);
        }
        
        System.out.println("Envie Formulario ");
        System.out.println("::::::::::::::::::::"+f.getFechaHoraTimeStamp());
    return true;
    }
    catch(Exception ex){
        ex.printStackTrace();
        return false;
    }
        
    
    
    }
    
    
    
    @Override
    public void run(){
      try {
            if(enviarForm()){
            Thread.sleep(20000);
            }else{
            Thread.sleep(20000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }}
    
}
