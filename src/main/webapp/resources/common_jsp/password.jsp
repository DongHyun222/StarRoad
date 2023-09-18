<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>mypage</title>
    <link rel="stylesheet" href="${path}/resources/static/css/common.css">
    <link rel="stylesheet" href="${path}/resources/static/css/mypage/sidebar.css">
    <link rel="stylesheet" href="${path}/resources/static/css/mypage/check_password.css">
    <script src="//code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="//code.jquery.com/jquery-latest.min.js"></script>
    <link rel="stylesheet" type="text/css" href="${path}/resources/static/css/member.css">

    <script type="text/javascript">
        $(function() {
            let errorFlag = false;
            $("#password1").blur(function() {
                let password = $("#password1").val();
                let confirmPassword = $("#confirmPassword").val();
                const passwordPattern = /^[a-zA-Z0-9]{8,12}$/;

                if (passwordPattern.test(password)) {
                    result = "비밀번호가 조건에 일치합니다";
                    $("#result_Password").html(result).css("color", "green");
                    errorFlag = true; // 비밀번호가 일치하면 오류 플래그를 리셋합니다.
                } else {
                    result = "비밀번호를 다시 입력해주세요 (8~12자리 영문/숫자 조합)";
                    $("#result_Password").html(result).css("color", "red");
                    errorFlag = false;
                }

                if (!errorFlag) { // 오류 플래그가 false인 경우에만 alert를 띄웁니다.
                    alert("비밀번호는 8~12자의 영문자와 숫자 조합이어야 합니다");
                }
            });

            $("#confirmPassword").blur(function() {
                let password = $("#password1").val();
                let confirmPassword = $("#confirmPassword").val();

                if (password !== confirmPassword) {
                    result = "비밀번호를 다시 입력해주세요";
                    $("#result_checkPassword").html(result).css("color", "red");

                } else {
                    result = "비밀번호가 일치합니다";
                    $("#result_checkPassword").html(result).css("color", "green");
                }
            });

        });


    </script>

</head>
<body>

    <div class="password-container" >
        <div class="info_h1">${currentUser.name}님의 정보<div>
        <form action="/starroad/mypage/password" method="post" enctype="multipart/form-data">
            <table>
                <tr>
                    <th>비밀번호 수정 <span class="star">*</span></th>
                    <td>
                        <input type="password" name="password" id="password1" required>
                        <div class='valid' id="result_Password">8~12자리 영문/숫자 조합 (대소문자)</div>
                    </td>
                </tr>
                <tr>
                    <th>비밀번호 확인 <span class="star">*</span></th>
                    <td>
                        <input type="password" name="confirm_password" id="confirmPassword" required>
                        <div class='valid' id="result_checkPassword">비밀번호를 다시한번 입력하세요</div>
                    </td>
                </tr>
                <br>
            </table>
            <br>
            <br>
            <button type="submit" class="submit-button" >비밀번호 수정</button>
        </form>
    </div>

</body>
</html>