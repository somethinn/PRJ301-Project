<%-- 
    Document   : user-account-new
    Created on : Jun 19, 2023, 8:27:33 PM
    Author     : Nguyen Thanh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>PROFILE</title>
                        <link rel="icon" type="image/png" href="assets/img/favicon_io/favicon-32x32.png">

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
    <body>
         <%@include file="header.jsp"%>
        <div class="account">
            <div class="movie_list--center" style="margin-top: 20px">
                <div class="movie_list--tittle">
                    <h2>Account</h2>
                </div>
            </div>

            <div class="account_form" style="margin-bottom: 30px">
                <form action="account-user" method="POST">
                    <div class="account_form--item">
                        <label for="">Name</label>
                        <input name="userName" value="${sessionScope.account.userName}" type="text"/>
                    </div>

                    <div class="account_form--item">
                        <label for="">Phone Number</label>
                        <input name="userPhone" value="${sessionScope.account.userPhone}" type="text"/>
                    </div>

                    <div class="account_form--item">
                        <label for="">Email</label>
                        <input name="email" value="${sessionScope.account.userEmail}" type="email" readonly=""/>
                    </div>

                    <div class="account_form--item">
                        
                        <label for="">Date of birth</label>
                        <c:if  test="${sessionScope.account.userDOB == null}">
                            <p style="color: red">${sessionScope.msgSetDate}</p>
                        </c:if>
                        <input name="userDOB" value="${sessionScope.account.userDOB}" type="date"/>
                    </div>

                    <div class="account_form--item-gender">
                        <!--                        <label for="" style="font-size: 30px; font-weight: 700;">Gender</label>
                                                <input type="radio" name="userGender"  style="margin: 0 20px;" checked/><label for="" style="margin: 16px 0; font-size: 20px; font-weight: 500">Male</label>
                                                <input type="radio" name="userGender" style="margin: 0 20px;"><label for="" style="margin: 16px 0; font-size: 20px; font-weight: 500">Female</label>-->
                        <label for="" style="font-size: 30px; font-weight: 700;">Gender</label>
                        <c:if test="${sessionScope.account.userGender == 'F'}">
                            <input id="male" name="userGender" type="radio" value="M" style="width: 10%; display: inline-block; height: 16px" ><label  for="male" style="margin: 16px 0; font-size: 16px;font-weight: 500">Male</label>
                            <input id="female" name="userGender" type="radio" value="F" style="width: 10%; display: inline-block; height: 16px" checked><label  for="female"  style="margin: 16 0px; font-size: 16px;font-weight: 500">Female</label>
                        </c:if>

                        <c:if test="${sessionScope.account.userGender == 'M'}">
                            <input id="male" name="userGender" type="radio" value="M" style="width: 10%; display: inline-block; height: 16px"  checked><label  for="male" style="margin: 16px 0; font-size: 16px;font-weight: 500">Male</label>
                            <input id="female" name="userGender" type="radio" value="F" style="width: 10%; display: inline-block; height: 16px"><label  for="female"  style="margin: 16px 0; font-size: 16px;font-weight: 500">Female</label>
                        </c:if>

                        <c:if test="${sessionScope.account.userGender == null}">
                            <input id="male" name="userGender" type="radio" value="M" style="width: 10%; display: inline-block; height: 16px"><label  for="male" style="margin: 16px 0; font-size: 16px;font-weight: 500">Male</label>
                            <input id="female" name="userGender" type="radio" value="F" style="width: 10%; display: inline-block; height: 16px"><label  for="female"  style="margin: 16px 0; font-size: 16px;font-weight: 500">Female</label>
                        </c:if>
                    </div>

                    <div class="account_form--item">
                        <label for="">Region</label>
                        <input name="userRegion" value="${sessionScope.account.userRegion}" type="text"/>
                    </div>

                    <br/>
                    <c:if test="${requestScope.msgSuccess != null}">
                        <p style="color: green">${requestScope.msgSuccess}</p>
                    </c:if>

                    <input class="save-btn" type="submit" value="SAVE">
                </form>

                    <a href="change-password-user">Change password?</a>

            </div>
        </div>
        <%@include file="footer.jsp"%>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
        crossorigin="anonymous"></script>

        <script src="https://code.jquery.com/jquery-3.7.0.js"
        integrity="sha256-JlqSTELeR4TLqP0OG9dxM7yDPqX1ox/HfgiSLBj8+kM=" crossorigin="anonymous"></script>
    </body>
</html>
