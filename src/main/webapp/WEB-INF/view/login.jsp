<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="URL" value="/dressshop"/>
<!DOCTYPE html>
<html>
<head>
    <title>Авторизация</title>
    <link type="text/css" rel="stylesheet" href="${URL}/css/loginStyle.css"/>
</head>
<body>

    <form class="loginForm" method="POST">
        <label>Авторизация</label>
        <div class="field">
            <label>Имя пользователя</label>
            <div class="input">
                <input type="text" name="login" placeholder="Введіть логін">
            </div>
        </div>

        <div class="field">
            <a href="#" class="forgot"> Забыли пароль?</a>
            <label>Пароль</label>

            <div class="input">
                <input type="password" name="password" placeholder="Пароль">
            </div>
        </div>
        <div class="submit">
            <button  type="submit"  name="submit" >Войти</button>
        </div>
    </form>

</body>
</html>
