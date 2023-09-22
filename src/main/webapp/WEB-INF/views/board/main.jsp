<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="${path}/resources/static/css/common.css">
    <link rel="stylesheet" type="text/css" href="${path}/resources/static/css/nav.css">
    <link rel="stylesheet" type="text/css" href="${path}/resources/static/css/board/board1.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
    <title>Starroad</title>
    <script src="//code.jquery.com/jquery-3.6.0.min.js"></script>

    <script type="text/javascript">
        $(function () {
            $("#navbar").load("${path}/resources/common_jsp/navbar.jsp");
        });
    </script>


</head>

<body>
<div id="navbar"></div>
<!-- 본문 내용 -->
<div id="carouselExampleAutoplaying" class="carousel slide" data-bs-ride="carousel">
    <div class="carousel-inner">
        <div class="carousel-item active" data-bs-interval="3000">
            <img src="${path}/resources/static/image/board/main/ad_1.png" class="d-block w-100" alt="...">
        </div>
        <div class="carousel-item" data-bs-interval="3000">
            <img src="${path}/resources/static/image/board/main/ad_2.png" class="d-block w-100" alt="...">
        </div>
        <div class="carousel-item" data-bs-interval="3500">
            <img src="${path}/resources/static/image/board/main/ad_3.png" class="d-block w-100" alt="...">
            <div class="carousel-caption d-none d-md-block">
                <div class="banner_title">
                    <h3> 신상품 출시! </h3>
                    <a class="bounce" href="https://otalk.kbstar.com/quics?page=C019392&cc=b032271:b032271"><h1>「KB청년도약계좌」</h1></a>
                </div>
            </div>
        </div>
    </div>
    <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleAutoplaying"
            data-bs-slide="prev">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Previous</span>
    </button>
    <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleAutoplaying"
            data-bs-slide="next">
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Next</span>
    </button>
</div>

<div class="board-box">
    <div class="board">
        <div class="board-header">
            <img src="${path}/resources/static/image/board/popular1.png" alt="인기글">
            <h2>인기게시판</h2>
            <a href="/starroad/board/popular" class="board-detail">더보기 ></a>
        </div>
        <div class="board-list">
            <c:forEach items="${popularBoard.content}" var="board" begin="0" end="5">
                <p>
                <div class="detailType">${board.detailType}</div>
                <a href="/starroad/board/detail?no=${board.no}">${board.title} </a>

                <div class="right-align">
                    <img src="${path}/resources/static/image/board/likes.png" alt="좋아요" class="like-icon">
                        ${board.likes}
                </div>
                </p>
            </c:forEach>
        </div>
    </div>

    <div class="board">
        <div class="board-header">
            <img src="${path}/resources/static/image/board/talk.png" alt="자유게시글">
            <h2>자유게시판</h2>
            <a href="/starroad/board/free?type=F" class="board-detail">더보기 ></a>
        </div>
        <div class="board-list">
            <c:forEach items="${freeBoard.content}" var="board" begin="0" end="5">
                <p>
                <div class="detailType">${board.detailType}</div>
                <a href="/starroad/board/detail?no=${board.no}">${board.title} </a>

                <div class="right-align">
                    <img src="${path}/resources/static/image/board/likes.png" alt="좋아요" class="like-icon">
                        ${board.likes}
                </div>
                </p>
            </c:forEach>
        </div>
    </div>

    <div class="board">
        <div class="board-header">
            <img src="${path}/resources/static/image/board/auth.png" alt="인증게시글">
            <h2>인증게시판</h2>
            <a href="/starroad/board/free?type=C" class="board-detail">더보기 ></a>
        </div>
        <div class="board-list">
            <c:forEach items="${authBoard.content}" var="board" begin="0" end="5">
                <p>
                <div class="detailType">${board.detailType}</div>
                <a href="/starroad/board/detail?no=${board.no}">${board.title} </a>

                <div class="right-align">
                    <img src="${path}/resources/static/image/board/likes.png" alt="좋아요" class="like-icon">
                        ${board.likes}
                </div>
                </p>
            </c:forEach>
        </div>
    </div>
</div>

</body>
</html>