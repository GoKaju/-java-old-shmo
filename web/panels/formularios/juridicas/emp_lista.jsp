<%-- 
    Document   : emp_lista
    Created on : 9/02/2016, 12:26:03 AM
    Author     : D4V3
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
       <input id="inputFileToLoad" type="file" onchange="encodeImageFileAsURL();" />
<div id="imgTest"></div>
<script type='text/javascript'>
  function encodeImageFileAsURL() {

    var filesSelected = document.getElementById("inputFileToLoad").files;
    if (filesSelected.length > 0) {
      var fileToLoad = filesSelected[0];

      var fileReader = new FileReader();

      fileReader.onload = function(fileLoadedEvent) {
        var srcData = fileLoadedEvent.target.result; // <--- data: base64

        var newImage = document.createElement('img');
        newImage.src = srcData;

        document.getElementById("imgTest").innerHTML = newImage.outerHTML;
        alert("Converted Base64 version is " + document.getElementById("imgTest").innerHTML);
        console.log("Converted Base64 version is " + document.getElementById("imgTest").innerHTML);
      }
      fileReader.readAsDataURL(fileToLoad);
    }
  }
</script>
    </body>
</html>
