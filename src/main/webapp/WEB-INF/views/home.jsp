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
                    if (page == 4) return;
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
    <div class="title" data-aos="fade-up" data-aos-duration="3000">
        내게 맞는 금융상품, 비교하기 힘드시죠?
    </div>
    <div id="section1_detail" class="detail" data-aos="fade-up" data-aos-duration="2000">
        사용자가 설정한 값에 따라서 <br>
        만기예상금액이 자동으로 계산되어 <br>
        모든 금융 상품을 볼 수 있습니다
    </div>
</div>
<div class="section" id="section2">
    <div class="page_container">
        <div>
            <div class="title" data-aos="fade-up" data-aos-duration="3000">
                청년을 위한 금융 혜택을 한눈에!
            </div>
            <div id="section2_detail" class="detail" data-aos="fade-up" data-aos-duration="2000">
                국민 은행의 추천 금융 상품을 확인하세요
            </div>
        </div>
        <img src="${path}/resources/static/image/home/animated1.gif" class="banner_3d_img" alt="...">
    </div>
</div>
<div class="section" id="section3">
    <div class="page_container">
        <img src="${path}/resources/static/image/home/animated1.gif" class="banner_3d_img" alt="...">
        <div>
            <div class="title" data-aos="fade-up" data-aos-duration="3000">
                지속 가능한 예적금 챌린지!
            </div>
            <div id="section3_detail" class="detail" data-aos="fade-up" data-aos-duration="2000">
                더 나은 재정 건강을 위한 여정을 시작하세요
            </div>
        </div>
    </div>
</div>
<div class="section" id="section4">
    <div class="page_container">
        <div>
            <div class="title" data-aos="fade-up" data-aos-duration="3000">
                청년을 위한 금융 혜택을 한눈에!
            </div>
            <div id="section4_detail" class="detail" data-aos="fade-up" data-aos-duration="2000">
                국민 은행의 추천 금융 상품을 확인하세요
            </div>
        </div>
        <img src="${path}/resources/static/image/home/animated1.gif" class="banner_3d_img" alt="...">
    </div>
</div>
</body>
</html>