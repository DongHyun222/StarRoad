<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>mypage</title>
    <link rel="stylesheet" href="${path}/resources/static/css/common.css">
    <link rel="stylesheet" href="${path}/resources/static/css/mypage/sidebar.css">
    <link rel="stylesheet" href="${path}/resources/static/css/mypage/check_password.css">
    <link rel="stylesheet" type="text/css" href="${path}/resources/static/css/member.css">
    <!-- jquery 링크, navbar -->
    <script src="//code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="//code.jquery.com/jquery-latest.min.js"></script>
    <script>
        function clearInput(inputField) {
            inputField.value = '';
        }
        $(".submit-button").click(function() {
            var requiredFields = $("input[required]");

            // 모든 필수 필드가 valid한지 확인
            var allValid = true;
            requiredFields.each(function() {
                if (!this.checkValidity()) {
                    allValid = false;
                    return false; // 검증 실패 시 반복문 종료
                }
            });

            // 모든 필수 필드가 valid하다면 alert 띄우기
            if (allValid) {
                alert("개인정보수정이 완료되었습니다.");
            }
        });
    </script>

    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    <script>
        $("body").on("click", "#address", function(event) {
            new daum.Postcode({
                oncomplete: function(data) {
                    $("#address").val(data.address); // 주소 넣기
                    $("input[id=address_detail]").focus(); // 상세입력 포커싱
                }
            }).open();
        });

    </script>

</head>
<body>
    <div class="info_div">
        <div class="info_h1">내 정보<div>
        <br>
        <br>
        <form action="/starroad/mypage/info" method="post" enctype="multipart/form-data">
            <table>
                <tr>
                    <th>전화번호 <span class="star">*</span></th>
                    <td>
                        <input type="text" name="phone" value="${currentUser.phone.substring(0,3)}" onclick="clearInput(this)"/> -
                        <input type="text" name="phone" value="${currentUser.phone.substring(4,8)}" onclick="clearInput(this)"/> -
                        <input type="text" name="phone" value="${currentUser.phone.substring(9,13)}" onclick="clearInput(this)"/>
                    </td>
                </tr>
                <tr>
                    <th>이메일 <span class="star">*</span></th>
                    <td>
                        <input type="text" id="email" name="email" value="${currentUser.email}" onclick="clearInput(this)" required>
                    </td>
                </tr>
                <tr>
                    <th>자택주소 <span class="star">*</span></th>
                    <td>
                        <input type="text" name="address" id="address" value="${currentUser.address.substring(0, currentUser.address.indexOf(','))}" required>
                    </td>
                <tr>
                    <th>상세주소 <span class="star" required>*</span></th>
                    <td>
                        <input type="text" name="address" id="address_detail" value="${currentUser.address.substring(currentUser.address.indexOf(',') + 1)}"
                        onclick="clearInput(this)" required>
                    </td>
                </tr>
                </tr>
                <tr>
                    <th>직업구분</th>
                    <td>
                        <select name="job" id="job">
                            <option disabled selected> ${currentUser.job} </option>
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
                        <input type="number" name="salary" id="salary" value="${currentUser.salary}" onclick="clearInput(this)" required>
                        <div class='valid'>1,000원 단위 (소득이 없을시 0)</div>
                    </td>
                </tr>
                    <th>거래목적</th>
                    <td>
                        <select name="purpose" id="purpose">
                            <option disabled selected> ${currentUser.purpose} </option>
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
                        <%--<input type="select" id="source" list="source_list" name="source">
                        <datalist id="source_list"> --%>
                        <select name="source" id="source" >
                            <option disabled selected> ${currentUser.source} </option>
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
                        <input type="number" name="goal" id="goal" min="0" max="100" value="${currentUser.goal}" onclick="clearInput(this)" required>
                        <span class="per">%</span>
                        <div class='valid'>퍼센트단위 (1~100사이 숫자 입력)</div>
                    </td>
                </tr>
            </table>
        <br>
        <button type="submit" class="submit-button" id="update_btn">정보 수정</button>
        </form>
    </div>
</body>
</html>