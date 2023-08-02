<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세조회</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- 부트스트랩 CSS 파일 링크 -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
    function comment_delete(comment_no){
        var articleNo = $('#articleNo').val();
        var commentNo = $('#commentNo_' + comment_no).val();
        
        // ajax : 정적페이지에서 동적페이지 구현
        $.ajax({
            type: "POST",
            url: "/Angel/commentDel.do",
            data: {
                articleNo: articleNo,
                commentNo: commentNo
            },
            success: function(response) {
                if(response.message === "success"){
                	alert("댓글이 삭제되었습니다.");
                    window.location.href = "/Angel/detail.do?articleNo=" + articleNo;
                } else {
                	alert("댓글이 삭제에 실패하였습니다.");
                	return;
                }
            },
            error: function() {
            	alert("댓글이 삭제에 실패하였습니다.");
                return false;
            }
        });
    }
</script>
<style>
  .button-container {
    display: flex; /* flexbox를 사용하여 버튼들을 한 줄에 표시 */
    align-items: center; /* 세로 방향으로 버튼들을 가운데 정렬 */
  }

  .inline-form {
    margin-right: 10px; /* 폼 사이에 약간의 간격 추가 */
  }
  
  .inline-form input {
    margin-left: 5px;
  }
</style>
</head>
<body>
<%@ include file = "/header.html" %>
<%-- ${DETAIL_CONTENT} --%>
<h1 class="text-center">상세글 조회</h1>
<div class="container">
  <div class="row justify-content-center">
    <div class="col" style="margin: 20px;">
      글번호 : ${DETAIL_CONTENT.articleNo}
      아이디 : ${DETAIL_CONTENT.memberid }
      이름 : ${DETAIL_CONTENT.name } 
      제목 : ${DETAIL_CONTENT.title } 
      조회수 : ${DETAIL_CONTENT.readCnt } 
      <hr>
      내용 : ${DETAIL_CONTENT.content } <br>
      <hr>
      <%-- 게시글 수정 --%>
      <form action="/view/ANGEL/article/ArticleUpdate.jsp" method="post">
        <input type="hidden" name="articleNo" value="${DETAIL_CONTENT.articleNo}">
        <input type="hidden" name="title" value="${DETAIL_CONTENT.title}">
        <input type="hidden" name="content" value="${DETAIL_CONTENT.content}">
        <div class="d-flex justify-content-center">
 		<button type="submit" class="btn btn-primary mr-2">수정</button>
          <a href="/Angel/delete.do?articleNo=${DETAIL_CONTENT.articleNo}" class="btn btn-primary mr-2">삭제</a>
          <a href="/Angel/article.do?pageNo=1" class="btn btn-primary mr-2">글 목록</a>
          <a href='javascript:history.go(-1)' class="btn btn-primary">뒤로 가기</a>
        </div>
      </form>
    </div>
  </div>
</div>

<%-- 댓글 --%>
<div class="container">
  <div class="row justify-content-center">
    <div class="col" style="margin: 20px;">
      <p><c:forEach items="${DETAIL_COMMENT}" var="comment">
          <li>댓글 작성자 : <c:out value="${comment.name}"/></li>
          <li>댓글 작성일 : <c:out value="${comment.regdate}"/></li>
          <li>댓글 내용 : <c:out value="${comment.comment}"/></li>
      	  <%-- 댓글 수정 --%>
      	  <div class="button-container">
            <form action="/view/ANGEL/article/CommentModify.jsp" method="get" class="inline-form">
              <button type="submit" class="btn btn-primary">수정</button>
              <input type="hidden" name="commentNo" value="${comment.commentNo}" />
              <input type="hidden" name="name" value="${comment.name}"/>
              <input type="hidden" name="articleNo" value="${DETAIL_CONTENT.articleNo}"/>
            </form>
          	<%-- 댓글 삭제 --%>
            <form id="delete_form" action="/Angel/commentDel.do" method="post" class="inline-form">
              <button type="button" class="btn btn-primary" onclick="comment_delete('${comment.commentNo}')">삭제</button>
              <input type="hidden" id="articleNo" name="articleNo" value="${DETAIL_CONTENT.articleNo}" />
              <input type="hidden" id="commentNo_${comment.commentNo}" name="commentNo" value="${comment.commentNo}" />
            </form>
          </div>
          <br /><br /> 
        </c:forEach>
      </p>
    </div>
  </div>
</div>

<%-- 댓글 입력 --%>
<div class="container">
  <div class="row justify-content-center">
    <div class="col" style="margin: 20px;">
      <form action="/Angel/comment.do" method="post">
        <input type="hidden" name="articleNo" value="${DETAIL_CONTENT.articleNo}">
        <p><input type="text" name="name" placeholder="이름" class="form-control"></p>
        <p><textarea rows="4" cols="50" name="comment" placeholder="댓글" class="form-control"></textarea></p>
        <input type="submit" value="등록" class="btn btn-primary">
      </form>
    </div>
  </div>
</div>
<%@ include file = "/footer.html" %>
</body>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.1/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</html>