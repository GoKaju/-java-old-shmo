var wrapper = document.getElementById("signature-pad"),
    clearButton = wrapper.querySelector("[data-action=clear]"),
    saveButton = wrapper.querySelector("[data-action=save]"),
    canvas = wrapper.querySelector("canvas"),
    signaturePad;
//
$("#btn_guardar").attr("disabled","disabled");


//configurar cookies
var key;
var user;

//generar llave
function makeid()
{
    var text = "";
    var possible = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    for( var i=0; i < 8; i++ )
        text += possible.charAt(Math.floor(Math.random() * possible.length));

    return text;
}
//obtener cookie
function getCookie(cname) {
    var name = cname + "=";
    var ca = document.cookie.split(';');
    for(var i = 0; i <ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0)===' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) === 0) {
            return c.substring(name.length,c.length);
        }
    }
    return "";
} 
//colocar cookie
function setCookie(cname, cvalue, exdays) {
    var d = new Date();
    d.setTime(d.getTime() + (exdays*24*60*60*1000));
    var expires = "expires="+ d.toUTCString();
    document.cookie = cname + "=" + cvalue + "; " + expires;
} 
//verificar cookies
function checkCookie() {
  

     key=getCookie("key");


    if (key!==null && key !=="") {
        alert("llave= " + key);
    } else {
        
//        var x  = makeid();
       var x = prompt("Por favor ingrese la nueva llave y presione aceptar", "");
       
        if (x !== null &&x !== "" ) {
            setCookie("key", x, 365);
            key= x;
//             alert("Nueva llave creada= " + key);
        }
    }
      user=getCookie("user");
    if (user!==null && user!=="") {
        
    } else {
//        user = prompt("Por favor ingrese el codigo de usuario", "");
//        if (user !== "" && user !== null) {
            setCookie("user", "user", 365);
//        }
    }
    
      if (key!==null && key !=="" && user!==null && user!=="") {
          conectar();
    } else {
checkCookie();
    
    }
    
}
checkCookie();
// conectar con ws

  var ws;
   var id=0;

function conectar() {
    
  if(ws !== null && ws !== undefined && ws !== null && ws.readyState !== ws.CLOSED){
                    console.log("WebSocket is already opened.") ;
                    return;
                }
            
 var uriWS="ws://"+location.host+"/JavaP1/WsHF/D/"+key;
// var uriWS="ws://echo.websocket.org:80/";
ws=new WebSocket(uriWS);
 ws.binaryType = 'arraybuffer';
 console.log (ws);
 
 ws.onopen=function(evento) {
 console.log("abierto");
 
       $("#btn_guardar").removeClass("btn-danger");
       $("#btn_guardar").addClass("btn-success");

 
// ws.send("hola");
 } 
 ws.onmessage=function(evento) {
     if(evento.data.trim()/evento.data.trim()===1){
         id=evento.data.trim();
     $("#btn_guardar").removeAttr("disabled");
            
     }
 }
 ws.onerror=function(evento) {
      $("#btn_guardar").removeClass("btn-success");
       $("#btn_guardar").addClass("btn-danger");
 alert(evento.data);
 }
 ws.onclose=function(evento) {
      $("#btn_guardar").removeClass("btn-success");
       $("#btn_guardar").addClass("btn-danger");
       $("#btn_guardar").attr("disabled","disabled");

 alert("Conexion cerrada");
 }
 
}










// Adjust canvas coordinate space taking into account pixel ratio,
// to make it look crisp on mobile devices.
// This also causes canvas to be cleared.
function resizeCanvas() {
    // When zoomed out to less than 100%, for some very strange reason,
    // some browsers report devicePixelRatio as less than 1
    // and only part of the canvas is cleared then.
    var ratio =  Math.max(window.devicePixelRatio || 1, 1);
    canvas.width = canvas.offsetWidth * ratio;
    canvas.height = canvas.offsetHeight * ratio;
    canvas.getContext("2d").scale(ratio, ratio);
}

window.onresize = resizeCanvas;
resizeCanvas();

signaturePad = new SignaturePad(canvas);

clearButton.addEventListener("click", function (event) {
    signaturePad.clear();
});

saveButton.addEventListener("click", function (event) {
    if (signaturePad.isEmpty()) {
        alert("Por favor firme primero.");
    } else {
//alert(signaturePad.toDataURL())
$.post('../../Firma','id='+id+'&firma='+signaturePad.toDataURL(),function (data){
    if(data.trim()==="1"){
$("#btn_guardar").attr("disabled","disabled");
        ws.send("$('#cajaImg').load('../panels/HF/peticion_firma.jsp #content_img');");
        
    }else{alert(data)}
    
});
        signaturePad.clear();
    }
});

  function sendMessage(dataType, dataValue){
                var json = JSON.stringify({
                    "type":dataType,
                    "data":dataValue
                });
                ws.send(json);
            }