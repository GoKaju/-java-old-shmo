/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ocupacional.servlets.facturacion;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ocupacional.bdatos.JuridicasDAO;
import ocupacional.bdatos.PersonaGeneralDAO;
import ocupacional.bdatos.facturacion.ProveedorDAO;
import ocupacional.bdatos.facturacion.ProveedoresExamenesDAO;
import ocupacional.utils.CampoFormJSON;
import ocupacional.valueobjects.JuridicasVO;
import ocupacional.valueobjects.PersonaGeneralVO;
import ocupacional.valueobjects.facturacion.ProveedorVO;
import ocupacional.valueobjects.facturacion.ProveedoresExamenesVO;
import org.json.simple.JSONArray;
import valeria.metodos.ManejadorFechas;
import valeria.response.Mediador;
import valeria.response.ObjetoRespuestaVO;
import valeria.response.RespuestaXML;

/**
 *
 * @author D4V3
 */
@WebServlet(name = "Servlet_ctr_proveedores", urlPatterns = {"/Proveedores"})
public class Servlet_ctr_proveedores extends HttpServlet {
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        response.setContentType("text/xml;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        Mediador e = (Mediador) session.getAttribute("Mediador");
        ManejadorFechas fechas = new ManejadorFechas();
        if (e != null) {
            e.LimpiarErrores();
            e.o.setRequest(request);
            String action = e.o.getvariable("action");
            System.out.println(action);
            if (action.equals("validarProveedor")) {
                String td = e.o.getvariable("td");
                String nd = e.o.getvariable("nd");
                
                PersonaGeneralVO pege = new PersonaGeneralDAO(e).Verificar(td, nd);
                
                if (pege.getPege_id() != null) {
                    ProveedorVO clie = new ProveedorDAO(e).Cargar(pege.getPege_id());
                    if (clie.getProv_id()!= null) {
                        e.getObjetoRespuestaVO().setTipooperacion("error");
                        e.getObjetoRespuestaVO().setRespuesta("0");
                        e.getObjetoRespuestaVO().setHtml("Este Proveedor ya esta registrado..!!");
                        
                    } else {
                        JuridicasVO juri = new JuridicasDAO(e).Cargar(pege.getPege_id());
                        System.out.println(pege.getPege_id());

//                        Se crea array de campo-value
                        JSONArray json = new JSONArray();
                        json.add(new CampoFormJSON("juri_razonsocial", juri.getJuri_razonsocial()));
                        json.add(new CampoFormJSON("acec_id", juri.getAcec_id()));
                        json.add(new CampoFormJSON("ciudad", pege.getCiud_id()));
                        json.add(new CampoFormJSON("pege_direccion", pege.getPege_direcciondomicilio()));
                        json.add(new CampoFormJSON("pege_telefono", pege.getPege_numerotelefono()));
                        json.add(new CampoFormJSON("pege_celular", pege.getPege_numerocelular()));
                        json.add(new CampoFormJSON("pege_email", pege.getPege_mail()));
                        json.add(new CampoFormJSON("rp_nombre", juri.getJuri_representante()));

//                        Json propiedades campos
                        JSONArray jsonP = new JSONArray();
                        jsonP.add(new CampoFormJSON("juri_razonsocial", "readonly", "true"));
                        jsonP.add(new CampoFormJSON("tido_id", "onchange", "this.value=" + pege.getTido_id()));
                        jsonP.add(new CampoFormJSON("pege_documento", "readonly", "true"));

//                        convertir JSON
                        StringWriter writer = new StringWriter();
                        json.writeJSONString(writer);
                        StringWriter writer2 = new StringWriter();
                        jsonP.writeJSONString(writer2);
                        
                        e.getObjetoRespuestaVO().setTipooperacion("ejecutarhtml");
                        e.getObjetoRespuestaVO().setRespuesta("1");
                        e.getObjetoRespuestaVO().setHtml("llenarFormJson('" + writer + "');PropiedadesFormJson('" + writer2 + "');$('#content').show();");
                        
                    }
                    
                } else {
                    
                    e.getObjetoRespuestaVO().setTipooperacion("ejecutarhtml");
                    e.getObjetoRespuestaVO().setRespuesta("1");
                    e.getObjetoRespuestaVO().setHtml("$('#content').show();");
                    
                }
                
            } else if (action.equals("newProveedor")) {
                String td = e.o.getvariable("td");
                String nd = e.o.getvariable("nd");
                
                PersonaGeneralVO pege = new PersonaGeneralDAO(e).Verificar(td, nd);
//revisar no esta insertando
                if (pege.getPege_id() != null) {
                    pege.setCiud_id(e.o.getvariable("ciudad"));
                    pege.setPege_direcciondomicilio(e.o.getvariable("pege_direccion"));
                    pege.setPege_numerotelefono(e.o.getvariable("pege_telefono"));
                    pege.setPege_numerocelular(e.o.getvariable("pege_celular"));
                    pege.setPege_mail(e.o.getvariable("pege_email"));
                    pege.setPege_registradopor(e.getUsuarioVO().getIdpersona());
                    pege.setPege_fechacambio(fechas.getFechaHoraTimeStamp());
                    
                    JuridicasVO juri = new JuridicasDAO(e).Cargar(pege.getPege_id());
                    juri.setAcec_id(e.o.getvariable("acec_id"));
                    juri.setJuri_razonsocial(e.o.getvariable("juri_razonsocial"));
                    juri.setJuri_representante(e.o.getvariable("rp_nombre"));
                    juri.setJuri_registradopor(e.getUsuarioVO().getIdpersona());
                    juri.setJuri_fechacambio(fechas.getFechaHoraTimeStamp());
                    ProveedorVO clie = new ProveedorDAO(e).Cargar(pege.getPege_id());
                    clie.setProv_estado(e.o.getvariable("est"));
                    clie.setProv_registradopor(e.getUsuarioVO().getIdpersona());
                    clie.setProv_fechacambio(fechas.getFechaHoraTimeStamp());
                    
                    if (new PersonaGeneralDAO(e).Actualizar(pege)) {
                        if (new JuridicasDAO(e).Actualizar(juri)) {
                            if (clie.getProv_id()!= null) {
                                if (new ProveedorDAO(e).Actualizar(clie)) {
                                    
                                    boolean b = false;
                                    for (ProveedoresExamenesVO l : new ProveedoresExamenesDAO(e).Listar(clie.getProv_id())) {
                                        
                                        if (new ProveedoresExamenesDAO(e).Eliminar(l)) {
                                            b = true;
                                            
                                        } else {
                                            b = false;
                                            break;
                                            
                                        }
                                        
                                    }
                                    
                                    ArrayList<ProveedoresExamenesVO> listaProveedorExamenes = (ArrayList<ProveedoresExamenesVO>) session.getAttribute("listaProveedorExamenes");
                                    if(listaProveedorExamenes !=null &&!listaProveedorExamenes.isEmpty()){
                                    for (ProveedoresExamenesVO l : listaProveedorExamenes) {
                                        l.setProv_id(clie.getProv_id());
                                        l.setPrex_registradopor(e.getUsuarioVO().getIdpersona());
                                        l.setPrex_fechacambio(fechas.getFechaHoraTimeStamp());
                                        if (new ProveedoresExamenesDAO(e).Insertar(l)) {
                                            b = true;
                                            
                                        } else {
                                            b = false;
                                            break;
                                            
                                        }
                                        
                                    }}else{
                                    b=true;}
                                    
                                    if (b) {
                                        session.removeAttribute("listaProveedorExamenes");
                                        e.getObjetoRespuestaVO().setTipooperacion("ejecutarhtml");
                                        e.getObjetoRespuestaVO().setRespuesta("1");
                                        e.getObjetoRespuestaVO().setHtml("mensajeOK('Insertado Correctamente','reloadTabla','../panels/formularios/basicas/proveedores.jsp');");
                                        
                                    } else {
                                        session.removeAttribute("listaProveedorExamenes");
                                        
                                        e.getObjetoRespuestaVO().setTipooperacion("ejecutarhtml");
                                        e.getObjetoRespuestaVO().setRespuesta("1");
                                        e.getObjetoRespuestaVO().setHtml("alertify.error('Error al Actualizar Proveedor...!')");
                                    }
                                } else {
                                    e.getObjetoRespuestaVO().setTipooperacion("ejecutarhtml");
                                    e.getObjetoRespuestaVO().setRespuesta("1");
                                    e.getObjetoRespuestaVO().setHtml("alertify.error('Error al Actualizar Proveedor..!')");
                                    
                                }
                            } else {
                                clie.setPege_id(pege.getPege_id());
                                if (new ProveedorDAO(e).Insertar(clie)) {
                                    boolean b = false;
                                    ArrayList<ProveedoresExamenesVO> listaProveedorExamenes = (ArrayList<ProveedoresExamenesVO>) session.getAttribute("listaProveedorExamenes");
                                   
                                    if(!listaProveedorExamenes.isEmpty()){
                                    for (ProveedoresExamenesVO l : listaProveedorExamenes) {
                                        l.setProv_id(clie.getProv_id());
                                        if (new ProveedoresExamenesDAO(e).Insertar(l)) {
                                            b = true;
                                        } else {
                                            b = false;
                                            break;
                                        }
                                        
                                    }}else{
                                    b=true;
                                    }
                                    
                                    if (b) {
                                        session.removeAttribute("listaProveedorExamenes");
                                        e.getObjetoRespuestaVO().setTipooperacion("ejecutarhtml");
                                        e.getObjetoRespuestaVO().setRespuesta("1");
                                        e.getObjetoRespuestaVO().setHtml("mensajeOK('Insertado Correctamente','reloadTabla','../panels/formularios/basicas/proveedores.jsp');");
                                        
                                    } else {
                                        session.removeAttribute("listaProveedorExamenes");
                                        
                                        e.getObjetoRespuestaVO().setTipooperacion("ejecutarhtml");
                                        e.getObjetoRespuestaVO().setRespuesta("1");
                                        e.getObjetoRespuestaVO().setHtml("alertify.error('Error al Insertar Proveedor..!')");
                                    }
                                } else {
                                    e.getObjetoRespuestaVO().setTipooperacion("ejecutarhtml");
                                    e.getObjetoRespuestaVO().setRespuesta("1");
                                    e.getObjetoRespuestaVO().setHtml("alertify.error('Error al Insertar Proveedor..!')");
                                    
                                }
                            }
                        }
                    }
                } else {
                    pege.setTido_id(td);
                    pege.setPege_documento(nd);
                    pege.setCiud_id(e.o.getvariable("ciudad"));
                    pege.setPege_direcciondomicilio(e.o.getvariable("pege_direccion"));
                    pege.setPege_numerotelefono(e.o.getvariable("pege_telefono"));
                    pege.setPege_numerocelular(e.o.getvariable("pege_celular"));
                    pege.setPege_mail(e.o.getvariable("pege_email"));
                    pege.setPege_registradopor(e.getUsuarioVO().getIdpersona());
                    pege.setPege_fechacambio(fechas.getFechaHoraTimeStamp());
                    
                    JuridicasVO juri = new JuridicasVO();
                    juri.setAcec_id(e.o.getvariable("acec_id"));
                    juri.setJuri_razonsocial(e.o.getvariable("juri_razonsocial"));
                    juri.setJuri_representante(e.o.getvariable("rp_nombre"));
                    juri.setJuri_registradopor(e.getUsuarioVO().getIdpersona());
                    juri.setJuri_fechacambio(fechas.getFechaHoraTimeStamp());
                    ProveedorVO clie = new ProveedorVO();
                    clie.setProv_estado(e.o.getvariable("est"));
                    clie.setProv_registradopor(e.getUsuarioVO().getIdpersona());
                    clie.setProv_fechacambio(fechas.getFechaHoraTimeStamp());
                    
                    if (new PersonaGeneralDAO(e).Insertar(pege)) {
                        juri.setPege_id(pege.getPege_id());
                        if (new JuridicasDAO(e).Insertar(juri)) {
                            clie.setPege_id(pege.getPege_id());
                            
                            if (new ProveedorDAO(e).Insertar(clie)) {
                                boolean b = false;
                                ArrayList<ProveedoresExamenesVO> listaProveedorExamenes = (ArrayList<ProveedoresExamenesVO>) session.getAttribute("listaProveedorExamenes");
                                if(!listaProveedorExamenes.isEmpty()){
                                for (ProveedoresExamenesVO l : listaProveedorExamenes) {
                                    l.setProv_id(clie.getProv_id());
                                    if (new ProveedoresExamenesDAO(e).Insertar(l)) {
                                        b = true;
                                        
                                    } else {
                                        b = false;
                                        break;
                                        
                                    }
                                    
                                }}else{
                                b=true;}
                                
                                if (b) {
                                    session.removeAttribute("listaProveedorExamenes");
                                    e.getObjetoRespuestaVO().setTipooperacion("ejecutarhtml");
                                    e.getObjetoRespuestaVO().setRespuesta("1");
                                    e.getObjetoRespuestaVO().setHtml("mensajeOK('Insertado Correctamente','reloadTabla','../panels/formularios/basicas/proveedores.jsp');");
                                    
                                } else {
                                    session.removeAttribute("listaProveedorExamenes");
                                    
                                    e.getObjetoRespuestaVO().setTipooperacion("ejecutarhtml");
                                    e.getObjetoRespuestaVO().setRespuesta("1");
                                    e.getObjetoRespuestaVO().setHtml("alertify.error('Error al Insertar Proveedor..!')");
                                }
                                
                            } else {
                                e.getObjetoRespuestaVO().setTipooperacion("ejecutarhtml");
                                e.getObjetoRespuestaVO().setRespuesta("1");
                                e.getObjetoRespuestaVO().setHtml("alertify.error('Error al Insertar Proveedor..!')");
                                
                            }
                        }
                    }
                }
            } else if (action.equals("eliminarProveedor")) {
                String id = e.o.getvariable("id");
                ProveedorVO ent = new ProveedorVO();
                ent.setProv_id(id);
                System.out.println(">>"+id);
               
                for (ProveedoresExamenesVO c : new ProveedoresExamenesDAO(e).Listar(id)) {
                    
                    new ProveedoresExamenesDAO(e).Eliminar(c);
                    
                }
                
                if (new ProveedorDAO(e).Eliminar(ent)) {
                    e.getObjetoRespuestaVO().setTipooperacion("ejecutarhtml");
                    e.getObjetoRespuestaVO().setRespuesta("1");
                    e.getObjetoRespuestaVO().setHtml("mensajeOK('Eliminado Correctamente','reloadTabla2','../panels/formularios/basicas/proveedores.jsp');");
                    
                } else {
                    
                    e.getObjetoRespuestaVO().setTipooperacion("error");
                    e.getObjetoRespuestaVO().setRespuesta("0");
                    e.getObjetoRespuestaVO().setHtml("La operación no se realizo correctamente..!!");
                }
                
            } else if (action.equals("AgregarExamen")) {
                String id = e.o.getvariable("Servicio_sel");
                String valor = e.o.getvariable("precio_text");
                ArrayList<ProveedoresExamenesVO> listaProveedorExamenes = (ArrayList<ProveedoresExamenesVO>) session.getAttribute("listaProveedorExamenes");
                
                if (listaProveedorExamenes == null) {
                    listaProveedorExamenes = new ArrayList<ProveedoresExamenesVO>();
                }
                ProveedoresExamenesVO c = new ProveedoresExamenesVO();
                c.setExam_id(id);
                c.setPrex_observacion(valor);
                c.setPrex_registradopor(e.getUsuarioVO().getIdpersona());
                c.setPrex_fechacambio(fechas.getFechaHoraTimeStamp());
                listaProveedorExamenes.add(c);
           
                
                session.removeAttribute("listaProveedorExamenes");
                session.setAttribute("listaProveedorExamenes", listaProveedorExamenes);
                
                e.getObjetoRespuestaVO().setTipooperacion("ejecutarhtml");
                e.getObjetoRespuestaVO().setRespuesta("1");
                e.getObjetoRespuestaVO().setHtml("mensajeOK('Agregado Correctamente','','');RecargaSelect('../panels/formularios/basicas/proveedores.jsp','cajaFormServicios','tablaFormServicios')");
                
            } else if (action.equals("RemoverExamen")) {
                int id = Integer.parseInt(e.o.getvariable("hash"));
                ArrayList<ProveedoresExamenesVO> listaProveedorExamenes = (ArrayList<ProveedoresExamenesVO>) session.getAttribute("listaProveedorExamenes");
                for (ProveedoresExamenesVO l : listaProveedorExamenes) {
                    if (l.hashCode() == id) {
                        listaProveedorExamenes.remove(l);
                        break;
                    }
                }
                
                session.removeAttribute("listaProveedorExamenes");
                session.setAttribute("listaProveedorExamenes", listaProveedorExamenes);
                
                e.getObjetoRespuestaVO().setTipooperacion("ejecutarhtml");
                e.getObjetoRespuestaVO().setRespuesta("1");
                e.getObjetoRespuestaVO().setHtml("mensajeOK('Removido Correctamente','','');RecargaSelect('../panels/formularios/basicas/proveedores.jsp','cajaFormServicios','tablaFormServicios')");
                
            }else if (action.equals("CargarExamenes")) {
                String id =e.o.getvariable("id");
                ArrayList<ProveedoresExamenesVO> listaProveedorExamenes = new ProveedoresExamenesDAO(e).Listar(id);
               
                
                session.removeAttribute("listaProveedorExamenes");
                session.setAttribute("listaProveedorExamenes", listaProveedorExamenes);
                
                e.getObjetoRespuestaVO().setTipooperacion("ejecutarhtml");
                e.getObjetoRespuestaVO().setRespuesta("1");
                e.getObjetoRespuestaVO().setHtml("mensajeOK('Cargado Correctamente','','');RecargaSelect('../panels/formularios/basicas/proveedores.jsp','cajaFormServicios','tablaFormServicios')");
                
            }
            else {
                e.getObjetoRespuestaVO().setTipooperacion("ejecutarhtml");
                e.getObjetoRespuestaVO().setRespuesta("1");
                e.getObjetoRespuestaVO().setHtml("alert('no hay accion')");
                
            }
            
        } else {
            ObjetoRespuestaVO ObjetoRespuestaVO = new ObjetoRespuestaVO();
            e = new Mediador(ObjetoRespuestaVO);
            e.getObjetoRespuestaVO().setRespuesta("0");
            e.getObjetoRespuestaVO().setTipooperacion("error");
            e.getObjetoRespuestaVO().setHtml("Variable de Sesion no encontrada");
        }
        
        System.out.println(new RespuestaXML().GenerarXML(e.getObjetoRespuestaVO()));
        out.print(new RespuestaXML().GenerarXML(e.getObjetoRespuestaVO()));
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(Servlet_ctr_clientes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(Servlet_ctr_clientes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}