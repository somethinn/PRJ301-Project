<%-- 
    Document   : home-page
    Created on : Jun 25, 2023, 3:34:44 PM
    Author     : Nguyen Thanh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>11-12 HOME</title>
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
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.carousel.min.css" integrity="sha512-tS3S5qG0BlhnQROyJXvNjeEM4UpMXHrQfTGmbQ1gKmelCxlSEBUaxhRBj/EFTzpbP4RVSrpEikbmdJobCvhE3g==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.theme.default.min.css" integrity="sha512-sMXtMNL1zRzolHYKEujM2AqCLUR9F2C4/05cdbxjjLSRvMQIciEPCQZo++nk7go3BtSuK9kfa/s+a4f4i5pLkw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <style>
            @media screen and (max-width: 768px) {
                #carouselExampleRide {
                    display: none;
                }
            }
        </style>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <div id="carouselExampleRide" class="carousel slide" data-bs-ride="true">
            <div class="carousel-inner">
                <div class="carousel-item active">
                    <a>
                        <img height="550px" src="https://wallpapercave.com/wp/wp9299722.jpg" class="d-block w-100" alt="...">
                    </a>
                </div>
                <c:forEach var="m" items="${requestScope.mListStatus}">
                    <div class="carousel-item">
                        <a href="movie-detail?movieID=${m.movieID}">
                            <img style="height: 550px;" src="./assets/img/slider/${m.movieImageSlide}" class="d-block w-100" alt="...">
                        </a>
                    </div>
                </c:forEach>

            </div>
            <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleRide" data-bs-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Previous</span>
            </button>
            <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleRide" data-bs-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Next</span>
            </button>
        </div>

        <div class="movie_selection">
            <div class="movie_selection--tittle">
                <h2>MOVIES SELECTION</h2>
            </div>
        </div>
        
        <!--padding: 50px 150px-->
        <div class="owl-carousel owl-theme" style="padding: 2% 12%">
            <c:forEach var="ml" items="${requestScope.mList}">
                <div class="item"><a href="movie-detail?movieID=${ml.movieID}"><img style="border: 5px solid #333;" height="300px" src="./assets/img/film/${ml.movieImage}" alt=""></a></div>
            </c:forEach>
        </div>
        
        <div class="movie_selection">
            <div class="movie_selection--tittle">
                <h2>EVENTS</h2>
            </div>
        </div>
        
        <div class="owl-carousel owl-theme" style="padding: 2% 12%">
            <c:forEach var="p" items="${requestScope.pList}">
                <div class="item"><a href="post-detail"><img style="border: 5px solid #333" height="300px" src="./assets/img/post/${p.postImage}" alt=""></a></div>
            </c:forEach>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
        crossorigin="anonymous"></script>

        <script src="https://code.jquery.com/jquery-3.7.0.js"
        integrity="sha256-JlqSTELeR4TLqP0OG9dxM7yDPqX1ox/HfgiSLBj8+kM=" crossorigin="anonymous"></script>

        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.0/jquery.min.js" integrity="sha512-3gJwYpMe3QewGELv8k/BX9vcqhryRdzRMxVfq6ngyWXwo03GFEzjsUm8Q7RZcHPHksttq7/GFoxjCVUjkjvPdw==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>

        <script src="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/owl.carousel.min.js" integrity="sha512-bPs7Ae6pVvhOSiIcyUClR7/q2OAsRiovw4vAkX+zJbw3ShAeeqezq50RIIcIURq7Oa20rW2n2q+fyXBNcU9lrw==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>

        <script type="text/javascript">
            $('.owl-carousel').owlCarousel({
                loop: true,
                margin: 10,
                autoplay: true,
                nav: true,
                responsive: {
                    0: {
                        items: 1
                    },
                    600: {
                        items: 3
                    },
                    1000: {
                        items: 5
                    }
                }
            })
        </script>
        </script>

        <%@include file="footer.jsp" %>
    </body>
</html>
