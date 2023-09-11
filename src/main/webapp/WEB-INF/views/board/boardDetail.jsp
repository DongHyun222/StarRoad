<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시물 상세보기</title>
    <script src="//code.jquery.com/jquery-3.6.0.min.js"></script>
    <link rel="stylesheet" type="text/css" href="/resources/static/css/boardDetail.css">
    <script type="text/javascript">
            $(function() {
                $("#navbar").load("/resources/navbar.jsp");
            });
    </script>
</head>
<body>
    <div class="container">
        <div id="navbar"></div>

        <div class="title">
            <h1>제목</h1>
            <c:out value="${board.title}" />
            <c:out value="${board.regdate}" />
            <c:out value="${board.likes}" />

            <div class="title-buttons">
                <button id="editBtn">수정</button>
                <button id="deleteBtn">삭제</button>
            </div>
        </div>

        <div class="content">
            <h2>내용<h2>
            <c:out value="${board.content}" />
            <div class="like-section">
                <h3>좋아요<h3>
                <c:out value="${board.likes}" />
            </div>
        </div>

        <div class="comment">
             <h2>댓글<h2>
             <c:out value="${board.commentNum}" />

             <div class="comment-input">
                 <textarea id="commentText" placeholder="댓글을 입력하세요" rows="4" cols="50"></textarea>
                 <button id="submitComment">등록</button>
             </div>
        </div>

        <div class="comments-list">
            <h2>댓글 목록<h2>
            <c:forEach var="comment" items="${board.comments}">
                <p>
                    <c:out value="${comment.no}" />:
                    <c:out value="${comment.content}" />:
                    <c:out value="${comment.regdate}" />:
                    <c:out value="${comment.board_no}" />:
                    <c:out value="${board.likes}" />
                </p>
            </c:forEach>
        </div>

    </div>
</body>
</html>