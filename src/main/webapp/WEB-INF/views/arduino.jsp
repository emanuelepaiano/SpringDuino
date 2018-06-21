<%-- 
    Document   : Arduino
    Created on : 26-mar-2018, 11.34.48
    Author     : Emanuele Paiano
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
         <!-- Favicon -->
        <link rel="shortcut icon" type="image/png" href="${pageContext.request.contextPath}/resources/img/favicon.png"/>

        <!-- Bootstrap -->
        <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">

        <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
        <link href="${pageContext.request.contextPath}/resources/css/ie10-viewport-bug-workaround.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="${pageContext.request.contextPath}/resources/css/signin.css" rel="stylesheet">
        
        <script src="${pageContext.request.contextPath}/resources/js/jquery.min.js" rel="stylesheet"></script>

        <title>Arduino Control</title>
        
         
        </head>
        <body>
        <center>
             <h1>Arduino LED</h1>
            <div class="buttons">
                <button id="ledOn">LED ON</button>
                <button id="ledOff">LED OFF</button>
            </div>                        
        </center>
        <script type="text/javascript">
                $( "#ledOn" ).click(function() {
                    
                    var baseurl=window.location.protocol + "//" + window.location.host + "/"+"SpringDuino/";
                    
                    //Send the AJAX call to the server
                  $.ajax({
                  //The URL to process the request
                    'url' : baseurl+'api/arduino/led/on',
                  //The type of request, also known as the "method" in HTML forms
                  //Can be 'GET' or 'POST'
                    'type' : 'GET',
                  //Any post-data/get-data parameters
                  //This is optional
                    'data' : {
                      
                    },
                  //The response from the server
                    'success' : function(data) {
                    //You can use any jQuery/JavaScript here!!!
                      if (data === "success") {
                        alert('request sent!');
                      }
                    }
                  });
                });
          
                
                $( "#ledOff" ).click(function() {
                    
                    var baseurl=window.location.protocol + "//" + window.location.host + "/"+"SpringDuino/";
                    
                    //Send the AJAX call to the server
                  $.ajax({
                  //The URL to process the request
                    'url' : baseurl+'api/arduino/led/off',
                  //The type of request, also known as the "method" in HTML forms
                  //Can be 'GET' or 'POST'
                    'type' : 'GET',
                  //Any post-data/get-data parameters
                  //This is optional
                    'data' : {
                      
                    },
                  //The response from the server
                    'success' : function(data) {
                    //You can use any jQuery/JavaScript here!!!
                      if (data === "success") {
                        alert('request sent!');
                      }
                    }
                  });
                });     
               
            </script>
        </body>
</html>
