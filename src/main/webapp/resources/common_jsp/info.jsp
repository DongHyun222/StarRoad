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
    <link rel="stylesheet" type="text/css" href="${path}/resources/static/css/member.css">
    <!-- jquery 링크, navbar -->
    <script src="//code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="//code.jquery.com/jquery-latest.min.js"></script>
</head>
    <div class="info_div">
        <div class="info_h1">내 정보<div>
        <br>
        <br>
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
                    <%--<input type="select" id="source" list="source_list" name="source">
                    <datalist id="source_list"> --%>
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
        <button type="submit" class="submit-button" id="update_btn">정보 수정</button>
    </div>
</html>