/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ocupacional.utils;

import java.io.IOException;
import java.io.Writer;
import java.util.LinkedHashMap;
import org.json.simple.JSONStreamAware;
import org.json.simple.JSONValue;

/**
 *
 * @author D4V3
 */
public class CampoFormJSON implements  JSONStreamAware{
    
    private String campo;
    private String value;
    private String Atrib;
    private String valueAtrib;

    public String getAtrib() {
        return Atrib;
    }

    public void setAtrib(String Atrib) {
        this.Atrib = Atrib;
    }

    public String getValueAtrib() {
        return valueAtrib;
    }

    public void setValueAtrib(String valueAtrib) {
        this.valueAtrib = valueAtrib;
    }

    public CampoFormJSON(String campo, String Atrib, String valueAtrib) {
        this.campo = campo;
        this.Atrib = Atrib;
        this.valueAtrib = valueAtrib;
    }
    

    public CampoFormJSON(String campo, String value) {
        this.campo = campo;
        this.value = value;
    }
    

    public String getCampo() {
        return campo;
    }

    public void setCampo(String campo) {
        this.campo = campo;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public void writeJSONString(Writer writer) throws IOException {
        LinkedHashMap obj= new LinkedHashMap();
        obj.put("campo", campo);
        obj.put("value", value);
        obj.put("Atrib", Atrib);
        obj.put("valueAtrib", valueAtrib);
        JSONValue.writeJSONString(obj, writer);
        
    }

 
    
    
    
}
