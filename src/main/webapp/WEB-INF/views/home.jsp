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
    <link rel="stylesheet" href="https://unpkg.com/aos@next/dist/aos.css"/>
    <script src="https://unpkg.com/aos@next/dist/aos.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/fullPage.js/4.0.20/fullpage.min.js"
            integrity="sha512-LGiXf+jHGTHcIybSsOWO3I/in+OObCkcEsWIZ7IyhzfD6RzD5qDUw2CK+JveuI7zTSEcDG//bIOvOpAJW2BWsg=="
            crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <script src="//code.jquery.com/jquery-3.6.0.min.js"></script>
    <script type="text/javascript">
        $(function () {
            AOS.init();

            $("#navbar").load("${path}/resources/common_jsp/navbar.jsp");

            window.addEventListener("wheel", function (e) {
                e.preventDefault();
            }, {passive: false});

            var mHtml = $("html");
            var page = 1;
            mHtml.animate({scrollTop: 0}, 10);
            $(window).on("wheel", function (e) {
                if (mHtml.is(":animated")) return;
                if (e.originalEvent.deltaY > 0) {
                    if (page == 5) return;
                    page++;
                } else if (e.originalEvent.deltaY < 0) {
                    if (page == 1) return;
                    page--;
                }
                var posTop = (page - 1) * $(window).height();
                mHtml.animate({scrollTop: posTop});
            })
        });
    </script>
    <title>청년을 위한 금융 서비스 STARROAD</title>
</head>

<body>
<div id="navbar"></div>
<div class="section" id="section1">
    <div class="content">
        <div class="title" data-aos="fade-up" data-aos-duration="3000">
            STAR ROAD
        </div>
    </div>
    <div class="scroll">
        <img src="${path}/resources/static/image/home/scroll.png">
    </div>
    <%--    </div>--%>
</div>
<div class="section" id="section2">
    <%--    <div class="page_container">--%>
    <div class="content">
        <div>
            <div class="title">
                STAR ROAD
            </div>
            <div id="section2_detail" class="detail" data-aos="fade-up" data-aos-duration="3000">
                한줄설명
            </div>
        </div>
    </div>
    <div class="scroll">
        <img src="${path}/resources/static/image/home/scroll.png">
    </div>
    <%--    </div>--%>
</div>
<div class="section" id="section3">
    <div class="page_container">
        <div>
            <div class="title" data-aos="fade-up" data-aos-duration="3000">
                예적금 상품 추천
            </div>
            <div id="section3_detail" class="detail" data-aos="fade-up" data-aos-duration="2000">
                다양한 국민은행 상품을 추천받아요.<br>만기해지 시 예상 금액을 볼 수 있어요
            </div>
            <button class="main_btn" data-aos="fade-up" data-aos-duration="3000"><a href="#">상품 추천 받기</a></button>
        </div>
        <img src="${path}/resources/static/image/home/section3.gif" class="main_3d_img" alt="...">
    </div>
</div>
<div class="section" id="section4">
    <div class="page_container">
        <div>
            <img id="section4_img_chart" src="${path}/resources/static/image/home/section4_1.png" class="main_3d_img" alt="...">
            <img id="section4_img_plant" src="${path}/resources/static/image/home/section4_2.png" class="main_3d_img" alt="...">
        </div>
        <div>
            <div class="title" data-aos="fade-up" data-aos-duration="3000">
                My Asset & Challenge
            </div>
            <div id="section4_detail" class="detail" data-aos="fade-up" data-aos-duration="2000">
                나의 자산 상태를 차트로 파악하고,<br>
                적금 유지하여 포인트리를 받아요.
            </div>
            <button class="main_btn" data-aos="fade-up" data-aos-duration="3000"><a href="#">자산 상태 보러가기</a></button>
        </div>
    </div>
</div>
<div class="section" id="section5">
    <div class="page_container">
        <div>
            <div class="title" data-aos="fade-up" data-aos-duration="3000">
                Policy
            </div>
            <div id="section5_detail" class="detail" data-aos="fade-up" data-aos-duration="3000">
                청년을 위한 금융 정책을 알아보아요
            </div>
            <button class="main_btn" data-aos="fade-up" data-aos-duration="3000"><a href="#">청년 정책 보러가기</a></button>
        </div>
        <img src="${path}/resources/static/image/home/section5_1.gif" class="main_3d_img" alt="...">
    </div>
</div>
</body>
</html>