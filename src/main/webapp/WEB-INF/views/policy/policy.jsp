<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>STARROAD</title>
    <link rel="icon" href="${path}/resources/static/image/home/logo1.png" type="image/x-icon">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
    <link rel="stylesheet" href="https://unpkg.com/aos@next/dist/aos.css"/>
    <script src="https://unpkg.com/aos@next/dist/aos.js"></script>
    <script type="text/javascript">
        $(function () {
            AOS.init();
            $("#navbar").load("${pageContext.request.contextPath}/resources/common_jsp/navbar.jsp");
        })
    </script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/static/css/common.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/resources/static/css/policy/policy.css">
</head>
<body>
<div id="policy-div-wrap">
    <div id="navbar"></div>
    <div class="title">청년 금융 정책</div>
    <main>
        <div id="main_box">
            <div class="search_box bg-light">
                <form name="policyForm" id="policyForm" class="search_form" method="get"
                      action="${pageContext.request.contextPath}/starroad/policy/result">
                    <div class="search_small_box">
                        <div class="search_box_title">지역</div>
                        <div class="search_box_option">
                            <select class="location_select_box" name="location">
                                <option disabled selected>선택해주세요</option>
                                <option id="location_서울" value="서울">서울</option>
                                <option id="location_경기" value="경기">경기</option>
                                <option id="location_중앙부처" value="중앙부처">중앙부처</option>
                            </select>
                        </div>
                    </div>

                    <div class="search_small_box">
                        <div class="search_box_title">태그</div>
                        <div class="search_box_option">

                            <ul class="ks-cboxtags">
                                <li><input type="checkbox" id="금융지원" name="tag1" value="금융지원"><label
                                        for="금융지원">금융지원</label></li>
                                <li><input type="checkbox" id="교육" name="tag2" value="교육"><label for="교육">교육</label>
                                </li>
                                <li><input type="checkbox" id="생활지원" name="tag3" value="생활지원"><label
                                        for="생활지원">생활지원</label></li>
                                <li><input type="checkbox" id="금융자산" name="tag4" value="금융자산"><label for="금융자산">금융자산
                                    형성</label></li>
                            </ul>

                        </div>
                    </div>

                    <div class="search_small_box">
                        <div class="search_box_title">정책명</div>
                        <div class="search_box_option">
                            <input class="search_input form-control me-2 search_bar" name="keyword" id="keyword"
                                   type="text" placeholder="키워드를 입력해주세요">
                            <button id="final" class="submit_btn" type="submit">검색</button>
                        </div>
                    </div>

                </form>
            </div>

            <div class="policy_box">
                <c:forEach items="${policyList}" var="item" varStatus="status">
                    <div class="policy" data-aos="fade-up" data-aos-delay="${200*status.index}" data-aos-duration="400">
                        <c:choose>
                            <c:when test="${currentUser ne null}">
                                <c:if test="${item.isLiked eq 'Y'}">
                                    <div class="like">💛</div>
                                </c:if>
                                <c:if test="${item.isLiked eq 'N'}">
                                    <form id="likeForm" method="post" action="/starroad/policy">
                                        <input type="hidden" name="policyNo" value="${item.no}">
                                        <input type="hidden" name="pageIndex" value="${currentPage}">
                                        <div class="like" id="heart_icon">🤍</div>
                                    </form>
                                </c:if>
                            </c:when>
                            <c:otherwise>
                                <div class="like"></div>
                            </c:otherwise>
                        </c:choose>
                        <div class="name">${item.name}</div>
                        <div class="explain">${item.explain}</div>
                        <div class="tag">#${item.tag}</div>
                        <div class="btn_div">
                            <button class="link_btn"><a href="${item.link}">더보기</a></button>
                        </div>
                    </div>
                </c:forEach>
            </div>

            <div aria-label="Page navigation example">
                <ul class="pagination">
                    <li class="page-item">
                        <a class="page-link" href="#" aria-label="Previous">
                            <span aria-hidden="true">&lt;</span>
                        </a>
                    </li>
                    <c:forEach begin="1" end="${pageEndIndex}" var="i">
                        <li class="page-item"><a class="page-link" href="#" aria-label="${i}" id="${i}_page">${i}</a>
                        </li>
                    </c:forEach>
                    <li class="page-item">
                        <a class="page-link" href="#" aria-label="Next">
                            <span aria-hidden="true">&gt;</span>
                        </a>
                    </li>
                </ul>
            </div>
        </div>

    </main>
</div>

<script>
    // 페이지 링크 요소를 선택
    let current_page = ${currentPage};
    document.getElementById(current_page + "_page").style.color = "#FFCC00FF";
    document.getElementById(current_page + "_page").style.textDecoration = "underline";
    document.getElementById(current_page + "_page").style.fontWeight = "bold";

    let next = current_page + 1;
    let prev = current_page - 1;

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


    $(document).ready(function () {
        $('.search_input').keyup(function (event) {
            if (event.which === 13) {
                event.preventDefault();
                $('form').submit();
            }
        });
    });

    $(document).ready(function() {
        $("#heart_icon").on("click", function() {
            $("#likeForm").submit();
        });
    });

</script>
</body>
</html>