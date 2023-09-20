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
    <title>STARROAD</title>
</head>

<body>
<div id="navbar"></div>
<!-- 본문 내용 -->
<div class="section" id="section1">
    <div class="title" data-aos="fade-up" data-aos-duration="3000">
        인류의 지속가능한 미래를 <br>
        선도하는 글로벌 비지니스 리더
    </div>
    <div id="section1_detail" class="detail" data-aos="fade-up" data-aos-duration="2000">
        디테일한 정보
    </div>
</div>
<div class="section" id="section2">
    <div class="title" data-aos="fade-up" data-aos-duration="3000">
        머시기머시기 머시기<br>
        lorem
    </div>
    <div id="section2_detail" class="detail" data-aos="fade-up" data-aos-duration="2000">
        Lorem ipsum dolor sit amet, consectetur adipisicing elit. Alias deleniti exercitationem explicabo magni nisi pariatur perspiciatis placeat quasi tenetur ut. Aliquam consectetur doloribus enim eveniet maiores modi officiis sit, temporibus?
    </div>


</div>
<div class="section" id="section3">
    <div class="title" data-aos="fade-up" data-aos-duration="3000">
        블라블라블라<br>
        lorem
    </div>
    <div id="section3_detail" class="detail" data-aos="fade-up" data-aos-duration="2000">
        Lorem ipsum dolor sit amet, consectetur adipisicing elit. Alias deleniti exercitationem explicabo magni nisi pariatur perspiciatis placeat quasi tenetur ut. Aliquam consectetur doloribus enim eveniet maiores modi officiis sit, temporibus?
    </div>
</div>
<div class="section" id="section4">
    <div class="title" data-aos="fade-up" data-aos-duration="3000">
        라라랄ㄹ라<br>
        f라라ㅏ라라라라ㅏ라라
    </div>
    <div id="section4_detail" class="detail" data-aos="fade-up" data-aos-duration="2000">
        Lorem ipsum dolor sit amet, consectetur adipisicing elit. Alias deleniti exercitationem explicabo magni nisi pariatur perspiciatis placeat quasi tenetur ut. Aliquam consectetur doloribus enim eveniet maiores modi officiis sit, temporibus?
    </div>
</div>
</body>
</html>