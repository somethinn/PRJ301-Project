<%-- 
    Document   : login
    Created on : Jun 11, 2023, 9:57:36 PM
    Author     : Nguyen Thanh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />

        <!--favicon link-->
        <link rel="icon" type="image/png" href="assets/img/favicon_io/favicon-32x32.png">

        <!-- Google font -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,300;0,400;0,500;0,700;0,800;0,900;1,300;1,500;1,700&display=swap" rel="stylesheet">
        <!-- End -->

        <!-- Bootstrap 5.3 -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
        <!-- End -->

        <link rel="stylesheet" href="./assets/css/styles.css" />
        <title>LOGIN</title>
    </head>
    <body>

        <%@include file="header.jsp"%>
        <div class="container-sm">
            <div class="login__header">
                <a href="login.jsp" class="login">LOGIN</a>
                <a href="register.jsp" class="">REGISTER</a>
            </div>

            <form class="login__form" action="login" method="POST">
                <c:if test="${requestScope.registerMsg != null}">
                    <p style="color: green">${requestScope.registerMsg}</p>
                </c:if> 
                <c:if test="${requestScope.forgotMsg != null}">
                    <p style="color: green">${requestScope.forgotMsg}</p>
                </c:if> 
                <div class="form__item">
                    <label for="">Email</label><br />
                    <input type="email" name="email" required value="${param.email}"/>
                </div>
                <br />

                <div class="form__item">
                    <label for="">Password</label><br />
                    <input type="password" name="password" required value="${param.password}" />
                </div>
                <br />


                <div class="form__item">
                    <table>
                        <tr><td style="color: red">${requestScope.error}</td></tr>
                        <tr>
                            <td><label for="">Please type the letters below*</label><br /></td>
                            <td><a href="javascript:void(0);" onclick="generateNewCaptcha();" class="refresh-captcha"
                                   style="margin-left: 30%; color: #bdbdbd">Refresh</a><br/>
                                <br /></td>
                        </tr>
                        <tr>
                            <td> <input type="text" name="captchaInput" required="" value="${param.captchaInput}"/><br /></td>
                            <td><img id="captcha-image" src="./captcha" alt="CAPTCHA" style="border: 2px solid #333; border-radius: 10px"/><br/>
                            </td>
                        </tr>
                    </table>


                </div>
                <br />

                <input type="submit" value="LOGIN" class="login-button" />
                <br />

                <!--Login with Google-->
                <div style="margin: 20px 0;">
                    <a href="https://accounts.google.com/o/oauth2/auth?scope=email%20profile%20openid &redirect_uri=http://localhost:8080/11twell/login&response_type=code
                       &client_id=365928126558-kq93qbavanbv7i1iq017dl6m9o03v8bf.apps.googleusercontent.com&approval_prompt=force"
                       style="border: 1px solid #333; border-radius: 20px; text-decoration: none; padding: 1% 4%; margin-left: 35%;"

                       > <img src="./assets/img/Logo/Google__G__Logo.svg.png" style="width: 2%">  Login With Google</a>
                </div>    
                <br/>


                <a href="forgot-password" class="forgotpassword">Forgot Your Password?</a>
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
    </body>
</html>
