<%-- 
    Document   : dashboard
    Created on : 14-mar-2018, 16.59.47
    Author     : lele
--%>
<%@page session="true"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dashboard</title>
    </head>
    <body>
        <h1>Logged as ${pageContext.request.userPrincipal.name}</h1>
        
        <sec:authorize access="hasRole('ROLE_ROOT')">
            <p>Warning: You are GOD, this is very dangerous!</p> 
       </sec:authorize>
        
        <br>
        <form action="${pageContext.request.contextPath}/logout" method="POST">
            <input type="submit" value="Logout" />
            <input type="hidden"                        
                   name="${_csrf.parameterName}"
                   value="${_csrf.token}"/>
        </form>
        
        <br>
         <h2>Books Rest commands:</h2>
            <ul>
                <li><a href="${pageContext.request.contextPath}/api/books/">api/books/</a> show all books JSON output</li>
                <li><a href="${pageContext.request.contextPath}/api/books/xml/">api/books/xml/</a> show all books in XML output</li>
                <li><a href="${pageContext.request.contextPath}/api/books/delete/">api/books/delete/ (delete)</a> delete book from library</li>
                <li><a href="${pageContext.request.contextPath}/api/books/create/">api/books/create/ (post)</a> add book to library</li>
                <li><a href="${pageContext.request.contextPath}/api/books/">api/books/ (put)</a> edit book present into library</li>
            </ul>
            
            <h2>Authors Rest commands:</h2>
            <ul>
                <li><a href="${pageContext.request.contextPath}/api/authors/">api/authors/</a> show all authors JSON output</li>
                <li><a href="${pageContext.request.contextPath}/api/authors/xml/">api/authors/xml/</a> show all authors in XML output</li>
                <li><a href="${pageContext.request.contextPath}/api/authors/create/">api/authors/create/ (post)</a> Add author to library</li>
                <li><a href="${pageContext.request.contextPath}/api/authors/delete/">api/authors/delete/ (delete)</a> delete author from library</li>
                <li><a href="${pageContext.request.contextPath}/api/authors/">api/authors/ (put)</a> edit author present into library</li>
            </ul>
            
            <h2>Extra commands:</h2>
            <ul>
                <li><a href="${pageContext.request.contextPath}/api/extra/echoagent/">api/extra/echoagent</a> show client agent</li>
                <li><a href="${pageContext.request.contextPath}/api/extra/echodate/">api/extra/echodate</a> show server date</li>
                <li><a href="${pageContext.request.contextPath}/api/extra/echobrowser/">api/extra/echobrowse</a> show client browser</li>
                <li><a href="${pageContext.request.contextPath}/api/extra/echooperatingsystem/">api/extra/echooperatingsystem</a> show client OS</li>
               
            </ul>
            
            <h2>Arduino commands:</h2>
            <ul>
                <li><a href="${pageContext.request.contextPath}/api/arduino/led/on">api/arduino/led/on</a> (GET) Arduino Led 13 ON</li>
                <li><a href="${pageContext.request.contextPath}/api/arduino/led/off/">api/arduino/led/off</a> (GET) Arduino Led 13 OFF</li>
           
            </ul>
        
            <h2>Arduino static pages:</h2>
            <ul>
                <li><a href="${pageContext.request.contextPath}/prv/arduino.html">prv/arduino.html</a> Arduino static page</li>
           
            </ul>
       
    </body>
</html>
