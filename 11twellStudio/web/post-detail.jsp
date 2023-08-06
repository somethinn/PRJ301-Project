<%-- 
    Document   : post-detail
    Created on : Jun 17, 2023, 11:20:23 PM
    Author     : somthinn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta name="viewport">
        <meta http-equiv="Content-Type" content="text/html" charset=UTF-8">
        <title>POST DETAIL</title>
        <link rel="icon" type="image/png" href="assets/img/favicon_io/favicon-32x32.png">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Montserrat&family=Roboto+Mono:ital,wght@1,200&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="./assets/css/post-style.css">
    </head>
    <body>
        <div class="post-detail" id="wrapped">
            <%@include file="header.jsp" %>
            <c:forEach items="${requestScope.pList}" var="p" >
                <div class="promotion-block">
                    <h1 style="color: red; margin-bottom: 10px; margin-top: 30px; font-weight: bold; ">${p.postTitle}</h1>
                    <div class="promotion-block-pic">
                        <img  src="./assets/img/post/${p.postImage}" alt="photo">
                    </div>
                    <div class="promotion-block-txt" style="margin-bottom: 20px ">
                        <h1 >Content</h1>
                        <br>
                        <p>
                            ${p.postContent}
                        </p>

                    </div>
                </div>
            </c:forEach>    
            <%@include file="footer.jsp" %>
        </div>
    </body>
</html>
