<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>mypage</title>
    <link rel="stylesheet" href="${path}/resources/static/css/common.css" />
    <link rel="stylesheet" href="${path}/resources/static/css/mypage/sidebar.css" />
</head>
<body>
<nav>
    <main>
        <aside>
            <div id='sidebar_title'>마이페이지</div>
            <ul>
                <li><a class='sidebar_menu' href='/starroad/mypage/asset'>나의 자산</a></li>
                <li><a class='sidebar_menu' href='/starroad/mypage/challenge'>적금 챌린지</a></li>
                <li><a class='sidebar_menu' href='/starroad/mypage/board' id='selected'>작성한 글 보기</a></li>
                <li><a class='sidebar_menu' href='/starroad/mypage/info'>정보 수정</a></li>
                <li><a class='sidebar_menu' href='/starroad/mypage/password'>비밀번호 수정</a></li>
            </ul>
        </aside>
        <article></article>
    </main>
</body>
</html>