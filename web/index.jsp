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
        <label for="region">Регион</label>
        <select name="region" class="form-control" id="region">
          <option value="us">США</option>
          <option value="ru">Россия</option>
          <option value="by">Беларусь</option>
        </select>
      </div>
      <div class="form-group">
        <label for="mistake-type">Тип ошибки</label>
        <select name="mistake" class="form-control" id="mistake-type">
          <option value="insert">Добавление символов</option>
          <option value="delete">Удаление символов</option>
          <option value="replace">Замена символов</option>
        </select>
      </div>
      <div class="form-group">
        <label for="mistake-percent">Процент ошибок</label>
        <input name="percent" type="range" min="1" max="90" value="50" class="slider" id="mistake-percent">
        <small id="valueOfRange" class="form-text text-muted">50%</small>
      </div>
      <div class="form-group">
        <label for="record-amount">Количество записей</label>
        <input name="amount" value="100" type="number" class="form-control" id="record-amount" aria-describedby="emailHelp" placeholder="100">
      </div>

      <div class="form-check">
        <input class="form-check-input" type="radio" name="type" id="pdf" value="pdf" checked>
        <label class="form-check-label" for="pdf">PDF файл</label>
      </div>
      <div class="form-check">
        <input class="form-check-input" type="radio" name="type" id="csv" value="csv">
        <label class="form-check-label" for="csv">CSV файл</label>
      </div>
      <button type="submit" name="page" value="service" class="btn btn-primary">Получить список</button>
    </form>
  </div>



  <script src="vendor/jquery-2.0.3.min.js"></script>
  <script src="vendor/bootstrap/js/bootstrap.min.js"></script>
  <script src="js/main.js"></script>
  </body>
</html>
