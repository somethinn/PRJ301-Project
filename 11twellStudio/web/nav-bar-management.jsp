<%-- 
    Document   : nav-bar-management
    Created on : Jul 4, 2023, 5:15:07 PM
    Author     : Nguyen Thanh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
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
        
        <style>
            body {
                overflow-y: hidden;
                overflow-x: hidden;
            }
        </style>
    </head>
    <body>
        <div class="management_nav col-md-2" style="padding-bottom: 20px; max-width: 218px; bottom: 0;  top: 0;">
            <div class="management_nav--logo">
                <img src="./assets/img/Logo/logo.png" alt="">
            </div>
            <c:if test="${sessionScope.admin.userName != null}">
                <div style="color: white; font-size: 14px">Hi manager ${sessionScope.admin.userName}</div>

            </c:if>
            <br style="color: white" />
            <div class="management_nav--link" style="margin-bottom: 20px" >
                <a href="dashboard-management">DASHBOARD</a>
                <a href="movie-management">MOVIE MANAGEMENT</a>
                <a href="order-management">ORDER MANAGEMENT</a>
                <a href="show-management">SHOW MANAGEMENT</a>
                <a href="slider-management">SLIDER MANAGEMENT</a>
                <a href="post-management">POST MANAGEMENT</a>
                <a href="promo-management">PROMO-CODE MANAGEMENT</a>
            </div>

            <a href="./logout-manager" class="management_nav--out" style="padding: 10px 10px">OUT</a>
        </div>
        
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
        crossorigin="anonymous"></script>

        <script src="https://code.jquery.com/jquery-3.7.0.js"
        integrity="sha256-JlqSTELeR4TLqP0OG9dxM7yDPqX1ox/HfgiSLBj8+kM=" crossorigin="anonymous"></script>
    </body>
</html>
