<%-- 
    Document   : peticion_firma
    Created on : 22/07/2016, 02:29:43 AM
    Author     : D4V3
--%>

<%@page import="valeria.response.Mediador"%>
<%@page import="valeria.metodos.Cadenas"%>
<%@page import="formularios.entidades.Anotaciones"%>
<%@page import="formularios.entidades.Huellafirma"%>
<%@page import="formularios.controlers.HuellafirmaJpaController"%>
<%@page import="formularios.controlers.AnotacionesJpaController"%>
<%@page import="javax.persistence.Persistence"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
  Mediador e = (Mediador) session.getAttribute("Mediador");
  
    if (e != null) {
   EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaP1PU");
             
                Cadenas o = new Cadenas();
                AnotacionesJpaController anotdao = new AnotacionesJpaController(emf);
                HuellafirmaJpaController hfdao = new HuellafirmaJpaController(emf);
              
                Anotaciones anot = anotdao.findAnotaciones(((Anotaciones) session.getAttribute("Anotacion")).getAnotId());
                        
                Huellafirma hf = new  Huellafirma();
                for (Huellafirma h : anot.getHuellafirmaList()) {
                    hf = h;
                }

%>
        
            <div class="modal-header">
                         <button type="button" class="close" data-dismiss="modal">&times;</button>
                <div class="row">
                    <div class="col-md-6">
                        <div id="btn_firma" class="btn btn-danger" onclick="verificarDispositivos()" ><img width="25" src="../images/tablet_firma_icon.svg" alt="Firma icono" title="Verificar dispositivo de firma "/></div>
                        <div id="btn_huella" class="btn btn-danger" onclick="verificarDispositivos()" ><img width="25" src="../images/huella_icon.svg" alt="Huella icono" title="Verificar dispositivo de huella "/></div>
                    </div>
                    <div id="signature-pad" class="m-signature-pad right col-md-4">

                        <button type="button" id="btn_guardar" class="button save btn btn-danger " onclick="pedirFirma(<%=anot.getAnotId()%>)" >Pedir Datos</button>
                        <button onclick="
                                $('#cajaImg').load('../panels/HF/peticion_firma.jsp #content_img');
                                " type="button" id="btn_recargar" class="btn btn-circle btn-info"  ><i class="glyphicon glyphicon-refresh"></i></button>
<!--                        <button onclick="
                             toggleCargaHF();
                                " type="button" id="btn_recargar" class="btn btn-circle btn-primary"  ><i class="glyphicon glyphicon-upload"></i></button>-->

                    </div>
                </div></div>
            <div id="cajaImg" class="modal-body">
                <div id="content_img" class="row">
                    <center>
                        
                    <img class="" width="500"  id="firma_img" src="<%=o.notEmpty(hf.getAnotFirma())%>" alt="Aqui va la firma." />
                        
                        
                        <img class="" width="200" id="huella_img" src="<%=o.notEmpty(hf.getAnotHuella())%>" alt="Aqui va la huella." />
                        
                    </center> 


            </div>
                        <fieldset hidden="" id="cargaHF">
                            
                        <legend>Cargar imágenes</legend>
                        <div class="col-md-12">
                          
                            <div class="col-md-12 form-group">
                                <label for="" class="control-label">Cargar Firma</label>
                                <input type="file" class="" id="firma_input" accept="image/*"  />
                            </div>
                            <div class="col-md-12 form-group">
                                <label for="" class="control-label">Cargar Huella</label>
                                <input type="file" class="" id="huella_input" accept="image/*"  />
                            </div>
                            
                           
                        </div>
                    
                        </fieldset>
                </div>
            <div class="modal-footer">
                
                <span onclick="CambiarCodigo()"  title="Cambiar codigo" class="left" style="cursor: 
                      pointer">  Codigo: <span id="codigoDisp"></span></span>
                
                
                 <!--<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>-->
                
            </div> <!--<script src="../../js/jquery-1.10.2.min.js"></script>-->
        <!--<script src="https://cdnjs.cloudflare.com/ajax/libs/bootbox.js/4.4.0/bootbox.min.js"></script>-->
        <script src="../panels/HF/js/app_1.js"> </script>
        <script>
 
            var opencargaHF=false;
            function toggleCargaHF(){
                if(opencargaHF){
                    $("#cargaHF").hide();
                    opencargaHF=false;
                }else{
                    $("#cargaHF").show();
                    opencargaHF=true;
                }
                
            }
            
 function encodeImageFileAsURL(id,tipo) {

    var filesSelected = document.getElementById("firma_input").files;
    if (filesSelected.length > 0) {
      var fileToLoad = filesSelected[0];

      var fileReader = new FileReader();

      fileReader.onload = function(fileLoadedEvent) {
        var srcData = fileLoadedEvent.target.result; // <--- data: base64

//$("#firma_txt").val(srcData);
//        var newImage = document.createElement('img');
//        newImage.src = srcData;
//        newImage.width = '400';
//
//        document.getElementById("img_firma").innerHTML = newImage.outerHTML;
        peticionAjax('/Formularios','action=guardarHf&anot=<%=anot.getAnotId()%>&tipo='+tipo+'data='+srcData+'');
      };
      fileReader.readAsDataURL(fileToLoad);
    }
  }
            
            
        </script>
 
<%}%>