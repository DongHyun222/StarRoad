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
</head>
<body>
    <nav></nav>
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
                <span>${member.name}님의 포인트리</span>
                <span>${member.point}P</span>
            </section>
            <section id="asset">
                <canvas id="myChart"></canvas>
            </section>
        </article>

        <!-- 차트 -->
        <script>
            data = {
                datasets: [{
                    backgroundColor: ['#FFBC00','#545045','#8D744A'],
                    data: [1510, 1000, ${member.investment}]
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