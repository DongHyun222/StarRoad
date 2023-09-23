<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>STARROAD</title>
    <link rel="icon" href="${path}/resources/static/image/home/logo1.png" type="image/x-icon">
</head>
<body>
<h1>Board List</h1>

<c:forEach var="board" items="${boardList}">
    <div>
        <p>No: <c:out value="${board.no}" /></p>
        <p>Title: <c:out value="${board.title}" /></p>
        <p>Reg Date: <c:out value="${board.regdate}" /></p>
        <!-- 다른 필드들을 필요에 따라 추가할 수 있습니다. -->
    </div>
</c:forEach>
</body>
</html>
