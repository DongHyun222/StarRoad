<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>게시물 작성 폼</title>
    <style>
        body {
            text-align: center; /* 텍스트 가운데 정렬 */
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
            align-items: flex-start;
        }

        .layout label {
            font-size: 12px; /* 폰트 크기 설정 */
            margin-bottom: 5px; /* 아래쪽 간격 설정 */
        }

        .layout input, .layout select, .layout textarea {
            width: 100%;
            box-sizing: border-box;
            margin-top: 10px;
            padding: 10px;
        }

        .layout select {
            width: 30%; /* 게시판 선택 드롭다운 너비 늘리기 */
            height: 40px; /* 게시판 선택 드롭다운 높이 줄이기 */
            margin-top: 5px; /* 드롭다운 간격 설정 */
        }

        .layout select#postType {
            width: 21%; /* 게시글 종류 드롭다운 너비 줄이기 */
            height: 40px; /* 게시글 종류 드롭다운 높이 줄이기 */
        }

        .layout textarea {
            min-height: 300px;
            margin-top: 10px; /* 내용 입력 칸과의 간격 설정 */
            padding: 10px;
        }

        /* 이미지 업로드 필드 스타일 */
        .layout .image-input {
            width: 100%;
            box-sizing: border-box;
            margin-top: 10px; /* 이미지 업로드와의 간격 설정 */
        }

        /* 이미지 업로드 버튼 스타일 */
        .layout .image-input input[type="file"] {
            margin-top: 10px; /* 이미지 업로드 버튼과의 간격 설정 */
        }

        /* 등록 버튼 스타일 */
        .layout .submit-button {
            display: block;
            margin: 20px auto;
            background-color: yellow;
            border: none;
            padding: 10px 20px;
            cursor: pointer;
        }
    </style>
</head>
<body>
<div class="container">
    <H1> 글쓰기</H1>

    <div class="layout">
        <form method="post" action="/starroad/writepro" enctype="multipart/form-data" >
        <!-- 게시판 선택 드롭다운 -->
        <label for="type">게시판 선택:</label>
        <select id="type" name="type" required>
            <option value=0>자유게시판</option>
            <option value=1>인증게시판</option>
        </select>

        <!-- 게시글 종류 드롭다운 -->
        <label for="detailType">게시글 종류:</label>
        <select id="detailType" name="detailType" required>
            <option value="잡담">잡담</option>
            <option value="정보">정보</option>
            <option value="질문">질문</option>
        </select>
        <br>

        <input name="title" type="text" id="title" placeholder="제목을 입력하세요" required>


        <textarea name="content" id="content" placeholder="내용을 입력하세요" required></textarea>
            <!-- 이미지 업로드 필드 -->
           <div class="image-input">
               <input type="file" name="image" id="image">
          </div>


        <!-- 등록 버튼 -->
        <button type="submit" class="submit-button">등록</button>
        </form> <!-- 폼 종료 -->
    </div>
</div>
</body>
</html>