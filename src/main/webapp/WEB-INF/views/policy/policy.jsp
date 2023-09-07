<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>POLICY</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/static/css/common.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/static/css/policy.css">
</head>
<body>
    <div id="policy-div-wrap">
        <nav>nav bar</nav>
        <main>
            <div class="title"><h1>청년 금융 정책</h1></div>

            <br>

            <nav class="navbar bg-body-tertiary">
                <div class="container-fluid">
                    <form class="d-flex" role="search">
                        <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
                        <button class="btn btn-outline-success" type="submit">Search</button>
                    </form>
                </div>
            </nav>

            <br>

            <div class="policyBox">
                <c:forEach items="${policyList}" var="item">
                    <div class="policy">
                        <div class="name">${item.name}</div>
                        <div class="explain">${item.explain}</div>
                        <div class="tag">#${item.tag}</div>
                        <div class="btnDiv"><button class="linkBtn"><a href="${item.link}">더보기</a></button></div>
                    </div>
                </c:forEach>
            </div>

            <br>
            <!--
            <nav aria-label="Page navigation example">
                <ul id="dyn_ul" class="pagination">
                    <li class="page-item">
                        <a class="page-link" href="#" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>
                    <li class="page-item"><a class="page-link" href="/starroad/policy?pageIndex=0">1</a></li>
                    <li class="page-item"><a class="page-link" href="/starroad/policy?pageIndex=1">2</a></li>
                    <li class="page-item"><a class="page-link" href="/starroad/policy?pageIndex=2">3</a></li>
                    <li class="page-item"><a class="page-link" href="/starroad/policy?pageIndex=3">4</a></li>
                    <li class="page-item"><a class="page-link" href="/starroad/policy?pageIndex=4">5</a></li>
                    <li class="page-item"><a class="page-link" href="/starroad/policy?pageIndex=5">6</a></li>
                    <li class="page-item"><a class="page-link" href="/starroad/policy?pageIndex=6">7</a></li>
                    <li class="page-item"><a class="page-link" href="/starroad/policy?pageIndex=7">8</a></li>
                    <li class="page-item">
                        <a class="page-link" href="#" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                </ul>
            </nav>
            -->

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

        </main>
    </div>

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
                        window.location.href = '/starroad/policy?pageIndex=' + prev;
                    }
                } else if (link.getAttribute('aria-label') === 'Next') {
                    if (parseInt(${pageEndIndex}) > current_page) {
                        window.location.href = '/starroad/policy?pageIndex=' + next;
                    }
                } else {
                    window.location.href = '/starroad/policy?pageIndex=' + link.getAttribute('aria-label');
                }
            });
        });
    </script>

</body>
</html>