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
        });
    </script>
</head>
<body>
    <div id="navbar"></div>

    <div class="container">

        <div class="form-container">
            <h1>회원가입</h1>
            <h2>기본정보 <span class="required"><span class="star">*</span>표시는 필수 입력입니다</span></h2>
            <form>
                <table>
                    <tr>
                        <th>성명 <span class="star">*</span></th>
                        <td><input type="text" name="name"></td>
                    </tr>
                    <tr>
                        <th>아이디 <span class="star">*</span></th>
                        <td>
                            <input type="text" name="username">
                            <button class='memberClick'>중복 확인</button><br>
                            <div class='valid'>6~12자리 영문/숫자 조합</div>
                        </td>

                    </tr>
                    <tr>
                        <th>비밀번호 <span class="star">*</span></th>
                        <td>
                            <input type="password" name="password">
                            <div class='valid'>8~12자리 영문/숫자 조합 (대소문자)</div>
                        </td>
                    </tr>
                    <tr>
                        <th>비밀번호 확인 <span class="star">*</span></th>
                        <td>
                            <input type="password" name="confirm_password">
                            <div class='valid'>비밀번호를 다시한번 입력하세요</div>
                        </td>
                    </tr>
                </table>
            </form>
            <br>
            <h2>추가정보</h2>
            <form>
                <table>
                    <tr>
                        <th>전화번호 <span class="star">*</span></th>
                        <td>
                            <input type="text" name="tel" /> -
                            <input type="text" name="tel" /> -
                            <input type="text" name="tel" />
                        </td>
                    </tr>
                    <tr>
                        <th>이메일 <span class="star">*</span></th>
                        <td>
                            <input type="text" id="user_email" name="email" required><span id="middle"> @ </span><input type="text" id="email_address" list="user_email_address" name="email">
                            <datalist id="user_email_address">
                                <option value="naver.com"></option>
                                <option value="daum.com"></option>
                                <option value="google.com"></option>
                                <option value="직접입력"></option>
                            </datalist>
                        </td>
                    </tr>
                    <tr>
                        <th>자택주소 <span class="star">*</span></th>
                        <td>
                            <input type="text" name="address">
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
            </form>
        </div>
    </div>

</body>
</html>