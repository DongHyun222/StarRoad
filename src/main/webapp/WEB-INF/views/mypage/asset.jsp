<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>mypage</title>
    <link rel="stylesheet" href="${path}/resources/static/css/common.css">
    <link rel="stylesheet" href="${path}/resources/static/css/mypage/sidebar.css">
    <link rel="stylesheet" href="${path}/resources/static/css/mypage/asset.css">
    <!-- 차트 링크 -->
    <script src="https://cdn.jsdelivr.net/npm/chart.js@4.4.0"></script>
    <!-- jquery 링크, navbar -->
    <script src="//code.jquery.com/jquery-3.6.0.min.js"></script>
    <script type="text/javascript">
        $(function() {
            $("#navbar").load("${path}/resources/common_jsp/navbar.jsp");

            if (${memberAssets.savings}+${memberAssets.deposit}+${memberAssets.investment}===0) {
                $("#asset").css("display","none")
                $("#info").css("display", "block")
            }
        });
    </script>
</head>
<body>
    <div id="navbar"></div>
        <main>
        <aside>
            <div id='sidebar_title'>마이페이지</div>
            <ul>
                <li><a class='sidebar_menu' href='/starroad/mypage/asset' id='selected'>나의 자산</a></li>
                <li><a class='sidebar_menu' href='/starroad/mypage/challenge'>적금 챌린지</a></li>
                <li><a class='sidebar_menu' href='/starroad/mypage/board'>작성한 글 보기</a></li>
                <li><a class='sidebar_menu' href='/starroad/mypage/info'>정보 수정</a></li>
                <li><a class='sidebar_menu' href='/starroad/mypage/password'>비밀번호 수정</a></li>
            </ul>
        </aside>
        <article>
            <section id="pointree">
                <span>${memberAssets.name}님의 포인트리</span>
                <span>${memberAssets.point}P</span>
            </section>
            <section id="asset">
                <canvas id="myChart"></canvas>
            </section>
            <section id="info">
                등록된 자산이 없습니다.
            </section>
        </article>

        <!-- 차트 -->
        <script>
            data = {
                datasets: [{
                    backgroundColor: ['#FFBC00','#545045','#8D744A'],
                    data: [${memberAssets.savings*0.1}, ${memberAssets.deposit*0.1}, ${memberAssets.investment*0.1}]
                }],
                labels: ['적금','예금','투자금']
            };

            const ctx = document.getElementById("myChart");
            const myDoughnutChart = new Chart(ctx, {
                type: 'doughnut',
                data: data,
                options: {
                    responsive: false
                }
            });
        </script>
    </main>
</body>
</html>