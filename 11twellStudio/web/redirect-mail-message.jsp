<%-- 
    Document   : register-message
    Created on : Jun 20, 2023, 7:36:26 AM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>11-12 STUDIO</title>
        <link rel="icon" type="image/png" href="assets/img/favicon_io/favicon-32x32.png">

    </head>
    <body>
        <%@include file="header.jsp"%>
        <div class="redirect-mail_container" style="display: flex; justify-content: center; height: 600px">
        <c:if test="${request.message != null}"> <h2 style="text-align: center">${requestScope.message} </h2> </c:if>
        <c:if test="${request.messageForgot != null}"> <h2 style="text-align: center">${requestScope.messageForgot} </h2> </c:if>
        <div>
            <h3 style="line-height: 600px; font-weight: 700">Please check your mailbox.</h3>
        </div>
        </div>
        <%@include file="footer.jsp"%>

    </body>
</html>
