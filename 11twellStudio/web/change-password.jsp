<%-- 
    Document   : change-password
    Created on : Jun 18, 2023, 11:00:21 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CHANGE PASSWORD</title>
        <link rel="icon" type="image/png" href="assets/img/favicon_io/favicon-32x32.png">

    </head>
    <body>
        <%@include file="header.jsp"%>

        <form action="change-password" method="POST">

            <div class="movie_list--center" style="margin: 20px 0">
                <div class="movie_list--tittle">
                    <h2>Change password</h2>
                </div>
            </div>
            <div class="form-change-pass-container" style="padding: 0 3.5%">
                <c:if test="${requestScope.userGoogle != null}">
                    <p>${requestScope.message}</p>
                    <input type="hidden" value="${requestScope.userGoogle.userEmail}" name="userGoogleMail">
                </c:if>

                <div  class="form__item">
                    <label>Enter password</label><br/>
                    <c:if test="${requestScope.wrongPassMsg != null}">
                        <p style="color: red">${requestScope.wrongPassMsg}</p>
                    </c:if>
                    <input type="password" name="userPassword" value="${param.userPassword}"/>
                </div>
                <br/>
                
                <div  class="form__item">
                    <label>Enter new password</label><br/>
                    <input type="password" name="userNewPassword" value="${param.userNewPassword}"/>
                </div>
                <br/>
                
                <div  class="form__item">
                    <label>Confirm new password</label><br/>
                <c:if test="${requestScope.wrongPassConfirmMsg != null}">
                    <p style="color: red">${requestScope.wrongPassConfirmMsg}</p>
                </c:if>
                <input type="password" name="confirmUserNewPassword" value="${param.confirmUserNewPassword}"/> 
                </div>
                <br/>
                
                <input style="margin-bottom: 70px" class="login-button form-input" type="submit" value="CHANGE PASSWORD"/>
            </div>
        </form>

        <%@include file="footer.jsp"%>

    </body>
</html>
