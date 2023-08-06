<%-- 
    Document   : movie-management
    Created on : Jun 14, 2023, 3:14:23 PM
    Author     : Nguyen Thanh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html" charset=UTF-8">
        <title>POST MANAGEMENT</title>
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
        <style>
            td {
                padding: 0 10px;
            }
            
            th {
                padding: 10px;
            }
        </style>
    </head>
    <body>

        <div class="management row">
            <%@include file="nav-bar-management.jsp"%>
           
            <div class="management_section col-md-10">
                <div class="management_section--tittle">
                    <h1>POST</h1>
                </div>

                <div class="management_section--feature row">

                    <div class="management_section--feature--add col-md-4">
                        <a href="check?action=add-post">ADD NEW POST</a>
                    </div>
                    <form class="col-md-8" action="post-management" method="POST">
                        <div class="management_section--feature--filter row">

                            <div class="management_section--feature--filter--tittle col-md-3" style="padding: 0;">
                                <p>Filter</p>
                            </div>

                            <div class="management_section--feature--filter--search--name col-md-3">
                                <input type="text" placeholder="NAME" name="name">
                            </div>

                            <div class="management_section--feature--filter--select--status col-md-3">
                                <select name="status" id="">
                                    <option value="" selected disabled hidden>STATUS</option>
                                    <option value="0">Inactive</option>
                                    <option value="1">Active</option>
                                </select>
                            </div>

                            <div class="management_section--feature--filter--btn col-md-3">
                                <input type="submit" value="GO" style="background-color: black; color: white; border: none; padding: 3px 15px; background-color: #ebebeb; width: 50%; color: black; font-weight: 700; border-radius: 5px; min-height: 28px; text-align: center"/>
                                <!--<a href="">GO</a>-->
                            </div> 
                        </div>
                    </form>
                </div>

                <div class="management_section--table">
                    <table>
                        <tr>
                            <th>ID</th>
                            <th>TITTLE</th>
                            <th>IMAGE</th>
                            <th>CONTENT</th>
                            <th>STATUS</th>
                            <th>ACTION</th>
                        </tr>
                        <c:forEach var="p" items="${requestScope.pList}">
                            <tr>
                                <td>${p.postID}</td>
                                <td>${p.postTitle}</td>
                                <td class="management_section--table--img"><img src="./assets/img/post/${p.postImage}" alt="" style="width: 20%"></td>
                                <td>${p.postContent}</td>
                                <td>
                                    <c:if test="${p.postStatus == 0}"><p style="color: red; font-weight: 700">Inactive</p></c:if>
                                    <c:if test="${p.postStatus == 1}"><p style="color: green; font-weight: 700">Active</p></c:if>
                                    </td>
                                    <td>
                                        <a href="check?action=update-post&postID=${p.postID}">Edit</a>
                                    <a href="#" onclick="doDelete('${p.postID}')">Delete</a>
                                    <a href="check?action=switch-post&postStatus=${p.postStatus}&postID=${p.postID}">Switch</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
                <nav aria-label="Page navigation example" style="margin: 20px 7% ;">
                        <ul class="pagination">
                            <li class="page-item ${(index-1 == 0) ? "disabled" : ""}"><a class="page-link" href="./post-management?index=${index-1}&name=${name}&status=${status}">&laquo;</a></li>
                            <c:forEach var="a"  begin="1" end="${totalPage}">
                                <li class="page-item ${(index == a) ? "active": ""} "><a class="page-link"  href="./post-management?index=${a}&name=${name}&status=${status}">${a}</a></li>
                            </c:forEach>

                            <li class="page-item ${(index+1 > totalPage) ? "disabled" : ""}"><a class="page-link" href="./post-management?index=${index+1}&name=${name}&status=${status}">&raquo;</a></li>
                        </ul>
                </nav>
            </div>
        </div>

        <script>
            function doDelete(id) {
                if (confirm("Are you sure to delete this event with id = " + id)) {
                    window.location = "check?action=delete-post&postID=" + id;
                }
            }
        </script>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
        crossorigin="anonymous"></script>

        <script src="https://code.jquery.com/jquery-3.7.0.js"
        integrity="sha256-JlqSTELeR4TLqP0OG9dxM7yDPqX1ox/HfgiSLBj8+kM=" crossorigin="anonymous"></script>
    </body>
</html>
