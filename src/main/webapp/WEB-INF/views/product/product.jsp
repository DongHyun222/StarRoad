<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>예적금 상품 추천</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="${path}/resources/static/css/common.css"/>
    <link rel="stylesheet" type="text/css" href="${path}/resources/static/css/nav.css">
    <link rel="stylesheet" type="text/css" href="${path}/resources/static/css/product.css">
</head>
<body>
<header>

</header>
<h1 class="fw-bold">예적금 상품 추천</h1>
<div id="product_search">
    <nav class="navbar navbar-light bg-light" id="product_search_nav">
        <div class="container-fluid">
            <form class="d-flex">
                <div>
                    <div class="search_type">상품 유형</div>
                    <div class="dropdown">
                        <a class="btn btn-secondary dropdown-toggle" href="#" role="button" id="dropdownMenuLink"
                           data-bs-toggle="dropdown" aria-expanded="false">
                            상품 유형 선택
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="dropdownMenuLink">
                            <li><a class="dropdown-item" href="#">적금</a></li>
                            <li><a class="dropdown-item" href="#">예금</a></li>
                        </ul>
                    </div>
                </div>

                <div>
                    <div class="search_type">가입 기간</div>
                    <ul id="period">
                        <li>
                            <button class="btn period_btn">1</button>
                        </li>
                        <li>
                            <button class="btn period_btn">2</button>
                        </li>
                        <li>
                            <button class="btn period_btn">3</button>
                        </li>
                        <li>
                            <button class="btn period_btn">4</button>
                        </li>
                    </ul>
                </div>

                <div>
                    <div class="search_type">상품</div>
                    <input class="form-control me-2 search_bar" type="search" placeholder="예적금 상품명을 적어주세요"
                           aria-label="Search">
                    <button class="btn search_btn" type="submit">검색</button>
                </div>

            </form>
        </div>
    </nav>
</div>
<div id="product_list">
    <ul>
        <c:forEach items="${productItems}" var="item">
            <li id="product_item">
                <div id="product" class="content">
                    <div class="sub">
                        <c:choose>
                            <c:when test="${item.type eq 'D'.charAt(0) }">
                                <div class="type">적금</div>
                            </c:when>
                            <c:when test="${item.type eq 'I'.charAt(0)}">
                                <div class="type">예금</div>
                            </c:when>
                        </c:choose>
                        <div class="attribute">${item.attribute}</div>
                    </div>

                    <div class="title">
                        <div class="name">${item.name}</div>
                        <div class="explain">${item.explain}</div>
                    </div>
                    <div class="rate">
                        최고 연 <span class="max_rate"><span>${item.maxRate}</span>%</span> (${item.maxRatePeriod}개월)
                    </div>
                </div>
                <div id="member" class="content">
                    현재 ${user}님의 자산으로 계산된<br>
                    만기 예상 금액은<br>
                    세후 <span>${price}</span>원 입니다.
                </div>
                <div id="detail_button" class="content">
                    <button class=" btn detail_btn"><a href="${item.link}">자세히</a></button>
                </div>

            </li>
        </c:forEach>
    </ul>
</div>
<nav aria-label="Page navigation example">
    <ul class="pagination">
        <li class="page-item">
            <a class="page-link" href="#" aria-label="Previous">
                <span aria-hidden="true">&laquo;</span>
            </a>
        </li>
        <c:forEach begin="1" end="${pageEndIndex}" var="i">
            <li class="page-item"><a class="page-link" href="#" aria-label="${i}" id="${i}_page">${i}</a></li>
        </c:forEach>
        <li class="page-item">
            <a class="page-link" href="#" aria-label="Next">
                <span aria-hidden="true">&raquo;</span>
            </a>
        </li>
    </ul>
</nav>
<script>
    // 페이지 링크 요소를 선택
    let current_page = ${currentPage};
    document.getElementById(current_page+"_page").style.color = "#FFCC00FF";
    document.getElementById(current_page+"_page").style.textDecoration = "underline";
    document.getElementById(current_page+"_page").style.fontWeight = "bold";

    let next = current_page+1;
    let prev = current_page-1;

    // 페이지 링크에 클릭 이벤트 리스너를 추가
    const pageLinks = document.querySelectorAll('.page-link');
    pageLinks.forEach((link) => {
        link.addEventListener('click', (event) => {
            event.preventDefault();
            if (link.getAttribute('aria-label') === 'Previous') {
                if (current_page > 1) {
                    window.location.href = '/starroad/product?page=' + prev;
                }
            } else if (link.getAttribute('aria-label') === 'Next') {
                if (parseInt(${pageEndIndex}) > current_page) {
                    window.location.href = '/starroad/product?page=' + next;
                }
            } else {
                window.location.href = '/starroad/product?page=' + link.getAttribute('aria-label');
            }
        });
    });
</script>
</body>
</html>