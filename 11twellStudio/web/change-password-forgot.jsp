<%-- 
    Document   : change-password-forgot
    Created on : Jun 27, 2023, 9:05:20 AM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CHANGE PASSWORD</title>
        <link rel="icon" type="image/png" href="assets/img/favicon_io/favicon-32x32.png">

        <link rel="stylesheet" href="./assets/css/styles.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
        <link rel="stylesheet" href="./assets/font/all.css">      
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link
            href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,300;0,400;0,500;0,700;0,800;0,900;1,300;1,500;1,700&display=swap"
            rel="stylesheet">
    </head>
    <body>
        <%@include file="header.jsp" %>
        <form action="change-password-forgot" method="POST" style="padding-bottom: 3%">
            <div class="movie_list--center" style="margin: 20px 0">
                <div class="movie_list--tittle">
                    <h2>Change password</h2>
                </div>
            </div>

            <input type="hidden" value="${requestScope.userMail}" name="userMail">

            <div class="form-change-pass-container" style="padding: 0 3.5%">
                <div  class="form__item">
                    <label>Enter new password</label><br/>
                    <input type="password" name="userNewPassword" value="${param.userNewPassword}" required=""/>
                </div>
                <br/>

                <div  class="form__item">
                    <label>Confirm new password</label><br/>
                    <c:if test="${requestScope.wrongPassConfirmMsg != null}">
                        <p style="color: red; display: inline">${requestScope.wrongPassConfirmMsg}</p>
                    </c:if>
                    <input type="password" name="confirmUserNewPassword" value="${param.confirmUserNewPassword}" required=""/> 
                </div>
                <br/>

                <input style="margin-bottom: 70px" class="login-button form-input" type="submit" value="RESET PASSWORD"/>

            </div>
        </form>

        <%@include file="footer.jsp" %>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
        crossorigin="anonymous"></script>

        <script src="https://code.jquery.com/jquery-3.7.0.js"
        integrity="sha256-JlqSTELeR4TLqP0OG9dxM7yDPqX1ox/HfgiSLBj8+kM=" crossorigin="anonymous"></script>
    </body>
</html>
