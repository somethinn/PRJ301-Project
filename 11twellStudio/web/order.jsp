<%-- 
    Document   : order
    Created on : Jun 12, 2023, 12:18:15 AM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>PAYMENT</title>
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
        <style>
            h4 {
                margin-bottom: 15px;
            }
            .payment {
                display: flex;
                flex-direction: column;
                padding: 0 80px;
                margin-top: 20px;
            }

            .payment-tittle {
                background-color: black;
                color: white;
                padding: 10px 10px;
                text-align: center;
            }

            .payment-details_container {
                display: flex;
            }

            .payment-details {
                display: flex;
                flex-direction: column;
            }

            .payment-details_information {
                background-color: #929292;
                color: white;
                padding: 10px 5px;
                text-align: center;
            }

            .payment-details_final_payment {
                background-color: #929292;
                color: white;
                text-align: center;
                margin-top: 10px;
            }

            .payment-details_contents {
                background-color: #fef8a0;
                padding: 20px 5px 5px 10px;
                margin-top: 10px;
            }

            .payment-details_momo {
                background-color: #fef8a0;
                padding: 20px 5px;
                margin-top: 10px;
            }

            .terms-of-use {
                margin-top: 20px;
            }

            .total {
                display: flex;
                flex-direction: column;

            }

            .total-tittle {
                text-align: center;
                background-color: #fef8a0;
                padding: 10px 5px;
            }

            .total-price {
                background-color: #929292;
                text-align: center;
                padding: 10px 5px;
            }

            .total-purchase-btn {
                width: 100%;
                padding: 10px 5px;
                background-color: green;
                font-weight: 700;
                font-size: 25px;
                border: none;
                color: white;
            }

            .total-purchase-btn:hover {
                opacity: 0.9;
            }

            .total-cancel-btn {
                width: 100%;
                padding: 10px 5px;
                background-color: red;
                font-weight: 700;
                font-size: 25px;
                border: none;
                color: white;
            }

            .total-cancel-btn:hover {
                opacity: 0.8;
            }

            .holding-time_tittle {
                text-align: center;
                background-color: #fef8a0;
                padding: 10px 5px;
                margin-top: 20px;
            }

            .holding-time_time {
                background-color: #929292;
                text-align: center;
                padding: 10px 5px;
                display: flex;
                justify-content: center;
            }

            .holding-time_time_white {
                background-color: white;
                display: flex;
                flex-direction: column;
                justify-content: center;
                width: 40%;
                padding: 20px 20px;
                border-radius: 3px;
            }

            .holding-time_time_red {
                background-color: red;
                color: white;
                width: 100%;
                padding: 20px 20px;
                border-radius: 3px;
            }

            .add-promo_btn {
                background-color: lightsalmon;;
                border: none;
                color: white;
                font-weight: 700;
                border-radius: 2px;
            }

            .add-promo_btn:hover {
                opacity: 0.8;
            }
        </style>
    </head>
    <body onload="countdown()">
        <%@include file="header.jsp" %>
        <div class="payment">
            <div class="payment-tittle">
                <h2 style="margin-bottom: 0">PAYMENT</h2>
            </div>

            <div class="payment-details_container row" style="margin-top: 30px;">

                <div class="payment-details col-md-8">
                    <c:set value="${requestScope.order}" var="order"></c:set>
                        <div class="payment-details_information"><h3 style="margin-bottom: 0;">TICKET INFORMATION</h3></div>
                        <div class="payment-details_contents">
                            <h4><strong>MOVIE:</strong> ${order.show.movie.movieTitle}</h4>
                        <h4><strong>DATE:</strong> ${order.show.showDate}</h4>
                        <h4><strong>TIME:</strong> ${order.show.movie.movieTime} minutes</h4>
                        <h4><strong>SLOT:</strong> ${order.show.slot.slotTime}</h4>
                        <h4><strong>ROOM:</strong> ${order.show.room.roomName}</h4>
                        <h4 style="display: inline-block"><strong>SEAT:</strong></h4>
                        <c:forEach items="${order.seatIDList}" var="s">
                            <h4 style="display: inline-block">${s.seatName}</h4>
                        </c:forEach>
                    </div>
                    <div class="payment-details_final_payment"><h3 style="margin-bottom: 0">FINAL PAYMENT</h3></div>
                    <div class="payment-details_momo"><input type="radio" checked=""/><strong>PAYMENT METHOD</strong><br/><br/>
                        <input type="radio"/><strong>PAYMENT METHOD</strong><br/><br/>
                        <input type="radio" /><strong>PAYMENT METHOD</strong>
                    </div>

                </div>
                <div class="total col-md-3">
                    <div class="total-purchase">
           

                        <form style="margin-bottom: 20px; display: flex; gap: 15px; ">
                            <input placeholder="Promo Code.." type="text" name="promoCode" id="promoCodeInput" value="${param.promoCode}" style="min-height: 53px; padding-left: 3%; min-width: 230px">
                            <input class="add-promo_btn" type="button" value="ADD" onclick="checkPromoCode()" style="min-width: 65px">
                        </form>    

                        <c:if test="${promoMsg != null}">
                            <div style="color: orange">${promoMsg}</div>
                        </c:if>
                    </div>
                    <div class="total-tittle">
                        <h4 style="margin-bottom: 0; font-weight: bold;">TOTAL</h4>
                    </div>

                    <div class="total-price">
                        <fmt:formatNumber value="${order.orderTotalPrice}" pattern="#,###" var="price" >
                        </fmt:formatNumber>
                        <h4 style="margin-bottom: 0; font-weight: bold;" id="totalPrice" >${price}đ</h4>
                    </div>



                    <div class="total-purchase">
                        <form action="redirect-after-pay">
                            <input type="hidden" name="orderID" value="${order.orderID}">
                            <input type="hidden" name="action" value="pay-success">
                            <input type="hidden" id="totalPriceInput" name="totalPriceAfterPromo" value="${order.orderTotalPrice}" />

                            <input class="total-purchase-btn" type="submit" value="PURCHASE">
                        </form>
                    </div>



                    <div class="total-cancel">
                        <form action="redirect-after-pay">
                            <input type="hidden" name="orderID" value="${order.orderID}">
                            <input type="hidden" name="action" value="pay-cancel">
                            <input class="total-cancel-btn" type="submit" value="CANCEL">
                        </form>
                    </div>

                    <div class="holding-time" style="margin-bottom: 50px">
                        <div class="holding-time_tittle"><h4 style="margin-bottom: 0; font-weight: bold;">COUNTDOWN CLOCK</h4></div>
                        <div class="holding-time_time">
                            <div class="holding-time_time_white">
                                <div class="holding-time_time_red">
                                    <h3 style="margin-bottom: 0"><span id="countdown">10:00</span></h3>
                                </div>

                                <p style="margin-bottom: 0; font-weight: bold; color: #ccc">Seconds</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>

        <%@include file="footer.jsp" %>
        <script>
//        function countdown() {
//            var minutes = 10; // Số phút đếm ngược
//            var seconds = minutes * 60; // Tổng số giây
//
//            var countdownElement = document.getElementById("countdown");
//
//            var countdownInterval = setInterval(function() {
//                var minutesLeft = Math.floor(seconds / 60);
//                var secondsLeft = seconds % 60;
//
//               
//                var formattedTime = (minutesLeft < 10 ? "0" + minutesLeft : minutesLeft) + ":" + (secondsLeft < 10 ? "0" + secondsLeft : secondsLeft);
//
//                countdownElement.innerHTML = formattedTime;
//
//               
//                seconds--;
//
//                
//                if (seconds < 0) {
//                    clearInterval(countdownInterval);
//                    window.location.href = "movie-list";
//                }
//            }, 1000);
//        }

            function redirectAfterCountdown() {
                // Chuyển hướng đến trang khác sau khi đếm ngược kết thúc
                window.location.href = "movie-list";
            }
            function countdown() {
                var seconds = 60; // Số giây đếm ngược

                // Hiển thị số giây đếm ngược ban đầu
                document.getElementById("countdown").textContent = seconds;

                // Giảm số giây và cập nhật hiển thị sau mỗi giây
                var countdownInterval = setInterval(function () {
                    seconds--;
                    document.getElementById("countdown").textContent = seconds;

                    if (seconds <= 0) {
                        // Đếm ngược đã kết thúc, xóa interval và chuyển hướng đến trang khác
                        clearInterval(countdownInterval);
                        redirectAfterCountdown();
                    }



                }, 1000);
            }

        </script>


        <script>
            var pList = [];
            <c:forEach items="${requestScope.pList}" var="promo">
            var promo = {
                promoID: ${promo.promoID},
                promoCode: "${promo.promoCode}",
                promoDiscount: ${promo.promoDiscount},
                promoContent: "${promo.promoContent}",
                promoStartDate: new Date(${promo.promoStartDate.time}),
                promoEndDate: new Date(${promo.promoEndDate.time}),
                promoStatus: ${promo.promoStatus}
            };
            pList.push(promo);
            </c:forEach>



            function checkPromoCode() {
                var promoCode = document.getElementById("promoCodeInput").value;
                var currentDate = new Date();

                for (var i = 0; i < pList.length; i++) {
                    var promo = pList[i];
                    var totalPrice_raw = ${order.orderTotalPrice};
                    var totalPrice = parseFloat(totalPrice_raw);
                    if (
                            promo.promoCode === promoCode &&
                            currentDate <= promo.promoEndDate &&
                            currentDate >= promo.promoStartDate &&
                            promo.promoStatus === 1

                            ) {

                        alert("Added successfully, discount: " + promo.promoContent);
                        totalPrice = totalPrice - (totalPrice * promo.promoDiscount);
                        console.log(totalPrice);
                        document.getElementById("totalPriceInput").value = totalPrice;
                        console.log(document.getElementById("totalPriceInput").value);

                        document.getElementById("totalPrice").innerHTML = totalPrice + "đ";



                        return;
                    }
                }


                alert("Promo code is invalid");
            }




        </script>







    </script>
</body>

</html>
