<%-- 
    Document   : booking
    Created on : Jun 14, 2023, 1:54:11 PM
    Author     : ASUS
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
        <title>DATE SHOW</title>
        <link rel="stylesheet" href="./assets/font/all.css">      
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link
            href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,300;0,400;0,500;0,700;0,800;0,900;1,300;1,500;1,700&display=swap"
            rel="stylesheet">

        <style>
            * {
                font-family: 'Montserrat', sans-serif;
            }
            
           

            .date-show {
                background-color: black;
                color: #ccc;
                max-width: 120px;
                width: 120px;
                height: 60px;
                border-radius: 10px;
                font-weight: bold;
                cursor: pointer;
                text-transform: uppercase;
                margin: 5%;
                border: 3px solid #c0c0c0;
              

            }
             .date-show:hover {
               background-color: red;
               color:#ebebeb;

            }

            .date_show-header {
                text-align: center;
                text-shadow: 3px 3px 7px #c0c0c0;
                color: #c0c0c0; 
                font-size: 60px;
                font-weight: bold;
               padding:  5% 0 4% 0;
            }
            
            .date_show-detail {
                width: 70%;
                margin: auto auto;
            }
            
            .time_show {
                text-decoration: none;
                text-align: center;
                color: orangered;
               font-size: 20px;
               display: inline-block; 
               margin: 2% 3%;
               padding: 1%;
               font-weight: bold;
               border: 3px solid #333;
               border-radius: 10px;
            }
            
            .time_show-detail {
                width: 70%;
                margin: auto auto;
                
               
               
            }
            .time_show:hover {
                background-color: #ccc;
                 
            }
            
           
            </style>
        </head>
        <body>


            <div style="background-color: #444444; min-height: 850px">
                    <div class="date_show-header">
                        11-12 CINEMA
                        <hr width="60%" style="margin: auto auto; color: #c0c0c0; height: 10px"/>
                    </div>
                <div style="text-align: center; color: orange; font-weight: bold; font-size: 25px;">MOVIE SHOW</div>   
                <div style="text-align: center; color: #ebebeb; font-weight: bold; font-size: 30px; padding-bottom:  3%">${requestScope.movie.movieTitle}</div>
                    <div class="date_show-detail">
                        <c:forEach items="${requestScope.listDate}" var="dateShow">
                        <fmt:formatDate value="${dateShow}" pattern="yyyy-MM-dd" var="date"/>
                        <fmt:formatDate value="${dateShow}" pattern="EE | dd  MM" var="dateShow"/>
                        <form action="./booking" method="POST" style="display: inline-block" style="margin: 5%">

                            <input type="hidden" value="${movieID}" name ="movieID">
                            <input type="hidden" value="${date}" name ="date">
                            <input type="submit" value="${dateShow}" class="date-show">
                        </form>
                         </c:forEach>
                    </div>
               
                    <div class="time_show-detail">  
                <c:forEach items="${requestScope.showMovieList}" var="show">
                  
                        <a href="./pick-seat?movieID=${show.movie.movieID}&showID=${show.showID}" class="time_show">
                            ${show.getSlot().slotTime}
                        </a>
                  
                </c:forEach>
                    </div>
            </div>

              

               
                <!--EEEE MM dd-->
  <%@include file="footer.jsp" %>
            </body>
        </html>
