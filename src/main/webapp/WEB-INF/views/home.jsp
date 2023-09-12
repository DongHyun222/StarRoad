<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="${path}/resources/static/css/common.css">
    <link rel="stylesheet" type="text/css" href="${path}/resources/static/css/home/home.css">
    <link rel="stylesheet" type="text/css" href="${path}/resources/static/css/nav.css">
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
<%--<div class="container">--%>
<div id="carouselExampleAutoplaying" class="carousel slide" data-bs-ride="carousel">
    <%--    <ol class="carousel-indicators">--%>
    <%--        <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>--%>
    <%--        <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>--%>
    <%--        <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>--%>
    <%--    </ol>--%>
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

<div class="content_title">
    <h6>내게 맞는 금융상품, 비교하기 힘드시죠?</h6>
    <h3>스타로드는 쉽고 정확하게 비교합니다.</h3>
</div>
<div class="bottom-section">
    <div class="section">
        <img src="${path}/resources/static/image/home/bank.gif" alt="이미지1">
        <p>국민 은행 내의 금융상품을 추천한다</p>
    </div>
    <div class="section">
        <img src="${path}/resources/static/image/home/piggy-bank.gif" alt="이미지2">
        <p>예적금을 지속할 수 있도록 돕는다 챌린지</p>
    </div>
    <div class="section">
        <img src="${path}/resources/static/image/home/cheque.gif" alt="이미지3">
        <p>청년 정책 및 청년에게 필요한 금융지식을 제공한다</p>
    </div>
</div>
</body>
</html>