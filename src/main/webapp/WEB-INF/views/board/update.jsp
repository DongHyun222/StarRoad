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

    <link rel="stylesheet" type="text/css" href="/resources/static/css/board/update.css">
    <script type="text/javascript">
        $(function() {
            $("#navbar").load("/resources/common_jsp/navbar.jsp");
        });

    </script>
</head>
<body>

<form method="post" action="/starroad/board/updatepro">
<div class="container">
    <div id="navbar"></div>
    <div class="title">

        <input name="title" class="titleStyle"  type="text" id="title" placeholder="제목을 입력하세요" required value="${board.title}" />
        <br>

        <span class="regdate"><c:out value="${board.regdate}" /></span>
        <span class="likes">
                <img src="https://ifh.cc/g/aw0vjY.png" alt="Like Icon" style="vertical-align: middle; width: 20px; height: 20px;">
                <c:out value="${board.likes}" />
            </span>
<%--
        <div class="title-buttons">
            <button id="editBtn">수정</button>
            <button id="deleteBtn">삭제</button>
        </div>--%>
    </div>

    <div class="content">
        <textarea name="content" class="contentStyle" id="content" placeholder="내용을 입력하세요" required>${board.content}</textarea>
        <div class="image-input">
            <input type="file" name="image" id="image">
        </div>
        <div class="update-button">
            <button type="submit" class="buttonStyle" id="updateBtn" class="submit-button">등록</button>
        </div>
    </div>
</div>




</form>
<script>
    document.getElementById("updateBtn").addEventListener("click", function() {

            location.href = "/starroad/board/updatePro" ; // 삭제 API 호출

    });

</script>
</body>
</html>