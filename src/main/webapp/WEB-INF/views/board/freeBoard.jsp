<%--
  Created by IntelliJ IDEA.
  User: student
  Date: 2023-09-06
  Time: 오후 1:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
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
        /* 그리드 스타일 */
        .grid-container {
            display: grid;
            grid-template-columns: repeat(2, 1fr); /* 2열로 분할 */
        }
        .grid-item {
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
        </div>
        <div class="board">
            <!-- 그리드로 표현 -->
            <div class="grid-container">
                <!-- 예시 데이터 -->
                <div class="grid-item">
                    <h3>Title 1</h3>
                    <p>Content 1</p>
                </div>
                <div class="grid-item">
                    <h3>Title 2</h3>
                    <p>Content 2</p>
                </div>
                <div class="grid-item">
                    <h3>Title 3</h3>
                    <p>Content 3</p>
                </div>
                <div class="grid-item">
                    <h3>Title 4</h3>
                    <p>Content 4</p>
                </div>
                <div class="grid-item">
                    <h3>Title 5</h3>
                    <p>Content 5</p>
                </div>
                <div class="grid-item">
                    <h3>Title 6</h3>
                    <p>Content 6</p>
                </div>
            </div>
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