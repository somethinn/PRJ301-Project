<%-- 
    Document   : movie-management
    Created on : Jun 14, 2023, 3:14:23 PM
    Author     : Nguyen Thanh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib  prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html" charset=UTF-8">
        <title>DASHBOARD</title>
        <!--favicon link-->
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
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

        <style>
            .total {
                display: flex;
                justify-content: center;
                margin-bottom: 50px;
            }

            .total-item {
                margin: 0 3%;
                text-align: center;
            }

            .total-item p {
                font-size: 18px;
                font-weight: 700;
                margin-bottom: 0;
            }
            
            .dashboard-char_table {
                text-align: center;
            }
            
            .dashboard-char_table table{
                width: 100%;
                border: 2px solid black;
                border-collapse: collapse;
                text-align: center;
            }
            
            .dashboard-char_table td {
                border: 2px solid black;
            }
            
            .dashboard-char_table th {
                border: 2px solid black;
            }
        </style>

    </head>
    <body>

        <div class="management row">
            <%@include file="nav-bar-management.jsp"%>

            <div class="management_section col-md-10">
                <div class="management_section--tittle">
                    <h1>DASHBOARD</h1>
                </div>

                <div class="total">
                    <div class="total-item">
                        <div>
                            <p style="font-size: 18px; color: orange;">${requestScope.totalMovie}</p>
                        </div>
                        <p>Total Movie</p>
                    </div>

                    <div class="total-item">
                        <div>
                            <p style="font-size: 18px; color: cadetblue;">
                            ${requestScope.totalOrderDetail}
                            </p>
                        </div>
                        <p>Total Order</p>
                    </div>

                    <div class="total-item">
                        <div>
                            <fmt:formatNumber value="${requestScope.totalRevenue}"  var="totalRevenue" pattern="###,###"></fmt:formatNumber>
                            <p style="font-size: 18px; color: green;">${totalRevenue} đ</p>
                        </div>
                        <p>Total Revenue /đ</p>
                    </div> 
                </div>

                        <div class="dashboard-char_container row" style="padding: 0 5%">
                    <div class="dashboard-char_real col-md-6">
                        <canvas id="myChart"></canvas>
                    </div>

                    <div class="dashboard-char_table col-md-6">
                        <h3>Top 3 selling product </h3>
                        <table>
                            <thead>
                                <tr>
                                    <th>MovieID</th>
                                    <th>Movie Name</th>
                                    <th>Number of Order</th>
                                </tr>
                            </thead>
                            <c:set value="0" var="count"></c:set>
                            <c:set value="${requestScope.listNumberOfOrderByMovieID}" var="numberOrder"></c:set>
                                <tbody>
                                <c:forEach items="${requestScope.listTop3OrderMovie}" var="m">
                                    <tr>
                                        <td>${m.movieID}</td>
                                        <td>${m.movieTitle}</td>
                                        <td>${numberOrder.get(count)}</td>
                                    </tr>
                                    <c:set value="${count + 1}" var="count"></c:set>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>

                </div>
            </div>


            <script>
                //use chart.js
                //preparing data for chart
                var today = new Date(); // nowdate
                var labels = [];
                var data = [];

                // create 7 day before today
                for (var i = 7; i > 0; i--) {
                    var date = new Date(today);
                    date.setDate(today.getDate() - i);
                    var formattedDate = date.toLocaleDateString('en-US', {month: 'short', day: 'numeric'});
                    labels.push(formattedDate);
                }

                // real data from databaase
                data = [${requestScope.listTotalPrice.get(0)}, ${requestScope.listTotalPrice.get(1)}, ${requestScope.listTotalPrice.get(2)}, ${requestScope.listTotalPrice.get(3)}, ${requestScope.listTotalPrice.get(4)}, ${requestScope.listTotalPrice.get(5)}, ${requestScope.listTotalPrice.get(6)}];

                var chartData = {
                    labels: labels,
                    datasets: [{
                            label: 'Revenue',
                            data: data,
                            borderColor: 'rgba(75, 192, 192, 1)',
                            fill: false
                        }]
                };

                // create chart
                var ctx = document.getElementById('myChart').getContext('2d');
                var myChart = new Chart(ctx, {
                    type: 'line',
                    data: chartData,
                    options: {
                        responsive: true,
                        scales: {
                            y: {
                                beginAtZero: true
                            }
                        }
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
