<%-- 
    Document   : header
    Created on : Jun 11, 2023, 4:25:09 PM
    Author     : Nguyen Thanh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
       
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"/>
        <link rel="stylesheet" href="./assets/css/styles.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
        crossorigin="anonymous"></script>
        <link rel="stylesheet" href="./assets/font/all.css">
        <script src="https://code.jquery.com/jquery-3.7.0.js"
        integrity="sha256-JlqSTELeR4TLqP0OG9dxM7yDPqX1ox/HfgiSLBj8+kM=" crossorigin="anonymous"></script>
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link
            href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,300;0,400;0,500;0,700;0,800;0,900;1,300;1,500;1,700&display=swap"
            rel="stylesheet">
    </head>
    <body>
        <div id="header">
            <div id="toggle">
                <i class="fa fa-bars" aria-hidden="true" style="color: #fff"></i>
                <div class="toogle_logo">
                    <img src="./assets/img/Logo/logo.png" alt="">
                </div>
            </div>
            <nav>
                <ul id="main-menu">
                    <a href="home-page"><img src="./assets/img/Logo/logo.png" alt="logo"></a>
                    <li>
                        <a href="movie-list">MOVIES</a>
                    </li>
                    <li>
                        <a href="my-ticket">MY TICKETS</a>
                    </li>
                    <li>
                        <a href="cultureplex.jsp">CULTUREPLEX</a>
                    </li>
                </ul>

                <div class="header_login">
                    <c:if test="${sessionScope.account == null}">
                        
                        <a href="register.jsp">Register</a>
                        /
                        <a href="login.jsp">Login</a>
                    </c:if>
                        <c:if test="${sessionScope.account != null}">
                            Hi <a href="account-user"> ${sessionScope.account.userName}</a>
                            /
                            <a href="./log-out">Logout</a>
                            
                        </c:if>    
                   
                </div>
            </nav>
        </div>
        
        <script>
        $(document).ready(function () {
            $('#toggle').click(function () {
                $('nav').slideToggle();
            })
        })
        </script>
    </body>
</html>
