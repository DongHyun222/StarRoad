<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>STARROAD</title>
    <link rel="icon" href="${path}/resources/static/image/home/logo.png" type="image/x-icon">
    <script src="//code.jquery.com/jquery-3.6.0.min.js"></script>
    <link rel="stylesheet" href="${path}/resources/static/css/common.css">
    <link rel="stylesheet" type="text/css" href="/resources/static/css/auth/login.css">
    <script type="text/javascript">
        $(function () {
            $("#navbar").load("/resources/common_jsp/navbar.jsp");
        });
    </script>
</head>
<body>
<div id="navbar"></div>
<main id="login_main">
    <div id="egg_c">
        <img id="egg" src="/resources/static/image/member/egg.png">
    </div>
    <article id="login_m">
        <div id="login_title">로그인</div>
        <div id="login_exp">청춘을 위한 Star Road</div>
        <form action="/starroad/login" method="post" class="text-container">
            <input type="text" name="id" placeholder="아이디" value="${written_id}"><br>
            <input type="password" name="password" placeholder="비밀번호">
            <div class="error-message">${error}</div>
            <input type="submit" value="로그인" class="login-button1">
        </form>
        <div class="link-container">
            <div class="signup-options">
                <span id="signup_e">계정이 없으신가요?</span>
                <a id="signup_a" href="/starroad/member">회원가입</a>
            </div>
        </div>
    </article>
</main>
</body>
</html>