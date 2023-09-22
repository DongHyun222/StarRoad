<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>STARROAD</title>
<link rel="icon" href="${path}/resources/static/image/home/logo.png" type="image/x-icon">

<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/static/css/board/error.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">

<!-- Custom CSS -->


</head>
<body>

<div class="card text-center">

    <img src="${path}/resources/static/image/board/error.png" alt="이미지1">

   <div class="card-body">
     <h5 class="card-title">Delete Error</h5>
     <p class="card-text">삭제 할 권한이 없습니다.</p>
     <a href="/starroad/board/main" class="btn btn-primary">메인페이지로 돌아가기</a>
   </div>

</div>

<!-- Bootstrap JS and jQuery -->
<script src="//code.jquery.com/jquery.js"></script>
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

</body>
</html>