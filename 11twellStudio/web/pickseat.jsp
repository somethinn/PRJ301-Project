<%-- 
    Document   : pickseat
    Created on : Jun 11, 2023, 4:41:19 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!--favicon link-->
        <link rel="icon" type="image/png" href="assets/img/favicon_io/favicon-32x32.png">
        <title>SEATS PICKING</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"/>

        <style>
            * {
                font-family: 'Montserrat', sans-serif;
            }

            .room_screen {
                background-color: white;
                border: none;
                border-radius: 5px;
                color: white;
                font-size: 5px;
                width: 70%;

                box-shadow: 1px 10px 15px 5px #888888;


            }
            .screen_text {
                color: white;
                font-weight: bold;
            }

            .seat_item {

                width: 22px;
                height: 22px;        
                border: 8px solid #444444;
                background-color: gray;
                padding: 3px;
                color: black;
                text-align: center;
                border-radius: 50%;
                min-width: 40px;
                font-size: 12px;

            }


            .container_pick-seat {
                margin-top: 10%;
                margin-bottom: 10%;
                background-color: #444444;
                padding-top: 1%;

            }

            #booking-seat_detail {
                background-color: black;
                padding: 2% 2%;
            }


        </style>
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



        <c:set var="count" value="${0}"></c:set>
            <div class="container-md container_pick-seat">
                <div class="room_screen" style="margin: auto auto;">Screen</div>
                <h2 class="screen_text" style="text-align: center; ">SCREEN</h2>
                <form action="pick-seat" method="POST" style="overflow-x: auto; margin: 10% 0;" >

                    <table style="margin: auto auto; ">

                    <c:forEach items="${requestScope.seatList}" var="s">
                        <c:if test="${count % 14 == 0}">
                            <tr/>
                            <tr>
                                <c:if test="${ s.getSeatName()  == 'X'}">
                                    <td class="seat_item" style="background-color: #d5d5d5; color: white; ">${s.getSeatName()}<input name="seatArr" class="seatArr" value="${s.getSeatID()}" type="checkbox" style="display: none;"></td>
                                    </c:if> 

                                <c:if test="${s.getSeatName()  != 'X'}">
                                    <td  class="seat_item"  id="${s.getSeatID()}" onclick="createBookingSession(${s.getSeatID()}, 1)" style="cursor: pointer;">${s.getSeatName()}<input name="seatArr" class="seatArr" value="${s.getSeatID()}" type="checkbox" style="display: none;"></td>
                                    </c:if> 

                            </c:if>




                            <c:if test="${count % 14 != 0}">
                                <c:if test="${ s.getSeatName()  == 'X'}">
                                    <td class="seat_item" style="background-color: #d5d5d5; color: white; ">${s.getSeatName()}<input name="seatArr" class="seatArr" value="${s.getSeatID()}" type="checkbox" style="display: none;"></td>
                                    </c:if>    
                                    <c:if test="${s.getSeatName()  != 'X'}">
                                    <td class="seat_item"  id="${s.getSeatID()}" onclick="createBookingSession(${s.getSeatID()}, 1)"  style="cursor: pointer;">${s.getSeatName()}

                                        <input name="seatArr" class="seatArr" value="${s.getSeatID()}" type="checkbox" style="display: none;">
                                    </td>
                                </c:if>          
                            </c:if>

                            <c:set var="count" value="${(count + 1)}"></c:set>

                        </c:forEach>


                </table>


                <div id="booking-seat_detail" style="display: flex; margin-top: 10%; ">

                    <div style="width: 25%">
                        <div style="width: 22px; height: 22px; background-color: gray; margin: 0;"></div><label style="color: white;">Available</label>
                        <div style="width: 22px; height: 22px; background-color: crimson; margin: 0;"></div> <label style="color: white;">Selecting</label>
                        <div style="width: 22px; height: 22px; background-color: #d5d5d5; color: white; text-align: center; margin: 0;">X</div><label style="color: white;">Booked</label>
                    </div>

                    <div style="width: 50%">
                        <table>
                            <tr>
                                <td></td>
                                <td></td>
                            </tr>
                        </table>
                        <div style="color: white;"><strong>MOVIE:</strong><label style="margin: 0 10px;" >${requestScope.movie.movieTitle}</label></div>
                        <div style="color: white;"><strong>ROOM:</strong><label style="margin: 0 10px;" >${requestScope.show.room.roomName}</label></div>
                        <div style="color: white;"><strong>SLOT:</strong><label style="margin: 0 10px;" >${requestScope.show.slot.slotTime}</label></div>
                        <div style="color: white;"><strong>TIME:</strong><label style="margin: 0 10px;" >${requestScope.movie.movieTime} minutes</label></div>

                        <div style="color: white;"><strong>SEAT:</strong><label style="margin: 0 10px;" id="pick-seat_detail">    </label></div>
                        <div style="color: white; font-size: 25px"><strong>TOTAL PRICE:</strong><label style="margin: 0 10px;" id="total-price">   </label></div>

                    </div>

                    <input name="showID" value="${requestScope.show.showID}" type="hidden"/>
                    <div style="width: 25%;">
                        <input type="submit" value="NEXT"
                               style="background-color: red;
                               padding: 10% 20%;
                               color: white;
                               border: 2px solid white;
                               border-radius: 10px;
                               float: right;
                               font-size: 20px;
                               font-weight: bold;
                               ">

                        <c:if test="${requestScope.pickingSeatRequired != null}">
                            <p style="color: white">${requestScope.pickingSeatRequired}</p>
                        </c:if>

                        <c:if test="${requestScope.pickingAgainMsg != null}">
                            <p style="color: white">Sorry, someone just pick your seat ${requestScope.pickingAgainMsg} , please select new one</p>
                        </c:if>
                    </div>



                </div>
            </form> 


        </div>

        <script type="text/javascript">

            var price = 0; // Khởi tạo giá trị ban đầu là 0
            var count = 0;

            function createBookingSession(seatID) {
                var seatElement = document.getElementById(seatID);

                if (seatElement.style.backgroundColor !== 'crimson') {
                    // Đang chọn ô
                    if (count === 8) {
                        alert('Maximum 8 seats');
                        back();
                    }
                    seatElement.style.backgroundColor = 'crimson';
                    seatElement.style.color = 'white';
                    document.getElementsByClassName("seatArr")[seatID - 1].checked = true;
                    document.getElementById("pick-seat_detail").innerHTML += "<label style=\"margin: 0 3px; color: white;\" id=\"seat-child" + seatID + "\">" + " " + seatElement.innerText + "</label>";
                    count++;
                    // Tăng giá tiền lên
                    price += parseFloat(${requestScope.show.showPrice});
                } else {
                    // Bỏ chọn ô
                    seatElement.style.backgroundColor = 'gray';
                    seatElement.style.color = 'black';
                    document.getElementsByClassName("seatArr")[seatID - 1].checked = false;
                    document.getElementById("pick-seat_detail").removeChild(document.getElementById("seat-child" + seatID));
                    count--;
                    // Giảm giá tiền xuống
                    price -= parseFloat(${requestScope.show.showPrice});
                }

                // Hiển thị giá tiền
                // Hiển thị giá tiền với định dạng xxx.xxx
                document.getElementById("total-price").innerHTML = formatPrice(price);
            }

            function formatPrice(price) {
                // Định dạng số với dấu phân tách hàng ngàn
//                return price.toLocaleString('en-US');
                return price.toLocaleString('vi-VN', {style: 'currency', currency: 'VND'});
            }

        </script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
        crossorigin="anonymous"></script>

        <script src="https://code.jquery.com/jquery-3.7.0.js"
        integrity="sha256-JlqSTELeR4TLqP0OG9dxM7yDPqX1ox/HfgiSLBj8+kM=" crossorigin="anonymous"></script>
    </body>
</html>
