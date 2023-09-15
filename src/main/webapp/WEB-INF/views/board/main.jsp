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
    <link rel="stylesheet" type="text/css" href="${path}/resources/static/css/home/home.css">
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
            <div class="banner_container">
                <div>
                    <div class="banner_title">
                        <h2>사용자 맞춤 금융상품</h2>
                    </div>
                    <p class="banner_content">
                        사용자가 설정한 값에 따라서 <br>
                        만기예상금액이 자동으로 계산되어 <br>
                        모든 금융 상품을 볼 수 있습니다.
                    </p>
                </div>
                <img src="${path}/resources/static/image/home/animated1.gif" class="banner_3d_img" alt="...">
            </div>
            <img src="${path}/resources/static/image/home/banner1.png" class="d-block w-100" alt="...">
        </div>
        <div class="carousel-item" data-bs-interval="3000">
            <div class="banner_container">
                <div>
                    <div class="banner_title">
                        <h2>사용자 맞춤 금융상품</h2>
                    </div>
                    <p class="banner_content">
                        사용자가 설정한 값에 따라서 <br>
                        만기예상금액이 자동으로 계산되어 <br>
                        모든 금융 상품을 볼 수 있습니다.
                    </p>
                </div>
                <img src="${path}/resources/static/image/home/animated1.gif" class="banner_3d_img" alt="...">
            </div>
            <img src="${path}/resources/static/image/home/banner2.png" class="d-block w-100" alt="...">
        </div>
        <div class="carousel-item" data-bs-interval="3000">
            <div class="banner_container">
                <div>
                    <div class="banner_title">
                        <h2>사용자 맞춤 금융상품</h2>
                    </div>
                    <p class="banner_content">
                        사용자가 설정한 값에 따라서 <br>
                        만기예상금액이 자동으로 계산되어 <br>
                        모든 금융 상품을 볼 수 있습니다.
                    </p>
                </div>
                <img src="${path}/resources/static/image/home/animated1.gif" class="banner_3d_img" alt="...">
            </div>
            <img src="${path}/resources/static/image/home/banner3.png" class="d-block w-100" alt="...">
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
                    <p><span>${board.detailType}</span>
                    <a href ="/starroad/board/detail?no=${board.no}">${board.title} </a></p>
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
                    <p><span>${board.detailType}</span>
                    <a href ="/starroad/board/detail?no=${board.no}">${board.title} </a></p>
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
                    <p><span>${board.detailType}</span>
                    <a href ="/starroad/board/detail?no=${board.no}">${board.title} </a></p>
                </c:forEach>
            </div>
        </div>
    </div>
</body>
</html>