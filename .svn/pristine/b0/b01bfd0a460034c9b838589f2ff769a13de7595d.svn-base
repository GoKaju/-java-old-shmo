
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
//        alert("llave= " + key);
    } else {
            x = prompt("Por favor ingrese la llave de la tableta", "");
       
        
        if (x !== null &&x !== "" ) {
            setCookie("key", x, 365);
            key= x;
             alert("Nueva llave= " + key);
        }
    }
     
    
      if (key!==null && key !=="" ) {
          $("#codigoDisp").html(key);
          conectar();
    } else {
checkCookie();
    
    }
    
}
checkCookie();
// conectar con ws

  var ws;
function conectar() {
    
  if(ws !== null && ws !== undefined && ws !== null && ws.readyState !== ws.CLOSED){
                    console.log("WebSocket is already opened.") ;
                    ws.close();
                }
 var uriWS="ws://"+location.host+"/JavaP1/WsHF/C/"+key;
ws=new WebSocket(uriWS);
  ws.binaryType = 'arraybuffer';
 console.log (ws);

 
 ws.onopen=function(evento) {
 console.log("abierto");
 ws.send("dispositivos?");
 
       $("#btn_guardar").removeClass("btn-danger");
       $("#btn_guardar").addClass("btn-success");
       $("#btn_guardar").removeAttr("disabled");

 
// ws.send("hola");
 } 
 
 ws.onmessage=function(evento) {
// alert(evento.data);
        eval(evento.data);
 }
 ws.onerror=function(evento) {
 console.log(evento.data);
 
 }
 ws.onclose=function(evento) {
     $("#btn_guardar").attr("disabled","disabled");
       $("#btn_guardar").removeClass("btn-success");
       $("#btn_guardar").addClass("btn-danger");

// alert("Se cerro la conexion");
 }
 
}
function  pedirFirma (id){
    ws.send(id); 
    
}
function verificarDispositivos(){
     ws.send("dispositivos?");
}
function CambiarCodigo(){
        setCookie("key", "", 365);
        checkCookie()
}



