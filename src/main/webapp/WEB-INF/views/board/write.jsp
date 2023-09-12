<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>게시물 작성 폼</title>
    <style>
        body {
            text-align: center;
        }

        .container {
            display: flex;
            flex-direction: column;
            align-items: center;
            margin-top: 50px;
        }

        .layout {
            width: 500px;
            display: flex;
            flex-direction: column;
            align-items: center; /* 요소를 수평 중앙에 정렬합니다. */
            margin: 0 auto; /* 좌우 여백을 자동으로 설정하여 가운데 정렬합니다. */
        }

        .layout label {
            font-size: 12px;
            margin-bottom: 5px;
        }

        .layout input, .layout select, .layout textarea {
            width: 100%;
            box-sizing: border-box;
            margin-top: 10px;
            padding: 10px;
        }

        .layout select {
            width: 31%;
            height: 50px;
            margin-top: 5px;
        }

        .layout select#postType {
            width: 15%;
            height: 50px;
        }

        .layout textarea {
            min-height: 300px;
            margin-top: 10px;
            padding: 10px;
        }

        .layout .image-input {
            width: 100%;
            box-sizing: border-box;
            margin-top: 10px;
        }

        .layout .image-input input[type="file"] {
            margin-top: 10px;
        }

        .layout .submit-button {
            display: block;
            margin: 20px auto;
            background-color: yellow;
            border: none;
            padding: 10px 20px;
            cursor: pointer;

    </style>
    <script src="//code.jquery.com/jquery-3.6.0.min.js"></script>
    <script type="text/javascript">
        $(function() {
            $("#navbar").load("${path}/resources/common_jsp/navbar.jsp");
        });
    </script>
</head>
<body>
<div id="navbar"></div>

<div class="container">
    <h1>글쓰기</h1>
    <br>
    <div class="layout">
        <form method="post" action="/starroad/board/writepro" enctype="multipart/form-data">
            <label for="type">게시판 선택:</label>
            <select id="type" name="type" required>
                <option value="0">자유게시판</option>
                <option value="1">인증게시판</option>
            </select>

            <label for="detailType">게시글 종류:</label>
            <select id="detailType" name="detailType" required>
                <option value="잡담">잡담</option>
                <option value="정보">정보</option>
                <option value="질문">질문</option>
            </select>
            <br>

            <input name="title" type="text" id="title" placeholder="제목을 입력하세요" required>

            <textarea name="content" id="content" placeholder="내용을 입력하세요" required></textarea>

            <div class="image-input">
                <input type="file" name="image" id="image">
            </div>

            <button type="submit" class="submit-button">등록</button>
        </form>
    </div>
</div>
</body>
</html>