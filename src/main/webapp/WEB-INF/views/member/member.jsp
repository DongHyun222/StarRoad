<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>MEMBER</title>
    <script src="//code.jquery.com/jquery-3.6.0.min.js"></script>
    <link rel="stylesheet" type="text/css" href="${path}/resources/static/css/common.css">
    <link rel="stylesheet" type="text/css" href="${path}/resources/static/css/member.css">
    <script type="text/javascript">
        $(function() {
            $("#navbar").load("${path}/resources/navbar.jsp");

            <%-- $("#confirmPassword").submit(function(){
                if($("#password").val() !== $("#password1").val()){
                    alert("비밀번호가 다릅니다.");
                    $("#password").val("").focus();
                    $("#password1").val("");
                    return false;
                }else if ($("#password").val().length < 8) {
                    alert("비밀번호는 8자 이상으로 설정해야 합니다.");
                    $("#password").val("").focus();
                    return false;
                }else if($.trim($("#password").val()) !== $("#password").val() || $.trim($("#email").val()) !== $("#email").val() || $.trim($("#id").val()) !== $("#id").val()){
                    alert("공백은 입력이 불가능합니다.");
                    return false;
                }
            }) --%>
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
                        <td><input type="text" name="name" required></td>
                    </tr>
                    <tr>
                        <th>아이디 <span class="star">*</span></th>
                        <td>
                            <input type="text" name="id" required>
                            <button class='memberClick'>중복 확인</button><br>
                            <div class='valid'>6~12자리 영문/숫자 조합</div>
                        </td>

                    </tr>
                    <tr>
                        <th>비밀번호 <span class="star">*</span></th>
                        <td>
                            <input type="password" name="password" required>
                            <div class='valid'>8~12자리 영문/숫자 조합 (대소문자)</div>
                        </td>
                    </tr>
                    <tr>
                        <th>비밀번호 확인 <span class="star">*</span></th>
                        <td>
                            <input type="password" name="confirm_password" id="confirmPassword" required>
                            <div class='valid'>비밀번호를 다시한번 입력하세요</div>
                        </td>
                    </tr>
                </table>
                <br>
                <h2>추가정보</h2>

                <table>
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
                            <input type="text" id="user_email" name="email" required>
                        </td>
                    </tr>
                    <tr>
                        <th>자택주소 <span class="star">*</span></th>
                        <td>
                            <input type="text" name="address" required>
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