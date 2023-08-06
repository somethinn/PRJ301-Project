<%-- 
    Document   : cultureplex
    Created on : Jun 19, 2023, 7:23:44 PM
    Author     : Nguyen Thanh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>11-12 RULES</title>
        <link rel="icon" type="image/png" href="assets/img/favicon_io/favicon-32x32.png">
        <!--<link rel="stylesheet" href="./assets/font/all.css">-->
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
        <div class="cultureplex">
            <div class="cultureplex_tittle">
                <h2>THE REGULATIONS AT 11-TWELL CINEMAS</h2>
            </div>

            <div class="cultureplex_content--container">
                <div class="cultureplex_content">
                    <h4>RULE:</h4>
                    <p>CGV sincerely thanks for your cooperation.</p>
                    <p>- No filming or photography.</p>
                    <p>- Please turn off your mobile phone ringer.</p>
                    <p>- No smoking.</p>
                    <p>- No causing disturbances.</p>
                    <p>- No chewing gum.</p>
                    <p>- No bringing pets into the cinema.</p>
                    <p>- Please take care of your personal belongings.</p>
                    <p>- Only food and drinks purchased at CGV are allowed inside the cinema.</p>
                    <p>- After 10 PM, CGV is not allowed to serve customers under 13 years old.</p>
                    <p>- After 11 PM, CGV is not allowed to serve customers under 16 years old.</p>
                    <p>The Theater Management has the right to refuse entry to customers who violate these rules.</p>
                    <br/>
                    <p>We have security cameras in the theater.</p>
                </div>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
        crossorigin="anonymous"></script>

        <script src="https://code.jquery.com/jquery-3.7.0.js"
        integrity="sha256-JlqSTELeR4TLqP0OG9dxM7yDPqX1ox/HfgiSLBj8+kM=" crossorigin="anonymous"></script>
        
        <%@include file="footer.jsp" %>
    </body>
</html>
