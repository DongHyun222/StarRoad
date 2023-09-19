<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>게시물 작성 폼</title>
    <link rel="stylesheet" type="text/css" href="/resources/static/css/board/write.css">
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
    <script src="//stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>r
    <script src="//code.jquery.com/jquery-3.6.0.min.js"></script>
    <script type="text/javascript">
        $(function() {
            $("#navbar").load("${path}/resources/common_jsp/navbar.jsp");
        });
        $(document).ready(function() {
            var detailTypes = {
                "F": ["거지방", "잡담", "정보공유"],
                "C": ["챌린지", "자산인증"]
            }

            $('#type').change(function() {
                var selectedType = $(this).val();

                // detailType select box 초기화
                $('#detailType').empty();

                // 선택된 type에 따라 detailTypes에서 해당 배열 가져와서 option으로 추가
                $.each(detailTypes[selectedType], function(index, value) {
                    $('#detailType').append($('<option>').text(value).attr('value', value));
                });
            });

            // 페이지 로드 시 초기 상태 설정
            $('#type').trigger('change');
        });
    </script>
</head>
<body>

<form method="post" action="/starroad/board/writepro" enctype="multipart/form-data"  >


    <div class="container">
        <div class="dkssud">

            <h2 class ="topTitle"> 글쓰기 </h2>

            <div class="update-button">
                <button type="submit" class="buttonStyle" id="updateBtn">등록</button>
            </div>
        <hr/>
            <div >

        <div id="navbar"></div>

        <div class = "row labelStyle justify-content-md-center">
            <div class="col-md-5 col-sm-12 form-group d-flex ">

                <select id="type" name="type" required class="form-control">
                    <option value="" disabled selected>게시판선택</option>
                    <option value="F">자유</option>
                    <option value="C">인증</option>
                </select>
            </div>

            <div class="col-md-5 col-sm-12 form-group d-flex ">

                <select id="detailType" name="detailType" required class="form-control"></select>
            </div>
        </div>

        <div class="title">
            <input name="title" class="titleStyle"  type="text" id="title" placeholder="제목을 입력하세요" required />
        </div>

        <div class="content">
            <textarea name="content" class="contentStyle" id="content" placeholder="내용을 입력하세요" required>${board.content}</textarea>
            <div class="image-input">
                <input type="file" name="image" id="image"  >
            </div>

        </div>
    </div>





</form>

</body>
</html>