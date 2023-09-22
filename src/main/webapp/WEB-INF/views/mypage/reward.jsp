<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>STARROAD</title>
    <link rel="icon" href="${path}/resources/static/image/home/logo.png" type="image/x-icon">
    <link rel="stylesheet" href="${path}/resources/static/css/common.css">
    <link rel="stylesheet" href="${path}/resources/static/css/mypage/sidebar.css">
    <link rel="stylesheet" href="${path}/resources/static/css/mypage/reward.css">
    <!-- jquery 링크, navbar -->
    <script src="//code.jquery.com/jquery-3.6.0.min.js"></script>
    <script type="text/javascript">
        $(function () {
            $("#navbar").load("${path}/resources/common_jsp/navbar.jsp")
        });
    </script>
</head>
<div id="navbar"></div>
<main>
    <aside>
        <div id='sidebar_title'>마이페이지</div>
        <ul>
            <li><a class='sidebar_menu' href='/starroad/mypage/asset'>나의 자산</a></li>
            <li><a class='sidebar_menu' href='/starroad/mypage/challenge' id='selected'>적금 챌린지</a></li>
            <li><a class='sidebar_menu' href='/starroad/mypage/board'>작성한 글 보기</a></li>
            <li><a class='sidebar_menu' href='/starroad/mypage/info'>정보 수정</a></li>
            <li><a class='sidebar_menu' href='/starroad/mypage/password'>비밀번호 수정</a></li>
        </ul>
    </aside>
    <article>
        <div id="prod_name">${name}</div>
        <section id="reward_c">
            <form id="reward_m" method="post" action="/starroad/mypage/save-reward">
                <div id="reward_t">${period}개월동안 고생하셨습니다!</div>
                <img id="wallet_img" src="/resources/static/image/mypage/wallet.png">
                <div id="r_content">버튼을 눌러 포인트리 <span id="rew">${reward}P</span>를 받으세요</div>
                <input type="hidden" name="sub_no" value="${sub_no}">
                <input type="hidden" name="reward" value="${reward}">
                <button id="reward_btn">리워드 받기</button>
            </form>
        </section>
    </article>
</main>
</html>