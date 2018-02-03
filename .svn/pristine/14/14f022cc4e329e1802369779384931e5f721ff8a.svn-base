/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ocupacional.servlets.historiaclinica;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

/**
 *
 * @author D4V3
 */
public class variables {
     public Properties getProperties(String ruta ) {
        try {  
            //se crea una instancia a la clase Properties      
            Properties propiedades = new Properties();
            //se leen el archivo .properties            
            System.out.println("entre "+ruta);
            InputStream ie= getClass().getClassLoader().getResourceAsStream("properties.properties");
            System.out.println("entre 2");
           
            if(ie!=null){
            
             propiedades.load(ie);
            System.out.println("entre3");
            //si el archivo de propiedades NO esta vacio retornan las propiedes leidas            
            if (!propiedades.isEmpty()) {
                return propiedades;
            }   
        else {//sino  retornara NULL    
                    return null;      
                    }
            
            }
            
            
           
        else {//sino  retornara NULL    
                    return null;      
                    }
    
        }catch (IOException ex) { 
            ex.printStackTrace();
    return null;
    }
}
     public boolean cambiarValidar(String val,String ruta ) {
        try {
            System.out.println("RUTA:: "+ ruta);
        Properties props = new Properties();
        props.setProperty("apw", val);
            File f = new File(ruta);
            OutputStream out = new FileOutputStream( f );
        props.store(out, "");
            System.out.println("FILE:: "+ f.getAbsolutePath());           
        return true;
    }
    catch (Exception e ) {
        e.printStackTrace();
        return false;
    
    }
}
     public boolean validar(String ruta ) {
     Properties p = this.getProperties(ruta);
     if(p!=null){
         return p.get("apw")!=null&&p.get("apw").equals("1");
     
     }
     else{
     return false;
     }
     
     }
     
     
     
    
}
