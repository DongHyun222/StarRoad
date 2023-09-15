<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Member</title>
    <script src="//code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="//code.jquery.com/jquery-latest.min.js"></script>
    <link rel="stylesheet" type="text/css" href="${path}/resources/static/css/common.css">
    <link rel="stylesheet" type="text/css" href="${path}/resources/static/css/member.css">
    <script type="text/javascript">
        $(function() {
            $("#navbar").load("${path}/resources/common_jsp/navbar.jsp");

            let idFlag = false;
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
                                id = true;
                                <%-- $("#password").trigger("focus"); --%>
                            } else {
                                result = "이미 사용중인 아이디입니다.";
                                $("#result_checkId").html(result).css("color", "red");
                                id = false;
                            }
                        },
                        error: function(error) {
                            console.log(id, error)
                            id = false;
                            alert("오류 발생");
                        }
                    });
                } else {
                    alert("아이디는 6~12자의 영문자와 숫자 조합이어야 합니다.");
                    id = false;
                    $("#id").val("").trigger("focus");
                }
            });

            let errorFlag = false;
            $("#password").blur(function() {
                let password = $("#password").val();
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

            $(".submit-button").click(function() {
                if (idFlag && errorFlag) {
                    alert("회원가입이 완료되었습니다.");
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
                            <select name="job" id="job">
                                <option disabled selected>직업을 선택하세요</option>
                                <option>학생</option>
                                <option>직장인</option>
                                <option>사업주</option>
                                <option>프리랜서</option>
                                <option>전문직</option>
                                <option>주부</option>
                                <option>기타</option>
                            </select>
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
                            <select name="purpose" id="purpose">
                                <option disabled selected>거래목적을 선택하세요</option>
                                <option >급여 및 생활비</option>
                                <option >저축 및 투자</option>
                                <option >사업상 거래</option>
                                <option >결제</option>
                                <option >대출</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <th>거래자금의 원천</th>
                        <td>
                            <select name="source" id="source" >
                                <option disabled selected>원천을 선택하세요</option>
                                <option >근로 및 연금소득</option>
                                <option >퇴직소득</option>
                                <option >사업소득</option>
                                <option >임대소득</option>
                                <option >매매소득</option>
                                <option>금융소득</option>
                                <option >용돈/생활비/상속</option>
                                <option >대출금</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <th>저금목표치 </th>
                        <td>
                            <span class="source"> 거래자금의 원천의</span>
                            <input type="number" name="goal" id="goal" min="0" max="100" required>
                            <span class="per">%</span>
                            <div class='valid'>퍼센트단위 (1~100사이 숫자 입력)</div>
                        </td>
                    </tr>
                </table>
                <br>
                <div class="agree-check">
                    <input type="checkbox" required /> 이용약관 개인정보 수집 및 이용, 마케팅 활용선택에 모두 동의합니다.
                </div>
                <br>
                <button type="submit" class="submit-button" >회원가입</button>
                <br>
                <br>
            </form>
        </div>
    </div>

</body>
</html>