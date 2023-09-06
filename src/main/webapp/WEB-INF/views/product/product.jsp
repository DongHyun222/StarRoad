<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>예적금 상품 추천</title>
    <link rel="stylesheet" type="text/css" href="${path}/resources/static/css/product.css">
</head>
<body>
<h1>예적금 상품 추천</h1>
<div id="product_search"></div>
<div id="product_list">
    <ul>
        <c:forEach items="${product_items}" var="item">
            <li id="product_item">
                <c:choose>
                    <c:when test="${item.type eq 'D'.charAt(0) }">
                        <div class="type">적금</div>
                    </c:when>
                    <c:when test="${item.type eq 'I'.charAt(0)}">
                        <div class="type">예금</div>
                    </c:when>
                </c:choose>
                <div class="title">
                    <div class="name">${item.name}</div>
                    <div class="explain">${item.explain}</div>
                </div>
                <div class="attribute">${item.attribute}</div>


                <div class="attribute">${item.attribute}</div>
            </li>
        </c:forEach>
    </ul>
</div>
</body>
</html>