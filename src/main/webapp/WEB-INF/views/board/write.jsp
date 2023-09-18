<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>게시물 작성 폼</title>
    <link rel="stylesheet" type="text/css" href="/resources/static/css/board/write.css">

    <script src="//code.jquery.com/jquery-3.6.0.min.js"></script>
    <script type="text/javascript">
        $(function() {
            $("#navbar").load("${path}/resources/common_jsp/navbar.jsp");
        });
    </script>
</head>
<body>
<form method="post" action="/starroad/board/writepro" enctype="multipart/form-data"  >
    <div class="container">
        <div id="navbar"></div>

        <div class="title">
            <input name="title" class="titleStyle"  type="text" id="title" placeholder="제목을 입력하세요" required />
            <br>
            <div class = "labelStyle">
            <label for="type">게시판 선택:</label>
            <select id="type" name="type" required>
                <option value="F">자유</option>
                <option value="C">인증</option>
            </select>

            <label for="detailType">게시글 종류:</label>
            <select id="detailType" name="detailType" required>
                <option value="잡담">잡담</option>
                <option value="정보">챌린지</option>
                <option value="질문">질문</option>
            </select>

            </div>

        </div>

        <div class="content">
            <textarea name="content" class="contentStyle" id="content" placeholder="내용을 입력하세요" required>${board.content}</textarea>
            <div class="image-input">
                <input type="file" name="image" id="image"  >
            </div>
            <div class="update-button">
                <button type="submit" class="buttonStyle" id="updateBtn">등록</button>
            </div>
        </div>
    </div>





</form>

</body>
</html>