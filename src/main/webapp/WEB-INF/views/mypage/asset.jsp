<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
        $(function () {
            $("#navbar").load("${path}/resources/common_jsp/navbar.jsp");

            if (${memberAssets.savings}+${memberAssets.deposit}+${memberAssets.investment} === 0) {
                $("#asset").css("display", "none")
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
        <h1 id="asset_title">${memberAssets.name}님의 자산</h1>
        <div id="asset_container">
            <div id="asset_div">
                <section>
                    <div>
                        <canvas id="myChart"></canvas>
                    </div>
                    <div id="info">등록된 자산이 없습니다.</div>
                </section>

                <section id="asset_info">
                    <table id="asset_info_table">
                        <tbody>
                        <tr>
                            <td>포인트리</td>
                            <td>${memberAssets.point}P</td>
                        </tr>
                        <tr>
                            <td>
                                <div id="deposit_color" class="color_class"></div>
                                예금
                            </td>
                            <td><fmt:parseNumber value="${memberAssets.deposit*0.1}" integerOnly="true"/>만원</td>
                        </tr>
                        <tr>
                            <td>
                                <div id="savings_color" class="color_class"></div>
                                적금
                            </td>
                            <td><fmt:parseNumber value="${memberAssets.savings*0.1}" integerOnly="true"/>만원</td>
                        </tr>
                        <tr>
                            <td>
                                <div id="invest_color" class="color_class"></div>
                                투자금
                            </td>
                            <td><fmt:parseNumber value="${memberAssets.investment*0.1}" integerOnly="true"/>만원</td>
                        </tr>
                        </tbody>
                    </table>
                </section>
            </div>
        </div>
    </article>

    <!-- 차트 -->
    <script>
        data = {
            datasets: [{
                backgroundColor: ['#FFBC00', '#545045', '#8D744A'],
                data: [${memberAssets.savings*0.1}, ${memberAssets.deposit*0.1}, ${memberAssets.investment*0.1}]
            }],
            labels: ['적금', '예금', '투자금']
        };

        const ctx = document.getElementById("myChart");
        const myDoughnutChart = new Chart(ctx, {
            type: 'doughnut',
            data: data,
            options: {
                responsive: false,
                plugins: {
                    legend: false, // 범례 숨기기
                    datalabels: {
                        display: false // 데이터 레이블 숨기기
                    },
                }
            }
        });
    </script>
</main>
</body>
</html>