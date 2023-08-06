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
                    <h1>EDIT SHOW</h1>
                </div>
                <c:set var="s" value="${requestScope.show}"/>
                <form action="update-show" class="add_movie--section--form" method="POST">
                    <input type="hidden" value="${s.showID}" name="show-id"/>
                    
                        <input type="hidden" value="${s.movie.movieID}" name="show-movie"/>
                    <div class="add_movie--section--form--item" style="width: 100%; display: flex; justify-content: center; align-items: center;">
                        <div style="min-width: 160px">
                            <p>Show Tittle:</p>
                        </div>
                        <input type="text" placeholder="Movie Tittle" readonly="" name="movie-tittle" value="${s.movie.movieTitle}">
                    </div>
                    <div class="add_movie--section--form--item" style="width: 100%; display: flex; justify-content: center; align-items: center;">
                        <div style="min-width: 160px">
                            <p>Date:</p>
                        </div>
                        <input type="date" placeholder="Show Date" name="show-date" value="${s.showDate}">
                        <!--<input type="text" placeholder="Slot" name="show-slot" value="${s.slot.slotTime}">-->
                    </div>
                    <div class="add_movie--section--form--item" style="width: 100%; display: flex; justify-content: center; align-items: center;">
                        <div style="min-width: 160px">
                            <p>Slot:</p>
                        </div>
                        <select name="show-slot" id="">                       
                            <c:forEach var="sl" items="${requestScope.listSlot}">
                                <c:if test="${s.slot.slotTime != sl.slotTime}"><option value="${sl.slotID}">${sl.slotTime}</option></c:if>                                                      
                                <c:if test="${s.slot.slotTime == sl.slotTime}"><option value="${sl.slotID}" selected="">${sl.slotTime}</option></c:if>
                            </c:forEach>
                        </select> 
                    </div>
                    <div class="add_movie--section--form--item" style="width: 100%; display: flex; justify-content: center; align-items: center;">
                        <div style="min-width: 160px">
                            <p>Price:</p>
                        </div>
                    <!--<input type="text" placeholder="Room" name="show-room" value="${s.room.roomName}">-->
                        <select name="show-room" id="">                       
                            <c:forEach var="r" items="${requestScope.listRoom}">
                                <c:if test="${s.room.roomName != r.roomName}"><option value="${r.roomID}">${r.roomName}</option></c:if>                                                      
                                <c:if test="${s.room.roomName == r.roomName}"><option value="${r.roomID}" selected="">${r.roomName}</option></c:if>
                            </c:forEach>
                        </select> 
                    </div>
                    <div class="add_movie--section--form--item" style="width: 100%; display: flex; justify-content: center; align-items: center;">
                        <div style="min-width: 160px">
                            <p>Room:</p>
                        </div>
                        <input type="number" placeholder="Show Price" name="show-price" required="" value="${s.showPrice}">
                    </div>
                    <div class="add_movie--section--form--item" style="width: 100%; display: flex; justify-content: center; align-items: center;">
                        <div style="min-width: 160px">
                            <p>Show Status:</p>
                        </div>
                        <select name="show-status" id="">
                            <c:choose>
                                <c:when test="${s.showStatus == 0}">
                                    <option value="0" selected>Inactive</option>
                                    <option value="1">Active</option>

                                </c:when>

                                <c:when test="${s.showStatus == 1}">
                                    <option value="0">Inactive</option>
                                    <option value="1">Active</option>
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
