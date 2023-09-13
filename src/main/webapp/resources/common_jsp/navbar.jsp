<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>navbar</title>
    <link rel="stylesheet" type="text/css" href="${path}/resources/static/css/common.css">
    <link rel="stylesheet" type="text/css" href="${path}/resources/static/css/nav.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
            crossorigin="anonymous"></script>
</head>
<body>
<header>
    <nav>
        <!-- 로고와 이름 -->
        <div>
            <div class="logo">
                <a href="/starroad">
                    <img src="${path}/resources/static/image/home/starroad.png" alt="로고 이미지">
                </a>
            </div>

            <ul>
                <li><a href="/starroad/product">금융상품</a></li>
                <li><a href="/starroad/policy">청년정책</a></li>
                <li><a href="/starroad/board/main">커뮤니티</a></li>
            </ul>
        </div>

        <!-- 로그인 버튼 -->
        <button class="btn"><a href="/starroad/login">로그인</a></button>

        <!-- 로그인 시 -->
        <!--
        <div>
            <button class="btn"><a href="/starroad/mypage/asset">마이페이지</a></button>
            <button class="btn"><a href="/starroad/logout">로그아웃</a></button>
        </div>
        -->
    </nav>
</header>


</body>
</html>