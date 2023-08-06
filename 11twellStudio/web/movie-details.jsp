<%-- 
    Document   : movie-details-new
    Created on : Jun 13, 2023, 8:19:15 PM
    Author     : Nguyen Thanh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <!--favicon link-->
         <link rel="icon" type="image/png" href="assets/img/favicon_io/favicon-32x32.png">

        <title>${requestScope.movie.movieTitle}</title>
        <link rel="stylesheet" href="./assets/css/styles.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
        crossorigin="anonymous"></script>
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
        
        <%@include file="header.jsp" %>
        <div class="movie_details">
            <div class="movie_list--center" style="margin-top: 20px">
            <div class="movie_list--tittle">
                <h2>Movie Details</h2>
            </div>
        </div>
          
        <div class="movie_details--section row">
            <div class="movie_details--section--img col-md-2">
                <img src="./assets/img/film/${requestScope.movie.movieImage}" alt="${requestScope.movie.movieTitle}">
            </div>

            <div class="movie_details--section--content col-md-10">
                <div class="movie_details--section--content--tittle">
                    <h1>${requestScope.movie.getMovieTitle()}</h1>
                </div>

                <div class="movie_details--section--content--detail">
                    <p><strong>Director: </strong>${requestScope.movie.getMovieDirector()}</p>
                    <p><strong>Genre: </strong>${requestScope.movie.getGenre()}</p>
                    <p>
                        <strong>Release date: </strong>
                        
                        <fmt:formatDate value="${requestScope.movie.getMoviePremiere()}" pattern="dd/MM/yyyy" />
                    </p>
                    <p><strong>Running time: </strong>${requestScope.movie.getMovieTime()} minutes</p>
                </div>

                    <a href="./booking?movieID=${requestScope.movie.getMovieID()}" class="movie_details--section--content--booking-btn" >BOOKING</a>
                    <p style="margin: 5% 0 0 0 ; text-align: justify;">${requestScope.movie.movieDescription}</p>

            </div>
        </div>
        <div class="movie_details--trailer">
            <iframe src="${requestScope.movie.getMovieTrailerLink()}" frameborder="0" style="width:840px; height: 474px; border: none"></iframe> 
        </div>
    </div>
        <div style="margin: 3%;"></div>
        <!--width:560px; 316px;-->
        <%@include file="footer.jsp" %>
        
    </body>
</html>
