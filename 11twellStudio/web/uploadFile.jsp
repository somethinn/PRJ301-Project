<%-- 
    Document   : uploadFile
    Created on : Jun 7, 2023, 9:44:52 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Upload</h1>
        <form method="post" action="UploadImg"  enctype="multipart/form-data">
            File: <input type="file" name="file" ><br><!-- comment -->
            Pid: <input type="text" name="mid"><!-- comment -->
            <input type="submit" value="up">
        </form>
        <img src="./images/rsz_spiderverse2_poster_4.jpg" alt="alt"/>
    </body>
</html>
