<%-- 
    Document   : edit-movie-management
    Created on : Jun 17, 2023, 2:35:39 PM
    Author     : Nguyen Thanh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>EDIT</title>
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
        <div class="add_movie">
            <div class="add_movie--section">
                <div class="add_movie--section--tittle">
                    <h1>EDIT MOVIE</h1>
                </div>
                <c:set var="m" value="${requestScope.movie}"/>
                <form action="update-movie" class="add_movie--section--form" method="POST">
                    <input type="hidden" value="${m.movieID}" name="movie-id"/>
                    <div class="add_movie--section--form--item" style="width: 100%; display: flex; justify-content: center; align-items: center;">
                        <div style="min-width: 160px">
                            <p>Movie Tittle:</p>
                        </div>
                        <input type="text" placeholder="Movie Tittle" name="movie-tittle" value="${m.movieTitle}">
                    </div>

                    <div class="add_movie--section--form--item" style="width: 100%; display: flex; justify-content: center; align-items: center;">
                        <div style="min-width: 160px">
                            <p>Genre:</p>
                        </div>
                        <select name="movie-genre" id="">                       
                            <c:forEach var="g" items="${requestScope.genreList}">
                                <c:if test="${m.genre != g.genreName}"><option value="${g.genreID}">${g.genreName}</option></c:if>                                                      
                                <c:if test="${m.genre == g.genreName}"><option value="${g.genreID}" selected="">${g.genreName}</option></c:if>
                            </c:forEach>
                        </select> 
                    </div>

                    <div class="add_movie--section--form--item" style="width: 100%; display: flex; justify-content: center; align-items: center;">
                        <div style="min-width: 160px">
                            <p>Image:</p>
                        </div>
                        <input type="file" name="movie-image" value="${m.movieImage}" required="">
                    </div>

                    <div class="add_movie--section--form--item" style="width: 100%; display: flex; justify-content: center; align-items: center;">
                        <div style="min-width: 160px">
                            <p>Premiere:</p>
                        </div>
                        <input type="date" placeholder="Movie Premiere" name="movie-premiere" value="${m.moviePremiere}">
                    </div>

                    <div class="add_movie--section--form--item" style="width: 100%; display: flex; justify-content: center; align-items: center;">
                        <div style="min-width: 160px">
                            <p>Movie Director:</p>
                        </div>
                        <input type="text" placeholder="Movie Director" name="movie-director" value="${m.movieDirector}">
                    </div>

                    <div class="add_movie--section--form--item" style="width: 100%; display: flex; justify-content: center; align-items: center;">
                        <div style="min-width: 160px">
                            <p>Movie Time:</p>
                        </div>
                        <input type="number" placeholder="Movie Time" name="movie-time" value="${m.movieTime}">
                    </div>

                    <div class="add_movie--section--form--item" style="width: 100%; display: flex; justify-content: center; align-items: center;">
                        <div style="min-width: 160px">
                            <p>Movie Slide:</p>
                        </div>
                        <input type="file" name="movie-slide" value="${m.movieImageSlide}">
                    </div>

                    <div class="add_movie--section--form--item" style="width: 100%; display: flex; justify-content: center; align-items: center;">
                        <div style="min-width: 160px">
                            <p>Movie Description:</p>
                        </div>
                        <input type="text" placeholder="Movie Description" name="movie-des" value="${m.movieDescription}">
                    </div>

                    <div class="add_movie--section--form--item" style="width: 100%; display: flex; justify-content: center; align-items: center;">
                        <div style="min-width: 160px">
                            <p>Movie Trailer Link:</p>
                        </div>
                        <input type="text" placeholder="Movie Trailer Link" name="movie-trailer" value="${m.movieTrailerLink}">
                    </div>

                    <div class="add_movie--section--form--item" style="width: 100%; display: flex; justify-content: center; align-items: center;">
                        <div style="min-width: 160px">
                            <p>Movie Status:</p>
                        </div>
                        <select name="movie-status" id="">
                            <c:choose>
                                <c:when test="${m.movieStatus == 0}">
                                    <option value="0" selected>Inactive</option>
                                    <option value="1">Now Showing</option>
                                    <option value="2">Coming Soon</option>
                                </c:when>
                                <c:when test="${m.movieStatus == 1}">
                                    <option value="0">Inactive</option>
                                    <option value="1" selected>Now Showing</option>
                                    <option value="2">Coming Soon</option>
                                </c:when>
                                <c:when test="${m.movieStatus == 2}">
                                    <option value="0">Inactive</option>
                                    <option value="1">Now Showing</option>
                                    <option value="2" selected>Coming Soon</option>
                                </c:when>
                            </c:choose>
                        </select>
                    </div>
                    <input class="add_movie--section--submit-btn" style="width: 62%" type="submit">
                </form>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
        crossorigin="anonymous"></script>

        <script src="https://code.jquery.com/jquery-3.7.0.js"
        integrity="sha256-JlqSTELeR4TLqP0OG9dxM7yDPqX1ox/HfgiSLBj8+kM=" crossorigin="anonymous"></script>
    </body>
</html>
