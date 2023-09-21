<%@ page import="java.sql.Blob" %>
<%@ page import="java.util.Base64" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="ko">
<head>
    <!-- 메타 정보, 스타일 등 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/static/css/common.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/static/css/board/board2.css">
    <script src="//code.jquery.com/jquery-3.6.0.min.js"></script>
    <script type="text/javascript">
        $(function() {
            $("#navbar").load("${path}/resources/common_jsp/navbar.jsp");
            if ("${type}" === 'F') {
                $("#nav_typeF").css("color", "#543d0d").css("font-weight", 800)
                    .css("box-shadow", "inset 0 -10px 0 #FFBC00FF");
            }else if ("${type}" === 'C') {
                $("#nav_typeC").css("color", "#543D0DFF").css("font-weight", 800)
                    .css("box-shadow", "inset 0 -10px 0 #FFBC00FF");
            }
            else {
                $("#nav_popular").css("color", "#543D0DFF").css("font-weight", 800)
                    .css("box-shadow", "inset 0 -10px 0 #FFBC00FF");
            }
        });
    </script>
</head>
<body>
<div id="navbar"></div>


<!-- 네비게이션 바 -->
<div class="board_nav">
    <div class="board_nav_type">
        <ul class="board_nav_type_list">
            <li class="sidebar_menu"><a href= "popular"><p id="nav_popular">인기글</p></a></li>
            <li class="sidebar_menu"><a href= "free?type=F"><p id="nav_typeF">자유게시판</p></a></li>
            <li class="sidebar_menu"><a href= "free?type=C"><p id="nav_typeC">인증방</p></a></li>
        </ul>
    </div>
    <div class="board_nav_btn">
        <button id="nav_btn" onclick="location.href='/starroad/board/write';">글쓰기</button>
    </div>
</div>


<!-- 자유게시판 -->
<main>
    <div class="main_box">
        <div class="board_items menu-content">
            <c:forEach items="${freeBoardPage}" var="board">
                <div class="item_box item grow" rel="grow" style="cursor: pointer;" onclick="location.href='/starroad/board/detail?no=${board.no}';">
                    <div class="item_img" style="background-color: lightyellow">
                        <c:choose>
                            <c:when test="${not empty board.imageBase64}">
                                <img class="img_detail1" src="data:image/jpeg;base64,${board.imageBase64}" alt=""/>
                            </c:when>
                        </c:choose>
                    </div>

                    <div class="item_tag">
                        <span class="item_tag_text">${board.detailType}</span>
                    </div>

                    <div class="item_title">
                        ${board.title}
                    </div>

                    <div class="item_content">
                            ${board.content}
                    </div>

                    <div class="item_footer">
                        <div class="item_id_date">
                            <div class="item_user_icon">
                                <i class="fas fa-user-circle"></i>
                            </div>
                            <div>
                                <span class="icon_id">
                                    <c:choose>
                                        <c:when test="${not empty board.memberId}">
                                            ${board.memberId}
                                        </c:when>
                                        <c:otherwise>
                                            imkiki
                                        </c:otherwise>
                                    </c:choose>
                                </span> <br>
                                <span class="icon_text_date"><fmt:formatDate value="${board.regdate}" pattern="yyyy-MM-dd" /></span>
                            </div>
                        </div>
                        <div class="item_icon">
                            <i class="far fa-thumbs-up"></i><span class="icon_text">${board.likes}</span>
                            <i class="far fa-comment"></i><span class="icon_text"> ${board.commentNum}</span>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</main>


<!-- 인기게시판 -->
<main>
    <div class="main_box">
        <div class="menu-content board_items" id="popular">
            <c:forEach items="${popularBoardPage}" var="board" begin="0" end="5">
                <div class="item_box item grow" rel="grow" style="cursor: pointer;" onclick="location.href='/starroad/board/detail?no=${board.no}';">
                    <div class="item_img" style="background-color: lightyellow">
                        <c:choose>
                            <c:when test="${not empty board.imageBase64}">
                                <img class="img_detail1" src="data:image/jpeg;base64,${board.imageBase64}" alt=""/>
                            </c:when>
                        </c:choose>
                    </div>

                    <div class="item_tag">
                        <span class="item_tag_text">${board.detailType}</span>
                    </div>

                    <div class="item_title">
                        ${board.title}
                    </div>

                    <div class="item_content">
                            ${board.content}
                    </div>

                    <div class="item_footer">
                        <div class="item_id_date">
                            <div class="item_user_icon">
                                <i class="fas fa-user-circle"></i>
                            </div>
                            <div>
                                <span class="icon_id">
                                    <c:choose>
                                        <c:when test="${not empty board.memberId}">
                                            ${board.memberId}
                                        </c:when>
                                        <c:otherwise>
                                            imkiki
                                        </c:otherwise>
                                    </c:choose>
                                </span> <br>
                                <span class="icon_text_date"><fmt:formatDate value="${board.regdate}" pattern="yyyy-MM-dd" /></span>
                            </div>
                        </div>
                        <div class="item_icon">
                            <i class="far fa-thumbs-up"></i><span class="icon_text">${board.likes}</span>
                            <i class="far fa-comment"></i><span class="icon_text"> ${board.commentNum}</span>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>

    </div>
</main>


<script>
    function showContent(menu, type) {
        // 모든 메뉴 내용 숨기기
        document.querySelectorAll('.menu-content').forEach(function (content) {
            content.style.display = 'none';
        });

        // 선택한 메뉴 내용 표시
        var menuContent = document.getElementById(menu);
        menuContent.style.display = 'block';

        // AJAX 요청 URL 정의
        let url;
        if (menu === 'free') {
            url = '/starroad/board?type=' + type;
        } else if (menu === 'authentication') {
            url = '/starroad/board?type=' + type;
        } else if (menu === 'popular') {
            url = '/starroad/popular';
        }

        // AJAX 요청 시작
        $.ajax({
            url: url,
            method: 'GET',
            success: function (data) {
                var content = $(data).find('.board').html();
                menuContent.querySelector('.board').innerHTML = content;
            },
            error: function (error) {
                console.error('Failed to load content:', error);
            }
        });
    }


</script>
</body>
</html>