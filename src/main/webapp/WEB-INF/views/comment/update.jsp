<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>STARROAD</title>
    <link rel="icon" href="${path}/resources/static/image/home/logo1.png" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href="/resources/static/css/board/update.css">
    <script src="//code.jquery.com/jquery-3.6.0.min.js"></script>
    <script type="text/javascript">
            $(function() {
                $("#navbar").load("/resources/common_jsp/navbar.jsp");
            });
    </script>
</head>
<body>
    <div class="container">
        <div id="navbar"></div>

        <form action="/starroad/comment/doUpdate" method="post">
            <div class="comment-section">
                <label for="content">댓글 내용:</label><br>
                <textarea id="commentText" name="content" rows="4" cols="50">${comment.content}</textarea><br>
                <input type="hidden" name="no" value="${comment.no}" />
                <input type="hidden" name="board" value="${comment.board.no}" />
                <button type="submit">수정 완료</button>
            </div>
        </form>
    </div>
</body>
</html>