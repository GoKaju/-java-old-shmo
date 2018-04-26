/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    ValidarFormID();
    Menu();

});
//$( '.modal .fade .modal-nuevo' ).on( 'show.bs.modal' , function () { 
//    $( '#Form-Data' ).bootstrapValidator( 'resetForm' , true ); 
//});

//loader 
$(document).ajaxStart(function () {
    $('.modalLoader').show();
});
$(document).ajaxStop(function () {
    $('.modalLoader').hide();
});

function convertDate(inputFormat) {
  function pad(s) { return (s < 10) ? '0' + s : s; }
  var d = new Date(inputFormat);
  return [pad(d.getDate()+1), pad(d.getMonth()+1), d.getFullYear()].join('-');
}





function validarExtencion(campo, ext) {
    var value = $(campo).val();
    var texto = value.split('.');
    if (texto[texto.length - 1].toUpperCase() !== ext.toUpperCase()) {

        alertify.error("El archivo debe ser de tipo: " + ext);
        $(campo).val('');

    }
}
function verLocation() {

    var texto = window.location.pathname.split('/');

    return texto[texto.length - 1];

}

function eliminarRegistro(ctr, datos) {
    alertify.confirm('Esta seguro de realizar esta acción', function (e) {
        if (e) {
            $.ajax({
                url: ctr,
                type: "POST",
                data: datos,
                dataType: "xml",
                success: function (data) {
                    xml_response(data);
                },
                error: function () {
                    alert("Error en el procesamiento");
                }
            });
        }
    });
}
function peticionAjax(ctr, datos) {
 if (validarRequiered(datos)) {
        $.ajax({
            url: ctr,
            type: "POST",
            data: datos,
            dataType: "xml",
            success: function (data) {
//                alert(datos)
                xml_response(data);
            },
            error: function () {

                alert("Error en el procesamiento");
            }
        });
    } else {
        alertify.error("Verifique los campos");
    }
}

function peticionAjaxSinValidar(ctr, datos) {

        $.ajax({
            url: ctr,
            type: "POST",
            data: datos,
            dataType: "xml",
            success: function (data) {
//                alert(datos)
                xml_response(data);
            },
            error: function () {

                alert("Error en el procesamiento");
            }
        });

}

function llenarFormJson(json) {
    var o = jQuery.parseJSON(json);
    var x = "";
    $.each(o, function (key, obj) {
        $('#' + obj.campo).val(obj.value);
    });
}

function PropiedadesFormJson(json) {
    var o = jQuery.parseJSON(json);
    var x = "";
    $.each(o, function (key, obj) {
        $('#' + obj.campo).attr(obj.Atrib, obj.valueAtrib);
    });
}


function validarRequiered(cadena) {

    var tamano = cadena.length;

    var b = true;
    var campo = true;
    var nombre = "";
    for (var i = 0; i < tamano; i++) {
        //guardar el nombre de el campo para agregarle css en caso de que este vacio  


        if (cadena.charAt(i) === "=") {
            if (cadena.charAt(i + 1) === "&") {
                b = false;
                $("textarea[name=" + nombre + "]").css('border', '2px solid red');
                $("input[name=" + nombre + "]").css('border', '2px solid red');
                $("select[name=" + nombre + "]").css('border', '2px solid red');


            } else if (cadena.charAt(i + 1) === "/^\s+$/") {
                if (cadena.charAt(i + 2) === "/^\s+$/" || cadena.charAt(i + 2) === "&") {
                    b = false;
                    $("textarea[name=" + nombre + "]").css('border', '2px solid red');
                    $("input[name=" + nombre + "]").css('border', '2px solid red');
                    $("select[name=" + nombre + "]").css('border', '2px solid red');

                }

            } else if (i === tamano - 1) {
                b = false;
                $("textarea[name=" + nombre + "]").css('border', '2px solid red');
                $("input[name=" + nombre + "]").css('border', '2px solid red');
                $("select[name=" + nombre + "]").css('border', '2px solid red');

            } else {
                $("textarea[name=" + nombre + "]").css('border', '1px solid gray');
                $("input[name=" + nombre + "]").css('border', '1px solid gray');
                $("select[name=" + nombre + "]").css('border', '1px solid gray');
            }

        } else if (cadena.charAt(i) === "&") {




            nombre = "";

        } else {
            nombre += cadena.charAt(i);

        }
    }

    return b;

}
function redireccionar(url, s, msj, tipo) {
    if (tipo === '1') {
        alertify.success(msj);

    } else if (tipo === '0') {
        alertify.error(msj);
    }

    setTimeout(function () {
        document.location.href = url;
    }
    , s * 1000);



}

function ValidarFormID() {

    $("#Form-Data").bootstrapValidator({
        message: "This value is not valid",
        feedbackIcons: {
            valid: "glyphicon glyphicon-ok",
            invalid: "glyphicon glyphicon-remove",
            validating: "glyphicon glyphicon-refresh"
        },
        excluded: [':disabled'],
        fields: {}
    }).on("success.form.bv", function (e) {
        e.preventDefault();
        var $form = $(e.target);
        var bv = $form.data("bootstrapValidator");
//    alert($form.attr("action"))
//    alert($form.serialize())
        $.ajax({
            url: $form.attr("action"),
            type: "POST",
            data: $form.serialize(),
            dataType: "xml",
            success: function (datos) {
                var xml;

                xml = datos;
                xml_response(xml);
            },
            error: function () {
                alert("Error en el procesamiento");
            }
        });
    });
}

function xml_response(xml) {

    //alert(xml);
    var respuesta = "";
    var mensaje = "";
    var operacion = "";
    var errores = "";
    var html = "";
    $(xml).find("response").each(function () {
        respuesta += $(this).find("respuesta").text();
        mensaje += $(this).find("mensaje").text();
        operacion += $(this).find("operacion").text();
        errores += $(this).find("errores").text();
        html += $(this).find("html").text();
    });
    //alert("respuesta ::::: " + respuesta);
    //alert("operacion ::::: " + operacion);
    if (respuesta === "1") {
        if (operacion === "ejecutarhtml") {
            eval(html);


        }
    }
    if (respuesta === "0") {
        if (operacion === "finalizar") {
            eval(html);
            VentanaModal();
        }
        if (operacion === "error") {
            VentanaModal(html + " " + errores);
        }
    }
}
function mensajeOK(texto, accion, url) {

    alertify.success(texto);


    if (accion !== "") {
        if (accion === "reloadTabla") {
            $(".close").click();
            setTimeout(function () {
                RecargaPanel(url, 'panelprincipal');

            }, '1000');




//             $('#modal').modal('hide');
//             $('.modal').modal('toggle');
            $('.modal').removeClass('show');
            $('.modal-backdrop').removeClass('modal-backdrop');


        } else
        if (accion === "reloadTabla2") {
            RecargaPanel(url, 'panelprincipal');

        }
        else {

            eval(accion);
        }

    }
}

function VentanaModal(texto) {

    alertify.error(texto);
//    Alertify.error(texto);


//    $().toastmessage({
//        inEffectDuration: 600, // in effect duration in miliseconds
//        stayTime: 3000, // time in miliseconds before the item has to disappear
//        text: '', // content of the item
//        sticky: false, // should the toast item sticky or not?
//        type: 'notice', // notice, warning, error, success
//        position: 'top-center', // top-left, top-center, top-right, middle-left, middle-center, middle-right
//        // Position of the toast container holding different toast.
//        // Position can be set only once at the very first call,
//        // changing the position after the first call does nothing
//        closeText: '',
//        close: function () {
//            //console.log("toast is closed ...");
//        }
//    });
//    $().toastmessage('showErrorToast', texto);
}

function RecargaPanel(Url, Panel) {
    $("#" + Panel).load(Url);

}

function RecargaForm(Url, Panel, conten) {
    $("#" + Panel).load(Url + " " + "#" + conten, function () {
        ValidarFormID();
    });
}
function RecargaSelect(Url, Panel, select) {
    $("#" + Panel).load(Url + " " + "#" + select);



}
function cadenaAleatoria(length)
{
    var text = "";
    var possible = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    for (var i = 0; i < length; i++)
        text += possible.charAt(Math.floor(Math.random() * possible.length));

    return text;
}

function calculoimc(altura, peso, r)
{
    altura = altura.toString().replace(',', '.');
    var alturaMetro = altura / 100;
    altura = parseInt(altura);
    peso = parseInt(peso);

    if (altura === "") {
        alertify.error("Por favor, introduce tu altura.");
    }
    else if (altura < 0) {
        alertify.error("La altura que introduzca debe ser positiva.");
    }
    else if (altura < 20) {
        alertify.error("Ha introducido la altura en metros. Por favor, multipliquela por 100 para introducirla en centimetros.");
    }
    else {

        if (peso === "") {
            alertify.error("Por favor, introduce tu peso.");
        }
        else if (peso < 0) {
            alertify.error("El peso que introduzca debe ser positivo.");
        }
        else {

            /*CALCULO IMC*/
            var alturaCuadrado = alturaMetro * alturaMetro;
            var imc = peso / alturaCuadrado;
            document.getElementById(r).value = Math.round(imc * 100) / 100;

        }
    }
}