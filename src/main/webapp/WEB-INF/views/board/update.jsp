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
    <script src="//code.jquery.com/jquery-3.6.0.min.js"></script>

    <link rel="stylesheet" type="text/css" href="/resources/static/css/board/update.css">
    <script type="text/javascript">
        $(function () {
            $("#navbar").load("/resources/common_jsp/navbar.jsp");
        });

    </script>
</head>
<body>


<form method="post" enctype="multipart/form-data" action="/starroad/board/updatepro?no=${board.no}">
    <input type="hidden" name="no" value="${board.no}">
<div class="container">
    <div id="navbar"></div>
    <div class="title">
            <input name="title" class="titleStyle" type="text" id="title" placeholder="제목을 입력하세요" required
                   value="${board.title}"/>
            <br>


        <span class="regdate"><c:out value="${board.regdate}" /></span>
        <span class="likes">
                <img src="https://ifh.cc/g/aw0vjY.png" alt="Like Icon" style="vertical-align: middle; width: 20px; height: 20px;">
                <c:out value="${board.likes}" />
        </span>
    </div>

    <div class="content">
        <textarea name="content" class="contentStyle" id="content" placeholder="내용을 입력하세요" required>${board.content}</textarea>
        <c:if test="${not empty board.image}">
            <img src="data:image/jpeg;base64,${board.imageBase64}" alt="" width="200" height="200">
        </c:if>

        <div class="image-input">
            <input type="file" name="newImage" id="newImage">

        </div>


        <div class="update-button">
            <button type="submit" class="buttonStyle">등록</button>
        </div>
        </div>
</div>


</form>

</body>
</html>