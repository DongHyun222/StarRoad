<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="ko">
<head>
    <!-- 메타 정보, 스타일 등 -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/static/css/board2.css"

</head>
<body>

<!-- Navbar -->
<div class="container">
    <div class="row">
        <div class="col-md-9">
            <div class="navbar">
                <a href="#" onclick="showContent('popular')">인기글</a>
                <a href="#" onclick="showContent('free')">자유게시판</a>
                <a href="#" onclick="showContent('authentication')">인증방</a>
            </div>
        </div>
        <div class="col-md-3 text-right">
            <div class="navbar-right">
                <a href="/starroad/boardWrite">글쓰기</a>
            </div>
        </div>
    </div>
</div>
<div class="container">
    <div id="popular" class="menu-content active">
        <!-- 인기글 내용을 여기에 표시 -->
        <!-- 예: <h2>인기글</h2> ... -->
    </div>

    <!-- 자유게시판 내용 -->
    <div id="free" class="menu-content">
        <!-- 게시판 내용 분리 -->
        <div class="board">


            <!-- 예시 데이터 대신 실제 데이터를 표시하도록 수정 -->
            <div class="row no-gutters">
                <c:forEach items="${freeBoardPage.content}" var="board">
                    <div class="col-md-6">
                        <div class="board-item">
                            <h3>${board.title}</h3>
                            <p>${board.content}</p>
                            <div class="icons">
                                <i class="far fa-thumbs-up"></i> ${board.likes} <i class="far fa-comment"></i> ${board.commentNum}


                            </div>
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
    <!-- 인증방 내용 -->
    <div id="authentication" class="menu-content">
        <!-- 게시판 내용 분리 -->
        <div class="board">
            <!-- 예시 데이터 대신 실제 데이터를 표시하도록 수정 -->
            <div class="row no-gutters">
                <c:forEach items="${authenticationBoardPage.content}" var="board">
                    <div class="col-md-6">
                        <div class="board-item">
                            <h3>${board.title}</h3>
                            <p>${board.content}</p>
                            <div class="icons">
                                <i class="far fa-thumbs-up"></i> ${board.likes} <i class="far fa-comment"></i> ${board.commentNum}
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>

            <nav aria-label="Page navigation">
                <ul class="pagination justify-content-center">
                    <c:if test="${authenticationBoardPage.totalPages > 1}">
                        <c:if test="${authenticationBoardPage.number != 0}">
                            <li class="page-item">
                                <a class="page-link" href="?page=0" aria-label="처음">
                                    <span aria-hidden="true">&laquo;&laquo;</span>
                                </a>
                            </li>
                            <li class="page-item">
                                <a class="page-link" href="?page=${authenticationBoardPage.number - 1}" aria-label="이전">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                            </li>
                        </c:if>
                        <c:forEach begin="0" end="${authenticationBoardPage.totalPages - 1}" varStatus="loop">
                            <li class="page-item ${loop.index == authenticationBoardPage.number ? 'active' : ''}">
                                <a class="page-link" href="?page=${loop.index}">${loop.index + 1}</a>
                            </li>
                        </c:forEach>
                        <c:if test="${authenticationBoardPage.number + 1 < authenticationBoardPage.totalPages}">
                            <li class="page-item">
                                <a class="page-link" href="?page=${authenticationBoardPage.number + 1}" aria-label="다음">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                            </li>
                            <li class="page-item">
                                <a class="page-link" href="?page=${authenticationBoardPage.totalPages - 1}" aria-label="끝">
                                    <span aria-hidden="true">&raquo;&raquo;</span>
                                </a>
                            </li>
                        </c:if>
                    </c:if>
                </ul>
            </nav>
        </div>
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