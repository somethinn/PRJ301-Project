<%-- 
    Document   : login-manager
    Created on : Jun 22, 2023, 3:41:27 PM
    Author     : somethinn
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>MANAGER LOGIN</title>
        <link rel="icon" type="image/png" href="assets/img/favicon_io/favicon-32x32.png">

        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;700&family=Roboto+Mono:ital,wght@1,200&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="./assets/css/manager-style.css">
    </head>
    <body>
        <div class="manager-login" id="wrapped">
            <div class="header">
                <a href="#"><img src="./assets/img/Logo/logo.png" alt="Logo"></a>
            </div>
            <div class="body">
                <h1 style="font-weight: 700"><strong>MANAGER LOGIN</strong></h1>
                <form action="./login-manager" class="form-login" method="POST">

                    <label for="account" style="margin-bottom: -28px">Manager account</label>
                    <input id="account" type="email" name="account" placeholder="Account" required="">
                    <label for="password" style="margin-bottom: -28px;"> Password</label>
                    <input id="password" type="password" name="password" placeholder="Password" required="">
                    <c:if test="${requestScope.errorMsg != null}"><div style="color: red">${requestScope.errorMsg}</div> </c:if>
                    <input type="submit" value="LOGIN" class="login-btn" style="border: none">
                </form>
            </div>
        </div>
    </body>
</html>