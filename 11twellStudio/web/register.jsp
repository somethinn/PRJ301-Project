<%-- 
    Document   : register
    Created on : Jun 13, 2023, 9:32:23 AM
    Author     : Nguyen Thanh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!--favicon link-->
        <link rel="icon" type="image/png" href="assets/img/favicon_io/favicon-32x32.png">
        <title>REGISTER</title>
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
        <%@include file="header.jsp"%>

        
        <div class="container-sm container-margin">
            <div class="register__header">
                <a href="login.jsp" class="">LOGIN</a>
                <a href="register.jsp" class="register">REGISTER</a>
            </div>
            <form class="register__form" action="./register" method="POST">
                <div  class="form__item">
                    <label for="name">Name*</label><br />
                    <input id="name" class="form-input" type="text" name="userName" required value="${param.userName}"/>
                </div>
                <br/>

                <div class="form__item">
                    <label for="phone">Phone number</label><br />
                    <input id="phone" class="form-input" type="number" name="userPhone" value="${param.userPhone}"/>
                </div>
                <br />

                <div class="form__item">
                     <c:if test="${requestScope.error!= null}">
                        <p style="color: red">${requestScope.error}</p>
                    </c:if>
                    <label for="email">Email*</label><br />
                    <input id="email" class="form-input" type="email" name="email" required value="${param.email}"/>
                </div>
                <br />

                <div class="form__item">
                    <label for="password">Password*</label><br />
                    <input id="password" class="form-input" type="password" name="password" required value="${param.password}"/>
                </div>
                <br />

                <div class="form__item">
                    <c:if test="${requestScope.msg != null}">
                        <p style="color: red">${requestScope.msg}</p>
                    </c:if>
                    <label for="confirm-password">Confirm password*</label><br />
                    <input id="confirm-password" class="form-input" type="password" name="confirmPassword" required value="${param.confirmPassword}"/>
                </div>
                <br />


                <div class="form__item">
                    <label for="" >Date of birth*</label><br />                 
                    <input name="userDOB" type="date" required value="${param.userDOB}">

                </div>
                <br />

                 <div class="form__item">
                    <label for="">Gender</label>                
                    <input id="male" name="userGender" type="radio" value="M" style="width: 10%; display: inline-block; height: 16px"><label  for="male" style="margin: 16px; font-size: 16px;font-weight: 500" >Male</label>
                    <input id="female" name="userGender" type="radio" value="F" style="width: 10%; display: inline-block; height: 16px"><label  for="female"  style="margin: 16px; font-size: 16px;font-weight: 500">Female</label>

                </div>
                <br />
                
                <div class="form__item">
                    <label for="region">Region</label><br />
                    <input id="region" class="form-input" type="text" name="userRegion" value="${param.userRegion}"/>
                </div>
                <br />

                <div class="form__item">
                     <table>
                        <tr>
                            <td><label for="captcha">Please type the letters below*</label><br /></td>
                            <td><a href="javascript:void(0);" onclick="generateNewCaptcha();" class="refresh-captcha"
                                   style="margin-left: 30%; color: #bdbdbd">Refresh</a><br/>
<br /></td>
                        </tr>
                        <c:if test="${requestScope.errorCaptcha != null}">
                        <tr><td style="color: red">${requestScope.errorCaptcha}</td></tr>
                        </c:if>
                        <tr>
                            <td> <input id="captcha" type="text" name="captchaInput" required="" value="${param.captchaInput}"/><br /></td>
                            <td><img id="captcha-image" src="./captcha" alt="CAPTCHA" style="border: 2px solid #333; border-radius: 10px"/><br/>
</td>
                        </tr>
                    </table>
                </div>
                <br />

                <input type="submit" value="SIGN-UP" class="login-button form-input" />
                <br />
            </form>
        </div>
        
        <%@include file="footer.jsp"%>
        

        <script>
            function generateNewCaptcha() {
                var xhr = new XMLHttpRequest();
                xhr.open('GET', './captcha?action=generateCaptcha', true);
                xhr.responseType = 'blob';

                xhr.onload = function () {
                    if (this.status === 200) {
                        var captchaImage = document.getElementById('captcha-image');
                        captchaImage.src = URL.createObjectURL(this.response);
                    }
                };

                xhr.send();
            }
        </script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
                            integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
                    crossorigin="anonymous"></script>
                    
                    <script src="https://code.jquery.com/jquery-3.7.0.js"
                    integrity="sha256-JlqSTELeR4TLqP0OG9dxM7yDPqX1ox/HfgiSLBj8+kM=" crossorigin="anonymous"></script>
    </body>
</html>
