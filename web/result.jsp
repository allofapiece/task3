<%--
  Created by IntelliJ IDEA.
  User: Listratsenka Stas
  Date: 06.03.2018
  Time: 16:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>People Base</title>
    <link rel="stylesheet" href="vendor/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="container">
    <form class="form-signin" action="FrontController"  method="get">
        <div class="form-group">
            <a href="outputfiles/data.<%=request.getParameter("type")%>" download>Скачать файл</a>
        </div>
        <div class="form-group">
            <button type="submit" name="page" value="index" class="btn btn-primary">Вернуться</button>
        </div>
    </form>
</div>



<script src="vendor/jquery-2.0.3.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap.min.js"></script>
<script src="js/main.js"></script>
</body>
</html>
