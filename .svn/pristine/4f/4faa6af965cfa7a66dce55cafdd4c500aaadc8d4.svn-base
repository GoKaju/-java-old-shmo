/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

/**
 *
 * @author D4V3
 */
import formularios.controlers.AnotacionesJpaController;
import formularios.controlers.HuellafirmaJpaController;
import formularios.entidades.Anotaciones;
import formularios.entidades.Huellafirma;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
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

@ServerEndpoint("/WsHF/{tipo}/{key}")
public class WsHuellaFirma {
// creamos un hashmap sincronizado para contener las conexiones
//    public static Map<String,Session>li= new HashMap<String, Session>();

    private static final Map<String, Session> sessions = Collections.synchronizedMap(new HashMap<String, Session>());

    @OnOpen
    public void open(Session session, @PathParam("key") final String key, @PathParam("tipo") final String tipo) {
//tipo  D= dispositivo C= cliente ambos deben tener la misma key
        System.out.println("abri una conexion " + sessions.size() + " tipo " + tipo + " key " + key);

        sessions.put(tipo + key, session);

    }

//@OnMessage
//public void mensaje(ByteBuffer mensaje, @PathParam("key") final String key, @PathParam("tipo") final String tipo){
//
//    System.out.println("data "+mensaje);
//    
//     if(tipo.equals("C")){
//   
//       try {
//          Session sF= sessions.get("D"+key);
//          Session sH= sessions.get("H"+key);
//          if(sF!=null){
//          sF.getBasicRemote().sendBinary(mensaje);
//          }
//          if(sH!=null){
//          sH.getBasicRemote().sendBinary(mensaje);
//          }
//       } catch (IOException ex) {
//           Logger.getLogger(WsHuellaFirma.class.getName()).log(Level.SEVERE, null, ex);
//       }  
//   }else
//   if(tipo.equals("D")){
//       try {
//           sessions.get("C"+key).getBasicRemote().sendBinary(mensaje);
//       } catch (IOException ex) {
//           Logger.getLogger(WsHuellaFirma.class.getName()).log(Level.SEVERE, null, ex);
//       } 
//   }
//}
    @OnMessage
    public void mensaje(String mensaje, @PathParam("key") final String key, @PathParam("tipo") final String tipo) {
        System.out.println("mje " + mensaje + " tipo " + tipo + " key " + key);
        if (mensaje.equals("dispositivos?")) {
            try {
                Session sF = sessions.get("D" + key);
                Session sH = sessions.get("H" + key);
                String msj = "$(\"#btn_huella\").removeClass(\"btn-success\");\n"
                        + "       $(\"#btn_huella\").addClass(\"btn-danger\");"
                        + "$(\"#btn_firma\").removeClass(\"btn-success\");\n"
                        + "       $(\"#btn_firma\").addClass(\"btn-danger\");";
                if (sF != null) {
                    msj += "$(\"#btn_firma\").removeClass(\"btn-danger\");\n"
                        + "       $(\"#btn_firma\").addClass(\"btn-success\");";
                }
                if (sH != null) {
                    msj += "$(\"#btn_huella\").removeClass(\"btn-danger\");\n"
                        + "       $(\"#btn_huella\").addClass(\"btn-success\");";
                }
                sessions.get("C" + key).getBasicRemote().sendText(msj);
            } catch (IOException ex) {
                Logger.getLogger(WsHuellaFirma.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            if (tipo.equals("C")) {
                EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaP1PU");
                emf.getCache().evictAll();

                AnotacionesJpaController anotdao = new AnotacionesJpaController(emf);
                HuellafirmaJpaController hfdao = new HuellafirmaJpaController(emf);
              
                Anotaciones anot = anotdao.findAnotaciones(Integer.parseInt(mensaje.trim()));
                Huellafirma hf = new  Huellafirma();
                for (Huellafirma h : anot.getHuellafirmaList()) {
                    hf = h;
                }

                    if (hf.getHufiId() == null && anot.getAnotId()!=null) {
              
                    hf.setAnotId(anot);
                    hfdao.create(hf);

                }
                try {
                    Session sF = sessions.get("D" + key);
                    Session sH = sessions.get("H" + key);
                    if (sF != null) {
                        sF.getBasicRemote().sendText(hf.getHufiId().toString());
                    }
                    if (sH != null) {
                        sH.getBasicRemote().sendText(hf.getHufiId().toString());
                    }
                } catch (IOException ex) {
                    Logger.getLogger(WsHuellaFirma.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (tipo.equals("D") || tipo.equals("H")) {
                try {
                    sessions.get("C" + key).getBasicRemote().sendText(mensaje);
                } catch (IOException ex) {
                    Logger.getLogger(WsHuellaFirma.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }

    @OnClose
    public void onClose(Session session, @PathParam("key") final String key, @PathParam("tipo") final String tipo) {
        System.out.println("cerre una conexion " + sessions.size() + " tipo " + tipo + " key " + key);
        // removemos la conexion
        sessions.remove(tipo + key);

    }

    @OnError
    public void onError(Session session, Throwable thr) {
        System.out.println("ha ocurrido un error :(");
        thr.printStackTrace();
        System.out.println(thr.getStackTrace());

    }
}
