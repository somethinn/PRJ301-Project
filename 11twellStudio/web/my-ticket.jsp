<%-- 
    Document   : my-ticket
    Created on : Jun 25, 2023, 9:46:11 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MY TICKETS</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" type="image/png" href="assets/img/favicon_io/favicon-32x32.png">

        <title>MOVIES</title>
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
        <div class="movie_list">
        <div class="movie_list--center">
            <div class="movie_list--tittle">
                <h2>My Tickets</h2>
            </div>
        </div>
            <div class="movie_list--content--sort--select" style="border: none; margin-left: 50px">
                <form action="my-ticket">
                        <p style="margin-right: 12px;">SHOW</p>
                        <select name="sortBy" id="sortBy" onchange="updateOption(this)" style="border-radius: 10px; border: 2px solid #333">
                            <option value="DESC">Latest</option>
                            <option value="ASC">Oldest</option>
                        </select>
                </form>
                    </div>
        <div style="padding: 0 55px" class="my-ticket row">

            <c:forEach items="${requestScope.listOrder}" var="o">
                <c:if test="${o.orderStatus == 1}">
                    
                    <div class="my-ticket-image col-md-2 col-sm-6">
                        <img style="width: 100%; height: 100%; border: 7px solid #333;" src="./assets/img/film/${o.show.movie.movieImage}" alt=""/>
                    </div>
                    <div class="my-ticket_details col-md-10">
                        <h4 style="font-weight: 700;">Booking number: ${o.orderID}</h4>
                        <c:if test="${o.orderStatus == 1}">
                            <h5 style="color: green; font-weight: 700;">(Status: paid)</h5>
                        </c:if>
                            <h5><strong>MOVIE: </strong>${o.show.movie.movieTitle}</h5>
                        <h5><strong>DATE: </strong>
                            <fmt:formatDate value="${o.show.showDate}" pattern="dd/MM/yyyy" var="date"></fmt:formatDate>
                            ${date}
                        </h5>
                            <h5><strong>TIME: </strong> ${o.show.movie.movieTime} minutes</h5>
                            <h5><strong>SLOT: </strong> ${o.show.slot.slotTime}</h5>
                            <h5><strong>ROOM: </strong> ${o.show.room.roomName}</h5>
                            <h5><strong>SEAT: </strong> <c:forEach items="${o.seatIDList}" var="s">
                                <label>${s.seatName}</label>
                            </c:forEach>
                            <h5><strong>PRICE: </strong> 
                                <fmt:formatNumber value="${o.orderTotalPrice}" pattern="#,###" var="price">
                                </fmt:formatNumber>
                                ${price}đ

                            </h5>
                    </div>
                                <hr style="margin: 25px 0; border: 1px solid black"/>
                </c:if>
            </c:forEach>
        </div>
        </div>
        <%@include file="footer.jsp" %>
        
        <script>
            function updateOption(selectElement) {       
                        localStorage.setItem('selectedOption', selectElement.value);       
                        selectElement.form.submit();
                    }
                    
                    
        
        
                    window.addEventListener('DOMContentLoaded', function () {
                        var selectedOption = localStorage.getItem('selectedOption');
                        if (selectedOption) {
                            var selectElement = document.getElementById('sortBy');
                            selectElement.value = selectedOption;
                        }
                    });
        </script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
        crossorigin="anonymous"></script>

        <script src="https://code.jquery.com/jquery-3.7.0.js"
        integrity="sha256-JlqSTELeR4TLqP0OG9dxM7yDPqX1ox/HfgiSLBj8+kM=" crossorigin="anonymous"></script>
    </body>
</html>
