<%-- 
    Document   : help
    Created on : 15-mar-2018, 9.46.48
    Author     : lele
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Help</title>
    </head>
    <body>
         <h1>Help</h1>
        
        <h2>For use REST service you need log in with following user:</h2>
        <ul>
            <li>User: root, Password: password</li>
            <li>User: admin, Password: password</li>
             <li>User: guest, Password: password</li>
        </ul>
        <p>You can log <a href="${pageContext.request.contextPath}/login.html">here</a></p>
    </body>
</html>
