<!DOCTYPE html>
<html>
    <head>
        <title>Start Page</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <h1>Welcome to simple Rest Library.</h1>
        
            <h2>Public webpages:</h2>
            <ul>
                <li><a href="${pageContext.request.contextPath}/pippo.html">pippo.html</a> show pippo.html static page</li>
                <li><a href="${pageContext.request.contextPath}/help.html">help.html</a> show help.html static page</li>
            </ul>
            
            <h1>Private pages</h1>
            <ul>
                <li><a href="${pageContext.request.contextPath}/prv/arduino.html">prv/arduino.html</a> Arduino simple control panel</li>
            </ul>
            
            
            <p>For other service (REST and Dashboard), you need to <a href="${pageContext.request.contextPath}/prv/dashboard.html">log in</a></p>
        
    </body>
</html>
