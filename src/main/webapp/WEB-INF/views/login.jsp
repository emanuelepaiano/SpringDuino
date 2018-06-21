<%-- 
    Document   : login
    Created on : 14-mar-2018, 11.04.45
    Author     : lele
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

        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
          <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->
        <title>Login Page</title>
    </head>
    <body onload='document.f.username.focus();'>
        
        <!--login modal-->
        <div id="loginModal" class="modal show" tabindex="-1" role="dialog" aria-hidden="true">
          <div class="modal-dialog">
          <div class="modal-content">
              <div class="modal-header">
                  <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                  <!-- ${pageContext.request.contextPath} -->
              </div>
              
              
              <div class="modal-body">  
                  <div class="form-group text-center">
                        <img width="200" src="${pageContext.request.contextPath}/resources/img/spring.png"> 
                    </div>
                  <form class="form col-md-12 center-block" action="${pageContext.request.contextPath}/login" method="post">
                    
                    <div class="form-group">
                      <input name='username' type="text" class="form-control input-lg" placeholder="Username">
                    </div>
                    <div class="form-group">
                      <input name='password' type="password" class="form-control input-lg" placeholder="Password">
                    </div>
                    <div class="form-group">
                      <button class="btn btn-primary btn-lg btn-block">Log in</button>
                      <!-- <span class="pull-right"><a href="#">Register</a></span><span><a href="#">Need help?</a></span> -->
                    </div>
                     <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                  </form>
              </div>
              <div class="modal-footer">
                  <div class="col-md-12">
                  <!-- <button class="btn" data-dismiss="modal" aria-hidden="true">Cancel</button> -->
                  </div>	
              </div>
          </div>
          </div>
        </div>
        
    </body>
</html>