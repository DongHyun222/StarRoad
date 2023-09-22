<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>STARROAD</title>
    <link rel="icon" href="${path}/resources/static/image/home/logo.png" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href="/resources/static/css/board/update.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <script src="//code.jquery.com/jquery-3.6.0.min.js"></script>
    <script type="text/javascript">
        $(function () {
            $("#navbar").load("/resources/common_jsp/navbar.jsp");
        });
    </script>
</head>
<body>
<div class="container">
    <div id="navbar"></div>

    <div class="title">
        <span class="title-text"><c:out value="${board.title}"/></span> <br>
        <div class="something">

            <div class="authorStyle">
                <i class="fas fa-user-circle"></i>
                <span class="memberId author-id"><c:out value="${board.memberId}"/></span>
            </div>
            <hr class="separator">
            <span class="regdate">
                <i class="fas fa-clock"></i>
                <fmt:formatDate value="${board.regdate}" pattern="yyyy-MM-dd HH:mm"/>
            </span>
        </div>
    </div>

    <div class="content">

        <img src="data:image/jpeg;base64,${board.imageBase64}" alt="" width="200" height="200"
             style="margin-bottom: 30px;" onerror="this.style.display='none'"/>
        <c:out value="${board.content}"/>
        <div class="like-section">
        </div>
    </div>
    <div class="comment1">댓글 수정</div>
    <div class="comment">
        <div class="comment-input">
            <form action="/starroad/comment/doUpdate" method="post">
                <div class="comment-section">
<%--                    <label>댓글 내용:</label><br>--%>
                    <textarea id="commentText" name="content" rows="4" cols="50" autofocus>${comment.content}</textarea><br>
                    <input type="hidden" name="no" value="${comment.no}"/>
                    <input type="hidden" name="board" value="${comment.board.no}"/>
                    <button id="submitComment" type="submit">수정 완료</button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>