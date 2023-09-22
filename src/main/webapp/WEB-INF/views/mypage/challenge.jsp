<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>mypage</title>
    <link rel="stylesheet" href="${path}/resources/static/css/common.css">
    <link rel="stylesheet" href="${path}/resources/static/css/mypage/sidebar.css">
    <link rel="stylesheet" href="${path}/resources/static/css/mypage/challenge.css">
    <!-- jquery 링크, navbar -->
    <script src="//code.jquery.com/jquery-3.6.0.min.js"></script>
    <script type="text/javascript">
        $(function () {
            let name = ""
            let ss_no = 0
            let period = 0
            $("#navbar").load("${path}/resources/common_jsp/navbar.jsp")

            $("#sel_sub").change(function () {
                let idx = document.getElementById("sel_sub").selectedIndex - 1
                let subscriptions = "${subscriptions}".split("SubscriptionDto(")
                subscriptions.shift()
                let paymentLogs = "${paymentLogs}".substring(2, "${paymentLogs}".length - 2).split("], [")

                let sub = subscriptions[idx].split(", ")
                ss_no = sub[0].split("=")[1]
                $("#ss_no_val").attr("value", ss_no)
                name = sub[21].split("=")[1]
                $("#sub_name").text(name)
                $("#name_val").attr("value", name)
                $("#sub_attr").text(sub[23].split("=")[1])
                $("#sub_exp").text(sub[22].split("=")[1])
                period = sub[32].split("=")[1]
                $("#period_val").attr("value", period)
                $("#sub_period").text(period + "개월")
                $("#sub_price").text(sub[33].split("=")[1].split(")")[0] * 0.1 + "만원")

                let logs = paymentLogs[idx].split(", ")
                let status = logs.pop()
                const fl_cont = document.querySelector('#flower_container')
                fl_cont.innerHTML = ''
                logs.forEach((fl) => {
                    let flower = document.createElement("img")
                    flower.src = "${path}/resources/static/image/mypage/flowers/" + fl + ".png"
                    flower.width = 100
                    flower.height = 100
                    flower.style.margin = "10px 5px"
                    document.querySelector('#flower_container').appendChild(flower)
                })

                $("#sub_info_s").css("display", "block")
                $("#flower_container").css({"display": "flex", "visibility": "visible"})
                $("#sel_pic").css("display", "none")
                $("#pic_exp").css("display", "none")

                if (status === "-1") {          // 성공
                    $("#reward_btn").text("리워드를 받으세요 🥳")
                        .attr("disabled", false)
                        .css({
                            "display": "block",
                            "background": "var(--main-kb-yellow-positive)",
                            "color": "black",
                            "cursor": "pointer"
                        })
                } else if (status === "-2") {   // 리워드를 이미 받았을 때, 끝났을 때
                    $("#reward_btn").text("완주 성공! 😎")
                        .attr("disabled", true)
                        .css({
                            "display": "block",
                            "background": "var(--sub-kb-gold)",
                            "color": "white",
                            "cursor": "unset"
                        })
                } else {
                    $("#reward_btn").text("").append("<strong>" + status + "</strong>" + "개월 남았어요 💪")
                        .attr("disabled", true)
                        .css({
                            "display": "block",
                            "background": "var(--sub-kb-gold)",
                            "color": "white",
                            "cursor": "unset"
                        })
                }
            })
        });
    </script>
</head>
<div id="navbar"></div>
<main>
    <aside>
        <div id='sidebar_title'>마이페이지</div>
        <ul>
            <li><a class='sidebar_menu' href='/starroad/mypage/asset'>나의 자산</a></li>
            <li><a class='sidebar_menu' href='/starroad/mypage/challenge' id='selected'>적금 챌린지</a></li>
            <li><a class='sidebar_menu' href='/starroad/mypage/board'>작성한 글 보기</a></li>
            <li><a class='sidebar_menu' href='/starroad/mypage/info'>정보 수정</a></li>
            <li><a class='sidebar_menu' href='/starroad/mypage/password'>비밀번호 수정</a></li>
        </ul>
    </aside>
    <article id="sub_article">
        <div>
            <select name="subscription" id="sel_sub">
                <option disabled selected>가입하신 적금을 선택해주세요</option>
                <c:forEach items="${subscriptions}" var="subscription" varStatus="status">
                    <option value="${status.index}">&nbsp;${subscription.prod.name}</option>
                </c:forEach>
            </select>

            <section id="sel_pic_exp">
                <img id="sel_pic" src="${path}/resources/static/image/mypage/sel_pic.png">
                <div id="pic_exp">가입하신 적금을 선택해주세요</div>
            </section>

            <section id="sub_info_s">
                <div id="sub_na_c">
                    <span id="sub_name"></span>
                    <span id="sub_attr"></span>
                </div>
                <div id="sub_be_c">
                    <div id="bef_exp"></div>
                    <div id="sub_exp"></div>
                </div>
                <div id="sub_pp_c">
                    <div id="sub_period"></div>
                    <div id="sub_price"></div>
                </div>
            </section>

            <section>
                <div id="flower_container"></div>
            </section>

            <form action="/starroad/mypage/reward" method="post">
                <input type="hidden" id="name_val" name="name">
                <input type="hidden" id="period_val" name="period">
                <input type="hidden" id="ss_no_val" name="sub_no">
                <button id="reward_btn"></button>
            </form>
        </div>
    </article>
</main>
</html>