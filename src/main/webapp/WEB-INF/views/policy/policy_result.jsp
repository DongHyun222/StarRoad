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
    <script type="text/javascript">
        $(function () {
            $("#navbar").load("${pageContext.request.contextPath}/resources/common_jsp/navbar.jsp");
        })
        $(function () {
            $("#location_${request_location}").prop("selected", true)
        })
        $(function () {
            $("#keyword").val("${request_keyword}");
        })
        $(function () {
            $("#${request_tag1}").prop('checked', true);
        })
        $(function () {
            $("#${request_tag2}").prop('checked', true);
        })
        $(function () {
            $("#${request_tag3}").prop('checked', true);
        })
        $(function () {
            $("#${request_tag4}").prop('checked', true);
        })
    </script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/static/css/common.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/static/css/policy/policy.css">
</head>
<body>
    <div id="policy-div-wrap">
        <div id="navbar"></div>
        <div class="title">청년 금융 정책</div>
        <main>
            <div id="main_box">
                <div class="search_box">

                    <form name="policyForm" id="policyForm" class="search_form" method="get" action="${pageContext.request.contextPath}/starroad/policy/result">
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
                                    <li><input type="checkbox" id="금융지원" name="tag1" value="금융지원"><label for="금융지원">금융지원</label></li>
                                    <li><input type="checkbox" id="교육" name="tag2" value="교육"><label for="교육">교육</label></li>
                                    <li><input type="checkbox" id="생활지원" name="tag3" value="생활지원"><label for="생활지원">생활지원</label></li>
                                    <li><input type="checkbox" id="금융자산" name="tag4" value="금융자산"><label for="금융자산">금융자산 형성</label></li>
                                </ul>

                            </div>
                        </div>

                        <div class="search_small_box">
                            <div class="search_box_title">정책명</div>
                            <div class="search_box_option">
                                <input class="search_input" name="keyword" id="keyword" type="text" placeholder="키워드를 입력해주세요">
                                <button id="final" class="submit_btn" type="submit">검색</button>
                            </div>
                        </div>

                    </form>
                </div>

                <div class="policy_box">
                    <c:forEach items="${policyList}" var="item">
                        <div class="policy">
                            <div class="name">${item.name}</div>
                            <div class="explain">${item.explain}</div>
                            <div class="tag">#${item.tag}</div>
                            <div class="btn_div"><button class="link_btn"><a href="${item.link}">더보기</a></button></div>
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
                            <li class="page-item"><a class="page-link" href="#" aria-label="${i}" id="${i}_page">${i}</a></li>
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
                        window.location.href = '/starroad/policy/result?location=${request_location}&tag1=${request_tag1}&tag2=${request_tag2}&tag3=${request_tag3}&tag4=${request_tag4}&keyword=${request_keyword}&pageIndex=' + prev;
                    }
                } else if (link.getAttribute('aria-label') === 'Next') {
                    if (parseInt(${pageEndIndex}) > current_page) {
                        window.location.href = '/starroad/policy/result?location=${request_location}&tag1=${request_tag1}&tag2=${request_tag2}&tag3=${request_tag3}&tag4=${request_tag4}&keyword=${request_keyword}&pageIndex=' + next;
                    }
                } else {
                    window.location.href = '/starroad/policy/result?location=${request_location}&tag1=${request_tag1}&tag2=${request_tag2}&tag3=${request_tag3}&tag4=${request_tag4}&keyword=${request_keyword}&pageIndex=' + link.getAttribute('aria-label');
                }
            });
        });
    </script>

</body>
</html>