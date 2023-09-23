<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>STARROAD</title>
    <link rel="icon" href="${path}/resources/static/image/home/logo1.png" type="image/x-icon">
    <link rel="stylesheet" href="${path}/resources/static/css/common.css">
    <link rel="stylesheet" href="${path}/resources/static/css/mypage/sidebar.css">
    <link rel="stylesheet" href="${path}/resources/static/css/mypage/board.css">
    <!-- jquery 링크, navbar -->
    <script src="//code.jquery.com/jquery-3.6.0.min.js"></script>
    <script type="text/javascript">
        $(function () {
            $("#navbar").load("${path}/resources/common_jsp/navbar.jsp");
        });
    </script>
</head>
<body>
<div id="navbar"></div>
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
    <article id="writings_c">
        <div id="sub_menu">
            <a id="not_sel" href="/starroad/mypage/board">작성글</a>&nbsp;
            <a id="sel" href="/starroad/mypage/comment">작성댓글</a>
        </div>
        <section>
            <c:forEach var="comment" items="${comments}">
                <div class="b_contents">
                    <span class="b_type">${comment.board.type.equals("0")?"자유게시판":"인증게시판"}</span><br>
                    <div class="b_title"><a href="/starroad/board/detail?no=${comment.board.no}" class="b_title">${comment.content}</a></div>
                    <div class="w_t_d">
                        <div class="w_title">${comment.board.title}</div>
                        <div class="w_date">${comment.regdate.toString().substring(0,10)}</div>
                    </div>
                </div>
            </c:forEach>
        </section>
    </article>
</main>
</body>
</html>