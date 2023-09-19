<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>게시물 상세보기</title>
    <script src="//code.jquery.com/jquery-3.6.0.min.js"></script>

    <link rel="stylesheet" type="text/css" href="/resources/static/css/board/detail.css">
    <script type="text/javascript">
            $(function() {
                $("#navbar").load("/resources/common_jsp/navbar.jsp");
            });

    </script>
</head>
<body>
    <div class="container">
        <div id="navbar"></div>

        <div class="title">
            <span class="title-text"><c:out value="${board.title}" /></span> <br>
            <div class ="something">
            <span class="memberId author-id"><c:out value="${board.memberId}"/></span>
            <hr class="separator">
            <span class="regdate">
                <fmt:formatDate value="${board.regdate}" pattern="yyyy-MM-dd HH:mm" />
            </span>
            <span class="likes">

                <img src="https://ifh.cc/g/aw0vjY.png" alt="Like Icon" style="vertical-align: middle; width: 20px; height: 20px;">
                <c:out value="${board.likes}" />
            </span>
            </div>

            <div class="title-buttons more-actions">
                <span class="icon">...</span>
                <div class="actions">
                    <button id="editBtn">수정</button>
                    <button id="deleteBtn">삭제</button>
                </div>
            </div>
        </div>

        <div class="content">

            <c:out value="${board.content}" />
            <div class="like-section">
            <form id="likeForm" method="post" action="/starroad/board/like">
                <input type="hidden" name="board" value="${board.no}">
                <img src="https://ifh.cc/g/aw0vjY.png" id="like-icon" alt="Like Icon" style="vertical-align: middle; width: 30px; height: 30px;" >
            </form>
                <span class="likes-count"> <c:out value="${board.likes}" />  </span>
                <%-- <c:out value="${board.likes}" /> --%>
                <img src="data:image/jpeg;base64,${board.imageBase64}" alt="" width="200" height="200" style="margin-bottom: 30px;" onerror="this.style.display='none'"/>
            </div>
        </div>

        <div class="comment">

        <div class="comment-input">
             <form action="/starroad/comment" method="post">
                 댓글<c:out value="${board.commentNum}" />
                 <textarea id="commentText" name="content" placeholder="댓글을 입력하세요" rows="4" cols="50"></textarea>
                 <input type="hidden" name="board" value="${board.no}" />
                 <button id="submitComment" type="submit">등록</button>
             </form>
        </div>
        </div>

        <div class="comments-list">
        <c:forEach var="comment" items="${board.comments}">
             <div class="comment-item">
                 <strong class="comment-author"><c:out value="${currentUser.id}" /></strong> <br>
                 <span class="comment-content"><c:out value="${comment.content}" /></span> <br>
                 <span class="comment-date">
                    <fmt:formatDate value="${comment.regdate}" pattern="yyyy-MM-dd HH:mm:ss" />
                 </span>

                 <div class="more-actions">
                    <span class="icon">...</span>
                        <div class="actions">
                            <button class="comment-edit" data-id="${comment.no}">수정</button>
                            <button class="comment-delete" data-id="${comment.no}">삭제</button>
                        </div>
                    </div>
                </div>
         </c:forEach>
        </div>

   <script>
     document.getElementById("deleteBtn").addEventListener("click", function() {
               if (confirm("정말로 삭제하시겠습니까?")) {
                   location.href = "/starroad/board/delete?no=" + ${board.no}; // 삭제 API 호출
               }
       });
     document.getElementById("editBtn").addEventListener("click", function() {

                 location.href = "/starroad/board/update?no=" + ${board.no}; // 수정 API 호출

       });
     document.getElementById("submitComment").addEventListener("click", function() {

                      location.href = "/starroad/board/update?no=" + ${board.no}; // 수정 API 호출

       });

  document.querySelectorAll(".comment-delete").forEach(function(button) {
      button.addEventListener("click", function() {
          const commentNo = button.getAttribute("data-id");
          console.log("commentNo=" + commentNo);

          if (confirm("정말로 댓글을 삭제하시겠습니까?")) {
              const form = document.createElement('form');
              form.method = 'POST';
              form.action = '/starroad/comment/delete?no='+commentNo;

              const input = document.createElement('input');
              input.type = 'hidden';
              input.name = 'no';
              input.value = commentNo;

              form.appendChild(input);
              document.body.appendChild(form);

              form.submit();
          }
      });
  });

        document.querySelectorAll(".comment-edit").forEach(function(button) {
            button.addEventListener("click", function() {
                const commentNo = button.getAttribute("data-id");
                location.href = "/starroad/comment/update?no=" + commentNo;
            });
        });

        document.getElementById("submitComment").addEventListener("click", function() {
            location.href = "/starroad/comment/create?no=" + ${board.no};
        });

       <!-- 좋아요 기능 -->
        $(document).ready(function() {
          $("#like-icon").on("click", function() {
              $("#likeForm").submit();
          });
        });

        <%-- 댓글 더보기 --%>
        document.querySelector('.more-actions .icon').addEventListener('click', function() {
            const parent = this.parentElement;
            parent.classList.toggle('active');
        });

        <%-- 게시글 더보기 --%>
            $(document).ready(function() {
                $('.more-actions .icon').click(function(e) {
                    e.stopPropagation(); // 이 이벤트가 부모 요소로 전파되는 것을 막습니다.
                    $(this).siblings('.actions').toggle(); // .actions의 표시 여부를 토글합니다.
                });

                // 페이지의 다른 부분을 클릭할 때 .actions를 숨깁니다.
                $(document).click(function() {
                    $('.more-actions .actions').hide();
                });

                // .actions 내부를 클릭할 때 .actions가 사라지는 것을 방지합니다.
                $('.more-actions .actions').click(function(e) {
                    e.stopPropagation();
                });
            });

   </script>
</body>
</html>