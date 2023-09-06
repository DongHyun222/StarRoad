<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>POLICY</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/static/css/common.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/static/css/policy.css">
</head>
<body>
    <div id="policy-div-wrap">
        <nav>nav bar</nav>
        <main>
            <div class="title"><h1>청년 금융 정책</h1></div>

            <nav class="navbar bg-body-tertiary">
                <div class="container-fluid">
                    <form class="d-flex" role="search">
                        <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
                        <button class="btn btn-outline-success" type="submit">Search</button>
                    </form>
                </div>
            </nav>

            <div class="policyBox">
                <c:forEach items="${list.iterator()}" var="l">
                    <div class="policy">
                        <div class="name">${l.name}</div>
                        <div class="explain">${l.explain}</div>
                        <div class="tag">${l.tag}</div>
                        <div class="linkBtn"><button><a href="${l.link}">더보기${l.link}</a></button></div>
                    </div>
                </c:forEach>
            </div>

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
        </main>
    </div>
</body>
</html>