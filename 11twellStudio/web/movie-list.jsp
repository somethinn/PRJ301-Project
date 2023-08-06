<%-- 
    Document   : movie-list-new
    Created on : Jun 11, 2023, 7:24:31 PM
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

        <title>MOVIES</title>
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

        <style>
            .like-btn:hover {
                opacity: 0.8;
            }
        </style>
    </head>
    <body>

        <%@include file="header.jsp" %>
        <div class="movie_list">

            <div class="movie_list--center">
                <div class="movie_list--tittle">
                    <h2>Movies</h2>
                    <h3>${requestScope.userGoogle.email}</h3>
                </div>
            </div>

            <div class="movie_list--content row">
                <form class="movie_list--content-sort col-md-3" action="./movie-list" method="POST">
                    <div class="movie_list--content--sort--search" >
                        <input value="${param.search}" type="text" placeholder="Movie name" name="search" style="padding: 3% 5%; border-radius: 10px; border: 2px solid #333">
                    </div>

                    <div class="movie_list--content--sort--select">
                        <p style="margin-right: 12px;">SHOW</p>
                        <select name="sortBy" id="sortBy" onchange="updateOption(this)"  style="padding: 3% ; border-radius: 10px; border: 2px solid #333">
                            <option value="ASC">Oldest</option>
                            <option value="DESC">Latest</option>
                        </select>
                    </div>

                    <input id="statusInput" name="status" value="${requestScope.status}" type="hidden">

                    <div class="movie_list--content--sort--link">
                        <a  href="#" onclick="setStatus(1); document.forms[0].submit();">NOW SHOWING</a>
                        <a  href="#" onclick="setStatus(2); document.forms[0].submit();">COMING SOON</a>
                    </div>
                </form>

                <div class="movie_list--content--item--container col-md-9 row " style="margin: 0">
                    <c:forEach var="m" items="${requestScope.mList}">
                        <div class="movie_list--content--item col-md-4 col-sm-6">
                            <div class="movie_list--content--item--img">
                                <a href="movie-detail?movieID=${m.movieID}" style="border: 5px solid #333; max-width: 100%; display: inline-table">
                                    <img  height="auto"src="./assets/img/film/${m.movieImage}" alt="" class="movie_list-image">
                                </a>

                            </div>

                            <div class="movie_list--content--item--tittle">
                                <a href="">${m.movieTitle}</a>
                            </div>

                            <div class="movie_list--content--item--des">
                                <p><strong>Genre:</strong> ${m.genre}</p>
                                <p><strong>Running time:</strong> ${m.movieTime} minutes</p>
                                <p>
                                    <strong>Release day:</strong>
                                    <fmt:formatDate value="${m.moviePremiere}" pattern="dd/MM/yyyy"/>
                                </p>
                            </div>

                            <div class="movie_list--content--item--status">
                                <p style="margin-bottom: 5px; color: #444444;"><strong>
                                        <c:if test="${m.movieStatus==1}"><p style="color: limegreen;">NOW SHOWING</p></c:if>
                                        <c:if test="${m.movieStatus==2}"><p style="color: orange;">COMING SOON</p></c:if>
                                        <c:if test="${m.movieStatus==0}">OUT OF DATE</c:if>
                                        </strong>
                                    </p>
                                </div>








                                <a class="movie_list--content--item--booking" href="./booking?movieID=${m.movieID}">BOOKING</a><br/><br/>
                                <a class="like-btn" style="text-decoration: none; padding: 10px 10px; background-color: dodgerblue; color: white; border-radius: 5px; font-weight: 700" href="./movie-like?movieID=${m.movieID}"><i class="fa fa-thumbs-up" aria-hidden="true"></i> Like: ${m.movieNumberOfLike}</a>

                        </div>                   
                    </c:forEach>

                    <nav aria-label="Page navigation example" style="margin: 20px 7% ;">
                        <ul class="pagination">
                            <!--                            <li class="page-item"><a class="page-link" href="">Previous</a></li>-->
                            <c:forEach var="a"  begin="1" end="${numberPage}">
                                <li class="page-item"><a class="page-link" href="./movie-list?index=${a}&search=${param.search}&sortBy=${requestScope.sortBy}" >${a}</a></li>

                            </c:forEach>

                            <!--                            <li class="page-item"><a class="page-link" href="#">Next</a></li>-->
                        </ul>
                    </nav>
                </div>
            </div>
        </div>





        <%@include file="footer.jsp" %>

        <script>

            function setStatus(status) {
                document.getElementById('statusInput').value = status;
            }

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
