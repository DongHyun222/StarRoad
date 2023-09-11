<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>MEMBER</title>
    <script src="//code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="//code.jquery.com/jquery-latest.min.js"></script>
    <link rel="stylesheet" type="text/css" href="${path}/resources/static/css/common.css">
    <link rel="stylesheet" type="text/css" href="${path}/resources/static/css/member.css">
    <script type="text/javascript">
        $(function() {
            $("#navbar").load("${path}/resources/navbar.jsp");

            $("#checkId").click(function(){
                let id = $("#id").val();
                // 정규 표현식을 사용하여 아이디 유효성 검사
                const idPattern = /^[a-zA-Z0-9]{6,12}$/;

                if (idPattern.test(id)) {
                    $.ajax({
                        type: 'post',
                        url: "/starroad/checkMemberId",
                        data: {"id": id},
                        success: function(data) {
                            if (data == "N") {
                                result = "사용 가능한 아이디입니다.";
                                $("#result_checkId").html(result).css("color", "green");
                                <%-- $("#password").trigger("focus"); --%>
                            } else {
                                result = "이미 사용중인 아이디입니다.";
                                $("#result_checkId").html(result).css("color", "red");
                            }
                        },
                        error: function(error) {
                            alert("오류 발생");
                        }
                    });
                } else {
                    alert("아이디는 6~12자의 영문자와 숫자 조합이어야 합니다.");
                    $("#id").val("").trigger("focus");
                }
            });

            let errorFlag = false;
            $("#password").blur(function() {
                let password = $("#password").val();
                let confirmPassword = $("#confirmPassword").val();
                const passwordPattern = /^[a-zA-Z0-9]{8,12}$/;

                if (passwordPattern.test(password)) {
                    if (password !== confirmPassword) {
                        $("#result_checkPassword").text("비밀번호를 다시 입력해주세요");
                        errorFlag = true;
                    } else {
                        $("#result_checkPassword").text("비밀번호가 일치합니다."); // 메시지를 비웁니다.
                        errorFlag = false; // 비밀번호가 일치하면 오류 플래그를 리셋합니다.
                    }
                } else {
                    if (!errorFlag) { // 오류 플래그가 false인 경우에만 alert를 띄웁니다.
                        alert("비밀번호는 8~12자의 영문자와 숫자 조합이어야 합니다");
                        errorFlag = true;
                    }
                    $("#password").val("").trigger("focus");
                }
            });

            $("#confirmPassword").blur(function() {
                let password = $("#password").val();
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
    <div id="navbar"></div>

    <div class="container">

        <div class="form-container">
            <h1>회원가입</h1>
            <h2>기본정보 <span class="required"><span class="star">*</span>표시는 필수 입력입니다</span></h2>
            <form action="/starroad/member" method="post" enctype="multipart/form-data">
                <table>
                    <tr>
                        <th>성명 <span class="star">*</span></th>
                        <td><input type="text" name="name" id="name" required></td>
                    </tr>
                    <tr>
                        <th>아이디 <span class="star">*</span></th>
                        <td>
                            <input type="text" name="id" id="id" required>
                            <a id="checkId" class="memberClick" >중복확인</a><br>
                            <div class='valid'><span id="result_checkId" style="font-size:12px;">6~12자리 영문/숫자 조합</span></div>
                            <%-- <div><span id="result_checkId" style="font-size:12px;"></span></div> --%>
                        </td>
                    </tr>
                    <tr>
                        <th>비밀번호 <span class="star">*</span></th>
                        <td>
                            <input type="password" name="password" id="password" required>
                            <div class='valid'>8~12자리 영문/숫자 조합 (대소문자)</div>
                        </td>
                    </tr>
                    <tr>
                        <th>비밀번호 확인 <span class="star">*</span></th>
                        <td>
                            <input type="password" name="confirm_password" id="confirmPassword" required>
                            <div class='valid' id="result_checkPassword">비밀번호를 다시한번 입력하세요</div>
                        </td>
                    </tr>
                </table>
                <br>
                <h2>추가정보</h2>

                <table>
                    <tr>
                        <th>생년월일 <span class="star">*</span></th>
                        <td><input type="date" name="birthday" id="birthday" required></td>
                    </tr>
                    <tr>
                        <th>전화번호 <span class="star">*</span></th>
                        <td>
                            <input type="text" name="phone" /> -
                            <input type="text" name="phone" /> -
                            <input type="text" name="phone" />
                        </td>
                    </tr>
                    <tr>
                        <th>이메일 <span class="star">*</span></th>
                        <td>
                            <input type="text" id="email" name="email" required>
                        </td>
                    </tr>
                    <tr>
                        <th>자택주소 <span class="star">*</span></th>
                        <td>
                            <input type="text" name="address" id="address" required>
                        </td>
                    </tr>
                    <tr>
                        <th>직업구분</th>
                        <td>
                            <input type="text" id="job" list="job_list" name="job">
                            <datalist id="job_list">
                                <option value="학생"></option>
                                <option value="직장인"></option>
                                <option value="사업주"></option>
                                <option value="프리랜서"></option>
                                <option value="전문직"></option>
                                <option value="주부"></option>
                                <option value="기타"></option>
                            </datalist>
                        </td>
                    </tr>
                    <tr>
                        <th>월수입 </th>
                        <td>
                            <input type="number" name="salary" id="salary" required>
                            <div class='valid'>1,000원 단위 (소득이 없을시 0)</div>
                        </td>
                    </tr>
                        <th>거래목적</th>
                        <td>
                            <input type="text" id="purpose" list="purpose_list" name="purpose">
                            <datalist id="purpose_list">
                                <option value="급여 및 생활비"></option>
                                <option value="저축 및 투자"></option>
                                <option value="사업상 거래"></option>
                                <option value="결제"></option>
                                <option value="대출"></option>
                            </datalist>
                        </td>
                    </tr>
                    <tr>
                        <th>거래자금의 원천</th>
                        <td>
                            <input type="text" id="source" list="source_list" name="source">
                            <datalist id="source_list">
                                <option value="근로 및 연금소득"></option>
                                <option value="퇴직소득"></option>
                                <option value="사업소득"></option>
                                <option value="임대소득"></option>
                                <option value="매매소득"></option>
                                <option value="금융소득"></option>
                                <option value="용돈/생활비/상속/"></option>
                                <option value="대출금"></option>
                            </datalist>
                        </td>
                    </tr>
                    <tr>
                        <th>저금목표치 </th>
                        <td>
                            <input type="number" name="goal" id="goal" min="0" max="100" required>
                            <div class='valid'>퍼센트단위 (1~100사이 숫자 입력)</div>
                        </td>
                    </tr>
                </table>
                <br>
                <button type="submit" class="submit-button">회원가입</button>
                <br>
                <br>
            </form>
        </div>
    </div>

</body>
</html>