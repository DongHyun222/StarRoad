<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <script src="//code.jquery.com/jquery-3.6.0.min.js"></script>
    <link rel="stylesheet" type="text/css" href="/resources/static/css/auth/login.css">
    <script type="text/javascript">
            $(function() {
                $("#navbar").load("/resources/common_jsp/navbar.jsp");
            });
    </script>
</head>
<body>
<div class="container">
    <div id="navbar"></div>
    <div class="title">Login</div>

    <form action="/starroad/login" method="post" class="text-container">
        <input type="text" name="id" placeholder="아이디"><br>
        <input type="password" name="password" placeholder="비밀번호">

        <c:if test="${not empty error}">
            <p class="error-message">${error}</p>
        </c:if>

        <div class="checkbox-container">
            <input type="checkbox" name="keepLoggedIn" id="keepLoggedIn">로그인상태유지<br>
        </div>
        <input type="submit" value="로그인" class="login-button1">
        <div class="link-container">
            <div class="signup-options">
                <a href="/starroad/member">회원가입</a>
            </div>
            <div class="find-options">
                <a href="/starroad/findidpassword">아이디 비밀번호 찾기</a>
            </div>
        </div>
    </form>
</div>
</body>
</html>