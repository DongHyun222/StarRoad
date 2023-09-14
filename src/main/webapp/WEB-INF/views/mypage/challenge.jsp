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
            $("#navbar").load("${path}/resources/common_jsp/navbar.jsp")

            $("#sel_sub").change(function () {
                let sel_sub_name = $("#sel_sub").val().trim()
                <%--console.log(typeof "${subscriptions}")  // String--%>
                let arr = "${subscriptions}".substring(1, "${subscriptions}".length - 1).split("SubscriptionDto")
                arr.shift()
                arr.forEach(function (sub) {
                    let prodSub = sub.split("ProductDto")[1].split(", ")
                    let name = prodSub[2].split("=")[1]
                    if (name === sel_sub_name) {
                        $("#sub_name").text(name)
                        $("#sub_attr").text(prodSub[4].split("=")[1])
                        $("#sub_exp").text(prodSub[3].split("=")[1])
                        $("#sub_period").text(prodSub[13].split("=")[1] + "개월")
                        $("#sub_price").text(prodSub[14].split("=")[1].substring(0, prodSub[14].split("=")[1].length - 2) + "만원")
                    }
                })
                $("#sub_info_s").css("display", "block")
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
    <article>
        <select name="subscription" id="sel_sub">
            <option disabled selected>가입하신 적금을 선택해주세요</option>
            <c:forEach items="${subscriptions}" var="subscription">
                <option>&nbsp;${subscription.prod.name}</option>
            </c:forEach>
        </select>
        <section id="sub_info_s">
            <div id="sub_na_c">
                <span id="sub_name"></span> <span id="sub_attr"></span>
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

    </article>
</main>
</html>