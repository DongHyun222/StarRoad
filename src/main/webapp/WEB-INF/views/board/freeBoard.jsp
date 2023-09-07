<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="ko">
<head>
    <!-- 메타 정보, 스타일 등 -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        /* 네비게이션 메뉴 스타일 */
        .navbar a {
            float: left;
            font-size: 16px;
            color: black;
            text-align: center;
            padding: 14px 16px;
            text-decoration: none; /* 밑줄 제거 */
        }
        .navbar-right { /* 글쓰기 */
            float: right;
        }
        .navbar-right a[href="/starroad/boardWrite"] {
            background-color: yellow; /* 배경색 노란색으로 설정 */
            font-weight: bold;
            text-decoration: none;
        }
        /* 밑줄 없애고 호버 스타일 */
        .navbar a:hover {
            background-color: #ddd;
            color: black;
        }
        /* 게시판 스타일 */
        .board {
            background-color: #f5f5f5;
            padding: 20px;
            border: 1px solid #ddd;
        }
        /* 게시글 스타일 */
        .board-item {
            flex: 0 0 calc(50% - 10px);
            margin-right: 10px;
            margin-bottom: 20px;
            border: 1px solid #ddd;
            padding: 10px;
            text-align: center;
        }
    </style>
</head>
<body>

<!-- Navbar -->
<div class="navbar">
    <a href="#" onclick="showContent('popular')">인기글</a>
    <a href="#" onclick="showContent('free')">자유게시판</a>
    <a href="#" onclick="showContent('authentication')">인증방</a>
</div>
<div class="navbar-right">
    <a href="/starroad/boardWrite">글쓰기</a>
</div>

<!-- 내용 -->
<div class="container">
    <div id="popular" class="menu-content active">

    </div>

    <!-- 자유게시판 내용 -->
    <div id="free" class="menu-content">
        <!-- 게시판 내용 분리 -->
        <div class="board">
            <h2>자유게시판</h2>

            <!-- 예시 데이터 -->
            <div class="row">
                <c:forEach items="${freeBoardPage.content}" var="board">
                    <div class="col-md-6">
                        <div class="board-item">
                            <h3>${board.title}</h3>
                            <p>${board.content}</p>
                        </div>
                    </div>
                </c:forEach>
            </div>



            <nav aria-label="Page navigation">
                <ul class="pagination justify-content-center">
                    <c:if test="${freeBoardPage.totalPages > 1}">
                        <c:if test="${freeBoardPage.number != 0}">
                            <li class="page-item">
                                <a class="page-link" href="?page=0" aria-label="처음">
                                    <span aria-hidden="true">&laquo;&laquo;</span>
                                </a>
                            </li>
                            <li class="page-item">
                                <a class="page-link" href="?page=${freeBoardPage.number - 1}" aria-label="이전">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                            </li>
                        </c:if>
                        <c:forEach begin="0" end="${freeBoardPage.totalPages - 1}" varStatus="loop">
                            <li class="page-item ${loop.index == freeBoardPage.number ? 'active' : ''}">
                                <a class="page-link" href="?page=${loop.index}">${loop.index + 1}</a>
                            </li>
                        </c:forEach>
                        <c:if test="${freeBoardPage.number + 1 < freeBoardPage.totalPages}">
                            <li class="page-item">
                                <a class="page-link" href="?page=${freeBoardPage.number + 1}" aria-label="다음">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                            </li>
                            <li class="page-item">
                                <a class="page-link" href="?page=${freeBoardPage.totalPages - 1}" aria-label="끝">
                                    <span aria-hidden="true">&raquo;&raquo;</span>
                                </a>
                            </li>
                        </c:if>
                    </c:if>
                </ul>
            </nav>
        </div>
    </div>

    <!-- 인증방 내용 -->
    <div id="authentication" class="menu-content">

    </div>
</div>

<!-- JavaScript -->
<script>
    function showContent(menu) {
        // 모든 메뉴 숨기기
        document.querySelectorAll('.menu-content').forEach(function (content) {
            content.style.display = 'none';
        });

        // 선택한 메뉴 표시
        document.getElementById(menu).style.display = 'block';
    }
</script>

</body>
</html>