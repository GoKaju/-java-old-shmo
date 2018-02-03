  var wsChat;
   var id=0;

function conectarChat(u_id) {
    id= u_id;
    console.log('usuario '+id)
  if(wsChat !== null && wsChat !== undefined && wsChat !== null && wsChat.readyState !== wsChat.CLOSED){
                    console.log("WebSocket is already opened.") ;
                    return;
                }
 var uriWS="ws://"+location.host+"/JavaP1/Chat/"+id;
wsChat=new WebSocket(uriWS);
 wsChat.binaryType = 'arraybuffer';
 console.log (wsChat);
 wsChat.onopen=function(evento) {
 console.log("abierto");
//       $("#btn_guardar").removeClass("btn-danger");
//       $("#btn_guardar").addClass("btn-success");

 
// ws.send("hola");
 } 
 wsChat.onmessage=function(evento) {
     console.log("mje: "+evento.data)
     var json =$.parseJSON(evento.data);
    if(json.accion==="status"){
        updateUsers(json);
    }
 };
 wsChat.onerror=function(evento) {
      $("#btn_guardar").removeClass("btn-success");
       $("#btn_guardar").addClass("btn-danger");
 alert(evento.data);
 };
 wsChat.onclose=function(evento) {
//      $("#btn_guardar").removeClass("btn-success");
//       $("#btn_guardar").addClass("btn-danger");
//       $("#btn_guardar").attr("disabled","disabled");

 console.log("Conexion cerrada");
 };
 
}

function  updateUsers(json){
    var users = $('#chat-users');
    if(json.status==="ONLINE"){
        var u= "<div class='alert alert-info' onclick='newConversation("+json.id+")' id='u_"+json.id+"' ><strong>"+json.tipo+": </strong>"+json.nombre+"</div>";
        users.append(u);
    }
       if(json.status==="OFFLINE"){
        $('#u_'+json.id).remove();
    }
}

function newConversation(id_other){
    console.log("entre a new conversation id: "+id_other)
//    se debe validar si la conversation ya esta creada
        var json = JSON.stringify({
                    "id":id_other,
                    "de":id,
                    "accion":"newConversation",
                    "conver_id":cadenaAleatoria(10)
                });
      wsChat.send(json);
    
}


  function sendMessage(dataType, dataValue){
                var json = JSON.stringify({
                    "type":dataType,
                    "data":dataValue
                });
                wsChat.send(json);
            }