<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
 <!-- Bootstrap CDN -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootswatch@4.5.2/dist/flatly/bootstrap.min.css" crossorigin="anonymous">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</head>
<body>
<%@ include file="/header.html" %>
<!-- 여기아래로 -->
<div style="text-align: center;">
<img src="/assets/images/whiBoard/whiskey_banner.jpg" style="height: 100px; width: auto;" >
</div>
<div class="container mt-4">
  <h1 class="display-4">상세글보기</h1>
  <div class="card">
    <div class="card-body">
      <div class="row">
        <div class="col">
          <p class="card-text">글번호 : ${SELCTED_ARTICLE.articleNo }</p>
        </div>
        <div class="col">
          <p class="card-text">작성자아이디 : ${SELCTED_ARTICLE.userId }</p>
        </div>
        <div class="col">
          <p class="card-text">술종류 : ${SELCTED_ARTICLE.category }</p>
        </div>
        <div class="col">
          <p class="card-text">작성일시 : ${SELCTED_ARTICLE.regDate }</p>
        </div>
        <div class="col">
          <p class="card-text">조회수 : ${SELCTED_ARTICLE.readCnt }</p>
        </div>
      </div>
    </div>
  </div>
</div>
<hr>
<div class="container mt-4">
  <div class="card">
    <div class="card-body">
      <h2 class="card-title">제목 : ${SELCTED_ARTICLE.title }</h2>
      <p class="card-text">${SELCTED_ARTICLE.content }</p>
	<hr>
      <c:if test="${AUTH_USER.user_id eq SELCTED_ARTICLE.userId or AUTH_USER.user_id eq 'admin'}">
        <a href="/CJH/whi_board/delete.do?articleNo=${SELCTED_ARTICLE.articleNo }" class="btn btn-primary">글 삭제</a>
      </c:if>

      <c:if test="${AUTH_USER.user_id eq SELCTED_ARTICLE.userId}">
        <a href="/view/CJH/whi_board/whi_board_content_modifyform.jsp?articleNo=${SELCTED_ARTICLE.articleNo }&title=${SELCTED_ARTICLE.title }&content=${SELCTED_ARTICLE.content }" class="btn btn-primary">글 수정</a>
      </c:if>
	
      <a href="javascript:history.go(-1);" class="btn btn-primary">뒤로 가기</a>
    </div>
  </div>
</div>
<hr>


<br>
<br>
<br>
<br>
<br><br><br>
<!-- 여기위로 -->
<%@ include file="/footer.html" %>
</body>
</html>