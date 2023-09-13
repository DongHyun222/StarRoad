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
            $("#navbar").load("${path}/resources/common_jsp/navbar.jsp");

            $("#sel_sub").blur(function () {
                $.ajax({
                    type: 'post',
                    url: "/api/starroad/mypage/subInfo",
                    data: {"sub": $("#sel_sub").val()},
                    success: function (data) {
                        // alert(data);
                        alert("${subscriptions}");
                    },
                    error: function (error) {
                        alert("잠시 후 시도해주세요.");
                    }
                });
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
            <c:forEach items="${subscriptions}" var="subscription">
                <option>&nbsp;${subscription.prod.name}</option>
            </c:forEach>
        </select>
        <section id="sub_info_s">
            <div id="sub_name"></div>
            <div id="sub_type"></div>
            <div id="sub_attr"></div>

            <div id="sub_pay"></div>
            <div id="sub_period"></div>


            <div id="sub_exp"></div>

        </section>

    </article>
</main>
</html>