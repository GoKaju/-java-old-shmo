/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.chat;

/**
 *
 * @author D4V3
 */
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import formularios.controlers.EmpleadosJpaController;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import ocupacional.JPA.controlers.ClientesJpaController;
import ocupacional.JPA.controlers.JuridicasJpaController;
import ocupacional.JPA.controlers.UsuariosJpaController;
import ocupacional.JPA.valueobjects.Usuarios;

@ServerEndpoint("/Chat/{key}")
public class chat_server {
    
    private class conversacion{
       private  List<Session> integrantes;
       private String nombre;
       private String key;

        public String getKey() {
            return key;
        }
       public void setKey(String key) {
            this.key = key;
        }
       

        public List<Session> getIntegrantes() {
            return integrantes;
        }

        public void setIntegrantes(List<Session> integrantes) {
            this.integrantes = integrantes;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }
    }
    
    private enum status{
    ONLINE,OFFLINE;
        
    
    }
    
    private class mje{
    
        private String de;
        private String accion;
        private String conver_id;
        private String mje;
        private String id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getDe() {
            return de;
        }

        public void setDe(String de) {
            this.de = de;
        }

        public String getAccion() {
            return accion;
        }

        public void setAccion(String accion) {
            this.accion = accion;
        }

        public String getConver_id() {
            return conver_id;
        }

        public void setConver_id(String conver_id) {
            this.conver_id = conver_id;
        }

        @Override
        public String toString() {
            return "mje{" + "de=" + de + ", accion=" + accion + ", conver_id=" + conver_id + ", mje=" + mje + ", id=" + id + '}';
        }

    

      
        

        public String getMje() {
            return mje;
        }

        public void setMje(String mje) {
            this.mje = mje;
        }
        
        
         
        
    
    }
    
    private class mjeStatus{
    status status;
    String id;
    String accion;
    String nombre;
    String tipo;

        @Override
        public String toString() {
            return "mjeStatus{" + "status=" + status + ", id=" + id + ", accion=" + accion + ", nombre=" + nombre + ", tipo=" + tipo + '}';
        }

     
    
    
    };
            
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaP");
 
    private static final Map<String, conversacion> conversations = Collections.synchronizedMap(new HashMap<String, conversacion>());
    private static final Map<String, Session> users = Collections.synchronizedMap(new HashMap<String, Session>());

    @OnOpen
    public void open(Session session, @PathParam("key") final String key) {
        System.out.println("conectado Chat id: " + key);
        users.put(key, session);
        this.enviarEstatusAll(status.ONLINE, key);
    }

    @OnMessage
    public void mensaje(String mensaje, @PathParam("key") final String key) {
        System.out.println("mje " + mensaje + " key " + key);
                        Gson gson = new GsonBuilder().create();
mje m = (mje) gson.fromJson(mensaje, mje.class);
        System.out.println(m); 
        
        if(m.getAccion().equals("newConversation")){
        
        this.newConversation(m);
        
        }
        
        
        

    }

    @OnClose
    public void onClose(Session session, @PathParam("key") final String key) {
        System.out.println("cerre una conexion " + users.size() + " key " + key);
        // removemos la conexion
        users.remove(key);
        this.enviarEstatusAll(status.OFFLINE, key);
        

    }

    @OnError
    public void onError(Session session, Throwable thr) {
        System.out.println("ha ocurrido un error :(");
       thr.printStackTrace();
        System.out.println(thr.getMessage());
     
    }
    
    
    private void enviarEstatusAll( status estatus ,String id){
        
        Iterator it = users.entrySet().iterator();
        Session remitente = users.get(id);
        //mensaje de estado
        mjeStatus m = new mjeStatus();
        m.status= estatus;
        m.id=id;
        m.accion="status";
//        Usuario
        Usuarios u = new UsuariosJpaController(emf).findUsuarios(Integer.parseInt(id));
        m.tipo=u.getUsuaTipo();
        // buscar nombre
     m.nombre= this.buscarNombre(u);
  
     
     mjeStatus m2 = new mjeStatus();
        m2.status= estatus;
        m2.accion="status";
        
    while (it.hasNext()) {
         Map.Entry pair = (Map.Entry)it.next();
        if(!pair.getKey().equals(id)){
        Session s= (Session)pair.getValue();
            try {
                Gson gson = new GsonBuilder().create();
              s.getBasicRemote().sendText(gson.toJson(m));
              
              m2.id=pair.getKey().toString();
              //        Usuario
        Usuarios u2 = new UsuariosJpaController(emf).findUsuarios(Integer.parseInt(m2.id));
           
        m2.tipo=u2.getUsuaTipo();
        // buscar nombre
     m2.nombre= this.buscarNombre(u2);
    
              remitente.getBasicRemote().sendText(gson.toJson(m2));
             
              
        System.out.println(m);
            } catch (IOException ex) {
                Logger.getLogger(chat_server.class.getName()).log(Level.SEVERE, null, ex);
            }
//        it.remove(); // avoids a ConcurrentModificationException
    }
    }
    }
    
    private void newConversation(mje m){
    
    conversacion conv = new conversacion();
    conv.setKey(m.getConver_id());
    List<Session> integ=  (List) new  ArrayList<Session>();
    integ.add(users.get(m.getDe()));
    integ.add(users.get(m.getId()));
    conv.setIntegrantes(integ);
   String nombre = this.buscarNombre(new UsuariosJpaController(emf).findUsuarios(Integer.parseInt(m.getDe())));
   nombre+=", ";
  nombre += this.buscarNombre(new UsuariosJpaController(emf).findUsuarios(Integer.parseInt(m.getId())));
        conv.setNombre(nombre);
        
    conversations.put(conv.getKey(), conv);
    
    
    }
    
    private String buscarNombre (Usuarios u2){
    
    
         // buscar nombre
     if(u2.getUsuaTipo().equals("C")){
     return new ClientesJpaController(emf).findClientes(u2.getUsuaIdpersona()).getClieNombre();
     }else if (u2.getUsuaTipo().equals("E")){
     return  new EmpleadosJpaController(emf).findEmpleados(u2.getUsuaIdpersona()).getEmplNombres();
     }else if (u2.getUsuaTipo().equals("L")){
        return  new JuridicasJpaController(emf).findJuridicas(u2.getUsuaIdpersona()).getJuriRazonsocial();
         
     }else{
     return null;
     }
    }
    
}
